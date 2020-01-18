package com.kindle.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kindle.backend.model.entity.Category;
import com.kindle.backend.response.attributeResponse.GetCategoryByCategoryIdResponse;
import com.kindle.backend.response.dataResponse.DataNoRelationResponse;
import com.kindle.backend.response.statusResponse.SuccessDataResponse;
import com.kindle.backend.service.CategoryService;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.core.Is.is;


@RunWith(SpringRunner.class)
@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private CategoryService categoryService;

  @MockBean
  private ObjectMapper objectMapper;

  private List<DataNoRelationResponse> dataNoRelationResponses;
  private SuccessDataResponse<DataNoRelationResponse> successDataResponse;
  private List<Category> categories;

  private List<DataNoRelationResponse> dataNoRelationResponsesGetCategoryById;
  private GetCategoryByCategoryIdResponse getCategoryByCategoryIdResponseGetCategoryById;
  private DataNoRelationResponse<GetCategoryByCategoryIdResponse> dataNoRelationResponseGetCategoryById;
  private SuccessDataResponse<DataNoRelationResponse> successDataResponseGetCategoryById;
  private Category categoryGetCategoryById;

  @Before
  public void setUp() {
    objectMapper = new ObjectMapper();

    categories = new ArrayList<>();
    categories.add(new Category(1, "Fiction"));
    categories.add(new Category(2, "Drama"));
    dataNoRelationResponses = new ArrayList<>();
    for (Category category : categories) {
      GetCategoryByCategoryIdResponse getCategoryByCategoryIdResponse = new GetCategoryByCategoryIdResponse(category.getName());
      DataNoRelationResponse<GetCategoryByCategoryIdResponse> dataNoRelationResponse = new DataNoRelationResponse<>(category.getCategoryId(), "category", getCategoryByCategoryIdResponse);
      dataNoRelationResponses.add(dataNoRelationResponse);
    }
    successDataResponse = new SuccessDataResponse<>(200, "OK", dataNoRelationResponses);

    categoryGetCategoryById = new Category(1, "Fiction");
    dataNoRelationResponsesGetCategoryById = new ArrayList<>();
    getCategoryByCategoryIdResponseGetCategoryById = new GetCategoryByCategoryIdResponse(categoryGetCategoryById.getName());
    dataNoRelationResponseGetCategoryById = new DataNoRelationResponse<>(categoryGetCategoryById.getCategoryId(), "category", getCategoryByCategoryIdResponseGetCategoryById);
    dataNoRelationResponsesGetCategoryById.add(dataNoRelationResponseGetCategoryById);
    successDataResponseGetCategoryById = new SuccessDataResponse<>(200, "OK", dataNoRelationResponsesGetCategoryById);
  }

  @Test
  public void getAllCategory() throws Exception {
    when(categoryService.findAllCategory()).thenReturn(successDataResponse);
    mockMvc.perform(get("/api/categories").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code", is(successDataResponse.getCode())));
  }

  @Test
  public void getCategoryByCategoryId() throws Exception {
    when(categoryService.findByCategoryId(1)).thenReturn(successDataResponseGetCategoryById);
    mockMvc.perform(get("/api/categories/" + 1).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code", is(successDataResponseGetCategoryById.getCode())));
  }
}
