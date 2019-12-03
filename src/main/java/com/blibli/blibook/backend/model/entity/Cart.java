package com.blibli.blibook.backend.model.entity;

import com.blibli.blibook.backend.model.constants.CartConstant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@EntityListeners(value = {AuditingEntityListener.class})
@Table(name = CartConstant.TABLE_NAME, uniqueConstraints = {
        @UniqueConstraint(columnNames = CartConstant.CART_ID)
})
public class Cart implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = CartConstant.CART_ID)
    private Integer cartId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = CartConstant.CART_USER_ID)
    private User user;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = CartConstant.CART_PRODUCT_ID)
    private Product product;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = CartConstant.CART_SHOP_ID)
    private Shop shop;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = CartConstant.CART_STATUS_ID)
    private CartStatus cartStatus;

}
