package com.kindle.backend.service;

import com.kindle.backend.model.entity.Book;
import com.kindle.backend.model.entity.Customer;
import com.kindle.backend.model.repository.BookRepository;
import com.kindle.backend.model.repository.CustomerRepository;
import com.kindle.backend.response.CartResponse;
import com.kindle.backend.response.PostResponse;
import com.kindle.backend.response.PutResponse;
import com.kindle.backend.response.WishlistResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private BookRepository bookRepository;

//  private EntityManager entityManager;
//
//  public CustomerService(EntityManager entityManager) {
//    this.entityManager = entityManager;
//  }
//
//  public Optional<Customer> save(Customer customer) {
//    try {
//      entityManager.getTransaction().begin();
//      entityManager.persist(customer);
//      entityManager.getTransaction().commit();
//
//      return Optional.of(customer);
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
//    return Optional.empty();
//  }
//
//  public void deleteByCustomerId(int customerId) {
//    Customer customer = entityManager.find(Customer.class, customerId);
//    if (customer != null) {
//      try {
//        entityManager.getTransaction().begin();
//        customer.getWishlist().forEach(wishlist -> {
//          wishlist.getLikedBook().remove(customer);
//        });
//        entityManager.remove(customer);
//        entityManager.getTransaction().commit();
//      } catch (Exception e) {
//        e.printStackTrace();
//      }
//    }
//  }

  public Customer findByCustomerId(Integer customerId){
    return customerRepository.findFirstByCustomerId(customerId);
  }

  public List<Customer> findAllCustomer(){
    return customerRepository.findAll();
  }

  public Customer save(Customer customer) {
    return customerRepository.save(customer);
  }

  public PutResponse updateCustomer(Integer customerId, Customer customer) {
    PutResponse updateResponse = new PutResponse();
    customer.setCustomerId(customerId);
    Customer customerResponse = customerRepository.save(customer);

    if (customerResponse == null) {
      updateResponse.setCode(401);
      updateResponse.setMessage("Error: update fail");
    } else {
      updateResponse.setCode(200);
      updateResponse.setMessage("Update success");
    }

    return updateResponse;
  }

  public long deleteByCustomerId(Integer customerId) {
    return customerRepository.deleteByCustomerId(customerId);
  }

  public PostResponse login(Customer customer) {
    String email = customer.getEmail();
    String password = customer.getPassword();
    PostResponse loginResponse = new PostResponse();

    Customer customerResponse = customerRepository.findFirstByEmailAndPassword(email, password);

    if (customerResponse == null) {
      loginResponse.setCode(401);
      loginResponse.setMessage("Login failed: wrong email or password");
    } else {
      if (customerResponse.getStatus().equals("Active")) {
        loginResponse.setUserId(customerResponse.getCustomerId());
        loginResponse.setCode(200);
        loginResponse.setMessage("Login success");
      } else {
        loginResponse.setCode(402);
        loginResponse.setMessage("Login failed: your account is " + customerResponse.getStatus());
      }
    }

    return loginResponse;
  }

  public PostResponse register(Customer customer) {
    PostResponse registerResponse = new PostResponse();

    Customer customerResponse = customerRepository.save(customer);

    if (customerResponse == null) {
      registerResponse.setCode(401);
      registerResponse.setMessage("Error: register fail");
    } else {
      registerResponse.setUserId(customerResponse.getCustomerId());
      registerResponse.setCode(200);
      registerResponse.setMessage("Register success");
    }

    return registerResponse;
  }

  @Transactional
  public List<Book> findCustomerLibrary(Integer customerId){
    Customer customerResponse = customerRepository.findFirstByCustomerId(customerId);

    return customerResponse.getLibrary();
  }

  public List<WishlistResponse> findCustomerWishlist(Integer customerId){
    Customer customerResponse = customerRepository.findFirstByCustomerId(customerId);
    List<Book> wishlist = customerResponse.getWishlist();
    List<WishlistResponse> wishlistResponses = new ArrayList<>();
    for (Book book : wishlist) {
      wishlistResponses.add(new WishlistResponse(book, book.getMerchant().getFullname()));
    }

    return wishlistResponses;
  }

  public List<CartResponse> findCustomerCart(Integer customerId){
    Customer customerResponse = customerRepository.findFirstByCustomerId(customerId);
    List<Book> cart = customerResponse.getCart();
    List<CartResponse> cartResponses = new ArrayList<>();
    for (Book book : cart) {
      cartResponses.add(new CartResponse(book, book.getMerchant().getFullname()));
    }

    return cartResponses;
  }

  public Customer addCustomerWishlist(Integer customerId, Integer bookSku) {
    Customer customerResponse = customerRepository.findFirstByCustomerId(customerId);
    Book bookResponse = bookRepository.findFirstByBookSku(bookSku);
    customerResponse.getWishlist().add(bookResponse);
    bookResponse.getLikedBook().add(customerResponse);
    bookRepository.save(bookResponse);

    return customerRepository.save(customerResponse);
  }
}
