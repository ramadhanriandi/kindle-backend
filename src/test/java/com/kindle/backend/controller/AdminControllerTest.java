package com.kindle.backend.controller;

import com.kindle.backend.model.entity.Admin;
import com.kindle.backend.response.attributeResponse.GetAdminByAdminIdResponse;
import com.kindle.backend.response.dataResponse.DataNoRelationResponse;
import com.kindle.backend.response.statusResponse.SuccessDataResponse;
import com.kindle.backend.service.AdminService;
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
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AdminController.class)
public class AdminControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private AdminService adminService;

  private SuccessDataResponse<DataNoRelationResponse> successDataResponse;
  private List<DataNoRelationResponse> dataNoRelationResponses;
  private DataNoRelationResponse<GetAdminByAdminIdResponse> dataNoRelationResponse;
  private GetAdminByAdminIdResponse getAdminByAdminIdResponse;
  private Admin admin;

  @Before
  public void setUp() {
    admin = new Admin();
    admin.setAdminId(1);
    admin.setEmail("test@example.com");
    admin.setUsername("test");
    admin.setPassword("123456");

    getAdminByAdminIdResponse = new GetAdminByAdminIdResponse(admin.getEmail(), admin.getUsername(), admin.getPassword());
    dataNoRelationResponse = new DataNoRelationResponse<>(admin.getAdminId(), "admin", getAdminByAdminIdResponse);
    dataNoRelationResponses = new ArrayList<>();
    dataNoRelationResponses.add(dataNoRelationResponse);
    successDataResponse = new SuccessDataResponse<>(200, "OK", dataNoRelationResponses);
  }

  @Test
  public void getAdminById() throws Exception {
    given(adminService.findByAdminId(1)).willReturn(successDataResponse);
    mockMvc.perform(get("/api/admins/" + 1).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code", is(successDataResponse.getCode())));
  }
}
