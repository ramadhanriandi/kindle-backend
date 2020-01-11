package com.kindle.backend.response.statusResponse;

import com.kindle.backend.response.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuccessDataResponse<T> extends BaseResponse {
  private int code;
  private String status;
  private List<T> data;

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
