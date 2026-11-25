package br.com.zenon.fraud.repository;

import br.com.zenon.fraud.Transaction;

import java.util.Optional;

public interface TransactionRepository {

    public Optional<Transaction> findByOriginName(String name);
    public void save(Transaction transaction);

}
