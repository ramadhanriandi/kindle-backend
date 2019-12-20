package com.kindle.backend.response;

import com.kindle.backend.model.entity.Book;

public class CartResponse {
  private Book bookData;
  private String merchant;

  public CartResponse(Book bookData, String merchant) {
    this.bookData = bookData;
    this.merchant = merchant;
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
