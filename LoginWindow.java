import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class LoginWindow extends JFrame {
    private BankManager bank;
    private JTextField accField;
    private JPasswordField pinField;
    private JLabel statusLabel;

    public LoginWindow(BankManager bank) {
        this.bank = bank;
        JFrame frame = UIHelper.createFrame("SecureBank - Login", 550, 580);
        frame.setLayout(new BorderLayout());

        // Gradient Header
        frame.add(UIHelper.createGradientHeader("\uD83C\uDFE6", "SECUREBANK"), BorderLayout.NORTH);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(UIHelper.BG);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 20, 30));

        JTabbedPane tabs = new JTabbedPane();
        tabs.setFont(UIHelper.getFont(Font.BOLD, 13));
        tabs.setBackground(UIHelper.CARD_BG);

        // ─── Login Tab ───
        JPanel loginPanel = new JPanel(new GridBagLayout());
        loginPanel.setBackground(UIHelper.CARD_BG);
        loginPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(UIHelper.TABLE_BORDER, 1, true),
                BorderFactory.createEmptyBorder(25, 30, 25, 30)));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        JLabel loginTitle = new JLabel("Welcome Back");
        loginTitle.setFont(UIHelper.getFont(Font.BOLD, 20));
        loginTitle.setForeground(UIHelper.TEXT_DARK);

        JLabel loginSub = new JLabel("Sign in to your account");
        loginSub.setFont(UIHelper.getFont(Font.PLAIN, 13));
        loginSub.setForeground(UIHelper.TEXT_GRAY);

        accField = UIHelper.createTextField(20);
        pinField = UIHelper.createPasswordField(20);

        statusLabel = new JLabel(" ");
        statusLabel.setFont(UIHelper.getFont(Font.PLAIN, 12));
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JButton loginBtn = UIHelper.createButton("Sign In", UIHelper.PRIMARY);
        loginBtn.setPreferredSize(new Dimension(200, 42));
        loginBtn.addActionListener(e -> login(frame));
        pinField.addActionListener(e -> login(frame));

        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        loginPanel.add(loginTitle, gbc);
        gbc.gridy = 1;
        loginPanel.add(loginSub, gbc);
        gbc.gridy = 2;
        loginPanel.add(Box.createVerticalStrut(10), gbc);
        gbc.gridy = 3;
        loginPanel.add(createFieldPanel("\uD83C\uDF10  Account Number", accField), gbc);
        gbc.gridy = 4;
        loginPanel.add(createFieldPanel("\uD83D\uDD11  PIN", pinField), gbc);
        gbc.gridy = 5;
        loginPanel.add(statusLabel, gbc);
        gbc.gridy = 6;
        loginPanel.add(loginBtn, gbc);

        tabs.addTab("  Sign In  ", loginPanel);

        // ─── Register Tab ───
        JPanel registerPanel = new JPanel(new GridBagLayout());
        registerPanel.setBackground(UIHelper.CARD_BG);
        registerPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(UIHelper.TABLE_BORDER, 1, true),
                BorderFactory.createEmptyBorder(20, 30, 20, 30)));
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.insets = new Insets(6, 6, 6, 6);
        gbc2.fill = GridBagConstraints.HORIZONTAL;
        gbc2.weightx = 1;

        JLabel regTitle = new JLabel("Create Account");
        regTitle.setFont(UIHelper.getFont(Font.BOLD, 20));
        regTitle.setForeground(UIHelper.TEXT_DARK);

        JLabel regSub = new JLabel("Start your banking journey");
        regSub.setFont(UIHelper.getFont(Font.PLAIN, 13));
        regSub.setForeground(UIHelper.TEXT_GRAY);

        JTextField regAccField = UIHelper.createTextField(20);
        JTextField regNameField = UIHelper.createTextField(20);
        JTextField regDepositField = UIHelper.createTextField(20);
        JPasswordField regPinField = UIHelper.createPasswordField(20);
        JLabel regStatus = new JLabel(" ");
        regStatus.setFont(UIHelper.getFont(Font.PLAIN, 12));
        regStatus.setHorizontalAlignment(SwingConstants.CENTER);

        gbc2.gridx = 0; gbc2.gridy = 0; gbc2.gridwidth = 2;
        registerPanel.add(regTitle, gbc2);
        gbc2.gridy = 1;
        registerPanel.add(regSub, gbc2);
        gbc2.gridy = 2;
        registerPanel.add(Box.createVerticalStrut(5), gbc2);
        gbc2.gridy = 3;
        registerPanel.add(createFieldPanel("\uD83C\uDF10  Account Number", regAccField), gbc2);
        gbc2.gridy = 4;
        registerPanel.add(createFieldPanel("\uD83D\uDC64  Full Name", regNameField), gbc2);
        gbc2.gridy = 5;
        registerPanel.add(createFieldPanel("\uD83D\uDCB3  Initial Deposit (\u20B9)", regDepositField), gbc2);
        gbc2.gridy = 6;
        registerPanel.add(createFieldPanel("\uD83D\uDD10  Set PIN", regPinField), gbc2);
        gbc2.gridy = 7;
        registerPanel.add(regStatus, gbc2);

        JButton registerBtn = UIHelper.createButton("Create Account", UIHelper.SUCCESS);
        registerBtn.setPreferredSize(new Dimension(200, 42));
        registerBtn.addActionListener(e -> {
            String accNum = regAccField.getText().trim();
            String name = regNameField.getText().trim();
            String depositStr = regDepositField.getText().trim().replaceAll("[^\\d.]", "");
            String pin = new String(regPinField.getPassword()).trim();

            if (accNum.isEmpty() || name.isEmpty() || depositStr.isEmpty() || pin.isEmpty()) {
                regStatus.setForeground(UIHelper.DANGER);
                regStatus.setText("\u274C  Please fill all fields!");
                return;
            }

            try {
                double deposit = Double.parseDouble(depositStr);
                bank.createAccount(accNum, name, deposit, pin);
                regStatus.setForeground(UIHelper.SUCCESS);
                regStatus.setText("\u2705  Account created! Go to Sign In tab.");
                regAccField.setText("");
                regNameField.setText("");
                regDepositField.setText("");
                regPinField.setText("");
            } catch (InvalidAmountException ex) {
                regStatus.setForeground(UIHelper.DANGER);
                regStatus.setText("\u274C  " + ex.getMessage());
            }
        });

        gbc2.gridy = 8;
        registerPanel.add(registerBtn, gbc2);

        tabs.addTab("  Register  ", registerPanel);

        mainPanel.add(tabs, BorderLayout.CENTER);
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private JPanel createFieldPanel(String labelText, JComponent field) {
        JPanel panel = new JPanel(new BorderLayout(0, 5));
        panel.setBackground(UIHelper.CARD_BG);

        JLabel label = new JLabel(labelText);
        label.setFont(UIHelper.getFont(Font.PLAIN, 12));
        label.setForeground(UIHelper.TEXT_GRAY);

        panel.add(label, BorderLayout.NORTH);
        panel.add(field, BorderLayout.CENTER);
        return panel;
    }

    private void login(JFrame frame) {
        String accNum = accField.getText().trim();
        String pin = new String(pinField.getPassword()).trim();

        if (accNum.isEmpty() || pin.isEmpty()) {
            statusLabel.setForeground(UIHelper.DANGER);
            statusLabel.setText("\u274C  Please fill all fields!");
            return;
        }

        Account account = bank.login(accNum, pin);
        if (account == null) {
            statusLabel.setForeground(UIHelper.DANGER);
            statusLabel.setText("\u274C  Invalid account number or PIN!");
        } else {
            frame.dispose();
            new DashboardWindow(bank, account);
        }
    }
}
