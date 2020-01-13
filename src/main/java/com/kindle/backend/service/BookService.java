package com.kindle.backend.service;

import com.kindle.backend.model.entity.Book;
import com.kindle.backend.model.entity.Merchant;
import com.kindle.backend.model.repository.BookRepository;
import com.kindle.backend.model.repository.MerchantRepository;
import com.kindle.backend.response.BaseResponse;
import com.kindle.backend.response.attributeResponse.GetBookResponse;
import com.kindle.backend.response.dataResponse.DataCompleteResponse;
import com.kindle.backend.response.dataResponse.DataNoAttributeResponse;
import com.kindle.backend.response.dataResponse.DataNoRelationResponse;
import com.kindle.backend.response.errorResponse.ErrorDetailResponse;
import com.kindle.backend.response.includedResponse.BookIncludedResponse;
import com.kindle.backend.response.includedResponse.MerchantIncludedResponse;
import com.kindle.backend.response.oldResponse.PutResponse;
import com.kindle.backend.response.relationshipResponse.BaseRelationshipDataResponse;
import com.kindle.backend.response.relationshipResponse.BookRelationshipResponse;
import com.kindle.backend.response.statusResponse.FailureDataResponse;
import com.kindle.backend.response.statusResponse.SuccessDataWithIncludedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
  @Autowired
  private BookRepository bookRepository;
  @Autowired
  private MerchantRepository merchantRepository;

  public Book findByBookSku(Integer sku){
    return bookRepository.findFirstByBookSku(sku);
  }

  public BaseResponse findAllBook(){
    List<Book> books = bookRepository.findAll();

    //if there is no book to be returned
    if (books == null){
      //create error detail response to be inserted into response
      ErrorDetailResponse errorDetailResponse = new ErrorDetailResponse(404, "Book not found");

      //wrap the error detail response into an array
      List<ErrorDetailResponse> errorDetailResponsesList = new ArrayList<>();
      errorDetailResponsesList.add(errorDetailResponse);

      //make a failure response
      FailureDataResponse failureDataResponse = new FailureDataResponse(400, "Bad Request", errorDetailResponsesList);
      return failureDataResponse;
    }
    else{
      //create list of data complete responses to store book and its relationship
      List<DataCompleteResponse> dataCompleteResponseList = new ArrayList<>();

      //create list of merchant IDs for included
      List<Integer> merchantList = new ArrayList<>();

      //iterate through all books
      for (Book book: books) {
        //append the respective merchantId to merchantList
        merchantList.add(book.getMerchantId());

        //create complete data response (includes id, type, attribute, relation)
        DataCompleteResponse<GetBookResponse, BookRelationshipResponse> dataCompleteResponse = new DataCompleteResponse<>();

        //create get book response
        GetBookResponse getBookResponse = new GetBookResponse();
        //set the book response's attributes
        getBookResponse.setTitle(book.getTitle());
        getBookResponse.setAuthor(book.getAuthor());
        getBookResponse.setYear(book.getYear());
        getBookResponse.setDescription(book.getDescription());
        getBookResponse.setPrice(book.getPrice());
        getBookResponse.setDocument(book.getDocument());
        getBookResponse.setVariant(book.getVariant());
        getBookResponse.setUrl(book.getCategories());
        getBookResponse.setCategories(book.getCategories());

        //create book relationship response
        //create the base relationship for book relationship
        List<DataNoAttributeResponse> dataNoAttributeResponseList = new ArrayList<>();
        dataNoAttributeResponseList.add(new DataNoAttributeResponse(book.getMerchantId(), "merchant"));
        BaseRelationshipDataResponse baseRelationshipDataResponse = new BaseRelationshipDataResponse(dataNoAttributeResponseList);
        BookRelationshipResponse bookRelationshipResponse = new BookRelationshipResponse(baseRelationshipDataResponse);

        //append attribute and relationship to data
        dataCompleteResponse.setId(book.getBookSku());
        dataCompleteResponse.setType("book");
        dataCompleteResponse.setAttributes(getBookResponse);
        dataCompleteResponse.setRelationships(bookRelationshipResponse);

        //append dataCompleteResponse to its list
        dataCompleteResponseList.add(dataCompleteResponse);
      }

      //note unique merchant IDs
      List<Integer> uniqueMerchantList = merchantList.stream().distinct().collect(Collectors.toList());

      List<DataNoRelationResponse> dataNoRelationResponseList = new ArrayList<>();

      //loop merchantList
      for (Integer merchantId: uniqueMerchantList){
        //get merchant fullname
        String fullname = merchantRepository.findFirstByMerchantId(merchantId).getFullname();

        //add each merchant to response
        MerchantIncludedResponse merchantIncludedResponse = new MerchantIncludedResponse(fullname);
        DataNoRelationResponse<MerchantIncludedResponse> dataNoRelationResponse = new DataNoRelationResponse<>();
        dataNoRelationResponse.setId(merchantId);
        dataNoRelationResponse.setType("merchant");
        dataNoRelationResponse.setAttributes(merchantIncludedResponse);

        //add dataNoRelationResponse to its list
        dataNoRelationResponseList.add(dataNoRelationResponse);
      }

      //create the final response
      SuccessDataWithIncludedResponse<DataCompleteResponse, DataNoRelationResponse> successDataWithIncludedResponse = new SuccessDataWithIncludedResponse<>(200, "OK", dataCompleteResponseList, dataNoRelationResponseList);

      return successDataWithIncludedResponse;
    }
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

  public List<Book> findBookByMerchantId(Integer merchant_id) {
    return bookRepository.findBookByMerchantId(merchant_id);
  }
}
