package com.website.online.sale.service;

import com.website.online.sale.dtos.response.PaymentSupportResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PaymentSupportService {
    List<PaymentSupportResponse> getAllPaymentSupport();
}
