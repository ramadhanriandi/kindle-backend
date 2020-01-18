package com.kindle.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kindle.backend.model.entity.Transaction;
import com.kindle.backend.response.attributeResponse.GetAllTransactionByCustomerIdResponse;
import com.kindle.backend.response.dataResponse.DataNoRelationResponse;
import com.kindle.backend.response.statusResponse.SuccessDataResponse;
import com.kindle.backend.service.TransactionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {
  @MockBean
  private TransactionService transactionService;

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ObjectMapper objectMapper;

  private List<Transaction> transactions;
  private Transaction transaction;

  private List<DataNoRelationResponse> dataNoRelationResponses;
  private GetAllTransactionByCustomerIdResponse getAllTransactionByCustomerIdResponse;
  private DataNoRelationResponse<GetAllTransactionByCustomerIdResponse> dataNoRelationResponse;
  private SuccessDataResponse<DataNoRelationResponse> successDataResponse;

  @Before
  public void setUp() throws ParseException {
    objectMapper = new ObjectMapper();

    transactions = new ArrayList<>();
    transaction = new Transaction();
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    Date date = dateFormat.parse("23/09/2007");
    long time = date.getTime();
    transaction.setDate(new Timestamp(time));
    transaction.setCustomerId(1);
    transaction.setTotal(0);
    transaction.setTransactionId(1);
    transactions.add(transaction);

    dataNoRelationResponses = new ArrayList<>();
    getAllTransactionByCustomerIdResponse = new GetAllTransactionByCustomerIdResponse(transaction.getDate(), transaction.getTotal());
    dataNoRelationResponse = new DataNoRelationResponse<>(transaction.getTransactionId(), "transaction", getAllTransactionByCustomerIdResponse);
    dataNoRelationResponses.add(dataNoRelationResponse);
    successDataResponse = new SuccessDataResponse<>(200, "OK", dataNoRelationResponses);

  }

  @Test
  public void getAllTransactionByCustomerId() throws Exception {
    when(transactionService.findAllTransactionByCustomerId(1)).thenReturn(successDataResponse);
    mockMvc.perform(get("/api/transactions?customerId=" + 1).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code", is(successDataResponse.getCode())));
  }

  @Test
  public void getByTransactionId() throws Exception {
    when(transactionService.findByTransactionId(1)).thenReturn(successDataResponse);
    mockMvc.perform(get("/api/transactions/" + 1).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code", is(successDataResponse.getCode())));
  }
}
