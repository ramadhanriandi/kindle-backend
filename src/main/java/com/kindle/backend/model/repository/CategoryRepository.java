package com.kindle.backend.model.repository;

import com.kindle.backend.model.entity.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {
  Category findFirstByCategoryId(Integer categoryId);

  List<Category> findAll();

  @Transactional
  long deleteCategoryByCategoryId(Integer categoryId);
}
