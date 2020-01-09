package com.kindle.backend.model.repository;

import com.kindle.backend.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
  @Transactional
  Customer findFirstByCustomerId(Integer customerId);

  Customer findFirstByEmailAndPassword(String email, String password);

  List<Customer> findAll();

  @Transactional
  long deleteByCustomerId(Integer customerId);
}
