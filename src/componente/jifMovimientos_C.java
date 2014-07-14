/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componente;

import controlador.BLConstante;
import controlador.BLMovimiento;
import controlador.BLUsuario;
import entidad.Constante;
import entidad.Usuario;
import java.awt.event.KeyEvent;
import java.util.Date;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import utilitario.CloseDialogEscape;
import utilitario.Funciones;

/**
 *
 * @author Richard
 */
public class jifMovimientos_C extends javax.swing.JInternalFrame {

    /**
     * Creates new form jifMovimientos_C
     */
    public jifMovimientos_C() {
        initComponents();
        getcombo_tipodocumento_all();
        getcombo_tipooperacion_all();
        limpiarFomulario_Movimiento();
    }

    private void limpiarFomulario_Movimiento() {
        txtFecha_Movimiento.setDate(new Date());
        txtNroComprobante_Movimiento.setText("");
        txtCantidad_Movimientos.setValue(1);
        txtMonto_Movimiento.setText("");
        txtConcepto_Movimiento.setText("");
    }

    private void getcombo_tipodocumento_all() {
        cboTipoComprobante_Movimiento.removeAllItems();
        for (Constante c : new BLConstante().get_constante_all_byClase(4)) {
            cboTipoComprobante_Movimiento.addItem(c);
        }
        AutoCompleteDecorator.decorate(cboTipoComprobante_Movimiento);
    }

    private void getcombo_tipooperacion_all() {
        cboTipoOperacion_Movimiento.removeAllItems();
        for (Constante c : new BLConstante().get_constante_all_byClase(5)) {
            cboTipoOperacion_Movimiento.addItem(c);
        }
        AutoCompleteDecorator.decorate(cboTipoOperacion_Movimiento);
    }

