package com.kindle.backend.controller;

import com.kindle.backend.model.constant.ApiPath;
import com.kindle.backend.model.entity.Book;
import com.kindle.backend.model.entity.Merchant;
import com.kindle.backend.response.BaseResponse;
import com.kindle.backend.response.oldResponse.PostResponse;
import com.kindle.backend.response.oldResponse.PutResponse;
import com.kindle.backend.service.BookService;
import com.kindle.backend.service.MerchantService;
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
  public PutResponse updateMerchant(@PathVariable Integer merchantId, @RequestBody Merchant merchant) {
    return this.merchantService.updateMerchant(merchantId, merchant);
  }

  @RequestMapping(value = ApiPath.MERCHANT_BY_MERCHANT_ID, method = RequestMethod.DELETE)
  public boolean deleteByMerchantId(@PathVariable Integer merchantId) {
    return this.merchantService.deleteByMerchantId(merchantId) > 0;
  }

  @RequestMapping(value = ApiPath.MERCHANT_LOGIN, method = RequestMethod.POST)
  public PostResponse login(@RequestBody Merchant merchant) {
    return this.merchantService.login(merchant);
  }

  @RequestMapping(value = ApiPath.MERCHANT_CATALOG, method = RequestMethod.GET)
  public List<Book> getMerchantCatalog(@PathVariable Integer merchantId){
    return this.bookService.findBookByMerchantId(merchantId);
  }
}
