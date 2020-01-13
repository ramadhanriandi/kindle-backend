package com.kindle.backend.response.relationshipResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionListRelationshipResponse {
  private BaseRelationshipDataResponse merchant;
  private BaseRelationshipDataResponse book;

  public BaseRelationshipDataResponse getMerchant() {
    return merchant;
  }

  public void setMerchant(BaseRelationshipDataResponse merchant) {
    this.merchant = merchant;
  }

  public BaseRelationshipDataResponse getBook() {
    return book;
  }

  public void setBook(BaseRelationshipDataResponse book) {
    this.book = book;
  }
}
