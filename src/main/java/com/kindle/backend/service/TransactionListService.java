package com.kindle.backend.service;

import com.kindle.backend.model.entity.Book;
import com.kindle.backend.model.entity.Customer;
import com.kindle.backend.model.entity.TransactionList;
import com.kindle.backend.model.repository.BookRepository;
import com.kindle.backend.model.repository.CustomerRepository;
import com.kindle.backend.model.repository.TransactionListRepository;
import com.kindle.backend.response.BaseResponse;
import com.kindle.backend.response.dataResponse.DataNoAttributeResponse;
import com.kindle.backend.response.dataResponse.DataNoRelationResponse;
import com.kindle.backend.response.dataResponse.DataWithRelationOnlyResponse;
import com.kindle.backend.response.errorResponse.ErrorDetailResponse;
import com.kindle.backend.response.includedResponse.BookIncludedResponse;
import com.kindle.backend.response.includedResponse.MerchantIncludedResponse;
import com.kindle.backend.response.includedResponse.TransactionIncludedResponse;
import com.kindle.backend.response.relationshipResponse.BaseRelationshipDataResponse;
import com.kindle.backend.response.relationshipResponse.MerchantOrderRelationshipResponse;
import com.kindle.backend.response.relationshipResponse.TransactionListRelationshipResponse;
import com.kindle.backend.response.statusResponse.FailureDataResponse;
import com.kindle.backend.response.statusResponse.SuccessDataResponse;
import com.kindle.backend.response.statusResponse.SuccessDataWithIncludedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionListService {
  @Autowired
  private TransactionListRepository transactionListRepository;
  @Autowired
  private CustomerRepository customerRepository;
  @Autowired
  private BookRepository bookRepository;

  public BaseResponse findAllTransactionListByTransactionId(int transactionId) {
    List<TransactionList> transactionLists = transactionListRepository.findAllByTransactionId(transactionId);

    if (transactionLists == null) {
      ErrorDetailResponse errorDetailResponse = new ErrorDetailResponse(404, "Transaction list not found");

      List<ErrorDetailResponse> errorDetailResponses = new ArrayList<>();
      errorDetailResponses.add(errorDetailResponse);
      FailureDataResponse failureDataResponse = new FailureDataResponse(400, "Bad Request", errorDetailResponses);

      return failureDataResponse;
    } else {
      List<DataWithRelationOnlyResponse> dataWithRelationOnlyResponses = new ArrayList<>();
      List<DataNoRelationResponse> dataNoRelationResponses = new ArrayList<>();

      for (TransactionList transactionList : transactionLists) {
        // relationships
        List<DataNoAttributeResponse> merchantRelationshipDatas = new ArrayList<>();
        DataNoAttributeResponse merchantRelationshipData = new DataNoAttributeResponse(transactionList.getMerchantId(), "merchant");
        merchantRelationshipDatas.add(merchantRelationshipData);
        BaseRelationshipDataResponse merchantRelationship = new BaseRelationshipDataResponse(merchantRelationshipDatas);

        List<DataNoAttributeResponse> bookRelationshipDatas = new ArrayList<>();
        DataNoAttributeResponse bookRelationshipData = new DataNoAttributeResponse(transactionList.getBookSku(), "book");
        bookRelationshipDatas.add(bookRelationshipData);
        BaseRelationshipDataResponse bookRelationship = new BaseRelationshipDataResponse(bookRelationshipDatas);

        TransactionListRelationshipResponse transactionListRelationshipResponse = new TransactionListRelationshipResponse(merchantRelationship, bookRelationship);

        DataWithRelationOnlyResponse<TransactionListRelationshipResponse> dataWithRelationOnlyResponse = new DataWithRelationOnlyResponse<>(transactionList.getTransactionListId(), "transactionlist", transactionListRelationshipResponse);

        dataWithRelationOnlyResponses.add(dataWithRelationOnlyResponse);

        // included
        MerchantIncludedResponse merchantIncludedResponse = new MerchantIncludedResponse(transactionList.getBookDetail().getMerchant().getFullname());
        BookIncludedResponse bookIncludedResponse = new BookIncludedResponse(transactionList.getBookDetail().getTitle(), transactionList.getBookDetail().getAuthor(), transactionList.getBookDetail().getYear(), transactionList.getBookDetail().getPrice(), transactionList.getBookDetail().getDocument());

        DataNoRelationResponse<MerchantIncludedResponse> merchantIncluded = new DataNoRelationResponse<>(transactionList.getMerchantId(), "merchant", merchantIncludedResponse);
        DataNoRelationResponse<BookIncludedResponse> bookIncluded = new DataNoRelationResponse<>(transactionList.getBookSku(), "book", bookIncludedResponse);

        if (!(dataNoRelationResponses.contains(merchantIncluded))) {
          dataNoRelationResponses.add(merchantIncluded);
        }

        if (!(dataNoRelationResponses.contains(bookIncluded))) {
          dataNoRelationResponses.add(bookIncluded);
        }
      }

      SuccessDataWithIncludedResponse<DataWithRelationOnlyResponse, DataNoRelationResponse> successDataResponse = new SuccessDataWithIncludedResponse<>(200, "OK", dataWithRelationOnlyResponses, dataNoRelationResponses);

      return successDataResponse;
    }
  }

  public BaseResponse findAllTransactionListByMerchantId(int merchantId) {
    List<TransactionList> transactionLists = transactionListRepository.findAllByMerchantId(merchantId);

    if ((transactionLists == null)) {
      ErrorDetailResponse errorDetailResponse = new ErrorDetailResponse(404, "Transaction list not found");

      List<ErrorDetailResponse> errorDetailResponses = new ArrayList<>();
      errorDetailResponses.add(errorDetailResponse);
      FailureDataResponse failureDataResponse = new FailureDataResponse(400, "Bad Request", errorDetailResponses);

      return failureDataResponse;
    }
    else {
      List<DataWithRelationOnlyResponse> dataWithRelationOnlyResponses = new ArrayList<>();
      List<DataNoRelationResponse> dataNoRelationResponses = new ArrayList<>();

      for (TransactionList transactionList : transactionLists) {
        // relationships
        List<DataNoAttributeResponse> transactionRelationshipDatas = new ArrayList<>();
        DataNoAttributeResponse transactionRelationshipData = new DataNoAttributeResponse(transactionList.getTransactionId(), "transaction");
        transactionRelationshipDatas.add(transactionRelationshipData);
        BaseRelationshipDataResponse transactionRelationship = new BaseRelationshipDataResponse(transactionRelationshipDatas);

        List<DataNoAttributeResponse> bookRelationshipDatas = new ArrayList<>();
        DataNoAttributeResponse bookRelationshipData = new DataNoAttributeResponse(transactionList.getBookSku(), "book");
        bookRelationshipDatas.add(bookRelationshipData);
        BaseRelationshipDataResponse bookRelationship = new BaseRelationshipDataResponse(bookRelationshipDatas);

        MerchantOrderRelationshipResponse merchantOrderRelationshipResponse = new MerchantOrderRelationshipResponse(transactionRelationship, bookRelationship);

        DataWithRelationOnlyResponse<MerchantOrderRelationshipResponse> dataWithRelationOnlyResponse = new DataWithRelationOnlyResponse<>(transactionList.getTransactionListId(), "transactionlist", merchantOrderRelationshipResponse);

        dataWithRelationOnlyResponses.add(dataWithRelationOnlyResponse);

        // included
        TransactionIncludedResponse transactionIncludedResponse = new TransactionIncludedResponse(transactionList.getTransactionDetail().getDate(), transactionList.getTransactionDetail().getCustomerId(), customerRepository.findFirstByCustomerId(transactionList.getTransactionDetail().getCustomerId()).getUsername());
        BookIncludedResponse bookIncludedResponse = new BookIncludedResponse(transactionList.getBookDetail().getTitle(), transactionList.getBookDetail().getAuthor(), transactionList.getBookDetail().getYear(), transactionList.getBookDetail().getPrice(), transactionList.getBookDetail().getDocument());

        DataNoRelationResponse<TransactionIncludedResponse> transactionIncluded = new DataNoRelationResponse<>(transactionList.getTransactionId(), "transaction", transactionIncludedResponse);
        DataNoRelationResponse<BookIncludedResponse> bookIncluded = new DataNoRelationResponse<>(transactionList.getBookSku(), "book", bookIncludedResponse);

        if (!(dataNoRelationResponses.contains(transactionIncluded))) {
          dataNoRelationResponses.add(transactionIncluded);
        }

        if (!(dataNoRelationResponses.contains(bookIncluded))) {
          dataNoRelationResponses.add(bookIncluded);
        }
      }

      SuccessDataWithIncludedResponse<DataWithRelationOnlyResponse, DataNoRelationResponse> successDataResponse = new SuccessDataWithIncludedResponse<>(200, "OK", dataWithRelationOnlyResponses, dataNoRelationResponses);

      return successDataResponse;
    }
  }

  public BaseResponse save(Integer customerId, TransactionList transactionList) {
    Customer customerResponse = customerRepository.findFirstByCustomerId(customerId);
    Book bookResponse = bookRepository.findFirstByBookSku(transactionList.getBookSku());

    customerResponse.getLibrary().add(bookResponse);
    bookResponse.getOwnerBook().add(customerResponse);

    Customer savedCustomer = customerRepository.save(customerResponse);
    Book savedBook = bookRepository.save(bookResponse);
    TransactionList savedTransactionList = transactionListRepository.save(transactionList);

    if (savedCustomer == null || savedBook == null || savedTransactionList == null) {
      ErrorDetailResponse errorDetailResponse = new ErrorDetailResponse(500, "Cannot create transaction list data");

      List<ErrorDetailResponse> errorDetailResponses = new ArrayList<>();
      errorDetailResponses.add(errorDetailResponse);
      FailureDataResponse failureDataResponse = new FailureDataResponse(500, "Internal server error", errorDetailResponses);

      return failureDataResponse;
    } else {
      DataNoAttributeResponse dataNoAttributeResponse = new DataNoAttributeResponse(savedTransactionList.getTransactionListId(), "transactionlist");

      List<DataNoAttributeResponse> dataNoAttributeResponses = new ArrayList<>();
      dataNoAttributeResponses.add(dataNoAttributeResponse);
      SuccessDataResponse<DataNoAttributeResponse> successDataResponse = new SuccessDataResponse<>(201, "Created", dataNoAttributeResponses);

      return successDataResponse;
    }
  }
}
