insert into user(name, email, password)
values('peter', 'peter@gmail.com','abc123');

insert into user(name, email, password)
values('jim', 'jim@gmail.com', 'abc123');

insert into project(name, userId, open, budget, description,  endDate, bidNum, winningPrice, currentPrice)
values('shoppingCar', 1, true , 666.00, 'Implement a shopping car', parsedatetime('12-09-2018 18:47:52.69', 'dd-MM-yyyy hh:mm:ss.SS'), null, null, 666.00);

insert into project(name, userId, open, budget, description,  endDate, bidNum, winningPrice, currentPrice)
values('calendar', 2, true , 888.00, 'Implement a calendar', parsedatetime('09-06-2018 18:47:52.69', 'dd-MM-yyyy hh:mm:ss.SS'), null, null, 888.00);
	
