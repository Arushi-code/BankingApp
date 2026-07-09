import java.io.*;
import java.util.*;

public class BankManager {
    private List<Account> accounts;
    private static final String FILE_NAME = "accounts.dat";

    public BankManager() {
        accounts = new ArrayList<>();
        loadAccounts();
    }

    public void createAccount(String accNum, String holder, double initialDeposit, String pin)
            throws InvalidAmountException {
        if (initialDeposit < 0) {
            throw new InvalidAmountException("Initial deposit cannot be negative.");
        }
        if (findAccount(accNum) != null) {
            throw new InvalidAmountException("Account number already exists.");
        }
        Account account = new Account(accNum, holder, initialDeposit, pin);
        accounts.add(account);
        saveAccounts();
    }

    public Account login(String accNum, String pin) {
        Account acc = findAccount(accNum);
        if (acc != null && acc.validatePin(pin)) {
            return acc;
        }
        return null;
    }

    public void deposit(Account account, double amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Deposit amount must be positive.");
        }
        account.setBalance(account.getBalance() + amount);
        saveAccounts();
    }

    public void withdraw(Account account, double amount)
            throws InsufficientFundsException, InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Withdrawal amount must be positive.");
        }
        if (amount > account.getBalance()) {
            throw new InsufficientFundsException(
                    "Insufficient funds. Available: $" + String.format("%.2f", account.getBalance()));
        }
        account.setBalance(account.getBalance() - amount);
        saveAccounts();
    }

    public double checkBalance(Account account) {
        return account.getBalance();
    }

    public Account findAccount(String accNum) {
        for (Account acc : accounts) {
            if (acc.getAccountNumber().equals(accNum)) {
                return acc;
            }
        }
        return null;
    }

    public List<Account> getAllAccounts() {
        return new ArrayList<>(accounts);
    }

    private void saveAccounts() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(accounts);
        } catch (IOException e) {
            System.out.println("Error saving accounts: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void loadAccounts() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            accounts = (List<Account>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading accounts: " + e.getMessage());
        }
    }
}
