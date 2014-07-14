/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componente;

import controlador.BLConstante;
import controlador.BLMovimiento;
import entidad.Constante;
import entidad.ListaMovimiento;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import utilitario.HeaderCellRenderer;

/**
 *
 * @author Richard
 */
public class jifReporte_Movimiento_C extends javax.swing.JInternalFrame {

    /**
     * Creates new form jifReporte_Movimiento_C
     */
    public jifReporte_Movimiento_C() {
        initComponents();
        getcombo_tipooperacion_all();
        txtFechaDesde.setDate(new Date());
        txtFechaHasta.setDate(new Date());
        formatear_tabla();
    }

    private void formatear_tabla() {
        jtb_lista_movimiento.setRowHeight(22);
        JTableHeader jtableHeaderDPV = jtb_lista_movimiento.getTableHeader();
        jtableHeaderDPV.setDefaultRenderer(new HeaderCellRenderer());
        jtb_lista_movimiento.setTableHeader(jtableHeaderDPV);
    }

    private void getcombo_tipooperacion_all() {
        cboTipoOperacion_Movimiento.removeAllItems();
        cboTipoOperacion_Movimiento.addItem("TODOS");
        for (Constante c : new BLConstante().get_constante_all_byClase(5)) {
            cboTipoOperacion_Movimiento.addItem(c);
        }
        AutoCompleteDecorator.decorate(cboTipoOperacion_Movimiento);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtFechaDesde = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        txtFechaHasta = new com.toedter.calendar.JDateChooser();
        chkTipoOperacion = new javax.swing.JCheckBox();
        cboTipoOperacion_Movimiento = new javax.swing.JComboBox();
        btn_rptmovimiento_buscar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtb_lista_movimiento = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setTitle("REPORTE MOVIMIENTO");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/comision_logo.jpg"))); // NOI18N

        jPanel1.setBackground(new java.awt.Color(195, 233, 164));

        txtFechaDesde.setDateFormatString("dd 'de' MMMM 'de' yyyy");
        txtFechaDesde.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("HASTA :");

        txtFechaHasta.setDateFormatString("dd 'de' MMMM 'de' yyyy");
        txtFechaHasta.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        chkTipoOperacion.setBackground(new java.awt.Color(195, 233, 164));
        chkTipoOperacion.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        chkTipoOperacion.setText("TIPO OPERACIÓN :");
        chkTipoOperacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTipoOperacionActionPerformed(evt);
            }
        });

        cboTipoOperacion_Movimiento.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cboTipoOperacion_Movimiento.setEnabled(false);

        btn_rptmovimiento_buscar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_rptmovimiento_buscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/buscar_rpt.png"))); // NOI18N
        btn_rptmovimiento_buscar.setText("BUSCAR");
        btn_rptmovimiento_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_rptmovimiento_buscarActionPerformed(evt);
            }
        });

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));

        jtb_lista_movimiento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "FECHA", "COMPR.", "N°", "EMPRESA", "R.U.C", "CANT.", "DETALLE", "INGRESO", "EGRESO", "SALDO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jtb_lista_movimiento);

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/excel_rpt.png"))); // NOI18N
        jButton1.setText("EXPORTAR");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("DESDE :");
        jLabel1.setToolTipText("");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(txtFechaDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFechaHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(chkTipoOperacion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboTipoOperacion_Movimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(btn_rptmovimiento_buscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addGap(0, 92, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(chkTipoOperacion)
                        .addComponent(cboTipoOperacion_Movimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_rptmovimiento_buscar)
                        .addComponent(jButton1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtFechaHasta, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtFechaDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 516, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_rptmovimiento_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_rptmovimiento_buscarActionPerformed
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        int tipo = 0;
        if (chkTipoOperacion.isSelected()) {
            switch (cboTipoOperacion_Movimiento.getSelectedItem().toString()) {
                case "TOTAL":
                    tipo = 0;
                    break;
                case "DEPOSITO":
                    tipo = 1;
                    break;
                case "EGRESO":
                    tipo = 2;
                    break;
            }
        } else {
            tipo = -1;
        }
        DefaultTableModel temp = (DefaultTableModel) jtb_lista_movimiento.getModel();
        temp.setRowCount(0);
        for (ListaMovimiento lm : new BLMovimiento().get_reporte_movimiento(sdf.format(txtFechaDesde.getDate()), sdf.format(txtFechaHasta.getDate()), tipo)) {
            Object[] datos = {lm.getDat_fecregistro(), lm.getTipoComprobante(), lm.getVar_numeroComprobante(), lm.getInt_proveedor(),
                "", lm.getDec_cantidad(), lm.getVar_concepto(), lm.getDec_ingreso(), lm.getDec_egreso(), lm.getDec_saldo()};
            temp.addRow(datos);
        }

    }//GEN-LAST:event_btn_rptmovimiento_buscarActionPerformed

    private void chkTipoOperacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTipoOperacionActionPerformed
        if (chkTipoOperacion.isSelected()) {
            cboTipoOperacion_Movimiento.setEnabled(true);
        } else {
            cboTipoOperacion_Movimiento.setEnabled(false);
        }
    }//GEN-LAST:event_chkTipoOperacionActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_rptmovimiento_buscar;
    private javax.swing.JComboBox cboTipoOperacion_Movimiento;
    private javax.swing.JCheckBox chkTipoOperacion;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jtb_lista_movimiento;
    private com.toedter.calendar.JDateChooser txtFechaDesde;
    private com.toedter.calendar.JDateChooser txtFechaHasta;
    // End of variables declaration//GEN-END:variables
}
