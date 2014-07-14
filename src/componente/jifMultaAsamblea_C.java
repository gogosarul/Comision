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
public class jifMultaAsamblea_C extends javax.swing.JInternalFrame {

    /**
     * Creates new form jifMultaAsamblea_C
     */
    public jifMultaAsamblea_C() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel57 = new javax.swing.JLabel();
        btn_cargar_asistencia_asamblea = new javax.swing.JButton();
        jScrollPane21 = new javax.swing.JScrollPane();
        txtListaAsistenciaUsuario_Asamblea = new javax.swing.JTextArea();
        jSeparator1 = new javax.swing.JSeparator();

        setBackground(new java.awt.Color(195, 233, 164));
        setClosable(true);
        setIconifiable(true);
        setTitle("PADRON MULTA - ASISTENCIA DE ASAMBLEA");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/comision logo.jpg"))); // NOI18N
        setOpaque(true);

        jLabel57.setBackground(new java.awt.Color(195, 233, 164));
        jLabel57.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel57.setText("LISTA DE ASISTENCIA DE USUARIOS ASAMBLEA :");
        jLabel57.setOpaque(true);

        btn_cargar_asistencia_asamblea.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_cargar_asistencia_asamblea.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/cargar_archivo.png"))); // NOI18N
        btn_cargar_asistencia_asamblea.setText("CARGAR ARCHIVO");
        btn_cargar_asistencia_asamblea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cargar_asistencia_asambleaActionPerformed(evt);
            }
        });

        txtListaAsistenciaUsuario_Asamblea.setColumns(20);
        txtListaAsistenciaUsuario_Asamblea.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtListaAsistenciaUsuario_Asamblea.setRows(5);
        txtListaAsistenciaUsuario_Asamblea.setWrapStyleWord(true);
        jScrollPane21.setViewportView(txtListaAsistenciaUsuario_Asamblea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel57, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_cargar_asistencia_asamblea, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jSeparator1)
            .addComponent(jScrollPane21)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_cargar_asistencia_asamblea)
                    .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane21, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cargar_asistencia_asambleaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cargar_asistencia_asambleaActionPerformed
        try {
            JFileChooser jfc_archivo = new JFileChooser();
            FileNameExtensionFilter filtroImagen = new FileNameExtensionFilter("XLS", "XLSX");
            jfc_archivo.setFileFilter(filtroImagen);
            jfc_archivo.setApproveButtonText("Abrir Excel");
            jfc_archivo.showOpenDialog(null);
            FileInputStream archivo = new FileInputStream(jfc_archivo.getSelectedFile().getAbsolutePath());
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea Registrar?", "Mensaje", JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.YES_OPTION) {
                txtListaAsistenciaUsuario_Asamblea.setText(new utilitario.Excel_Reader().leer_registrar_excel(archivo, 1));/**/

            } else {
                txtListaAsistenciaUsuario_Asamblea.setText(new utilitario.Excel_Reader().leer_excel(archivo, 1));
            }
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }
    }//GEN-LAST:event_btn_cargar_asistencia_asambleaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cargar_asistencia_asamblea;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea txtListaAsistenciaUsuario_Asamblea;
    // End of variables declaration//GEN-END:variables
}
