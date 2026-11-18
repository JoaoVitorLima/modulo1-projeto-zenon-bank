package br.com.zenon.fraud;

import java.math.BigDecimal;

public record Transaction(int step, TransactionType type, BigDecimal amount, TransactionCustomer origin,
                          TransactionCustomer recipient, Boolean isFraud, Boolean isFlaggedFraud ) {

    public Transaction {
        if (step < 1)
            throw new IllegalArgumentException("step should be positive: " + step);
        if (type == null)
            throw new IllegalArgumentException("type should not be null");
        if (amount == null)
            throw new IllegalArgumentException("amount should not be null");
        if (amount.signum() < 0)
            throw new IllegalArgumentException("amount should be positive: " + amount);
        if (origin == null || recipient == null)
            throw new IllegalArgumentException("origin/recipient should not be null");
    }
}
