DROP TABLE IF EXISTS fund;

CREATE TABLE fund (
    fund_id SERIAL NOT NULL PRIMARY KEY,
    symbol VARCHAR(10),
    fund_name VARCHAR(128),
    fund_value DECIMAL
);