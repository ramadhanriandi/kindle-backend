package com.kindle.backend.response.includedResponse;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookIncludedResponse {
  private String title;
  private String author;
  private int year;
  private float price;
  private String document;

  public BookIncludedResponse(String title, String author, int year, float price, String document) {
    this.title = title;
    this.author = author;
    this.year = year;
    this.price = price;
    this.document = document;
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

  public float getPrice() {
    return price;
  }

  public void setPrice(float price) {
    this.price = price;
  }

  public String getDocument() {
    return document;
  }

  public void setDocument(String document) {
    this.document = document;
  }
}
