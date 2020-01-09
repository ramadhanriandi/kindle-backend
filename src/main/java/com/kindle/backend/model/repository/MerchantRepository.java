package com.kindle.backend.model.repository;

import com.kindle.backend.model.entity.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, Integer> {
  Merchant findFirstByMerchantId(Integer merchantId);

  Merchant findFirstByEmailAndPassword(String email, String password);

  List<Merchant> findAll();

  @Transactional
  long deleteByMerchantId(Integer merchantId);
}
