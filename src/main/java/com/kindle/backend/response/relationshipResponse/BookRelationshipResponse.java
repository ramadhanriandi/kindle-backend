package com.kindle.backend.response.relationshipResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookRelationshipResponse{
    private BaseRelationshipDataResponse merchant;

    public BaseRelationshipDataResponse getMerchant() {
        return merchant;
    }

    public void setMerchant(BaseRelationshipDataResponse merchant) {
        this.merchant = merchant;
    }
}
