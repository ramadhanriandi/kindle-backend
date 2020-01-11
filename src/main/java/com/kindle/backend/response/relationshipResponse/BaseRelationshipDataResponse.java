package com.kindle.backend.response.relationshipResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseRelationshipDataResponse<T> {
  private List<T> data;

  public List<T> getData() {
    return data;
  }

  public void setData(List<T> data) {
    this.data.addAll(data);
  }
}
