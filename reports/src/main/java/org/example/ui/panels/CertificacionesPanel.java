package org.example.ui.panels;

import org.example.styles.AppColors;
import org.example.styles.AppStyles;
import org.example.service.ReporteService;
import org.example.ui.components.ComponentFactory;
import org.example.utils.DatePickerField;
import org.example.utils.DateUtils;
import org.example.utils.ValidationUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Panel para gestión de certificaciones
 */
public class CertificacionesPanel extends JPanel {

    // Campos del colaborador
    private JTextField txtColaborador;
    private JTextField txtCedula;
    private JTextField txtNumeroExpediente;
    private JTextField txtDireccion;
    private JTextField txtTelefono;
    private JTextField txtCorreo;
    private JTextField txtEdad;
    private JTextField txtProvincia;
    private JTextField txtCuidad;

    // Campos de fecha
    private DatePickerField fecha_emision;
    private DatePickerField fecha_revision;
    private DatePickerField fecha_solicitud;
    private DatePickerField fecha_aceptada;
    private DatePickerField fecha_recepcion;
    private DatePickerField fecha_ultima_revision;
    private DatePickerField fecha_entrega_documentos;

    // Checkboxes de certificaciones
    private Map<String, JCheckBox> checkBoxCertificaciones;

    public CertificacionesPanel() {
        setLayout(new BorderLayout());
        setOpaque(false);
        initComponents();
    }

    private void initComponents() {
        // Panel principal con scroll
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setOpaque(false);
        contentPanel.setBorder(new EmptyBorder(20, 0, 20, 0));

        // Agregar cards
        contentPanel.add(createEmployeeDataCard());
        contentPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        contentPanel.add(createCertificationsCard());

        // Scroll pane
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBorder(null);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        add(scrollPane, BorderLayout.CENTER);
        add(createFooter(), BorderLayout.SOUTH);
    }

    private JPanel createEmployeeDataCard() {
        JPanel card = ComponentFactory.createCardPanel("Datos del Colaborador");
        card.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 25, 8, 25);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        int row = 1;

        // Información básica
        addSectionSeparator(card, "INFORMACIÓN BÁSICA", row++, gbc);
        txtColaborador = addLabeledField(card, "Nombre Completo", row++, gbc, true);
        txtCedula = addLabeledField(card, "Cédula", row++, gbc, true);
        txtNumeroExpediente = addLabeledField(card, "Número de Expediente", row++, gbc, true);
        txtDireccion= addLabeledField(card, "Dirección", row++, gbc, true);
        txtTelefono = addLabeledField(card, "Teléfono", row++, gbc, false);
        txtCorreo = addLabeledField(card, "Correo Electrónico", row++, gbc, false);

        row++;
        addSectionSeparator(card, "INFORMACIÓN ADICIONAL", row++, gbc);
        txtEdad = addLabeledField(card, "Edad - Años Cumplidos", row++, gbc, false);
        txtProvincia = addLabeledField(card, "Provincia", row++, gbc, false);
        txtCuidad = addLabeledField(card, "Cuidad (Parroquia)", row++, gbc, false);

        row++;
        addSectionSeparator(card, "FECHAS", row++, gbc);

        fecha_entrega_documentos = addLabeledDateField(card, "Fecha", row++, gbc, false);
        fecha_emision = addLabeledDateField(card, "Fecha Emisión", row++, gbc, false);
        fecha_revision = addLabeledDateField(card, "Fecha Revisión", row++, gbc, false);
        fecha_solicitud = addLabeledDateField(card, "Fecha Solicitud", row++, gbc, false);
        fecha_aceptada = addLabeledDateField(card, "Fecha Aceptada", row++, gbc, false);
        fecha_recepcion = addLabeledDateField(card, "Fecha Recepción", row++, gbc, false);
        fecha_ultima_revision = addLabeledDateField(card, "Fecha Ultima Revisión", row++, gbc, false);

        initializeDates();

