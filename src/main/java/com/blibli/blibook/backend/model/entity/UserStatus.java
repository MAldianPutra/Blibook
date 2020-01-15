package com.blibli.blibook.backend.model.entity;

import com.blibli.blibook.backend.model.constants.UserStatusConstant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@EntityListeners(value = {AuditingEntityListener.class})
@Table(name = UserStatusConstant.TABLE_NAME, uniqueConstraints = {
        @UniqueConstraint(columnNames = UserStatusConstant.USER_STATUS_ID),
        @UniqueConstraint(columnNames = UserStatusConstant.USER_STATUS_NAME)
})
public class UserStatus implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = UserStatusConstant.USER_STATUS_ID)
    private Integer userStatusId;

    @Column(name = UserStatusConstant.USER_STATUS_NAME)
    private String userStatusName;

    @JsonIgnore
    @OneToMany(mappedBy = "userStatus", fetch = FetchType.LAZY)
    private Set<User> users;

}
