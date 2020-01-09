package com.kindle.backend.model.repository;

import com.kindle.backend.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {
  Book findFirstByBookSku(Integer sku);

  List<Book> findAll();

  List<Book> findBookByMerchantId(Integer merchant_id);

  @Transactional
  long deleteBookByBookSku(Integer sku);
}
