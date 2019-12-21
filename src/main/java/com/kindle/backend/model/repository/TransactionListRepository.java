package com.kindle.backend.model.repository;

import com.kindle.backend.model.entity.TransactionList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionListRepository extends CrudRepository<TransactionList, Integer> {
  List<TransactionList> findAllByTransactionId(Integer transactionId);
}