package org.example.ui.panels;

import org.example.styles.AppColors;
import org.example.styles.AppStyles;
import org.example.service.ReporteService;
import org.example.ui.components.ComponentFactory;
import org.example.utils.DateUtils;
import org.example.utils.ValidationUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Panel para gestión de documentos de contratación
 */
public class ContratosPanel extends JPanel {

    // Campos de datos personales
    private JTextField txtColaborador;
    private JTextField txtCedula;
    private JTextField txtNombres;
    private JTextField txtApellidos;
    private JTextField txtDireccion;
    private JTextField txtCiudad;

    // Campos laborales
    private JTextField txtArea;
    private JTextField txtCargo;
    private JTextField txtReemplazo;
    private JTextField txtLugarTrabajo;
    private JTextField txtRecibidoPor;
    private JTextField txtResponsableDTH;

    // Campos de vistos buenos
    private JTextField txtAnalistaTH;
    private JTextField txtTrabajadoraSocial;
    private JTextField txtRemuneraciones;
    private JTextField txtSeguros;
    private JTextField txtSeguridad;
    private JTextField txtRiesgo;
    private JTextField txtCoordinadorCalidad;
    private JTextField txtDepartamentoMedico;
    private JTextField txtIngenieroCalidad;
    private JTextField txtTecnologia;
    private JTextField txtDistribucion;

    // Campos de fecha
    private JTextField txtDia;
    private JTextField txtMes;
    private JTextField txtAnio;
    private JTextField txtFecha;

    // Checkboxes de reportes
    private Map<String, JCheckBox> checkBoxReportes;

    public ContratosPanel() {
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
        contentPanel.add(createPersonalDataCard());
        contentPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        contentPanel.add(createReportsCard());

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

    private JPanel createPersonalDataCard() {
        JPanel card = ComponentFactory.createCardPanel("Información del Colaborador");
        card.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 25, 8, 25);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        int row = 1;

        // Datos personales
        addSectionSeparator(card, "DATOS PERSONALES", row++, gbc);
        txtColaborador = addLabeledField(card, "Nombre Completo", row++, gbc, true);
        txtNombres = addLabeledField(card, "Nombres", row++, gbc, true);
        txtApellidos = addLabeledField(card, "Apellidos", row++, gbc, true);
        txtCedula = addLabeledField(card, "Cédula", row++, gbc, true);
        txtDireccion = addLabeledField(card, "Dirección", row++, gbc, true);
        txtCiudad = addLabeledField(card, "Ciudad", row++, gbc, true);

        row++;
        addSectionSeparator(card, "INFORMACIÓN LABORAL", row++, gbc);
        txtCargo = addLabeledField(card, "Cargo", row++, gbc, true);
        txtArea = addLabeledField(card, "Área / Departamento", row++, gbc, true);
        txtLugarTrabajo = addLabeledField(card, "Lugar de Trabajo", row++, gbc, true);
        txtReemplazo = addLabeledField(card, "Reemplazo", row++, gbc, false);
        txtRecibidoPor = addLabeledField(card, "Recibido por", row++, gbc, true);
        txtResponsableDTH = addLabeledField(card, "Responsable DTH", row++, gbc, true);

        row++;
        addSectionSeparator(card, "VISTOS BUENOS / ÁREAS RESPONSABLES", row++, gbc);
        txtAnalistaTH = addLabeledField(card, "Analista de Talento Humano", row++, gbc, false);
        txtTrabajadoraSocial = addLabeledField(card, "Trabajador/a Social", row++, gbc, false);
        txtRemuneraciones = addLabeledField(card, "Analista de Remuneraciones", row++, gbc, false);
        txtSeguros = addLabeledField(card, "Jefe de Sección de Seguros", row++, gbc, false);
        txtSeguridad = addLabeledField(card, "Supervisor de Seguridad y Salud en el Trabajo", row++, gbc, false);
        txtRiesgo = addLabeledField(card, "Asistente de Seguridad y Salud en el Trabajo", row++, gbc, false);
        txtCoordinadorCalidad = addLabeledField(card, "Coordinador del Sistema de Evaluación de Desempeño", row++, gbc, false);
        txtDepartamentoMedico = addLabeledField(card, "Departamento Médico", row++, gbc, false);
        txtIngenieroCalidad = addLabeledField(card, "Ingeniero de Calidad", row++, gbc, false);
        txtTecnologia = addLabeledField(card, "Tecnología", row++, gbc, false);
        txtDistribucion = addLabeledField(card, "Superintendente de Reparaciones", row++, gbc, false);

        row++;
        addSectionSeparator(card, "FECHA DEL DOCUMENTO", row++, gbc);
        addDateFields(card, row++, gbc);
        txtFecha = addLabeledField(card, "Fecha Completa", row++, gbc, false);
        txtFecha.setEditable(true);
        txtFecha.setBackground(AppColors.INPUT_BG_DISABLED);

        initializeDates();
        setupDateSynchronization();

        return card;
    }

