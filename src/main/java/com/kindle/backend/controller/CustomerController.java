package com.kindle.backend.controller;

import com.kindle.backend.model.constant.ApiPath;
import com.kindle.backend.model.entity.Customer;
import com.kindle.backend.response.BaseResponse;
import com.kindle.backend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class CustomerController {
  @Autowired
  private CustomerService customerService;

  @RequestMapping(value = ApiPath.CUSTOMER, method = RequestMethod.GET)
  public BaseResponse getAllCustomer() {
    return this.customerService.findAllCustomer();
  }

  @RequestMapping(value = ApiPath.CUSTOMER_BY_CUSTOMER_ID, method = RequestMethod.GET)
  public BaseResponse getCustomerById(@PathVariable Integer customerId) {
    return this.customerService.findByCustomerId(customerId);
  }

  @RequestMapping(value = ApiPath.CUSTOMER, method = RequestMethod.POST)
  public BaseResponse save(@RequestBody Customer customer) {
    return this.customerService.save(customer);
  }

  @RequestMapping(value = ApiPath.CUSTOMER_BY_CUSTOMER_ID, method = RequestMethod.PUT)
  public BaseResponse updateCustomer(@PathVariable Integer customerId, @RequestBody Customer customer) {
    return this.customerService.updateCustomer(customerId, customer);
  }

  @RequestMapping(value = ApiPath.CUSTOMER_BY_CUSTOMER_ID, method = RequestMethod.DELETE)
  public BaseResponse deleteByCustomerId(@PathVariable Integer customerId) {
    return this.customerService.deleteByCustomerId(customerId);
  }

  @RequestMapping(value = ApiPath.CUSTOMER_LOGIN, method = RequestMethod.POST)
  public BaseResponse login(@RequestBody Customer customer) {
    return this.customerService.login(customer);
  }

  @RequestMapping(value = ApiPath.CUSTOMER_CART, method = RequestMethod.GET)
  public BaseResponse getCustomerCart(@PathVariable Integer customerId) {
    return this.customerService.findCustomerCart(customerId);
  }

  @RequestMapping(value = ApiPath.CUSTOMER_LIBRARY, method = RequestMethod.GET)
  public BaseResponse getCustomerLibrary(@PathVariable Integer customerId) {
    return this.customerService.findCustomerLibrary(customerId);
  }

  @RequestMapping(value = ApiPath.CUSTOMER_WISHLIST, method = RequestMethod.GET)
  public BaseResponse getCustomerWishlist(@PathVariable Integer customerId) {
    return this.customerService.findCustomerWishlist(customerId);
  }

  @RequestMapping(value = ApiPath.CUSTOMER_CART_CHECK, method = RequestMethod.GET)
  public BaseResponse isOnCart(@PathVariable Integer customerId, @PathVariable Integer bookSku) {
    return this.customerService.isOnCart(customerId, bookSku);
  }

  @RequestMapping(value = ApiPath.CUSTOMER_LIBRARY_CHECK, method = RequestMethod.GET)
  public BaseResponse inOnLibrary(@PathVariable Integer customerId, @PathVariable Integer bookSku) {
    return this.customerService.isOnLibrary(customerId, bookSku);
  }

  @RequestMapping(value = ApiPath.CUSTOMER_WISHLIST_CHECK, method = RequestMethod.GET)
  public BaseResponse isOnWishlist(@PathVariable Integer customerId, @PathVariable Integer bookSku) {
    return this.customerService.isOnWishlist(customerId, bookSku);
  }

  @RequestMapping(value = ApiPath.CUSTOMER_CART, method = RequestMethod.POST)
  public BaseResponse addCustomerCart(@PathVariable Integer customerId, @RequestParam Integer bookSku) {
    return this.customerService.addCustomerCart(customerId, bookSku);
  }

  @RequestMapping(value = ApiPath.CUSTOMER_LIBRARY, method = RequestMethod.POST)
  public BaseResponse addCustomerLibrary(@PathVariable Integer customerId, @RequestParam Integer bookSku) {
    return this.customerService.addCustomerLibrary(customerId, bookSku);
  }

  @RequestMapping(value = ApiPath.CUSTOMER_WISHLIST, method = RequestMethod.POST)
  public BaseResponse addCustomerWishlist(@PathVariable Integer customerId, @RequestParam Integer bookSku) {
    return this.customerService.addCustomerWishlist(customerId, bookSku);
  }

  @RequestMapping(value = ApiPath.CUSTOMER_CART, method = RequestMethod.DELETE)
  public BaseResponse deleteCustomerCart(@PathVariable Integer customerId, @RequestParam Integer bookSku) {
    return this.customerService.deleteCustomerCart(customerId, bookSku);
  }

  @RequestMapping(value = ApiPath.CUSTOMER_WISHLIST, method = RequestMethod.DELETE)
  public BaseResponse deleteCustomerWishlist(@PathVariable Integer customerId, @RequestParam Integer bookSku) {
    return this.customerService.deleteCustomerWishlist(customerId, bookSku);
  }
}
