package br.com.zenon.fraud;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TransactionMapRepository implements TransactionRepository {

    private final Map<String, Transaction> transactionsMap = new HashMap<>();

    public TransactionMapRepository(List<Transaction> transactions) {
        for (Transaction t : transactions) {
            transactionsMap.putIfAbsent(t.origin().name(), t);
        }
    }

    @Override
    public Optional<Transaction> findByOriginName(String name) {

        return Optional.ofNullable(transactionsMap.get(name));
    }

}
