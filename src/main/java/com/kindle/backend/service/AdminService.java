package com.kindle.backend.service;

import com.kindle.backend.model.entity.Admin;
import com.kindle.backend.model.repository.AdminRepository;
import com.kindle.backend.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {
  @Autowired
  private AdminRepository adminRepository;

  public BaseResponse findByAdminId(Integer adminId) {
    Admin admin = adminRepository.findFirstByAdminId(adminId);

    if (admin == null) {
      ErrorDetailResponse errorDetailResponse = new ErrorDetailResponse(404, "AdminId not found");

      List<ErrorDetailResponse> errorDetailResponses = new ArrayList<>();
      errorDetailResponses.add(errorDetailResponse);
      FailureDataResponse failureDataResponse = new FailureDataResponse(400, "Bad Request", errorDetailResponses);

      return failureDataResponse;
    } else {
      GetAdminByAdminIdResponse getAdminByAdminIdResponse = new GetAdminByAdminIdResponse(admin.getEmail(), admin.getUsername(), admin.getPassword());

      List<GetAdminByAdminIdResponse> getAdminByAdminIdResponses = new ArrayList<>();
      getAdminByAdminIdResponses.add(getAdminByAdminIdResponse);
      DataNoRelationResponse<GetAdminByAdminIdResponse> dataNoRelationResponse = new DataNoRelationResponse<>(admin.getAdminId(), "admin", getAdminByAdminIdResponses);

      List<DataNoRelationResponse> dataNoRelationResponses = new ArrayList<>();
      dataNoRelationResponses.add(dataNoRelationResponse);
      SuccessDataResponse<DataNoRelationResponse> successDataResponse = new SuccessDataResponse<>(200, "OK", dataNoRelationResponses);

      return successDataResponse;
    }
  }

  public BaseResponse updateAdmin(Integer adminId, Admin admin) {
    Admin fetchResponse = adminRepository.findFirstByAdminId(adminId);

    if (fetchResponse == null) {
      ErrorDetailResponse errorDetailResponse = new ErrorDetailResponse(404, "AdminId not found");

      List<ErrorDetailResponse> errorDetailResponses = new ArrayList<>();
      errorDetailResponses.add(errorDetailResponse);
      FailureDataResponse failureDataResponse = new FailureDataResponse(400, "Bad Request", errorDetailResponses);

      return failureDataResponse;
    } else {
      admin.setAdminId(adminId);
      adminRepository.save(admin);

      DataNoAttributeResponse dataNoAttributeResponse = new DataNoAttributeResponse(admin.getAdminId(), "admin");

      List<DataNoAttributeResponse> dataNoAttributeResponses = new ArrayList<>();
      dataNoAttributeResponses.add(dataNoAttributeResponse);
      SuccessDataResponse<DataNoAttributeResponse> successDataResponse = new SuccessDataResponse<>(200, "OK", dataNoAttributeResponses);

      return successDataResponse;
    }
  }

  public BaseResponse login(Admin admin) {
    String email = admin.getEmail();
    String password = admin.getPassword();

    Admin fetchResponse = adminRepository.findFirstByEmailAndPassword(email, password);

    if (fetchResponse == null) {
      ErrorDetailResponse errorDetailResponse = new ErrorDetailResponse(404, "Wrong email or password");

      List<ErrorDetailResponse> errorDetailResponses = new ArrayList<>();
      errorDetailResponses.add(errorDetailResponse);
      FailureDataResponse failureDataResponse = new FailureDataResponse(400, "Bad Request", errorDetailResponses);

      return failureDataResponse;
    } else {
      DataNoAttributeResponse dataNoAttributeResponse = new DataNoAttributeResponse(fetchResponse.getAdminId(), "admin");

      List<DataNoAttributeResponse> dataNoAttributeResponses = new ArrayList<>();
      dataNoAttributeResponses.add(dataNoAttributeResponse);
      SuccessDataResponse<DataNoAttributeResponse> successDataResponse = new SuccessDataResponse<>(200, "OK", dataNoAttributeResponses);

      return successDataResponse;
    }
  }
}
