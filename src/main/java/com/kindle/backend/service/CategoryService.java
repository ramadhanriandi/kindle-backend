package com.kindle.backend.service;

import com.kindle.backend.model.entity.Category;
import com.kindle.backend.model.repository.CategoryRepository;
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

  public void updateCategory(Integer categoryId, Category category) {
    category.setCategoryId(categoryId);
    categoryRepository.save(category);
  }

  public long deleteByCategoryId(Integer categoryId) {
    return categoryRepository.deleteCategoryByCategoryId(categoryId);
  }
}
