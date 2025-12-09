package org.example.sqch13ex1.controller;

import org.example.sqch13ex1.dto.TransferRequest;
import org.example.sqch13ex1.model.Account;
import org.example.sqch13ex1.services.TransferService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AcountController {

    private final TransferService transferService;

    public AcountController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("transfer")
    public void transferMoney( @RequestBody TransferRequest request) {
        transferService.transferMoney(
                request.getSendderAccountId(),
                request.getReceiverAccountId(),
                request.getAmount()
        );
    }

    @GetMapping("/accounts")
    public List<Account> getAllAccounts() {
        return transferService.getAllAccounts();
    }
}
