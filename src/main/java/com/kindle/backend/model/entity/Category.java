package com.kindle.backend.model.entity;

import com.kindle.backend.model.constant.CategoryConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = CategoryConstant.TABLE_NAME)
public class Category {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = CategoryConstant.CATEGORY_ID)
  private int categoryId;

  @Column(name = CategoryConstant.CATEGORY_NAME)
  private String name;

  public int getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(int categoryId) {
    this.categoryId = categoryId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
