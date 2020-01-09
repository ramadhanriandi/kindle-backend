package com.kindle.backend.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kindle.backend.model.constant.AdminConstant;

import javax.persistence.*;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = AdminConstant.TABLE_NAME)
public class Admin{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = AdminConstant.ADMIN_ID)
  private int adminId;

  @Column(name = AdminConstant.ADMIN_USERNAME)
  private String username;

  @Column(name = AdminConstant.ADMIN_EMAIL)
  private String email;

  @Column(name = AdminConstant.ADMIN_PASSWORD)
  private String password;

  public int getAdminId() {
    return adminId;
  }

  public void setAdminId(int adminId) {
    this.adminId = adminId;
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





}
