package com.kindle.backend.response.relationshipResponse;

import com.kindle.backend.response.dataResponse.DataNoAttributeResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class BaseRelationshipDataResponse {
  private List<DataNoAttributeResponse> data;

  public BaseRelationshipDataResponse(List<DataNoAttributeResponse> data) {
    this.data = data;
  }

  public List<DataNoAttributeResponse> getData() {
    return data;
  }

  public void setData(List<DataNoAttributeResponse> data) {
    this.data.addAll(data);
  }
}
