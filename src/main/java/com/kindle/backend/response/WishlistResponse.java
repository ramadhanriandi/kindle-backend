package com.kindle.backend.response;

import com.kindle.backend.model.entity.Book;

public class WishlistResponse {
  private Book bookData;
  private String merchant;

  public WishlistResponse(Book bookData, String merchant) {
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
