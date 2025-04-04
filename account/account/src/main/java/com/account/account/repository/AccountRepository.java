package com.account.account.repository;

import com.account.account.model.Account;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
   
    Optional<Account> findByAccountNumber(String accountNumber);
}
