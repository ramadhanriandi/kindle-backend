package com.kindle.backend.controller;

import com.kindle.backend.model.constant.ApiPath;
import com.kindle.backend.model.entity.Admin;
import com.kindle.backend.response.BaseResponse;
import com.kindle.backend.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class AdminController {
  @Autowired
  private AdminService adminService;

  @RequestMapping(value = ApiPath.ADMIN_BY_ADMIN_ID, method = RequestMethod.GET)
  public BaseResponse getAdminById(@PathVariable Integer adminId) {
    return this.adminService.findByAdminId(adminId);
  }

  @RequestMapping(value = ApiPath.ADMIN_BY_ADMIN_ID, method = RequestMethod.PUT)
  public BaseResponse updateAdmin(@PathVariable Integer adminId, @RequestBody Admin admin) {
    return this.adminService.updateAdmin(adminId, admin);
  }

  @RequestMapping(value = ApiPath.ADMIN_LOGIN, method = RequestMethod.POST)
  public BaseResponse login(@RequestBody Admin admin) {
    return this.adminService.login(admin);
  }
}
