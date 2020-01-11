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

  public Category findByCategoryId(Integer categoryId){
    return categoryRepository.findFirstByCategoryId(categoryId);
  }

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

  public Category save(Category category) {
    return categoryRepository.save(category);
  }

  public PutResponse updateCategory(Integer categoryId, Category category) {
    PutResponse updateResponse = new PutResponse();
    category.setCategoryId(categoryId);
    Category categoryResponse = categoryRepository.save(category);

    if (categoryResponse == null) {
      updateResponse.setCode(401);
      updateResponse.setMessage("Error: update fail");
    } else {
      updateResponse.setCode(200);
      updateResponse.setMessage("Update success");
    }

    return updateResponse;
  }

  public long deleteByCategoryId(Integer categoryId) {
    return categoryRepository.deleteCategoryByCategoryId(categoryId);
  }
}
