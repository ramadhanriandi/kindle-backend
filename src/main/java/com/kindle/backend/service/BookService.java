package com.kindle.backend.service;

import com.kindle.backend.model.entity.Book;
import com.kindle.backend.model.repository.BookRepository;
import com.kindle.backend.response.BookDetailResponse;
import com.kindle.backend.response.PutResponse;
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

  public PutResponse updateBook(Integer sku, Book book) {
    PutResponse updateResponse = new PutResponse();
    book.setBookSku(sku);
    Book bookResponse = bookRepository.save(book);

    if (bookResponse == null) {
      updateResponse.setCode(401);
      updateResponse.setMessage("Error: update fail");
    } else {
      updateResponse.setCode(200);
      updateResponse.setMessage("Update success");
    }

    return updateResponse;
  }

  public long deleteByBookSku(Integer sku) {
    return bookRepository.deleteBookByBookSku(sku);
  }

  public List<Book> findBookByMerchantId(Integer merchant_id){ return bookRepository.findBookByMerchantId(merchant_id); }

  public BookDetailResponse getBookDetail(Integer sku) {
    Book bookResponse = bookRepository.findFirstByBookSku(sku);
    BookDetailResponse bookDetailResponse = new BookDetailResponse();

    if (bookDetailResponse == null) {
      bookDetailResponse.setCode(401);
      bookDetailResponse.setMessage("Fetch failed");
    } else {
      bookDetailResponse.setCode(200);
      bookDetailResponse.setMessage("Fetch success");
      bookDetailResponse.setBookData(bookResponse);
      bookDetailResponse.setMerchant(bookResponse.getMerchant().getFullname());
      bookDetailResponse.setCategories  (bookResponse.getCategories());
    }

    return bookDetailResponse;
  }
}
