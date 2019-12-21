package com.kindle.backend.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kindle.backend.model.constant.CartConstant;
import com.kindle.backend.model.constant.CustomerConstant;
import com.kindle.backend.model.constant.LibraryConstant;
import com.kindle.backend.model.constant.WishlistConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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

  @JsonIgnore
  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinTable(
          name = LibraryConstant.TABLE_NAME,
          joinColumns = @JoinColumn(name = LibraryConstant.CUSTOMER_ID),
          inverseJoinColumns = @JoinColumn(name = LibraryConstant.BOOK_SKU)
  )
  private List<Book> library;

  @JsonIgnore
  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinTable(
          name = WishlistConstant.TABLE_NAME,
          joinColumns = @JoinColumn(name = WishlistConstant.CUSTOMER_ID),
          inverseJoinColumns = @JoinColumn(name = WishlistConstant.BOOK_SKU)
  )
  private List<Book> wishlist;

  @JsonIgnore
  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinTable(
          name = CartConstant.TABLE_NAME,
          joinColumns = @JoinColumn(name = CartConstant.CUSTOMER_ID),
          inverseJoinColumns = @JoinColumn(name = CartConstant.BOOK_SKU)
  )
  private List<Book> cart;

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

  public List<Book> getLibrary() {
    return library;
  }

  public void setLibrary(List<Book> library) {
    this.library = library;
  }

  public List<Book> getWishlist() {
    return wishlist;
  }

  public void setWishlist(List<Book> wishlist) {
    this.wishlist = wishlist;
  }

  public List<Book> getCart() {
    return cart;
  }

  public void setCart(List<Book> cart) {
    this.cart = cart;
  }
}
