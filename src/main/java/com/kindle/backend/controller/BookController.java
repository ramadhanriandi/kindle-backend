package com.kindle.backend.controller;

import com.kindle.backend.model.constant.ApiPath;
import com.kindle.backend.model.entity.Book;
import com.kindle.backend.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class BookController {
  @Autowired
  private BookService bookService;

  @RequestMapping(value = ApiPath.BOOK, method = RequestMethod.GET)
  public List<Book> getAllBook() {
    return this.bookService.findAllBook();
  }

  @RequestMapping(value = ApiPath.BOOK_BY_BOOK_SKU, method = RequestMethod.GET)
  public Book getBookBySku(@PathVariable Integer sku) {
    return this.bookService.findByBookSku(sku);
  }

  @RequestMapping(value = ApiPath.BOOK, method = RequestMethod.POST)
  public Book save(@RequestBody Book book) {
    return this.bookService.save(book);
  }

  @RequestMapping(value = ApiPath.BOOK_BY_BOOK_SKU, method = RequestMethod.PUT)
  public void updateBook(@PathVariable Integer sku, @RequestBody Book book) {
    this.bookService.updateBook(sku, book);
  }

  @RequestMapping(value = ApiPath.BOOK_BY_BOOK_SKU, method = RequestMethod.DELETE)
  public boolean deleteByBookSku(@PathVariable Integer sku) {
    return this.bookService.deleteByBookSku(sku) > 0;
  }
}