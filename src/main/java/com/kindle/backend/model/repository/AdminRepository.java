package com.kindle.backend.model.repository;

import com.kindle.backend.model.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
  Admin findFirstByAdminId(Integer adminId);

  Admin findFirstByEmailAndPassword(String email, String password);
}
