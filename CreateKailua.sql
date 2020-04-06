-- Creating and using the database
CREATE DATABASE kailua;
USE kailua;

-- Creating the tables
CREATE TABLE model
(
	model_id INT NOT NULL UNIQUE AUTO_INCREMENT,
    model_group INT NOT NULL,
    brand VARCHAR(45) NOT NULL,
    model_details VARCHAR(45) NOT NULL,
    fuel_type VARCHAR(20) NOT NULL,
    
    PRIMARY KEY(model_id)
);

CREATE TABLE car
(
	car_id INT NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY,
    reg_nr VARCHAR(7) NOT NULL,
    reg_date DATE NOT NULL,
    odometer INT NOT NULL,
    model_id INT NOT NULL,
    
    FOREIGN KEY (model_id) REFERENCES model(model_id)
    ON DELETE CASCADE
);

CREATE TABLE licence
(
	licence_no INT NOT NULL UNIQUE PRIMARY KEY,
    licence_date DATE NOT NULL
);

CREATE TABLE address 
(
	address_id INT NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY,
    address_details VARCHAR(75) NOT NULL,
    zip VARCHAR(10) NOT NULL,
    city VARCHAR(45) NOT NULL
);

CREATE TABLE customer
(
	customer_id INT NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(45) NOT NULL,
    last_name VARCHAR(45) NOT NULL,
    phone_no VARCHAR(16) NOT NULL,
    email VARCHAR(45) NOT NULL,
    licence_no INT NOT NULL,
    address_id INT NOT NULL,
    
    FOREIGN KEY(licence_no) REFERENCES licence(licence_no)
    ON DELETE CASCADE,
    
    FOREIGN KEY(address_id) REFERENCES address(address_id)
    ON DELETE CASCADE
);

CREATE TABLE rental
(
	rental_id INT NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY,
    start_date DATETIME NOT NULL,
    end_date DATETIME NOT NULL,
    max_km INT NOT NULL,
    car_id INT NOT NULL,
    customer_id INT NOT NULL,
    
    FOREIGN KEY(car_id) REFERENCES car(car_id)
    ON DELETE CASCADE,
    
    FOREIGN KEY(customer_id) REFERENCES customer(customer_id)
    ON DELETE CASCADE
)

