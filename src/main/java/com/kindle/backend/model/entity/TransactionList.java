package com.kindle.backend.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kindle.backend.model.constant.TransactionListConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = TransactionListConstant.TABLE_NAME)
public class TransactionList {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = TransactionListConstant.TRANSACTIONLIST_ID)
  private int transactionListId;

  @Column(name = TransactionListConstant.TRANSACTIONLIST_BOOK_SKU)
  private int bookSku;

  @Column(name = TransactionListConstant.TRANSACTIONLIST_MERCHANT_ID)
  private int merchantId;

  @Column(name = TransactionListConstant.TRANSACTIONLIST_TRANSACTION_ID)
  private int transactionId;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = TransactionListConstant.TRANSACTIONLIST_BOOK_SKU, insertable = false, updatable = false)
  private Book bookDetail;

  public int getTransactionListId() {
    return transactionListId;
  }

  public void setTransactionListId(int transactionListId) {
    this.transactionListId = transactionListId;
  }

  public int getBookSku() {
    return bookSku;
  }

  public void setBookSku(int bookSku) {
    this.bookSku = bookSku;
  }

  public int getMerchantId() {
    return merchantId;
  }

  public void setMerchantId(int merchantId) {
    this.merchantId = merchantId;
  }

  public int getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(int transactionId) {
    this.transactionId = transactionId;
  }

  public Book getBookDetail() {
    return bookDetail;
  }

  public void setBookDetail(Book bookDetail) {
    this.bookDetail = bookDetail;
  }
}
