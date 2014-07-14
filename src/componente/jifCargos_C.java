/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componente;

import controlador.BLCargo;
import entidad.Cargo;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import utilitario.Funciones;

/**
 *
 * @author Richard
 */
public class jifCargos_C extends javax.swing.JInternalFrame {

    /**
     * Creates new form jifCargos_C
     */
    public jifCargos_C() {
        initComponents();
        getcombo_cargo_all("");
    }

    private void getcombo_cargo_all(String condicion) {
        DefaultTableModel temp = (DefaultTableModel) jtCargos_Administracion.getModel();
        temp.setRowCount(0);
        for (Cargo c : new BLCargo().get_cargo_all(condicion)) {
            Object[] datos = {c.getVar_descripcion(), c.getVar_estado()};
            temp.addRow(datos);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel80 = new javax.swing.JLabel();
        txtDescripcionCargo = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        btn_guardar_cargos3 = new javax.swing.JButton();
        jLabel81 = new javax.swing.JLabel();
        txtBuscarCargo = new org.jdesktop.swingx.JXSearchField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtCargos_Administracion = new javax.swing.JTable();

        setBackground(new java.awt.Color(195, 233, 164));
        setClosable(true);
        setIconifiable(true);
        setTitle("CARGOS");

        jLabel80.setBackground(new java.awt.Color(195, 233, 164));
        jLabel80.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel80.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel80.setText("REGISTRO DE CARGOS");
        jLabel80.setOpaque(true);

        txtDescripcionCargo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtDescripcionCargo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDescripcionCargo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDescripcionCargoKeyTyped(evt);
            }
        });

        jLabel54.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel54.setText("DESCRIPCIÓN :");

        btn_guardar_cargos3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_guardar_cargos3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/Save.png"))); // NOI18N
        btn_guardar_cargos3.setText("GUARDAR");
        btn_guardar_cargos3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardar_cargos3ActionPerformed(evt);
            }
        });

        jLabel81.setBackground(new java.awt.Color(0, 153, 153));
        jLabel81.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel81.setForeground(new java.awt.Color(255, 255, 255));
        jLabel81.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel81.setText("LISTA DE CARGOS");
        jLabel81.setOpaque(true);

        txtBuscarCargo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtBuscarCargo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarCargoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarCargoKeyTyped(evt);
            }
        });

        jtCargos_Administracion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NOMBRE", "ESTADO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jtCargos_Administracion);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel81, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel54)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDescripcionCargo)
                        .addGap(18, 18, 18)
                        .addComponent(btn_guardar_cargos3, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtBuscarCargo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel80, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 496, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel54, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_guardar_cargos3)
                        .addComponent(txtDescripcionCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscarCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtDescripcionCargoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionCargoKeyTyped
        new Funciones().soloLetras(evt);
        if (txtDescripcionCargo.getText().length() == 30) {
            evt.consume();
        }
        new Funciones().convertir_mayuscula(evt);
    }//GEN-LAST:event_txtDescripcionCargoKeyTyped

    private void btn_guardar_cargos3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardar_cargos3ActionPerformed
        try {
            if (txtDescripcionCargo.getText().compareTo("") != 0) {
                boolean resultado = false;
                resultado = new BLCargo().Registrar(txtDescripcionCargo.getText());
                if (resultado) {
                    JOptionPane.showMessageDialog(null, "Se registro Correctamente", "AVISO", JOptionPane.INFORMATION_MESSAGE);
                    getcombo_cargo_all("");
                }
            } else {
                JOptionPane.showMessageDialog(null, "No se Admiten Campos Vacios", "ALERTA", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            System.out.println("Error de Ingreso" + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_guardar_cargos3ActionPerformed

    private void txtBuscarCargoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarCargoKeyReleased
        getcombo_cargo_all(txtBuscarCargo.getText());
    }//GEN-LAST:event_txtBuscarCargoKeyReleased

    private void txtBuscarCargoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarCargoKeyTyped
        new Funciones().convertir_mayuscula(evt);
    }//GEN-LAST:event_txtBuscarCargoKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_guardar_cargos3;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtCargos_Administracion;
    private org.jdesktop.swingx.JXSearchField txtBuscarCargo;
    private javax.swing.JTextField txtDescripcionCargo;
    // End of variables declaration//GEN-END:variables
}
