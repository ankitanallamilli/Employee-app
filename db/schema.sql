-- Run once as the MySQL root user (only needed for a local MySQL install).
-- The app also creates the table itself on startup (ddl-auto=update).

CREATE DATABASE IF NOT EXISTS empdb;
CREATE USER IF NOT EXISTS 'empuser'@'%' IDENTIFIED BY 'emppass';
GRANT ALL PRIVILEGES ON empdb.* TO 'empuser'@'%';
FLUSH PRIVILEGES;

USE empdb;

CREATE TABLE IF NOT EXISTS employees (
  id         BIGINT AUTO_INCREMENT PRIMARY KEY,
  name       VARCHAR(100)  NOT NULL,
  email      VARCHAR(255)  NOT NULL UNIQUE,
  department VARCHAR(255)  NOT NULL,
  salary     DECIMAL(19,2) NOT NULL
);
