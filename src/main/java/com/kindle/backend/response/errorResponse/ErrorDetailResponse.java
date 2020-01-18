package com.kindle.backend.response.errorResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorDetailResponse {
  private int status;
  private String detail;

  public ErrorDetailResponse(int status, String detail) {
    this.status = status;
    this.detail = detail;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public String getDetail() {
    return detail;
  }

  public void setDetail(String detail) {
    this.detail = detail;
  }
}
