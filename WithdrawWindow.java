import javax.swing.*;
import java.awt.*;

public class WithdrawWindow extends JFrame {
    private BankManager bank;
    private Account account;
    private JFrame parentFrame;

    public WithdrawWindow(BankManager bank, Account account, JFrame parentFrame) {
        this.bank = bank;
        this.account = account;
        this.parentFrame = parentFrame;
        JFrame frame = UIHelper.createFrame("Withdraw", 450, 350);
        frame.setLayout(new BorderLayout());

        JLabel header = UIHelper.createHeader("\uD83D\uDCB5", "WITHDRAW");
        frame.add(header, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(UIHelper.BG);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        JTextField amountField = UIHelper.createTextField(15);
        JLabel statusLabel = new JLabel(" ");
        statusLabel.setFont(UIHelper.getFont(Font.PLAIN, 12));
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);

        gbc.gridx = 0; gbc.gridy = 0;
        mainPanel.add(UIHelper.createLabel("Enter Amount (₹):"), gbc);
        gbc.gridy = 1;
        mainPanel.add(amountField, gbc);
        gbc.gridy = 2;
        mainPanel.add(statusLabel, gbc);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        btnPanel.setBackground(UIHelper.BG);

        JButton withdrawBtn = UIHelper.createButton("Withdraw", UIHelper.WARNING);
        withdrawBtn.addActionListener(e -> {
            String input = amountField.getText().trim().replaceAll("[^\\d.]", "");
            if (input.isEmpty()) {
                statusLabel.setForeground(UIHelper.DANGER);
                statusLabel.setText("Please enter an amount!");
                return;
            }
            try {
                double amount = Double.parseDouble(input);
                bank.withdraw(account, amount);
                statusLabel.setForeground(UIHelper.SUCCESS);
                statusLabel.setText("Withdrew ₹" + String.format("%.2f", amount) + "!");
                amountField.setText("");
                parentFrame.repaint();
            } catch (InsufficientFundsException ex) {
                statusLabel.setForeground(UIHelper.DANGER);
                statusLabel.setText(ex.getMessage());
            } catch (InvalidAmountException ex) {
                statusLabel.setForeground(UIHelper.DANGER);
                statusLabel.setText(ex.getMessage());
            }
        });

        JButton closeBtn = UIHelper.createButton("Close", UIHelper.DANGER);
        closeBtn.addActionListener(e -> frame.dispose());

        btnPanel.add(withdrawBtn);
        btnPanel.add(closeBtn);

        gbc.gridy = 3;
        mainPanel.add(btnPanel, gbc);

        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
