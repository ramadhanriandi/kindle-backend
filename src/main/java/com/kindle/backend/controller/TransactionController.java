package com.kindle.backend.controller;

import com.kindle.backend.model.constant.ApiPath;
import com.kindle.backend.model.entity.Transaction;
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
  public List<Transaction> getAllTransactionByCustomerId(@RequestParam  int customerId) {
    return this.transactionService.findAllTransactionByCustomerId(customerId);
  }
}
