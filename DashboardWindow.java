import javax.swing.*;
import java.awt.*;

public class DashboardWindow extends JFrame {
    private BankManager bank;
    private Account account;

    public DashboardWindow(BankManager bank, Account account) {
        this.bank = bank;
        this.account = account;
        JFrame frame = UIHelper.createFrame("Dashboard", 600, 500);
        frame.setLayout(new BorderLayout());

        // Header
        JPanel header = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, UIHelper.PRIMARY, getWidth(), 0, UIHelper.PRIMARY_DARK);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        header.setPreferredSize(new Dimension(0, 100));
        header.setLayout(new BorderLayout());
        header.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));

        JLabel welcomeLabel = new JLabel("\uD83C\uDFE6  Welcome, " + account.getAccountHolder());
        welcomeLabel.setFont(UIHelper.getFont(Font.BOLD, 24));
        welcomeLabel.setForeground(Color.WHITE);

        JLabel accLabel = new JLabel("Account: " + account.getAccountNumber());
        accLabel.setFont(UIHelper.getFont(Font.PLAIN, 14));
        accLabel.setForeground(new Color(200, 220, 255));

        JPanel labelPanel = new JPanel(new GridLayout(2, 1));
        labelPanel.setOpaque(false);
        labelPanel.add(welcomeLabel);
        labelPanel.add(accLabel);

        JButton logoutBtn = UIHelper.createButton("Logout", UIHelper.DANGER);
        logoutBtn.addActionListener(e -> {
            frame.dispose();
            new LoginWindow(bank);
        });

        header.add(labelPanel, BorderLayout.CENTER);
        header.add(logoutBtn, BorderLayout.EAST);
        frame.add(header, BorderLayout.NORTH);

        // Balance Card
        JPanel balanceCard = UIHelper.createCard();
        balanceCard.setLayout(new BorderLayout());
        balanceCard.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(15, 20, 5, 20),
                new javax.swing.border.LineBorder(UIHelper.TABLE_BORDER, 1, true)));

        JLabel balTitle = new JLabel("Current Balance");
        balTitle.setFont(UIHelper.getFont(Font.PLAIN, 14));
        balTitle.setForeground(UIHelper.TEXT_GRAY);

        JLabel balAmount = new JLabel("₹" + String.format("%.2f", account.getBalance()));
        balAmount.setFont(UIHelper.getFont(Font.BOLD, 32));
        balAmount.setForeground(UIHelper.SUCCESS);

        balanceCard.add(balTitle, BorderLayout.NORTH);
        balanceCard.add(balAmount, BorderLayout.CENTER);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(UIHelper.BG);
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 5, 20));
        topPanel.add(balanceCard, BorderLayout.CENTER);
        frame.add(topPanel, BorderLayout.NORTH);

        // Operation Buttons
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 15, 15));
        buttonPanel.setBackground(UIHelper.BG);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 20, 20));

        JButton depositBtn = createOpButton("\uD83D\uDCB3", "Deposit", UIHelper.SUCCESS);
        depositBtn.addActionListener(e -> new DepositWindow(bank, account, frame));

        JButton withdrawBtn = createOpButton("\uD83D\uDCB5", "Withdraw", UIHelper.WARNING);
        withdrawBtn.addActionListener(e -> new WithdrawWindow(bank, account, frame));

        JButton balanceBtn = createOpButton("\uD83D\uDCCA", "Check Balance", UIHelper.PRIMARY);
        balanceBtn.addActionListener(e -> new BalanceWindow(account));

        JButton detailsBtn = createOpButton("\uD83D\uDCCB", "Account Details", new Color(103, 58, 183));
        detailsBtn.addActionListener(e -> new DetailsWindow(account));

        buttonPanel.add(depositBtn);
        buttonPanel.add(withdrawBtn);
        buttonPanel.add(balanceBtn);
        buttonPanel.add(detailsBtn);

        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private JButton createOpButton(String icon, String text, Color bg) {
        JButton btn = new JButton("<html><center><font size='5'>" + icon + "</font><br><font size='3'>" + text + "</font></center></html>");
        btn.setFont(UIHelper.getFont(Font.BOLD, 14));
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setPreferredSize(new Dimension(200, 120));
        btn.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setOpaque(true);
        return btn;
    }
}
