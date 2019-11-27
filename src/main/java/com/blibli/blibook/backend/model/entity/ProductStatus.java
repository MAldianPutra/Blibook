package com.blibli.blibook.backend.model.entity;

import com.blibli.blibook.backend.model.constants.ProductStatusConstant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@EntityListeners(value = {AuditingEntityListener.class})
@Table(name = ProductStatusConstant.TABLE_NAME, uniqueConstraints = {
        @UniqueConstraint(columnNames = ProductStatusConstant.PRODUCT_STATUS_ID),
        @UniqueConstraint(columnNames = ProductStatusConstant.PRODUCT_STATUS_NAME)
})
public class ProductStatus implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = ProductStatusConstant.PRODUCT_STATUS_ID)
    private Integer productStatusId;

    @Column(name = ProductStatusConstant.PRODUCT_STATUS_NAME)
    private String productStatusName;

    @JsonIgnore
    @OneToMany(mappedBy = "productStatus", fetch = FetchType.LAZY)
    private Set<Product> products;

}
