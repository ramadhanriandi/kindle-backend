package com.kindle.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kindle.backend.model.entity.Book;
import com.kindle.backend.model.entity.Customer;
import com.kindle.backend.response.attributeResponse.*;
import com.kindle.backend.response.dataResponse.DataCompleteResponse;
import com.kindle.backend.response.dataResponse.DataNoAttributeResponse;
import com.kindle.backend.response.dataResponse.DataNoRelationResponse;
import com.kindle.backend.response.includedResponse.MerchantIncludedResponse;
import com.kindle.backend.response.relationshipResponse.BaseRelationshipDataResponse;
import com.kindle.backend.response.relationshipResponse.CartRelationshipResponse;
import com.kindle.backend.response.relationshipResponse.WishlistRelationshipResponse;
import com.kindle.backend.response.statusResponse.SuccessDataResponse;
import com.kindle.backend.response.statusResponse.SuccessDataWithIncludedResponse;
import com.kindle.backend.service.CustomerService;
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
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private CustomerService customerService;

  @MockBean
  private ObjectMapper objectMapper;

  private List<Customer> customers;
  private Customer customer;

  private List<DataNoRelationResponse> dataNoRelationResponses;
  private GetAllCustomerResponse getAllCustomerResponse;
  private DataNoRelationResponse<GetAllCustomerResponse> dataNoRelationResponse;
  private SuccessDataResponse<DataNoRelationResponse> successDataResponse;

  private List<DataNoRelationResponse> dataNoRelationResponsesGetCustomerById;
  private GetCustomerByCustomerIdResponse getCustomerByCustomerIdResponseGetCustomerById;
  private DataNoRelationResponse<GetCustomerByCustomerIdResponse> dataNoRelationResponseGetCustomerById;
  private SuccessDataResponse<DataNoRelationResponse> successDataResponseGetCustomerById;

  private Book book;
  private List<Book> cart;
  private List<DataCompleteResponse> dataCompleteResponsesGetCustomerCart;
  private List<DataNoRelationResponse> dataNoRelationResponsesGetCustomerCart;
  private GetCustomerCartResponse getCustomerCartResponseGetCustomerCart;
  private List<DataNoAttributeResponse> merchantRelationshipDatasGetCustomerCart;
  private DataNoAttributeResponse merchantRelationshipDataGetCustomerCart;
  private BaseRelationshipDataResponse merchantRelationshipGetCustomerCart;
  private CartRelationshipResponse cartRelationshipResponseGetCustomerCart;
  private DataCompleteResponse<GetCustomerCartResponse, CartRelationshipResponse> dataCompleteResponseGetCustomerCart;
  private MerchantIncludedResponse merchantIncludedResponseGetCustomerCart;
  private DataNoRelationResponse<MerchantIncludedResponse> merchantIncludedGetCustomerCart;
  private SuccessDataWithIncludedResponse<DataCompleteResponse, DataNoRelationResponse> successDataResponseGetCustomerCart;

  private List<Book> wishlist;
  private List<DataCompleteResponse> dataCompleteResponsesGetCustomerWishlist;
  private List<DataNoRelationResponse> dataNoRelationResponsesGetCustomerWishlist;
  private GetCustomerWishlistResponse getCustomerCartResponseGetCustomerWishlist;
  private List<DataNoAttributeResponse> merchantRelationshipDatasGetCustomerWishlist;
  private DataNoAttributeResponse merchantRelationshipDataGetCustomerWishlist;
  private BaseRelationshipDataResponse merchantRelationshipGetCustomerWishlist;
  private WishlistRelationshipResponse wishlistRelationshipResponse;
  private DataCompleteResponse<GetCustomerWishlistResponse, WishlistRelationshipResponse> dataCompleteResponseGetCustomerWishlist;
  private MerchantIncludedResponse merchantIncludedResponseGetCustomerWishlist;
  private DataNoRelationResponse<MerchantIncludedResponse> merchantIncludedGetCustomerWishlist;
  private SuccessDataWithIncludedResponse<DataCompleteResponse, DataNoRelationResponse> successDataResponseGetCustomerWishlist;

  private List<Book> library;
  private List<DataNoRelationResponse> dataNoRelationResponsesGetCustomerLibrary;
  private GetCustomerLibraryResponse getCustomerLibraryResponseGetCustomerLibrary;
  private DataNoRelationResponse<GetCustomerLibraryResponse> dataNoRelationResponseGetCustomerLibrary;
  private SuccessDataResponse<DataNoRelationResponse> successDataResponseGetCustomerLibrary;

  private DataNoAttributeResponse dataNoAttributeResponseIsOnCart;
  private List<DataNoAttributeResponse> dataNoAttributeResponsesIsOnCart;
  private SuccessDataResponse<DataNoAttributeResponse> successDataResponseIsOnCart;

  private DataNoAttributeResponse dataNoAttributeResponseIsOnLibrary;
  private List<DataNoAttributeResponse> dataNoAttributeResponsesIsOnLibrary;
  private SuccessDataResponse<DataNoAttributeResponse> successDataResponseIsOnLibrary;

  private DataNoAttributeResponse dataNoAttributeResponseIsOnWishlist;
  private List<DataNoAttributeResponse> dataNoAttributeResponsesIsOnWishlist;
  private SuccessDataResponse<DataNoAttributeResponse> successDataResponseIsOnWishlist;

  @Before
  public void setUp() {
    objectMapper = new ObjectMapper();

    customers = new ArrayList<>();
    customer = new Customer();
    customer.setCustomerId(1);
    customer.setEmail("email");
    customer.setPassword("password");
    customer.setUsername("username");
    customer.setStatus("status");
    customers.add(customer);
    dataNoRelationResponses = new ArrayList<>();
    getAllCustomerResponse = new GetAllCustomerResponse(customer.getUsername(), customer.getStatus(), customer.getEmail(), customer.getPassword());
    dataNoRelationResponse = new DataNoRelationResponse<>(customer.getCustomerId(), "customer", getAllCustomerResponse);
    dataNoRelationResponses.add(dataNoRelationResponse);
    successDataResponse = new SuccessDataResponse<>(200, "OK", dataNoRelationResponses);

    dataNoRelationResponsesGetCustomerById = new ArrayList<>();
    getCustomerByCustomerIdResponseGetCustomerById = new GetCustomerByCustomerIdResponse(customer.getUsername(), customer.getStatus(), customer.getEmail(), customer.getPassword());
    dataNoRelationResponseGetCustomerById = new DataNoRelationResponse<>(customer.getCustomerId(), "customer", getCustomerByCustomerIdResponseGetCustomerById);
    dataNoRelationResponsesGetCustomerById.add(dataNoRelationResponseGetCustomerById);
    successDataResponseGetCustomerById = new SuccessDataResponse<>(200, "OK", dataNoRelationResponsesGetCustomerById);

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
    cart = new ArrayList<>();
    cart.add(book);
    dataCompleteResponsesGetCustomerCart = new ArrayList<>();
    dataNoRelationResponsesGetCustomerCart = new ArrayList<>();
    getCustomerCartResponseGetCustomerCart = new GetCustomerCartResponse(book.getTitle(), book.getAuthor(), book.getYear(), book.getPrice(), book.getDocument());
    merchantRelationshipDatasGetCustomerCart = new ArrayList<>();
    merchantRelationshipDataGetCustomerCart = new DataNoAttributeResponse(book.getMerchantId(), "merchant");
    merchantRelationshipDatasGetCustomerCart.add(merchantRelationshipDataGetCustomerCart);
    merchantRelationshipGetCustomerCart = new BaseRelationshipDataResponse(merchantRelationshipDatasGetCustomerCart);
    cartRelationshipResponseGetCustomerCart = new CartRelationshipResponse(merchantRelationshipGetCustomerCart);
    dataCompleteResponseGetCustomerCart = new DataCompleteResponse<>(book.getBookSku(), "book", getCustomerCartResponseGetCustomerCart, cartRelationshipResponseGetCustomerCart);
    dataCompleteResponsesGetCustomerCart.add(dataCompleteResponseGetCustomerCart);
    merchantIncludedResponseGetCustomerCart = new MerchantIncludedResponse("fullname");
    merchantIncludedGetCustomerCart = new DataNoRelationResponse<>(book.getMerchantId(), "merchant", merchantIncludedResponseGetCustomerCart);
    dataNoRelationResponsesGetCustomerCart.add(merchantIncludedGetCustomerCart);
    successDataResponseGetCustomerCart = new SuccessDataWithIncludedResponse<>(200, "OK", dataCompleteResponsesGetCustomerCart, dataNoRelationResponsesGetCustomerCart);

    wishlist = new ArrayList<>();
    wishlist.add(book);
    dataCompleteResponsesGetCustomerWishlist = new ArrayList<>();
    dataNoRelationResponsesGetCustomerWishlist = new ArrayList<>();
    getCustomerCartResponseGetCustomerWishlist = new GetCustomerWishlistResponse(book.getTitle(), book.getAuthor(), book.getYear(), book.getPrice(), book.getDocument());
    merchantRelationshipDatasGetCustomerWishlist = new ArrayList<>();
    merchantRelationshipDataGetCustomerWishlist = new DataNoAttributeResponse(book.getMerchantId(), "merchant");
    merchantRelationshipDatasGetCustomerWishlist.add(merchantRelationshipDataGetCustomerWishlist);
    merchantRelationshipGetCustomerWishlist = new BaseRelationshipDataResponse(merchantRelationshipDatasGetCustomerWishlist);
    wishlistRelationshipResponse = new WishlistRelationshipResponse(merchantRelationshipGetCustomerWishlist);
    dataCompleteResponseGetCustomerWishlist = new DataCompleteResponse<>(book.getBookSku(), "book", getCustomerCartResponseGetCustomerWishlist, wishlistRelationshipResponse);
    dataCompleteResponsesGetCustomerWishlist.add(dataCompleteResponseGetCustomerWishlist);
    merchantIncludedResponseGetCustomerWishlist = new MerchantIncludedResponse("fullname");
    merchantIncludedGetCustomerWishlist = new DataNoRelationResponse<>(book.getMerchantId(), "merchant", merchantIncludedResponseGetCustomerWishlist);
    dataNoRelationResponsesGetCustomerWishlist.add(merchantIncludedGetCustomerWishlist);
    successDataResponseGetCustomerWishlist = new SuccessDataWithIncludedResponse<>(200, "OK", dataCompleteResponsesGetCustomerWishlist, dataNoRelationResponsesGetCustomerWishlist);

    library = new ArrayList<>();
    library.add(book);
    dataNoRelationResponsesGetCustomerLibrary = new ArrayList<>();
    getCustomerLibraryResponseGetCustomerLibrary = new GetCustomerLibraryResponse(book.getDocument());
    dataNoRelationResponseGetCustomerLibrary = new DataNoRelationResponse<>(book.getBookSku(), "book", getCustomerLibraryResponseGetCustomerLibrary);
    dataNoRelationResponsesGetCustomerLibrary.add(dataNoRelationResponseGetCustomerLibrary);
    successDataResponseGetCustomerLibrary = new SuccessDataResponse<>(200, "OK", dataNoRelationResponsesGetCustomerLibrary);

    dataNoAttributeResponseIsOnCart = new DataNoAttributeResponse(book.getBookSku(), "book");
    dataNoAttributeResponsesIsOnCart = new ArrayList<>();
    dataNoAttributeResponsesIsOnCart.add(dataNoAttributeResponseIsOnCart);
    successDataResponseIsOnCart = new SuccessDataResponse<>(200, "OK", dataNoAttributeResponsesIsOnCart);

    dataNoAttributeResponseIsOnLibrary = new DataNoAttributeResponse(book.getBookSku(), "book");
    dataNoAttributeResponsesIsOnLibrary = new ArrayList<>();
    dataNoAttributeResponsesIsOnLibrary.add(dataNoAttributeResponseIsOnLibrary);
    successDataResponseIsOnLibrary = new SuccessDataResponse<>(200, "OK", dataNoAttributeResponsesIsOnLibrary);

    dataNoAttributeResponseIsOnWishlist = new DataNoAttributeResponse(book.getBookSku(), "book");
    dataNoAttributeResponsesIsOnWishlist = new ArrayList<>();
    dataNoAttributeResponsesIsOnWishlist.add(dataNoAttributeResponseIsOnWishlist);
    successDataResponseIsOnWishlist = new SuccessDataResponse<>(200, "OK", dataNoAttributeResponsesIsOnWishlist);
  }

  @Test
  public void getAllCustomer() throws Exception {
    when(customerService.findAllCustomer()).thenReturn(successDataResponse);
    mockMvc.perform(get("/api/customers").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code", is(successDataResponse.getCode())));
  }

  @Test
  public void getCustomerById() throws Exception {
    when(customerService.findByCustomerId(1)).thenReturn(successDataResponseGetCustomerById);
    mockMvc.perform(get("/api/customers/" + 1).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code", is(successDataResponseGetCustomerById.getCode())));
  }

  @Test
  public void getCustomerCart() throws Exception {
    when(customerService.findCustomerCart(1)).thenReturn(successDataResponseGetCustomerCart);
    mockMvc.perform(get("/api/customers/" + 1 + "/cart").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code", is(successDataResponseGetCustomerCart.getCode())));
  }

  @Test
  public void getCustomerLibrary() throws Exception {
    when(customerService.findCustomerLibrary(1)).thenReturn(successDataResponseGetCustomerLibrary);
    mockMvc.perform(get("/api/customers/" + 1 + "/library").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code", is(successDataResponseGetCustomerLibrary.getCode())));
  }

  @Test
  public void getCustomerWishlist() throws Exception {
    when(customerService.findCustomerWishlist(1)).thenReturn(successDataResponseGetCustomerWishlist);
    mockMvc.perform(get("/api/customers/" + 1 + "/wishlist").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code", is(successDataResponseGetCustomerWishlist.getCode())));
  }

  @Test
  public void isOnCart() throws Exception {
    when(customerService.isOnCart(1, 1)).thenReturn(successDataResponseIsOnCart);
    mockMvc.perform(get("/api/customers/" + 1 + "/cart/" + 1 + "/check").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code", is(successDataResponseIsOnCart.getCode())));
  }

  @Test
  public void isOnLibrary() throws Exception {
    when(customerService.isOnLibrary(1, 1)).thenReturn(successDataResponseIsOnLibrary);
    mockMvc.perform(get("/api/customers/" + 1 + "/library/" + 1 + "/check").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code", is(successDataResponseIsOnLibrary.getCode())));
  }

  @Test
  public void isOnWishlist() throws Exception {
    when(customerService.isOnWishlist(1, 1)).thenReturn(successDataResponseIsOnWishlist);
    mockMvc.perform(get("/api/customers/" + 1 + "/wishlist/" + 1 + "/check").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code", is(successDataResponseIsOnWishlist.getCode())));
  }
}
