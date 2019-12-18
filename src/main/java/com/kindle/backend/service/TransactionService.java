package com.kindle.backend.service;

import com.kindle.backend.model.entity.Transaction;
import com.kindle.backend.model.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
  @Autowired
  private TransactionRepository transactionRepository;

  public List<Transaction> findAllTransactionByCustomerId(int customerId){
    return transactionRepository.findAllByCustomerId(customerId);
  }
}
