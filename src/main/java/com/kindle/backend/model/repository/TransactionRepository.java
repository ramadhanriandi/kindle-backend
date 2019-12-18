package com.kindle.backend.model.repository;

import com.kindle.backend.model.entity.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Integer> {
  List<Transaction> findAllByCustomerId(int customerId);
}
