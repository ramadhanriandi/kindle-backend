package com.kindle.backend.service;

import com.kindle.backend.model.entity.Merchant;
import com.kindle.backend.model.repository.MerchantRepository;
import com.kindle.backend.response.BaseResponse;
import com.kindle.backend.response.attributeResponse.GetAllMerchantResponse;
import com.kindle.backend.response.dataResponse.DataNoRelationResponse;
import com.kindle.backend.response.errorResponse.ErrorDetailResponse;
import com.kindle.backend.response.oldResponse.PostResponse;
import com.kindle.backend.response.oldResponse.PutResponse;
import com.kindle.backend.response.statusResponse.FailureDataResponse;
import com.kindle.backend.response.statusResponse.SuccessDataResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MerchantService {
  @Autowired
  private MerchantRepository merchantRepository;

  public BaseResponse findAllMerchant(){
    List<Merchant> merchants = merchantRepository.findAll();

    if (merchants == null) {
      ErrorDetailResponse errorDetailResponse = new ErrorDetailResponse(404, "Merchant not found");

      List<ErrorDetailResponse> errorDetailResponses = new ArrayList<>();
      errorDetailResponses.add(errorDetailResponse);
      FailureDataResponse failureDataResponse = new FailureDataResponse(400, "Bad Request", errorDetailResponses);

      return failureDataResponse;
    } else {
      List<DataNoRelationResponse> dataNoRelationResponses = new ArrayList<>();

      for (Merchant merchant : merchants) {
        GetAllMerchantResponse getAllMerchantResponse = new GetAllMerchantResponse(merchant.getFullname(), merchant.getStatus());
        DataNoRelationResponse<GetAllMerchantResponse> dataNoRelationResponse = new DataNoRelationResponse<>(merchant.getMerchantId(), "merchant", getAllMerchantResponse);
        dataNoRelationResponses.add(dataNoRelationResponse);
      }

      SuccessDataResponse<DataNoRelationResponse> successDataResponse = new SuccessDataResponse<>(200, "OK", dataNoRelationResponses);

      return successDataResponse;
    }
  }

  public Merchant findByMerchantId(Integer merchantId){
//    return merchantRepository.findFirstByMerchantId(merchantId);
    Category category = categoryRepository.findFirstByCategoryId(categoryId);

    if (category == null) {
      ErrorDetailResponse errorDetailResponse = new ErrorDetailResponse(404, "CategoryId not found");

      List<ErrorDetailResponse> errorDetailResponses = new ArrayList<>();
      errorDetailResponses.add(errorDetailResponse);
      FailureDataResponse failureDataResponse = new FailureDataResponse(400, "Bad Request", errorDetailResponses);

      return failureDataResponse;
    } else {
      List<DataNoRelationResponse> dataNoRelationResponses = new ArrayList<>();

      GetCategoryByCategoryIdResponse getCategoryByCategoryIdResponse = new GetCategoryByCategoryIdResponse(category.getName());
      DataNoRelationResponse<GetCategoryByCategoryIdResponse> dataNoRelationResponse = new DataNoRelationResponse<>(category.getCategoryId(), "category", getCategoryByCategoryIdResponse);
      dataNoRelationResponses.add(dataNoRelationResponse);

      SuccessDataResponse<DataNoRelationResponse> successDataResponse = new SuccessDataResponse<>(200, "OK", dataNoRelationResponses);

      return successDataResponse;
    }
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
