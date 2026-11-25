package br.com.zenon.fraud.repository;

import br.com.zenon.fraud.Transaction;
import br.com.zenon.fraud.TransactionCustomer;
import br.com.zenon.fraud.TransactionType;
import br.com.zenon.fraud.config.ConnectionFactory;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class TransactionSQLRepository implements TransactionRepository {

    @Override
    public Optional<Transaction> findByOriginName(String originName) {
        String sql = """
            SELECT id, step, type, amount, name_orig, old_balance_org, new_balance_orig,\s
            name_dest, old_balance_dest, new_balance_dest, is_fraud, is_flagged_fraud\s
            FROM TRANSACTIONS WHERE name_orig = ?
            ORDER BY step
            LIMIT 1
            """;

        try (Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1,originName);

            try (ResultSet rs = ps.executeQuery()) {

                if(rs.next()) {
                    Transaction transaction = mapResultSetToTransaction(rs);
                    return Optional.of(transaction);
                } else {
                    IO.println("Transação não encontrada para origin: " + originName);
                    return Optional.empty();
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Transaction transaction) {

    }

    private Transaction mapResultSetToTransaction(ResultSet rs) {
        try {
            return new Transaction(
                    rs.getInt("step"),
                    TransactionType.valueOf(rs.getString("type")),
                    rs.getBigDecimal("amount"),
                    new TransactionCustomer(
                            rs.getString("name_orig"),
                            rs.getBigDecimal("old_balance_org"),
                            rs.getBigDecimal("new_balance_orig")),
                    new TransactionCustomer(
                            rs.getString("name_dest"),
                            rs.getBigDecimal("old_balance_dest"),
                            rs.getBigDecimal("new_balance_dest")),
                    rs.getBoolean("is_fraud"),
                    rs.getBoolean("is_flagged_fraud")
            );

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}
