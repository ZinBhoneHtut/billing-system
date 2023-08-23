# Billing System

Welcome to the **Billing System** project! This system provides a comprehensive solution for managing bills, transactions, and payments. Whether you're a business owner or an individual, this billing system simplifies the process of creating, retrieving, and managing bills and transactions.

## Features

The Billing System project offers the following features:

1. **Add Bill:** Easily create and add new bills to the system. Specify name and description.

2. **Retrieve Bill:**
   Retrieve bills with a specific ID or obtain a comprehensive list of all bills. This functionality streamlines the process of swiftly accessing precise bill details, contributing to a more efficient and informed workflow.

3. **Pay Bill:** Efficiently log payments and document corresponding transactions. This essential feature empowers you to maintain a real-time overview of bill payment statuses, enhancing your ability to manage financial records seamlessly.

4. **Retrieve Transaction:** Retrieve transaction records to keep a log of all the financial activities within the system.

## Getting Started

Follow these steps to run the Billing System project locally:


1. Ensure you have the following software installed on your system:
   - Java 1.8
   - Spring Boot 2.7.3
   - Maven 3.6.2 (Optional: You can use the Maven Wrapper if Maven is not installed)
   - MySQL 8.0
2. Clone the project repository:
   ```bash
   git clone https://github.com/yourusername/billing-system.git
3. Navigate to the project directory: 
   ```bash
   cd billing-system
4. Open the `application.properties` file in the `src/main/resources` directory.
5. Modify the database username and password to match your own database setup:
   ```properties
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```
6. Run the project using maven: `mvn spring-boot:run`\
   Alternatively, you can use the Maven Wrapper: `mvnw spring-boot:run`

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

