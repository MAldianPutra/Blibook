package com.blibli.blibook.backend.model.entity;

import com.blibli.blibook.backend.model.constants.UserRoleConstant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@EntityListeners(value = {AuditingEntityListener.class})
@Table(name = UserRoleConstant.TABLE_NAME, uniqueConstraints = {
        @UniqueConstraint(columnNames = UserRoleConstant.USER_ROLE_ID),
        @UniqueConstraint(columnNames = UserRoleConstant.USER_ROLE_NAME)
})
public class UserRole implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = UserRoleConstant.USER_ROLE_ID)
    private Integer userRoleId;

    @Column(name = UserRoleConstant.USER_ROLE_NAME)
    private String userRoleName;

    @JsonIgnore
    @OneToMany(mappedBy = "userRole", fetch = FetchType.LAZY)
    private Set<User> users;

}
