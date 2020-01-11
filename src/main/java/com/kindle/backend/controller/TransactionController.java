package com.kindle.backend.controller;

import com.kindle.backend.model.constant.ApiPath;
import com.kindle.backend.model.entity.Transaction;
import com.kindle.backend.response.BaseResponse;
import com.kindle.backend.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class TransactionController {
  @Autowired
  private TransactionService transactionService;

  @RequestMapping(value = ApiPath.TRANSACTION, method = RequestMethod.GET)
  public BaseResponse getAllTransactionByCustomerId(@RequestParam int customerId) {
    return this.transactionService.findAllTransactionByCustomerId(customerId);
  }

  @RequestMapping(value = ApiPath.TRANSACTION_BY_TRANSACTION_ID, method = RequestMethod.GET)
  public BaseResponse getByTransactionId(@PathVariable Integer transactionId) {
    return this.transactionService.findByTransactionId(transactionId);
  }

  @RequestMapping(value = ApiPath.TRANSACTION, method = RequestMethod.POST)
  public Transaction save(@RequestBody Transaction transaction) {
    return this.transactionService.save(transaction);
  }
}
