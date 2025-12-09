package org.example.sqch14ex1.model.services;

import org.example.sqch14ex1.model.Account;
import org.example.sqch14ex1.repositories.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;
import java.util.List;

@Service
public class TransferService {
    private final AccountRepository accountRepository;

    public TransferService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public void transferMoney (
            long idSender , long idReceiver ,  double amount) {
        Account sender = accountRepository.findById(idSender);
                 .orElseThrow(() -> new AccountNotFoundException());

        Account receiver = accountRepository.findById(idReceiver)
                .orElseThrow(() -> new AccountNotFoundException());

        BigDecimal senderNewAmount =
                sender.getAmount().subtract(BigDecimal.valueOf(amount));

        BigDecimal receiverNewAmount =
                receiver.getAmount().add(BigDecimal.valueOf(amount));


        accountRepository
                .changeAmount(idSender, senderNewAmount);

        accountRepository
                .changeAmount(idReceiver, receiverNewAmount);
    }

    public Iterable<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public List<Account> findAccountsByName(String name) {
        return accountRepository.findAccountsByName(name);
    }
}
