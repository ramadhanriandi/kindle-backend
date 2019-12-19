package com.kindle.backend.service;

import com.kindle.backend.model.entity.Book;
import com.kindle.backend.model.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
  @Autowired
  private BookRepository bookRepository;

  public Book findByBookSku(Integer sku){
    return bookRepository.findFirstByBookSku(sku);
  }

  public List<Book> findAllBook(){
    return bookRepository.findAll();
  }

  public Book save(Book book) {
    return bookRepository.save(book);
  }

  public void updateBook(Integer sku, Book book) {
    book.setBookSku(sku);
    bookRepository.save(book);
  }

  public long deleteByBookSku(Integer sku) {
    return bookRepository.deleteBookByBookSku(sku);
  }

  public List<Book> findBookByMerchantId(Integer merchant_id){ return bookRepository.findBookByMerchantId(merchant_id); }
}
