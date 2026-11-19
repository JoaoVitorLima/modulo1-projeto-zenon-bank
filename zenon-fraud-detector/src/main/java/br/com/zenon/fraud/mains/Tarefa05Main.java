package br.com.zenon.fraud.mains;

import br.com.zenon.fraud.FraudAnalyzer;
import br.com.zenon.fraud.Transaction;
import br.com.zenon.fraud.TransactionIngestor;
import br.com.zenon.fraud.TransactionType;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class Tarefa05Main {

    static void main() throws IOException {

        TransactionIngestor transactionIngestor = new TransactionIngestor("zenon-fraud-detector/data/PS_20174392719_1491204439457_log.csv");

        FraudAnalyzer fraudAnalyzer = new FraudAnalyzer(transactionIngestor.getLista());

        IO.println("1. Total de Fraudes: " + fraudAnalyzer.totalFrauds());
        IO.println("2. Top 3 Fraudes de Maior Valor:");
        for (BigDecimal f : fraudAnalyzer.biggestFrauds()) {
            IO.println(f.toPlainString());
        }
        IO.println("3. Clientes Suspeitos:");
        for (String s : fraudAnalyzer.top5SuspectClients()) {
            IO.println(s);
        }
        IO.println("4. Prejuízo Total: " + fraudAnalyzer.totalLostAmount());
        IO.println("5. Fraudes por Tipo:");
        fraudAnalyzer.fraudsPerType().forEach((type, total) ->
                IO.println(" - " + type + ": " + total));
    }
}
