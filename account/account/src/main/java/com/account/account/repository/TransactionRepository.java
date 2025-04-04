package com.account.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.account.account.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
