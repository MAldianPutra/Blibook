package com.blibli.blibook.backend.model.entity;

import com.blibli.blibook.backend.model.constants.CartStatusConstant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@EntityListeners(value = {AuditingEntityListener.class})
@Table(name = CartStatusConstant.TABLE_NAME, uniqueConstraints = {
        @UniqueConstraint(columnNames = CartStatusConstant.CART_STATUS_ID),
        @UniqueConstraint(columnNames = CartStatusConstant.CART_STATUS_NAME)
})
public class CartStatus implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = CartStatusConstant.CART_STATUS_ID)
    private Integer cartStatusId;

    @Column(name = CartStatusConstant.CART_STATUS_NAME)
    private String cartStatusName;

    @JsonIgnore
    @OneToMany(mappedBy = "cartStatus", fetch = FetchType.LAZY)
    private Set<User> carts;

}