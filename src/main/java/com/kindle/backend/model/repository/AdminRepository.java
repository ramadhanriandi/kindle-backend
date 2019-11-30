package com.kindle.backend.model.repository;

import com.kindle.backend.model.entity.Admin;
import org.checkerframework.checker.units.qual.A;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
  Admin findFirstByAdminId(Integer adminId);

  List<Admin> findAll();
}
