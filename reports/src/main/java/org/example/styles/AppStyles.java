package org.example.styles;

import java.awt.*;

/**
 * Estilos para la aplicación
 */
public class AppStyles {

    // Fuentes
    public static final String FONT_FAMILY = "Segoe UI";
    public static final Font FONT_TITLE = new Font(FONT_FAMILY, Font.BOLD, 36);
    public static final Font FONT_SUBTITLE = new Font(FONT_FAMILY, Font.PLAIN, 16);
    public static final Font FONT_SECTION_TITLE = new Font(FONT_FAMILY, Font.BOLD, 20);
    public static final Font FONT_SUBSECTION = new Font(FONT_FAMILY, Font.BOLD, 12);
    public static final Font FONT_LABEL = new Font(FONT_FAMILY, Font.BOLD, 13);
    public static final Font FONT_INPUT = new Font(FONT_FAMILY, Font.PLAIN, 14);
    public static final Font FONT_BUTTON = new Font(FONT_FAMILY, Font.BOLD, 16);
    public static final Font FONT_BUTTON_SMALL = new Font(FONT_FAMILY, Font.BOLD, 13);
    public static final Font FONT_BADGE = new Font(FONT_FAMILY, Font.BOLD, 10);
    public static final Font FONT_TAB = new Font(FONT_FAMILY, Font.BOLD, 14);

    // Bordes redondeados
    public static final int BORDER_RADIUS_SMALL = 8;
    public static final int BORDER_RADIUS_MEDIUM = 12;
    public static final int BORDER_RADIUS_LARGE = 16;
    public static final int BORDER_RADIUS_XLARGE = 20;

    // Espaciado
    public static final int SPACING_TINY = 5;
    public static final int SPACING_SMALL = 10;
    public static final int SPACING_MEDIUM = 15;
    public static final int SPACING_LARGE = 20;
    public static final int SPACING_XLARGE = 25;
    public static final int SPACING_XXLARGE = 30;

    // Padding
    public static final Insets PADDING_SMALL = new Insets(8, 12, 8, 12);
    public static final Insets PADDING_MEDIUM = new Insets(12, 16, 12, 16);
    public static final Insets PADDING_LARGE = new Insets(18, 24, 18, 24);

    private AppStyles() {
        // Clase de utilidad, no instanciar
    }
}