package com.account.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.account.account.model.*;
import com.account.account.service.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        Account newAccount = accountService.createAccount(account);
        return new ResponseEntity<>(newAccount, HttpStatus.CREATED);
    }

    @PostMapping("/{idAccount}/transactions")
    public ResponseEntity<Transaction> registerTransaction(@PathVariable Long idAccount, @RequestBody Transaction transaction) {
        Transaction newTransaction = accountService.registerTransaction(idAccount, transaction);
        return new ResponseEntity<>(newTransaction, HttpStatus.CREATED);
    }
    
}
