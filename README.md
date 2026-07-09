# 🏦 SecureBank – Java Banking Application

A **desktop-based Banking Management System** developed in **Java Swing** that enables users to securely manage bank accounts through an intuitive graphical interface. The application demonstrates **Object-Oriented Programming (OOP)** principles, **file handling**, **exception handling**, and **GUI development**, with persistent data storage using Java Serialization.

---

## ✨ Features

- 🔐 Secure user registration and login using **Account Number** and **PIN**
- 💰 Deposit funds into an account
- 💸 Withdraw funds with balance validation
- 📊 Check current account balance
- 👤 View account details
- 📈 Dashboard displaying banking statistics
  - Total Accounts
  - Total Deposits
  - Total Withdrawals
  - Total Transactions
- 📝 Transaction history with date and time
- 💾 Automatic data persistence using Java Serialization
- ⚠️ Custom exception handling for invalid operations
- 🖥️ Clean and user-friendly Java Swing interface

---

## 🛠️ Technologies Used

- Java (JDK 8 or later)
- Java Swing
- Object-Oriented Programming (OOP)
- Java Collections Framework
- File Handling
- Object Serialization
- Exception Handling

---

## 📁 Project Structure

```text
SecureBank/
│
├── Main.java                          # Application entry point
├── Account.java                       # Account model
├── BankManager.java                   # Banking operations and file handling
├── Transaction.java                   # Transaction model
├── UIHelper.java                      # Shared Swing UI utilities
│
├── LoginWindow.java                   # Login & Registration screen
├── DashboardWindow.java               # Dashboard and banking operations
├── DepositWindow.java                 # Deposit funds
├── WithdrawWindow.java                # Withdraw funds
├── BalanceWindow.java                 # Balance inquiry
├── DetailsWindow.java                 # Account information
│
├── InsufficientFundsException.java    # Custom exception
├── InvalidAmountException.java        # Custom exception
│
├── accounts.dat                       # Serialized account data (auto-generated)
├── transactions.dat                   # Serialized transaction data (auto-generated)
├── .gitignore
└── README.md
```

---

## 🚀 Getting Started

### Prerequisites

- Java JDK 8 or later

### Clone the Repository

```bash
git clone https://github.com/Arushi-code/BankingApp.git
cd BankingApp
```

### Compile the Project

```bash
javac *.java
```

### Run the Application

```bash
java Main
```

---

## 🏗️ System Modules

- **User Authentication**
  - Register a new account
  - Secure login using Account Number and PIN

- **Account Management**
  - View account information
  - Check account balance

- **Transaction Management**
  - Deposit money
  - Withdraw money
  - Record every transaction automatically

- **Dashboard**
  - View banking statistics
  - Monitor total deposits, withdrawals, transactions, and registered accounts

---

## 💾 Data Persistence

The application stores data locally using Java Object Serialization.

### Data Files

- `accounts.dat` – Stores account information
- `transactions.dat` – Stores transaction history

### Features

- Automatically creates data files on first execution.
- Loads saved data during application startup.
- Saves all updates immediately after each transaction.
- Maintains account and transaction history across sessions.

---

## 🧠 OOP Concepts Demonstrated

| Concept | Implementation |
|----------|----------------|
| **Classes & Objects** | Account, Transaction, BankManager, GUI Windows |
| **Encapsulation** | Private fields with getters and setters |
| **Inheritance** | Custom exceptions extending `Exception` |
| **Polymorphism** | Exception handling through multiple catch blocks |
| **Abstraction** | Business logic separated in `BankManager` |
| **Serialization** | Persistent storage of objects |
| **Exception Handling** | Custom and built-in exception management |

---

## 📸 Application Workflow

```text
Launch Application
        │
        ▼
 Login / Register
        │
        ▼
    Dashboard
        │
 ┌──────┼───────────────┐
 │      │       │       │
 ▼      ▼       ▼       ▼
Deposit Withdraw Balance Details
        │
        ▼
 Update Data Files
```

---

## 🔮 Future Enhancements

- 🔒 Password hashing and encryption
- 🗄️ MySQL database integration
- 📄 PDF account statements
- 📤 Export transaction history to CSV/Excel
- 📱 JavaFX-based modern interface
- 🌐 REST API integration
- 📧 Email notifications
- 👨‍💼 Admin dashboard

---

## 📚 Learning Outcomes

This project demonstrates practical knowledge of:

- Java Programming
- Java Swing GUI Development
- Object-Oriented Programming
- File Handling
- Serialization
- Exception Handling
- Collections Framework
- Desktop Application Development

---

## 👩‍💻 Author

**Aarushi Jha**

- GitHub: https://github.com/Arushi-code
- LinkedIn: https://www.linkedin.com/in/aarushi-jha-641821337

---

## ⭐ Support

If you found this project useful, consider giving it a **⭐ Star** on GitHub.
