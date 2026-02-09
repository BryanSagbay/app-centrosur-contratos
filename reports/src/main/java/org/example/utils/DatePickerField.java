package org.example.utils;

import com.toedter.calendar.JDateChooser;
import org.example.styles.AppColors;
import org.example.styles.AppStyles;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Campo de fecha personalizado con calendario desplegable
 */
public class DatePickerField extends JPanel {
    private JDateChooser dateChooser;
    private static final String DATE_FORMAT = "dd-MM-yyyy";

    public DatePickerField() {
        setLayout(new BorderLayout());
        setOpaque(false);
        initializeComponents();
    }

    private void initializeComponents() {
        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString(DATE_FORMAT);
        dateChooser.setFont(AppStyles.FONT_INPUT);

        // Configurar el campo de texto del date chooser
        JTextField textField = (JTextField) dateChooser.getDateEditor().getUiComponent();
        textField.setFont(AppStyles.FONT_INPUT);
        textField.setBackground(AppColors.INPUT_BG_DISABLED);
        textField.setForeground(AppColors.TEXT_PRIMARY);
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(AppColors.BORDER_COLOR, 1),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        textField.setEditable(true); // Permite escribir manualmente

        // Configurar el botón del calendario con ICONO PNG
        JButton calendarButton = (JButton) dateChooser.getCalendarButton();

        try {
            // Cargar el icono PNG desde resources
            ImageIcon icon = new ImageIcon(getClass().getResource("/icons/calendario.png"));

            // Redimensionar el icono (ajusta el tamaño según necesites)
            Image img = icon.getImage().getScaledInstance(22, 22, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(img);

            calendarButton.setIcon(scaledIcon);
            calendarButton.setText(""); // Quitar el texto
        } catch (Exception e) {
            // Si no encuentra el icono, usar texto como respaldo
            calendarButton.setText("📅");
            System.err.println("No se pudo cargar el icono del calendario: " + e.getMessage());
        }

        calendarButton.setBackground(AppColors.INPUT_BG_FOCUS);
        calendarButton.setForeground(Color.WHITE);
        calendarButton.setBorderPainted(false);
        calendarButton.setFocusPainted(false);
        calendarButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        calendarButton.setPreferredSize(new Dimension(40, 35));

        add(dateChooser, BorderLayout.CENTER);
    }

    /**
     * Establece la fecha
     */
    public void setDate(Date date) {
        dateChooser.setDate(date);
    }

    /**
     * Obtiene la fecha seleccionada
     */
    public Date getDate() {
        return dateChooser.getDate();
    }

    /**
     * Obtiene la fecha como String en formato DD-MM-YYYY
     */
    public String getDateAsString() {
        Date date = dateChooser.getDate();
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        return sdf.format(date);
    }

    /**
     * Establece la fecha desde un String en formato DD-MM-YYYY
     */
    public void setDateFromString(String dateString) {
        if (dateString == null || dateString.trim().isEmpty()) {
            dateChooser.setDate(null);
            return;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            Date date = sdf.parse(dateString);
            dateChooser.setDate(date);
        } catch (Exception e) {
            dateChooser.setDate(null);
        }
    }

    /**
     * Limpia el campo de fecha
     */
    public void clear() {
        dateChooser.setDate(null);
    }

    /**
     * Habilita o deshabilita el componente
     */
    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        dateChooser.setEnabled(enabled);
    }

    /**
     * Obtiene el JDateChooser interno para configuraciones adicionales
     */
    public JDateChooser getDateChooser() {
        return dateChooser;
    }
}