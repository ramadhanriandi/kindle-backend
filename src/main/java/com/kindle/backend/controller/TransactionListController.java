package com.kindle.backend.controller;

import com.kindle.backend.model.constant.ApiPath;
import com.kindle.backend.model.entity.TransactionList;
import com.kindle.backend.response.TransactionListResponse;
import com.kindle.backend.service.TransactionListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class TransactionListController {
  @Autowired
  private TransactionListService transactionListService;

  @RequestMapping(value = ApiPath.TRANSACTIONLIST, method = RequestMethod.GET)
  public List<TransactionListResponse> getAllTransactionListByQuery(@RequestParam(required = false) String transactionId, @RequestParam(required = false) String merchantId) {
      return this.transactionListService.findAllTranscationListByQuery(transactionId, merchantId);
  }

  @RequestMapping(value = ApiPath.TRANSACTIONLIST, method = RequestMethod.POST)
  public TransactionList save(@RequestParam Integer customerId, @RequestBody TransactionList transactionList) {
      return this.transactionListService.save(customerId, transactionList);
  }


}
