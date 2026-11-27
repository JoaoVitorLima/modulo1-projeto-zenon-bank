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

        long iniList = System.nanoTime();

        TransactionSQLRepository repository = new TransactionSQLRepository();
        List<Transaction> transacoes = ingestor.getLista();

        IO.println("Iniciado a inserção no BD...");
        for (Transaction t : transacoes) {
            repository.save(t);
        }
        IO.println("Finalizado a inserção no BD!");
        long fimList = System.nanoTime();
        IO.println("  Tempo de inserção - SQL (1x): " + (fimList - iniList) + " ns");



        repository.findByOriginName("C1231006815");
        repository.findByOriginName("C12345");
    }
}
