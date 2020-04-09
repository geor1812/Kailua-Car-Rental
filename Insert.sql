USE kailua;

INSERT INTO address
	(address_details, zip, city)
VALUES 
	('Amagerbro 47, 3 TH', '2300', 'Copenhagen'),
    ('Smallegade 3D, 1 TV', '2000', 'Frederiksberg'),
    ('Korsørgade 6, 3', '2100', 'Copenhagen'),
    ('Skolevænget 17', '2800', 'Kongens Lyngby'),
    ('Klerkegade 5, 7', '1304', 'Copenhagen');

INSERT INTO licence VALUES
(17568981, '2017-03-25'),
(22456990, '2009-11-02'),
(54541289, '2012-07-17'),
(81922256, '2006-05-29'),
(12753390, '2000-10-05');

INSERT INTO customer
(first_name, last_name, phone_no, email, licence_no, address_id)
VALUES
('Hanna', 'Larsen', '75492203', 'hannal@gmail.com', 17568981, 1),
('Jakob', 'Funch', '43891733', 'funchjk90@hotmail.com', 22456990, 2),
('Frederik', 'Bjorn', '63671867', 'fred_bjorn@gmail.com', 54541289, 3),
('Mette', 'Morgensen', '36162280', 'mette.morgensen@gmail.com', 81922256, 4),
('Emma', 'Lund', '70554789', 'emma_lund17@yahoo.com', 12753390, 5);

INSERT INTO model
(model_group, brand, model_details, fuel_type)
VALUES
(1,'Jaguar','XJS', 'E85 Gasoline'),
(1,'Porsche','Caiman', '90+ Octane Gasoline'),
(2,'Volkswagen','Sharan', 'Diesel'),
(3,'Nissan','300ZX', '90+ Octane Gasoline'),
(2,'Land Rover','Discovery', 'E85 Gasoline'),
(3,'Bmw','Z4', '90+ Octane Gasoline');

INSERT INTO car
(reg_nr, reg_date, odometer, model_id)
VALUES 
('XM32356', '2017-06-10', 20000, 1),
('PC22590', '2019-04-09', 15000, 2),
('ZX89633', '2015-01-16', 55000, 3),
('NN90833', '2016-12-02', 50000, 4),
('PC22590', '2013-07-17', 83000, 5),
('ZC17030', '2017-09-28', 45000, 6);

INSERT INTO rental
(start_date, end_date, max_km, car_id, customer_id)
VALUES
('2019-11-02 15:00:00', '2019-11-12 20:00:00', 5000, 1, 3),
('2019-11-28 10:15:00', '2019-12-20 20:00:00', 10000, 3, 1),
('2020-01-15 12:36:00', '2020-02-01 12:00:00', 10000, 6, 2),
('2020-03-17 09:45:00', '2020-03-25 12:00:00', 5000, 2, 4);
    