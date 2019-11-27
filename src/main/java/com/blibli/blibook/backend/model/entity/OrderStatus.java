package com.blibli.blibook.backend.model.entity;

import com.blibli.blibook.backend.model.constants.OrderStatusConstant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@EntityListeners(value = {AuditingEntityListener.class})
@Table(name = OrderStatusConstant.TABLE_NAME, uniqueConstraints = {
        @UniqueConstraint(columnNames = OrderStatusConstant.ORDER_STATUS_ID),
        @UniqueConstraint(columnNames = OrderStatusConstant.ORDER_STATUS_NAME)
})
public class OrderStatus implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = OrderStatusConstant.ORDER_STATUS_ID)
    private Integer orderStatusId;

    @Column(name = OrderStatusConstant.ORDER_STATUS_NAME)
    private String orderStatusName;

    @JsonIgnore
    @OneToMany(mappedBy = "orderStatus", fetch = FetchType.LAZY)
    private Set<User> orders;

}
