package com.kindle.backend.response.dataResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataCompleteResponse<T1, T2> {
  private int id;
  private String type;
  private T1 attributes;
  private T2 relationships;

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

  public T1 getAttributes() {
    return attributes;
  }

  public void setAttributes(T1 attributes) {
    this.attributes = attributes;
  }

  public T2 getRelationships() {
    return relationships;
  }

  public void setRelationships(T2 relationships) {
    this.relationships = relationships;
  }
}
