package br.com.zenon.fraud;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TransactionIngestor {

    private List<Transaction> lista = new ArrayList<>();

    public TransactionIngestor(String nomeArquivo) throws IOException {

        try (var lines = Files.lines(Path.of(nomeArquivo))) {
            lines.skip(1).limit(10_000).forEach(linha -> {
                try {
                    String[] p = linha.split(",", -1);
                    int step = Integer.parseInt(p[0]);
                    TransactionType type = TransactionType.valueOf(p[1]);
                    BigDecimal amount = new BigDecimal(p[2]);
                    String originName = p[3];
                    BigDecimal originOld = new BigDecimal(p[4]);
                    BigDecimal originNew = new BigDecimal(p[5]);
                    String destName = p[6];
                    BigDecimal destOld = new BigDecimal(p[7]);
                    BigDecimal destNew = new BigDecimal(p[8]);
                    boolean isFraud = "1".equals(p[9]);
                    boolean isFlagged = "1".equals(p[10]);

                    lista.add(new Transaction(
                            step, type, amount,
                            new TransactionCustomer(originName, originOld, originNew),
                            new TransactionCustomer(destName, destOld, destNew),
                            isFraud, isFlagged));
                } catch (RuntimeException e) {
                    System.err.println("Erro: " + linha + " | " + e);
                }
            });
        }
    }

    public List<Transaction> getLista() {
        return lista;
    }


}
