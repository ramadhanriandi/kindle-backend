package com.kindle.backend.response.relationshipResponse;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookRelationshipResponse{
    private BaseRelationshipDataResponse merchant;

    public BookRelationshipResponse(BaseRelationshipDataResponse merchant) {
        this.merchant = merchant;
    }

    public BaseRelationshipDataResponse getMerchant() {
        return merchant;
    }

    public void setMerchant(BaseRelationshipDataResponse merchant) {
        this.merchant = merchant;
    }
}
