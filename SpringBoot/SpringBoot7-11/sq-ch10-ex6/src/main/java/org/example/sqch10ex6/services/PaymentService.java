package org.example.sqch10ex6.services;

import org.example.sqch10ex6.exceptions.NotEnoughMoneyException;
import org.example.sqch10ex6.model.PaymentDetails;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    public PaymentDetails processPayment() {
        throw new NotEnoughMoneyException();
    }
}
