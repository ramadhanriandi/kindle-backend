package com.kindle.backend.controller;

import com.kindle.backend.model.constant.ApiPath;
import com.kindle.backend.model.entity.Book;
import com.kindle.backend.model.entity.Customer;
import com.kindle.backend.response.CartResponse;
import com.kindle.backend.response.PostResponse;
import com.kindle.backend.response.PutResponse;
import com.kindle.backend.response.WishlistResponse;
import com.kindle.backend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class CustomerController {
  @Autowired
  private CustomerService customerService;

  @RequestMapping(value = ApiPath.CUSTOMER, method = RequestMethod.GET)
  public List<Customer> getAllCustomer() {
    return this.customerService.findAllCustomer();
  }

  @RequestMapping(value = ApiPath.CUSTOMER_BY_CUSTOMER_ID, method = RequestMethod.GET)
  public Customer getCustomerById(@PathVariable Integer customerId) {
    return this.customerService.findByCustomerId(customerId);
  }

  @RequestMapping(value = ApiPath.CUSTOMER, method = RequestMethod.POST)
  public Customer save(@RequestBody Customer customer) {
    return this.customerService.save(customer);
  }

  @RequestMapping(value = ApiPath.CUSTOMER_BY_CUSTOMER_ID, method = RequestMethod.PUT)
  public PutResponse updateCustomer(@PathVariable Integer customerId, @RequestBody Customer customer) {
    return this.customerService.updateCustomer(customerId, customer);
  }

  @RequestMapping(value = ApiPath.CUSTOMER_BY_CUSTOMER_ID, method = RequestMethod.DELETE)
  public boolean deleteByCustomerId(@PathVariable Integer customerId) {
    return this.customerService.deleteByCustomerId(customerId) > 0;
  }

  @RequestMapping(value = ApiPath.CUSTOMER_LOGIN, method = RequestMethod.POST)
  public PostResponse login(@RequestBody Customer customer) {
    return this.customerService.login(customer);
  }

  @RequestMapping(value = ApiPath.CUSTOMER_REGISTER, method = RequestMethod.POST)
  public PostResponse register(@RequestBody Customer customer) {
    return this.customerService.register(customer);
  }

  @RequestMapping(value = ApiPath.CUSTOMER_CART, method = RequestMethod.GET)
  public List<CartResponse> getCustomerCart(@PathVariable Integer customerId) {
    return this.customerService.findCustomerCart(customerId);
  }

  @RequestMapping(value = ApiPath.CUSTOMER_LIBRARY, method = RequestMethod.GET)
  public List<Book> getCustomerLibrary(@PathVariable Integer customerId) {
    return this.customerService.findCustomerLibrary(customerId);
  }

  @RequestMapping(value = ApiPath.CUSTOMER_WISHLIST, method = RequestMethod.GET)
  public List<WishlistResponse> getCustomerWishlist(@PathVariable Integer customerId) {
    return this.customerService.findCustomerWishlist(customerId);
  }
}
