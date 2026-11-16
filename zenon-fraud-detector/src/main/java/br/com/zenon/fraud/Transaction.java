package br.com.zenon.fraud;

import java.math.BigDecimal;

public record Transaction(Long step, Type type, BigDecimal amount, Customer origin,
                          Customer destination, Boolean isFraud, Boolean isFlaggedFraud ) {

    public enum Type {
        CASH_IN, CASH_OUT, DEBIT, PAYMENT, TRANSFER;
    }

    public Transaction {
        if (step < 0)
            throw new IllegalArgumentException("step não pode ser negativo");
        if (type == null)
            throw new IllegalArgumentException("type não pode ser nulo");
        if (amount == null || amount.signum() < 0)
            throw new IllegalArgumentException("amount inválido");
        if (origin == null || destination == null)
            throw new IllegalArgumentException("clientes de origem/destino obrigatórios");
    }
}
