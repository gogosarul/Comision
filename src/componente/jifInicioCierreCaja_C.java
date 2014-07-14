/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componente;

import utilitario.Funciones;

/**
 *
 * @author Richard
 */
public class jifInicioCierreCaja_C extends javax.swing.JInternalFrame {

    /**
     * Creates new form jifInicioCierreCaja
     */
    public jifInicioCierreCaja_C() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpInicioCierre = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        txtMontoInicial_InicioCierreCaja = new org.jdesktop.swingx.JXTextField();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("INICIO / CIERRE CAJA");
        setToolTipText("");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/comision logo.jpg"))); // NOI18N

        jpInicioCierre.setBackground(new java.awt.Color(195, 233, 164));

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel43.setText("Monto Incial :");

        txtMontoInicial_InicioCierreCaja.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMontoInicial_InicioCierreCaja.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtMontoInicial_InicioCierreCaja.setPrompt("S/. 0.00");
        txtMontoInicial_InicioCierreCaja.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMontoInicial_InicioCierreCajaKeyTyped(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/money.png"))); // NOI18N
        jButton4.setText("INICIAR CAJA");
        jButton4.setIconTextGap(20);

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/Exit.png"))); // NOI18N
        jButton5.setText("CERRAR CAJA");
        jButton5.setIconTextGap(20);

        javax.swing.GroupLayout jpInicioCierreLayout = new javax.swing.GroupLayout(jpInicioCierre);
        jpInicioCierre.setLayout(jpInicioCierreLayout);
        jpInicioCierreLayout.setHorizontalGroup(
            jpInicioCierreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpInicioCierreLayout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addGroup(jpInicioCierreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jpInicioCierreLayout.createSequentialGroup()
                        .addComponent(jLabel43)
                        .addGap(18, 18, 18)
                        .addComponent(txtMontoInicial_InicioCierreCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(87, Short.MAX_VALUE))
        );
        jpInicioCierreLayout.setVerticalGroup(
            jpInicioCierreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpInicioCierreLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jpInicioCierreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMontoInicial_InicioCierreCaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel43))
                .addGap(18, 18, 18)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpInicioCierre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpInicioCierre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtMontoInicial_InicioCierreCajaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoInicial_InicioCierreCajaKeyTyped
        new Funciones().soloDecimales(evt);
        if (txtMontoInicial_InicioCierreCaja.getText().length() == 10) {
            evt.consume();
        }
    }//GEN-LAST:event_txtMontoInicial_InicioCierreCajaKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JPanel jpInicioCierre;
    private org.jdesktop.swingx.JXTextField txtMontoInicial_InicioCierreCaja;
    // End of variables declaration//GEN-END:variables
}
