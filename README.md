# Billing System

Welcome to the **Billing System** project! This system provides a comprehensive solution for managing bills, transactions, and payments. Whether you're a business owner or an individual, this billing system simplifies the process of creating, retrieving, and managing bills and transactions.

## Features

The Billing System project offers the following features:

1. **Add Bill:** Easily create and add new bills to the system. Specify relevant details such as customer information, items purchased, and due dates.

2. **Retrieve Bill:** Retrieve bill details using various search parameters. This helps in quickly accessing specific bills and their information.

3. **Pay Bill:** Record payments and mark bills as paid. This feature allows you to keep track of bill payment statuses.

4. **Retrieve Transaction:** Retrieve transaction records to keep a log of all the financial activities within the system.

## Getting Started

Follow these steps to run the Billing System project locally:

1. Ensure you have Java and Maven installed on your system.
2. Clone the project repository:
3. Navigate to the project directory:
4. Open the `application.properties` file in the `src/main/resources` directory.
5. Modify the database username and password to match your own database setup:
```properties
spring.datasource.username=your_username
spring.datasource.password=your_password
```
6. Run the project using maven: `mvnw spring-boot:run`

## Application Start and Endpoint Access

The application will start, allowing you to access the endpoints and interact with the system.

## Calling Web Services

To interact with the Billing System's web services, a Postman collection is provided in the `src/main/resources` directory with name `Billing System.postman_collection.json`. Import this collection into Postman to easily make API requests and test the functionality.

## Default Usernames and Passwords

The following default usernames and passwords are available:

- For admin access: Username: `admin`, Password: `root`
- For user access: Username: `user`, Password: `root`

## Data Initialization

You can initialize data in the Billing System through two methods:

1. **SQL Initialization:** Run SQL scripts (which is provided in `src/main/resources` directory named `init_data.sql`) to set up a predefined dataset in the database.

2. **Automatic Initialization:** When you run the project using `mvnw spring-boot:run`, the system will automatically initialize sample data. This is useful for quickly testing the application with dummy data.

## License

This project currently does not have a specific license. Please use, modify, and distribute the code responsibly.

