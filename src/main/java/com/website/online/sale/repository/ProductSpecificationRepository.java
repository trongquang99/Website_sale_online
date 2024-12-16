package com.website.online.sale.repository;

import com.website.online.sale.model.ProductSpecification;
import com.website.online.sale.model.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductSpecificationRepository extends JpaRepository<ProductSpecification, Long> {
    ProductSpecification findByIdProduct(Long idProduct);

    List<ProductSpecification> findByConfigContaining(String config);

    ProductSpecification findFirstByIdProduct(Long idProduct);
}