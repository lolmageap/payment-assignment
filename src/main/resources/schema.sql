CREATE TABLE IF NOT EXISTS customer (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255),
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS bankbook (
    id BIGINT PRIMARY KEY,
    customer_id BIGINT,
    account_number VARCHAR(255),
    balance DECIMAL(19, 2),
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customer(id)
);

CREATE TABLE IF NOT EXISTS market (
    id BIGINT PRIMARY KEY,
    token VARCHAR(255),
    name VARCHAR(255),
    bankbook_id BIGINT,
    is_active BOOLEAN,
    is_delete BOOLEAN,
    deleted_at TIMESTAMP,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    FOREIGN KEY (bankbook_id) REFERENCES bankbook(id)
);

CREATE TABLE IF NOT EXISTS order_table (
    id BIGINT PRIMARY KEY,
    order_token VARCHAR(255),
    customer_id BIGINT,
    market_id BIGINT,
    price DECIMAL(19, 2),
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customer(id),
    FOREIGN KEY (market_id) REFERENCES market(id)
);

CREATE TABLE IF NOT EXISTS payment_history (
    id BIGINT PRIMARY KEY,
    customer_id BIGINT,
    market_id BIGINT,
    order_id BIGINT,
    price DECIMAL(19, 2),
    is_success BOOLEAN,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customer(id),
    FOREIGN KEY (market_id) REFERENCES market(id),
    FOREIGN KEY (order_id) REFERENCES order_table(id)
);
