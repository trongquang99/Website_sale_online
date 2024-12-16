package com.website.online.sale.repository;

import com.website.online.sale.model.ProductCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCartRepository extends JpaRepository<ProductCart, Long> {
    List<ProductCart> findAllByIdGh(Long idGh);
}