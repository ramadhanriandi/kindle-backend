package com.kindle.backend.response.dataResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataNoRelationResponse<T> {
  private int id;
  private String type;
  private T attributes;

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
