package com.kindle.backend.response;

import com.kindle.backend.model.entity.Book;
import com.kindle.backend.model.entity.Merchant;

public class BookDetailResponse {
  private int code;
  private String message;
  private Book bookData;
  private Merchant merchantData;

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

  public Merchant getMerchantData() {
    return merchantData;
  }

  public void setMerchantData(Merchant merchantData) {
    this.merchantData = merchantData;
  }
}
