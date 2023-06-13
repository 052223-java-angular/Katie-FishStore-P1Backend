package com.revature.katieskritters.services;

import org.springframework.stereotype.Service;

import com.revature.katieskritters.dtos.requests.NewPaymentRequest;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PaymentService {

    public boolean processPayment(NewPaymentRequest paymentRequest) {
        if (!isValidCardNumber(paymentRequest.getCardNumber())) {
            return false;
        } else {
            return true;
        }
    }

    private boolean isValidCardNumber(String cardNumber) {
        if (cardNumber == null || cardNumber.length() != 16) {
            return false;
        } else {
            return true;
        }
    }
}