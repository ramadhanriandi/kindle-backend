package com.kindle.backend.controller;

import com.kindle.backend.model.constant.ApiPath;
import com.kindle.backend.model.entity.Category;
import com.kindle.backend.response.BaseResponse;
import com.kindle.backend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class CategoryController {
  @Autowired
  private CategoryService categoryService;

  @RequestMapping(value = ApiPath.CATEGORY, method = RequestMethod.GET)
  public BaseResponse getAllCategory() {
    return this.categoryService.findAllCategory();
  }

  @RequestMapping(value = ApiPath.CATEGORY_BY_CATEGORY_ID, method = RequestMethod.GET)
  public BaseResponse getCategoryByCategoryId(@PathVariable Integer categoryId) {
    return this.categoryService.findByCategoryId(categoryId);
  }

  @RequestMapping(value = ApiPath.CATEGORY, method = RequestMethod.POST)
  public BaseResponse save(@RequestBody Category category) {
    return this.categoryService.save(category);
  }

  @RequestMapping(value = ApiPath.CATEGORY_BY_CATEGORY_ID, method = RequestMethod.PUT)
  public BaseResponse updateCategory(@PathVariable Integer categoryId, @RequestBody Category category) {
    return this.categoryService.updateCategory(categoryId, category);
  }

  @RequestMapping(value = ApiPath.CATEGORY_BY_CATEGORY_ID, method = RequestMethod.DELETE)
  public boolean deleteByCategoryId(@PathVariable Integer categoryId) {
    return this.categoryService.deleteByCategoryId(categoryId) > 0;
  }
}
