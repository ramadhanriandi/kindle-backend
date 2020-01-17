package com.kindle.backend.response.attributeResponse;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class GetAllTransactionByCustomerIdResponse {
  private Timestamp date;
  private int total;

  public GetAllTransactionByCustomerIdResponse(Timestamp date, int total) {
    this.date = date;
    this.total = total;
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
}
