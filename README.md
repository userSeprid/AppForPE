# Web application for Product Engine.

This is a web-application that works as a bank.
This bank persist data about:
- Clients (ID, name, address, age);
- Accounts (ID, owner ID, amount of money)
- Transactions (ID, sender account ID, receiver account ID, timestamp(when transaction was performed);

Application consists of 4 pages and additional forms:

First page:
- List of bank clients (Names in table are clickable and refer to 'Client page');
- Form for creating a client;

Second page:
- Link that refer to first page;
- Table with client details;
- List of client accounts (ID's in table are clickable and refer to 'Money operations page');
- Form for creating an account;

Third page:
- Link that refer to first page;
- Table with account details;
- From for money deposit;
- Form for money withdraw;
- Form for performing transactions between accounts;

Fourth page:
- Transaction list with filter by account ID;
      
If on page four you input '1' as result you will have all deposit and withdraw transactions.

Technology, tools, libs used:
- Maven
- Spring-boot
- MySQL DB
- Thymeleaf

For deployment spring-boot plugin is uses. (org.springframework.boot:spring-boot-maven-plugin:2.0.2.RELEASE:run -f pom.xml)
