INSERT INTO vendor (id, name, phone_number, email, address_id) VALUES
                                                       (1, 'John Doe', '1234567890', 'johndoe@example.com', 1),
                                                       (2, 'Jane Smith', '0987654321', 'janesmith@example.com', 2);

INSERT INTO vendor (id, name, phone_number, email, address_id) VALUES
                                                        (3, 'Ab', '0933654321', 'ab@example.com', 3);


INSERT INTO address (id, street, city, state, zip) VALUES
                                                            (1, '123 Main St', 'Anytown', 'CA', '12345'),
                                                            (2, '456 Elm St', 'Othertown', 'NY', '67890'),
                                                            (3, '789 Oak St', 'Somewhere', 'TX', '45678');



INSERT INTO category (id, name) VALUES
                                    (1, 'Category 1'),
                                    (2, 'Category 2'),
                                    (3, 'Category 3');

INSERT INTO product (id, name, description, price, quantity, category_id, vendor_id) VALUES
                                                                                         (1, 'Product 1', 'Description 1', 10.99, 100, 1, 1),
                                                                                         (2, 'Product 2', 'Description 2', 20.99, 50, 2, 1),
                                                                                         (3, 'Product 3', 'Description 3', 5.99, 200, 3, 2),
                                                                                         (4, 'Product 4', 'Description 4', 15.99, 75, 1, 3),
                                                                                         (5, 'Product 5', 'Description 5', 8.99, 150, 2, 2);
