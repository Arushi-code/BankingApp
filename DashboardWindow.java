import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DashboardWindow extends JFrame {
    private BankManager bank;
    private Account account;

    public DashboardWindow(BankManager bank, Account account) {
        this.bank = bank;
        this.account = account;
        JFrame frame = UIHelper.createFrame("SecureBank - Dashboard", 650, 550);
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

        // Balance Card
        JPanel balanceSection = new JPanel(new BorderLayout());
        balanceSection.setBackground(UIHelper.BG);
        balanceSection.setBorder(BorderFactory.createEmptyBorder(20, 30, 10, 30));

        JPanel balanceCard = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, new Color(46, 160, 67), getWidth(), getHeight(), new Color(36, 130, 52));
                g2d.setPaint(gp);
                g2d.fillRoundRect(0, 0, getWidth() - 3, getHeight() - 3, 18, 18);
            }
        };
        balanceCard.setOpaque(false);
        balanceCard.setPreferredSize(new Dimension(0, 100));
        balanceCard.setLayout(new BorderLayout());
        balanceCard.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        JPanel leftInfo = new JPanel(new GridLayout(2, 1));
        leftInfo.setOpaque(false);

        JLabel balTitle = new JLabel("Available Balance");
        balTitle.setFont(UIHelper.getFont(Font.PLAIN, 14));
        balTitle.setForeground(new Color(220, 255, 220));

        JLabel accLabel = new JLabel("Account: " + account.getAccountNumber());
        accLabel.setFont(UIHelper.getFont(Font.PLAIN, 12));
        accLabel.setForeground(new Color(200, 240, 200));

        leftInfo.add(balTitle);
        leftInfo.add(accLabel);

        JLabel balAmount = new JLabel("\u20B9" + String.format("%.2f", account.getBalance()));
        balAmount.setFont(UIHelper.getFont(Font.BOLD, 34));
        balAmount.setForeground(Color.WHITE);
        balAmount.setHorizontalAlignment(SwingConstants.RIGHT);

        balanceCard.add(leftInfo, BorderLayout.CENTER);
        balanceCard.add(balAmount, BorderLayout.EAST);
        balanceSection.add(balanceCard, BorderLayout.CENTER);
        frame.add(balanceSection, BorderLayout.NORTH);

        // Operations Grid
        JPanel opsPanel = new JPanel(new GridLayout(2, 2, 18, 18));
        opsPanel.setBackground(UIHelper.BG);
        opsPanel.setBorder(BorderFactory.createEmptyBorder(15, 30, 25, 30));

        opsPanel.add(createOpCard("\uD83D\uDCB3", "Deposit", "Add funds to your account", UIHelper.SUCCESS, e -> new DepositWindow(bank, account, frame)));
        opsPanel.add(createOpCard("\uD83D\uDCB5", "Withdraw", "Withdraw from your account", UIHelper.WARNING, e -> new WithdrawWindow(bank, account, frame)));
        opsPanel.add(createOpCard("\uD83D\uDCCA", "Balance", "Check your current balance", UIHelper.PRIMARY, e -> new BalanceWindow(account)));
        opsPanel.add(createOpCard("\uD83D\uDCCB", "Details", "View account information", new Color(103, 58, 183), e -> new DetailsWindow(account)));

        frame.add(opsPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private JPanel createOpCard(String icon, String title, String subtitle, Color bgColor, ActionListener listener) {
        JPanel card = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(UIHelper.SHADOW);
                g2d.fillRoundRect(3, 3, getWidth() - 3, getHeight() - 3, 18, 18);
                g2d.setColor(UIHelper.CARD_BG);
                g2d.fillRoundRect(0, 0, getWidth() - 3, getHeight() - 3, 18, 18);
            }
        };
        card.setOpaque(false);
        card.setLayout(new GridBagLayout());
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JPanel content = new JPanel();
        content.setOpaque(false);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        JLabel iconLabel = new JLabel(icon);
        iconLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 38));
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(UIHelper.getFont(Font.BOLD, 18));
        titleLabel.setForeground(UIHelper.TEXT_DARK);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subLabel = new JLabel(subtitle);
        subLabel.setFont(UIHelper.getFont(Font.PLAIN, 11));
        subLabel.setForeground(UIHelper.TEXT_GRAY);
        subLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        content.add(iconLabel);
        content.add(Box.createVerticalStrut(8));
        content.add(titleLabel);
        content.add(Box.createVerticalStrut(3));
        content.add(subLabel);

        card.add(content);

        // Hover effect
        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                card.repaint();
                card.setBorder(BorderFactory.createLineBorder(bgColor, 2, true));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                card.repaint();
                card.setBorder(BorderFactory.createEmptyBorder());
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                listener.actionPerformed(null);
            }
        });

        return card;
    }
}
