package com.kindle.backend.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kindle.backend.model.constant.TransactionConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = TransactionConstant.TABLE_NAME)
public class Transaction {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = TransactionConstant.TRANSACTION_ID)
  private int transactionId;

  @Column(name = TransactionConstant.TRANSACTION_DATE, insertable = false, updatable = false)
  private Timestamp date;

  @Column(name = TransactionConstant.TRANSACTION_TOTAL)
  private int total;

  @Column(name = TransactionConstant.TRANSACTION_CUSTOMER_ID)
  private int customerId;

  @JsonIgnore
  @LazyCollection(LazyCollectionOption.FALSE)
  @OneToMany(mappedBy = "transactionDetail", fetch = FetchType.EAGER)
  private List<TransactionList> transactionOrderList;

  public int getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(int transactionId) {
    this.transactionId = transactionId;
  }

  public Timestamp getDate() {
    return date;
  }

  public void setDate(Timestamp date) {
    this.date = date;
  }

  public int getTotal() {
    return total;
  }

  public void setTotal(int total) {
    this.total = total;
  }

  public int getCustomerId() {
    return customerId;
  }

  public void setCustomerId(int customerId) {
    this.customerId = customerId;
  }

  public List<TransactionList> getTransactionOrderList() {
    return transactionOrderList;
  }

  public void setTransactionOrderList(List<TransactionList> transactionOrderList) {
    this.transactionOrderList = transactionOrderList;
  }

}
