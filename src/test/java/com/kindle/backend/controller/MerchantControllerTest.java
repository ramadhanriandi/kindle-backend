package com.kindle.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kindle.backend.model.entity.Book;
import com.kindle.backend.model.entity.Merchant;
import com.kindle.backend.response.attributeResponse.GetAllMerchantResponse;
import com.kindle.backend.response.attributeResponse.GetMerchantByMerchantIdResponse;
import com.kindle.backend.response.dataResponse.DataNoRelationResponse;
import com.kindle.backend.response.statusResponse.SuccessDataResponse;
import com.kindle.backend.service.BookService;
import com.kindle.backend.service.MerchantService;
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

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(MerchantController.class)
public class MerchantControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private BookService bookService;

  @MockBean
  private TransactionListService transactionListService;

  @MockBean
  private MerchantService merchantService;

  @MockBean
  private ObjectMapper objectMapper;

  private List<Merchant> merchants;
  private Merchant merchant;

  private List<Book> books;
  private Book book;

  private List<DataNoRelationResponse> dataNoRelationResponsesGetAllMerchant;
  private GetAllMerchantResponse getAllMerchantResponseGetAllMerchant;
  private DataNoRelationResponse<GetAllMerchantResponse> dataNoRelationResponseGetAllMerchant;
  private SuccessDataResponse<DataNoRelationResponse> successDataResponseGetAllMerchant;

  private List<DataNoRelationResponse> dataNoRelationResponsesGetMerchantCatalog;
  private GetMerchantByMerchantIdResponse getMerchantByMerchantIdResponseGetMerchantCatalog;
  private DataNoRelationResponse<GetMerchantByMerchantIdResponse> dataNoRelationResponseGetMerchantCatalog;
  private SuccessDataResponse<DataNoRelationResponse> successDataResponseGetMerchantCatalog;

  @Before
  public void setUp() {
    objectMapper = new ObjectMapper();

    merchant = new Merchant();
    merchant.setMerchantId(1);
    merchant.setDescription("description");
    merchant.setEmail("email");
    merchant.setFullname("fullname");
    merchant.setPassword("password");
    merchant.setPhone("phone");
    merchant.setStatus("status");

    merchants = new ArrayList<>();
    merchants.add(merchant);

    books = new ArrayList<>();
    book = new Book();
    book.setBookSku(1);
    book.setTitle("Title");
    book.setCategories("Categories");
    book.setUrl("Url");
    book.setVariant("Variant");
    book.setDocument("Document");
    book.setPrice(1000);
    book.setDescription("Description");
    book.setYear(2020);
    book.setAuthor("Author");
    book.setMerchantId(1);

    dataNoRelationResponsesGetAllMerchant = new ArrayList<>();
    getAllMerchantResponseGetAllMerchant = new GetAllMerchantResponse(merchant.getFullname(), merchant.getStatus());
    dataNoRelationResponseGetAllMerchant = new DataNoRelationResponse<>(merchant.getMerchantId(), "merchant", getAllMerchantResponseGetAllMerchant);
    dataNoRelationResponsesGetAllMerchant.add(dataNoRelationResponseGetAllMerchant);
    successDataResponseGetAllMerchant = new SuccessDataResponse<>(200, "OK", dataNoRelationResponsesGetAllMerchant);

    dataNoRelationResponsesGetMerchantCatalog = new ArrayList<>();
    getMerchantByMerchantIdResponseGetMerchantCatalog = new GetMerchantByMerchantIdResponse(merchant.getUsername(), merchant.getEmail(), merchant.getPassword(), merchant.getFullname(), merchant.getDescription(), merchant.getPhone(), merchant.getStatus());
    dataNoRelationResponseGetMerchantCatalog = new DataNoRelationResponse<>(merchant.getMerchantId(), "merchant", getMerchantByMerchantIdResponseGetMerchantCatalog);
    dataNoRelationResponsesGetMerchantCatalog.add(dataNoRelationResponseGetMerchantCatalog);
    successDataResponseGetMerchantCatalog = new SuccessDataResponse<>(200, "OK", dataNoRelationResponsesGetMerchantCatalog);
  }

  @Test
  public void getAllMerchant() throws Exception {
    when(merchantService.findAllMerchant()).thenReturn(successDataResponseGetAllMerchant);
    mockMvc.perform(get("/api/merchants").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code", is(successDataResponseGetAllMerchant.getCode())));
  }

  @Test
  public void getMerchantById() throws Exception {
    when(merchantService.findByMerchantId(1)).thenReturn(successDataResponseGetAllMerchant);
    mockMvc.perform(get("/api/merchants/" + 1).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code", is(successDataResponseGetAllMerchant.getCode())));
  }

  @Test
  public void getMerchantCatalog() throws Exception {
    when(bookService.findBookByMerchantId(1)).thenReturn(successDataResponseGetMerchantCatalog);
    mockMvc.perform(get("/api/merchants/" + 1 + "/catalog").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code", is(successDataResponseGetMerchantCatalog.getCode())));
  }

  @Test
  public void getAllTransactionListByMerchantId() throws Exception {
    when(transactionListService.findAllTransactionListByMerchantId(1)).thenReturn(successDataResponseGetMerchantCatalog);
    mockMvc.perform(get("/api/merchants/" + 1 + "/orders").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code", is(successDataResponseGetMerchantCatalog.getCode())));
  }
}
