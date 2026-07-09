import javax.swing.*;
import java.awt.*;

public class DetailsWindow extends JFrame {
    public DetailsWindow(Account account) {
        JFrame frame = UIHelper.createFrame("Account Details", 450, 320);
        frame.setLayout(new BorderLayout());

        JLabel header = UIHelper.createHeader("\uD83D\uDCCB", "ACCOUNT DETAILS");
        frame.add(header, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(UIHelper.BG);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 15, 8, 15);
        gbc.anchor = GridBagConstraints.WEST;

        String[][] fields = {
            {"Account Number", account.getAccountNumber()},
            {"Account Holder", account.getAccountHolder()},
            {"Balance", "₹" + String.format("%.2f", account.getBalance())}
        };

        for (int i = 0; i < fields.length; i++) {
            gbc.gridx = 0; gbc.gridy = i;
            JLabel label = UIHelper.createLabel(fields[i][0] + ":");
            label.setFont(UIHelper.getFont(Font.BOLD, 14));
            mainPanel.add(label, gbc);

            gbc.gridx = 1;
            JLabel value = new JLabel(fields[i][1]);
            value.setFont(UIHelper.getFont(Font.PLAIN, 14));
            mainPanel.add(value, gbc);
        }

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
