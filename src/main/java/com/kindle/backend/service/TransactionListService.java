package com.kindle.backend.service;

import com.kindle.backend.model.entity.TransactionList;
import com.kindle.backend.model.repository.TransactionListRepository;
import com.kindle.backend.response.TransactionListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionListService {
  @Autowired
  private TransactionListRepository transactionListRepository;

  public List<TransactionListResponse> findAllTranscationListByTransactionId(int transactionId){
    List<TransactionList> transactionLists = transactionListRepository.findAllByTransactionId(transactionId);
    List<TransactionListResponse> transactionListResponses = new ArrayList<>();
    for (TransactionList transactionList : transactionLists) {
      transactionListResponses.add(new TransactionListResponse(transactionList.getBookDetail()));
    }

    return transactionListResponses;
  }
}
