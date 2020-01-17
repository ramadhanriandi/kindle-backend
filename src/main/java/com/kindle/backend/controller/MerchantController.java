package com.kindle.backend.controller;

import com.kindle.backend.model.constant.ApiPath;
import com.kindle.backend.model.entity.Book;
import com.kindle.backend.model.entity.Merchant;
import com.kindle.backend.response.BaseResponse;
import com.kindle.backend.response.oldResponse.PostResponse;
import com.kindle.backend.service.BookService;
import com.kindle.backend.service.MerchantService;
import com.kindle.backend.service.TransactionListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class MerchantController {
  @Autowired
  private MerchantService merchantService;
  @Autowired
  private BookService bookService;
  @Autowired
  private TransactionListService transactionListService;

  @RequestMapping(value = ApiPath.MERCHANT, method = RequestMethod.GET)
  public BaseResponse getAllMerchant() {
    return this.merchantService.findAllMerchant();
  }

  @RequestMapping(value = ApiPath.MERCHANT_BY_MERCHANT_ID, method = RequestMethod.GET)
  public BaseResponse getMerchantById(@PathVariable Integer merchantId) {
    return this.merchantService.findByMerchantId(merchantId);
  }

  @RequestMapping(value = ApiPath.MERCHANT, method = RequestMethod.POST)
  public BaseResponse save(@RequestBody Merchant merchant) {
    return this.merchantService.save(merchant);
  }

  @RequestMapping(value = ApiPath.MERCHANT_BY_MERCHANT_ID, method = RequestMethod.PUT)
  public BaseResponse updateMerchant(@PathVariable Integer merchantId, @RequestBody Merchant merchant) {
    return this.merchantService.updateMerchant(merchantId, merchant);
  }

  @RequestMapping(value = ApiPath.MERCHANT_BY_MERCHANT_ID, method = RequestMethod.DELETE)
  public BaseResponse deleteByMerchantId(@PathVariable Integer merchantId) {
    return this.merchantService.deleteByMerchantId(merchantId);
  }

  @RequestMapping(value = ApiPath.MERCHANT_LOGIN, method = RequestMethod.POST)
  public BaseResponse login(@RequestBody Merchant merchant) {
    return this.merchantService.login(merchant);
  }

  @RequestMapping(value = ApiPath.MERCHANT_CATALOG, method = RequestMethod.GET)
  public BaseResponse getMerchantCatalog(@PathVariable Integer merchantId){
    return this.bookService.findBookByMerchantId(merchantId);
  }

  @RequestMapping(value = ApiPath.MERCHANT_ORDERS, method = RequestMethod.GET)
  public BaseResponse getAllTransactionListByMerchantId(@PathVariable int merchantId) {
      return this.transactionListService.findAllTransactionListByMerchantId(merchantId);
  }
}
