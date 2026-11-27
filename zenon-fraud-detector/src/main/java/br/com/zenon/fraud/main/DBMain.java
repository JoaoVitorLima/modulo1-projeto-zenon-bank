package br.com.zenon.fraud.main;

import br.com.zenon.fraud.Transaction;
import br.com.zenon.fraud.TransactionIngestor;
import br.com.zenon.fraud.config.ConnectionFactory;
import br.com.zenon.fraud.repository.TransactionSQLRepository;

import java.io.IOException;
import java.util.List;

public class DBMain {

    private static final String ARQUIVO = "data/PS_20174392719_1491204439457_log.csv";

    void main() throws IOException {
        ConnectionFactory.getConnection();
        IO.println("Conexão com o DB criada!");

        TransactionIngestor ingestor = new TransactionIngestor(ARQUIVO);
        TransactionSQLRepository repository = new TransactionSQLRepository();

        List<Transaction> transacoes = ingestor.getLista();

        for (Transaction t : transacoes) {
            repository.save(t);
        }

        repository.findByOriginName("C1231006815");
    }
}
