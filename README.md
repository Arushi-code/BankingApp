# 🏦 SecureBank - Banking Application

A professional Java-based banking application with Swing GUI and file handling for data persistence.

## Features

- **User Authentication** — Register and login with account number & PIN
- **Deposit** — Add funds to your account
- **Withdraw** — Withdraw funds with balance validation
- **Balance Check** — View current balance
- **Account Details** — View account information
- **Dashboard Stats** — Total deposits, withdrawals, transactions, and accounts
- **Transaction History** — All transactions logged with date/time
- **File Handling** — Data persists across sessions (accounts.dat, transactions.dat)
- **Exception Handling** — Invalid input, insufficient funds, duplicate accounts

## Requirements

- Java JDK 8 or higher
- No external libraries required

## How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/Arushi-code/BankingApp.git
   ```

2. Navigate to the project folder:
   ```bash
   cd BankingApp
   ```

3. Compile all Java files:
   ```bash
   javac *.java
   ```

4. Run the application:
   ```bash
   java Main
   ```

## Project Structure

```
BankingApp/
├── Main.java                  # Entry point - launches LoginWindow
├── Account.java               # Account model (encapsulation)
├── BankManager.java           # Banking operations + file handling
├── Transaction.java           # Transaction model
├── UIHelper.java              # Reusable Swing UI components
├── LoginWindow.java           # Login/Register page
├── DashboardWindow.java       # Dashboard with stats & operations
├── DepositWindow.java         # Deposit form
├── WithdrawWindow.java        # Withdraw form
├── BalanceWindow.java         # Balance display
├── DetailsWindow.java         # Account details
├── InsufficientFundsException.java  # Custom exception
├── InvalidAmountException.java      # Custom exception
├── accounts.dat               # Account data (auto-generated)
├── transactions.dat           # Transaction data (auto-generated)
└── .gitignore
```

## OOP Concepts Used

- **Encapsulation** — Private fields with getters/setters in Account class
- **Inheritance** — Custom exceptions extend Exception class
- **Polymorphism** — Exception handling with multiple catch blocks
- **Abstraction** — BankManager handles business logic separately

## Author

**Arushi Jha**
- GitHub: [@Arushi-code](https://github.com/Arushi-code)
- Email: aarushijha12@gmail.com
