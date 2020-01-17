package com.kindle.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kindle.backend.model.entity.TransactionList;
import com.kindle.backend.response.dataResponse.DataNoAttributeResponse;
import com.kindle.backend.response.dataResponse.DataNoRelationResponse;
import com.kindle.backend.response.dataResponse.DataWithRelationOnlyResponse;
import com.kindle.backend.response.includedResponse.BookIncludedResponse;
import com.kindle.backend.response.includedResponse.MerchantIncludedResponse;
import com.kindle.backend.response.relationshipResponse.BaseRelationshipDataResponse;
import com.kindle.backend.response.relationshipResponse.TransactionListRelationshipResponse;
import com.kindle.backend.response.statusResponse.SuccessDataWithIncludedResponse;
import com.kindle.backend.service.TransactionListService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(TransactionListController.class)
public class TransactionListControllerTest {
  @MockBean
  private TransactionListService transactionListService;

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ObjectMapper objectMapper;

  private List<TransactionList> transactionLists;
  private TransactionList transactionList;

  private List<DataWithRelationOnlyResponse> dataWithRelationOnlyResponses;
  private List<DataNoRelationResponse> dataNoRelationResponses;
  private List<DataNoAttributeResponse> merchantRelationshipDatas;
  private DataNoAttributeResponse merchantRelationshipData;
  private BaseRelationshipDataResponse merchantRelationship;
  private List<DataNoAttributeResponse> bookRelationshipDatas;
  private DataNoAttributeResponse bookRelationshipData;
  private BaseRelationshipDataResponse bookRelationship;
  private TransactionListRelationshipResponse transactionListRelationshipResponse;
  private DataWithRelationOnlyResponse<TransactionListRelationshipResponse> dataWithRelationOnlyResponse;
  private MerchantIncludedResponse merchantIncludedResponse;
  private BookIncludedResponse bookIncludedResponse;
  private DataNoRelationResponse<MerchantIncludedResponse> merchantIncluded;
  private DataNoRelationResponse<BookIncludedResponse> bookIncluded;
  private SuccessDataWithIncludedResponse<DataWithRelationOnlyResponse, DataNoRelationResponse> successDataResponse;

  @Before
  public void setUp() throws ParseException {
    objectMapper = new ObjectMapper();

    transactionLists = new ArrayList<>();
    transactionList = new TransactionList();
    transactionList.setBookSku(1);
    transactionList.setMerchantId(1);
    transactionList.setTransactionId(1);
    transactionList.setTransactionListId(1);

    dataWithRelationOnlyResponses = new ArrayList<>();
    dataNoRelationResponses = new ArrayList<>();
    merchantRelationshipDatas = new ArrayList<>();
    merchantRelationshipData = new DataNoAttributeResponse(transactionList.getMerchantId(), "merchant");
    merchantRelationshipDatas.add(merchantRelationshipData);
    merchantRelationship = new BaseRelationshipDataResponse(merchantRelationshipDatas);
    bookRelationshipDatas = new ArrayList<>();
    bookRelationshipData = new DataNoAttributeResponse(transactionList.getBookSku(), "book");
    bookRelationshipDatas.add(bookRelationshipData);
    bookRelationship = new BaseRelationshipDataResponse(bookRelationshipDatas);
    transactionListRelationshipResponse = new TransactionListRelationshipResponse(merchantRelationship, bookRelationship);
    dataWithRelationOnlyResponse = new DataWithRelationOnlyResponse<>(transactionList.getTransactionListId(), "transactionlist", transactionListRelationshipResponse);
    dataWithRelationOnlyResponses.add(dataWithRelationOnlyResponse);
    merchantIncludedResponse = new MerchantIncludedResponse();
    bookIncludedResponse = new BookIncludedResponse();
    merchantIncluded = new DataNoRelationResponse<>(transactionList.getMerchantId(), "merchant", merchantIncludedResponse);
    bookIncluded = new DataNoRelationResponse<>(transactionList.getBookSku(), "book", bookIncludedResponse);
    dataNoRelationResponses.add(merchantIncluded);
    dataNoRelationResponses.add(bookIncluded);
    successDataResponse = new SuccessDataWithIncludedResponse<>(200, "OK", dataWithRelationOnlyResponses, dataNoRelationResponses);
  }

  @Test
  public void getAllTransactionListByTransactionId() throws Exception {
    when(transactionListService.findAllTransactionListByTransactionId(1)).thenReturn(successDataResponse);
    mockMvc.perform(get("/api/transactionlists?transactionId=" + 1).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code", is(successDataResponse.getCode())));
  }
}
