package com.kindle.backend.service;

import com.kindle.backend.model.entity.Merchant;
import com.kindle.backend.model.repository.MerchantRepository;
import com.kindle.backend.response.BaseResponse;
import com.kindle.backend.response.attributeResponse.GetAllMerchantResponse;
import com.kindle.backend.response.attributeResponse.GetMerchantByMerchantIdResponse;
import com.kindle.backend.response.dataResponse.DataNoAttributeResponse;
import com.kindle.backend.response.dataResponse.DataNoRelationResponse;
import com.kindle.backend.response.errorResponse.ErrorDetailResponse;
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

  public BaseResponse findByMerchantId(Integer merchantId){
    Merchant merchant = merchantRepository.findFirstByMerchantId(merchantId);

    if (merchant == null) {
      ErrorDetailResponse errorDetailResponse = new ErrorDetailResponse(404, "MerchantId not found");

      List<ErrorDetailResponse> errorDetailResponses = new ArrayList<>();
      errorDetailResponses.add(errorDetailResponse);
      FailureDataResponse failureDataResponse = new FailureDataResponse(400, "Bad Request", errorDetailResponses);

      return failureDataResponse;
    } else {
      List<DataNoRelationResponse> dataNoRelationResponses = new ArrayList<>();

      GetMerchantByMerchantIdResponse getMerchantByMerchantIdResponse = new GetMerchantByMerchantIdResponse(merchant.getUsername(), merchant.getEmail(), merchant.getPassword(), merchant.getFullname(), merchant.getDescription(), merchant.getPhone(), merchant.getStatus());
      DataNoRelationResponse<GetMerchantByMerchantIdResponse> dataNoRelationResponse = new DataNoRelationResponse<>(merchant.getMerchantId(), "merchant", getMerchantByMerchantIdResponse);
      dataNoRelationResponses.add(dataNoRelationResponse);

      SuccessDataResponse<DataNoRelationResponse> successDataResponse = new SuccessDataResponse<>(200, "OK", dataNoRelationResponses);

      return successDataResponse;
    }
  }

  public BaseResponse save(Merchant merchant) {
    Merchant merchantResponse = merchantRepository.save(merchant);

    if (merchantResponse == null) {
      ErrorDetailResponse errorDetailResponse = new ErrorDetailResponse(500, "Cannot create merchant data");

      List<ErrorDetailResponse> errorDetailResponses = new ArrayList<>();
      errorDetailResponses.add(errorDetailResponse);
      FailureDataResponse failureDataResponse = new FailureDataResponse(500, "Internal server error", errorDetailResponses);

      return failureDataResponse;
    } else {
      DataNoAttributeResponse dataNoAttributeResponse = new DataNoAttributeResponse(merchantResponse.getMerchantId(), "merchant");

      List<DataNoAttributeResponse> dataNoAttributeResponses = new ArrayList<>();
      dataNoAttributeResponses.add(dataNoAttributeResponse);
      SuccessDataResponse<DataNoAttributeResponse> successDataResponse = new SuccessDataResponse<>(201, "Created", dataNoAttributeResponses);

      return successDataResponse;
    }
  }

  public BaseResponse updateMerchant(Integer merchantId, Merchant merchant) {
    Merchant fetchResponse = merchantRepository.findFirstByMerchantId(merchantId);

    if (fetchResponse == null) {
      ErrorDetailResponse errorDetailResponse = new ErrorDetailResponse(404, "MerchantId not found");

      List<ErrorDetailResponse> errorDetailResponses = new ArrayList<>();
      errorDetailResponses.add(errorDetailResponse);
      FailureDataResponse failureDataResponse = new FailureDataResponse(400, "Bad Request", errorDetailResponses);

      return failureDataResponse;
    } else {
      merchant.setMerchantId(merchantId);
      Merchant merchantResponse = merchantRepository.save(merchant);

      if (merchantResponse == null) {
        ErrorDetailResponse errorDetailResponse = new ErrorDetailResponse(500, "Cannot update merchant data");

        List<ErrorDetailResponse> errorDetailResponses = new ArrayList<>();
        errorDetailResponses.add(errorDetailResponse);
        FailureDataResponse failureDataResponse = new FailureDataResponse(500, "Internal server error", errorDetailResponses);

        return failureDataResponse;
      } else {
        DataNoAttributeResponse dataNoAttributeResponse = new DataNoAttributeResponse(merchantId, "merchant");

        List<DataNoAttributeResponse> dataNoAttributeResponses = new ArrayList<>();
        dataNoAttributeResponses.add(dataNoAttributeResponse);
        SuccessDataResponse<DataNoAttributeResponse> successDataResponse = new SuccessDataResponse<>(200, "OK", dataNoAttributeResponses);

        return successDataResponse;
      }
    }
  }

  public BaseResponse deleteByMerchantId(Integer merchantId) {
    Merchant fetchResponse = merchantRepository.findFirstByMerchantId(merchantId);

    if (fetchResponse == null) {
      ErrorDetailResponse errorDetailResponse = new ErrorDetailResponse(404, "MerchantId not found");

      List<ErrorDetailResponse> errorDetailResponses = new ArrayList<>();
      errorDetailResponses.add(errorDetailResponse);
      FailureDataResponse failureDataResponse = new FailureDataResponse(400, "Bad Request", errorDetailResponses);

      return failureDataResponse;
    } else {
      long merchantResponse = merchantRepository.deleteByMerchantId(merchantId);

      if (merchantResponse <= 0) {
        ErrorDetailResponse errorDetailResponse = new ErrorDetailResponse(500, "Cannot delete merchant data");

        List<ErrorDetailResponse> errorDetailResponses = new ArrayList<>();
        errorDetailResponses.add(errorDetailResponse);
        FailureDataResponse failureDataResponse = new FailureDataResponse(500, "Internal server error", errorDetailResponses);

        return failureDataResponse;
      } else {
        DataNoAttributeResponse dataNoAttributeResponse = new DataNoAttributeResponse(merchantId, "merchant");

        List<DataNoAttributeResponse> dataNoAttributeResponses = new ArrayList<>();
        dataNoAttributeResponses.add(dataNoAttributeResponse);
        SuccessDataResponse<DataNoAttributeResponse> successDataResponse = new SuccessDataResponse<>(200, "OK", dataNoAttributeResponses);

        return successDataResponse;
      }
    }
  }

  public BaseResponse login(Merchant merchant) {
    String email = merchant.getEmail();
    String password = merchant.getPassword();

    Merchant fetchResponse = merchantRepository.findFirstByEmailAndPassword(email, password);

    if (fetchResponse == null) {
      ErrorDetailResponse errorDetailResponse = new ErrorDetailResponse(404, "Wrong email or password");

      List<ErrorDetailResponse> errorDetailResponses = new ArrayList<>();
      errorDetailResponses.add(errorDetailResponse);
      FailureDataResponse failureDataResponse = new FailureDataResponse(400, "Bad Request", errorDetailResponses);

      return failureDataResponse;
    } else {
      DataNoAttributeResponse dataNoAttributeResponse = new DataNoAttributeResponse(fetchResponse.getMerchantId(), "merchant");

      List<DataNoAttributeResponse> dataNoAttributeResponses = new ArrayList<>();
      dataNoAttributeResponses.add(dataNoAttributeResponse);
      SuccessDataResponse<DataNoAttributeResponse> successDataResponse = new SuccessDataResponse<>(200, "OK", dataNoAttributeResponses);

      return successDataResponse;
    }
  }
}
