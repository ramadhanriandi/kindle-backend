package com.kindle.backend.controller;

import com.kindle.backend.model.constant.ApiPath;
import com.kindle.backend.model.entity.Admin;
import com.kindle.backend.response.PostResponse;
import com.kindle.backend.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class AdminController {
  @Autowired
  private AdminService adminService;

  @RequestMapping(value = ApiPath.ADMIN_BY_ADMIN_ID, method = RequestMethod.PUT)
  public void updateAdmin(@PathVariable Integer id, @RequestBody Admin admin) {
    this.adminService.updateAdmin(id, admin);
  }
  @RequestMapping(value = ApiPath.ADMIN_LOGIN, method = RequestMethod.POST)
  public PostResponse login(@RequestBody Admin admin) {
    return this.adminService.login(admin);
  }
}
