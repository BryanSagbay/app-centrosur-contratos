package org.example.ui.components;

import org.example.styles.AppColors;
import org.example.styles.AppStyles;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Factory para crear componentes UI estandarizados y reutilizables
 */
public class ComponentFactory {

    /**
     * Crea un campo de texto con estilo moderno
     */
    public static JTextField createModernTextField() {
        JTextField textField = new JTextField();
        textField.setFont(AppStyles.FONT_INPUT);
        textField.setForeground(AppColors.TEXT_PRIMARY);
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(AppColors.BORDER_COLOR, 1, true),
                new EmptyBorder(12, 16, 12, 16)
        ));
        textField.setBackground(Color.WHITE);

        addFocusEffects(textField);
        return textField;
    }

    /**
     * Crea un campo de texto con estilo de fecha
     */
    public static JTextField createDateTextField() {
        JTextField textField = new JTextField();
        textField.setFont(new Font(AppStyles.FONT_FAMILY, Font.BOLD, 15));
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setForeground(AppColors.TEXT_PRIMARY);
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(AppColors.BORDER_COLOR, 2, true),
                new EmptyBorder(14, 12, 14, 12)
        ));
        textField.setBackground(Color.WHITE);

        addDateFieldFocusEffects(textField);
        return textField;
    }

    /**
     * Crea un checkbox con estilo moderno
     */
    public static JCheckBox createModernCheckBox(String text) {
        JCheckBox checkBox = new JCheckBox(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                if (getModel().isRollover()) {
                    g2.setColor(new Color(249, 250, 251));
                    g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                }

                g2.dispose();
                super.paintComponent(g);
            }
        };

        checkBox.setFont(AppStyles.FONT_INPUT);
        checkBox.setOpaque(false);
        checkBox.setForeground(AppColors.TEXT_PRIMARY);
        checkBox.setCursor(new Cursor(Cursor.HAND_CURSOR));
        checkBox.setFocusPainted(false);
        checkBox.setSelected(true);
        checkBox.setBorder(new EmptyBorder(14, 16, 14, 16));

        return checkBox;
    }

    /**
     * Crea un botón principal con gradiente
     */
    public static JButton createPrimaryButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                Color c1, c2;
                if (getModel().isPressed()) {
                    c1 = AppColors.PRIMARY_PRESSED;
                    c2 = AppColors.SECONDARY_COLOR.darker();
                } else if (getModel().isRollover()) {
                    c1 = AppColors.PRIMARY_HOVER;
                    c2 = AppColors.SECONDARY_COLOR;
                } else {
                    c1 = AppColors.PRIMARY_COLOR;
                    c2 = AppColors.SECONDARY_COLOR;
                }

                GradientPaint gp = new GradientPaint(0, 0, c1, getWidth(), 0, c2);
                g2.setPaint(gp);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(),
                        AppStyles.BORDER_RADIUS_MEDIUM, AppStyles.BORDER_RADIUS_MEDIUM);

                g2.dispose();
                super.paintComponent(g);
            }
        };

        button.setFont(AppStyles.FONT_BUTTON);
        button.setForeground(Color.WHITE);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setBorder(new EmptyBorder(18, 60, 18, 60));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setFocusPainted(false);

        return button;
    }

    /**
     * Crea un botón secundario con color personalizado
     */
    public static JButton createSecondaryButton(String text, Color color, Color hoverColor) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                if (getModel().isPressed()) {
                    g2.setColor(color.darker());
                } else if (getModel().isRollover()) {
                    g2.setColor(hoverColor);
                } else {
                    g2.setColor(color);
                }

                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                g2.dispose();
                super.paintComponent(g);
            }
        };

        button.setFont(org.example.styles.AppStyles.FONT_BUTTON_SMALL);
        button.setForeground(Color.WHITE);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setBorder(new EmptyBorder(10, 24, 10, 24));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setFocusPainted(false);

        return button;
    }

    /**
     * Crea un panel con sombra estilo card
     */
    public static JPanel createCardPanel(String title) {
        JPanel card = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Sombra profesional
                g2.setColor(new Color(0, 0, 0, 6));
                for (int i = 0; i < 4; i++) {
                    g2.fillRoundRect(i, i, getWidth() - i * 2, getHeight() - i * 2, 20, 20);
                }

                // Fondo del card
                g2.setColor(AppColors.CARD_COLOR);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(),
                        AppStyles.BORDER_RADIUS_LARGE, AppStyles.BORDER_RADIUS_LARGE);

                g2.dispose();
            }
        };

        card.setOpaque(false);
        card.setBorder(new EmptyBorder(25, 0, 25, 0));
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

        if (title != null && !title.isEmpty()) {
            JLabel lblTitulo = new JLabel(title);
            lblTitulo.setFont(AppStyles.FONT_SECTION_TITLE);
            lblTitulo.setForeground(AppColors.TEXT_PRIMARY);
            lblTitulo.setBorder(new EmptyBorder(0, 25, 25, 25));
            card.add(lblTitulo);
        }

        return card;
    }

    /**
     * Crea un badge moderno
     */
    public static JLabel createBadge(String text) {
        JLabel badge = new JLabel(text);
        badge.setFont(AppStyles.FONT_BADGE);
        badge.setForeground(AppColors.PRIMARY_COLOR);
        badge.setOpaque(true);
        badge.setBackground(new Color(239, 246, 255));
        badge.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(191, 219, 254), 1, true),
                new EmptyBorder(5, 14, 5, 14)
        ));
        return badge;
    }

    /**
     * Agrega efectos de foco a un campo de texto
     */
    private static void addFocusEffects(JTextField textField) {
        textField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textField.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(AppColors.FOCUS_BORDER, 2, true),
                        new EmptyBorder(11, 15, 11, 15)
                ));
                textField.setBackground(AppColors.INPUT_BG_FOCUS);
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                textField.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(AppColors.BORDER_COLOR, 1, true),
                        new EmptyBorder(12, 16, 12, 16)
                ));
                textField.setBackground(Color.WHITE);
            }
        });
    }

    /**
     * Agrega efectos de foco a un campo de fecha
     */
    private static void addDateFieldFocusEffects(JTextField textField) {
        textField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textField.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(AppColors.FOCUS_BORDER, 2, true),
                        new EmptyBorder(14, 12, 14, 12)
                ));
                textField.setBackground(AppColors.INPUT_BG_FOCUS);
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                textField.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(AppColors.BORDER_COLOR, 2, true),
                        new EmptyBorder(14, 12, 14, 12)
                ));
                textField.setBackground(Color.WHITE);
            }
        });
    }

    private ComponentFactory() {
        // Clase de utilidad, no instanciar
    }
}