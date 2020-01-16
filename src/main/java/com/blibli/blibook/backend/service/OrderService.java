package com.blibli.blibook.backend.service;

import com.blibli.blibook.backend.dto.*;
import com.blibli.blibook.backend.model.entity.*;
import com.blibli.blibook.backend.repository.*;
import com.blibli.blibook.backend.service.impl.ObjectMapperServiceImpl;
import com.blibli.blibook.backend.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderStatusRepository orderStatusRepository;
    public Optional<OrderStatus> findOptionalOrderStatusByOrderStatusId(Integer orderStatusId){
        return orderStatusRepository.findById(orderStatusId);
    }
    public OrderStatus findOrderStatusByOrderStatusId(Integer orderStatusId){
        return orderStatusRepository.findFirstByOrderStatusId(orderStatusId);
    }

    @Autowired
    private UserRepository userRepository;
    public Optional<User> findUserId(Integer userId){
        return userRepository.findById(userId);
    }

    @Autowired
    private ProductRepository productRepository;
    public Optional<Product> findProductId(Integer productId){
        return productRepository.findById(productId);
    }

    @Autowired
    private CartRepository cartRepository;
    public Boolean existsCartByUserIdAndProductId(Integer userId, Integer productId){
        return cartRepository.existsCartByUser_UserIdAndProduct_ProductId(userId, productId);
    }
    public Cart findCartByUserIdAndProductId(Integer userId, Integer productId){
        return cartRepository.findByUser_UserIdAndProduct_ProductId(userId, productId);
    }
    public long deleteCart(Integer cartId){
        return cartRepository.deleteByCartId(cartId);
    }

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private OrderServiceImpl orderServiceImpl;

    @Autowired
    private ObjectMapperServiceImpl objectMapperService;

    public Order findFirstByOrderId(Integer orderId){
        return orderRepository.findFirstByOrderId(orderId);
    }

    public ResponseDTO findByUserIdAndOrderStatusId(Integer userId, Integer orderStatusId){
        try{
            List<Order> orderList = orderRepository.findByUser_UserIdAndOrderStatus_OrderStatusId(userId, orderStatusId);
            List<ProductReviewDTO> productReviewDTOS = orderServiceImpl.findOrderList(orderList);
            ArrayList<ProductReviewDTO> data = new ArrayList<>(productReviewDTOS);
            return new ResponseDTO(200, "Success.", data);
        }catch (DataAccessException ex){
            return new ResponseDTO(400, ex.getCause().getMessage(), null);
        }

    }

    public ResponseDTO findByShopIdAndOrderStatusId(Integer shopId, Integer orderStatusId){
        try{
            List<Order> orderList = orderRepository.findByShop_ShopIdAndOrderStatus_OrderStatusId(shopId, orderStatusId);
            List<ProductReviewDTO> productReviewDTOS = orderServiceImpl.findOrderList(orderList);
            ArrayList<ProductReviewDTO> data = new ArrayList<>(productReviewDTOS);
            return new ResponseDTO(200, "Success.", data);
        }catch (DataAccessException ex){
            return new ResponseDTO(400, ex.getCause().getMessage(), null);
        }
    }

    public ResponseDTO findOrderWaitingShopId(Integer shopId, Integer orderStatusId){

        ArrayList<OrderShopDTO> data = new ArrayList<>();
        ResponseDTO response;

        try {
            List<Order> orderList = orderRepository.findByShop_ShopIdAndOrderStatus_OrderStatusId(shopId, orderStatusId);

            if (orderList.isEmpty()) {
                response = new ResponseDTO(404, "Data Not Found!", null);
            } else {
                for (Order order : orderList) {
                    data.add(new OrderShopDTO(order.getOrderId(),
                            objectMapperService.mapToUserDTO(
                                    userRepository.findFirstByUserId(order.getUser().getUserId())),
                            objectMapperService.mapToProductDetailDTO(productRepository.findFirstByProductId(order.getProduct().getProductId()))));
                }
                response = new ResponseDTO(200, "Success", data);
            }
        } catch (DataAccessException ex) {
            response = new ResponseDTO(500, ex.getCause().getMessage(), null);
        }

        return response;
    }

    public ResponseDTO findUserLibrary(Integer userId, Integer orderStatusId){
        try{
            ArrayList<ProductPhotoDTO> productPhotoDTOS =
                    new ArrayList<>(orderServiceImpl.findUserLibrary(userId, orderStatusId));
            return new ResponseDTO(200, "Success.", null);
        }catch (DataAccessException ex){
            return new ResponseDTO(400, ex.getCause().getMessage(), null);
        }
    }

    public boolean findOrderExists(Integer userId, Integer productId){
        return orderRepository.existsOrderByUser_UserIdAndProduct_ProductId(userId, productId);
    }

    public ResponseDTO findAll(){
        try{
            ArrayList<OrderShopDTO> data = new ArrayList<>();
            List<Order> orders = orderRepository.findAll();
            for(Order order : orders){
                data.add(objectMapperService.mapToOrderShopDTO(order));
            }
            return new ResponseDTO(200, "Success.", data);
        }catch (DataAccessException ex){
            return new ResponseDTO(400, ex.getCause().getMessage(), null);
        }
    }

    public Order save(Order order){
        return orderRepository.save(order);
    }

    public ResponseDTO findOrder(Order order) {
        ArrayList<Order> objOrder = new ArrayList<>();
        ResponseDTO response;

        try {
            objOrder.add(orderRepository.findFirstByOrderId(order.getOrderId()));

            if (objOrder.get(0) != null) {
                response = new ResponseDTO(200, "Success", objOrder);
            }
            else {
                response = new ResponseDTO(400, "Failed!", null);
            }
        } catch (DataAccessException ex) {
            response = new ResponseDTO(500, ex.getCause().getMessage(), null);
        }

        return response;
    }

    public ResponseDTO initiateOrder(Integer userId, Integer productId){
        try {
            ArrayList<OrderShopDTO> data = new ArrayList<>();
            Integer orderStatusId = 1;
            if(findOrderExists(userId, productId)){
                Order order = orderRepository.findByUser_UserIdAndProduct_ProductId(userId, productId);
                OrderStatus orderStatus = findOrderStatusByOrderStatusId(order.getOrderStatus().getOrderStatusId());
                if(orderStatus.getOrderStatusName().equals("NOT_PAID")){
                    data.add(objectMapperService.mapToOrderShopDTO(order));
                    return new ResponseDTO(200, "Product ordered before.", data);
                }else{
                    return new ResponseDTO(403, "Product was purchased or waiting for confirmation", null);
                }
            }
            else
            {
                return constructOrder(orderStatusId, userId, productId);
            }
        }catch (DataAccessException ex){
            return new ResponseDTO(400, ex.getMessage(), null);
        }
    }

    private ResponseDTO constructOrder(Integer statusId, Integer userId, Integer productId){
        Optional<OrderStatus> orderStatus = findOptionalOrderStatusByOrderStatusId(statusId);
        Optional<User> user = findUserId(userId);
        Optional<Product> product = findProductId(productId);
        Order newOrder = new Order();
        orderStatus.ifPresent(newOrder::setOrderStatus);
        user.ifPresent(newOrder::setUser);
        product.ifPresent(newOrder::setProduct);
        newOrder.setShop(product.get().getShop());
        orderRepository.save(newOrder);

        return findOrder(newOrder);
    }

    public ResponseDTO orderedProductById(Integer orderId) {
        try{
            Order order = orderRepository.findFirstByOrderId(orderId);
            Product product = productRepository.findFirstByProductId(order.getProduct().getProductId());
            OrderStatus orderStatus = orderStatusRepository.findFirstByOrderStatusId(order.getOrderStatus().getOrderStatusId());

            if(orderStatus.getOrderStatusName().equals("NOT_PAID")){
                ArrayList<ProductDetailDTO> data = new ArrayList<>();
                data.add(objectMapperService.mapToProductDetailDTO(product));
                return new ResponseDTO(200, "Success.", data);
            } else{
                return new ResponseDTO(400, "Order not found", null);
            }

        }catch (DataAccessException ex){
            return new ResponseDTO(400, ex.getMessage(), null);
        }
    }

    public ResponseDTO confirmOrder(Integer id) {
        try{
            // orderStatusId 3 = COMPLETED
            Integer orderStatusId = 3;
            Optional<OrderStatus> orderStatus = findOptionalOrderStatusByOrderStatusId(orderStatusId);
            Order updateOrder = findFirstByOrderId(id);
            updateOrder.setOrderStatus(orderStatus.get());
            save(updateOrder);
            ArrayList<OrderShopDTO> data = new ArrayList<>();
            data.add(objectMapperService.mapToOrderShopDTO(updateOrder));
            return new ResponseDTO(200, "Success", data);
        }catch (DataAccessException ex){
            return new ResponseDTO(400, ex.getMessage(), null);
        }
    }
}
