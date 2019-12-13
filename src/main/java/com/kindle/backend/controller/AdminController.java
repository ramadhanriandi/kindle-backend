package com.kindle.backend.controller;

import com.kindle.backend.model.entity.Admin;
import com.kindle.backend.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {
  @Autowired
  private AdminService adminService;

  @RequestMapping(value = "/api/admins", method = RequestMethod.GET)
  public List<Admin> getAllAdmin() {
    return this.adminService.findAllAdmin();
  }

  @RequestMapping(value = "/api/admins/{id}", method = RequestMethod.GET)
  public Admin getAdminById(@PathVariable Integer id) {
    return this.adminService.findByAdminId(id);
  }

  @RequestMapping(value = "/api/admins", method = RequestMethod.POST)
  public Admin save(@RequestBody Admin admin) {
    return this.adminService.save(admin);
  }

  @RequestMapping(value = "/api/admins/{id}", method = RequestMethod.PUT)
  public void updateAdmin(@PathVariable Integer id, @RequestBody Admin admin) {
    this.adminService.updateAdmin(id, admin);
  }

  @RequestMapping(value = "/api/admins/{id}", method = RequestMethod.DELETE)
  public boolean deleteByAdminId(@PathVariable Integer id) {
    return this.adminService.deleteByAdminId(id) > 0;
  }
}
