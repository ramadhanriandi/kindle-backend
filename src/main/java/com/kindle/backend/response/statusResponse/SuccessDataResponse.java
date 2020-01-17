package com.kindle.backend.response.statusResponse;

import com.kindle.backend.response.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class SuccessDataResponse<T> extends BaseResponse {
  private int code;
  private String status;
  private List<T> data;

  public SuccessDataResponse(int code, String status, List<T> data) {
    this.code = code;
    this.status = status;
    this.data = data;
  }

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

  public List<T> getData() {
    return data;
  }

  public void setData(List<T> data) {
    this.data.addAll(data);
  }
}
