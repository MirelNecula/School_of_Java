package org.example.sqch11ex3.controllers;

import org.example.sqch11ex3.model.Payment;
import org.example.sqch11ex3.proxy.PaymentsProxy;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class PaymentsController {

    private final PaymentsProxy paymentsProxy;

    public PaymentsController(PaymentsProxy paymentsProxy) {
        this.paymentsProxy = paymentsProxy;
    }

    @PostMapping("/payment")
    public Mono<Payment> createPayment(
            @RequestBody Payment payment
    ) {
        String requestId = java.util.UUID.randomUUID().toString();
        return paymentsProxy.createPayment(requestId, payment);
    }
}
