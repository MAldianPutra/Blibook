package com.blibli.blibook.backend.model.entity;

import com.blibli.blibook.backend.model.constants.BaseConstant;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Base {

    @Id
    @Column(name = BaseConstant.ID)
    @GeneratedValue(generator = BaseConstant.SYSTEM_UUID)
    @GenericGenerator(name = BaseConstant.SYSTEM_UUID, strategy = BaseConstant.STRATEGY_UUID2)
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
