package com.kindle.backend.service;

import com.kindle.backend.model.entity.Merchant;
import com.kindle.backend.model.repository.MerchantRepository;
import com.kindle.backend.response.PostResponse;
import com.kindle.backend.response.PutResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MerchantService {
  @Autowired
  private MerchantRepository merchantRepository;

  public Merchant findByMerchantId(Integer merchantId){
    return merchantRepository.findFirstByMerchantId(merchantId);
  }

  public List<Merchant> findAllMerchant(){
    return merchantRepository.findAll();
  }

  public Merchant save(Merchant merchant) {
    return merchantRepository.save(merchant);
  }

  public PutResponse updateMerchant(Integer merchantId, Merchant merchant) {
    PutResponse updateResponse = new PutResponse();
    merchant.setMerchantId(merchantId);
    Merchant merchantResponse = merchantRepository.save(merchant);

    if (merchantResponse == null) {
      updateResponse.setCode(401);
      updateResponse.setMessage("Error: update fail");
    } else {
      updateResponse.setCode(200);
      updateResponse.setMessage("Update success");
    }

    return updateResponse;
  }

  public long deleteByMerchantId(Integer merchantId) {
    return merchantRepository.deleteByMerchantId(merchantId);
  }

  public PostResponse login(Merchant merchant) {
    String email = merchant.getEmail();
    String password = merchant.getPassword();
    PostResponse loginResponse = new PostResponse();

    Merchant merchantResponse = merchantRepository.findFirstByEmailAndPassword(email, password);

    if (merchantResponse == null) {
      loginResponse.setCode(401);
      loginResponse.setMessage("Login failed: wrong email or password");
    } else {
      if (merchantResponse.getStatus().equals("Active")) {
        loginResponse.setUserId(merchantResponse.getMerchantId());
        loginResponse.setCode(200);
        loginResponse.setMessage("Login success");
      } else {
        loginResponse.setCode(402);
        loginResponse.setMessage("Login failed: your account is " + merchantResponse.getStatus());
      }
    }

    return loginResponse;
  }
}
