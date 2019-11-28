package com.blibli.blibook.backend.model.entity;

import com.blibli.blibook.backend.model.constants.PaymentConstant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@EntityListeners(value = {AuditingEntityListener.class})
@Table(name = PaymentConstant.TABLE_NAME, uniqueConstraints = {
        @UniqueConstraint(columnNames = PaymentConstant.PAYMENT_ID),
        @UniqueConstraint(columnNames = PaymentConstant.PAYMENT_ORDER_ID)
})
public class Payment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = PaymentConstant.PAYMENT_ID)
    private Integer paymentId;

    @Column(name = PaymentConstant.PAYMENT_DATE)
    private LocalDateTime datePayment;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = PaymentConstant.PAYMENT_ORDER_ID)
    private Order order;

}
