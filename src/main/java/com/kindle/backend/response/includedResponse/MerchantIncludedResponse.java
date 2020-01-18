package com.kindle.backend.response.includedResponse;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MerchantIncludedResponse {
  private String fullname;

  public MerchantIncludedResponse(String fullname) {
    this.fullname = fullname;
  }

  public String getFullname() {
    return fullname;
  }

  public void setFullname(String fullname) {
    this.fullname = fullname;
  }
}
