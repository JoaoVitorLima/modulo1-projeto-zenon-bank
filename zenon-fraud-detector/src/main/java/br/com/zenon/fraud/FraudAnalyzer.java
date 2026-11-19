package br.com.zenon.fraud;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FraudAnalyzer {

    private final List<Transaction> transactions;

    public FraudAnalyzer(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<Transaction> fraud() {
        return transactions.stream()
                .filter(Transaction::isFraud)
                .toList();
    }

    public long totalFrauds() {
        return fraud().size();
    }

    public List<BigDecimal> biggestFrauds() {
        return fraud().stream()
                .sorted(Comparator.comparing(Transaction::amount).reversed())
                .limit(3)
                .map(Transaction::amount)
                .toList();
    }

    public List<String> top5SuspectClients() {
        return fraud().stream()
                .collect(Collectors.groupingBy(
                        t -> t.origin().name(),
                        Collectors.reducing(BigDecimal.ZERO, Transaction::amount, BigDecimal::add)
                ))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, BigDecimal>comparingByValue().reversed())
                .limit(5)
                .map(Map.Entry::getKey)
                .toList();
    }

    public BigDecimal totalLostAmount() {
        return fraud().stream()
                .map(Transaction::amount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Map<TransactionType, Long> fraudsPerType() {
        return fraud().stream()
                .collect(Collectors.groupingBy(Transaction::type, Collectors.counting()));
    }
}
