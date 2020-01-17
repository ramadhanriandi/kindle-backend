package com.kindle.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kindle.backend.model.entity.Admin;
import com.kindle.backend.response.attributeResponse.GetAdminByAdminIdResponse;
import com.kindle.backend.response.dataResponse.DataNoAttributeResponse;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AdminController.class)
public class AdminControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private AdminService adminService;

  @MockBean
  private ObjectMapper objectMapper;

  private SuccessDataResponse<DataNoRelationResponse> successDataResponseGetAdminById;
  private List<DataNoRelationResponse> dataNoRelationResponsesGetAdminById;
  private DataNoRelationResponse<GetAdminByAdminIdResponse> dataNoRelationResponseGetAdminById;
  private GetAdminByAdminIdResponse getAdminByAdminIdResponseGetAdminById;
  private Admin adminGetAdminById;

  private DataNoAttributeResponse dataNoAttributeResponseUpdateAdmin;
  private List<DataNoAttributeResponse> dataNoAttributeResponsesUpdateAdmin;
  private SuccessDataResponse<DataNoAttributeResponse> successDataResponseUpdateAdmin;
  private Admin updatedAdmin;

  private DataNoAttributeResponse dataNoAttributeResponseLoginAdmin;
  private List<DataNoAttributeResponse> dataNoAttributeResponsesLoginAdmin;
  private SuccessDataResponse<DataNoAttributeResponse> successDataResponseLoginAdmin;
  private Admin loginAdmin;

  @Before
  public void setUp() {
    objectMapper = new ObjectMapper();

    adminGetAdminById = new Admin();
    adminGetAdminById.setAdminId(1);
    adminGetAdminById.setEmail("test@example.com");
    adminGetAdminById.setUsername("test");
    adminGetAdminById.setPassword("123456");

    getAdminByAdminIdResponseGetAdminById = new GetAdminByAdminIdResponse(adminGetAdminById.getEmail(), adminGetAdminById.getUsername(), adminGetAdminById.getPassword());
    dataNoRelationResponseGetAdminById = new DataNoRelationResponse<>(adminGetAdminById.getAdminId(), "admin", getAdminByAdminIdResponseGetAdminById);
    dataNoRelationResponsesGetAdminById = new ArrayList<>();
    dataNoRelationResponsesGetAdminById.add(dataNoRelationResponseGetAdminById);
    successDataResponseGetAdminById = new SuccessDataResponse<>(200, "OK", dataNoRelationResponsesGetAdminById);

    updatedAdmin = new Admin();
    updatedAdmin.setEmail("test2@example.com");
    updatedAdmin.setUsername("test2");
    updatedAdmin.setPassword("123456");

    dataNoAttributeResponseUpdateAdmin = new DataNoAttributeResponse(updatedAdmin.getAdminId(), "admin");
    dataNoAttributeResponsesUpdateAdmin = new ArrayList<>();
    dataNoAttributeResponsesUpdateAdmin.add(dataNoAttributeResponseUpdateAdmin);
    successDataResponseUpdateAdmin = new SuccessDataResponse<>(200, "OK", dataNoAttributeResponsesUpdateAdmin);

    loginAdmin = new Admin();
    loginAdmin.setEmail("test2@example.com");
    loginAdmin.setPassword("123456");

    dataNoAttributeResponseLoginAdmin = new DataNoAttributeResponse(loginAdmin.getAdminId(), "admin");
    dataNoAttributeResponsesLoginAdmin = new ArrayList<>();
    dataNoAttributeResponsesLoginAdmin.add(dataNoAttributeResponseLoginAdmin);
    successDataResponseLoginAdmin = new SuccessDataResponse<>(200, "OK", dataNoAttributeResponsesLoginAdmin);
  }

  @Test
  public void getAdminById() throws Exception {
    when(adminService.findByAdminId(1)).thenReturn(successDataResponseGetAdminById);
    mockMvc.perform(get("/api/admins/" + 1).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code", is(successDataResponseGetAdminById.getCode())));
  }

  @Test
  public void updateAdmin() throws Exception {
    when(adminService.updateAdmin(1, updatedAdmin)).thenReturn(successDataResponseUpdateAdmin);
    mockMvc.perform(put("/api/admins/" + 1)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(updatedAdmin))
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code", is(successDataResponseUpdateAdmin.getCode())));
  }

  @Test
  public void loginAdmin() throws Exception {
    when(adminService.login(loginAdmin)).thenReturn(successDataResponseLoginAdmin);
    mockMvc.perform(post("/api/admins/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(updatedAdmin))
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code", is(successDataResponseUpdateAdmin.getCode())));
  }
}
