package com.kindle.backend.response.relationshipResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MerchantOrderRelationshipResponse {
  private BaseRelationshipDataResponse book;

  public BaseRelationshipDataResponse getBook() {
    return book;
  }

  public void setBook(BaseRelationshipDataResponse book) {
    this.book = book;
  }
}
