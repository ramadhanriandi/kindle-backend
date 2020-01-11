package com.kindle.backend.service;

import com.kindle.backend.model.entity.Book;
import com.kindle.backend.model.entity.Customer;
import com.kindle.backend.model.entity.TransactionList;
import com.kindle.backend.model.repository.BookRepository;
import com.kindle.backend.model.repository.CustomerRepository;
import com.kindle.backend.model.repository.TransactionListRepository;
import com.kindle.backend.response.oldResponse.TransactionListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionListService {
  @Autowired
  private TransactionListRepository transactionListRepository;

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private BookRepository bookRepository;

  public List<TransactionListResponse> findAllTranscationListByTransactionId(int transactionId) {
    List<TransactionList> transactionLists = transactionListRepository.findAllByTransactionId(transactionId);
    List<TransactionListResponse> transactionListResponses = new ArrayList<>();
    for (TransactionList transactionList : transactionLists) {
      transactionListResponses.add(new TransactionListResponse(transactionList.getBookDetail()));
    }

    return transactionListResponses;
  }

  public TransactionList save(Integer customerId, TransactionList transactionList) {
    Customer customerResponse = customerRepository.findFirstByCustomerId(customerId);
    Book bookResponse = bookRepository.findFirstByBookSku(transactionList.getBookSku());

    customerResponse.getLibrary().add(bookResponse);
    bookResponse.getOwnerBook().add(customerResponse);

    customerRepository.save(customerResponse);
    bookRepository.save(bookResponse);

    return transactionListRepository.save(transactionList);
  }
}
