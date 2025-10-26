"# Bank-Management-System-Using-JDBC" 
## 🏦 Project Overview

The **M16 Bank Management System** is a console-based application built with **Java** and **JDBC** for managing fundamental banking operations. It facilitates customer registration, login, transactional activities (Debit, Credit, Mobile Transactions, Statement), PIN management, and an administrative interface for approving new accounts and managing closed accounts.

This system is designed using a multi-layered architecture (DTO/DAO/Service/Main) to ensure separation of concerns and maintainability.

## ✨ Features

### Customer Operations
* **Customer Registration:** New customers can register, with data validation for fields like Name, Email ID, Mobile Number, Aadhar, and PAN. New accounts are created with a **"Pending"** status.
* **Customer Login:** Existing 'Active' customers can log in using their Email ID or Mobile Number and PIN.
* **Debit/Withdrawal:** Withdraw funds from the account, checking for sufficient balance.
* **Credit/Deposit:** Deposit funds into the account.
* **Check Balance:** View the current account balance.
* **Check Statement:** View a detailed list of all past transactions.
* **Mobile Transaction (Transfer):** Transfer funds to another account using the recipient's mobile number.
* **Change PIN:** Securely update the account's PIN number.
* **Request for Closing Account:** Change the account status to **"CLOSED"** for administrative review.

---

### Admin Operations
* **Admin Login:** Secure login for system administrators.
* **View All Customers:** Retrieve and display details for all customers.
* **Accept Pending Details:**
    * View a list of all customers with a **"Pending"** status.
    * Approve a pending account, automatically generating a unique **Account Number** and **PIN**, and setting the account **Status** to **"ACTIVE"**.
* **Remove Customer:** Process requests for closed accounts by permanently deleting the customer record from the system.

---

## 🛠️ Technology Stack

* **Language:** Java
* **Database Connectivity:** JDBC (Java Database Connectivity)
* **Database:** MySQL (Assumed, based on `JdbcConnections.forMySqlConnection()`)

## 🧱 Project Structure (Layers)

The application follows a standard layered approach:

| Package | Purpose | Key Classes |
| :--- | :--- | :--- |
| `org.bank.dto` | Data Transfer Objects (Model layer). Holds data and simple logic like `toString()`. | `CustomerDetails`, `TransactionDetails` |
| `org.bank.dao` | Data Access Objects. Handles all communication with the database (SQL queries). | `AdminDAO`, `CustomerDAO`, `TransactionDAO` |
| `org.bank.service`| Business Logic layer. Contains validation, transaction coordination, and business rules. | `AdminService`, `CustomerService`, `TransactionService` |
| `org.bank.exception` | Custom exceptions for handling specific business errors. | `CustomerInvalidDataException` |
| `org.bank.main` | Entry point of the application. | `Main` |

## 🚀 Getting Started

### Prerequisites

1.  **Java Development Kit (JDK):** Version 8 or newer.
2.  **MySQL Server:** A running instance of a MySQL database.
3.  **MySQL JDBC Driver:** The connector JAR file to link Java and MySQL.
4.  **Database Setup:** The necessary tables (`customer_details`, `transaction_details`, `admin_details`) must be created in your MySQL database.

### Database Schema (Illustrative)

You will need tables corresponding to your DTOs and DAO logic.

| Table Name | Primary Fields (from code analysis) | Status/Notes |
| :--- | :--- | :--- |
| `customer_details` | `Customer_Id`, `Customer_Account_Number`, `Customer_PIN`, `Customer_Status`, `Customer_Amount` | Stores all customer personal and account data. |
| `transaction_details` | `Transaction_ID`, `Account_Number`, `Transaction_Amount`, `Transction_Type`, `Balance_Amount` | Records all debit, credit, and mobile transactions. |
| `admin_details` | `Admin_Email_ID`, `Admin_Password` | Used for admin login validation. |

### Configuration

You need to configure the database connection details within the `org.bank.util.JdbcConnections` class (which wasn't provided, but is referenced in all DAO classes).

**In `JdbcConnections` (Conceptual):**
Ensure the `forMySqlConnection()` method returns a valid `Connection` object configured with your database URL, username, and password.

### Running the Application

1.  Compile the Java files.
2.  Run the `Main` class:
    ```bash
    java org.bank.main.Main
    ```
3.  The application will start with the main menu:
    ```
    **--***Welcome to M16 Bank***--**

    Enter Your Choice: 
    1.For Customer Login
    2.For Customer Registration
    3.For Admin Login
    ```