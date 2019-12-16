package com.kindle.backend.model.repository;

import com.kindle.backend.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
  Book findFirstByBook_sku(Integer sku);

  List<Book> findAll();

  @Transactional
  long deleteBookByBook_sku(Integer sku);
}
