CREATE DATABASE billing_system;
USE billing_system;

CREATE TABLE bill (
  id BIGINT NOT NULL AUTO_INCREMENT,
  created_by VARCHAR(255),
  created_date DATETIME(6),
  updated_by VARCHAR(255),
  updated_date DATETIME(6),
  description VARCHAR(255),
  name VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE transaction (
  id BIGINT NOT NULL AUTO_INCREMENT,
  amount BIGINT,
  api_caller VARCHAR(255) NOT NULL,
  created_by VARCHAR(255),
  created_date DATETIME(6),
  updated_by VARCHAR(255),
  updated_date DATETIME(6),
  phone_number VARCHAR(20) NOT NULL,
  reference_no VARCHAR(255) NOT NULL,
  bill_id BIGINT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (bill_id) REFERENCES bill(id) ON DELETE CASCADE
) ENGINE = InnoDB;

CREATE TABLE user_info (
  id BIGINT NOT NULL AUTO_INCREMENT,
  created_by VARCHAR(255),
  created_date DATETIME(6),
  updated_by VARCHAR(255),
  updated_date DATETIME(6),
  email VARCHAR(80),
  name VARCHAR(60) NOT NULL,
  password VARCHAR(255),
  phone_number VARCHAR(30),
  role VARCHAR(255),
  PRIMARY KEY (id)
) ENGINE = InnoDB;

INSERT INTO user_info (created_by, created_date, email, name, password, phone_number, role) VALUES
	('System', NOW(6), 'admin@gmail.com', 'admin', '$2a$10$4zpVVvhyrQ8cv9hyRTz5mOrL6IQYv.q0ubdAsrl3kMhEq21cUs1Hq', '959123456789', 'ADMIN' ),
	('System', NOW(6), 'user@gmail.com', 'user', '$2a$10$4zpVVvhyrQ8cv9hyRTz5mOrL6IQYv.q0ubdAsrl3kMhEq21cUs1Hq', '959987654321', 'USER'
);

INSERT INTO bill(name, description, created_by, created_date) VALUES
	('Bill 1', 'Bill 1 description', 'System', Now(6)),
	('Bill 2', 'Bill 2 description', 'System', Now(6)),
	('Bill 3', 'Bill 3 description', 'System', Now(6)),
	('Bill 4', 'Bill 4 description', 'System', Now(6)),
	('Bill 5', 'Bill 5 description', 'System', Now(6)),
	('Bill 6', 'Bill 6 description', 'System', Now(6)),
	('Bill 7', 'Bill 7 description', 'System', Now(6)
);
