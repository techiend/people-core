-- BASIC BRAND TABLE
CREATE TABLE BRAND (
    ID INT PRIMARY KEY,
    NAME VARCHAR(100) NOT NULL
);

-- BASIC PRODUCT TABLE
CREATE TABLE PRODUCT (
    ID INT PRIMARY KEY,
    NAME VARCHAR(100) NOT NULL
);

-- PRICE TABLE
CREATE TABLE PRICES (
    BRAND_ID INT NOT NULL,
    START_DATE TIMESTAMP NOT NULL,
    END_DATE TIMESTAMP NOT NULL,
    PRICE_LIST INT NOT NULL,
    PRODUCT_ID INT NOT NULL,
    PRIORITY INT NOT NULL DEFAULT 0,
    PRICE FLOAT NOT NULL,
    CURR VARCHAR(5) NOT NULL,
    CONSTRAINT fk_prices_brand FOREIGN KEY (BRAND_ID) REFERENCES BRAND(id),
    CONSTRAINT fk_prices_product FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCT(id),
    CONSTRAINT pk_prices PRIMARY KEY (BRAND_ID, PRODUCT_ID, START_DATE)
);

CREATE SEQUENCE brand_id_seq as integer;
CREATE SEQUENCE product_id_seq as integer;
