import java.util.Scanner;

public class Main {
    private static BankManager bank = new BankManager();
    private static Scanner sc = new Scanner(System.in);
    private static Account currentAccount = null;

    public static void main(String[] args) {
        System.out.println("=============================================");
        System.out.println("        BANKING APPLICATION");
        System.out.println("=============================================");

        while (true) {
            if (currentAccount == null) {
                showAuthMenu();
                int choice = getIntInput("Enter your choice: ");
                switch (choice) {
                    case 1 -> register();
                    case 2 -> login();
                    case 3 -> {
                        System.out.println("Goodbye!");
                        sc.close();
                        return;
                    }
                    default -> System.out.println("Invalid choice!");
                }
            } else {
                showBankMenu();
                int choice = getIntInput("Enter your choice: ");
                switch (choice) {
                    case 1 -> deposit();
                    case 2 -> withdraw();
                    case 3 -> checkBalance();
                    case 4 -> viewDetails();
                    case 5 -> logout();
                    default -> System.out.println("Invalid choice!");
                }
            }
        }
    }

    private static void showAuthMenu() {
        System.out.println();
        System.out.println("---------------------------------------------");
        System.out.println("  1. Create Account");
        System.out.println("  2. Login");
        System.out.println("  3. Exit");
        System.out.println("---------------------------------------------");
    }

    private static void showBankMenu() {
        System.out.println();
        System.out.println("  Welcome, " + currentAccount.getAccountHolder() + "!");
        System.out.println("---------------------------------------------");
        System.out.println("  1. Deposit");
        System.out.println("  2. Withdraw");
        System.out.println("  3. Check Balance");
        System.out.println("  4. View Account Details");
        System.out.println("  5. Logout");
        System.out.println("---------------------------------------------");
    }

    private static void register() {
        System.out.println("\n--- Create Account ---");
        System.out.print("Account Number: ");
        String accNum = sc.nextLine().trim();
        System.out.print("Account Holder Name: ");
        String name = sc.nextLine().trim();
        System.out.print("Initial Deposit: ₹");
        double deposit = getDoubleInput("");
        System.out.print("Set PIN: ");
        String pin = sc.nextLine().trim();

        try {
            bank.createAccount(accNum, name, deposit, pin);
            System.out.println("Account created successfully!");
        } catch (InvalidAmountException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void login() {
        System.out.println("\n--- Login ---");
        System.out.print("Account Number: ");
        String accNum = sc.nextLine().trim();
        System.out.print("PIN: ");
        String pin = sc.nextLine().trim();

        currentAccount = bank.login(accNum, pin);
        if (currentAccount == null) {
            System.out.println("Invalid account number or PIN!");
        } else {
            System.out.println("Login successful! Welcome, " + currentAccount.getAccountHolder());
        }
    }

    private static void deposit() {
        System.out.println("\n--- Deposit ---");
        System.out.print("Amount to deposit: ₹");
        double amount = getDoubleInput("");
        try {
            bank.deposit(currentAccount, amount);
            System.out.println("Deposited ₹" + String.format("%.2f", amount) + " successfully!");
            System.out.println("New Balance: ₹" + String.format("%.2f", currentAccount.getBalance()));
        } catch (InvalidAmountException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void withdraw() {
        System.out.println("\n--- Withdraw ---");
        System.out.print("Amount to withdraw: ₹");
        double amount = getDoubleInput("");
        try {
            bank.withdraw(currentAccount, amount);
            System.out.println("Withdrew ₹" + String.format("%.2f", amount) + " successfully!");
            System.out.println("New Balance: ₹" + String.format("%.2f", currentAccount.getBalance()));
        } catch (InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (InvalidAmountException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void checkBalance() {
        System.out.println("\n--- Balance ---");
        System.out.println("Current Balance: ₹" + String.format("%.2f", bank.checkBalance(currentAccount)));
    }

    private static void viewDetails() {
        System.out.println("\n--- Account Details ---");
        System.out.println(currentAccount);
    }

    private static void logout() {
        System.out.println("Logged out. Goodbye, " + currentAccount.getAccountHolder() + "!");
        currentAccount = null;
    }

    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = sc.nextLine().trim().replaceAll("[^\\d]", "");
                int value = Integer.parseInt(input);
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = sc.nextLine().trim().replaceAll("[^\\d.]", "");
                double value = Double.parseDouble(input);
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid amount.");
            }
        }
    }
}
