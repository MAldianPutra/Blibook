package com.blibli.blibook.backend.model.entity;


import com.blibli.blibook.backend.model.constants.ProductLibraryConstant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@EntityListeners(value = {AuditingEntityListener.class})
@Table(name = ProductLibraryConstant.TABLE_NAME)
public class ProductLibrary implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = ProductLibraryConstant.PRODUCT_LIBRARY_ID)
    private int productLibraryId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = ProductLibraryConstant.PRODUCT_LIBRARY_USER_ID)
    private User user;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = ProductLibraryConstant.PRODUCT_LIBRARY_PRODUCT_ID)
    private Product product;

}
