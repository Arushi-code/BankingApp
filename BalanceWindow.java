import javax.swing.*;
import java.awt.*;

public class BalanceWindow extends JFrame {
    public BalanceWindow(Account account) {
        JFrame frame = UIHelper.createFrame("Check Balance", 400, 280);
        frame.setLayout(new BorderLayout());

        JLabel header = UIHelper.createHeader("\uD83D\uDCCA", "BALANCE");
        frame.add(header, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(UIHelper.BG);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel balLabel = new JLabel("₹" + String.format("%.2f", account.getBalance()));
        balLabel.setFont(UIHelper.getFont(Font.BOLD, 36));
        balLabel.setForeground(UIHelper.SUCCESS);
        balLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel subLabel = new JLabel("Available Balance");
        subLabel.setFont(UIHelper.getFont(Font.PLAIN, 14));
        subLabel.setForeground(UIHelper.TEXT_GRAY);
        subLabel.setHorizontalAlignment(SwingConstants.CENTER);

        gbc.gridx = 0; gbc.gridy = 0;
        mainPanel.add(balLabel, gbc);
        gbc.gridy = 1;
        mainPanel.add(subLabel, gbc);

        JButton closeBtn = UIHelper.createButton("Close", UIHelper.DANGER);
        closeBtn.addActionListener(e -> frame.dispose());

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnPanel.setBackground(UIHelper.BG);
        btnPanel.add(closeBtn);

        frame.add(mainPanel, BorderLayout.CENTER);
        frame.add(btnPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}
