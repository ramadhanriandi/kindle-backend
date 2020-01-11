package com.kindle.backend.response.dataResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataWithRelationOnlyResponse<T> {
  private int id;
  private String type;
  private T relationships;

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

  public T getRelationships() {
    return relationships;
  }

  public void setRelationships(T relationships) {
    this.relationships = relationships;
  }
}
