package com.kindle.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kindle.backend.model.entity.Book;
import com.kindle.backend.response.attributeResponse.GetBookResponse;
import com.kindle.backend.response.dataResponse.DataCompleteResponse;
import com.kindle.backend.response.dataResponse.DataNoAttributeResponse;
import com.kindle.backend.response.dataResponse.DataNoRelationResponse;
import com.kindle.backend.response.includedResponse.MerchantIncludedResponse;
import com.kindle.backend.response.relationshipResponse.BaseRelationshipDataResponse;
import com.kindle.backend.response.relationshipResponse.BookRelationshipResponse;
import com.kindle.backend.response.statusResponse.SuccessDataWithIncludedResponse;
import com.kindle.backend.service.BookService;
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
@WebMvcTest(BookController.class)
public class BookControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private BookService bookService;

  @MockBean
  private ObjectMapper objectMapper;

  private List<DataCompleteResponse> dataCompleteResponses;
  private List<DataNoRelationResponse> dataNoRelationResponses;
  private SuccessDataWithIncludedResponse<DataCompleteResponse, DataNoRelationResponse> successDataWithIncludedResponse;
  private List<Book> books;

  private GetBookResponse getBookResponseGetBookBySku;
  private List<DataNoAttributeResponse> dataNoAttributeResponseListGetBookBySku;
  private BaseRelationshipDataResponse baseRelationshipDataResponseGetBookBySku;
  private BookRelationshipResponse bookRelationshipResponseGetBookBySku;
  private DataCompleteResponse<GetBookResponse, BookRelationshipResponse> dataCompleteResponseGetBookBySku;
  private List<DataCompleteResponse> dataCompleteResponseListGetBookBySku;
  private MerchantIncludedResponse merchantIncludedResponseGetBookBySku;
  private DataNoRelationResponse<MerchantIncludedResponse> dataNoRelationResponseGetBookBySku;
  private List<DataNoRelationResponse> dataNoRelationResponseListGetBookBySku;
  private SuccessDataWithIncludedResponse successDataWithIncludedResponseGetBookBySku;
  private Book book;

  @Before
  public void setUp() {
    objectMapper = new ObjectMapper();

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

    dataCompleteResponses = new ArrayList<>();
    dataCompleteResponses.add(new DataCompleteResponse());
    dataNoRelationResponses = new ArrayList<>();
    dataNoRelationResponses.add(new DataNoRelationResponse());
    successDataWithIncludedResponse = new SuccessDataWithIncludedResponse<>(200, "OK", dataCompleteResponses, dataNoRelationResponses);

    getBookResponseGetBookBySku = new GetBookResponse();
    dataNoAttributeResponseListGetBookBySku = new ArrayList<>();
    dataNoAttributeResponseListGetBookBySku.add(new DataNoAttributeResponse(1, "merchant"));
    baseRelationshipDataResponseGetBookBySku = new BaseRelationshipDataResponse();
    bookRelationshipResponseGetBookBySku = new BookRelationshipResponse();
    dataCompleteResponseGetBookBySku = new DataCompleteResponse<>();
    dataCompleteResponseListGetBookBySku = new ArrayList<>();
    dataCompleteResponseListGetBookBySku.add(dataCompleteResponseGetBookBySku);
    merchantIncludedResponseGetBookBySku = new MerchantIncludedResponse("Fullname");
    dataNoRelationResponseGetBookBySku = new DataNoRelationResponse<>();
    dataNoRelationResponseListGetBookBySku = new ArrayList<>();
    dataNoRelationResponseListGetBookBySku.add(dataNoRelationResponseGetBookBySku);
    successDataWithIncludedResponseGetBookBySku = new SuccessDataWithIncludedResponse<>(200, "OK", dataCompleteResponseListGetBookBySku, dataNoRelationResponseListGetBookBySku);
  }

  @Test
  public void getAllCategory() throws Exception {
    when(bookService.findAllBook()).thenReturn(successDataWithIncludedResponse);
    mockMvc.perform(get("/api/books").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code", is(successDataWithIncludedResponse.getCode())));
  }

  @Test
  public void getBookBySku() throws Exception {
    when(bookService.findByBookSku(1)).thenReturn(successDataWithIncludedResponseGetBookBySku);
    mockMvc.perform(get("/api/books/" + 1).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code", is(successDataWithIncludedResponseGetBookBySku.getCode())));
  }
}
