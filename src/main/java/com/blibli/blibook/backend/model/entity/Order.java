package com.blibli.blibook.backend.model.entity;

import com.blibli.blibook.backend.model.constants.OrderConstant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@EntityListeners(value = {AuditingEntityListener.class})
@Table(name = OrderConstant.TABLE_NAME, uniqueConstraints = {
        @UniqueConstraint(columnNames = OrderConstant.ORDER_ID),
})
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = OrderConstant.ORDER_ID)
    private Integer orderId;

    @JsonIgnore
    @OneToOne(mappedBy = "order", fetch = FetchType.LAZY)
    private Payment payment;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = OrderConstant.ORDER_CART_ID)
    private Cart cart;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = OrderConstant.ORDER_USER_ID)
    private User user;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = OrderConstant.ORDER_PRODUCT_ID)
    private Product product;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = OrderConstant.ORDER_SHOP_ID)
    private Shop shop;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = OrderConstant.ORDER_STATUS_ID)
    private OrderStatus orderStatus;
}
