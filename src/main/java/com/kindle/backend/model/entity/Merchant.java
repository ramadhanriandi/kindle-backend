package com.kindle.backend.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kindle.backend.model.constant.MerchantConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = MerchantConstant.TABLE_NAME)
public class Merchant {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = MerchantConstant.MERCHANT_ID)
  private int merchantId;

  @Column(name = MerchantConstant.MERCHANT_USERNAME)
  private String username;

  @Column(name = MerchantConstant.MERCHANT_EMAIL)
  private String email;

  @Column(name = MerchantConstant.MERCHANT_PASSWORD)
  private String password;

  @Column(name = MerchantConstant.MERCHANT_FULLNAME)
  private String fullname;

  @Column(name = MerchantConstant.MERCHANT_DESCRIPTION)
  private String description;

  @Column(name = MerchantConstant.MERCHANT_PHONE)
  private String phone;

  @Column(name = MerchantConstant.MERCHANT_STATUS)
  private String status;

  @JsonIgnore
  @OneToMany(fetch = FetchType.EAGER, mappedBy = "merchant")
  private List<Book> catalog;

  public int getMerchantId() {
    return merchantId;
  }

  public void setMerchantId(int merchantId) {
    this.merchantId = merchantId;
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

  public List<Book> getCatalog() {
    return catalog;
  }

  public void setCatalog(List<Book> catalog) {
    this.catalog = catalog;
  }
}