    private void RegistrarMovimiento() {
        Date fecha = txtFecha_Movimiento.getDate();
        int tipo_comprobante = ((Constante) cboTipoComprobante_Movimiento.getSelectedItem()).getInt_valor();
        int proveedor = 1;
        int tipo_operacion = ((Constante) cboTipoOperacion_Movimiento.getSelectedItem()).getInt_valor();
        double monto = Double.parseDouble(txtRucProveedor_Movimiento.getText());
        String nro_comprobante = txtNroComprobante_Movimiento.getText();
        double cantidad = txtCantidad_Movimientos.getValue();
        String concepto = txtConcepto_Movimiento.getText();
        if (new BLMovimiento().insertarMovimiento(1, monto, tipo_operacion, tipo_comprobante, nro_comprobante,
                cantidad, proveedor, concepto, new java.sql.Timestamp(fecha.getTime()))) {
            JOptionPane.showMessageDialog(null, "Registro Exitoso", "MENSAJE", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error al Registrar", "MENSAJE", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jdValidacion_Movimiento = new javax.swing.JDialog();
        jPanel20 = new javax.swing.JPanel();
        jLabel76 = new javax.swing.JLabel();
        txtValidacionPass_Movimiento = new javax.swing.JPasswordField();
        btn_validar_movimiento = new javax.swing.JButton();
        jpMovimiento = new javax.swing.JPanel();
        jLabel50 = new javax.swing.JLabel();
        cboTipoOperacion_Movimiento = new org.jdesktop.swingx.JXComboBox();
        jLabel71 = new javax.swing.JLabel();
        txtMonto_Movimiento = new org.jdesktop.swingx.JXTextField();
        jLabel72 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        txtConcepto_Movimiento = new javax.swing.JTextArea();
        jLabel73 = new javax.swing.JLabel();
        txtFecha_Movimiento = new com.toedter.calendar.JDateChooser();
        btn_Cancelar_movimiento = new javax.swing.JButton();
        btn_Guardar_movimiento = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        cboTipoComprobante_Movimiento = new javax.swing.JComboBox();
        txtNroComprobante_Movimiento = new javax.swing.JTextField();
        jLabel63 = new javax.swing.JLabel();
        jXComboBox1 = new org.jdesktop.swingx.JXComboBox();
        jLabel66 = new javax.swing.JLabel();
        txtCantidad_Movimientos = new com.toedter.components.JSpinField();
        txtRucProveedor_Movimiento = new javax.swing.JTextField();

        jdValidacion_Movimiento.setTitle("Validar Usuario");
        jdValidacion_Movimiento.setResizable(false);

        jPanel20.setBackground(new java.awt.Color(195, 233, 164));

        jLabel76.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel76.setText("Ingrese Clave:");

        txtValidacionPass_Movimiento.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtValidacionPass_Movimiento.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        btn_validar_movimiento.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_validar_movimiento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/key_validation.png"))); // NOI18N
        btn_validar_movimiento.setText("OK");
        btn_validar_movimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_validar_movimientoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel76)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(txtValidacionPass_Movimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_validar_movimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel76)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtValidacionPass_Movimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_validar_movimiento))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jdValidacion_MovimientoLayout = new javax.swing.GroupLayout(jdValidacion_Movimiento.getContentPane());
        jdValidacion_Movimiento.getContentPane().setLayout(jdValidacion_MovimientoLayout);
        jdValidacion_MovimientoLayout.setHorizontalGroup(
            jdValidacion_MovimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jdValidacion_MovimientoLayout.setVerticalGroup(
            jdValidacion_MovimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setClosable(true);
        setIconifiable(true);
        setTitle("MOVIMIENTO CAJA");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/comision logo.jpg"))); // NOI18N

        jpMovimiento.setBackground(new java.awt.Color(195, 233, 164));
        jpMovimiento.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel50.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel50.setText("TIPO OPERACION :");

        cboTipoOperacion_Movimiento.setEditable(true);
        cboTipoOperacion_Movimiento.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel71.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel71.setText("CANTIDAD :");

        txtMonto_Movimiento.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMonto_Movimiento.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtMonto_Movimiento.setPrompt("S/. 0.00");
        txtMonto_Movimiento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMonto_MovimientoKeyTyped(evt);
            }
        });

        jLabel72.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel72.setText("CONCEPTO :");

        txtConcepto_Movimiento.setColumns(20);
        txtConcepto_Movimiento.setRows(5);
        txtConcepto_Movimiento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtConcepto_MovimientoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtConcepto_MovimientoKeyTyped(evt);
            }
        });
        jScrollPane11.setViewportView(txtConcepto_Movimiento);

        jLabel73.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel73.setText("FECHA :");

        txtFecha_Movimiento.setDateFormatString("dd 'de' MMMM 'de' yyyy");
        txtFecha_Movimiento.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btn_Cancelar_movimiento.setBackground(new java.awt.Color(255, 51, 0));
        btn_Cancelar_movimiento.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_Cancelar_movimiento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/Cancel-icon.png"))); // NOI18N
        btn_Cancelar_movimiento.setText("CANCELAR");
        btn_Cancelar_movimiento.setIconTextGap(8);
        btn_Cancelar_movimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Cancelar_movimientoActionPerformed(evt);
            }
        });

        btn_Guardar_movimiento.setBackground(new java.awt.Color(255, 51, 0));
        btn_Guardar_movimiento.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_Guardar_movimiento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/Save-icon.png"))); // NOI18N
        btn_Guardar_movimiento.setText("GUARDAR");
        btn_Guardar_movimiento.setIconTextGap(8);
        btn_Guardar_movimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Guardar_movimientoActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("TIPO COMPROBANTE :");

        cboTipoComprobante_Movimiento.setEditable(true);
        cboTipoComprobante_Movimiento.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtNroComprobante_Movimiento.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNroComprobante_Movimiento.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNroComprobante_Movimiento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNroComprobante_MovimientoKeyTyped(evt);
            }
        });

        jLabel63.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel63.setText("PROVEEDOR:");

        jXComboBox1.setEditable(true);
        jXComboBox1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel66.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel66.setText("MONTO :");

        txtRucProveedor_Movimiento.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtRucProveedor_Movimiento.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jpMovimientoLayout = new javax.swing.GroupLayout(jpMovimiento);
        jpMovimiento.setLayout(jpMovimientoLayout);
        jpMovimientoLayout.setHorizontalGroup(
            jpMovimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpMovimientoLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jpMovimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpMovimientoLayout.createSequentialGroup()
                        .addComponent(jLabel72)
                        .addGroup(jpMovimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpMovimientoLayout.createSequentialGroup()
                                .addGap(269, 269, 269)
                                .addComponent(btn_Cancelar_movimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpMovimientoLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_Guardar_movimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(188, 188, 188))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpMovimientoLayout.createSequentialGroup()
                                .addGap(72, 72, 72)
                                .addGroup(jpMovimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jpMovimientoLayout.createSequentialGroup()
                                        .addComponent(txtCantidad_Movimientos, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel66)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtMonto_Movimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(jScrollPane11)))))
                    .addGroup(jpMovimientoLayout.createSequentialGroup()
                        .addGroup(jpMovimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpMovimientoLayout.createSequentialGroup()
                                .addGroup(jpMovimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel50)
                                    .addComponent(jLabel71)
                                    .addComponent(jLabel73))
                                .addGap(31, 31, 31)
                                .addGroup(jpMovimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cboTipoOperacion_Movimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtFecha_Movimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jpMovimientoLayout.createSequentialGroup()
                                .addGroup(jpMovimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel63))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jpMovimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jpMovimientoLayout.createSequentialGroup()
                                        .addComponent(cboTipoComprobante_Movimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtNroComprobante_Movimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jpMovimientoLayout.createSequentialGroup()
                                        .addComponent(jXComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtRucProveedor_Movimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(1, 1, 1)))
                .addGap(43, 43, 43))
        );
        jpMovimientoLayout.setVerticalGroup(
            jpMovimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpMovimientoLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jpMovimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtFecha_Movimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel73))
                .addGap(18, 18, 18)
                .addGroup(jpMovimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cboTipoComprobante_Movimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNroComprobante_Movimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jpMovimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel63)
                    .addComponent(jXComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRucProveedor_Movimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpMovimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel50)
                    .addComponent(cboTipoOperacion_Movimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jpMovimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpMovimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel71)
                        .addComponent(txtMonto_Movimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel66))
                    .addComponent(txtCantidad_Movimientos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jpMovimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel72)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jpMovimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_Guardar_movimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_Cancelar_movimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpMovimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpMovimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtMonto_MovimientoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMonto_MovimientoKeyTyped
        new Funciones().soloDecimales(evt);
        if (txtMonto_Movimiento.getText().length() == 10) {
            evt.consume();
        }
    }//GEN-LAST:event_txtMonto_MovimientoKeyTyped

    private void txtConcepto_MovimientoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConcepto_MovimientoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            txtConcepto_Movimiento.setText(txtConcepto_Movimiento.getText().trim());
            btn_Guardar_movimiento.requestFocus();
        }
    }//GEN-LAST:event_txtConcepto_MovimientoKeyPressed

    private void txtConcepto_MovimientoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConcepto_MovimientoKeyTyped
        new Funciones().convertir_mayuscula(evt);
    }//GEN-LAST:event_txtConcepto_MovimientoKeyTyped

    private void btn_Cancelar_movimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Cancelar_movimientoActionPerformed
        limpiarFomulario_Movimiento();
    }//GEN-LAST:event_btn_Cancelar_movimientoActionPerformed

    private void btn_Guardar_movimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Guardar_movimientoActionPerformed
        try {
            if (txtFecha_Movimiento.getDate() != null && txtMonto_Movimiento.getText().compareTo("") != 0
                    && txtConcepto_Movimiento.getText().compareTo("") != 0) {
                modalvalidacion_movimiento();
            } else {
                JOptionPane.showMessageDialog(null, "No se admite campos vacios", "ALERTA", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            System.out.println("Error al registrar Cuenta" + e.toString());
        }
    }//GEN-LAST:event_btn_Guardar_movimientoActionPerformed

    private void txtNroComprobante_MovimientoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNroComprobante_MovimientoKeyTyped
        new Funciones().convertir_mayuscula(evt);
    }//GEN-LAST:event_txtNroComprobante_MovimientoKeyTyped

    private void btn_validar_movimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_validar_movimientoActionPerformed
        try {
            Usuario u = new Usuario();
            BLUsuario us = new BLUsuario();
            char passArray[] = txtValidacionPass_Movimiento.getPassword();
            String pass = new String(passArray);
            u = us.get_usuario_bypassword(pass);
            if (u.getVar_user() != null) {
                jdValidacion_Movimiento.dispose();
                RegistrarMovimiento();
                txtValidacionPass_Movimiento.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "[Clave Incorrecta]", "Alerta", JOptionPane.ERROR_MESSAGE);
                txtValidacionPass_Movimiento.requestFocus();
                txtValidacionPass_Movimiento.setText("");
            }

        } catch (Exception e) {
            System.out.println("Error de Validacion ");
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_validar_movimientoActionPerformed

    public void modalvalidacion_movimiento() {
        jdValidacion_Movimiento.pack();
        jdValidacion_Movimiento.setLocationRelativeTo(null);
        jdValidacion_Movimiento.getRootPane().registerKeyboardAction(new CloseDialogEscape(jdValidacion_Movimiento),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
        jdValidacion_Movimiento.setModal(true);
        jdValidacion_Movimiento.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Cancelar_movimiento;
    private javax.swing.JButton btn_Guardar_movimiento;
    private javax.swing.JButton btn_validar_movimiento;
    private javax.swing.JComboBox cboTipoComprobante_Movimiento;
    private org.jdesktop.swingx.JXComboBox cboTipoOperacion_Movimiento;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JScrollPane jScrollPane11;
    private org.jdesktop.swingx.JXComboBox jXComboBox1;
    private javax.swing.JDialog jdValidacion_Movimiento;
    private javax.swing.JPanel jpMovimiento;
    private com.toedter.components.JSpinField txtCantidad_Movimientos;
    private javax.swing.JTextArea txtConcepto_Movimiento;
    private com.toedter.calendar.JDateChooser txtFecha_Movimiento;
    private org.jdesktop.swingx.JXTextField txtMonto_Movimiento;
    private javax.swing.JTextField txtNroComprobante_Movimiento;
    private javax.swing.JTextField txtRucProveedor_Movimiento;
    private javax.swing.JPasswordField txtValidacionPass_Movimiento;
    // End of variables declaration//GEN-END:variables
}
