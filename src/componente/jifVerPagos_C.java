/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componente;

import controlador.BLAgricultor;
import controlador.BLPagos;
import controlador.BLUsuario;
import entidad.Agricultor;
import entidad.Pago;
import entidad.Usuario;
import java.awt.event.KeyEvent;
import java.util.Date;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import utilitario.CloseDialogEscape;
import utilitario.Funciones;

/**
 *
 * @author Richard
 */
public class jifVerPagos_C extends javax.swing.JInternalFrame {

    int idPago = 0;

    public jifVerPagos_C() {
        initComponents();
        formatear_estructura_todas_tablas();
        getcombo_cliente_all();
        limpiarformularioPagar();
    }

    private void formatear_estructura_todas_tablas() {
        ((DefaultTableCellRenderer) jtVerPagos.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        jtVerPagos.setRowHeight(22);
        jtVerPagos.getColumnModel().getColumn(0).setMaxWidth(0);
        jtVerPagos.getColumnModel().getColumn(0).setMinWidth(0);
        jtVerPagos.getColumnModel().getColumn(0).setPreferredWidth(0);
        jtVerPagos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void limpiarformularioPagar() {
        txtMontoTotal_Pago.setText("");
        txtVoucher_RegistrarPago.setText("");
        txtMonto_Pago.setText("");
        txtObservacion_RegistrarPagos.setText("");
    }

    private void getcombo_cliente_all() {
        cboFiltroAgricultor_VerPagos.removeAllItems();
        for (Agricultor c : new BLAgricultor().get_agricultores_byActivos("")) {
            cboFiltroAgricultor_VerPagos.addItem(c);
        }
        AutoCompleteDecorator.decorate(cboFiltroAgricultor_VerPagos);
    }

    private void gettabla_verpagos_byAgricultor(String dni, int id, int estado) {
        DefaultTableModel temp = (DefaultTableModel) jtVerPagos.getModel();
        temp.setRowCount(0);
        for (Pago p : new BLPagos().get_pagos_bycliente(dni, id, estado)) {
            Object[] datos = {p.getInt_id(), p.getVar_cuenta(), p.getVar_descripcion(), p.getDat_fechregistro(),
                p.getDec_monto(), p.getDec_amortizacion(), p.getDec_saldo(), p.getInt_estado()};
            temp.addRow(datos);
        }
    }

    private void Anular_Pagos(int idempleado) {
        try {
            idPago = Integer.parseInt(jtVerPagos.getValueAt(jtVerPagos.getSelectedRow(), 0).toString());
            if (new BLPagos().AnularPago(idPago, txtObservacion_AnularPago.getText(), idempleado)) {
                JOptionPane.showMessageDialog(null, "Se Anulo Correctamente", "MENSAJE", JOptionPane.INFORMATION_MESSAGE);

            } else {
                JOptionPane.showMessageDialog(null, "Error al Anular", "MENSAJE", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            System.out.println("Error de aNULACION");
            e.printStackTrace();
        }
    }

    private void Pagar() {
        double montototal = Double.parseDouble(txtMontoTotal_Pago.getText());
        double monto = Double.parseDouble(txtMonto_Pago.getText());
        if (monto <= montototal) {
            Pago p = new Pago();
            p.setInt_id(idPago);
            p.setVar_boucherpago(txtVoucher_RegistrarPago.getText());
            p.setVar_observacion(txtObservacion_RegistrarPagos.getText());
            p.setDec_monto(Double.parseDouble(txtMonto_Pago.getText()));
            if (new BLPagos().RegistrarPagos(p)) {
                JOptionPane.showMessageDialog(null, "Registro Exitoso", "MENSAJE", JOptionPane.INFORMATION_MESSAGE);
                limpiarformularioPagar();
                if (jrbDni_VerPagos.isSelected()) {
                    gettabla_verpagos_byAgricultor(txtFiltroDni_VerPagos.getText(), 0, 1);
                }
                if (jrbAgricultor_VerPagos.isSelected()) {
                    int id = ((Agricultor) cboFiltroAgricultor_VerPagos.getSelectedItem()).getInt_id();
                    gettabla_verpagos_byAgricultor("", id, 1);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error al Registrar", "MENSAJE", JOptionPane.ERROR_MESSAGE);
                limpiarformularioPagar();
            }
        } else {
            JOptionPane.showMessageDialog(null, "El Monto Ingresado Debe Ser Menor que el Saldo", "MENSAJE", JOptionPane.ERROR_MESSAGE);
            limpiarformularioPagar();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpmVerPagos = new javax.swing.JPopupMenu();
        jmip_Pagar = new javax.swing.JMenuItem();
        jmip_GenerarDocumento = new javax.swing.JMenuItem();
        jmip_Anular = new javax.swing.JMenuItem();
        jdPagar = new javax.swing.JDialog();
        jPanel9 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        txtFecha_RegistrarPagos = new com.toedter.calendar.JDateChooser();
        txtVoucher_RegistrarPago = new javax.swing.JTextField();
        jScrollPane19 = new javax.swing.JScrollPane();
        txtObservacion_RegistrarPagos = new javax.swing.JTextArea();
        btn_Cancelar_Pago = new javax.swing.JButton();
        btn_Guardar_pago = new javax.swing.JButton();
        jLabel88 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        txtMontoTotal_Pago = new javax.swing.JTextField();
        jLabel131 = new javax.swing.JLabel();
        txtMonto_Pago = new org.jdesktop.swingx.JXTextField();
        jdValidacion_Anular = new javax.swing.JDialog();
        jPanel40 = new javax.swing.JPanel();
        jLabel118 = new javax.swing.JLabel();
        btn_validar_anulacion = new javax.swing.JButton();
        jScrollPane29 = new javax.swing.JScrollPane();
        txtObservacion_AnularPago = new javax.swing.JTextArea();
        jLabel92 = new javax.swing.JLabel();
        txtValidacionPass_Anular = new javax.swing.JPasswordField();
        jdValidacion_Pago = new javax.swing.JDialog();
        jPanel21 = new javax.swing.JPanel();
        jLabel64 = new javax.swing.JLabel();
        txtValidacionPass_Pagos = new javax.swing.JPasswordField();
        btn_validar_pago = new javax.swing.JButton();
        rb_group = new javax.swing.ButtonGroup();
        jPanel8 = new javax.swing.JPanel();
        jLabel84 = new javax.swing.JLabel();
        jpVerPagos = new javax.swing.JPanel();
        cboFiltroAgricultor_VerPagos = new org.jdesktop.swingx.JXComboBox();
        txtFiltroDni_VerPagos = new javax.swing.JTextField();
        btn_buscar_pagos = new javax.swing.JButton();
        btn_Imprimir_pagos = new javax.swing.JButton();
        jrbDni_VerPagos = new javax.swing.JRadioButton();
        jrbAgricultor_VerPagos = new javax.swing.JRadioButton();
        jLabel86 = new javax.swing.JLabel();
        cboEstado_VerPagos = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtVerPagos = new javax.swing.JTable();

        jmip_Pagar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jmip_Pagar.setText("Realizar Pago");
        jmip_Pagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmip_PagarActionPerformed(evt);
            }
        });
        jpmVerPagos.add(jmip_Pagar);

        jmip_GenerarDocumento.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jmip_GenerarDocumento.setText("Ver Documento");
        jmip_GenerarDocumento.setToolTipText("");
        jpmVerPagos.add(jmip_GenerarDocumento);

        jmip_Anular.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jmip_Anular.setText("Anular");
        jmip_Anular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmip_AnularActionPerformed(evt);
            }
        });
        jpmVerPagos.add(jmip_Anular);

        jdPagar.setResizable(false);

        jPanel9.setBackground(new java.awt.Color(195, 233, 164));

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel45.setText("Fecha:");

        jLabel85.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel85.setText("Boucher:");

        jLabel87.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel87.setText("Observacion:");

        txtFecha_RegistrarPagos.setEnabled(false);

        txtObservacion_RegistrarPagos.setColumns(20);
        txtObservacion_RegistrarPagos.setRows(5);
        txtObservacion_RegistrarPagos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtObservacion_RegistrarPagosKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtObservacion_RegistrarPagosKeyTyped(evt);
            }
        });
        jScrollPane19.setViewportView(txtObservacion_RegistrarPagos);

        btn_Cancelar_Pago.setBackground(new java.awt.Color(255, 51, 0));
        btn_Cancelar_Pago.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_Cancelar_Pago.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/cancelar.png"))); // NOI18N
        btn_Cancelar_Pago.setText("Cancelar");
        btn_Cancelar_Pago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Cancelar_PagoActionPerformed(evt);
            }
        });

        btn_Guardar_pago.setBackground(new java.awt.Color(255, 51, 0));
        btn_Guardar_pago.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_Guardar_pago.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/Save.png"))); // NOI18N
        btn_Guardar_pago.setText("Guardar");
        btn_Guardar_pago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Guardar_pagoActionPerformed(evt);
            }
        });

        jLabel88.setBackground(new java.awt.Color(0, 153, 153));
        jLabel88.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel88.setForeground(new java.awt.Color(255, 255, 255));
        jLabel88.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel88.setText("REALIZAR PAGOS");
        jLabel88.setOpaque(true);

        jLabel91.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel91.setText("Saldo:");

        txtMontoTotal_Pago.setEnabled(false);

        jLabel131.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel131.setText("Monto:");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel88, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel45)
                    .addComponent(jLabel91)
                    .addComponent(jLabel131)
                    .addComponent(jLabel85)
                    .addComponent(jLabel87))
                .addGap(42, 42, 42)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(btn_Guardar_pago)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_Cancelar_Pago))
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtFecha_RegistrarPagos, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                                .addComponent(txtMontoTotal_Pago))
                            .addComponent(txtVoucher_RegistrarPago, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(txtMonto_Pago, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel45)
                    .addComponent(txtFecha_RegistrarPagos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel91)
                    .addComponent(txtMontoTotal_Pago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel85)
                    .addComponent(txtVoucher_RegistrarPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel131)
                    .addComponent(txtMonto_Pago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel87))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Cancelar_Pago, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Guardar_pago, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jdPagarLayout = new javax.swing.GroupLayout(jdPagar.getContentPane());
        jdPagar.getContentPane().setLayout(jdPagarLayout);
        jdPagarLayout.setHorizontalGroup(
            jdPagarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jdPagarLayout.setVerticalGroup(
            jdPagarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jdValidacion_Anular.setTitle("Validar Usuario");
        jdValidacion_Anular.setResizable(false);

        jPanel40.setBackground(new java.awt.Color(195, 233, 164));

        jLabel118.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel118.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel118.setText("Ingrese Observacion:");

        btn_validar_anulacion.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_validar_anulacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/key_validation.png"))); // NOI18N
        btn_validar_anulacion.setText("OK");
        btn_validar_anulacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_validar_anulacionActionPerformed(evt);
            }
        });

        txtObservacion_AnularPago.setColumns(20);
        txtObservacion_AnularPago.setLineWrap(true);
        txtObservacion_AnularPago.setRows(5);
        txtObservacion_AnularPago.setWrapStyleWord(true);
        txtObservacion_AnularPago.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtObservacion_AnularPagoKeyPressed(evt);
            }
        });
        jScrollPane29.setViewportView(txtObservacion_AnularPago);

        jLabel92.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel92.setText("Ingrese Clave:");

        txtValidacionPass_Anular.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtValidacionPass_Anular.setForeground(new java.awt.Color(255, 153, 51));
        txtValidacionPass_Anular.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel40Layout = new javax.swing.GroupLayout(jPanel40);
        jPanel40.setLayout(jPanel40Layout);
        jPanel40Layout.setHorizontalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel40Layout.createSequentialGroup()
                        .addComponent(jLabel92)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel40Layout.createSequentialGroup()
                        .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane29)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel40Layout.createSequentialGroup()
                                .addComponent(txtValidacionPass_Anular, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_validar_anulacion))
                            .addComponent(jLabel118, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 20, Short.MAX_VALUE))))
        );
        jPanel40Layout.setVerticalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel118, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane29, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel92)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_validar_anulacion)
                    .addComponent(txtValidacionPass_Anular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jdValidacion_AnularLayout = new javax.swing.GroupLayout(jdValidacion_Anular.getContentPane());
        jdValidacion_Anular.getContentPane().setLayout(jdValidacion_AnularLayout);
        jdValidacion_AnularLayout.setHorizontalGroup(
            jdValidacion_AnularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jdValidacion_AnularLayout.setVerticalGroup(
            jdValidacion_AnularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jdValidacion_Pago.setTitle("Validar Usuario");

        jPanel21.setBackground(new java.awt.Color(195, 233, 164));
        jPanel21.setToolTipText("");

        jLabel64.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel64.setText("Ingrese Clave:");

        txtValidacionPass_Pagos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtValidacionPass_Pagos.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        btn_validar_pago.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_validar_pago.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/key_validation.png"))); // NOI18N
        btn_validar_pago.setText("OK");
        btn_validar_pago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_validar_pagoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(jLabel64)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(txtValidacionPass_Pagos, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_validar_pago, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel64)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtValidacionPass_Pagos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_validar_pago))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jdValidacion_PagoLayout = new javax.swing.GroupLayout(jdValidacion_Pago.getContentPane());
        jdValidacion_Pago.getContentPane().setLayout(jdValidacion_PagoLayout);
        jdValidacion_PagoLayout.setHorizontalGroup(
            jdValidacion_PagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jdValidacion_PagoLayout.setVerticalGroup(
            jdValidacion_PagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setClosable(true);
        setIconifiable(true);
        setTitle("VER PAGOS");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/comision_logo.jpg"))); // NOI18N

        jPanel8.setBackground(new java.awt.Color(195, 233, 164));

        jLabel84.setBackground(new java.awt.Color(0, 153, 153));
        jLabel84.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel84.setForeground(new java.awt.Color(255, 255, 255));
        jLabel84.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel84.setText("VER PAGOS");
        jLabel84.setOpaque(true);

        jpVerPagos.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        cboFiltroAgricultor_VerPagos.setEnabled(false);
        cboFiltroAgricultor_VerPagos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtFiltroDni_VerPagos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtFiltroDni_VerPagos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFiltroDni_VerPagos.setEnabled(false);
        txtFiltroDni_VerPagos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFiltroDni_VerPagosKeyTyped(evt);
            }
        });

        btn_buscar_pagos.setBackground(new java.awt.Color(204, 255, 204));
        btn_buscar_pagos.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_buscar_pagos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/Search-icon.png"))); // NOI18N
        btn_buscar_pagos.setText("BUSCAR");
        btn_buscar_pagos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscar_pagosActionPerformed(evt);
            }
        });

        btn_Imprimir_pagos.setBackground(new java.awt.Color(204, 255, 204));
        btn_Imprimir_pagos.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_Imprimir_pagos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/print_1.png"))); // NOI18N
        btn_Imprimir_pagos.setText("IMPRIMIR");
        btn_Imprimir_pagos.setEnabled(false);

        rb_group.add(jrbDni_VerPagos);
        jrbDni_VerPagos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jrbDni_VerPagos.setLabel("DNI:");
        jrbDni_VerPagos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbDni_VerPagosActionPerformed(evt);
            }
        });

        rb_group.add(jrbAgricultor_VerPagos);
        jrbAgricultor_VerPagos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jrbAgricultor_VerPagos.setText("Usuario:");
        jrbAgricultor_VerPagos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbAgricultor_VerPagosActionPerformed(evt);
            }
        });

        jLabel86.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel86.setText("Estado:");

        cboEstado_VerPagos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cboEstado_VerPagos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Programado", "Cancelado", "Anulado" }));

        javax.swing.GroupLayout jpVerPagosLayout = new javax.swing.GroupLayout(jpVerPagos);
        jpVerPagos.setLayout(jpVerPagosLayout);
        jpVerPagosLayout.setHorizontalGroup(
            jpVerPagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpVerPagosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpVerPagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jrbDni_VerPagos)
                    .addComponent(jrbAgricultor_VerPagos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpVerPagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFiltroDni_VerPagos, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpVerPagosLayout.createSequentialGroup()
                        .addComponent(cboFiltroAgricultor_VerPagos, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel86)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboEstado_VerPagos, 0, 145, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(btn_buscar_pagos, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_Imprimir_pagos, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jpVerPagosLayout.setVerticalGroup(
            jpVerPagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpVerPagosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpVerPagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_buscar_pagos, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpVerPagosLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jpVerPagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFiltroDni_VerPagos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jrbDni_VerPagos))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpVerPagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboFiltroAgricultor_VerPagos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jrbAgricultor_VerPagos)
                            .addComponent(jLabel86)
                            .addComponent(cboEstado_VerPagos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btn_Imprimir_pagos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jtVerPagos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "N° CUENTA", "DESCRIPCIÓN", "FECHA", "MONTO", "MONTO AMORTIZADO", "SALDO", "ESTADO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtVerPagos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jtVerPagosMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jtVerPagosMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jtVerPagos);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel84, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpVerPagos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel84, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpVerPagos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtFiltroDni_VerPagosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFiltroDni_VerPagosKeyTyped
        new Funciones().soloNumeros(evt);
        if (txtFiltroDni_VerPagos.getText().length() == 8) {
            evt.consume();
        }
    }//GEN-LAST:event_txtFiltroDni_VerPagosKeyTyped

    private void btn_buscar_pagosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscar_pagosActionPerformed
        int indice = cboEstado_VerPagos.getSelectedIndex();
        int estado = 0;
        if (indice == 0) {
            estado = 1;
        }
        if (indice == 1) {
            estado = 2;
        }
        if (indice == 2) {
            estado = 3;
        }
        if (jrbDni_VerPagos.isSelected()) {
            gettabla_verpagos_byAgricultor(txtFiltroDni_VerPagos.getText(), 0, estado);
        }
        if (jrbAgricultor_VerPagos.isSelected()) {
            int id = ((Agricultor) cboFiltroAgricultor_VerPagos.getSelectedItem()).getInt_id();
            gettabla_verpagos_byAgricultor("", id, estado);
        }
    }//GEN-LAST:event_btn_buscar_pagosActionPerformed

    private void jrbDni_VerPagosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbDni_VerPagosActionPerformed
        if (jrbDni_VerPagos.isSelected()) {
            txtFiltroDni_VerPagos.setEnabled(true);
            cboFiltroAgricultor_VerPagos.setEnabled(false);
        }
    }//GEN-LAST:event_jrbDni_VerPagosActionPerformed

    private void jrbAgricultor_VerPagosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbAgricultor_VerPagosActionPerformed
        if (jrbAgricultor_VerPagos.isSelected()) {
            cboFiltroAgricultor_VerPagos.setEnabled(true);
            txtFiltroDni_VerPagos.setEnabled(false);
        }
    }//GEN-LAST:event_jrbAgricultor_VerPagosActionPerformed

    private void jmip_PagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmip_PagarActionPerformed
        try {
            if (cboEstado_VerPagos.getSelectedIndex() == 0) {
                txtFecha_RegistrarPagos.setDate(new Date());
                txtMontoTotal_Pago.setText(jtVerPagos.getModel().getValueAt(jtVerPagos.getSelectedRow(), 6).toString());
                idPago = Integer.parseInt(jtVerPagos.getValueAt(jtVerPagos.getSelectedRow(), 0).toString());
                jdPagar.pack();
                jdPagar.setLocationRelativeTo(null);
                jdPagar.getRootPane().registerKeyboardAction(new CloseDialogEscape(jdPagar),
                        KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
                jdPagar.setModal(true);
                jdPagar.setVisible(true);

            } else {
                JOptionPane.showMessageDialog(null, "El Servicio Ya Esta Pagado");
            }
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
    }//GEN-LAST:event_jmip_PagarActionPerformed

    private void jmip_AnularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmip_AnularActionPerformed
        try {
            modalvalidacion_anular();
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }
    }//GEN-LAST:event_jmip_AnularActionPerformed

    private void txtObservacion_RegistrarPagosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtObservacion_RegistrarPagosKeyTyped
        Character c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            evt.setKeyChar(Character.toUpperCase(c));
        }
    }//GEN-LAST:event_txtObservacion_RegistrarPagosKeyTyped

    private void txtObservacion_RegistrarPagosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtObservacion_RegistrarPagosKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtObservacion_RegistrarPagos.setText(txtObservacion_RegistrarPagos.getText().trim());
            btn_Guardar_pago.requestFocus();
        }
    }//GEN-LAST:event_txtObservacion_RegistrarPagosKeyPressed

    private void btn_Cancelar_PagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Cancelar_PagoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_Cancelar_PagoActionPerformed

    private void btn_Guardar_pagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Guardar_pagoActionPerformed
        try {
            if (txtVoucher_RegistrarPago.getText().compareTo("") != 0 && txtObservacion_RegistrarPagos.getText().compareTo("") != 0) {
                modalvalidacion_pagos();
            } else {
                JOptionPane.showMessageDialog(null, "No se admite campos vacios", "ALERTA", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            System.out.println("Error al Realizar Pago: " + e.getMessage());
        } finally {
            jdPagar.dispose();
        }
    }//GEN-LAST:event_btn_Guardar_pagoActionPerformed

    private void btn_validar_anulacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_validar_anulacionActionPerformed
        try {
            Usuario u = new Usuario();
            BLUsuario us = new BLUsuario();
            char passArray[] = txtValidacionPass_Anular.getPassword();
            String pass = new String(passArray);
            u = us.get_usuario_bypassword(pass);
            if (u.getVar_user() != null) {
                jdValidacion_Anular.dispose();
                Anular_Pagos(u.getInt_id());
                if (jrbDni_VerPagos.isSelected()) {
                    gettabla_verpagos_byAgricultor(txtFiltroDni_VerPagos.getText(), 0, cboEstado_VerPagos.getSelectedIndex());
                }
                if (jrbAgricultor_VerPagos.isSelected()) {
                    int id = ((Agricultor) cboFiltroAgricultor_VerPagos.getSelectedItem()).getInt_id();
                    gettabla_verpagos_byAgricultor("", id, cboEstado_VerPagos.getSelectedIndex());
                }
                txtValidacionPass_Anular.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "[Clave Incorrecta]", "Alerta", JOptionPane.ERROR_MESSAGE);
                txtValidacionPass_Anular.requestFocus();
                txtValidacionPass_Anular.setText("");
            }

        } catch (Exception e) {
            System.out.println("Error de Validacion ");
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_validar_anulacionActionPerformed

    private void txtObservacion_AnularPagoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtObservacion_AnularPagoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtObservacion_AnularPago.setText(txtObservacion_AnularPago.getText().trim());
            txtValidacionPass_Anular.requestFocus();
        }
    }//GEN-LAST:event_txtObservacion_AnularPagoKeyPressed

    private void btn_validar_pagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_validar_pagoActionPerformed
        try {
            Usuario u = new Usuario();
            BLUsuario us = new BLUsuario();
            char passArray[] = txtValidacionPass_Pagos.getPassword();
            String pass = new String(passArray);
            u = us.get_usuario_bypassword(pass);
            if (u.getVar_user() != null) {
                jdValidacion_Pago.dispose();
                Pagar();
                txtValidacionPass_Pagos.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "[Clave Incorrecta]", "Alerta", JOptionPane.ERROR_MESSAGE);
                txtValidacionPass_Pagos.requestFocus();
                txtValidacionPass_Pagos.setText("");
            }
        } catch (Exception e) {
            System.out.println("Error de Validacion ");
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_validar_pagoActionPerformed

    private void jtVerPagosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtVerPagosMousePressed
        if (evt.isPopupTrigger() && jtVerPagos.getModel().getRowCount() != 0
                && jtVerPagos.getSelectedRow() != -1) {
            jpmVerPagos.show(jtVerPagos, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_jtVerPagosMousePressed

    private void jtVerPagosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtVerPagosMouseReleased
        if (evt.isPopupTrigger() && jtVerPagos.getModel().getRowCount() != 0
                && jtVerPagos.getSelectedRow() != -1) {
            jpmVerPagos.show(jtVerPagos, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_jtVerPagosMouseReleased

    public void modalvalidacion_anular() {
        jdValidacion_Anular.pack();
        jdValidacion_Anular.setLocationRelativeTo(null);
        jdValidacion_Anular.getRootPane().registerKeyboardAction(new CloseDialogEscape(jdValidacion_Anular),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
        jdValidacion_Anular.setModal(true);
        jdValidacion_Anular.setVisible(true);
    }

    public void modalvalidacion_pagos() {
        jdValidacion_Pago.pack();
        jdValidacion_Pago.setLocationRelativeTo(null);
        jdValidacion_Pago.getRootPane().registerKeyboardAction(new CloseDialogEscape(jdValidacion_Pago),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
        jdValidacion_Pago.setModal(true);
        jdValidacion_Pago.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Cancelar_Pago;
    private javax.swing.JButton btn_Guardar_pago;
    private javax.swing.JButton btn_Imprimir_pagos;
    private javax.swing.JButton btn_buscar_pagos;
    private javax.swing.JButton btn_validar_anulacion;
    private javax.swing.JButton btn_validar_pago;
    private javax.swing.JComboBox cboEstado_VerPagos;
    private org.jdesktop.swingx.JXComboBox cboFiltroAgricultor_VerPagos;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel131;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane29;
    private javax.swing.JDialog jdPagar;
    private javax.swing.JDialog jdValidacion_Anular;
    private javax.swing.JDialog jdValidacion_Pago;
    private javax.swing.JMenuItem jmip_Anular;
    private javax.swing.JMenuItem jmip_GenerarDocumento;
    private javax.swing.JMenuItem jmip_Pagar;
    private javax.swing.JPanel jpVerPagos;
    private javax.swing.JPopupMenu jpmVerPagos;
    private javax.swing.JRadioButton jrbAgricultor_VerPagos;
    private javax.swing.JRadioButton jrbDni_VerPagos;
    private javax.swing.JTable jtVerPagos;
    private javax.swing.ButtonGroup rb_group;
    private com.toedter.calendar.JDateChooser txtFecha_RegistrarPagos;
    private javax.swing.JTextField txtFiltroDni_VerPagos;
    private javax.swing.JTextField txtMontoTotal_Pago;
    private org.jdesktop.swingx.JXTextField txtMonto_Pago;
    private javax.swing.JTextArea txtObservacion_AnularPago;
    private javax.swing.JTextArea txtObservacion_RegistrarPagos;
    private javax.swing.JPasswordField txtValidacionPass_Anular;
    private javax.swing.JPasswordField txtValidacionPass_Pagos;
    private javax.swing.JTextField txtVoucher_RegistrarPago;
    // End of variables declaration//GEN-END:variables
}
