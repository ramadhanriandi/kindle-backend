package com.kindle.backend.response.attributeResponse;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetAllMerchantResponse {
  private String fullname;
  private String status;

  public GetAllMerchantResponse(String fullname, String status) {
    this.fullname = fullname;
    this.status = status;
  }

  public String getFullname() {
    return fullname;
  }

  public void setFullname(String fullname) {
    this.fullname = fullname;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
