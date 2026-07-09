import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class BankManager {
    private List<Account> accounts;
    private List<Transaction> transactions;
    private static final String ACCOUNTS_FILE = "accounts.dat";
    private static final String TRANSACTIONS_FILE = "transactions.dat";

    public BankManager() {
        accounts = new ArrayList<>();
        transactions = new ArrayList<>();
        loadAccounts();
        loadTransactions();
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
        if (initialDeposit > 0) {
            transactions.add(new Transaction("DEPOSIT", initialDeposit, initialDeposit));
        }
        saveAccounts();
        saveTransactions();
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
        transactions.add(new Transaction("DEPOSIT", amount, account.getBalance()));
        saveAccounts();
        saveTransactions();
    }

    public void withdraw(Account account, double amount)
            throws InsufficientFundsException, InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Withdrawal amount must be positive.");
        }
        if (amount > account.getBalance()) {
            throw new InsufficientFundsException(
                    "Insufficient funds. Available: ₹" + String.format("%.2f", account.getBalance()));
        }
        account.setBalance(account.getBalance() - amount);
        transactions.add(new Transaction("WITHDRAW", amount, account.getBalance()));
        saveAccounts();
        saveTransactions();
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

    public List<Transaction> getAllTransactions() {
        return new ArrayList<>(transactions);
    }

    public List<Transaction> getRecentTransactions(int count) {
        int size = transactions.size();
        int start = Math.max(0, size - count);
        return new ArrayList<>(transactions.subList(start, size));
    }

    public double getTotalDeposits() {
        return transactions.stream()
                .filter(t -> t.getType().equals("DEPOSIT"))
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public double getTotalWithdrawals() {
        return transactions.stream()
                .filter(t -> t.getType().equals("WITHDRAW"))
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public int getTransactionCount() {
        return transactions.size();
    }

    public int getAccountCount() {
        return accounts.size();
    }

    private void saveAccounts() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ACCOUNTS_FILE))) {
            oos.writeObject(accounts);
        } catch (IOException e) {
            System.out.println("Error saving accounts: " + e.getMessage());
        }
    }

    private void saveTransactions() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(TRANSACTIONS_FILE))) {
            oos.writeObject(transactions);
        } catch (IOException e) {
            System.out.println("Error saving transactions: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void loadAccounts() {
        File file = new File(ACCOUNTS_FILE);
        if (!file.exists()) return;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            accounts = (List<Account>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading accounts: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void loadTransactions() {
        File file = new File(TRANSACTIONS_FILE);
        if (!file.exists()) return;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            transactions = (List<Transaction>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading transactions: " + e.getMessage());
        }
    }
}
