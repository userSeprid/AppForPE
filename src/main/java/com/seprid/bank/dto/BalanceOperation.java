package com.seprid.bank.dto;

import com.seprid.bank.entity.Account;
import com.seprid.bank.entity.Client;

public class BalanceOperation {
    private Client client;
    private Account account;
    private Integer amount;

    public BalanceOperation(Client client, Account account, Integer amount) {
        this.client = client;
        this.account = account;
        this.amount = amount;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "BalanceOperation{" +
                "client=" + client +
                ", account=" + account +
                ", amount=" + amount +
                '}';
    }
}
