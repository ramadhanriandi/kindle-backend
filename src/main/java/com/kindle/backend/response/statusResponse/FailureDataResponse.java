package com.kindle.backend.response.statusResponse;

import com.kindle.backend.response.BaseResponse;
import com.kindle.backend.response.errorResponse.ErrorDetailResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FailureDataResponse extends BaseResponse {
  private int code;
  private String status;
  private List<ErrorDetailResponse> errors;

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public List<ErrorDetailResponse> getErrors() {
    return errors;
  }

  public void setErrors(List<ErrorDetailResponse> errors) {
    this.errors = errors;
  }
}
