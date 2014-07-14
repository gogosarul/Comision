/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componente;

import controlador.BLAgricultor;
import controlador.BLLateral;
import controlador.BLTraspaso;
import entidad.Agricultor;
import entidad.Lateral;
import entidad.ListaLateral;
import entidad.ListaTraspasos;
import entidad.SubLateral;
import java.awt.event.KeyEvent;
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
public class jifTraspaso_C extends javax.swing.JInternalFrame {

    int idAgricultor_Traspaso = 0;
    int idLateral_Traspaso = 0;
    int idNuevoAgricultor_Traspaso = 0;
    int idAgri_Traspaso = 0;
    int idLat_Traspaso = 0;

    public jifTraspaso_C() {
        initComponents();
        formatear_estructura_todas_tablas();
        limpiarFomulario_Traspaso();
        get_agricultores_byActivos("");
        getcombo_agricultor_antiguos();
        getcombo_agricultor_nuevos();
        get_lateral_all();
        get_sublatreles_all("");
    }

    private void formatear_estructura_todas_tablas() {
        ((DefaultTableCellRenderer) jtTraspaso.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        jtTraspaso.setRowHeight(22);
        jtTraspaso.getColumnModel().getColumn(0).setMaxWidth(0);
        jtTraspaso.getColumnModel().getColumn(0).setMinWidth(0);
        jtTraspaso.getColumnModel().getColumn(0).setPreferredWidth(0);
        jtTraspaso.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        ((DefaultTableCellRenderer) jtModalLateral_Traspaso.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        jtModalLateral_Traspaso.setRowHeight(22);
        jtModalLateral_Traspaso.getColumnModel().getColumn(0).setMaxWidth(0);
        jtModalLateral_Traspaso.getColumnModel().getColumn(0).setMinWidth(0);
        jtModalLateral_Traspaso.getColumnModel().getColumn(0).setPreferredWidth(0);
        jtModalLateral_Traspaso.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        ((DefaultTableCellRenderer) jtModalAgricultorNuevo_Traspaso.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        jtModalAgricultorNuevo_Traspaso.setRowHeight(22);
        jtModalAgricultorNuevo_Traspaso.getColumnModel().getColumn(0).setMaxWidth(0);
        jtModalAgricultorNuevo_Traspaso.getColumnModel().getColumn(0).setMinWidth(0);
        jtModalAgricultorNuevo_Traspaso.getColumnModel().getColumn(0).setPreferredWidth(0);
        jtModalAgricultorNuevo_Traspaso.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        ((DefaultTableCellRenderer) jtModalAgricultor_Traspaso.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        jtModalAgricultor_Traspaso.setRowHeight(22);
        jtModalAgricultor_Traspaso.getColumnModel().getColumn(0).setMaxWidth(0);
        jtModalAgricultor_Traspaso.getColumnModel().getColumn(0).setMinWidth(0);
        jtModalAgricultor_Traspaso.getColumnModel().getColumn(0).setPreferredWidth(0);
        jtModalAgricultor_Traspaso.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void limpiarFomulario_Traspaso() {
        idAgricultor_Traspaso = 0;
        idLateral_Traspaso = 0;
        idNuevoAgricultor_Traspaso = 0;
        txtAgricultor_Traspaso.setText("");
        txtLateralCliente_Traspaso.setText("");
        txtSubLateralAgricultor_Traspo.setText("");
        txtNroHectares_Traspaso.setText("");
        txtNuevoAgricultor_Traspaso.setText("");
        txtCantidadHectaria_Traspaso.setText("");
        txtNuevoConMedida_Traspaso.setText("");
        txtNuevoSinMedida_Traspaso.setText("");
        txtObservacion_Traspaso.setText("");
        txtNumDocumento_Traspaso.setText("");
    }

    private void gettabla_traspaso_byclientenuevoantiguo(String condicion) {
        DefaultTableModel temp = (DefaultTableModel) jtTraspaso.getModel();
        temp.setRowCount(0);
        for (ListaTraspasos t : new BLTraspaso().get_cliente_all_byclientenuevoantiguo(condicion)) {
            Object[] datos = {t.getInt_id_traspaso(), t.getVar_numdocumento(),
                t.getVar_nombre_antiguo() + ' ' + t.getVar_apepaterno_antiguo() + ' ' + t.getVar_apematerno_antiguo(),
                t.getVar_nombre_nuevo() + ' ' + t.getVar_apepaterno_nuevo() + ' ' + t.getVar_apematerno_nuevo(),
                t.getInt_cantidadtraspaso(), t.getVar_lateral(), t.getVar_sublateral(),
                t.getDec_conmedida(), t.getDec_sinmedida()};
            temp.addRow(datos);
        }
    }

    private void gettabla_lateral_byagricultoractivos(String palabra, int id) {
        DefaultTableModel temp1 = (DefaultTableModel) jtModalLateral_Traspaso.getModel();
        temp1.setRowCount(0);
        for (ListaLateral l : new BLLateral().get_lateral_byactivocliente(palabra, id)) {
            Object[] datos = {l.getInt_id(), l.getVar_descripcion(), l.getVar_descripcion_sublateral(), l.getDec_conmedida(), l.getDec_sinmedida(), l.getInt_numhectareas()};
            temp1.addRow(datos);
        }
    }

    private void get_agricultores_byActivos(String palabra) {
        DefaultTableModel temp1 = (DefaultTableModel) jtModalAgricultor_Traspaso.getModel();
        DefaultTableModel temp2 = (DefaultTableModel) jtModalAgricultorNuevo_Traspaso.getModel();
        temp1.setRowCount(0);
        temp2.setRowCount(0);

        for (Agricultor c : new BLAgricultor().get_agricultores_byActivos(palabra)) {
            Object[] datos = {c.getInt_id(), c.getVar_nombre() + ' ' + c.getVar_apepaterno() + ' ' + c.getVar_apematerno()};
            temp1.addRow(datos);
            temp2.addRow(datos);
        }
    }

    private void getcombo_agricultor_antiguos() {
        cboAntiguoAgricultor_Traspaso.removeAllItems();
        for (Agricultor c : new BLAgricultor().get_agricultores_antiguos()) {
            cboAntiguoAgricultor_Traspaso.addItem(c);
        }
        AutoCompleteDecorator.decorate(cboAntiguoAgricultor_Traspaso);
    }

    private void getcombo_agricultor_nuevos() {
        cboNuevoAgricultor_Traspaso.removeAllItems();
        for (Agricultor c : new BLAgricultor().get_agricultores_nuevos()) {
            cboNuevoAgricultor_Traspaso.addItem(c);
        }
        AutoCompleteDecorator.decorate(cboNuevoAgricultor_Traspaso);
    }

    private void get_lateral_all() {
        cboLateral_Traspaso.removeAllItems();
        for (Lateral lt : new BLLateral().get_lateral_all()) {
            cboLateral_Traspaso.addItem(lt);
        }
        //AutoCompleteDecorator.decorate(cboSubLateral_Traspaso);
    }

    private void get_sublatreles_all(String condicion) {
        cboSubLateral_Traspaso.removeAllItems();
        for (SubLateral sbl : new BLLateral().get_sublateral_all("")) {
            cboSubLateral_Traspaso.addItem(sbl);
        }
        //AutoCompleteDecorator.decorate(cboSubLateral_Traspaso);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jdTraspasoAgricultor = new javax.swing.JDialog();
        jpBuscarAgricultor_Traspaso = new javax.swing.JPanel();
        jLabel52 = new javax.swing.JLabel();
        txtModalAgricultor_Traspaso = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtModalAgricultor_Traspaso = new javax.swing.JTable();
        jdTraspasoNuevoAgricultor = new javax.swing.JDialog();
        jpBuscarAgricultorNuevo_Traspaso = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        txtModalAgricultorNuevo_Traspaso = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtModalAgricultorNuevo_Traspaso = new javax.swing.JTable();
        jdTraspasoLateral = new javax.swing.JDialog();
        jpTraspasoLateral = new javax.swing.JPanel();
        jLabel90 = new javax.swing.JLabel();
        txtModalLateral_Traspaso = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        jtModalLateral_Traspaso = new javax.swing.JTable();
        jpTraspaso = new javax.swing.JPanel();
        jtbTraspaso = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        chkAntiguoDuenio_Agricultor = new javax.swing.JCheckBox();
        btn_Buscar_Traspaso = new javax.swing.JButton();
        chkAntiguoNuevo_Agricultor = new javax.swing.JCheckBox();
        cboAntiguoAgricultor_Traspaso = new org.jdesktop.swingx.JXComboBox();
        cboNuevoAgricultor_Traspaso = new org.jdesktop.swingx.JXComboBox();
        jLabel83 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtTraspaso = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtAgricultor_Traspaso = new javax.swing.JTextField();
        btn_Traspaso_ModalAgricultor = new javax.swing.JButton();
        txtLateralCliente_Traspaso = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtSubLateralAgricultor_Traspo = new javax.swing.JTextField();
        jLabel67 = new javax.swing.JLabel();
        txtNroHectares_Traspaso = new javax.swing.JTextField();
        btn_Traspaso_ModalLateral = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtNuevoAgricultor_Traspaso = new javax.swing.JTextField();
        txtCantidadHectaria_Traspaso = new javax.swing.JTextField();
        btn_Traspaso_ModalNuevoAgricultor = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtNuevoConMedida_Traspaso = new javax.swing.JTextField();
        txtNuevoSinMedida_Traspaso = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        txtObservacion_Traspaso = new javax.swing.JTextArea();
        jLabel55 = new javax.swing.JLabel();
        txtNumDocumento_Traspaso = new javax.swing.JTextField();
        cboLateral_Traspaso = new org.jdesktop.swingx.JXComboBox();
        cboSubLateral_Traspaso = new org.jdesktop.swingx.JXComboBox();
        btn_Cancelar_Traspaso = new javax.swing.JButton();
        btn_Guardar_Traspaso = new javax.swing.JButton();

        jdTraspasoAgricultor.setTitle("USUARIO");

        jpBuscarAgricultor_Traspaso.setBackground(new java.awt.Color(195, 233, 164));

        jLabel52.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel52.setText("Usuario:");

        txtModalAgricultor_Traspaso.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtModalAgricultor_Traspaso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtModalAgricultor_TraspasoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtModalAgricultor_TraspasoKeyTyped(evt);
            }
        });

        jtModalAgricultor_Traspaso.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "USUARIO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtModalAgricultor_Traspaso.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtModalAgricultor_TraspasoMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jtModalAgricultor_Traspaso);

        javax.swing.GroupLayout jpBuscarAgricultor_TraspasoLayout = new javax.swing.GroupLayout(jpBuscarAgricultor_Traspaso);
        jpBuscarAgricultor_Traspaso.setLayout(jpBuscarAgricultor_TraspasoLayout);
        jpBuscarAgricultor_TraspasoLayout.setHorizontalGroup(
            jpBuscarAgricultor_TraspasoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpBuscarAgricultor_TraspasoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpBuscarAgricultor_TraspasoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpBuscarAgricultor_TraspasoLayout.createSequentialGroup()
                        .addComponent(jLabel52)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtModalAgricultor_Traspaso, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 558, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpBuscarAgricultor_TraspasoLayout.setVerticalGroup(
            jpBuscarAgricultor_TraspasoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpBuscarAgricultor_TraspasoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpBuscarAgricultor_TraspasoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel52)
                    .addComponent(txtModalAgricultor_Traspaso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jdTraspasoAgricultorLayout = new javax.swing.GroupLayout(jdTraspasoAgricultor.getContentPane());
        jdTraspasoAgricultor.getContentPane().setLayout(jdTraspasoAgricultorLayout);
        jdTraspasoAgricultorLayout.setHorizontalGroup(
            jdTraspasoAgricultorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpBuscarAgricultor_Traspaso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jdTraspasoAgricultorLayout.setVerticalGroup(
            jdTraspasoAgricultorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpBuscarAgricultor_Traspaso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jdTraspasoNuevoAgricultor.setTitle("NUEVO USUARIO");

        jpBuscarAgricultorNuevo_Traspaso.setBackground(new java.awt.Color(195, 233, 164));

        jLabel53.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel53.setText("USUARIO :");

        txtModalAgricultorNuevo_Traspaso.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtModalAgricultorNuevo_Traspaso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtModalAgricultorNuevo_TraspasoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtModalAgricultorNuevo_TraspasoKeyTyped(evt);
            }
        });

        jtModalAgricultorNuevo_Traspaso.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "USUARIO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtModalAgricultorNuevo_Traspaso.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtModalAgricultorNuevo_TraspasoMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jtModalAgricultorNuevo_Traspaso);

        javax.swing.GroupLayout jpBuscarAgricultorNuevo_TraspasoLayout = new javax.swing.GroupLayout(jpBuscarAgricultorNuevo_Traspaso);
        jpBuscarAgricultorNuevo_Traspaso.setLayout(jpBuscarAgricultorNuevo_TraspasoLayout);
        jpBuscarAgricultorNuevo_TraspasoLayout.setHorizontalGroup(
            jpBuscarAgricultorNuevo_TraspasoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpBuscarAgricultorNuevo_TraspasoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpBuscarAgricultorNuevo_TraspasoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 587, Short.MAX_VALUE)
                    .addGroup(jpBuscarAgricultorNuevo_TraspasoLayout.createSequentialGroup()
                        .addComponent(jLabel53)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtModalAgricultorNuevo_Traspaso)))
                .addContainerGap())
        );
        jpBuscarAgricultorNuevo_TraspasoLayout.setVerticalGroup(
            jpBuscarAgricultorNuevo_TraspasoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpBuscarAgricultorNuevo_TraspasoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpBuscarAgricultorNuevo_TraspasoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel53)
                    .addComponent(txtModalAgricultorNuevo_Traspaso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jdTraspasoNuevoAgricultorLayout = new javax.swing.GroupLayout(jdTraspasoNuevoAgricultor.getContentPane());
        jdTraspasoNuevoAgricultor.getContentPane().setLayout(jdTraspasoNuevoAgricultorLayout);
        jdTraspasoNuevoAgricultorLayout.setHorizontalGroup(
            jdTraspasoNuevoAgricultorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpBuscarAgricultorNuevo_Traspaso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jdTraspasoNuevoAgricultorLayout.setVerticalGroup(
            jdTraspasoNuevoAgricultorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpBuscarAgricultorNuevo_Traspaso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jpTraspasoLateral.setBackground(new java.awt.Color(195, 233, 164));

        jLabel90.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel90.setText("Lateral:");

        txtModalLateral_Traspaso.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtModalLateral_Traspaso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtModalLateral_TraspasoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtModalLateral_TraspasoKeyTyped(evt);
            }
        });

