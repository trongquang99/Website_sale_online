package com.website.online.sale.repository;

import com.website.online.sale.model.PaymentSupport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentSupportRepository extends JpaRepository<PaymentSupport, Long> {
}