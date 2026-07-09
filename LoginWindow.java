import javax.swing.*;
import java.awt.*;

public class LoginWindow extends JFrame {
    private BankManager bank;
    private JTextField accField;
    private JPasswordField pinField;
    private JLabel statusLabel;

    public LoginWindow(BankManager bank) {
        this.bank = bank;
        JFrame frame = UIHelper.createFrame("Banking Application - Login", 500, 520);
        frame.setLayout(new BorderLayout());

        JLabel header = UIHelper.createHeader("\uD83C\uDFE6", "BANKING APPLICATION");
        frame.add(header, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(UIHelper.BG);

        JTabbedPane tabs = new JTabbedPane();
        tabs.setFont(UIHelper.getFont(Font.PLAIN, 14));

        // Login Tab
        JPanel loginPanel = new JPanel(new GridBagLayout());
        loginPanel.setBackground(UIHelper.CARD_BG);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        accField = UIHelper.createTextField(20);
        pinField = UIHelper.createPasswordField(20);

        gbc.gridx = 0; gbc.gridy = 0;
        loginPanel.add(UIHelper.createLabel("Account Number:"), gbc);
        gbc.gridy = 1;
        loginPanel.add(accField, gbc);
        gbc.gridy = 2;
        loginPanel.add(UIHelper.createLabel("PIN:"), gbc);
        gbc.gridy = 3;
        loginPanel.add(pinField, gbc);

        statusLabel = new JLabel(" ");
        statusLabel.setFont(UIHelper.getFont(Font.PLAIN, 12));
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 4;
        loginPanel.add(statusLabel, gbc);

        JButton loginBtn = UIHelper.createButton("Login", UIHelper.PRIMARY);
        loginBtn.addActionListener(e -> login(frame));
        pinField.addActionListener(e -> login(frame));
        gbc.gridy = 5;
        loginPanel.add(loginBtn, gbc);

        tabs.addTab("Login", loginPanel);

        // Register Tab
        JPanel registerPanel = new JPanel(new GridBagLayout());
        registerPanel.setBackground(UIHelper.CARD_BG);
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.insets = new Insets(8, 10, 8, 10);
        gbc2.fill = GridBagConstraints.HORIZONTAL;
        gbc2.weightx = 1;

        JTextField regAccField = UIHelper.createTextField(20);
        JTextField regNameField = UIHelper.createTextField(20);
        JTextField regDepositField = UIHelper.createTextField(20);
        JPasswordField regPinField = UIHelper.createPasswordField(20);
        JLabel regStatus = new JLabel(" ");
        regStatus.setFont(UIHelper.getFont(Font.PLAIN, 12));
        regStatus.setHorizontalAlignment(SwingConstants.CENTER);

        gbc2.gridx = 0; gbc2.gridy = 0;
        registerPanel.add(UIHelper.createLabel("Account Number:"), gbc2);
        gbc2.gridy = 1;
        registerPanel.add(regAccField, gbc2);
        gbc2.gridy = 2;
        registerPanel.add(UIHelper.createLabel("Account Holder Name:"), gbc2);
        gbc2.gridy = 3;
        registerPanel.add(regNameField, gbc2);
        gbc2.gridy = 4;
        registerPanel.add(UIHelper.createLabel("Initial Deposit (₹):"), gbc2);
        gbc2.gridy = 5;
        registerPanel.add(regDepositField, gbc2);
        gbc2.gridy = 6;
        registerPanel.add(UIHelper.createLabel("Set PIN:"), gbc2);
        gbc2.gridy = 7;
        registerPanel.add(regPinField, gbc2);
        gbc2.gridy = 8;
        registerPanel.add(regStatus, gbc2);

        JButton registerBtn = UIHelper.createButton("Create Account", UIHelper.SUCCESS);
        registerBtn.addActionListener(e -> {
            String accNum = regAccField.getText().trim();
            String name = regNameField.getText().trim();
            String depositStr = regDepositField.getText().trim().replaceAll("[^\\d.]", "");
            String pin = new String(regPinField.getPassword()).trim();

            if (accNum.isEmpty() || name.isEmpty() || depositStr.isEmpty() || pin.isEmpty()) {
                regStatus.setForeground(UIHelper.DANGER);
                regStatus.setText("Please fill all fields!");
                return;
            }

            try {
                double deposit = Double.parseDouble(depositStr);
                bank.createAccount(accNum, name, deposit, pin);
                regStatus.setForeground(UIHelper.SUCCESS);
                regStatus.setText("Account created! Go to Login tab.");
                regAccField.setText("");
                regNameField.setText("");
                regDepositField.setText("");
                regPinField.setText("");
            } catch (InvalidAmountException ex) {
                regStatus.setForeground(UIHelper.DANGER);
                regStatus.setText(ex.getMessage());
            }
        });
        gbc2.gridy = 9;
        registerPanel.add(registerBtn, gbc2);

        tabs.addTab("Register", registerPanel);

        mainPanel.add(tabs, BorderLayout.CENTER);
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void login(JFrame frame) {
        String accNum = accField.getText().trim();
        String pin = new String(pinField.getPassword()).trim();

        if (accNum.isEmpty() || pin.isEmpty()) {
            statusLabel.setForeground(UIHelper.DANGER);
            statusLabel.setText("Please fill all fields!");
            return;
        }

        Account account = bank.login(accNum, pin);
        if (account == null) {
            statusLabel.setForeground(UIHelper.DANGER);
            statusLabel.setText("Invalid account number or PIN!");
        } else {
            frame.dispose();
            new DashboardWindow(bank, account);
        }
    }
}
