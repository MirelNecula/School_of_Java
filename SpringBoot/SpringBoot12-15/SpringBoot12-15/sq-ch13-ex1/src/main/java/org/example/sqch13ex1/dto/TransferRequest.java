package org.example.sqch13ex1.dto;

import java.math.BigDecimal;

public class TransferRequest {

    private long sendderAccountId;
    private long receiverAccountId;
    private BigDecimal amount;

    public long getReceiverAccountId() {
        return receiverAccountId;
    }

    public void setReceiverAccountId(long receiverAccountId) {
        this.receiverAccountId = receiverAccountId;
    }

    public long getSendderAccountId() {
        return sendderAccountId;
    }

    public void setSendderAccountId(long sendderAccountId) {
        this.sendderAccountId = sendderAccountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
