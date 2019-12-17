package com.kindle.backend.model.entity;

import com.kindle.backend.model.constant.MerchantConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
}
