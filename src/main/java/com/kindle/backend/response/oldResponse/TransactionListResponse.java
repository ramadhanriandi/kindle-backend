package com.kindle.backend.response.oldResponse;

import com.kindle.backend.model.entity.Book;

public class TransactionListResponse {
  private Book bookData;

  public TransactionListResponse(Book bookData) {
    this.bookData = bookData;
  }

  public Book getBookData() {
    return bookData;
  }

  public void setBookData(Book bookData) {
    this.bookData = bookData;
  }
}
