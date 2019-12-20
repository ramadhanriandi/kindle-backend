package com.kindle.backend.controller;

import com.kindle.backend.model.constant.ApiPath;
import com.kindle.backend.model.entity.Category;
import com.kindle.backend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class CategoryController {
  @Autowired
  private CategoryService categoryService;

  @RequestMapping(value = ApiPath.CATEGORY, method = RequestMethod.GET)
  public List<Category> getAllCategory() {
    return this.categoryService.findAllCategory();
  }

  @RequestMapping(value = ApiPath.CATEGORY_BY_CATEGORY_ID, method = RequestMethod.GET)
  public Category getCategoryByCategoryId(@PathVariable Integer categoryId) {
    return this.categoryService.findByCategoryId(categoryId);
  }

  @RequestMapping(value = ApiPath.CATEGORY, method = RequestMethod.POST)
  public Category save(@RequestBody Category category) {
    return this.categoryService.save(category);
  }

  @RequestMapping(value = ApiPath.CATEGORY_BY_CATEGORY_ID, method = RequestMethod.PUT)
  public void updateCategory(@PathVariable Integer categoryId, @RequestBody Category category) {
    this.categoryService.updateCategory(categoryId, category);
  }

  @RequestMapping(value = ApiPath.CATEGORY_BY_CATEGORY_ID, method = RequestMethod.DELETE)
  public boolean deleteByCategoryId(@PathVariable Integer categoryId) {
    return this.categoryService.deleteByCategoryId(categoryId) > 0;
  }
}
