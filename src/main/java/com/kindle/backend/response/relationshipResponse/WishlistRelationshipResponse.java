package com.kindle.backend.response.relationshipResponse;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WishlistRelationshipResponse {
  private BaseRelationshipDataResponse merchant;

  public WishlistRelationshipResponse(BaseRelationshipDataResponse merchant) {
    this.merchant = merchant;
  }

  public BaseRelationshipDataResponse getMerchant() {
    return merchant;
  }

  public void setMerchant(BaseRelationshipDataResponse merchant) {
    this.merchant = merchant;
  }
}
