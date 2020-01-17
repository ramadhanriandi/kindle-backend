package com.kindle.backend.service;

import com.kindle.backend.model.entity.Book;
import com.kindle.backend.model.entity.Customer;
import com.kindle.backend.model.repository.BookRepository;
import com.kindle.backend.model.repository.CustomerRepository;
import com.kindle.backend.response.BaseResponse;
import com.kindle.backend.response.attributeResponse.GetAllCustomerResponse;
import com.kindle.backend.response.attributeResponse.GetCustomerCartResponse;
import com.kindle.backend.response.attributeResponse.GetCustomerLibraryResponse;
import com.kindle.backend.response.attributeResponse.GetCustomerWishlistResponse;
import com.kindle.backend.response.dataResponse.DataCompleteResponse;
import com.kindle.backend.response.dataResponse.DataNoAttributeResponse;
import com.kindle.backend.response.dataResponse.DataNoRelationResponse;
import com.kindle.backend.response.errorResponse.ErrorDetailResponse;
import com.kindle.backend.response.includedResponse.MerchantIncludedResponse;
import com.kindle.backend.response.relationshipResponse.BaseRelationshipDataResponse;
import com.kindle.backend.response.relationshipResponse.CartRelationshipResponse;
import com.kindle.backend.response.relationshipResponse.WishlistRelationshipResponse;
import com.kindle.backend.response.statusResponse.FailureDataResponse;
import com.kindle.backend.response.statusResponse.SuccessDataResponse;
import com.kindle.backend.response.statusResponse.SuccessDataWithIncludedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        GetAllCustomerResponse getAllCustomerResponse = new GetAllCustomerResponse(customer.getUsername(), customer.getStatus(), customer.getEmail(), customer.getPassword());
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

      GetAllCustomerResponse getAllCustomerResponse = new GetAllCustomerResponse(customer.getUsername(), customer.getStatus(), customer.getEmail(), customer.getPassword());
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

  public BaseResponse deleteByCustomerId(Integer customerId) {
    Customer fetchResponse = customerRepository.findFirstByCustomerId(customerId);

    if (fetchResponse == null) {
      ErrorDetailResponse errorDetailResponse = new ErrorDetailResponse(404, "CustomerId not found");

      List<ErrorDetailResponse> errorDetailResponses = new ArrayList<>();
      errorDetailResponses.add(errorDetailResponse);
      FailureDataResponse failureDataResponse = new FailureDataResponse(400, "Bad Request", errorDetailResponses);

      return failureDataResponse;
    } else {
      long customerResponse = customerRepository.deleteByCustomerId(customerId);

      if (customerResponse <= 0) {
        ErrorDetailResponse errorDetailResponse = new ErrorDetailResponse(500, "Cannot delete customer data");

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

  public BaseResponse login(Customer customer) {
    String email = customer.getEmail();
    String password = customer.getPassword();

    Customer fetchResponse = customerRepository.findFirstByEmailAndPassword(email, password);

    if (fetchResponse == null) {
      ErrorDetailResponse errorDetailResponse = new ErrorDetailResponse(404, "Wrong email or password");

      List<ErrorDetailResponse> errorDetailResponses = new ArrayList<>();
      errorDetailResponses.add(errorDetailResponse);
      FailureDataResponse failureDataResponse = new FailureDataResponse(400, "Bad Request", errorDetailResponses);

      return failureDataResponse;
    } else {
      DataNoAttributeResponse dataNoAttributeResponse = new DataNoAttributeResponse(fetchResponse.getCustomerId(), "customer");

      List<DataNoAttributeResponse> dataNoAttributeResponses = new ArrayList<>();
      dataNoAttributeResponses.add(dataNoAttributeResponse);
      SuccessDataResponse<DataNoAttributeResponse> successDataResponse = new SuccessDataResponse<>(200, "OK", dataNoAttributeResponses);

      return successDataResponse;
    }
  }

  public BaseResponse findCustomerLibrary(Integer customerId){
    Customer customer = customerRepository.findFirstByCustomerId(customerId);

    if (customer == null) {
      ErrorDetailResponse errorDetailResponse = new ErrorDetailResponse(404, "CustomerId not found");

      List<ErrorDetailResponse> errorDetailResponses = new ArrayList<>();
      errorDetailResponses.add(errorDetailResponse);
      FailureDataResponse failureDataResponse = new FailureDataResponse(400, "Bad Request", errorDetailResponses);

      return failureDataResponse;
    } else {
      List<Book> library = customer.getLibrary();
      List<DataNoRelationResponse> dataNoRelationResponses = new ArrayList<>();

      for (Book book : library) {
        GetCustomerLibraryResponse getCustomerLibraryResponse = new GetCustomerLibraryResponse(book.getDocument());
        DataNoRelationResponse<GetCustomerLibraryResponse> dataNoRelationResponse = new DataNoRelationResponse<>(book.getBookSku(), "book", getCustomerLibraryResponse);
        dataNoRelationResponses.add(dataNoRelationResponse);
      }

      SuccessDataResponse<DataNoRelationResponse> successDataResponse = new SuccessDataResponse<>(200, "OK", dataNoRelationResponses);

      return successDataResponse;
    }
  }

  public BaseResponse isOnLibrary(Integer customerId, Integer bookSku){
    Customer fetchResponse = customerRepository.findFirstByCustomerId(customerId);

    if (fetchResponse == null) {
      ErrorDetailResponse errorDetailResponse = new ErrorDetailResponse(404, "CustomerId not found");

      List<ErrorDetailResponse> errorDetailResponses = new ArrayList<>();
      errorDetailResponses.add(errorDetailResponse);
      FailureDataResponse failureDataResponse = new FailureDataResponse(400, "Bad Request", errorDetailResponses);

      return failureDataResponse;
    } else {
      List<Book> library = fetchResponse.getLibrary();;
      boolean found = false;

      for (Book book : library) {
        if (book.getBookSku() == bookSku) {
          found = true;
        }
      }

      if (found) {
        DataNoAttributeResponse dataNoAttributeResponse = new DataNoAttributeResponse(bookSku, "book");

        List<DataNoAttributeResponse> dataNoAttributeResponses = new ArrayList<>();
        dataNoAttributeResponses.add(dataNoAttributeResponse);
        SuccessDataResponse<DataNoAttributeResponse> successDataResponse = new SuccessDataResponse<>(200, "OK", dataNoAttributeResponses);

        return successDataResponse;
      } else {
        ErrorDetailResponse errorDetailResponse = new ErrorDetailResponse(404, "BookSku not found");

        List<ErrorDetailResponse> errorDetailResponses = new ArrayList<>();
        errorDetailResponses.add(errorDetailResponse);
        FailureDataResponse failureDataResponse = new FailureDataResponse(400, "Bad Request", errorDetailResponses);

        return failureDataResponse;
      }
    }
  }

  public BaseResponse findCustomerWishlist(Integer customerId){
    Customer customer = customerRepository.findFirstByCustomerId(customerId);

    if (customer == null) {
      ErrorDetailResponse errorDetailResponse = new ErrorDetailResponse(404, "CustomerId not found");

      List<ErrorDetailResponse> errorDetailResponses = new ArrayList<>();
      errorDetailResponses.add(errorDetailResponse);
      FailureDataResponse failureDataResponse = new FailureDataResponse(400, "Bad Request", errorDetailResponses);

      return failureDataResponse;
    } else {
      List<Book> wishlist = customer.getWishlist();
      List<DataCompleteResponse> dataCompleteResponses = new ArrayList<>();
      List<DataNoRelationResponse> dataNoRelationResponses = new ArrayList<>();

      for (Book book : wishlist) {
        // attributes
        GetCustomerWishlistResponse getCustomerWishlistResponse = new GetCustomerWishlistResponse(book.getTitle(), book.getAuthor(), book.getYear(), book.getPrice(), book.getDocument());

        // relationships
        List<DataNoAttributeResponse> merchantRelationshipDatas = new ArrayList<>();
        DataNoAttributeResponse merchantRelationshipData = new DataNoAttributeResponse(book.getMerchantId(), "merchant");
        merchantRelationshipDatas.add(merchantRelationshipData);
        BaseRelationshipDataResponse merchantRelationship = new BaseRelationshipDataResponse(merchantRelationshipDatas);
        WishlistRelationshipResponse wishlistRelationshipResponse = new WishlistRelationshipResponse(merchantRelationship);

        DataCompleteResponse<GetCustomerWishlistResponse, WishlistRelationshipResponse> dataCompleteResponse = new DataCompleteResponse<>(book.getBookSku(), "book", getCustomerWishlistResponse, wishlistRelationshipResponse);
        dataCompleteResponses.add(dataCompleteResponse);

        // included
        MerchantIncludedResponse merchantIncludedResponse = new MerchantIncludedResponse(book.getMerchant().getFullname());
        DataNoRelationResponse<MerchantIncludedResponse> merchantIncluded = new DataNoRelationResponse<>(book.getMerchantId(), "merchant", merchantIncludedResponse);

        if (!(dataNoRelationResponses.contains(merchantIncluded))) {
          dataNoRelationResponses.add(merchantIncluded);
        }
      }

      SuccessDataWithIncludedResponse<DataCompleteResponse, DataNoRelationResponse> successDataResponse = new SuccessDataWithIncludedResponse<>(200, "OK", dataCompleteResponses, dataNoRelationResponses);

      return successDataResponse;
    }
  }

  public BaseResponse isOnWishlist(Integer customerId, Integer bookSku){
    Customer fetchResponse = customerRepository.findFirstByCustomerId(customerId);

    if (fetchResponse == null) {
      ErrorDetailResponse errorDetailResponse = new ErrorDetailResponse(404, "CustomerId not found");

      List<ErrorDetailResponse> errorDetailResponses = new ArrayList<>();
      errorDetailResponses.add(errorDetailResponse);
      FailureDataResponse failureDataResponse = new FailureDataResponse(400, "Bad Request", errorDetailResponses);

      return failureDataResponse;
    } else {
      List<Book> wishlist = fetchResponse.getWishlist();;
      boolean found = false;

      for (Book book : wishlist) {
        if (book.getBookSku() == bookSku) {
          found = true;
        }
      }

      if (found) {
        DataNoAttributeResponse dataNoAttributeResponse = new DataNoAttributeResponse(bookSku, "book");

        List<DataNoAttributeResponse> dataNoAttributeResponses = new ArrayList<>();
        dataNoAttributeResponses.add(dataNoAttributeResponse);
        SuccessDataResponse<DataNoAttributeResponse> successDataResponse = new SuccessDataResponse<>(200, "OK", dataNoAttributeResponses);

        return successDataResponse;
      } else {
        ErrorDetailResponse errorDetailResponse = new ErrorDetailResponse(404, "BookSku not found");

        List<ErrorDetailResponse> errorDetailResponses = new ArrayList<>();
        errorDetailResponses.add(errorDetailResponse);
        FailureDataResponse failureDataResponse = new FailureDataResponse(400, "Bad Request", errorDetailResponses);

        return failureDataResponse;
      }
    }
  }

  public BaseResponse findCustomerCart(Integer customerId){
    Customer customer = customerRepository.findFirstByCustomerId(customerId);

    if (customer == null) {
      ErrorDetailResponse errorDetailResponse = new ErrorDetailResponse(404, "CustomerId not found");

      List<ErrorDetailResponse> errorDetailResponses = new ArrayList<>();
      errorDetailResponses.add(errorDetailResponse);
      FailureDataResponse failureDataResponse = new FailureDataResponse(400, "Bad Request", errorDetailResponses);

      return failureDataResponse;
    } else {
      List<Book> cart = customer.getCart();
      List<DataCompleteResponse> dataCompleteResponses = new ArrayList<>();
      List<DataNoRelationResponse> dataNoRelationResponses = new ArrayList<>();

      for (Book book : cart) {
        // attributes
        GetCustomerCartResponse getCustomerCartResponse = new GetCustomerCartResponse(book.getTitle(), book.getAuthor(), book.getYear(), book.getPrice(), book.getDocument());

        // relationships
        List<DataNoAttributeResponse> merchantRelationshipDatas = new ArrayList<>();
        DataNoAttributeResponse merchantRelationshipData = new DataNoAttributeResponse(book.getMerchantId(), "merchant");
        merchantRelationshipDatas.add(merchantRelationshipData);
        BaseRelationshipDataResponse merchantRelationship = new BaseRelationshipDataResponse(merchantRelationshipDatas);
        CartRelationshipResponse cartRelationshipResponse = new CartRelationshipResponse(merchantRelationship);

        DataCompleteResponse<GetCustomerCartResponse, CartRelationshipResponse> dataCompleteResponse = new DataCompleteResponse<>(book.getBookSku(), "book", getCustomerCartResponse, cartRelationshipResponse);
        dataCompleteResponses.add(dataCompleteResponse);

        // included
        MerchantIncludedResponse merchantIncludedResponse = new MerchantIncludedResponse(book.getMerchant().getFullname());
        DataNoRelationResponse<MerchantIncludedResponse> merchantIncluded = new DataNoRelationResponse<>(book.getMerchantId(), "merchant", merchantIncludedResponse);

        if (!(dataNoRelationResponses.contains(merchantIncluded))) {
          dataNoRelationResponses.add(merchantIncluded);
        }
      }

      SuccessDataWithIncludedResponse<DataCompleteResponse, DataNoRelationResponse> successDataResponse = new SuccessDataWithIncludedResponse<>(200, "OK", dataCompleteResponses, dataNoRelationResponses);

      return successDataResponse;
    }
  }

  public BaseResponse isOnCart(Integer customerId, Integer bookSku){
    Customer fetchResponse = customerRepository.findFirstByCustomerId(customerId);

    if (fetchResponse == null) {
      ErrorDetailResponse errorDetailResponse = new ErrorDetailResponse(404, "CustomerId not found");

      List<ErrorDetailResponse> errorDetailResponses = new ArrayList<>();
      errorDetailResponses.add(errorDetailResponse);
      FailureDataResponse failureDataResponse = new FailureDataResponse(400, "Bad Request", errorDetailResponses);

      return failureDataResponse;
    } else {
      List<Book> cart = fetchResponse.getCart();;
      boolean found = false;

      for (Book book : cart) {
        if (book.getBookSku() == bookSku) {
          found = true;
        }
      }

      if (found) {
        DataNoAttributeResponse dataNoAttributeResponse = new DataNoAttributeResponse(bookSku, "book");

        List<DataNoAttributeResponse> dataNoAttributeResponses = new ArrayList<>();
        dataNoAttributeResponses.add(dataNoAttributeResponse);
        SuccessDataResponse<DataNoAttributeResponse> successDataResponse = new SuccessDataResponse<>(200, "OK", dataNoAttributeResponses);

        return successDataResponse;
      } else {
        ErrorDetailResponse errorDetailResponse = new ErrorDetailResponse(404, "BookSku not found");

        List<ErrorDetailResponse> errorDetailResponses = new ArrayList<>();
        errorDetailResponses.add(errorDetailResponse);
        FailureDataResponse failureDataResponse = new FailureDataResponse(400, "Bad Request", errorDetailResponses);

        return failureDataResponse;
      }
    }
  }

  public BaseResponse addCustomerWishlist(Integer customerId, Integer bookSku) {
    Book bookResponse = bookRepository.findFirstByBookSku(bookSku);
    Customer customerResponse = customerRepository.findFirstByCustomerId(customerId);

    customerResponse.getWishlist().add(bookResponse);
    bookResponse.getLikedBook().add(customerResponse);

    Book savedBook = bookRepository.save(bookResponse);
    Customer savedCustomer = customerRepository.save(customerResponse);

    if (savedCustomer == null || savedBook == null) {
      ErrorDetailResponse errorDetailResponse = new ErrorDetailResponse(500, "Cannot add the book into customer wishlist");

      List<ErrorDetailResponse> errorDetailResponses = new ArrayList<>();
      errorDetailResponses.add(errorDetailResponse);
      FailureDataResponse failureDataResponse = new FailureDataResponse(500, "Internal server error", errorDetailResponses);

      return failureDataResponse;
    } else {
      DataNoAttributeResponse dataNoAttributeResponse = new DataNoAttributeResponse(savedBook.getBookSku(), "book");

      List<DataNoAttributeResponse> dataNoAttributeResponses = new ArrayList<>();
      dataNoAttributeResponses.add(dataNoAttributeResponse);
      SuccessDataResponse<DataNoAttributeResponse> successDataResponse = new SuccessDataResponse<>(201, "Created", dataNoAttributeResponses);

      return successDataResponse;
    }
  }

  public BaseResponse deleteCustomerWishlist(Integer customerId, Integer bookSku) {
    Customer customerResponse = customerRepository.findFirstByCustomerId(customerId);
    Book bookResponse = bookRepository.findFirstByBookSku(bookSku);

    if (customerResponse == null || bookResponse == null) {
      ErrorDetailResponse errorDetailResponse = new ErrorDetailResponse(404, "CustomerId or BookSku not found");

      List<ErrorDetailResponse> errorDetailResponses = new ArrayList<>();
      errorDetailResponses.add(errorDetailResponse);
      FailureDataResponse failureDataResponse = new FailureDataResponse(400, "Bad Request", errorDetailResponses);

      return failureDataResponse;
    } else {
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
      Book savedBook = bookRepository.save(bookResponse);
      Customer savedCustomer = customerRepository.save(customerResponse);

      if (savedBook == null || savedCustomer == null) {
        ErrorDetailResponse errorDetailResponse = new ErrorDetailResponse(500, "Cannot delete the book from customer wishlsit");

        List<ErrorDetailResponse> errorDetailResponses = new ArrayList<>();
        errorDetailResponses.add(errorDetailResponse);
        FailureDataResponse failureDataResponse = new FailureDataResponse(500, "Internal server error", errorDetailResponses);

        return failureDataResponse;
      } else {
        DataNoAttributeResponse dataNoAttributeResponse = new DataNoAttributeResponse(bookSku, "book");

        List<DataNoAttributeResponse> dataNoAttributeResponses = new ArrayList<>();
        dataNoAttributeResponses.add(dataNoAttributeResponse);
        SuccessDataResponse<DataNoAttributeResponse> successDataResponse = new SuccessDataResponse<>(200, "OK", dataNoAttributeResponses);

        return successDataResponse;
      }
    }
  }

  public BaseResponse addCustomerCart(Integer customerId, Integer bookSku) {
    Book bookResponse = bookRepository.findFirstByBookSku(bookSku);
    Customer customerResponse = customerRepository.findFirstByCustomerId(customerId);

    customerResponse.getCart().add(bookResponse);
    bookResponse.getCartedBook().add(customerResponse);

    Book savedBook = bookRepository.save(bookResponse);
    Customer savedCustomer = customerRepository.save(customerResponse);

    if (savedCustomer == null || savedBook == null) {
      ErrorDetailResponse errorDetailResponse = new ErrorDetailResponse(500, "Cannot add the book into customer cart");

      List<ErrorDetailResponse> errorDetailResponses = new ArrayList<>();
      errorDetailResponses.add(errorDetailResponse);
      FailureDataResponse failureDataResponse = new FailureDataResponse(500, "Internal server error", errorDetailResponses);

      return failureDataResponse;
    } else {
      DataNoAttributeResponse dataNoAttributeResponse = new DataNoAttributeResponse(savedBook.getBookSku(), "book");

      List<DataNoAttributeResponse> dataNoAttributeResponses = new ArrayList<>();
      dataNoAttributeResponses.add(dataNoAttributeResponse);
      SuccessDataResponse<DataNoAttributeResponse> successDataResponse = new SuccessDataResponse<>(201, "Created", dataNoAttributeResponses);

      return successDataResponse;
    }
  }

  public BaseResponse deleteCustomerCart(Integer customerId, Integer bookSku) {
    Customer customerResponse = customerRepository.findFirstByCustomerId(customerId);
    Book bookResponse = bookRepository.findFirstByBookSku(bookSku);

    if (customerResponse == null || bookResponse == null) {
      ErrorDetailResponse errorDetailResponse = new ErrorDetailResponse(404, "CustomerId or BookSku not found");

      List<ErrorDetailResponse> errorDetailResponses = new ArrayList<>();
      errorDetailResponses.add(errorDetailResponse);
      FailureDataResponse failureDataResponse = new FailureDataResponse(400, "Bad Request", errorDetailResponses);

      return failureDataResponse;
    } else {
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
      Book savedBook = bookRepository.save(bookResponse);
      Customer savedCustomer = customerRepository.save(customerResponse);

      if (savedBook == null || savedCustomer == null) {
        ErrorDetailResponse errorDetailResponse = new ErrorDetailResponse(500, "Cannot delete the book from customer cart");

        List<ErrorDetailResponse> errorDetailResponses = new ArrayList<>();
        errorDetailResponses.add(errorDetailResponse);
        FailureDataResponse failureDataResponse = new FailureDataResponse(500, "Internal server error", errorDetailResponses);

        return failureDataResponse;
      } else {
        DataNoAttributeResponse dataNoAttributeResponse = new DataNoAttributeResponse(bookSku, "book");

        List<DataNoAttributeResponse> dataNoAttributeResponses = new ArrayList<>();
        dataNoAttributeResponses.add(dataNoAttributeResponse);
        SuccessDataResponse<DataNoAttributeResponse> successDataResponse = new SuccessDataResponse<>(200, "OK", dataNoAttributeResponses);

        return successDataResponse;
      }
    }
  }

  public BaseResponse addCustomerLibrary(Integer customerId, Integer bookSku) {
    Book bookResponse = bookRepository.findFirstByBookSku(bookSku);
    Customer customerResponse = customerRepository.findFirstByCustomerId(customerId);

    customerResponse.getLibrary().add(bookResponse);
    bookResponse.getOwnerBook().add(customerResponse);

    Book savedBook = bookRepository.save(bookResponse);
    Customer savedCustomer = customerRepository.save(customerResponse);

    if (savedCustomer == null || savedBook == null) {
      ErrorDetailResponse errorDetailResponse = new ErrorDetailResponse(500, "Cannot add the book into customer library");

      List<ErrorDetailResponse> errorDetailResponses = new ArrayList<>();
      errorDetailResponses.add(errorDetailResponse);
      FailureDataResponse failureDataResponse = new FailureDataResponse(500, "Internal server error", errorDetailResponses);

      return failureDataResponse;
    } else {
      DataNoAttributeResponse dataNoAttributeResponse = new DataNoAttributeResponse(savedBook.getBookSku(), "book");

      List<DataNoAttributeResponse> dataNoAttributeResponses = new ArrayList<>();
      dataNoAttributeResponses.add(dataNoAttributeResponse);
      SuccessDataResponse<DataNoAttributeResponse> successDataResponse = new SuccessDataResponse<>(201, "Created", dataNoAttributeResponses);

      return successDataResponse;
    }
  }
}
