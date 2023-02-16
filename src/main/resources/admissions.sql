DROP DATABASE IF EXISTS admissions;
CREATE DATABASE admissions CHAR SET UTF8;
SHOW DATABASES;
USE admissions;

-- CREATE TABLE cart
-- (
--     id    INT          NOT NULL AUTO_INCREMENT,
--     name  VARCHAR(255) NOT NULL,
--     total INT          NOT NULL,
--     PRIMARY KEY (id)
-- ) ENGINE = INNODB;
--
-- CREATE TABLE cart_item
-- (
--     cart_id INT DEFAULT NULL,
--     item_id INT DEFAULT NULL,
--     CONSTRAINT cart_id_FK FOREIGN KEY (cart_id)
--         REFERENCES cart (id) ON DELETE CASCADE ON UPDATE RESTRICT,
--     CONSTRAINT item_id_FK FOREIGN KEY (item_id)
--         REFERENCES item (id) ON DELETE CASCADE ON UPDATE RESTRICT
-- ) ENGINE = INNODB;
--
-- DESCRIBE cart;
--
-- SELECT *
-- FROM cart;