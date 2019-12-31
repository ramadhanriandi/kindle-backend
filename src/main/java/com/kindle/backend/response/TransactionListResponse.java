package com.kindle.backend.response;

import com.kindle.backend.model.entity.Book;
import com.kindle.backend.model.entity.Transaction;

public class TransactionListResponse {
  private Book bookData;
  private Transaction transactionData;

  public TransactionListResponse(Book bookData) {
    this.bookData = bookData;
  }

  public TransactionListResponse(Transaction transactionData, Book bookData){
    this.bookData = bookData;
    this.transactionData = transactionData;
  }

  public Book getBookData() {
    return bookData;
  }

  public void setBookData(Book bookData) {
    this.bookData = bookData;
  }

  public Transaction getTransactionData() { return transactionData; }

  public void setTransactionData(Transaction transactionData) { this.transactionData = transactionData; }
}
