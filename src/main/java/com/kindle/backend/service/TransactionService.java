package com.kindle.backend.service;

import com.kindle.backend.model.entity.Transaction;
import com.kindle.backend.model.repository.TransactionRepository;
import com.kindle.backend.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {
  @Autowired
  private TransactionRepository transactionRepository;

  public BaseResponse findAllTransactionByCustomerId(int customerId){
    List<Transaction> transactions = transactionRepository.findAllByCustomerId(customerId);

    if (transactions == null) {
      ErrorDetailResponse errorDetailResponse = new ErrorDetailResponse(404, "Transaction not found");

      List<ErrorDetailResponse> errorDetailResponses = new ArrayList<>();
      errorDetailResponses.add(errorDetailResponse);
      FailureDataResponse failureDataResponse = new FailureDataResponse(400, "Bad Request", errorDetailResponses);

      return failureDataResponse;
    } else {
      List<DataNoRelationResponse> dataNoRelationResponses = new ArrayList<>();

      for (Transaction transaction : transactions) {
        GetAllTransactionByCustomerIdResponse getAllTransactionByCustomerIdResponse = new GetAllTransactionByCustomerIdResponse(transaction.getDate(), transaction.getTotal());
        DataNoRelationResponse<GetAllTransactionByCustomerIdResponse> dataNoRelationResponse = new DataNoRelationResponse<>(transaction.getTransactionId(), "transaction", getAllTransactionByCustomerIdResponse);
        dataNoRelationResponses.add(dataNoRelationResponse);
      }

      SuccessDataResponse<DataNoRelationResponse> successDataResponse = new SuccessDataResponse<>(200, "OK", dataNoRelationResponses);

      return successDataResponse;
    }
  }

  public Transaction findByTransactionId(int transactionId) {
    return transactionRepository.findByTransactionId(transactionId);
  }

  public Transaction save(Transaction transaction) {
    return transactionRepository.save(transaction);
  }
}
