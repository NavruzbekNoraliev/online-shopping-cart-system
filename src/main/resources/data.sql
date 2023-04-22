-- insert into Product(id,description,name,price) values
--  ("laptop","iowa",4200),
--  ("watch","cali",433),
--  ("washingmachine","seattle",700);

-- INSERT INTO person (id,fname,lname) VALUES (5,'Rox','Diamonds');

insert into category  (name) values ("electronics");
insert into product  (available, color, description, name, price, quantity, vendor_id, category_id) values (true,"GREEN","bestqulity","MOBILE",200,100,1,1);
insert into customer_comment  (content, customer_id, product_id) values ("electronics",1,1);
