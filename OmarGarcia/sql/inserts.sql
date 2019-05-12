INSERT INTO cus_lifebank.customer(
            dui, email, last_anme, name, telefono)
    VALUES ('123456789', '"omargarcia645@gmail.com"', 'garcia','omar', '22222222');

INSERT INTO acc_lifebank.account(
            id_account, end_date, name, start_date, state, id_customer)
    VALUES ('PEA1', '01-01-2020', 'CUENTA AHORRO 1', '01-01-2019', 'A', '123456789');

INSERT INTO acc_lifebank.account(
            id_account, end_date, name, start_date, state, id_customer)
    VALUES ('PEA2', '01-01-2020', 'CUENTA AHORRO 2', '01-01-2019', 'A', '123456789');

INSERT INTO acc_lifebank.account(
            id_account, end_date, name, start_date, state, id_customer)
    VALUES ('CCA1', '01-01-2020', 'TARJETA CREDITO 1', '01-01-2019', 'A', '123456789');

INSERT INTO acc_lifebank.account(
            id_account, end_date, name, start_date, state, id_customer)
    VALUES ('LOA1', '01-01-2020', 'PRESTAMO AUTO 1', '01-01-2019', 'A', '123456789');

INSERT INTO acc_lifebank.personal_account(
            id_account,available,reserved)
    VALUES ('PEA1',1000,0);

INSERT INTO acc_lifebank.personal_account(
            id_account,available,reserved)
    VALUES ('PEA2',1000,0);

INSERT INTO acc_lifebank.credit_account(
            available, interest_amount, interest_rate, acc_limit, monthly_cut, 
            id_account)
    VALUES (1000, 0, 39, 1000, 1, 
            'CCA1');

INSERT INTO acc_lifebank.loan_account(
            debt, interest_amount, interest_rate, total, id_account)
    VALUES (100, 20, 5, 200, 'LOA1');

INSERT INTO txn_lifebank.transaction(
            amount, description, transaction_date, id_account,
            transaction_number,transaction_type)
    VALUES (10, 'AMAZON', '01-05-2019', 'CCA1','20190511010101001','D');

INSERT INTO txn_lifebank.transaction(
            amount, description, transaction_date, id_account,
            transaction_number,transaction_type)
    VALUES (20, 'EBAY', '02-05-2019', 'CCA1','20190511010101002','D');

INSERT INTO txn_lifebank.transaction(
            amount, description, transaction_date, id_account,
            transaction_number,transaction_type)
    VALUES (30, 'NETFLIX 1', '01-02-2019', 'CCA1','20190511010101003','D');

INSERT INTO txn_lifebank.transaction(
            amount, description, transaction_date, id_account,
            transaction_number,transaction_type)
    VALUES (10, 'NETFLIX 2', '01-04-2019', 'PEA1','20190511010101004','D');

INSERT INTO txn_lifebank.transaction(
            amount, description, transaction_date, id_account,
            transaction_number,transaction_type)
    VALUES (20, 'AMAZON', '02-04-2019', 'PEA1','20190511010101005','D');

INSERT INTO txn_lifebank.transaction(
            amount, description, transaction_date, id_account,
            transaction_number,transaction_type)
    VALUES (30, 'EBAY', '01-01-2019', 'PEA1','20190511010101006','D');


