package org.example.sqch10ex5.services;

import org.example.sqch10ex5.exceptions.NotEnoughMoneyException;
import org.example.sqch10ex5.model.PaymentDetails;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    public PaymentDetails processPayment() {
        throw new NotEnoughMoneyException();
    }
}
