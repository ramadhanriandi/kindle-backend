package com.kindle.backend.service;

import com.kindle.backend.model.entity.Transaction;
import com.kindle.backend.model.entity.TransactionList;
import com.kindle.backend.model.repository.TransactionListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransactionListService {
  @Autowired
  private TransactionListRepository transactionListRepository;

  @Transactional(propagation =  Propagation.SUPPORTS, readOnly = true)
  public List<TransactionList> findAllTransactionListByTransactionId(int transactionId){
    return transactionListRepository.findAllByTransactionId(transactionId);
  }
}
