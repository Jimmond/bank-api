# Bank API

Banking Management System built with Java 21, Spring Boot, PostgreSQL and JPA.

## Features

* Customer Management
* Account Management
* Deposits
* Withdrawals
* Transfers
* Transaction History
* PostgreSQL Persistence
* REST API

## Tech Stack

* Java 21
* Spring Boot 3.5
* Spring Data JPA
* Hibernate
* PostgreSQL
* Maven
* Lombok

## Endpoints

### Customers

* POST /api/customers
* GET /api/customers

### Accounts

* POST /api/accounts
* GET /api/accounts
* GET /api/accounts/{id}
* GET /api/accounts/number/{accountNumber}

### Transactions

* POST /api/accounts/{id}/deposit
* POST /api/accounts/{id}/withdraw
* POST /api/accounts/transfer
* GET /api/accounts/{id}/transactions

## Author

Jimmy Ortiz
