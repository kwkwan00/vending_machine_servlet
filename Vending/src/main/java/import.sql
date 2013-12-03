INSERT INTO VendorItem (ItemId, Name, Stock, Price, ImagePath) VALUES (1, 'PEPSI (Can)', 0, 0.85, '../resources/images/pepsi.jpg');
INSERT INTO VendorItem (ItemId, Name, Stock, Price, ImagePath) VALUES (2, 'MOUNTAIN DEW (Bottle)', 0, 1.25, '../resources/images/mountaindew.jpg');
INSERT INTO VendorItem (ItemId, Name, Stock, Price, ImagePath) VALUES (3, 'DR. PEPPER (Bottle)', 0, 1.25, '../resources/images/drpepper.jpg');
INSERT INTO VendorItem (ItemId, Name, Stock, Price, ImagePath) VALUES (4, 'LIPTON TEA', 0, 1.75, '../resources/images/lipton.jpg');

INSERT INTO Machine (MachineId, UserBalance, Locked) VALUES (1, 0.00, false);

INSERT INTO MachineAdmin (MachineAdminId, Username, Password) VALUES (1, 'admin', 'password');
