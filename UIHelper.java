import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class UIHelper {
    public static final Color BG = new Color(240, 242, 245);
    public static final Color CARD_BG = Color.WHITE;
    public static final Color PRIMARY = new Color(33, 150, 243);
    public static final Color PRIMARY_DARK = new Color(25, 118, 210);
    public static final Color SUCCESS = new Color(76, 175, 80);
    public static final Color WARNING = new Color(255, 152, 0);
    public static final Color DANGER = new Color(244, 67, 54);
    public static final Color TEXT_DARK = new Color(33, 33, 33);
    public static final Color TEXT_GRAY = new Color(117, 117, 117);
    public static final Color TABLE_HEADER = new Color(33, 150, 243);
    public static final Color TABLE_BORDER = new Color(224, 224, 224);
    public static final Color TABLE_ALT = new Color(245, 248, 255);

    public static JFrame createFrame(String title, int width, int height) {
        JFrame frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setBackground(BG);
        return frame;
    }

    public static Font getFont(int style, int size) {
        return new Font("Segoe UI", style, size);
    }

    public static JLabel createHeader(String icon, String title) {
        JPanel header = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, PRIMARY, getWidth(), 0, PRIMARY_DARK);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        header.setPreferredSize(new Dimension(0, 80));
        header.setLayout(new GridBagLayout());

        JLabel iconLabel = new JLabel(icon);
        iconLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 32));
        iconLabel.setForeground(Color.WHITE);

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(getFont(Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 0; gbc.insets = new Insets(0, 0, 0, 10);
        header.add(iconLabel, gbc);
        gbc.gridx = 1;
        header.add(titleLabel, gbc);

        JFrame frame = new JFrame();
        frame.getContentPane().add(header);
        frame.remove(header);

        return new JLabel(icon + "  " + title) {{
            setFont(UIHelper.getFont(Font.BOLD, 24));
            setForeground(Color.WHITE);
            setHorizontalAlignment(SwingConstants.CENTER);
            setPreferredSize(new Dimension(0, 80));
            setOpaque(true);
            setBackground(PRIMARY);
        }};
    }

    public static JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(getFont(Font.PLAIN, 14));
        label.setForeground(TEXT_DARK);
        return label;
    }

    public static JTextField createTextField(int cols) {
        JTextField field = new JTextField(cols);
        field.setFont(getFont(Font.PLAIN, 14));
        field.setPreferredSize(new Dimension(200, 35));
        field.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(TABLE_BORDER, 1, true),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        return field;
    }

    public static JPasswordField createPasswordField(int cols) {
        JPasswordField field = new JPasswordField(cols);
        field.setFont(getFont(Font.PLAIN, 14));
        field.setPreferredSize(new Dimension(200, 35));
        field.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(TABLE_BORDER, 1, true),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        return field;
    }

    public static JButton createButton(String text, Color bg) {
        JButton btn = new JButton(text);
        btn.setFont(getFont(Font.BOLD, 14));
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setPreferredSize(new Dimension(120, 38));
        btn.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setOpaque(true);
        return btn;
    }

    public static JPanel createCard() {
        JPanel card = new JPanel();
        card.setBackground(CARD_BG);
        card.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(TABLE_BORDER, 1, true),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)));
        return card;
    }
}
