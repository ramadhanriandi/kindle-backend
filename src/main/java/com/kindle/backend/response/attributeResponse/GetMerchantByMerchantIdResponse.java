package com.kindle.backend.response.attributeResponse;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetMerchantByMerchantIdResponse {
  private String username;
  private String email;
  private String password;
  private String fullname;
  private String description;
  private String phone;
  private String status;

  public GetMerchantByMerchantIdResponse(String username, String email, String password, String fullname, String description, String phone, String status) {
    this.username = username;
    this.email = email;
    this.password = password;
    this.fullname = fullname;
    this.description = description;
    this.phone = phone;
    this.status = status;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getFullname() {
    return fullname;
  }

  public void setFullname(String fullname) {
    this.fullname = fullname;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
