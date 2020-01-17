package com.kindle.backend.response.attributeResponse;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class GetTransactionByTransactionIdResponse {
  private Timestamp date;
  private int total;
  private int customerId;

  public GetTransactionByTransactionIdResponse(Timestamp date, int total, int customerId) {
    this.date = date;
    this.total = total;
    this.customerId = customerId;
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
}
