/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package componente;

import controlador.BLComite;
import entidad.Comite;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import utilitario.Funciones;

/**
 *
 * @author Richard
 */
public class jifComites_C extends javax.swing.JInternalFrame {

   
    public jifComites_C() {
        initComponents();
        gettabla_comite_byActivos("");
    }

    private void gettabla_comite_byActivos(String palabra) {
        DefaultTableModel temp1 = (DefaultTableModel) jtComite_Administracion.getModel();
        temp1.setRowCount(0);
        for (Comite c : new BLComite().get_comite_byActivos(palabra)) {
            Object[] datos1 = {c.getVar_nombre(), c.getVar_estado()};
            temp1.addRow(datos1);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpcomites = new javax.swing.JPanel();
        jPanel30 = new javax.swing.JPanel();
        jLabel112 = new javax.swing.JLabel();
        txtComite_Registrar = new javax.swing.JTextField();
        btnGuardar_Comite1 = new javax.swing.JButton();
        jLabel113 = new javax.swing.JLabel();
        txtFiltroComite_Administracion = new org.jdesktop.swingx.JXSearchField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtComite_Administracion = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setTitle("COMITES");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/comision logo.jpg"))); // NOI18N

        jpcomites.setBackground(new java.awt.Color(195, 233, 164));

        jPanel30.setBackground(new java.awt.Color(195, 233, 164));
        jPanel30.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel112.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel112.setText("Comite :");

        txtComite_Registrar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtComite_Registrar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtComite_Registrar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtComite_RegistrarKeyTyped(evt);
            }
        });

        btnGuardar_Comite1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnGuardar_Comite1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/Save.png"))); // NOI18N
        btnGuardar_Comite1.setText("GUARDAR");
        btnGuardar_Comite1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar_Comite1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel112)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtComite_Registrar, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardar_Comite1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar_Comite1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtComite_Registrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel112))
                .addContainerGap())
        );

        jLabel113.setBackground(new java.awt.Color(0, 153, 153));
        jLabel113.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel113.setForeground(new java.awt.Color(255, 255, 255));
        jLabel113.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel113.setText("LISTA DE COMITES");
        jLabel113.setOpaque(true);

        txtFiltroComite_Administracion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtFiltroComite_Administracion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFiltroComite_AdministracionKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFiltroComite_AdministracionKeyTyped(evt);
            }
        });

        jtComite_Administracion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jtComite_Administracion);

        javax.swing.GroupLayout jpcomitesLayout = new javax.swing.GroupLayout(jpcomites);
        jpcomites.setLayout(jpcomitesLayout);
        jpcomitesLayout.setHorizontalGroup(
            jpcomitesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpcomitesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpcomitesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel30, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel113, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtFiltroComite_Administracion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jpcomitesLayout.setVerticalGroup(
            jpcomitesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpcomitesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel113, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFiltroComite_Administracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpcomites, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpcomites, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtComite_RegistrarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtComite_RegistrarKeyTyped
        if (txtComite_Registrar.getText().length() == 45) {
            evt.consume();
        }
        new Funciones().convertir_mayuscula(evt);
    }//GEN-LAST:event_txtComite_RegistrarKeyTyped

    private void btnGuardar_Comite1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar_Comite1ActionPerformed
        try {
            if (txtComite_Registrar.getText().compareTo("") != 0) {
                if (new BLComite().Registrar(txtComite_Registrar.getText())) {
                    gettabla_comite_byActivos("");
                    JOptionPane.showMessageDialog(null, "Registro Exitoso", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Registro Fallido", "Mensaje", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, " No se admiten campos vacios ", "Mensaje", 1);
            }
        } catch (Exception e) {
            System.out.println("Error al registrar comite" + e.getMessage());
        }
    }//GEN-LAST:event_btnGuardar_Comite1ActionPerformed

    private void txtFiltroComite_AdministracionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFiltroComite_AdministracionKeyReleased
        gettabla_comite_byActivos(txtFiltroComite_Administracion.getText());
    }//GEN-LAST:event_txtFiltroComite_AdministracionKeyReleased

    private void txtFiltroComite_AdministracionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFiltroComite_AdministracionKeyTyped
        new Funciones().convertir_mayuscula(evt);
    }//GEN-LAST:event_txtFiltroComite_AdministracionKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar_Comite1;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jpcomites;
    private javax.swing.JTable jtComite_Administracion;
    private javax.swing.JTextField txtComite_Registrar;
    private org.jdesktop.swingx.JXSearchField txtFiltroComite_Administracion;
    // End of variables declaration//GEN-END:variables
}
