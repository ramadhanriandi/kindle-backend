package com.kindle.backend.model.entity;

import com.kindle.backend.model.constant.CustomerConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = CustomerConstant.TABLE_NAME)
public class Customer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = CustomerConstant.CUSTOMER_ID)
  private int customerId;

  @Column(name = CustomerConstant.CUSTOMER_USERNAME)
  private String username;

  @Column(name = CustomerConstant.CUSTOMER_EMAIL)
  private String email;

  @Column(name = CustomerConstant.CUSTOMER_PASSWORD)
  private String password;

  @Column(name = CustomerConstant.CUSTOMER_STATUS)
  private String status;

  public int getCustomerId() {
    return customerId;
  }

  public void setCustomerId(int customerId) {
    this.customerId = customerId;
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

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
