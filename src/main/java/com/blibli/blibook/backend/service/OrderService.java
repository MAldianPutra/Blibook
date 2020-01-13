package com.blibli.blibook.backend.service;

import com.blibli.blibook.backend.dto.*;
import com.blibli.blibook.backend.model.entity.*;
import com.blibli.blibook.backend.repository.*;
import com.blibli.blibook.backend.service.impl.ObjectMapperServiceImpl;
import com.blibli.blibook.backend.service.impl.OrderServiceImpl;
import org.aspectj.weaver.ast.Or;
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

    public List<ProductReviewDTO> findByUserIdAndOrderStatusId(Integer userId, Integer orderStatusId){
        List<Order> orderList = orderRepository.findByUser_UserIdAndOrderStatus_OrderStatusId(userId, orderStatusId);
        return orderServiceImpl.findOrderList(orderList);
    }

    public List<ProductReviewDTO> findByShopIdAndOrderStatusId(Integer shopId, Integer orderStatusId){
        List<Order> orderList = orderRepository.findByShop_ShopIdAndOrderStatus_OrderStatusId(shopId, orderStatusId);
        return orderServiceImpl.findOrderList(orderList);
    }

    public ResponseDTO findOrderWaitingShopId(Integer shopId, Integer orderStatusId){

        ArrayList<OrderShopDTO> orderShopList = new ArrayList<>();
        ResponseDTO response;

        try {
            List<Order> orderList = orderRepository.findByShop_ShopIdAndOrderStatus_OrderStatusId(shopId, orderStatusId);

            if (orderList.isEmpty()) {
                response = new ResponseDTO(404, "Data Not Found!", null);
            } else {
                for (Order order : orderList) {
                    orderShopList.add(new OrderShopDTO(order.getOrderId(),
                            objectMapperService.mapToUserDTO(
                                    userRepository.findFirstByUserId(order.getUser().getUserId())),
                            objectMapperService.mapToProductDetail(productRepository.findFirstByProductId(order.getProduct().getProductId()))));
                }
                response = new ResponseDTO(200, "Success", orderShopList);
            }
        } catch (DataAccessException ex) {
            response = new ResponseDTO(500, ex.getCause().getMessage(), null);
        }

        return response;
    }

    public List<ProductPhotoDTO> findUserLibrary(Integer userId, Integer orderStatusId){
        return orderServiceImpl.findUserLibrary(userId, orderStatusId);
    }

    public boolean findOrderExists(Integer userId, Integer productId){
        return orderRepository.existsOrderByUser_UserIdAndProduct_ProductId(userId, productId);
    }

    public List<Order> findAll(){
        return orderRepository.findAll();
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
                data.add(objectMapperService.mapToProductDetail(product));
                return new ResponseDTO(200, "Success.", data);
            } else{
                return new ResponseDTO(400, "Order not found", null);
            }

        }catch (DataAccessException ex){
            return new ResponseDTO(400, ex.getMessage(), null);
        }
    }
}
