package com.kindle.backend.service;

import com.kindle.backend.model.entity.Book;
import com.kindle.backend.model.entity.Customer;
import com.kindle.backend.model.entity.TransactionList;
import com.kindle.backend.model.repository.BookRepository;
import com.kindle.backend.model.repository.CustomerRepository;
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

//    List<Book> bookList = new ArrayList<>();
//    for (Book book : customerResponse.getCart()) {
//      if (book.getBookSku() != transactionList.getBookSku()) {
//        bookList.add(book);
//      }
//    }
//
//    List<Customer> customerList = new ArrayList<>();
//    for (Customer customer : bookResponse.getCartedBook()) {
//      if (customer.getCustomerId() != customerResponse.getCustomerId()) {
//        customerList.add(customer);
//      }
//    }

//    customerResponse.getCart().remove(bookResponse);
//    bookResponse.getCartedBook().remove(customerResponse);
//    customerResponse.setCart(bookList);
//    bookResponse.setCartedBook(customerList);

    customerResponse.getLibrary().add(bookResponse);
    bookResponse.getOwnerBook().add(customerResponse);

    customerRepository.save(customerResponse);
    bookRepository.save(bookResponse);

    return transactionListRepository.save(transactionList);
  }
}
