CREATE TABLE clients (
	id INT PRIMARY KEY AUTO_INCREMENT,
	NAME TEXT,
	email TEXT,
	phone INT
);

CREATE TABLE client_registration_ids (
	id INT PRIMARY KEY AUTO_INCREMENT,
	client_id INT,
	registration_id TEXT,
	FOREIGN KEY (client_id) REFERENCES clients (id)
);