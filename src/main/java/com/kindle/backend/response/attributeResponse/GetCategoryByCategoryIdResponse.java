package com.kindle.backend.response.attributeResponse;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetCategoryByCategoryIdResponse {
  private String name;

  public GetCategoryByCategoryIdResponse(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
