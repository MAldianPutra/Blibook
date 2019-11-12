package com.blibli.blibook.backend.model.entity;

import com.blibli.blibook.backend.model.constants.UserConstant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@EntityListeners(value = {AuditingEntityListener.class})
@Table(name = UserConstant.TABLE_NAME, uniqueConstraints = {
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

    @Column(name = UserConstant.USER_IS_ADMIN)
    private boolean userIsAdmin;

    @Column(name = UserConstant.USER_IS_BLOCKED)
    private boolean userIsBlocked;

    @Column(name = UserConstant.USER_PHOTO_LINK)
    private String userPhotoLink;

    @JsonIgnore
    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private Shop shop;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<ProductLibrary> productLibraries;

//    @JsonIgnore
//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//    private Set<Cart> carts;
}
