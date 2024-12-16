package com.website.online.sale.service;

import com.website.online.sale.dtos.response.PaymentSupportResponse;
import com.website.online.sale.model.PaymentSupport;
import com.website.online.sale.repository.PaymentSupportRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentSupportServiceImpl implements PaymentSupportService{
    private final PaymentSupportRepository paymentSupportRepository;

    public PaymentSupportServiceImpl(PaymentSupportRepository paymentSupportRepository) {
        this.paymentSupportRepository = paymentSupportRepository;
    }

    @Override
    public List<PaymentSupportResponse> getAllPaymentSupport() {
        List<PaymentSupportResponse> paymentSupportResponses = new ArrayList<>();

        List<PaymentSupport> paymentSupports = paymentSupportRepository.findAll();
        paymentSupports.forEach(payment -> {
            PaymentSupportResponse paymentSupportResponse = new PaymentSupportResponse();
            paymentSupportResponse.setId(payment.getId());
            paymentSupportResponse.setName(payment.getName());
            paymentSupportResponse.setIcon(payment.getIcon());

            paymentSupportResponses.add(paymentSupportResponse);
        });
        return paymentSupportResponses;
    }
}
