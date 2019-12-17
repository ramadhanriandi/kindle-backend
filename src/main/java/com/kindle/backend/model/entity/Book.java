package com.kindle.backend.model.entity;

import com.kindle.backend.model.constant.BookConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = BookConstant.TABLE_NAME)
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = BookConstant.BOOK_SKU)
  private int book_sku;

  @Column(name = BookConstant.BOOK_TITLE)
  private String title;

  @Column(name = BookConstant.BOOK_AUTHOR)
  private String author;

  @Column(name = BookConstant.BOOK_YEAR)
  private int year;

  @Column(name = BookConstant.BOOK_DESCRIPTION)
  private String description;

  @Column(name = BookConstant.BOOK_PRICE)
  private float price;

  @Column(name = BookConstant.BOOK_MERCHANT_ID)
  private int merchant_id;

  @Column(name = BookConstant.BOOK_DOCUMENT)
  private String document;

  public int getBook_sku() {
    return book_sku;
  }

  public void setBook_sku(int book_sku) {
    this.book_sku = book_sku;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public float getPrice() {
    return price;
  }

  public void setPrice(float price) {
    this.price = price;
  }

  public int getMerchant_id() {
    return merchant_id;
  }

  public void setMerchant_id(int merchant_id) {
    this.merchant_id = merchant_id;
  }

  public String getDocument() {
    return document;
  }

  public void setDocument(String document) {
    this.document = document;
  }
}