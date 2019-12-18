package com.kindle.backend.service;

import com.kindle.backend.model.entity.Admin;
import com.kindle.backend.model.repository.AdminRepository;
import com.kindle.backend.response.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
  @Autowired
  private AdminRepository adminRepository;

  public void updateAdmin(Integer id, Admin admin) {
    admin.setAdminId(id);
    adminRepository.save(admin);
  }

  public PostResponse login(Admin admin) {
    String email = admin.getEmail();
    String password = admin.getPassword();
    PostResponse loginResponse = new PostResponse();

    Admin adminResponse = adminRepository.findFirstByEmailAndPassword(email, password);

    if (adminResponse == null) {
      loginResponse.setCode(401);
      loginResponse.setMessage("Login failed: wrong email or password");
    } else {
      loginResponse.setUserId(adminResponse.getAdminId());
      loginResponse.setCode(200);
      loginResponse.setMessage("Login success");
    }

    return loginResponse;
  }
}
