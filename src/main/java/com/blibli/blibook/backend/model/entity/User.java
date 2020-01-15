package com.blibli.blibook.backend.model.entity;

import com.blibli.blibook.backend.model.constants.UserConstant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(value = {AuditingEntityListener.class})
@Table(name = UserConstant.TABLE_NAME, uniqueConstraints = {
        @UniqueConstraint(columnNames = UserConstant.USER_ID),
        @UniqueConstraint(columnNames = UserConstant.USER_NAME),
        @UniqueConstraint(columnNames = UserConstant.USER_EMAIL)
})
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = UserConstant.USER_ID)
    private Integer userId;

    @Column(name = UserConstant.USER_EMAIL)
    private String userEmail;

    @Column(name = UserConstant.USER_NAME)
    private String userName;

    @Column(name = UserConstant.USER_PASSWORD)
    private String userPassword;

    @Column(name = UserConstant.USER_PASSWORD_CONFIRMATION)
    private String userPasswordConfirmation;

    @Column(name = UserConstant.USER_BIRTHDATE)
    private String userBirthdate;

    @Column(name = UserConstant.USER_HANDPHONE)
    private String userHandphone;

    @Column(name = UserConstant.USER_GENDER)
    private String userGender;

    @JsonIgnore
    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private Shop shop;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Order> orders;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Cart> carts;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = UserConstant.USER_STATUS_ID)
    private UserStatus userStatus;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = UserConstant.USER_ROLE_ID)
    private UserRole userRole;

}
