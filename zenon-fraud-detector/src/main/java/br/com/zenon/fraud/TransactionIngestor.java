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

        Path arquivo = Path.of(nomeArquivo);
        String conteudoArquivo = Files.readString(arquivo);
        String[] linhaArquivo = conteudoArquivo.split("\n");

        for (int i = 1; i <= 1000; i++) {
            String linha = linhaArquivo[i];
            String[] partes = linha.split(",");

            Long step = Long.parseLong(partes[0]);
            Transaction.Type type = Transaction.Type.valueOf(partes[1]);
            BigDecimal amount = new BigDecimal(partes[2]);
            String originName = partes[3];
            BigDecimal originOldBalance = new BigDecimal(partes[4]);
            BigDecimal originNewBalance = new BigDecimal(partes[5]);
            String destinationName = partes[6];
            BigDecimal destinationOldBalance = new BigDecimal(partes[7]);
            BigDecimal destinationNewBalance = new BigDecimal(partes[8]);
            boolean isFraud = "1".equals(partes[9]);
            boolean isFlaggedFraud = "1".equals(partes[10]);

            Transaction transaction = new  Transaction(
                    step,
                    type,
                    amount,
                    new Customer(originName, originOldBalance, originNewBalance),
                    new Customer(destinationName, destinationOldBalance, destinationNewBalance),
                    isFraud,
                    isFlaggedFraud
            );

            lista.add(transaction);
        }
    }

    public List<Transaction> getLista() {
        return lista;
    }


}
