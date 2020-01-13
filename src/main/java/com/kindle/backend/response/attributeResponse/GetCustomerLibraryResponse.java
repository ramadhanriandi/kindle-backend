package com.kindle.backend.response.attributeResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetCustomerLibraryResponse {
  private String document;

  public String getDocument() {
    return document;
  }

  public void setDocument(String document) {
    this.document = document;
  }
}
