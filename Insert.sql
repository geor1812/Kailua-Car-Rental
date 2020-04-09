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


    