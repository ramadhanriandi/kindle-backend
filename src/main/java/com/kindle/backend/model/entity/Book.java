package com.kindle.backend.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kindle.backend.model.constant.BookConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = BookConstant.TABLE_NAME)
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = BookConstant.BOOK_SKU)
  private int bookSku;

  @Column(name = BookConstant.BOOK_TITLE)
  private String title;

  @Column(name = BookConstant.BOOK_AUTHOR)
  private String author;

  @Column(name = BookConstant.BOOK_YEAR)
  private int year;

  @Column(name = BookConstant.BOOK_DESCRIPTION)
  private String description;

  @Column(name = BookConstant.BOOK_PRICE)
  private float price;

  @Column(name = BookConstant.BOOK_MERCHANT_ID)
  private int merchantId;

  @Column(name = BookConstant.BOOK_DOCUMENT)
  private String document;

  @Column(name = BookConstant.BOOK_VARIANT)
  private String variant;

  @Column(name = BookConstant.BOOK_URL)
  private String url;

  @Column(name = BookConstant.BOOK_CATEGORIES)
  private String categories;

  @JsonIgnore
  @ManyToMany(mappedBy = "library", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
  private List<Customer> ownerBook;

  @JsonIgnore
  @LazyCollection(LazyCollectionOption.FALSE)
  @OneToMany(mappedBy = "bookDetail", fetch = FetchType.EAGER)
  private List<TransactionList> orderList;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "merchant_id", nullable = false, insertable = false, updatable = false)
  private Merchant merchant;

  @JsonIgnore
  @ManyToMany(mappedBy = "wishlist", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
  private List<Customer> likedBook;

  @JsonIgnore
  @ManyToMany(mappedBy = "cart", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
  private List<Customer> cartedBook;

  public int getBookSku() {
    return bookSku;
  }

  public void setBookSku(int bookSku) {
    this.bookSku = bookSku;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public float getPrice() {
    return price;
  }

  public void setPrice(float price) {
    this.price = price;
  }

  public String getDocument() {
    return document;
  }

  public void setDocument(String document) {
    this.document = document;
  }

  public int getMerchantId() {
    return merchantId;
  }

  public void setMerchantId(int merchantId) {
    this.merchantId = merchantId;
  }

  public String getVariant() {
    return variant;
  }

  public void setVariant(String variant) {
    this.variant = variant;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public List<Customer> getOwnerBook() {
    return ownerBook;
  }

  public void setOwnerBook(List<Customer> ownerBook) {
    this.ownerBook = ownerBook;
  }

  public List<TransactionList> getOrderList() {
    return orderList;
  }

  public void setOrderList(List<TransactionList> orderList) {
    this.orderList = orderList;
  }

  public Merchant getMerchant() {
    return merchant;
  }

  public void setMerchant(Merchant merchant) {
    this.merchant = merchant;
  }

  public List<Customer> getLikedBook() {
    return likedBook;
  }

  public void setLikedBook(List<Customer> likedBook) {
    this.likedBook = likedBook;
  }

  public List<Customer> getCartedBook() {
    return cartedBook;
  }

  public void setCartedBook(List<Customer> cartedBook) {
    this.cartedBook = cartedBook;
  }

  public String getCategories() {
    return categories;
  }

  public void setCategories(String categories) {
    this.categories = categories;
  }
}
