package br.com.zenon.fraud.mains;

import br.com.zenon.fraud.Customer;
import br.com.zenon.fraud.Transaction;

import java.math.BigDecimal;

import static br.com.zenon.fraud.Transaction.Type.*;

public class Tarefa02Main {

    static void main() {

        var transacao1 = new Transaction(
                1L,
                Transaction.Type.PAYMENT,
                new BigDecimal("9839.64"),
                new Customer("C1231006815", new BigDecimal("170136.0"), new BigDecimal("160296.36")),
                new Customer("M1979787155", new BigDecimal("0.0"), new BigDecimal("0.0")),
                false,
                false
                );

        var transacao2 = new Transaction(
                743L,
                CASH_OUT,
                new BigDecimal("850002.52"),
                new Customer("C1280323807", new BigDecimal("850002.52"), new BigDecimal("0.0")),
                new Customer("C873221189", new BigDecimal("6510099.11"), new BigDecimal("7360101.63")),
                true,
                false
        );

        System.out.println("Transação 1: \r\n" + transacao1);
        System.out.println("-----------------------------");
        System.out.println("Transação 2: \r\n" + transacao2);
    }
}
