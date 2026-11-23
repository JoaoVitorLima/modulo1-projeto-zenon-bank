package br.com.zenon.fraud;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;

public class TransactionReport {

    private long totalLinhas;
    private long totalFraudes;
    private BigDecimal valorTotalTransacionado  = BigDecimal.ZERO;

    public void process(String nomeArquivo) throws IOException {
        try (var lines = Files.lines(Path.of(nomeArquivo))) {
            lines.skip(1)
                    .forEach(this::processLine);
        }
    }

    private void processLine(String linha) {
        totalLinhas++;

        String[] p = linha.split(",", -1);
        try {
            BigDecimal amount = new BigDecimal(p[2]);
            valorTotalTransacionado = valorTotalTransacionado.add(amount);

            if ("1".equals(p[9])) {
                totalFraudes++;
            }
        } catch (RuntimeException e) {
        }
    }

    public long getTotalLinhas() {
        return totalLinhas;
    }

    public long getTotalFraudes() {
        return totalFraudes;
    }

    public BigDecimal getValorTotalTransacionado() {
        return valorTotalTransacionado;
    }
}
