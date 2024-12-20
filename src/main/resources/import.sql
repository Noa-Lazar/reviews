-- Mock data for Store
INSERT INTO store (id, organization_id, store_name) VALUES (1, 100, 'Store A');
INSERT INTO store (id, organization_id, store_name) VALUES (2, 100, 'Store B');
INSERT INTO store (id, organization_id, store_name) VALUES (3, 100, 'Store C');

-- Mock data for Review
INSERT INTO review (id, store_id, product_id, content, rating, request_time) VALUES (1, 1, 2001, 'Great product!', 5, CURRENT_TIMESTAMP);
INSERT INTO review (id, store_id, product_id, content, rating, request_time) VALUES (2, 2, 2002, 'Good value.', 4, CURRENT_TIMESTAMP);

-- Mock data for Syndication
INSERT INTO syndication (id, source_store_id, target_store_id) VALUES (1, 1, 2);
INSERT INTO syndication (id, source_store_id, target_store_id) VALUES (2, 2, 3);
