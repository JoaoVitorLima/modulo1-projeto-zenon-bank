package br.com.zenon.fraud.mains;

import br.com.zenon.fraud.Transaction;
import br.com.zenon.fraud.TransactionIngestor;

import java.io.IOException;
import java.util.List;

public class Tarefa03Main {

    static void main() throws IOException {

        TransactionIngestor transactionIngestor = new TransactionIngestor("zenon-fraud-detector/data/PS_20174392719_1491204439457_log.csv");

        List<Transaction> transacoes = transactionIngestor.getLista();

        for (Transaction t : transacoes) {
            IO.println(t);
        }

    }
}
