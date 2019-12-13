package com.kindle.backend.service;

import com.kindle.backend.model.entity.Admin;
import com.kindle.backend.model.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
  @Autowired
  private AdminRepository adminRepository;

  public Admin findByAdminId(Integer adminId){
    return adminRepository.findFirstByAdminId(adminId);
  }

  public List<Admin> findAllAdmin(){
    return adminRepository.findAll();
  }

  public Admin save(Admin admin) {
    return adminRepository.save(admin);
  }

  public void updateAdmin(Integer id, Admin admin) {
    admin.setAdminId(id);
    adminRepository.save(admin);
  }

  public long deleteByAdminId(Integer adminId) {
    return adminRepository.deleteByAdminId(adminId);
  }
}
