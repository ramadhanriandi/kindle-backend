package com.kindle.backend.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kindle.backend.model.constant.CategoryConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

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

  @JsonIgnore
  @ManyToMany(mappedBy = "categories", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @Fetch(value = FetchMode.SUBSELECT)
  private List<Book> categorizedBooks;

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

  public List<Book> getCategorizedBooks() {
    return categorizedBooks;
  }

  public void setCategorizedBooks(List<Book> categorizedBooks) {
    this.categorizedBooks = categorizedBooks;
  }
}
