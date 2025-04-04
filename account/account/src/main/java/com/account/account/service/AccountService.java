package com.account.account.service;

import com.account.account.exception.DataNotFound;
import com.account.account.model.*;
import com.account.account.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public Account getAccountById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new DataNotFound("Account not found"));
    }

    public Account updateAccount(Long id, Account account) {
        Account accountUpdate = getAccountById(id);
        account.setAccountNumber(accountUpdate.getAccountNumber());
        account.setAccountType(accountUpdate.getAccountType());
        account.setState(accountUpdate.getState());
        account.setIdClient(accountUpdate.getIdClient());
        return accountRepository.save(account);
    }

    public Transaction registerTransaction(Long idAccount, Transaction transaction) {
        Account account = accountRepository.findById(idAccount)
                .orElseThrow(() -> new DataNotFound("Account not found);"));

        if (account.getState() == false) {
            throw new DataNotFound("Account is inactive");
        }

        // Cash out
        if ("OUT".equals(transaction.getTransactionType()) && account.getInitialBalance().compareTo(transaction.getValue()) < 0) {
            throw new DataNotFound("Balance not available");
        }

        // Update cash
        double newCash = account.getInitialBalance() + transaction.getValue();
        account.setInitialBalance(newCash);
        accountRepository.save(account);

        // CRUD transaction
        transaction.setDate(new Date());
        transaction.setCash(newCash);
        transaction.setAccount(account);
        
        return transactionRepository.save(transaction);
    }

}
