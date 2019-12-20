package com.kindle.backend.response;

import com.kindle.backend.model.entity.Book;

public class BookDetailResponse {
  private int code;
  private String message;
  private Book bookData;
  private String merchant;

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Book getBookData() {
    return bookData;
  }

  public void setBookData(Book bookData) {
    this.bookData = bookData;
  }

  public String getMerchant() {
    return merchant;
  }

  public void setMerchant(String merchant) {
    this.merchant = merchant;
  }
}
