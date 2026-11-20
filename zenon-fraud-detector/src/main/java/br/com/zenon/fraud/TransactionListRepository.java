package br.com.zenon.fraud;

import java.util.List;
import java.util.Optional;

public class TransactionListRepository implements TransactionRepository {

    private final List<Transaction> transactions;

    public TransactionListRepository(List<Transaction> transactions) {
        this.transactions = List.copyOf(transactions);
    }

    @Override
    public Optional<Transaction> findByOriginName(String name) {

        return transactions.stream()
                .filter(t -> t.origin().name().equals(name))
                .findFirst();
    }

}
