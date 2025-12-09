package org.example.sqch11ex1.proxy;

import org.example.sqch11ex1.model.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "payments" , url = "${payments.service.url}")
public interface PaymentsProxy {

    @PostMapping("/payment")
    Payment createPayment(
        @RequestHeader("requestId") String requestId,
        @RequestBody Payment payment);

}
