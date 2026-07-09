import javax.swing.*;
import java.awt.*;

public class BalanceWindow extends JFrame {
    public BalanceWindow(Account account) {
        JFrame frame = UIHelper.createFrame("SecureBank - Balance", 420, 320);
        frame.setLayout(new BorderLayout());

        frame.add(UIHelper.createGradientHeader("\uD83D\uDCCA", "BALANCE"), BorderLayout.NORTH);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(UIHelper.BG);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JPanel card = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(UIHelper.SHADOW);
                g2d.fillRoundRect(3, 3, getWidth() - 3, getHeight() - 3, 18, 18);
                GradientPaint gp = new GradientPaint(0, 0, new Color(41, 98, 255), getWidth(), getHeight(), new Color(100, 50, 200));
                g2d.setPaint(gp);
                g2d.fillRoundRect(0, 0, getWidth() - 3, getHeight() - 3, 18, 18);
            }
        };
        card.setOpaque(false);
        card.setLayout(new GridBagLayout());

        JPanel content = new JPanel();
        content.setOpaque(false);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        JLabel accLabel = new JLabel("Account: " + account.getAccountNumber());
        accLabel.setFont(UIHelper.getFont(Font.PLAIN, 13));
        accLabel.setForeground(new Color(200, 215, 255));
        accLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel balLabel = new JLabel("\u20B9" + String.format("%.2f", account.getBalance()));
        balLabel.setFont(UIHelper.getFont(Font.BOLD, 40));
        balLabel.setForeground(Color.WHITE);
        balLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subLabel = new JLabel("Available Balance");
        subLabel.setFont(UIHelper.getFont(Font.PLAIN, 13));
        subLabel.setForeground(new Color(200, 215, 255));
        subLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        content.add(accLabel);
        content.add(Box.createVerticalStrut(10));
        content.add(balLabel);
        content.add(Box.createVerticalStrut(5));
        content.add(subLabel);

        card.add(content);

        JButton closeBtn = UIHelper.createButton("Close", UIHelper.DANGER);
        closeBtn.addActionListener(e -> frame.dispose());

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnPanel.setBackground(UIHelper.BG);
        btnPanel.add(closeBtn);

        mainPanel.add(card, BorderLayout.CENTER);
        mainPanel.add(btnPanel, BorderLayout.SOUTH);
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
