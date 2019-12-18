package com.kindle.backend.service;

import com.kindle.backend.model.entity.Customer;
import com.kindle.backend.model.repository.CustomerRepository;
import com.kindle.backend.response.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
  @Autowired
  private CustomerRepository customerRepository;

  public Customer findByCustomerId(Integer customerId){
    return customerRepository.findFirstByCustomerId(customerId);
  }

  public List<Customer> findAllCustomer(){
    return customerRepository.findAll();
  }

  public Customer save(Customer customer) {
    return customerRepository.save(customer);
  }

  public void updateCustomer(Integer customerId, Customer customer) {
    customer.setCustomerId(customerId);
    customerRepository.save(customer);
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
}
