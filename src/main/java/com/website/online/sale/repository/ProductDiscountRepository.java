package com.website.online.sale.repository;

import com.website.online.sale.model.ProductDiscount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDiscountRepository extends JpaRepository<ProductDiscount, Long> {
  ProductDiscount findByIdSp (Long idSp);
  ProductDiscount findFirstByIdSp (Long idSp);
}