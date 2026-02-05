package org.example.ui.components;

import org.example.styles.AppColors;
import org.example.styles.AppStyles;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import java.awt.*;

/**
 * Tabs moderno con estilo personalizado
 */
public class ModernTabbedPane extends JTabbedPane {

    public ModernTabbedPane() {
        super();
        initializeUI();
    }

    private void initializeUI() {
        setTabPlacement(JTabbedPane.TOP);
        setOpaque(false);
        setFont(AppStyles.FONT_TAB);

        setUI(new BasicTabbedPaneUI() {
            private final Color selectedColor = AppColors.PRIMARY_COLOR;
            private final Color hoverColor = AppColors.PRIMARY_HOVER;
            private final Color normalColor = AppColors.TEXT_SECONDARY;

            @Override
            protected void installDefaults() {
                super.installDefaults();
                tabInsets = new Insets(15, 30, 15, 30);
                selectedTabPadInsets = new Insets(0, 0, 0, 0);
                tabAreaInsets = new Insets(10, 20, 0, 20);
            }

            @Override
            protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex,
                                              int x, int y, int w, int h, boolean isSelected) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                if (isSelected) {
                    // Tab seleccionado con fondo
                    g2.setColor(new Color(239, 246, 255)); // Blue-50
                    g2.fillRoundRect(x, y, w, h, 12, 12);

                    // Borde inferior del tab seleccionado
                    g2.setColor(selectedColor);
                    g2.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                    g2.drawLine(x + 10, y + h - 2, x + w - 10, y + h - 2);
                } else {
                    // Tab no seleccionado con hover
                    Rectangle bounds = getBoundsAt(tabIndex);
                    Point mousePos = getMousePosition();

                    if (mousePos != null && bounds.contains(mousePos)) {
                        g2.setColor(new Color(249, 250, 251)); // Gray-50
                        g2.fillRoundRect(x, y, w, h, 12, 12);
                    }
                }

                g2.dispose();
            }

            @Override
            protected void paintTabBorder(Graphics g, int tabPlacement, int tabIndex,
                                          int x, int y, int w, int h, boolean isSelected) {
                // No pintar borde
            }

            @Override
            protected void paintContentBorder(Graphics g, int tabPlacement, int selectedIndex) {
                // No pintar borde del contenido
            }

            @Override
            protected void paintFocusIndicator(Graphics g, int tabPlacement, Rectangle[] rects,
                                               int tabIndex, Rectangle iconRect, Rectangle textRect,
                                               boolean isSelected) {
                // No pintar indicador de foco
            }

            @Override
            protected void paintText(Graphics g, int tabPlacement, Font font, FontMetrics metrics,
                                     int tabIndex, String title, Rectangle textRect, boolean isSelected) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

                g2.setFont(AppStyles.FONT_TAB);

                if (isSelected) {
                    g2.setColor(selectedColor);
                } else {
                    Rectangle bounds = getBoundsAt(tabIndex);
                    Point mousePos = getMousePosition();

                    if (mousePos != null && bounds.contains(mousePos)) {
                        g2.setColor(hoverColor);
                    } else {
                        g2.setColor(normalColor);
                    }
                }

                g2.drawString(title, textRect.x, textRect.y + metrics.getAscent());
                g2.dispose();
            }
        });
    }

    @Override
    public void addTab(String title, Component component) {
        super.addTab(title, null, component, null);
    }

    @Override
    public void addTab(String title, Icon icon, Component component) {
        super.addTab(title, icon, component, null);
    }
}
