package com.kindle.backend.controller;

import com.kindle.backend.model.constant.ApiPath;
import com.kindle.backend.model.entity.TransactionList;
import com.kindle.backend.service.TransactionListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class TransactionListController {
  @Autowired
  private TransactionListService transactionListService;

  @RequestMapping(value = ApiPath.TRANSACTIONLIST_BY_TRANSACTION_ID, method = RequestMethod.GET)
  public List<TransactionList> getAllTransactionListByTransactionId(@RequestParam int transactionId) {
    return this.transactionListService.findAllTransactionListByTransactionId(transactionId);
  }
}
