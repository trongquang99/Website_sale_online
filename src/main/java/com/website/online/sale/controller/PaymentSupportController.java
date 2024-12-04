package com.website.online.sale.controller;

import com.website.online.sale.dtos.response.PaymentSupportResponse;
import com.website.online.sale.service.PaymentSupportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PaymentSupportController {
    private final PaymentSupportService paymentSupportService;

    public PaymentSupportController(PaymentSupportService paymentSupportService) {
        this.paymentSupportService = paymentSupportService;
    }

    @GetMapping("/api/v1/payment_upport")
    public List<PaymentSupportResponse> getPaymentSupport() {
        List<PaymentSupportResponse> responses =paymentSupportService.getAllPaymentSupport();
        return responses;
    }
}
