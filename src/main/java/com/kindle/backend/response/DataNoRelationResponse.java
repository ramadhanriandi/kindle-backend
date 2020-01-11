package com.kindle.backend.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataNoRelationResponse<T> {
  private int id;
  private String type;
  private List<T> attributes;

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

  public List<T> getAttributes() {
    return attributes;
  }

  public void setAttributes(List<T> attributes) {
    this.attributes = attributes;
  }
}
