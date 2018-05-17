package com.seprid.bank.repository;

import com.seprid.bank.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    List<Account> findAllByOwnerId(Integer id);
}
