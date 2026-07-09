import javax.swing.*;
import java.awt.*;

public class DepositWindow extends JFrame {
    private BankManager bank;
    private Account account;
    private JFrame parentFrame;

    public DepositWindow(BankManager bank, Account account, JFrame parentFrame) {
        this.bank = bank;
        this.account = account;
        this.parentFrame = parentFrame;
        JFrame frame = UIHelper.createFrame("SecureBank - Deposit", 480, 400);
        frame.setLayout(new BorderLayout());

        frame.add(UIHelper.createGradientHeader("\uD83D\uDCB3", "DEPOSIT"), BorderLayout.NORTH);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(UIHelper.BG);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        // Form Card
        JPanel formCard = UIHelper.createCard();
        formCard.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        JLabel titleLabel = new JLabel("Enter Deposit Amount");
        titleLabel.setFont(UIHelper.getFont(Font.BOLD, 16));
        titleLabel.setForeground(UIHelper.TEXT_DARK);

        JTextField amountField = UIHelper.createTextField(15);
        amountField.setPreferredSize(new Dimension(250, 42));

        JLabel statusLabel = new JLabel(" ");
        statusLabel.setFont(UIHelper.getFont(Font.PLAIN, 12));
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);

        gbc.gridx = 0; gbc.gridy = 0;
        formCard.add(titleLabel, gbc);
        gbc.gridy = 1;
        formCard.add(amountField, gbc);
        gbc.gridy = 2;
        formCard.add(statusLabel, gbc);

        // Buttons
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        btnPanel.setBackground(UIHelper.CARD_BG);

        JButton depositBtn = UIHelper.createButton("Deposit", UIHelper.SUCCESS);
        depositBtn.addActionListener(e -> {
            String input = amountField.getText().trim().replaceAll("[^\\d.]", "");
            if (input.isEmpty()) {
                statusLabel.setForeground(UIHelper.DANGER);
                statusLabel.setText("\u274C  Please enter an amount!");
                return;
            }
            try {
                double amount = Double.parseDouble(input);
                bank.deposit(account, amount);
                statusLabel.setForeground(UIHelper.SUCCESS);
                statusLabel.setText("\u2705  Deposited \u20B9" + String.format("%.2f", amount) + " successfully!");
                amountField.setText("");
                parentFrame.repaint();
            } catch (InvalidAmountException ex) {
                statusLabel.setForeground(UIHelper.DANGER);
                statusLabel.setText("\u274C  " + ex.getMessage());
            }
        });

        JButton closeBtn = UIHelper.createButton("Close", UIHelper.DANGER);
        closeBtn.addActionListener(e -> frame.dispose());

        btnPanel.add(depositBtn);
        btnPanel.add(closeBtn);

        gbc.gridy = 3;
        formCard.add(btnPanel, gbc);

        mainPanel.add(formCard, BorderLayout.CENTER);
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
