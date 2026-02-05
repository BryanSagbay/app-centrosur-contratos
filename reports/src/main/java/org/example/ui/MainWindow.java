package org.example.ui;

import org.example.styles.AppColors;
import org.example.styles.AppStyles;
import org.example.ui.components.ComponentFactory;
import org.example.ui.components.ModernTabbedPane;
import org.example.ui.panels.CertificacionesPanel;
import org.example.ui.panels.ContratosPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URL;

/**
 * Ventana principal de la aplicación con sistema de pestañas
 */
public class MainWindow extends JFrame {

    public MainWindow() {
        initializeWindow();
        initializeComponents();
    }

    private void initializeWindow() {
        setTitle("Sistema de Gestión Documental");
        setSize(1000, 900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setMinimumSize(new Dimension(900, 800));
    }

    private void initializeComponents() {
        // Panel principal con gradiente
        JPanel mainPanel = createGradientPanel();
        mainPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
        mainPanel.setLayout(new BorderLayout());

        // Header
        JPanel headerPanel = createHeader();
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Tabs con los paneles
        ModernTabbedPane tabbedPane = createTabbedPane();
        mainPanel.add(tabbedPane, BorderLayout.CENTER);

        add(mainPanel);
    }

    private JPanel createGradientPanel() {
        return new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

                int w = getWidth();
                int h = getHeight();

                GradientPaint gp = new GradientPaint(
                        0, 0, AppColors.BACKGROUND_START,
                        0, h, AppColors.BACKGROUND_END
                );

                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
            }
        };
    }

    private JPanel createHeader() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        panel.setBorder(new EmptyBorder(0, 0, 25, 0));

        // Logo
        Icon logoCENTROSUR = loadIcon("icons/logo.png", 200, 35);
        JLabel logoLabel = new JLabel(logoCENTROSUR);
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Título
        JLabel titulo = new JLabel("Sistema de Talento Humano");
        titulo.setFont(AppStyles.FONT_TITLE);
        titulo.setForeground(AppColors.TEXT_PRIMARY);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Subtítulo
        JLabel subtitulo = new JLabel("Gestión de contratos y certificaciones de manera optimizada");
        subtitulo.setFont(AppStyles.FONT_SUBTITLE);
        subtitulo.setForeground(AppColors.TEXT_SECONDARY);
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Badge
        JPanel badgePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        badgePanel.setOpaque(false);
        JLabel badge = ComponentFactory.createBadge("VERSIÓN 2.0");
        badgePanel.add(badge);

        // Agregar componentes en orden vertical
        panel.add(logoLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(titulo);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(subtitulo);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        panel.add(badgePanel);

        return panel;
    }

    private ModernTabbedPane createTabbedPane() {
        ModernTabbedPane tabbedPane = new ModernTabbedPane();

        // Cargar los iconos con tamaño específico (16x16 o 20x20 son tamaños comunes para tabs)
        Icon certificadoIcon = loadIcon("icons/certificado.png", 28, 28);
        Icon contratoIcon    = loadIcon("icons/contrato.png", 28, 28);

        // Tab de Contratos
        ContratosPanel contratosPanel = new ContratosPanel();
        tabbedPane.addTab("Proceso de Contratación", contratoIcon, contratosPanel);

        // Tab de Certificaciones
        CertificacionesPanel certificacionesPanel = new CertificacionesPanel();
        tabbedPane.addTab("Certificaciones", certificadoIcon, certificacionesPanel);

        return tabbedPane;
    }

    private Icon loadIcon(String path, int width, int height) {
        URL url = MainWindow.class.getClassLoader().getResource(path);
        if (url == null) {
            throw new IllegalStateException("No se encontró el recurso: " + path);
        }
        ImageIcon originalIcon = new ImageIcon(url);
        Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

    /**
     * Muestra la ventana principal
     */
    public void display() {
        setVisible(true);
    }
}