package com.website.online.sale.repository;

import com.website.online.sale.model.TopSelling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopSellingRepository extends JpaRepository<TopSelling, Long> {
  List<TopSelling> findByIdCategory(Long idCategory);

}