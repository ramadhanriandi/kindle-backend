package com.kindle.backend.service;

import com.kindle.backend.model.entity.Category;
import com.kindle.backend.model.repository.CategoryRepository;
import com.kindle.backend.response.*;
import com.kindle.backend.response.attributeResponse.GetCategoryByCategoryIdResponse;
import com.kindle.backend.response.errorResponse.ErrorDetailResponse;
import com.kindle.backend.response.dataResponse.DataNoAttributeResponse;
import com.kindle.backend.response.dataResponse.DataNoRelationResponse;
import com.kindle.backend.response.statusResponse.FailureDataResponse;
import com.kindle.backend.response.statusResponse.SuccessDataResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
  @Autowired
  private CategoryRepository categoryRepository;

  public BaseResponse findAllCategory(){
    List<Category> categories = categoryRepository.findAll();

    if (categories == null) {
      ErrorDetailResponse errorDetailResponse = new ErrorDetailResponse(404, "Category not found");

      List<ErrorDetailResponse> errorDetailResponses = new ArrayList<>();
      errorDetailResponses.add(errorDetailResponse);
      FailureDataResponse failureDataResponse = new FailureDataResponse(400, "Bad Request", errorDetailResponses);

      return failureDataResponse;
    } else {
      List<DataNoRelationResponse> dataNoRelationResponses = new ArrayList<>();

      for (Category category : categories) {
        GetCategoryByCategoryIdResponse getCategoryByCategoryIdResponse = new GetCategoryByCategoryIdResponse(category.getName());
        DataNoRelationResponse<GetCategoryByCategoryIdResponse> dataNoRelationResponse = new DataNoRelationResponse<>(category.getCategoryId(), "category", getCategoryByCategoryIdResponse);
        dataNoRelationResponses.add(dataNoRelationResponse);
      }

      SuccessDataResponse<DataNoRelationResponse> successDataResponse = new SuccessDataResponse<>(200, "OK", dataNoRelationResponses);

      return successDataResponse;
    }
  }

  public BaseResponse findByCategoryId(Integer categoryId){
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

  public BaseResponse save(Category category) {
    Category categoryResponse = categoryRepository.save(category);

    if (categoryResponse == null) {
      ErrorDetailResponse errorDetailResponse = new ErrorDetailResponse(500, "Cannot create the category");

      List<ErrorDetailResponse> errorDetailResponses = new ArrayList<>();
      errorDetailResponses.add(errorDetailResponse);
      FailureDataResponse failureDataResponse = new FailureDataResponse(500, "Internal server error", errorDetailResponses);

      return failureDataResponse;
    } else {
      DataNoAttributeResponse dataNoAttributeResponse = new DataNoAttributeResponse(categoryResponse.getCategoryId(), "category");

      List<DataNoAttributeResponse> dataNoAttributeResponses = new ArrayList<>();
      dataNoAttributeResponses.add(dataNoAttributeResponse);
      SuccessDataResponse<DataNoAttributeResponse> successDataResponse = new SuccessDataResponse<>(201, "Created", dataNoAttributeResponses);

      return successDataResponse;
    }
  }

  public BaseResponse updateCategory(Integer categoryId, Category category) {
    Category fetchResponse = categoryRepository.findFirstByCategoryId(categoryId);

    if (fetchResponse == null) {
      ErrorDetailResponse errorDetailResponse = new ErrorDetailResponse(404, "CategoryId not found");

      List<ErrorDetailResponse> errorDetailResponses = new ArrayList<>();
      errorDetailResponses.add(errorDetailResponse);
      FailureDataResponse failureDataResponse = new FailureDataResponse(400, "Bad Request", errorDetailResponses);

      return failureDataResponse;
    } else {
      category.setCategoryId(categoryId);
      Category categoryResponse = categoryRepository.save(category);

      if (categoryResponse == null) {
        ErrorDetailResponse errorDetailResponse = new ErrorDetailResponse(500, "Cannot update category data");

        List<ErrorDetailResponse> errorDetailResponses = new ArrayList<>();
        errorDetailResponses.add(errorDetailResponse);
        FailureDataResponse failureDataResponse = new FailureDataResponse(500, "Internal server error", errorDetailResponses);

        return failureDataResponse;
      } else {
        DataNoAttributeResponse dataNoAttributeResponse = new DataNoAttributeResponse(categoryId, "category");

        List<DataNoAttributeResponse> dataNoAttributeResponses = new ArrayList<>();
        dataNoAttributeResponses.add(dataNoAttributeResponse);
        SuccessDataResponse<DataNoAttributeResponse> successDataResponse = new SuccessDataResponse<>(200, "OK", dataNoAttributeResponses);

        return successDataResponse;
      }
    }
  }

  public BaseResponse deleteByCategoryId(Integer categoryId) {
    Category fetchResponse = categoryRepository.findFirstByCategoryId(categoryId);

    if (fetchResponse == null) {
      ErrorDetailResponse errorDetailResponse = new ErrorDetailResponse(404, "CategoryId not found");

      List<ErrorDetailResponse> errorDetailResponses = new ArrayList<>();
      errorDetailResponses.add(errorDetailResponse);
      FailureDataResponse failureDataResponse = new FailureDataResponse(400, "Bad Request", errorDetailResponses);

      return failureDataResponse;
    } else {
      long categoryResponse = categoryRepository.deleteCategoryByCategoryId(categoryId);

      if (categoryResponse <= 0) {
        ErrorDetailResponse errorDetailResponse = new ErrorDetailResponse(500, "Cannot delete the category");

        List<ErrorDetailResponse> errorDetailResponses = new ArrayList<>();
        errorDetailResponses.add(errorDetailResponse);
        FailureDataResponse failureDataResponse = new FailureDataResponse(500, "Internal server error", errorDetailResponses);

        return failureDataResponse;
      } else {
        DataNoAttributeResponse dataNoAttributeResponse = new DataNoAttributeResponse(categoryId, "category");

        List<DataNoAttributeResponse> dataNoAttributeResponses = new ArrayList<>();
        dataNoAttributeResponses.add(dataNoAttributeResponse);
        SuccessDataResponse<DataNoAttributeResponse> successDataResponse = new SuccessDataResponse<>(200, "OK", dataNoAttributeResponses);

        return successDataResponse;
      }
    }
  }
}
