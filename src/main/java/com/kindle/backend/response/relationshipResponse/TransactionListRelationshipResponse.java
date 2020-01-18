package com.kindle.backend.response.relationshipResponse;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TransactionListRelationshipResponse {
  private BaseRelationshipDataResponse merchant;
  private BaseRelationshipDataResponse book;

  public TransactionListRelationshipResponse(BaseRelationshipDataResponse merchant, BaseRelationshipDataResponse book) {
    this.merchant = merchant;
    this.book = book;
  }

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
