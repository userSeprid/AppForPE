package com.seprid.bank.dto;

public class TransactionOperation {
    private Integer senderID;
    private Integer receiverID;
    private Integer amount;

    public TransactionOperation(Integer senderID, Integer receiverID, Integer amount) {
        this.senderID = senderID;
        this.receiverID = receiverID;
        this.amount = amount;
    }

    public Integer getSenderID() {
        return senderID;
    }

    public void setSenderID(Integer senderID) {
        this.senderID = senderID;
    }

    public Integer getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(Integer receiverID) {
        this.receiverID = receiverID;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
