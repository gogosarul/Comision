/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componente;

import controlador.BLMaterial;
import entidad.Material;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import utilitario.Funciones;

/**
 *
 * @author Richard
 */
public class jifMateriales_C extends javax.swing.JInternalFrame {

    /**
     * Creates new form jifMateriales_C
     */
    public jifMateriales_C() {
        initComponents();
        limpiarformulario_materiales();
        gettabla_material_all("");
    }

    private void limpiarformulario_materiales() {
        txtNombre_Material.setText("");
        txtCantidad_Material.setText("");
        txtDescripcion_Material.setText("");
        txtFiltroNombre_Material.setText("");
    }

    private void gettabla_material_all(String condicion) {
        DefaultTableModel temp = (DefaultTableModel) jtMaterial.getModel();
        temp.setRowCount(0);
        for (Material m : new BLMaterial().get_material_all(condicion)) {
            Object[] datos = {m.getInt_id(), m.getVar_nombre(), m.getInt_cantidad(), m.getVar_descripcion(), m.getVar_estado()};
            temp.addRow(datos);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_guardar_materiales = new javax.swing.JButton();
        jPanel48 = new javax.swing.JPanel();
        jLabel134 = new javax.swing.JLabel();
        jLabel135 = new javax.swing.JLabel();
        txtCantidad_Material = new javax.swing.JTextField();
        jLabel136 = new javax.swing.JLabel();
        txtDescripcion_Material = new javax.swing.JTextField();
        txtNombre_Material = new javax.swing.JTextField();
        jLabel137 = new javax.swing.JLabel();
        btn_cancelar_materiales = new javax.swing.JButton();
        jLabel120 = new javax.swing.JLabel();
        txtFiltroNombre_Material = new org.jdesktop.swingx.JXSearchField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtMaterial = new javax.swing.JTable();

        setBackground(new java.awt.Color(195, 233, 164));
        setClosable(true);
        setIconifiable(true);
        setTitle("MATERIALES");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/comision logo.jpg"))); // NOI18N
        setOpaque(true);

        btn_guardar_materiales.setBackground(new java.awt.Color(255, 102, 0));
        btn_guardar_materiales.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_guardar_materiales.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/Save.png"))); // NOI18N
        btn_guardar_materiales.setText("GUARDAR");
        btn_guardar_materiales.setIconTextGap(8);
        btn_guardar_materiales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardar_materialesActionPerformed(evt);
            }
        });

        jPanel48.setBackground(new java.awt.Color(195, 233, 164));

        jLabel134.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel134.setText("MATERIAL :");

        jLabel135.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel135.setText("CANTIDAD :");

        txtCantidad_Material.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtCantidad_Material.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCantidad_Material.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidad_MaterialtxtNombre_Lateral6ActionPerformed(evt);
            }
        });
        txtCantidad_Material.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidad_MaterialtxtNombre_Lateral6KeyTyped(evt);
            }
        });

        jLabel136.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel136.setText("DESCRIPCIÓN :");

        txtDescripcion_Material.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtDescripcion_Material.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDescripcion_Material.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescripcion_MaterialtxtNombre_Lateral7ActionPerformed(evt);
            }
        });
        txtDescripcion_Material.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDescripcion_MaterialtxtNombre_Lateral7KeyTyped(evt);
            }
        });

        txtNombre_Material.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNombre_Material.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNombre_Material.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombre_MaterialtxtNombre_Lateral6ActionPerformed(evt);
            }
        });
        txtNombre_Material.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombre_MaterialtxtNombre_Lateral6KeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel48Layout = new javax.swing.GroupLayout(jPanel48);
        jPanel48.setLayout(jPanel48Layout);
        jPanel48Layout.setHorizontalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel48Layout.createSequentialGroup()
                .addContainerGap(48, Short.MAX_VALUE)
                .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel48Layout.createSequentialGroup()
                        .addComponent(jLabel136)
                        .addGap(28, 28, 28))
                    .addGroup(jPanel48Layout.createSequentialGroup()
                        .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel134)
                            .addComponent(jLabel135))
                        .addGap(48, 48, 48)))
                .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtNombre_Material, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                    .addComponent(txtCantidad_Material, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDescripcion_Material))
                .addGap(55, 55, 55))
        );
        jPanel48Layout.setVerticalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel48Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel134)
                    .addComponent(txtNombre_Material, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel135)
                    .addComponent(txtCantidad_Material, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel136)
                    .addComponent(txtDescripcion_Material, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jLabel137.setBackground(new java.awt.Color(195, 233, 164));
        jLabel137.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel137.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel137.setText("REGISTRO DE MATERIALES");
        jLabel137.setOpaque(true);

        btn_cancelar_materiales.setBackground(new java.awt.Color(255, 102, 0));
        btn_cancelar_materiales.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_cancelar_materiales.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/cancelar.png"))); // NOI18N
        btn_cancelar_materiales.setText("CANCELAR");
        btn_cancelar_materiales.setIconTextGap(8);
        btn_cancelar_materiales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelar_materialesActionPerformed(evt);
            }
        });

        jLabel120.setBackground(new java.awt.Color(0, 153, 153));
        jLabel120.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel120.setForeground(new java.awt.Color(255, 255, 255));
        jLabel120.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel120.setText("LISTA DE LATERALES");
        jLabel120.setOpaque(true);

        txtFiltroNombre_Material.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtFiltroNombre_Material.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFiltroNombre_MaterialKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFiltroNombre_MaterialKeyTyped(evt);
            }
        });

        jtMaterial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "MATERIAL", "CANTIDAD", "DESCRIPCIÓN", "ESTADO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jtMaterial);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFiltroNombre_Material, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 667, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel137, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel120, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jPanel48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btn_cancelar_materiales, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btn_guardar_materiales, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(0, 21, Short.MAX_VALUE)))
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(240, 240, 240)
                .addComponent(txtFiltroNombre_Material, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(24, 24, 24)
                    .addComponent(jLabel137, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(21, 21, 21)
                            .addComponent(btn_guardar_materiales, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btn_cancelar_materiales, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jLabel120, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(292, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_guardar_materialesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardar_materialesActionPerformed
        try {
            boolean resultado = false;
            if (txtNombre_Material.getText().compareTo("") != 0 && txtCantidad_Material.getText().compareTo("") != 0
                    && txtDescripcion_Material.getText().compareTo("") != 0) {
                BLMaterial m = new BLMaterial();
                resultado = m.Registrar(txtNombre_Material.getText(), Integer.parseInt(txtCantidad_Material.getText()), txtDescripcion_Material.getText());
                if (resultado) {
                    JOptionPane.showMessageDialog(null, "Se Registro Correctamente", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                    gettabla_material_all("");
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo Registrar", "Alerta", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(null, "No se Admiten Campos Vacios", "Alerta", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            System.out.println("Error de Registro" + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_guardar_materialesActionPerformed

    private void txtCantidad_MaterialtxtNombre_Lateral6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidad_MaterialtxtNombre_Lateral6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidad_MaterialtxtNombre_Lateral6ActionPerformed

    private void txtCantidad_MaterialtxtNombre_Lateral6KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidad_MaterialtxtNombre_Lateral6KeyTyped
        new Funciones().soloDecimales(evt);
        if (txtCantidad_Material.getText().length() == 11) {
            evt.consume();
        }
    }//GEN-LAST:event_txtCantidad_MaterialtxtNombre_Lateral6KeyTyped

    private void txtDescripcion_MaterialtxtNombre_Lateral7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescripcion_MaterialtxtNombre_Lateral7ActionPerformed

    }//GEN-LAST:event_txtDescripcion_MaterialtxtNombre_Lateral7ActionPerformed

    private void txtDescripcion_MaterialtxtNombre_Lateral7KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcion_MaterialtxtNombre_Lateral7KeyTyped
        new Funciones().soloLetras(evt);
        if (txtDescripcion_Material.getText().length() == 100) {
            evt.consume();
        }
        new Funciones().convertir_mayuscula(evt);
    }//GEN-LAST:event_txtDescripcion_MaterialtxtNombre_Lateral7KeyTyped

    private void txtNombre_MaterialtxtNombre_Lateral6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombre_MaterialtxtNombre_Lateral6ActionPerformed

    }//GEN-LAST:event_txtNombre_MaterialtxtNombre_Lateral6ActionPerformed

    private void txtNombre_MaterialtxtNombre_Lateral6KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombre_MaterialtxtNombre_Lateral6KeyTyped
        new Funciones().soloLetras(evt);
        if (txtNombre_Material.getText().length() == 45) {
            evt.consume();
        }
        new Funciones().convertir_mayuscula(evt);
    }//GEN-LAST:event_txtNombre_MaterialtxtNombre_Lateral6KeyTyped

    private void btn_cancelar_materialesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelar_materialesActionPerformed
        limpiarformulario_materiales();
    }//GEN-LAST:event_btn_cancelar_materialesActionPerformed

    private void txtFiltroNombre_MaterialKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFiltroNombre_MaterialKeyReleased
        gettabla_material_all(txtFiltroNombre_Material.getText());
    }//GEN-LAST:event_txtFiltroNombre_MaterialKeyReleased

    private void txtFiltroNombre_MaterialKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFiltroNombre_MaterialKeyTyped
        new Funciones().soloLetras(evt);
        if (txtFiltroNombre_Material.getText().length() == 45) {
            evt.consume();
        }
        new Funciones().convertir_mayuscula(evt);
    }//GEN-LAST:event_txtFiltroNombre_MaterialKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cancelar_materiales;
    private javax.swing.JButton btn_guardar_materiales;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel134;
    private javax.swing.JLabel jLabel135;
    private javax.swing.JLabel jLabel136;
    private javax.swing.JLabel jLabel137;
    private javax.swing.JPanel jPanel48;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtMaterial;
    private javax.swing.JTextField txtCantidad_Material;
    private javax.swing.JTextField txtDescripcion_Material;
    private org.jdesktop.swingx.JXSearchField txtFiltroNombre_Material;
    private javax.swing.JTextField txtNombre_Material;
    // End of variables declaration//GEN-END:variables
}
