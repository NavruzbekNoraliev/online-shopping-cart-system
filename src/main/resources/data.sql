INSERT INTO category (id, name) VALUES
                                    (1, 'Electronics'), (2, 'Clothing'), (3, 'Beauty'), (4, 'Home Goods'), (5, 'Sports');

INSERT INTO product (name, description, price, quantity, vendor_id, color, available, category_id, image_url)
VALUES
    ('Apple iPhone 12', '64GB 5G Smartphone', 999.99, 50, 1, 'White', true, 1, 'https://www.shutterstock.com/image-illustration/cairo-egypt-october-30-2020-600w-1844050267.jpg'),
    ('Samsung Galaxy Watch 3', '45mm GPS Smartwatch', 329.99, 30, 2, 'Black', true, 1, 'https://www.shutterstock.com/shutterstock/photos/1797375715/display_1500/stock-photo-darmstadt-germany-th-august-a-german-photographer-visiting-a-shopping-mal-viewing-the-1797375715.jpg'),
    ('Nike Air Force 1', 'Men\'s Low Top Sneaker', 89.99, 100, 3, 'Black/White', true, 2, 'https://www.shutterstock.com/shutterstock/photos/1533604973/display_1500/stock-photo-wellington-new-zealand-october-black-nike-air-force-s-with-red-tag-1533604973.jpg'),
    ('Lululemon Align Pant', 'Women\'s Yoga Leggings', 98.00, 75, 4, 'Black', true, 5, 'https://www.shutterstock.com/shutterstock/photos/465886553/display_1500/stock-photo-young-beautiful-stylish-hipster-woman-in-pink-leather-jacket-glam-rock-style-summer-trend-outfit-465886553.jpg'),
    ('MacBook Pro', '16-inch Laptop with Touch Bar', 2399.00, 20, 1, 'Silver', true, 1, 'https://www.shutterstock.com/shutterstock/photos/2174063125/display_1500/stock-photo-st-petersburg-russia-june-close-up-top-view-of-macbook-grey-laptop-by-apple-mackbook-2174063125.jpg'),
    ('Dyson V11 Absolute', 'Cordless Vacuum Cleaner', 599.99, 10, 5, 'Blue', true, 4,'https://www.shutterstock.com/shutterstock/photos/1734612494/display_1500/stock-photo-penang-malaysia-may-close-up-of-the-mini-motorhead-of-dyson-cyclone-v-fluffy-vacuum-1734612494.jpg'),
    ('Clinique Moisture Surge', 'Hydrating Face Cream', 39.00, 200, 6, null, true, 3,'https://www.shutterstock.com/shutterstock/photos/763618693/display_1500/stock-photo-close-up-beauty-portrait-of-a-laughing-beautiful-half-naked-woman-applying-face-cream-and-looking-763618693.jpg'),
    ('Keurig K-Elite', 'Single Serve Coffee Maker', 169.99, 50, 7, 'Brushed Silver', true, 4),
    ('Adidas Ultraboost 21', 'Men\'s Running Shoes', 180.00, 80, 8, 'Grey', true, 5),
    ('Cuisinart Air Fryer', 'Compact 4-Quart Air Fryer', 99.99, 30, 9, 'Black', true, 4),
    ('Amazon Echo Dot', 'Smart Speaker with Alexa', 49.99, 100, 10, 'Charcoal', true, 1),
    ('Ninja Blender', '1000W Professional Blender', 79.99, 25, 11, 'Black/Silver', true, 4),
    ('Calvin Klein Underwear', 'Men\'s Boxer Briefs', 29.99, 150, 12, 'Black', true, 2),
    ('Bose QuietComfort 35 II', 'Wireless Noise-Cancelling Headphones', 299.99, 40, 13, 'Silver', true, 1),
    ('Revlon One-Step Hair Dryer', 'Hot Air Brush', 59.99, 50, 14, 'Black', true, 3);