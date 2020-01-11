package com.kindle.backend.response.statusResponse;

import com.kindle.backend.response.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuccessDataWithIncludedResponse<T1, T2> extends BaseResponse {
  private int code;
  private String status;
  private List<T1> data;
  private List<T2> included;

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

  public List<T1> getData() {
    return data;
  }

  public void setData(List<T1> data) {
    this.data.addAll(data);
  }

  public List<T2> getIncluded() {
    return included;
  }

  public void setIncluded(List<T2> included) {
    this.included.addAll(included);
  }
}
