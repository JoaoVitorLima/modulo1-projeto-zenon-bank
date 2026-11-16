package br.com.zenon.fraud;

import java.math.BigDecimal;

public record Customer(String name, BigDecimal oldBalance, BigDecimal newBalance) {

    public Customer {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("nome não pode ser vazio");
        if (oldBalance == null || newBalance == null)
            throw new IllegalArgumentException("saldos não podem ser nulos");
        if (oldBalance.signum() < 0 || newBalance.signum() < 0)
            throw new IllegalArgumentException("saldos não podem ser negativos");
    }
}