        return card;
    }

    private void initializeDates() {
        java.util.Date currentDate = new java.util.Date();
        fecha_entrega_documentos.setDate(currentDate);
        fecha_emision.setDate(currentDate);
        fecha_revision.setDate(currentDate);
        fecha_solicitud.setDate(currentDate);
        fecha_aceptada.setDate(currentDate);
        fecha_recepcion.setDate(currentDate);
        fecha_ultima_revision.setDate(currentDate);
    }

    private JPanel createCertificationsCard() {
        JPanel card = ComponentFactory.createCardPanel("Tipo de Certificación");
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

        checkBoxCertificaciones = new LinkedHashMap<>();

        String[] certificaciones = {
                "LISTA DE VERIFICACIÓN DEL PROCESO",
                "ACUERDO DE CUMPLIMIENTO PARA PERSONAS",
                "SOLICITUD PARA LA CERTIFICACIÓN DE PERSONAS"
        };

        String[] archivos = {
                "C01ListaVerificacionProceso.jrxml",
                "C02SolicitudCertificacionPersona.jrxml",
                "C09AcuerdoCumplimiento.jrxml"
        };

        JPanel checkboxContainer = new JPanel();
        checkboxContainer.setLayout(new BoxLayout(checkboxContainer, BoxLayout.Y_AXIS));
        checkboxContainer.setOpaque(false);
        checkboxContainer.setBorder(new EmptyBorder(10, 25, 25, 25));

        for (int i = 0; i < certificaciones.length; i++) {
            JCheckBox checkBox = ComponentFactory.createModernCheckBox(certificaciones[i]);
            checkBox.setSelected(false); // Por defecto no seleccionados
            checkBoxCertificaciones.put(archivos[i], checkBox);
            checkboxContainer.add(checkBox);

            if (i < certificaciones.length - 1) {
                checkboxContainer.add(Box.createRigidArea(new Dimension(0, 10)));
            }
        }

        card.add(checkboxContainer);
        card.add(createActionButtons());

        // Agregar nota informativa
        card.add(createInfoNote());

        return card;
    }

    private JPanel createInfoNote() {
        JPanel notePanel = new JPanel(new BorderLayout(10, 0));
        notePanel.setOpaque(false);
        notePanel.setBorder(new EmptyBorder(10, 25, 20, 25));
        notePanel.setBackground(new Color(239, 246, 255));

        JLabel iconLabel = new JLabel("ℹ");
        iconLabel.setFont(new Font(AppStyles.FONT_FAMILY, Font.BOLD, 18));
        iconLabel.setForeground(AppColors.PRIMARY_COLOR);

        JLabel noteLabel = new JLabel("<html><i>Seleccione el tipo de certificación según sus necesidades. " +
                "Puede generar múltiples certificados a la vez.</i></html>");
        noteLabel.setFont(new Font(AppStyles.FONT_FAMILY, Font.ITALIC, 12));
        noteLabel.setForeground(AppColors.TEXT_SECONDARY);

        notePanel.add(iconLabel, BorderLayout.WEST);
        notePanel.add(noteLabel, BorderLayout.CENTER);

        return notePanel;
    }

    private JPanel createActionButtons() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 20));
        panel.setOpaque(false);
        panel.setBorder(new EmptyBorder(5, 25, 20, 25));

        JButton btnSelectAll = ComponentFactory.createSecondaryButton(
                "Seleccionar Todos",
                AppColors.SUCCESS_COLOR,
                AppColors.SUCCESS_HOVER
        );

        JButton btnClearAll = ComponentFactory.createSecondaryButton(
                "Limpiar Selección",
                AppColors.WARNING_COLOR,
                AppColors.WARNING_HOVER
        );

        btnSelectAll.addActionListener(e ->
                checkBoxCertificaciones.values().forEach(cb -> cb.setSelected(true)));

        btnClearAll.addActionListener(e ->
                checkBoxCertificaciones.values().forEach(cb -> cb.setSelected(false)));

        panel.add(btnSelectAll);
        panel.add(btnClearAll);

        return panel;
    }

    private JPanel createFooter() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 25));
        panel.setOpaque(false);

        JButton btnGenerar = ComponentFactory.createPrimaryButton("GENERAR CERTIFICACIONES");
        btnGenerar.addActionListener(e -> generateCertifications());

        panel.add(btnGenerar);
        return panel;
    }

    private void addSectionSeparator(JPanel panel, String text, int row, GridBagConstraints gbc) {
        JPanel separatorPanel = new JPanel(new BorderLayout(15, 0));
        separatorPanel.setOpaque(false);
        separatorPanel.setBorder(new EmptyBorder(20, 0, 15, 0));

        JLabel label = new JLabel(text);
        label.setFont(AppStyles.FONT_SUBSECTION);
        label.setForeground(AppColors.PRIMARY_COLOR);
        label.setPreferredSize(new Dimension(280, 20));

        JSeparator separator = new JSeparator();
        separator.setForeground(AppColors.BORDER_COLOR);
        separator.setBackground(AppColors.BORDER_COLOR);

        separatorPanel.add(label, BorderLayout.WEST);
        separatorPanel.add(separator, BorderLayout.CENTER);

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        panel.add(separatorPanel, gbc);
        gbc.gridwidth = 1;
    }

    private JTextField addLabeledField(JPanel panel, String labelText, int row, GridBagConstraints gbc, boolean required) {
        JLabel label = new JLabel(labelText + (required ? " *" : ""));
        label.setFont(AppStyles.FONT_LABEL);
        label.setForeground(AppColors.TEXT_PRIMARY);

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 1;
        gbc.weightx = 0.4;
        panel.add(label, gbc);

        JTextField textField = ComponentFactory.createModernTextField();
        gbc.gridx = 1;
        gbc.weightx = 0.6;
        panel.add(textField, gbc);

        return textField;
    }

    private DatePickerField addLabeledDateField(JPanel panel, String labelText, int row, GridBagConstraints gbc, boolean required) {
        JLabel label = new JLabel(labelText + (required ? " *" : ""));
        label.setFont(AppStyles.FONT_LABEL);
        label.setForeground(AppColors.TEXT_PRIMARY);

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 1;
        gbc.weightx = 0.4;
        panel.add(label, gbc);

        DatePickerField dateField = ComponentFactory.createDatePickerField();
        gbc.gridx = 1;
        gbc.weightx = 0.6;
        panel.add(dateField, gbc);

        return dateField;
    }

    private void generateCertifications() {
        // Validar campos obligatorios
        if (!validateRequiredFields()) {
            return;
        }

        // Obtener certificaciones seleccionadas
        List<String> selectedCerts = getSelectedCertifications();
        if (selectedCerts.isEmpty()) {
            showMessage(
                    "Debe seleccionar al menos una certificación para generar",
                    "Selección Requerida",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        // Preparar parámetros
        Map<String, Object> params = buildParameters();

        // Generar certificaciones
        ReporteService.generarDocumentosSeleccionados(selectedCerts, params);

        showMessage(
                "Se han generado " + selectedCerts.size() + " certificación(es) exitosamente ✓",
                "Generación Exitosa",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private boolean validateRequiredFields() {
        if (!ValidationUtils.isNotEmpty(txtColaborador) ||
                !ValidationUtils.isNotEmpty(txtCedula) ||
                !ValidationUtils.isNotEmpty(txtDireccion) ||
                !ValidationUtils.isNotEmpty(txtTelefono) ||
                !ValidationUtils.isNotEmpty(txtCorreo) ||
                !ValidationUtils.isNotEmpty(txtEdad) ||
                !ValidationUtils.isNotEmpty(txtProvincia) ||
                !ValidationUtils.isNotEmpty(txtCuidad)) {

            showMessage(
                    "Por favor complete todos los campos obligatorios marcados con *",
                    "Campos Requeridos",
                    JOptionPane.WARNING_MESSAGE
            );
            return false;
        }
        return true;
    }

    private List<String> getSelectedCertifications() {
        List<String> selected = new ArrayList<>();
        checkBoxCertificaciones.forEach((archivo, checkBox) -> {
            if (checkBox.isSelected()) {
                selected.add(archivo);
            }
        });
        return selected;
    }

    private Map<String, Object> buildParameters() {
        Map<String, Object> params = new HashMap<>();

        params.put("colaborador", txtColaborador.getText().trim());
        params.put("cedula", txtCedula.getText().trim());
        params.put("numero_expediente", txtNumeroExpediente.getText().trim());
        params.put("direccion", txtDireccion.getText().trim());
        params.put("telefono", txtTelefono.getText().trim());
        params.put("correo", txtCorreo.getText().trim());
        params.put("edad", txtEdad.getText().trim());
        params.put("provincia", txtProvincia.getText().trim());
        params.put("cuidad", txtCuidad.getText().trim());

        // Obtener fechas como String en formato DD-MM-YYYY
        params.put("fecha_emision", fecha_emision.getDateAsString());
        params.put("fecha_revision", fecha_revision.getDateAsString());
        params.put("fecha_solicitud", fecha_solicitud.getDateAsString());
        params.put("fecha_aceptada", fecha_aceptada.getDateAsString());
        params.put("fecha_recepcion",fecha_recepcion.getDateAsString());
        params.put("fecha_ultima_revision", fecha_ultima_revision.getDateAsString());
        params.put("fecha_entrega_documentos", fecha_entrega_documentos.getDateAsString());

        return params;
    }

    private void showMessage(String message, String title, int type) {
        UIManager.put("OptionPane.background", AppColors.CARD_COLOR);
        UIManager.put("Panel.background", AppColors.CARD_COLOR);
        UIManager.put("OptionPane.messageForeground", AppColors.TEXT_PRIMARY);
        JOptionPane.showMessageDialog(this, message, title, type);
    }
}