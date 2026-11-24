package br.com.zenon.fraud;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;

public class TransactionReport {

    private long totalLines;
    private long totalFrauds;
    private BigDecimal totalAmountTransacted  = BigDecimal.ZERO;

    public void process(String nomeArquivo) throws IOException {
        try (var lines = Files.lines(Path.of(nomeArquivo))) {
            lines.skip(1)
                    .forEach(this::processLine);
        }
    }

    private void processLine(String linha) {
        totalLines++;

        String[] p = linha.split(",", -1);
        try {
            BigDecimal amount = new BigDecimal(p[2]);
            totalAmountTransacted = totalAmountTransacted.add(amount);

            if ("1".equals(p[9])) {
                totalFrauds++;
            }
        } catch (RuntimeException e) {
        }
    }

    public long getTotalLines() {
        return totalLines;
    }

    public long getTotalFrauds() {
        return totalFrauds;
    }

    public BigDecimal getTotalAmountTransacted() {
        return totalAmountTransacted;
    }
}
