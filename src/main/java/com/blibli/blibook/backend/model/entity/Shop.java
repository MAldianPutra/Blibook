package com.blibli.blibook.backend.model.entity;

import com.blibli.blibook.backend.model.constants.ShopConstant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@EntityListeners(value = {AuditingEntityListener.class})
@Table(name = ShopConstant.TABLE_NAME, uniqueConstraints = {
        @UniqueConstraint(columnNames = ShopConstant.SHOP_ID),
        @UniqueConstraint(columnNames = ShopConstant.SHOP_USER_ID),
        @UniqueConstraint(columnNames = ShopConstant.SHOP_NAME)
})
public class Shop implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = ShopConstant.SHOP_ID)
    private Integer shopId;

    @Column(name = ShopConstant.SHOP_NAME)
    private String shopName;

    @Column(name = ShopConstant.SHOP_ADDRESS)
    private String shopAddress;

    @Column(name = ShopConstant.SHOP_CITY)
    private String shopCity;

    @Column(name = ShopConstant.SHOP_PROVINCE)
    private String shopProvince;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = ShopConstant.SHOP_USER_ID)
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "shop", fetch = FetchType.LAZY)
    private Set<Product> products;

    @JsonIgnore
    @OneToMany(mappedBy = "shop", fetch = FetchType.LAZY)
    private Set<Cart> carts;

    @JsonIgnore
    @OneToMany(mappedBy = "shop", fetch = FetchType.LAZY)
    private Set<Order> orders;

}
