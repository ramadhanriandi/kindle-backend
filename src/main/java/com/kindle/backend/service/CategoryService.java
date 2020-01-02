package com.kindle.backend.service;

import com.kindle.backend.model.entity.Category;
import com.kindle.backend.model.repository.CategoryRepository;
import com.kindle.backend.response.PutResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
  @Autowired
  private CategoryRepository categoryRepository;

  public Category findByCategoryId(Integer categoryId){
    return categoryRepository.findFirstByCategoryId(categoryId);
  }

  public List<Category> findAllCategory(){
    return categoryRepository.findAll();
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
