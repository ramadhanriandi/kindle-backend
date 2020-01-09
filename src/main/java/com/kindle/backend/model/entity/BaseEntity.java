package com.kindle.backend.model.entity;

import com.kindle.backend.model.constant.BaseEntityConstant;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
abstract class BaseEntity implements Serializable {

  @Id
  @Column(name = BaseEntityConstant.ID)
  @GeneratedValue(generator = BaseEntityConstant.SYSTEM_UUID)
  @GenericGenerator(name = BaseEntityConstant.SYSTEM_UUID, strategy = BaseEntityConstant.STRATEGY_UUID2)
  private Integer id;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }
}

