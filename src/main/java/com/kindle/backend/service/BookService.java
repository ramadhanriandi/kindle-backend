package com.kindle.backend.service;

import com.kindle.backend.model.entity.Book;
import com.kindle.backend.model.repository.BookRepository;
import com.kindle.backend.model.repository.MerchantRepository;
import com.kindle.backend.response.BaseResponse;
import com.kindle.backend.response.attributeResponse.GetBookResponse;
import com.kindle.backend.response.attributeResponse.GetMerchantCatalogResponse;
import com.kindle.backend.response.dataResponse.DataCompleteResponse;
import com.kindle.backend.response.dataResponse.DataNoAttributeResponse;
import com.kindle.backend.response.dataResponse.DataNoRelationResponse;
import com.kindle.backend.response.errorResponse.ErrorDetailResponse;
import com.kindle.backend.response.includedResponse.MerchantIncludedResponse;
import com.kindle.backend.response.relationshipResponse.BaseRelationshipDataResponse;
import com.kindle.backend.response.relationshipResponse.BookRelationshipResponse;
import com.kindle.backend.response.statusResponse.FailureDataResponse;
import com.kindle.backend.response.statusResponse.SuccessDataResponse;
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

  public BaseResponse findByBookSku(Integer sku){
    Book bookResult =  bookRepository.findFirstByBookSku(sku);

    //if there is no book to be returned
    if (bookResult == null){
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
      //create attribute get book response for complete data response
      GetBookResponse getBookResponse = new GetBookResponse();
      //set the book response's attributes
      getBookResponse.setTitle(bookResult.getTitle());
      getBookResponse.setAuthor(bookResult.getAuthor());
      getBookResponse.setYear(bookResult.getYear());
      getBookResponse.setDescription(bookResult.getDescription());
      getBookResponse.setPrice(bookResult.getPrice());
      getBookResponse.setDocument(bookResult.getDocument());
      getBookResponse.setVariant(bookResult.getVariant());
      getBookResponse.setUrl(bookResult.getUrl());
      getBookResponse.setCategories(bookResult.getCategories());

      //create relationship response for complete data response
      //create data no attribute respnose first
      List<DataNoAttributeResponse> dataNoAttributeResponseList = new ArrayList<>();
      dataNoAttributeResponseList.add(new DataNoAttributeResponse(bookResult.getMerchantId(), "merchant"));
      //create base relationship response then
      BaseRelationshipDataResponse baseRelationshipDataResponse = new BaseRelationshipDataResponse(dataNoAttributeResponseList);
      //create Book relationship response then
      BookRelationshipResponse bookRelationshipResponse = new BookRelationshipResponse(baseRelationshipDataResponse);

      //create complete data response (includes id, type, attribute, relation)
      DataCompleteResponse<GetBookResponse, BookRelationshipResponse> dataCompleteResponse = new DataCompleteResponse<>();
      dataCompleteResponse.setId(bookResult.getBookSku());
      dataCompleteResponse.setType("book");
      dataCompleteResponse.setAttributes(getBookResponse);
      dataCompleteResponse.setRelationships(bookRelationshipResponse);
      List<DataCompleteResponse> dataCompleteResponseList = new ArrayList<>();
      dataCompleteResponseList.add(dataCompleteResponse);

      //create included response
      //get merchant attribute first
      //get fullname of merchant
      String fullname = merchantRepository.findFirstByMerchantId(bookResult.getMerchantId()).getFullname();
      MerchantIncludedResponse merchantIncludedResponse = new MerchantIncludedResponse(fullname);
      //create Data no relation response to be wrapped as include response
      DataNoRelationResponse<MerchantIncludedResponse> dataNoRelationResponse = new DataNoRelationResponse<>();
      dataNoRelationResponse.setId(bookResult.getMerchantId());
      dataNoRelationResponse.setType("merchant");
      dataNoRelationResponse.setAttributes(merchantIncludedResponse);
      //create list of data no relation response as final wrap for include response
      List<DataNoRelationResponse> dataNoRelationResponseList = new ArrayList<>();
      dataNoRelationResponseList.add(dataNoRelationResponse);

      //wrap the success response
      SuccessDataWithIncludedResponse<DataCompleteResponse, DataNoRelationResponse> successDataWithIncludedResponse = new SuccessDataWithIncludedResponse<>(200, "OK", dataCompleteResponseList, dataNoRelationResponseList);

      return successDataWithIncludedResponse;
    }
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

  public BaseResponse save(Book book) {
       Book bookResult = bookRepository.save(book);

       //if book cannot be created
       if (bookResult == null){
           //create error detail response to be inserted into response
           ErrorDetailResponse errorDetailResponse = new ErrorDetailResponse(500, "Cannot create the book");

           //wrap the error detail response into an array
           List<ErrorDetailResponse> errorDetailResponsesList = new ArrayList<>();
           errorDetailResponsesList.add(errorDetailResponse);

           //make a failure response
           FailureDataResponse failureDataResponse = new FailureDataResponse(500, "Internal server error", errorDetailResponsesList);
           return failureDataResponse;
       }
       else{
           //create the content of data response
           List<DataNoAttributeResponse> dataNoAttributeResponseList = new ArrayList<>();
           dataNoAttributeResponseList.add(new DataNoAttributeResponse(bookResult.getBookSku(), "book"));

           //create the success message
           SuccessDataResponse<DataNoAttributeResponse> successDataResponse = new SuccessDataResponse<>(201, "Created", dataNoAttributeResponseList);

           return successDataResponse;
       }
  }

  public BaseResponse updateBook(Integer sku, Book book) {
    Book bookResponse = bookRepository.findFirstByBookSku(sku);

    //if book not found
    if(bookResponse == null){
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
      book.setBookSku(sku);
      Book bookUpdate = bookRepository.save(book);

      //if book cannot be deleted
      if (bookUpdate == null ) {
        //create error detail response to be inserted into response
        ErrorDetailResponse errorDetailResponse = new ErrorDetailResponse(500, "Cannot update the book");

        //wrap the error detail response into an array
        List<ErrorDetailResponse> errorDetailResponsesList = new ArrayList<>();
        errorDetailResponsesList.add(errorDetailResponse);

        //make a failure response
        FailureDataResponse failureDataResponse = new FailureDataResponse(500, "Internal server error", errorDetailResponsesList);
        return failureDataResponse;
      }
      else{
        //create the content of data response
        List<DataNoAttributeResponse> dataNoAttributeResponseList = new ArrayList<>();
        dataNoAttributeResponseList.add(new DataNoAttributeResponse(sku, "book"));

        //create the success message
        SuccessDataResponse<DataNoAttributeResponse> successDataResponse = new SuccessDataResponse<>(200, "OK", dataNoAttributeResponseList);

        return successDataResponse;
      }
    }
  }

  public BaseResponse deleteByBookSku(Integer sku) {
    Book bookResponse = bookRepository.findFirstByBookSku(sku);

    //if book not found
    if(bookResponse == null){
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
      long isBookDeleted = bookRepository.deleteBookByBookSku(sku);
      //if book cannot be deleted
      if (isBookDeleted <= 0) {
        //create error detail response to be inserted into response
        ErrorDetailResponse errorDetailResponse = new ErrorDetailResponse(500, "Cannot delete the book");

        //wrap the error detail response into an array
        List<ErrorDetailResponse> errorDetailResponsesList = new ArrayList<>();
        errorDetailResponsesList.add(errorDetailResponse);

        //make a failure response
        FailureDataResponse failureDataResponse = new FailureDataResponse(500, "Internal server error", errorDetailResponsesList);
        return failureDataResponse;
      }
      else{
        //create the content of data response
        List<DataNoAttributeResponse> dataNoAttributeResponseList = new ArrayList<>();
        dataNoAttributeResponseList.add(new DataNoAttributeResponse(sku, "book"));

        //create the success message
        SuccessDataResponse<DataNoAttributeResponse> successDataResponse = new SuccessDataResponse<>(200, "OK", dataNoAttributeResponseList);

        return successDataResponse;
      }
    }
  }

  public BaseResponse findBookByMerchantId(Integer merchant_id) {
    List<Book> bookResultList = bookRepository.findBookByMerchantId(merchant_id);

    //if there is no book to be returned
    if(bookResultList == null){
        //create error detail response to be inserted into response
        ErrorDetailResponse errorDetailResponse = new ErrorDetailResponse(404, "Book or merchant not found");

        //wrap the error detail response into an array
        List<ErrorDetailResponse> errorDetailResponsesList = new ArrayList<>();
        errorDetailResponsesList.add(errorDetailResponse);

        //make a failure response
        FailureDataResponse failureDataResponse = new FailureDataResponse(400, "Bad Request", errorDetailResponsesList);
        return failureDataResponse;
    }
    else{
        //create list containing all the data
        List<DataNoRelationResponse> dataNoRelationResponseList = new ArrayList<>();

        for (Book book : bookResultList){
            //create the attribute
            GetMerchantCatalogResponse getMerchantCatalogResponse = new GetMerchantCatalogResponse(book.getDocument());

            //wrap the attribute in data no relation response
            DataNoRelationResponse<GetMerchantCatalogResponse> dataNoRelationResponse = new DataNoRelationResponse<>();
            dataNoRelationResponse.setId(book.getBookSku());
            dataNoRelationResponse.setType("book");
            dataNoRelationResponse.setAttributes(getMerchantCatalogResponse);

            //add the data to the data list
            dataNoRelationResponseList.add(dataNoRelationResponse);
        }

        //wrap the list into success response
        SuccessDataResponse<DataNoRelationResponse> successDataResponse = new SuccessDataResponse<>(200, "OK", dataNoRelationResponseList);

        return successDataResponse;
    }
  }
}
