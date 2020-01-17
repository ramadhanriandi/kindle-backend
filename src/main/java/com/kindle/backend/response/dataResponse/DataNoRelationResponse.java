package com.kindle.backend.response.dataResponse;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DataNoRelationResponse<T> {
  private int id;
  private String type;
  private T attributes;

  public DataNoRelationResponse(int id, String type, T attributes) {
    this.id = id;
    this.type = type;
    this.attributes = attributes;
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

  public T getAttributes() {
    return attributes;
  }

  public void setAttributes(T attributes) {
    this.attributes = attributes;
  }
}
