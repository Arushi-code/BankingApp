import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DashboardWindow extends JFrame {
    private BankManager bank;
    private Account account;

    public DashboardWindow(BankManager bank, Account account) {
        this.bank = bank;
        this.account = account;
        JFrame frame = UIHelper.createFrame("SecureBank - Dashboard", 700, 620);
        frame.setLayout(new BorderLayout());

        // Header
        JPanel header = UIHelper.createDashboardHeader(
                "\uD83C\uDFE6", "DASHBOARD",
                "Welcome back, " + account.getAccountHolder());

        JButton logoutBtn = UIHelper.createSmallButton("Logout", UIHelper.DANGER);
        logoutBtn.addActionListener(e -> {
            frame.dispose();
            new LoginWindow(bank);
        });

        header.add(logoutBtn, BorderLayout.EAST);
        frame.add(header, BorderLayout.NORTH);

        // Content
        JPanel content = new JPanel();
        content.setBackground(UIHelper.BG);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));

        // Balance Card
        JPanel balanceCard = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(UIHelper.SHADOW);
                g2d.fillRoundRect(3, 3, getWidth() - 3, getHeight() - 3, 18, 18);
                GradientPaint gp = new GradientPaint(0, 0, new Color(46, 160, 67), getWidth(), getHeight(), new Color(36, 130, 52));
                g2d.setPaint(gp);
                g2d.fillRoundRect(0, 0, getWidth() - 3, getHeight() - 3, 18, 18);
            }
        };
        balanceCard.setOpaque(false);
        balanceCard.setMaximumSize(new Dimension(Integer.MAX_VALUE, 90));
        balanceCard.setLayout(new BorderLayout());
        balanceCard.setBorder(BorderFactory.createEmptyBorder(18, 25, 18, 25));

        JPanel leftInfo = new JPanel(new GridLayout(2, 1));
        leftInfo.setOpaque(false);
        JLabel balTitle = new JLabel("Available Balance");
        balTitle.setFont(UIHelper.getFont(Font.PLAIN, 13));
        balTitle.setForeground(new Color(220, 255, 220));
        JLabel accLabel = new JLabel("Account: " + account.getAccountNumber());
        accLabel.setFont(UIHelper.getFont(Font.PLAIN, 11));
        accLabel.setForeground(new Color(200, 240, 200));
        leftInfo.add(balTitle);
        leftInfo.add(accLabel);

        JLabel balAmount = new JLabel("\u20B9" + String.format("%.2f", account.getBalance()));
        balAmount.setFont(UIHelper.getFont(Font.BOLD, 30));
        balAmount.setForeground(Color.WHITE);
        balAmount.setHorizontalAlignment(SwingConstants.RIGHT);

        balanceCard.add(leftInfo, BorderLayout.CENTER);
        balanceCard.add(balAmount, BorderLayout.EAST);
        content.add(balanceCard);
        content.add(Box.createVerticalStrut(15));

        // Stats Section
        JLabel statsTitle = UIHelper.createBoldLabel("Statistics", 16);
        content.add(statsTitle);
        content.add(Box.createVerticalStrut(10));

        JPanel statsPanel = new JPanel(new GridLayout(1, 4, 12, 0));
        statsPanel.setOpaque(false);
        statsPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 90));

        statsPanel.add(createStatCard("\uD83D\uDCB3", "Total Deposits",
                "\u20B9" + String.format("%.2f", bank.getTotalDeposits()), UIHelper.SUCCESS));
        statsPanel.add(createStatCard("\uD83D\uDCB5", "Total Withdrawals",
                "\u20B9" + String.format("%.2f", bank.getTotalWithdrawals()), UIHelper.WARNING));
        statsPanel.add(createStatCard("\uD83D\uDCCA", "Transactions",
                String.valueOf(bank.getTransactionCount()), UIHelper.PRIMARY));
        statsPanel.add(createStatCard("\uD83C\uDFE6", "Total Accounts",
                String.valueOf(bank.getAccountCount()), new Color(103, 58, 183)));

        content.add(statsPanel);
        content.add(Box.createVerticalStrut(20));

        // Operations Section
        JLabel opsTitle = UIHelper.createBoldLabel("Quick Actions", 16);
        content.add(opsTitle);
        content.add(Box.createVerticalStrut(10));

        JPanel opsPanel = new JPanel(new GridLayout(1, 4, 12, 0));
        opsPanel.setOpaque(false);
        opsPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 110));

        opsPanel.add(createOpButton("\uD83D\uDCB3", "Deposit", UIHelper.SUCCESS, e -> new DepositWindow(bank, account, frame)));
        opsPanel.add(createOpButton("\uD83D\uDCB5", "Withdraw", UIHelper.WARNING, e -> new WithdrawWindow(bank, account, frame)));
        opsPanel.add(createOpButton("\uD83D\uDCCA", "Balance", UIHelper.PRIMARY, e -> new BalanceWindow(account)));
        opsPanel.add(createOpButton("\uD83D\uDCCB", "Details", new Color(103, 58, 183), e -> new DetailsWindow(account)));

        content.add(opsPanel);

        frame.add(content, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private JPanel createStatCard(String icon, String title, String value, Color color) {
        JPanel card = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(UIHelper.SHADOW);
                g2d.fillRoundRect(2, 2, getWidth() - 2, getHeight() - 2, 14, 14);
                g2d.setColor(UIHelper.CARD_BG);
                g2d.fillRoundRect(0, 0, getWidth() - 2, getHeight() - 2, 14, 14);
            }
        };
        card.setOpaque(false);
        card.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 8, 5, 8);

        JLabel iconLabel = new JLabel(icon);
        iconLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 22));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(UIHelper.getFont(Font.PLAIN, 11));
        titleLabel.setForeground(UIHelper.TEXT_GRAY);

        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(UIHelper.getFont(Font.BOLD, 16));
        valueLabel.setForeground(color);

        gbc.gridx = 0; gbc.gridy = 0;
        card.add(iconLabel, gbc);
        gbc.gridy = 1;
        card.add(titleLabel, gbc);
        gbc.gridy = 2;
        card.add(valueLabel, gbc);

        return card;
    }

    private JPanel createOpButton(String icon, String text, Color bgColor, ActionListener listener) {
        JPanel card = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(UIHelper.SHADOW);
                g2d.fillRoundRect(2, 2, getWidth() - 2, getHeight() - 2, 14, 14);
                g2d.setColor(bgColor);
                g2d.fillRoundRect(0, 0, getWidth() - 2, getHeight() - 2, 14, 14);
            }
        };
        card.setOpaque(false);
        card.setLayout(new GridBagLayout());
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel iconLabel = new JLabel(icon);
        iconLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 28));
        iconLabel.setForeground(Color.WHITE);

        JLabel textLabel = new JLabel(text);
        textLabel.setFont(UIHelper.getFont(Font.BOLD, 14));
        textLabel.setForeground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 8, 5, 8);
        gbc.gridx = 0; gbc.gridy = 0;
        card.add(iconLabel, gbc);
        gbc.gridy = 1;
        card.add(textLabel, gbc);

        card.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) { listener.actionPerformed(null); }
            public void mouseEntered(MouseEvent e) { card.repaint(); }
        });

        return card;
    }
}
