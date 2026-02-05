package org.example.styles;

import java.awt.*;

/**
 * Colores para la aplicación
 * Paleta de colores moderna basada en Tailwind CSS
 */
public class AppColors {

    // Colores primarios
    public static final Color PRIMARY_COLOR = new Color(59, 130, 246); // Blue-500
    public static final Color PRIMARY_HOVER = new Color(37, 99, 235); // Blue-600
    public static final Color PRIMARY_PRESSED = new Color(29, 78, 216); // Blue-700

    // Colores secundarios
    public static final Color SECONDARY_COLOR = new Color(99, 102, 241); // Indigo-500
    public static final Color SECONDARY_HOVER = new Color(79, 70, 229); // Indigo-600

    // Colores de estado
    public static final Color SUCCESS_COLOR = new Color(34, 197, 94); // Green-500
    public static final Color SUCCESS_HOVER = new Color(22, 163, 74); // Green-600
    public static final Color WARNING_COLOR = new Color(249, 115, 22); // Orange-500
    public static final Color WARNING_HOVER = new Color(234, 88, 12); // Orange-600
    public static final Color ERROR_COLOR = new Color(239, 68, 68); // Red-500
    public static final Color ERROR_HOVER = new Color(220, 38, 38); // Red-600

    // Colores de fondo
    public static final Color BACKGROUND_START = new Color(249, 250, 251); // Gray-50
    public static final Color BACKGROUND_END = new Color(243, 244, 246); // Gray-100
    public static final Color CARD_COLOR = Color.WHITE;

    // Colores de texto
    public static final Color TEXT_PRIMARY = new Color(17, 24, 39); // Gray-900
    public static final Color TEXT_SECONDARY = new Color(107, 114, 128); // Gray-500
    public static final Color TEXT_TERTIARY = new Color(156, 163, 175); // Gray-400

    // Colores de bordes
    public static final Color BORDER_COLOR = new Color(229, 231, 235); // Gray-200
    public static final Color FOCUS_BORDER = new Color(147, 197, 253); // Blue-300

    // Colores de input
    public static final Color INPUT_BG_FOCUS = new Color(239, 246, 255); // Blue-50
    public static final Color INPUT_BG_DISABLED = new Color(249, 250, 251); // Gray-50

    // Colores de tabs
    public static final Color TAB_SELECTED = PRIMARY_COLOR;
    public static final Color TAB_UNSELECTED = TEXT_SECONDARY;
    public static final Color TAB_HOVER = PRIMARY_HOVER;

    private AppColors() {
        // Clase de utilidad, no instanciar
    }
}