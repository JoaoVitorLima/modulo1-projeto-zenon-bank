package br.com.zenon.fraud.main;

import br.com.zenon.fraud.TransactionIngestor;
import br.com.zenon.fraud.repository.TransactionListRepository;
import br.com.zenon.fraud.repository.TransactionMapRepository;

import java.io.IOException;

public class Tarefa06Main {

    static void main() throws IOException {

        TransactionIngestor transactionIngestor = new TransactionIngestor("zenon-fraud-detector/data/PS_20174392719_1491204439457_log.csv");

        TransactionListRepository transactionListRepository = new TransactionListRepository(transactionIngestor.getLista());
        TransactionMapRepository transactionMapRepository = new TransactionMapRepository(transactionIngestor.getLista());

        transactionListRepository.findByOriginName("C1231006815")
                .ifPresentOrElse(
                        IO::println,
                        () -> IO.println("Transação não encontrada para o cliente C1231006815"));

        transactionListRepository.findByOriginName("C12345")
                .ifPresentOrElse(
                        IO::println,
                        () -> IO.println("Transação não encontrada para o cliente C12345"));

        IO.println("-----------------------------------------------");

        long iniList = System.nanoTime();
        transactionListRepository.findByOriginName("C1868032458");
        long fimList = System.nanoTime();
        IO.println("  LIST tempo (1x): " + (fimList - iniList) + " ns");


        long iniMap = System.nanoTime();
        transactionMapRepository.findByOriginName("C1868032458");
        long fimMap = System.nanoTime();
        IO.println("  MAP tempo (1x): " + (fimMap - iniMap) + " ns");
    }
}
