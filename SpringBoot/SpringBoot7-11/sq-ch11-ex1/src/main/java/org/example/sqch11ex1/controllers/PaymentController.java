package org.example.sqch11ex1.controllers;

import org.example.sqch11ex1.model.Payment;
import org.example.sqch11ex1.proxy.PaymentsProxy;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {
    private final PaymentsProxy paymentsProxy;

    public PaymentController(PaymentsProxy paymentsProxy) {
        this.paymentsProxy = paymentsProxy;
    }

    @PostMapping("/payment")
    public Payment creatPayment(
            @RequestBody Payment payment
    ) {
        String requestId = java.util.UUID.randomUUID().toString();
        return paymentsProxy.createPayment(requestId, payment);
    }
}
