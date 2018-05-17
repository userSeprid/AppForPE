package com.seprid.bank.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer transactionId;
    private Integer transferredAmount;
    private Integer senderAccountId;
    private Integer receiverAccountId;
    @GeneratedValue()
    private Date transactionDate;

    public Transaction() {
    }

    public Transaction(Integer transferredAmount, Integer senderAccountId, Integer receiverAccountId, Date transactionDate) {
        this.transferredAmount = transferredAmount;
        this.senderAccountId = senderAccountId;
        this.receiverAccountId = receiverAccountId;
        this.transactionDate = transactionDate;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getTransferredAmount() {
        return transferredAmount;
    }

    public void setTransferredAmount(Integer transferredAmount) {
        this.transferredAmount = transferredAmount;
    }

    public Integer getSenderAccountId() {
        return senderAccountId;
    }

    public void setSenderAccountId(Integer senderAccountId) {
        this.senderAccountId = senderAccountId;
    }

    public Integer getReceiverAccountId() {
        return receiverAccountId;
    }

    public void setReceiverAccountId(Integer receiverAccountId) {
        this.receiverAccountId = receiverAccountId;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }
}
