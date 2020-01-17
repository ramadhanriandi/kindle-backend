package com.kindle.backend.response.dataResponse;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DataNoAttributeResponse {
  private int id;
  private String type;

  public DataNoAttributeResponse(int id, String type) {
    this.id = id;
    this.type = type;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}
