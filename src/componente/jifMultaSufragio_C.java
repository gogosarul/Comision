/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componente;

import java.io.FileInputStream;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Richard
 */
public class jifMultaSufragio_C extends javax.swing.JInternalFrame {

    public jifMultaSufragio_C() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel14 = new javax.swing.JPanel();
        jLabel58 = new javax.swing.JLabel();
        btn_cargar_asistencia_asamblea1 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane26 = new javax.swing.JScrollPane();
        txtListaAsistenciaUsuario_Sufragio = new javax.swing.JTextArea();

        setClosable(true);
        setIconifiable(true);
        setTitle("MULTA SUFRAGIO");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/comision logo.jpg"))); // NOI18N

        jPanel14.setBackground(new java.awt.Color(195, 233, 164));

        jLabel58.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel58.setText("LISTA DE SUFRAGIO DE USUARIOS :");

        btn_cargar_asistencia_asamblea1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_cargar_asistencia_asamblea1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/cargar_archivo.png"))); // NOI18N
        btn_cargar_asistencia_asamblea1.setText("CARGAR ARCHIVO");
        btn_cargar_asistencia_asamblea1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cargar_asistencia_asamblea1ActionPerformed(evt);
            }
        });

        txtListaAsistenciaUsuario_Sufragio.setColumns(20);
        txtListaAsistenciaUsuario_Sufragio.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtListaAsistenciaUsuario_Sufragio.setRows(5);
        txtListaAsistenciaUsuario_Sufragio.setWrapStyleWord(true);
        jScrollPane26.setViewportView(txtListaAsistenciaUsuario_Sufragio);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel58, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_cargar_asistencia_asamblea1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jSeparator2)
            .addComponent(jScrollPane26)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_cargar_asistencia_asamblea1)
                    .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane26, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cargar_asistencia_asamblea1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cargar_asistencia_asamblea1ActionPerformed
        try {
            JFileChooser jfc_archivo = new JFileChooser();
            FileNameExtensionFilter filtroImagen = new FileNameExtensionFilter("XLS", "XLSX");
            jfc_archivo.setFileFilter(filtroImagen);
            jfc_archivo.setApproveButtonText("Abrir Excel");
            jfc_archivo.showOpenDialog(null);
            FileInputStream archivo = new FileInputStream(jfc_archivo.getSelectedFile().getAbsolutePath());
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea Registrar?", "Mensaje", JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.YES_OPTION) {
                txtListaAsistenciaUsuario_Sufragio.setText(new utilitario.Excel_Reader().leer_registrar_excel(archivo, 2));
            } else {
                txtListaAsistenciaUsuario_Sufragio.setText(new utilitario.Excel_Reader().leer_excel(archivo, 2));
            }
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }
    }//GEN-LAST:event_btn_cargar_asistencia_asamblea1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cargar_asistencia_asamblea1;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JScrollPane jScrollPane26;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextArea txtListaAsistenciaUsuario_Sufragio;
    // End of variables declaration//GEN-END:variables
}
