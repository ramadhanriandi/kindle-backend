package com.kindle.backend.service;

import com.kindle.backend.model.entity.Customer;
import com.kindle.backend.model.repository.CustomerRepository;
import com.kindle.backend.response.LoginResponse;
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

  public LoginResponse login(Customer customer) {
    String email = customer.getEmail();
    String password = customer.getPassword();
    LoginResponse loginResponse = new LoginResponse();

    Customer customerResponse = customerRepository.findFirstByEmailAndPassword(email, password);

    if (customerResponse == null) {
      loginResponse.setCode(401);
      loginResponse.setMessage("Login failed: wrong email or password");
    } else {
      loginResponse.setCode(200);
      loginResponse.setMessage("Login success");
    }

    return loginResponse;
  }
}