    private void addDateFields(JPanel panel, int row, GridBagConstraints gbc) {
        JPanel fechaPanel = new JPanel(new GridLayout(1, 3, 15, 0));
        fechaPanel.setOpaque(false);

        txtDia = ComponentFactory.createDateTextField();
        txtMes = ComponentFactory.createDateTextField();
        txtAnio = ComponentFactory.createDateTextField();

        fechaPanel.add(createLabeledPanel(txtDia, "Día"));
        fechaPanel.add(createLabeledPanel(txtMes, "Mes"));
        fechaPanel.add(createLabeledPanel(txtAnio, "Año"));

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        panel.add(fechaPanel, gbc);
        gbc.gridwidth = 1;
    }

    private JPanel createLabeledPanel(JTextField field, String labelText) {
        JPanel panel = new JPanel(new BorderLayout(0, 6));
        panel.setOpaque(false);

        JLabel label = new JLabel(labelText);
        label.setFont(AppStyles.FONT_SUBSECTION);
        label.setForeground(AppColors.TEXT_SECONDARY);

        panel.add(label, BorderLayout.NORTH);
        panel.add(field, BorderLayout.CENTER);

        return panel;
    }

    private void initializeDates() {
        txtDia.setText(DateUtils.getCurrentDay());
        txtMes.setText(DateUtils.getCurrentMonth());
        txtAnio.setText(DateUtils.getCurrentYear());
        txtFecha.setText(DateUtils.formatCurrentFullDate());
    }

    private void setupDateSynchronization() {
        javax.swing.event.DocumentListener listener = new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { updateFullDate(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { updateFullDate(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { updateFullDate(); }

            private void updateFullDate() {
                String dia = txtDia.getText().trim();
                String mes = txtMes.getText().trim();
                String anio = txtAnio.getText().trim();

                if (!dia.isEmpty() && !mes.isEmpty() && !anio.isEmpty()) {
                    txtFecha.setText(DateUtils.formatFullDate(dia, mes, anio));
                }
            }
        };

        txtDia.getDocument().addDocumentListener(listener);
        txtMes.getDocument().addDocumentListener(listener);
        txtAnio.getDocument().addDocumentListener(listener);
    }

    private JPanel createReportsCard() {
        JPanel card = ComponentFactory.createCardPanel("Selección de Documentos");
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

        checkBoxReportes = new LinkedHashMap<>();

        String[] reportes = {
                "Constancia de Entrega de Documentos",
                "Acta de Entrega de Cargo",
                "Acta de Recepción de Reglamento",
                "Autorización de Caución",
                "Carnet con Descuento",
                "Carnet sin Descuento",
                "Inducción Genérica"
        };

        String[] archivos = {
                "ConstanciaEntregaDocumentos.jrxml",
                "ActaEntregaDescriptivoCargo.jrxml",
                "ActaRecepcionReglamento.jrxml",
                "AutorizacionCaucion.jrxml",
                "CarnetIdentificacionConDescuento.jrxml",
                "CarnetIdentificacionSinDescuento.jrxml",
                "InduccionGenerica.jrxml"
        };

        JPanel checkboxContainer = new JPanel(new GridLayout(0, 2, 20, 15));
        checkboxContainer.setOpaque(false);
        checkboxContainer.setBorder(new EmptyBorder(10, 25, 25, 25));

        for (int i = 0; i < reportes.length; i++) {
            JCheckBox checkBox = ComponentFactory.createModernCheckBox(reportes[i]);
            checkBoxReportes.put(archivos[i], checkBox);
            checkboxContainer.add(checkBox);
        }

        card.add(checkboxContainer);
        card.add(createActionButtons());

        return card;
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
                checkBoxReportes.values().forEach(cb -> cb.setSelected(true)));

        btnClearAll.addActionListener(e ->
                checkBoxReportes.values().forEach(cb -> cb.setSelected(false)));

        panel.add(btnSelectAll);
        panel.add(btnClearAll);

        return panel;
    }

    private JPanel createFooter() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 25));
        panel.setOpaque(false);

