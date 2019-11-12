package com.blibli.blibook.backend.model.entity;

import com.blibli.blibook.backend.model.constants.ProductConstant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@EntityListeners(value = {AuditingEntityListener.class})
@Table(name = ProductConstant.TABLE_NAME, uniqueConstraints = {
        @UniqueConstraint(columnNames = ProductConstant.PRODUCT_SKU)
})
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = ProductConstant.PRODUCT_ID)
    private Integer productId;

    @Column(name = ProductConstant.PRODUCT_NAME)
    private String productName;

    @Column(name = ProductConstant.PRODUCT_AUTHOR)
    private String productAuthor;

    @Column(name = ProductConstant.PRODUCT_ISBN)
    private String productIsbn;

    @Column(name = ProductConstant.PRODUCT_SKU)
    private String productSku;

    @Column(name = ProductConstant.PRODUCT_COUNTRY)
    private String productCountry;

    @Column(name = ProductConstant.PRODUCT_PRICE)
    private Float productPrice;

    @Column(name = ProductConstant.PRODUCT_DISCOUNT)
    private Float productDiscount;

    @Column(name = ProductConstant.PRODUCT_DISCOUNTED_PRICE)
    private Float productDiscountPrice;

    @Column(name = ProductConstant.PRODUCT_DESCRIPTION)
    private String productDescription;

    @Column(name = ProductConstant.PRODUCT_LENGTH)
    private String productLength;

    @Column(name = ProductConstant.PRODUCT_RELEASE_YEAR)
    private Integer productReleaseYear;

    @Column(name = ProductConstant.PRODUCT_RELEASE_DATE)
    private Date productReleaseDate;

    @Column(name = ProductConstant.PRODUCT_LANGUAGE)
    private String productLanguage;

    @Column(name = ProductConstant.PRODUCT_VARIANT)
    private String productVariant;

    @Column(name = ProductConstant.PRODUCT_PHOTO_LINK)
    private String productPhotoLink;

    @Column(name = ProductConstant.PRODUCT_ITEM_LINK)
    private String productItemLink;

    @JsonIgnore
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private Set<ProductLibrary> productLibraries;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = ProductConstant.PRODUCT_SHOP_ID)
    private Shop shop;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = ProductConstant.PRODUCT_CATEGORY_ID)
    private ProductCategory productCategory;

//    @JsonIgnore
//    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
//    private Set<Cart> carts;
}
