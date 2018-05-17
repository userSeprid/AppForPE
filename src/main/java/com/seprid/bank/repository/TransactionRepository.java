package com.seprid.bank.repository;

import com.seprid.bank.entity.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction, Integer> {
    List<Transaction> findAll();
}