        jtModalLateral_Traspaso.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "LATERAL", "SUB LATERAL", "CON MEDIDA", "SIN MEDIDA", "NRO HECTAREAS"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtModalLateral_Traspaso.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtModalLateral_TraspasoMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jtModalLateral_Traspaso);

        javax.swing.GroupLayout jpTraspasoLateralLayout = new javax.swing.GroupLayout(jpTraspasoLateral);
        jpTraspasoLateral.setLayout(jpTraspasoLateralLayout);
        jpTraspasoLateralLayout.setHorizontalGroup(
            jpTraspasoLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpTraspasoLateralLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpTraspasoLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 516, Short.MAX_VALUE)
                    .addGroup(jpTraspasoLateralLayout.createSequentialGroup()
                        .addComponent(jLabel90)
                        .addGap(14, 14, 14)
                        .addComponent(txtModalLateral_Traspaso)))
                .addContainerGap())
        );
        jpTraspasoLateralLayout.setVerticalGroup(
            jpTraspasoLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTraspasoLateralLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jpTraspasoLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel90)
                    .addComponent(txtModalLateral_Traspaso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jdTraspasoLateralLayout = new javax.swing.GroupLayout(jdTraspasoLateral.getContentPane());
        jdTraspasoLateral.getContentPane().setLayout(jdTraspasoLateralLayout);
        jdTraspasoLateralLayout.setHorizontalGroup(
            jdTraspasoLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpTraspasoLateral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jdTraspasoLateralLayout.setVerticalGroup(
            jdTraspasoLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpTraspasoLateral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setClosable(true);
        setIconifiable(true);
        setTitle("REALIZAR TRASPASO");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/comision_logo.jpg"))); // NOI18N

        jtbTraspaso.setBackground(new java.awt.Color(195, 233, 164));
        jtbTraspaso.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jtbTraspaso.setOpaque(true);

        jPanel5.setBackground(new java.awt.Color(195, 233, 164));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        chkAntiguoDuenio_Agricultor.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chkAntiguoDuenio_Agricultor.setText(" Antiguo Dueño :");
        chkAntiguoDuenio_Agricultor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkAntiguoDuenio_AgricultorActionPerformed(evt);
            }
        });

        btn_Buscar_Traspaso.setBackground(new java.awt.Color(204, 255, 204));
        btn_Buscar_Traspaso.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_Buscar_Traspaso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/Search-icon.png"))); // NOI18N
        btn_Buscar_Traspaso.setText("CONSULTAR");
        btn_Buscar_Traspaso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Buscar_TraspasoActionPerformed(evt);
            }
        });

        chkAntiguoNuevo_Agricultor.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chkAntiguoNuevo_Agricultor.setText(" Nuevo Dueño :");
        chkAntiguoNuevo_Agricultor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkAntiguoNuevo_AgricultorActionPerformed(evt);
            }
        });

        cboAntiguoAgricultor_Traspaso.setEnabled(false);
        cboAntiguoAgricultor_Traspaso.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cboAntiguoAgricultor_Traspaso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cboAntiguoAgricultor_TraspasoKeyTyped(evt);
            }
        });

        cboNuevoAgricultor_Traspaso.setEnabled(false);
        cboNuevoAgricultor_Traspaso.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkAntiguoDuenio_Agricultor)
                    .addComponent(chkAntiguoNuevo_Agricultor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cboAntiguoAgricultor_Traspaso, javax.swing.GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
                    .addComponent(cboNuevoAgricultor_Traspaso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 138, Short.MAX_VALUE)
                .addComponent(btn_Buscar_Traspaso, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chkAntiguoDuenio_Agricultor)
                            .addComponent(cboAntiguoAgricultor_Traspaso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chkAntiguoNuevo_Agricultor)
                            .addComponent(cboNuevoAgricultor_Traspaso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(btn_Buscar_Traspaso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        jLabel83.setBackground(new java.awt.Color(0, 153, 153));
        jLabel83.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel83.setForeground(new java.awt.Color(255, 255, 255));
        jLabel83.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel83.setText("LISTA DE TRASPASOS DE ÁREA DE CULTIVO");
        jLabel83.setOpaque(true);

        jtTraspaso.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "N° Documento", "Antiguo Dueño", "Nuevo Dueño ", "Cantidad Traspaso", "Lateral", "Sub Lateral", "Con Medida", "Sin Medida"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jtTraspaso);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel83, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
                .addContainerGap())
        );

        jtbTraspaso.addTab("CONSULTAR   ", new javax.swing.ImageIcon(getClass().getResource("/recurso/Consultar.png")), jPanel5); // NOI18N

        jPanel6.setBackground(new java.awt.Color(195, 233, 164));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jPanel3.setBackground(new java.awt.Color(195, 233, 164));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Dueño Antiguo", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Usuario:");

        txtAgricultor_Traspaso.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtAgricultor_Traspaso.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAgricultor_Traspaso.setEnabled(false);

        btn_Traspaso_ModalAgricultor.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_Traspaso_ModalAgricultor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/Search.png"))); // NOI18N
        btn_Traspaso_ModalAgricultor.setText("Buscar Usuario");
        btn_Traspaso_ModalAgricultor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Traspaso_ModalAgricultorActionPerformed(evt);
            }
        });

        txtLateralCliente_Traspaso.setEditable(false);
        txtLateralCliente_Traspaso.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtLateralCliente_Traspaso.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Lateral:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Sub Lateral:");

        txtSubLateralAgricultor_Traspo.setEditable(false);
        txtSubLateralAgricultor_Traspo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtSubLateralAgricultor_Traspo.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel67.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel67.setText("# Hectareas :");

        txtNroHectares_Traspaso.setEditable(false);
        txtNroHectares_Traspaso.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNroHectares_Traspaso.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        btn_Traspaso_ModalLateral.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_Traspaso_ModalLateral.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/Search.png"))); // NOI18N
        btn_Traspaso_ModalLateral.setText("Buscar Lateral");
        btn_Traspaso_ModalLateral.setEnabled(false);
        btn_Traspaso_ModalLateral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Traspaso_ModalLateralActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(txtSubLateralAgricultor_Traspo, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtLateralCliente_Traspaso)
                            .addComponent(txtAgricultor_Traspaso))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btn_Traspaso_ModalLateral, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_Traspaso_ModalAgricultor, javax.swing.GroupLayout.Alignment.LEADING))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNroHectares_Traspaso, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(39, 39, 39))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(txtAgricultor_Traspaso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_Traspaso_ModalAgricultor))
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtLateralCliente_Traspaso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Traspaso_ModalLateral))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel67, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNroHectares_Traspaso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtSubLateralAgricultor_Traspo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11)))
                .addGap(7, 7, 7))
        );

        jPanel4.setBackground(new java.awt.Color(195, 233, 164));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Traspaso", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("Cantidad Hectario a Traspaso:");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("Nuevo Usuario:");

        txtNuevoAgricultor_Traspaso.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNuevoAgricultor_Traspaso.setEnabled(false);
        txtNuevoAgricultor_Traspaso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNuevoAgricultor_TraspasoKeyTyped(evt);
            }
        });

        txtCantidadHectaria_Traspaso.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtCantidadHectaria_Traspaso.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCantidadHectaria_Traspaso.setEnabled(false);
        txtCantidadHectaria_Traspaso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadHectaria_TraspasoKeyTyped(evt);
            }
        });

        btn_Traspaso_ModalNuevoAgricultor.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_Traspaso_ModalNuevoAgricultor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/Search.png"))); // NOI18N
        btn_Traspaso_ModalNuevoAgricultor.setText("Buscar Usuario");
        btn_Traspaso_ModalNuevoAgricultor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Traspaso_ModalNuevoAgricultorActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("Lateral:");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setText("Sub Lateral:");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setText("Con Medida:");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setText("Sin Medida:");

        txtNuevoConMedida_Traspaso.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNuevoConMedida_Traspaso.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNuevoConMedida_Traspaso.setEnabled(false);
        txtNuevoConMedida_Traspaso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNuevoConMedida_TraspasoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNuevoConMedida_TraspasoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNuevoConMedida_TraspasoKeyTyped(evt);
            }
        });

        txtNuevoSinMedida_Traspaso.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNuevoSinMedida_Traspaso.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNuevoSinMedida_Traspaso.setEnabled(false);
        txtNuevoSinMedida_Traspaso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNuevoSinMedida_TraspasoKeyTyped(evt);
            }
        });

        jLabel54.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel54.setText("Observacion:");

        txtObservacion_Traspaso.setColumns(20);
        txtObservacion_Traspaso.setRows(5);
        txtObservacion_Traspaso.setEnabled(false);
        txtObservacion_Traspaso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtObservacion_TraspasoKeyTyped(evt);
            }
        });
        jScrollPane10.setViewportView(txtObservacion_Traspaso);

        jLabel55.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel55.setText("N° Documento:");

        txtNumDocumento_Traspaso.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNumDocumento_Traspaso.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNumDocumento_Traspaso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumDocumento_TraspasoKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(jLabel14)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtCantidadHectaria_Traspaso, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE))
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel17)
                                        .addComponent(jLabel15))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtNuevoConMedida_Traspaso)
                                        .addComponent(cboLateral_Traspaso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(18, 18, 18)
                                .addComponent(txtNuevoAgricultor_Traspaso, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel16))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNuevoSinMedida_Traspaso, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                                    .addComponent(cboSubLateral_Traspaso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(btn_Traspaso_ModalNuevoAgricultor, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel54)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane10))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel55)
                        .addGap(18, 18, 18)
                        .addComponent(txtNumDocumento_Traspaso, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNumDocumento_Traspaso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel55))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtNuevoAgricultor_Traspaso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Traspaso_ModalNuevoAgricultor))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtCantidadHectaria_Traspaso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(cboLateral_Traspaso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboSubLateral_Traspaso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtNuevoConMedida_Traspaso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(txtNuevoSinMedida_Traspaso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel54)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        btn_Cancelar_Traspaso.setBackground(new java.awt.Color(255, 102, 0));
        btn_Cancelar_Traspaso.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_Cancelar_Traspaso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/Cancel-icon.png"))); // NOI18N
        btn_Cancelar_Traspaso.setText("CANCELAR");
        btn_Cancelar_Traspaso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Cancelar_TraspasoActionPerformed(evt);
            }
        });

        btn_Guardar_Traspaso.setBackground(new java.awt.Color(255, 102, 0));
        btn_Guardar_Traspaso.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_Guardar_Traspaso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/Save-icon.png"))); // NOI18N
        btn_Guardar_Traspaso.setText("GUARDAR");
        btn_Guardar_Traspaso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Guardar_TraspasoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_Guardar_Traspaso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_Cancelar_Traspaso, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(btn_Cancelar_Traspaso, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_Guardar_Traspaso, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(153, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 335, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        jtbTraspaso.addTab("REGISTRAR  ", new javax.swing.ImageIcon(getClass().getResource("/recurso/registro_2.png")), jPanel6); // NOI18N

        javax.swing.GroupLayout jpTraspasoLayout = new javax.swing.GroupLayout(jpTraspaso);
        jpTraspaso.setLayout(jpTraspasoLayout);
        jpTraspasoLayout.setHorizontalGroup(
            jpTraspasoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTraspasoLayout.createSequentialGroup()
                .addComponent(jtbTraspaso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jpTraspasoLayout.setVerticalGroup(
            jpTraspasoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTraspasoLayout.createSequentialGroup()
                .addComponent(jtbTraspaso, javax.swing.GroupLayout.PREFERRED_SIZE, 557, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpTraspaso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpTraspaso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void chkAntiguoDuenio_AgricultorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAntiguoDuenio_AgricultorActionPerformed
        if (chkAntiguoDuenio_Agricultor.isSelected()) {
            cboAntiguoAgricultor_Traspaso.setEnabled(true);
        } else {
            cboAntiguoAgricultor_Traspaso.setEnabled(false);
        }
    }//GEN-LAST:event_chkAntiguoDuenio_AgricultorActionPerformed

    private void btn_Buscar_TraspasoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Buscar_TraspasoActionPerformed
        try {
            if (chkAntiguoNuevo_Agricultor.isSelected()) {
                int idAgri_Nuevo = ((Agricultor) cboNuevoAgricultor_Traspaso.getSelectedItem()).getInt_id();
                gettabla_traspaso_byclientenuevoantiguo(" int_id_nuevo=" + idAgri_Nuevo);
            }
            if (chkAntiguoDuenio_Agricultor.isSelected()) {
                int idAgri_Antiguo = ((Agricultor) cboAntiguoAgricultor_Traspaso.getSelectedItem()).getInt_id();
                gettabla_traspaso_byclientenuevoantiguo(" int_clienteAntiguo_id=" + idAgri_Antiguo);
            }
        } catch (Exception e) {
            System.out.println("Error de Listado-Vista" + e.getMessage());
        }
    }//GEN-LAST:event_btn_Buscar_TraspasoActionPerformed

    private void chkAntiguoNuevo_AgricultorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAntiguoNuevo_AgricultorActionPerformed
        if (chkAntiguoNuevo_Agricultor.isSelected()) {
            cboNuevoAgricultor_Traspaso.setEnabled(true);
        } else {
            cboNuevoAgricultor_Traspaso.setEnabled(false);
        }
    }//GEN-LAST:event_chkAntiguoNuevo_AgricultorActionPerformed

    private void cboAntiguoAgricultor_TraspasoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cboAntiguoAgricultor_TraspasoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_cboAntiguoAgricultor_TraspasoKeyTyped

    private void btn_Traspaso_ModalAgricultorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Traspaso_ModalAgricultorActionPerformed
        try {
            jdTraspasoAgricultor.pack();
            jdTraspasoAgricultor.setLocationRelativeTo(null);
            jdTraspasoAgricultor.getRootPane().registerKeyboardAction(new CloseDialogEscape(jdTraspasoAgricultor),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
            jdTraspasoAgricultor.setModal(true);
            jdTraspasoAgricultor.setVisible(true);
        } catch (Exception e) {
            System.out.println("Error al cargar Traspsado" + e.toString());
        }
    }//GEN-LAST:event_btn_Traspaso_ModalAgricultorActionPerformed

    private void btn_Traspaso_ModalLateralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Traspaso_ModalLateralActionPerformed
        try {
            gettabla_lateral_byagricultoractivos("", idAgri_Traspaso);
            jdTraspasoLateral.pack();
            jdTraspasoLateral.setLocationRelativeTo(null);
            jdTraspasoLateral.getRootPane().registerKeyboardAction(new CloseDialogEscape(jdTraspasoAgricultor),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
            jdTraspasoLateral.setModal(true);
            jdTraspasoLateral.setVisible(true);
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }
    }//GEN-LAST:event_btn_Traspaso_ModalLateralActionPerformed

    private void txtNuevoAgricultor_TraspasoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNuevoAgricultor_TraspasoKeyTyped

    }//GEN-LAST:event_txtNuevoAgricultor_TraspasoKeyTyped

    private void txtCantidadHectaria_TraspasoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadHectaria_TraspasoKeyTyped
        new Funciones().soloDecimales(evt);
        if (txtCantidadHectaria_Traspaso.getText().length() == 16) {
            evt.consume();
        }
    }//GEN-LAST:event_txtCantidadHectaria_TraspasoKeyTyped

    private void btn_Traspaso_ModalNuevoAgricultorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Traspaso_ModalNuevoAgricultorActionPerformed
        try {
            jdTraspasoNuevoAgricultor.pack();
            jdTraspasoNuevoAgricultor.setLocationRelativeTo(null);
            jdTraspasoNuevoAgricultor.getRootPane().registerKeyboardAction(new CloseDialogEscape(jdTraspasoNuevoAgricultor),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
            jdTraspasoNuevoAgricultor.setModal(true);
            jdTraspasoNuevoAgricultor.setVisible(true);
        } catch (Exception e) {
            System.out.println("Error al cargar Traspaso Nuevo " + e.toString());
        }
    }//GEN-LAST:event_btn_Traspaso_ModalNuevoAgricultorActionPerformed

    private void txtNuevoConMedida_TraspasoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNuevoConMedida_TraspasoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNuevoConMedida_TraspasoKeyPressed

    private void txtNuevoConMedida_TraspasoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNuevoConMedida_TraspasoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNuevoConMedida_TraspasoKeyReleased

    private void txtNuevoConMedida_TraspasoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNuevoConMedida_TraspasoKeyTyped
        new Funciones().soloDecimales(evt);
        if (txtNuevoConMedida_Traspaso.getText().length() == 16) {
            evt.consume();
        }
    }//GEN-LAST:event_txtNuevoConMedida_TraspasoKeyTyped

    private void txtNuevoSinMedida_TraspasoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNuevoSinMedida_TraspasoKeyTyped
        new Funciones().soloDecimales(evt);
        if (txtNuevoSinMedida_Traspaso.getText().length() == 16) {
            evt.consume();
        }
    }//GEN-LAST:event_txtNuevoSinMedida_TraspasoKeyTyped

    private void txtObservacion_TraspasoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtObservacion_TraspasoKeyTyped
        new Funciones().convertir_mayuscula(evt);
    }//GEN-LAST:event_txtObservacion_TraspasoKeyTyped

    private void txtNumDocumento_TraspasoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumDocumento_TraspasoKeyTyped
        if (txtNumDocumento_Traspaso.getText().length() == 10) {
            evt.consume();
        }
        new Funciones().convertir_mayuscula(evt);
    }//GEN-LAST:event_txtNumDocumento_TraspasoKeyTyped

    private void btn_Cancelar_TraspasoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Cancelar_TraspasoActionPerformed
        limpiarFomulario_Traspaso();
    }//GEN-LAST:event_btn_Cancelar_TraspasoActionPerformed

    private void btn_Guardar_TraspasoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Guardar_TraspasoActionPerformed
        try {
            if (txtCantidadHectaria_Traspaso.getText().compareTo("") != 0 && txtNumDocumento_Traspaso.getText().compareTo("") != 0
                    && txtNuevoConMedida_Traspaso.getText().compareTo("") != 0 && txtNuevoSinMedida_Traspaso.getText().compareTo("") != 0
                    && txtAgricultor_Traspaso.getText().compareTo("") != 0 && txtLateralCliente_Traspaso.getText().compareTo("") != 0
                    && txtSubLateralAgricultor_Traspo.getText().compareTo("") != 0 && txtNroHectares_Traspaso.getText().compareTo("") != 0
                    && txtNuevoAgricultor_Traspaso.getText().compareTo("") != 0) {
                BLTraspaso t = new BLTraspaso();
                int cant = Integer.parseInt(txtCantidadHectaria_Traspaso.getText());
                boolean resultado = t.RegistrarTraspaso(idNuevoAgricultor_Traspaso, 1, cant, idAgri_Traspaso, idLat_Traspaso,
                        ((Lateral) cboLateral_Traspaso.getSelectedItem()).getInt_id(),
                        ((SubLateral) cboSubLateral_Traspaso.getSelectedItem()).getInt_id(),
                        //txtNuevoSubLateral_Traspaso.getText(),
                        Double.parseDouble(txtNuevoConMedida_Traspaso.getText()), Double.parseDouble(txtNuevoSinMedida_Traspaso.getText()),
                        txtObservacion_Traspaso.getText(), txtNumDocumento_Traspaso.getText());

                if (resultado == true) {
                    JOptionPane.showMessageDialog(null, "Se realizo el Traspaso Correctamente");
                    limpiarFomulario_Traspaso();
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo traspasar");
                    limpiarFomulario_Traspaso();
                }
            } else {
                JOptionPane.showMessageDialog(null, "No se admite campos vacios", "ALERTA", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error" + e.getMessage());
        }
    }//GEN-LAST:event_btn_Guardar_TraspasoActionPerformed

    private void txtModalAgricultor_TraspasoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtModalAgricultor_TraspasoKeyReleased
        get_agricultores_byActivos(txtModalAgricultor_Traspaso.getText());
    }//GEN-LAST:event_txtModalAgricultor_TraspasoKeyReleased

    private void txtModalAgricultor_TraspasoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtModalAgricultor_TraspasoKeyTyped
        Character c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            evt.setKeyChar(Character.toUpperCase(c));
        }
    }//GEN-LAST:event_txtModalAgricultor_TraspasoKeyTyped

    private void txtModalAgricultorNuevo_TraspasoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtModalAgricultorNuevo_TraspasoKeyReleased
        try {
            get_agricultores_byActivos(txtModalAgricultorNuevo_Traspaso.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_txtModalAgricultorNuevo_TraspasoKeyReleased

    private void txtModalAgricultorNuevo_TraspasoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtModalAgricultorNuevo_TraspasoKeyTyped
        Character c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            evt.setKeyChar(Character.toUpperCase(c));
        }
    }//GEN-LAST:event_txtModalAgricultorNuevo_TraspasoKeyTyped

    private void txtModalLateral_TraspasoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtModalLateral_TraspasoKeyReleased
        gettabla_lateral_byagricultoractivos(txtModalLateral_Traspaso.getText(), idAgri_Traspaso);
    }//GEN-LAST:event_txtModalLateral_TraspasoKeyReleased

    private void txtModalLateral_TraspasoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtModalLateral_TraspasoKeyTyped
        Character c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            evt.setKeyChar(Character.toUpperCase(c));
        }
    }//GEN-LAST:event_txtModalLateral_TraspasoKeyTyped

    private void jtModalAgricultorNuevo_TraspasoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtModalAgricultorNuevo_TraspasoMouseClicked
        try {
            idNuevoAgricultor_Traspaso = Integer.parseInt(String.valueOf(jtModalAgricultorNuevo_Traspaso.getModel().getValueAt(jtModalAgricultorNuevo_Traspaso.getSelectedRow(), 0)));
            txtNuevoAgricultor_Traspaso.setText(String.valueOf(jtModalAgricultorNuevo_Traspaso.getModel().getValueAt(jtModalAgricultorNuevo_Traspaso.getSelectedRow(), 1)));
            System.out.println(idNuevoAgricultor_Traspaso);
        } catch (Exception e) {
            System.out.println("" + e.toString());
        } finally {
            jdTraspasoNuevoAgricultor.dispose();
        }
    }//GEN-LAST:event_jtModalAgricultorNuevo_TraspasoMouseClicked

    private void jtModalAgricultor_TraspasoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtModalAgricultor_TraspasoMouseClicked
        try {
            idAgri_Traspaso = Integer.parseInt(String.valueOf(jtModalAgricultor_Traspaso.getModel().getValueAt(jtModalAgricultor_Traspaso.getSelectedRow(), 0)));
            txtAgricultor_Traspaso.setText(String.valueOf(jtModalAgricultor_Traspaso.getModel().getValueAt(jtModalAgricultor_Traspaso.getSelectedRow(), 1)));
            btn_Traspaso_ModalLateral.setEnabled(true);
            System.out.println(idAgri_Traspaso);
            txtNumDocumento_Traspaso.setEnabled(true);
            txtCantidadHectaria_Traspaso.setEnabled(true);
            //txtNuevoLateral_Traspaso.setEnabled(true);
            //txtNuevoSubLateral_Traspaso.setEnabled(true);
            txtNuevoConMedida_Traspaso.setEnabled(true);
            txtNuevoSinMedida_Traspaso.setEnabled(true);
            txtObservacion_Traspaso.setEnabled(true);

        } catch (Exception e) {
            System.out.println("" + e.toString());
        } finally {
            jdTraspasoAgricultor.dispose();
        }
    }//GEN-LAST:event_jtModalAgricultor_TraspasoMouseClicked

    private void jtModalLateral_TraspasoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtModalLateral_TraspasoMouseClicked
        try {
            idLat_Traspaso = Integer.parseInt(String.valueOf(jtModalLateral_Traspaso.getModel().getValueAt(jtModalLateral_Traspaso.getSelectedRow(), 0)));
            String lateral = String.valueOf(jtModalLateral_Traspaso.getModel().getValueAt(jtModalLateral_Traspaso.getSelectedRow(), 1));
            String sublateral = String.valueOf(jtModalLateral_Traspaso.getModel().getValueAt(jtModalLateral_Traspaso.getSelectedRow(), 2));
            String hec = String.valueOf(jtModalLateral_Traspaso.getModel().getValueAt(jtModalLateral_Traspaso.getSelectedRow(), 5));
            txtLateralCliente_Traspaso.setText(lateral);
            txtSubLateralAgricultor_Traspo.setText(sublateral);
            txtNroHectares_Traspaso.setText(hec);
        } catch (Exception e) {
            System.out.println("Error al obtener los datos de Lateral for Traspaso" + e.getMessage());
        } finally {
            jdTraspasoLateral.dispose();
        }
    }//GEN-LAST:event_jtModalLateral_TraspasoMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Buscar_Traspaso;
    private javax.swing.JButton btn_Cancelar_Traspaso;
    private javax.swing.JButton btn_Guardar_Traspaso;
    private javax.swing.JButton btn_Traspaso_ModalAgricultor;
    private javax.swing.JButton btn_Traspaso_ModalLateral;
    private javax.swing.JButton btn_Traspaso_ModalNuevoAgricultor;
    private org.jdesktop.swingx.JXComboBox cboAntiguoAgricultor_Traspaso;
    private org.jdesktop.swingx.JXComboBox cboLateral_Traspaso;
    private org.jdesktop.swingx.JXComboBox cboNuevoAgricultor_Traspaso;
    private org.jdesktop.swingx.JXComboBox cboSubLateral_Traspaso;
    private javax.swing.JCheckBox chkAntiguoDuenio_Agricultor;
    private javax.swing.JCheckBox chkAntiguoNuevo_Agricultor;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JDialog jdTraspasoAgricultor;
    private javax.swing.JDialog jdTraspasoLateral;
    private javax.swing.JDialog jdTraspasoNuevoAgricultor;
    private javax.swing.JPanel jpBuscarAgricultorNuevo_Traspaso;
    private javax.swing.JPanel jpBuscarAgricultor_Traspaso;
    private javax.swing.JPanel jpTraspaso;
    private javax.swing.JPanel jpTraspasoLateral;
    private javax.swing.JTable jtModalAgricultorNuevo_Traspaso;
    private javax.swing.JTable jtModalAgricultor_Traspaso;
    private javax.swing.JTable jtModalLateral_Traspaso;
    private javax.swing.JTable jtTraspaso;
    private javax.swing.JTabbedPane jtbTraspaso;
    private javax.swing.JTextField txtAgricultor_Traspaso;
    private javax.swing.JTextField txtCantidadHectaria_Traspaso;
    private javax.swing.JTextField txtLateralCliente_Traspaso;
    private javax.swing.JTextField txtModalAgricultorNuevo_Traspaso;
    private javax.swing.JTextField txtModalAgricultor_Traspaso;
    private javax.swing.JTextField txtModalLateral_Traspaso;
    private javax.swing.JTextField txtNroHectares_Traspaso;
    private javax.swing.JTextField txtNuevoAgricultor_Traspaso;
    private javax.swing.JTextField txtNuevoConMedida_Traspaso;
    private javax.swing.JTextField txtNuevoSinMedida_Traspaso;
    private javax.swing.JTextField txtNumDocumento_Traspaso;
    private javax.swing.JTextArea txtObservacion_Traspaso;
    private javax.swing.JTextField txtSubLateralAgricultor_Traspo;
    // End of variables declaration//GEN-END:variables
}
