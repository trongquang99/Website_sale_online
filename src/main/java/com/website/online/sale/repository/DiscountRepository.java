package com.website.online.sale.repository;

import com.website.online.sale.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {

    Discount findFirstById(Long idGg);
}