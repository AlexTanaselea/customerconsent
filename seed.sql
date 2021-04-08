CREATE USER 'mysql'@'%' IDENTIFIED BY 'mysql';
grant all on *.* to 'mysql'@'%';

CREATE TABLE IF NOT EXISTS customer (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    CONSTRAINT unique_username UNIQUE(username)
) ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS consent (
    customer_id INT NOT NULL,
    type VARCHAR(20) NOT NULL,
    channel_name VARCHAR(20) NOT NULL,
    consent BOOLEAN DEFAULT false,
    CONSTRAINT unique_consent UNIQUE(customer_id, type, channel_name)
) ENGINE=INNODB;

INSERT INTO customer(id, username, first_name, last_name)
VALUES
(1, 'robert_m', 'robert', 'martin'),
(2, 'bob_b', 'bob', 'b'),
(3, 'alice_a', 'alice', 'a');

INSERT INTO consent(customer_id, type, channel_name, consent)
VALUES 
((SELECT id FROM customer WHERE username = 'robert_m'), 'ESSENTIALS', 'EMAIL', true),
((SELECT id FROM customer WHERE username = 'robert_m'), 'MARKETING', 'PHONE', false),
((SELECT id FROM customer WHERE username = 'robert_m'), 'OTHERS', 'PHONE', true),
((SELECT id FROM customer WHERE username = 'alice_a'), 'ESSENTIALS', 'EMAIL', true),
((SELECT id FROM customer WHERE username = 'alice_a'), 'OTHERS', 'SOCIAL', true);