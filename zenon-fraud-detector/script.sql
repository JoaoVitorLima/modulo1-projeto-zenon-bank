CREATE TABLE IF NOT EXISTS TRANSACTIONS (
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    step            INT             NOT NULL,
    type            VARCHAR(20)     NOT NULL,
    amount          DECIMAL(19, 2)  NOT NULL,
    name_orig       VARCHAR(50)     NOT NULL,
    old_balance_org DECIMAL(19, 2)  NOT NULL,
    new_balance_orig DECIMAL(19, 2) NOT NULL,
    name_dest       VARCHAR(50)     NOT NULL,
    old_balance_dest DECIMAL(19, 2) NOT NULL,
    new_balance_dest DECIMAL(19, 2) NOT NULL,
    is_fraud        BOOLEAN         NOT NULL DEFAULT FALSE,
    is_flagged_fraud BOOLEAN         NOT NULL DEFAULT FALSE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;