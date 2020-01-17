package com.kindle.backend.response.relationshipResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MerchantOrderRelationshipResponse {
  private BaseRelationshipDataResponse transaction;
  private BaseRelationshipDataResponse book;

  public BaseRelationshipDataResponse getTransaction() {
    return transaction;
  }

  public void setTransaction(BaseRelationshipDataResponse transaction) {
    this.transaction = transaction;
  }

  public BaseRelationshipDataResponse getBook() {
    return book;
  }

  public void setBook(BaseRelationshipDataResponse book) {
    this.book = book;
  }
}
