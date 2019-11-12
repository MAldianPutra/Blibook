package com.blibli.blibook.backend.model.entity;

import com.blibli.blibook.backend.model.constants.ProductCategoryConstant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@EntityListeners(value = {AuditingEntityListener.class})
@Table(name = ProductCategoryConstant.TABLE_NAME)
public class ProductCategory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = ProductCategoryConstant.PRODUCT_CATEGORY_ID)
    private Integer productCategoryId;

    @Column(name = ProductCategoryConstant.PRODUCT_CATEGORY_NAME)
    private String productCategoryName;

    @JsonIgnore
    @OneToMany(mappedBy = "productCategory", fetch = FetchType.LAZY)
    private Set<Product> products;

}
