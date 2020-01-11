package com.kindle.backend.service;

import com.kindle.backend.model.entity.Category;
import com.kindle.backend.model.repository.CategoryRepository;
import com.kindle.backend.response.*;
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

  public Category save(Category category) {
    return categoryRepository.save(category);
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
      categoryRepository.save(category);

      DataNoAttributeResponse dataNoAttributeResponse = new DataNoAttributeResponse(categoryId, "category");

      List<DataNoAttributeResponse> dataNoAttributeResponses = new ArrayList<>();
      dataNoAttributeResponses.add(dataNoAttributeResponse);
      SuccessDataResponse<DataNoAttributeResponse> successDataResponse = new SuccessDataResponse<>(200, "OK", dataNoAttributeResponses);

      return successDataResponse;
    }
  }

  public long deleteByCategoryId(Integer categoryId) {
    return categoryRepository.deleteCategoryByCategoryId(categoryId);
  }
}
