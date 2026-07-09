import javax.swing.*;
import java.awt.*;

public class DetailsWindow extends JFrame {
    public DetailsWindow(Account account) {
        JFrame frame = UIHelper.createFrame("SecureBank - Details", 480, 380);
        frame.setLayout(new BorderLayout());

        frame.add(UIHelper.createGradientHeader("\uD83D\uDCCB", "ACCOUNT DETAILS"), BorderLayout.NORTH);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(UIHelper.BG);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JPanel card = UIHelper.createCard();
        card.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 15, 12, 15);
        gbc.anchor = GridBagConstraints.WEST;

        String[][] fields = {
            {"\uD83C\uDF10  Account Number", account.getAccountNumber()},
            {"\uD83D\uDC64  Account Holder", account.getAccountHolder()},
            {"\uD83D\uDCB3  Current Balance", "\u20B9" + String.format("%.2f", account.getBalance())}
        };

        for (int i = 0; i < fields.length; i++) {
            gbc.gridx = 0; gbc.gridy = i;
            JLabel label = new JLabel(fields[i][0]);
            label.setFont(UIHelper.getFont(Font.BOLD, 14));
            label.setForeground(UIHelper.TEXT_GRAY);
            card.add(label, gbc);

            gbc.gridx = 1;
            JLabel value = new JLabel(fields[i][1]);
            value.setFont(UIHelper.getFont(Font.PLAIN, 15));
            value.setForeground(UIHelper.TEXT_DARK);
            card.add(value, gbc);
        }

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
