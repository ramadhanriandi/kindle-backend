package com.kindle.backend.controller;

import com.kindle.backend.model.entity.Admin;
import com.kindle.backend.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class AdminController {
  private AdminService service;

  @Autowired
  public AdminController(AdminService service) {
    this.service = service;
  }

  @GetMapping("/api/admins")
  public List<Admin> getAllAdmin() {
    return this.service.findAllAdmin();
  }

  @GetMapping("/api/admins/{id}")
  public Admin getAdminById(@PathVariable Integer id) {
    return this.service.findByAdminId(id);
  }

  @PostMapping(value="/api/admins")
  public Admin save(@RequestBody Admin admin) {
    return this.service.save(admin);
  }
}
