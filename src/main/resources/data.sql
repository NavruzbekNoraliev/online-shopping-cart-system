-- Insert customer
INSERT INTO customer (first_name, last_name, email, phone) VALUES ('John', 'Doe', 'john.doe@example.com', '1234567890');

-- Insert shipping address
INSERT INTO address (street, city, state, zip) VALUES ('123 Main St', 'Anytown', 'CA', '12345');
SET @shipping_address_id = LAST_INSERT_ID();

-- Insert billing address
INSERT INTO address (street, city, state, zip) VALUES ('456 Elm St', 'Anytown', 'CA', '12345');
SET @billing_address_id = LAST_INSERT_ID();

-- Insert account
INSERT INTO account (username, password) VALUES ('johndoe@gmail.com', 'password123');
SET @account_id = LAST_INSERT_ID();

-- Link customer to shipping and billing addresses
INSERT INTO customer (customer_id, shipping_address_id) VALUES (1, @shipping_address_id);
INSERT INTO customer (customer_id, billing_address_id) VALUES (1, @billing_address_id);

-- Link customer to account
INSERT INTO customer (customer_id, account_account_id) VALUES (1, @account_id);
