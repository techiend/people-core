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
    CONSTRAINT pk_prices PRIMARY KEY (BRAND_ID, PRODUCT_ID, START_DATE)
);
