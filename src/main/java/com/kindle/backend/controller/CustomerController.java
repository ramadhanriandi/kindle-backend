package com.kindle.backend.controller;

import com.kindle.backend.model.constant.ApiPath;
import com.kindle.backend.model.entity.Customer;
import com.kindle.backend.response.LoginResponse;
import com.kindle.backend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
  public void updateCustomer(@PathVariable Integer customerId, @RequestBody Customer customer) {
    this.customerService.updateCustomer(customerId, customer);
  }

  @RequestMapping(value = ApiPath.CUSTOMER_BY_CUSTOMER_ID, method = RequestMethod.DELETE)
  public boolean deleteByCustomerId(@PathVariable Integer customerId) {
    return this.customerService.deleteByCustomerId(customerId) > 0;
  }

  @RequestMapping(value = ApiPath.CUSTOMER_LOGIN, method = RequestMethod.POST)
  public LoginResponse login(@RequestBody Customer customer) {
    return this.customerService.login(customer);
  }
}
