package br.com.zenon.fraud.mains;

import br.com.zenon.fraud.TransactionReport;

import java.io.IOException;

public class ReportMain {

    static void main() throws IOException {
        TransactionReport report = new TransactionReport();
        report.process("zenon-fraud-detector/data/PS_20174392719_1491204439457_log.csv");

        IO.println("Total de linhas: " + report.getTotalLinhas());
        IO.println("Total de fraudes: " + report.getTotalFraudes());
        IO.println("Valor total transacionado: " + report.getValorTotalTransacionado());
    }
}