        JButton btnGenerar = ComponentFactory.createPrimaryButton("GENERAR DOCUMENTOS");
        btnGenerar.addActionListener(e -> generateDocuments());

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

    private JTextField addLabeledField(JPanel panel, String labelText, int row,
                                       GridBagConstraints gbc, boolean required) {
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

    private void generateDocuments() {
        // Validar campos obligatorios
        if (!validateRequiredFields()) {
            return;
        }

        // Obtener reportes seleccionados
        List<String> selectedReports = getSelectedReports();
        if (selectedReports.isEmpty()) {
            showMessage(
                    "Debe seleccionar al menos un documento para generar",
                    "Selección Requerida",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        // Preparar parámetros
        Map<String, Object> params = buildParameters();

        // Generar documentos
        ReporteService.generarDocumentosSeleccionados(selectedReports, params);

        showMessage(
                "Se han generado " + selectedReports.size() + " documento(s) exitosamente ✓",
                "Generación Exitosa",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private boolean validateRequiredFields() {
        if (!ValidationUtils.isNotEmpty(txtColaborador) ||
                !ValidationUtils.isNotEmpty(txtCedula) ||
                !ValidationUtils.isNotEmpty(txtArea) ||
                !ValidationUtils.isNotEmpty(txtCargo) ||
                !ValidationUtils.isNotEmpty(txtLugarTrabajo) ||
                !ValidationUtils.isNotEmpty(txtRecibidoPor) ||
                !ValidationUtils.isNotEmpty(txtDia) ||
                !ValidationUtils.isNotEmpty(txtMes) ||
                !ValidationUtils.isNotEmpty(txtAnio) ||
                !ValidationUtils.isNotEmpty(txtNombres) ||
                !ValidationUtils.isNotEmpty(txtApellidos) ||
                !ValidationUtils.isNotEmpty(txtDireccion) ||
                !ValidationUtils.isNotEmpty(txtCiudad) ||
                !ValidationUtils.isNotEmpty(txtResponsableDTH)) {

            showMessage(
                    "Por favor complete todos los campos obligatorios marcados con *",
                    "Campos Requeridos",
                    JOptionPane.WARNING_MESSAGE
            );
            return false;
        }
        return true;
    }

    private List<String> getSelectedReports() {
        List<String> selected = new ArrayList<>();
        checkBoxReportes.forEach((archivo, checkBox) -> {
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
        params.put("area_departamento", txtArea.getText().trim());
        params.put("reemplazo", txtReemplazo.getText().trim());
        params.put("fecha_entrega_documentos", txtFecha.getText());
        params.put("direccion", txtDireccion.getText().trim());
        params.put("lugar_trabajo", txtLugarTrabajo.getText().trim());
        params.put("recibido_por", txtRecibidoPor.getText().trim());
        params.put("dia", txtDia.getText().trim());
        params.put("mes", txtMes.getText().trim());
        params.put("anio", txtAnio.getText().trim());
        params.put("ciudad_colaborador", txtCiudad.getText().trim());
        params.put("colaborador_nombres", txtNombres.getText().trim());
        params.put("colaborador_apellidos", txtApellidos.getText().trim());
        params.put("cargo", txtCargo.getText().trim());
        params.put("responsable_DTH", txtResponsableDTH.getText().trim());
        params.put("analista_th", txtAnalistaTH.getText().trim());
        params.put("trabajadora_social", txtTrabajadoraSocial.getText().trim());
        params.put("remuneraciones", txtRemuneraciones.getText().trim());
        params.put("seguros", txtSeguros.getText().trim());
        params.put("seguridad", txtSeguridad.getText().trim());
        params.put("riesgo", txtRiesgo.getText().trim());
        params.put("coordinador_calidad", txtCoordinadorCalidad.getText().trim());
        params.put("departamento_medico", txtDepartamentoMedico.getText().trim());
        params.put("ingeniero_calidad", txtIngenieroCalidad.getText().trim());
        params.put("tecnologia", txtTecnologia.getText().trim());
        params.put("distribucion", txtDistribucion.getText().trim());
        return params;
    }

    private void showMessage(String message, String title, int type) {
        UIManager.put("OptionPane.background", AppColors.CARD_COLOR);
        UIManager.put("Panel.background", AppColors.CARD_COLOR);
        UIManager.put("OptionPane.messageForeground", AppColors.TEXT_PRIMARY);
        JOptionPane.showMessageDialog(this, message, title, type);
    }
}
