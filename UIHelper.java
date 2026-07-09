import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

public class UIHelper {
    public static final Color BG = new Color(235, 240, 248);
    public static final Color CARD_BG = Color.WHITE;
    public static final Color PRIMARY = new Color(41, 98, 255);
    public static final Color PRIMARY_HOVER = new Color(25, 75, 210);
    public static final Color PRIMARY_LIGHT = new Color(227, 236, 255);
    public static final Color SUCCESS = new Color(46, 160, 67);
    public static final Color SUCCESS_HOVER = new Color(36, 130, 52);
    public static final Color WARNING = new Color(255, 140, 0);
    public static final Color WARNING_HOVER = new Color(220, 120, 0);
    public static final Color DANGER = new Color(220, 53, 69);
    public static final Color DANGER_HOVER = new Color(185, 40, 55);
    public static final Color TEXT_DARK = new Color(30, 30, 30);
    public static final Color TEXT_GRAY = new Color(120, 130, 140);
    public static final Color TEXT_LIGHT = new Color(180, 190, 200);
    public static final Color TABLE_HEADER = new Color(41, 98, 255);
    public static final Color TABLE_BORDER = new Color(220, 225, 235);
    public static final Color TABLE_ALT = new Color(248, 250, 253);
    public static final Color SHADOW = new Color(0, 0, 0, 30);
    public static final Color GRADIENT_START = new Color(41, 98, 255);
    public static final Color GRADIENT_END = new Color(100, 50, 200);
    public static final Color GOLD = new Color(255, 193, 7);

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

    public static JPanel createGradientHeader(String icon, String title) {
        JPanel header = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, GRADIENT_START, getWidth(), getHeight(), GRADIENT_END);
                g2d.setPaint(gp);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 0, 0);
            }
        };
        header.setPreferredSize(new Dimension(0, 90));
        header.setLayout(new GridBagLayout());

        JLabel iconLabel = new JLabel(icon);
        iconLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 36));
        iconLabel.setForeground(Color.WHITE);

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(getFont(Font.BOLD, 26));
        titleLabel.setForeground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 0; gbc.insets = new Insets(0, 0, 0, 12);
        header.add(iconLabel, gbc);
        gbc.gridx = 1;
        header.add(titleLabel, gbc);

        return header;
    }

    public static JPanel createDashboardHeader(String icon, String title, String subtitle) {
        JPanel header = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, GRADIENT_START, getWidth(), getHeight(), GRADIENT_END);
                g2d.setPaint(gp);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 0, 0);
            }
        };
        header.setPreferredSize(new Dimension(0, 110));
        header.setLayout(new BorderLayout());
        header.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JPanel leftPanel = new JPanel();
        leftPanel.setOpaque(false);
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        JLabel iconLabel = new JLabel(icon + "  " + title);
        iconLabel.setFont(getFont(Font.BOLD, 26));
        iconLabel.setForeground(Color.WHITE);

        JLabel subLabel = new JLabel("  " + subtitle);
        subLabel.setFont(getFont(Font.PLAIN, 13));
        subLabel.setForeground(new Color(200, 215, 255));

        leftPanel.add(iconLabel);
        leftPanel.add(Box.createVerticalStrut(5));
        leftPanel.add(subLabel);

        header.add(leftPanel, BorderLayout.CENTER);
        return header;
    }

    public static JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(getFont(Font.PLAIN, 14));
        label.setForeground(TEXT_DARK);
        return label;
    }

    public static JLabel createBoldLabel(String text, int size) {
        JLabel label = new JLabel(text);
        label.setFont(getFont(Font.BOLD, size));
        label.setForeground(TEXT_DARK);
        return label;
    }

    public static JTextField createTextField(int cols) {
        JTextField field = new JTextField(cols) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(getBackground());
                g2d.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);
                super.paintComponent(g);
            }
        };
        field.setFont(getFont(Font.PLAIN, 14));
        field.setPreferredSize(new Dimension(220, 40));
        field.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(TABLE_BORDER, 1, true),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)));
        field.setOpaque(false);
        field.setBackground(CARD_BG);
        return field;
    }

    public static JPasswordField createPasswordField(int cols) {
        JPasswordField field = new JPasswordField(cols) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(getBackground());
                g2d.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);
                super.paintComponent(g);
            }
        };
        field.setFont(getFont(Font.PLAIN, 14));
        field.setPreferredSize(new Dimension(220, 40));
        field.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(TABLE_BORDER, 1, true),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)));
        field.setOpaque(false);
        field.setBackground(CARD_BG);
        return field;
    }

    public static JButton createButton(String text, Color bgColor) {
        JButton btn = new JButton(text) {
            private Color currentBg = bgColor;
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                if (getModel().isPressed()) {
                    g2d.setColor(bgColor.darker().darker());
                } else if (getModel().isRollover()) {
                    g2d.setColor(bgColor.brighter());
                } else {
                    g2d.setColor(bgColor);
                }
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
                super.paintComponent(g);
            }
        };
        btn.setFont(getFont(Font.BOLD, 13));
        btn.setForeground(Color.WHITE);
        btn.setContentAreaFilled(false);
        btn.setOpaque(false);
        btn.setFocusPainted(false);
        btn.setPreferredSize(new Dimension(130, 40));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createEmptyBorder(8, 18, 8, 18));
        return btn;
    }

    public static JButton createSmallButton(String text, Color bgColor) {
        JButton btn = createButton(text, bgColor);
        btn.setPreferredSize(new Dimension(100, 34));
        btn.setFont(getFont(Font.BOLD, 12));
        return btn;
    }

    public static JPanel createCard() {
        JPanel card = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(SHADOW);
                g2d.fillRoundRect(3, 3, getWidth() - 3, getHeight() - 3, 15, 15);
                g2d.setColor(CARD_BG);
                g2d.fillRoundRect(0, 0, getWidth() - 3, getHeight() - 3, 15, 15);
            }
        };
        card.setOpaque(false);
        card.setBorder(BorderFactory.createEmptyBorder(18, 18, 18, 18));
        return card;
    }

    public static JPanel createCardWithBorder() {
        JPanel card = createCard();
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(3, 3, 3, 3),
                BorderFactory.createCompoundBorder(
                        new LineBorder(new Color(220, 225, 235), 1, true),
                        BorderFactory.createEmptyBorder(18, 18, 18, 18))));
        return card;
    }

    public static JButton createIconButton(String icon, String text, Color bgColor) {
        JButton btn = new JButton("<html><center><font size='5'>" + icon + "</font><br><font size='3'><b>" + text + "</b></font></center></html>") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                if (getModel().isPressed()) {
                    g2d.setColor(bgColor.darker().darker());
                } else if (getModel().isRollover()) {
                    g2d.setColor(bgColor.brighter());
                } else {
                    g2d.setColor(bgColor);
                }
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 16, 16);
                super.paintComponent(g);
            }
        };
        btn.setFont(getFont(Font.BOLD, 14));
        btn.setForeground(Color.WHITE);
        btn.setContentAreaFilled(false);
        btn.setOpaque(false);
        btn.setFocusPainted(false);
        btn.setPreferredSize(new Dimension(200, 110));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return btn;
    }

    public static JLabel createStatusIcon(String icon, String text, Color color) {
        JLabel label = new JLabel(icon + " " + text);
        label.setFont(getFont(Font.PLAIN, 13));
        label.setForeground(color);
        return label;
    }
}
