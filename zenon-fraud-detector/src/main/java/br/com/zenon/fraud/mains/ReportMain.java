package br.com.zenon.fraud.mains;

import br.com.zenon.fraud.TransactionReport;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;
import java.util.ResourceBundle;

public class ReportMain {

    static void main(String[] args) throws IOException {
        String language = (args.length > 0 ? args[0] : "pt");
        var locale = Locale.of(language);

        var integerFormatter = NumberFormat.getIntegerInstance(locale);
        var currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        currencyFormatter.setCurrency(Currency.getInstance("USD"));

        ResourceBundle resourceBundle = ResourceBundle.getBundle("report", locale);

        TransactionReport report = new TransactionReport();
        report.process("zenon-fraud-detector/data/PS_20174392719_1491204439457_log.csv");

        String fmtTotalLines = integerFormatter.format(report.getTotalLines());
        String fmtTotalFrauds = integerFormatter.format(report.getTotalFrauds());
        String fmtTotalAmount = currencyFormatter.format(report.getTotalAmountTransacted());

        String msgTotalLines = resourceBundle.getString("total.lines");
        String msgTotalFrauds = resourceBundle.getString("total.frauds");
        String msgTotalAmount = resourceBundle.getString("total.amount");

        IO.println("""
                %s: %s
                %s: %s
                %s: %s
                """.formatted(
                msgTotalLines, fmtTotalLines,
                msgTotalFrauds, fmtTotalFrauds,
                msgTotalAmount, fmtTotalAmount
        ));
    }
}
