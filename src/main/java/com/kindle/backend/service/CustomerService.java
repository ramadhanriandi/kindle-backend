package com.kindle.backend.service;

import com.kindle.backend.model.entity.Book;
import com.kindle.backend.model.entity.Customer;
import com.kindle.backend.model.repository.BookRepository;
import com.kindle.backend.model.repository.CustomerRepository;
import com.kindle.backend.response.BaseResponse;
import com.kindle.backend.response.attributeResponse.GetAllCustomerResponse;
import com.kindle.backend.response.dataResponse.DataNoAttributeResponse;
import com.kindle.backend.response.dataResponse.DataNoRelationResponse;
import com.kindle.backend.response.errorResponse.ErrorDetailResponse;
import com.kindle.backend.response.oldResponse.CartResponse;
import com.kindle.backend.response.oldResponse.PostResponse;
import com.kindle.backend.response.oldResponse.WishlistResponse;
import com.kindle.backend.response.statusResponse.FailureDataResponse;
import com.kindle.backend.response.statusResponse.SuccessDataResponse;
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

  public BaseResponse findAllCustomer(){
    List<Customer> customers = customerRepository.findAll();

    if (customers == null) {
      ErrorDetailResponse errorDetailResponse = new ErrorDetailResponse(404, "Customer not found");

      List<ErrorDetailResponse> errorDetailResponses = new ArrayList<>();
      errorDetailResponses.add(errorDetailResponse);
      FailureDataResponse failureDataResponse = new FailureDataResponse(400, "Bad Request", errorDetailResponses);

      return failureDataResponse;
    } else {
      List<DataNoRelationResponse> dataNoRelationResponses = new ArrayList<>();

      for (Customer customer : customers) {
        GetAllCustomerResponse getAllCustomerResponse = new GetAllCustomerResponse(customer.getUsername(), customer.getStatus());
        DataNoRelationResponse<GetAllCustomerResponse> dataNoRelationResponse = new DataNoRelationResponse<>(customer.getCustomerId(), "customer", getAllCustomerResponse);
        dataNoRelationResponses.add(dataNoRelationResponse);
      }

      SuccessDataResponse<DataNoRelationResponse> successDataResponse = new SuccessDataResponse<>(200, "OK", dataNoRelationResponses);

      return successDataResponse;
    }
  }

  public BaseResponse findByCustomerId(Integer customerId){
    Customer customer = customerRepository.findFirstByCustomerId(customerId);

    if (customer == null) {
      ErrorDetailResponse errorDetailResponse = new ErrorDetailResponse(404, "CustomerId not found");

      List<ErrorDetailResponse> errorDetailResponses = new ArrayList<>();
      errorDetailResponses.add(errorDetailResponse);
      FailureDataResponse failureDataResponse = new FailureDataResponse(400, "Bad Request", errorDetailResponses);

      return failureDataResponse;
    } else {
      List<DataNoRelationResponse> dataNoRelationResponses = new ArrayList<>();

      GetAllCustomerResponse getAllCustomerResponse = new GetAllCustomerResponse(customer.getUsername(), customer.getStatus());
      DataNoRelationResponse<GetAllCustomerResponse> dataNoRelationResponse = new DataNoRelationResponse<>(customerId, "customer", getAllCustomerResponse);
      dataNoRelationResponses.add(dataNoRelationResponse);

      SuccessDataResponse<DataNoRelationResponse> successDataResponse = new SuccessDataResponse<>(200, "OK", dataNoRelationResponses);

      return successDataResponse;
    }
  }

  public BaseResponse save(Customer customer) {
    Customer customerResponse = customerRepository.save(customer);

    if (customerResponse == null) {
      ErrorDetailResponse errorDetailResponse = new ErrorDetailResponse(500, "Cannot create customer data");

      List<ErrorDetailResponse> errorDetailResponses = new ArrayList<>();
      errorDetailResponses.add(errorDetailResponse);
      FailureDataResponse failureDataResponse = new FailureDataResponse(500, "Internal server error", errorDetailResponses);

      return failureDataResponse;
    } else {
      DataNoAttributeResponse dataNoAttributeResponse = new DataNoAttributeResponse(customerResponse.getCustomerId(), "customer");

      List<DataNoAttributeResponse> dataNoAttributeResponses = new ArrayList<>();
      dataNoAttributeResponses.add(dataNoAttributeResponse);
      SuccessDataResponse<DataNoAttributeResponse> successDataResponse = new SuccessDataResponse<>(201, "Created", dataNoAttributeResponses);

      return successDataResponse;
    }
  }

  public BaseResponse updateCustomer(Integer customerId, Customer customer) {
    Customer fetchResponse = customerRepository.findFirstByCustomerId(customerId);

    if (fetchResponse == null) {
      ErrorDetailResponse errorDetailResponse = new ErrorDetailResponse(404, "CustomerId not found");

      List<ErrorDetailResponse> errorDetailResponses = new ArrayList<>();
      errorDetailResponses.add(errorDetailResponse);
      FailureDataResponse failureDataResponse = new FailureDataResponse(400, "Bad Request", errorDetailResponses);

      return failureDataResponse;
    } else {
      customer.setCustomerId(customerId);
      Customer customerResponse = customerRepository.save(customer);

      if (customerResponse == null) {
        ErrorDetailResponse errorDetailResponse = new ErrorDetailResponse(500, "Cannot update customer data");

        List<ErrorDetailResponse> errorDetailResponses = new ArrayList<>();
        errorDetailResponses.add(errorDetailResponse);
        FailureDataResponse failureDataResponse = new FailureDataResponse(500, "Internal server error", errorDetailResponses);

        return failureDataResponse;
      } else {
        DataNoAttributeResponse dataNoAttributeResponse = new DataNoAttributeResponse(customerId, "customer");

        List<DataNoAttributeResponse> dataNoAttributeResponses = new ArrayList<>();
        dataNoAttributeResponses.add(dataNoAttributeResponse);
        SuccessDataResponse<DataNoAttributeResponse> successDataResponse = new SuccessDataResponse<>(200, "OK", dataNoAttributeResponses);

        return successDataResponse;
      }
    }
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

  public boolean isOnLibrary(Integer customerId, Integer bookSku){
    Customer customerResponse = customerRepository.findFirstByCustomerId(customerId);
    List<Book> library = customerResponse.getLibrary();
    for (Book book : library) {
      if (book.getBookSku() == bookSku) {
        return true;
      }
    }
    return false;
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

  public boolean isOnWishlist(Integer customerId, Integer bookSku){
    Customer customerResponse = customerRepository.findFirstByCustomerId(customerId);
    List<Book> wishlist = customerResponse.getWishlist();
    for (Book book : wishlist) {
      if (book.getBookSku() == bookSku) {
        return true;
      }
    }
    return false;
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

  public boolean isOnCart(Integer customerId, Integer bookSku){
    Customer customerResponse = customerRepository.findFirstByCustomerId(customerId);
    List<Book> cart = customerResponse.getCart();
    for (Book book : cart) {
      if (book.getBookSku() == bookSku) {
        return true;
      }
    }
    return false;
  }

  public Customer addCustomerWishlist(Integer customerId, Integer bookSku) {
    Customer customerResponse = customerRepository.findFirstByCustomerId(customerId);
    Book bookResponse = bookRepository.findFirstByBookSku(bookSku);
    customerResponse.getWishlist().add(bookResponse);
    bookResponse.getLikedBook().add(customerResponse);
    bookRepository.save(bookResponse);

    return customerRepository.save(customerResponse);
  }

  public Customer deleteCustomerWishlist(Integer customerId, Integer bookSku) {
    Customer customerResponse = customerRepository.findFirstByCustomerId(customerId);
    Book bookResponse = bookRepository.findFirstByBookSku(bookSku);

    List<Book> bookList = new ArrayList<>();
    for (Book book : customerResponse.getWishlist()) {
      if (book.getBookSku() != bookResponse.getBookSku()) {
        bookList.add(book);
      }
    }

    List<Customer> customerList = new ArrayList<>();
    for (Customer customer : bookResponse.getLikedBook()) {
      if (customer.getCustomerId() != customerResponse.getCustomerId()) {
        customerList.add(customer);
      }
    }

    customerResponse.setWishlist(bookList);
    bookResponse.setLikedBook(customerList);
    bookRepository.save(bookResponse);

    return customerRepository.save(customerResponse);
  }

  public Customer addCustomerCart(Integer customerId, Integer bookSku) {
    Customer customerResponse = customerRepository.findFirstByCustomerId(customerId);
    Book bookResponse = bookRepository.findFirstByBookSku(bookSku);
    customerResponse.getCart().add(bookResponse);
    bookResponse.getCartedBook().add(customerResponse);
    bookRepository.save(bookResponse);

    return customerRepository.save(customerResponse);
  }

  public Customer deleteCustomerCart(Integer customerId, Integer bookSku) {
    Customer customerResponse = customerRepository.findFirstByCustomerId(customerId);
    Book bookResponse = bookRepository.findFirstByBookSku(bookSku);

    List<Book> bookList = new ArrayList<>();
    for (Book book : customerResponse.getCart()) {
      if (book.getBookSku() != bookResponse.getBookSku()) {
        bookList.add(book);
      }
    }

    List<Customer> customerList = new ArrayList<>();
    for (Customer customer : bookResponse.getCartedBook()) {
      if (customer.getCustomerId() != customerResponse.getCustomerId()) {
        customerList.add(customer);
      }
    }

    customerResponse.setCart(bookList);
    bookResponse.setCartedBook(customerList);
    bookRepository.save(bookResponse);

    return customerRepository.save(customerResponse);
  }

  public Customer addCustomerLibrary(Integer customerId, Integer bookSku) {
    Customer customerResponse = customerRepository.findFirstByCustomerId(customerId);
    Book bookResponse = bookRepository.findFirstByBookSku(bookSku);

    customerResponse.getLibrary().add(bookResponse);
    bookResponse.getOwnerBook().add(customerResponse);
    bookRepository.save(bookResponse);

    return customerRepository.save(customerResponse);
  }
}
