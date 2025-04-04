package com.account.account.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.account.account.config.RabbitConfig;
import com.account.account.dto.ClientMessage;
import com.account.account.model.Account;
import com.account.account.repository.AccountRepository;

@Service
public class ClientListener {

    @Autowired
    private AccountRepository accountRepository;

    @RabbitListener(queues = RabbitConfig.QUEUE)
    public void saveMesagge(ClientMessage message) {
        System.out.println("Message Received");

        Account account = new Account();
        account.setAccountNumber("000-" + message.getIdClient());
        account.setAccountType("Savings Account");
        account.setInitialBalance(0.0);
        account.setState(true);
        accountRepository.save(account);
    }
    
}
