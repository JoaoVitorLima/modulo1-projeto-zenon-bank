package br.com.zenon.fraud.main;

import br.com.zenon.fraud.config.ConnectionFactory;
import br.com.zenon.fraud.repository.TransactionSQLRepository;

public class DBMain {

    void main() {
        ConnectionFactory.getConnection();
        IO.println("Conexão com o DB criada!");

        TransactionSQLRepository repository = new TransactionSQLRepository();
        repository.findByOriginName("C123");
    }
}
