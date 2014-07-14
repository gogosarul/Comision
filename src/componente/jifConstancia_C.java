/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componente;

import controlador.BLAgricultor;
import controlador.BLComite;
import controlador.BLConstancia;
import controlador.BLConstante;
import controlador.BLCuenta;
import controlador.BLLateral;
import controlador.BLPeriodo;
import controlador.BLUsuario;
import entidad.Agricultor;
import entidad.Comite;
import entidad.Constancia;
import entidad.Constante;
import entidad.ListaConstancia;
import entidad.ListaCuentaMonto;
import entidad.ListaLateral;
import entidad.PeriodoCampania;
import entidad.Usuario;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
public class jifConstancia_C extends javax.swing.JInternalFrame {

    int idComite_Constancia = 0;
    int idCliente_Constancia = 0;
    int idLateral_Constancia = 0;
    int idPeriodo_Constancia = 0;

    public jifConstancia_C() {
        initComponents();
        formatear_estructura_todas_tablas();
        getcombo_cliente_all();
        getcombo_periodo_all();
        getcombo_tipocultivo_all();
        limpiarFomulario_Constancia();

    }

    private void formatear_estructura_todas_tablas() {
        ((DefaultTableCellRenderer) jtBusqueda_Constancia.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        jtBusqueda_Constancia.setRowHeight(22);
        jtBusqueda_Constancia.getColumnModel().getColumn(0).setMaxWidth(0);
        jtBusqueda_Constancia.getColumnModel().getColumn(0).setMinWidth(0);
        jtBusqueda_Constancia.getColumnModel().getColumn(0).setPreferredWidth(0);
        jtBusqueda_Constancia.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        ((DefaultTableCellRenderer) jtModalLateral_Constancia.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        jtModalLateral_Constancia.setRowHeight(22);
        jtModalLateral_Constancia.getColumnModel().getColumn(0).setMaxWidth(0);
        jtModalLateral_Constancia.getColumnModel().getColumn(0).setMinWidth(0);
        jtModalLateral_Constancia.getColumnModel().getColumn(0).setPreferredWidth(0);
        jtModalLateral_Constancia.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        ((DefaultTableCellRenderer) jtModalAgricultor_Constancia.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        jtModalAgricultor_Constancia.setRowHeight(22);
        jtModalAgricultor_Constancia.getColumnModel().getColumn(0).setMaxWidth(0);
        jtModalAgricultor_Constancia.getColumnModel().getColumn(0).setMinWidth(0);
        jtModalAgricultor_Constancia.getColumnModel().getColumn(0).setPreferredWidth(0);
        jtModalAgricultor_Constancia.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void limpiarFomulario_Constancia() {
        idPeriodo_Constancia = 0;
        idCliente_Constancia = 0;
        idComite_Constancia = 0;
        idLateral_Constancia = 0;
        txtFecha_Constancia.setDate(new Date());
        txtComite_Constancia.setText("");
        txtCliente_Constancia.setText("");
        txtCampania_Constancia.setText("");
        txtLateral_Constancia.setText("");
        txtFiltroInicio_Constancia.setDate(new Date());
        txtFiltroFin_Constancia.setDate(new Date());
        txtFiltroInicio_Constancia.setDate(new Date());
        txtFiltroFin_Constancia.setDate(new Date());
        cboAgricultorFiltro_Constancia.setSelectedIndex(0);
        txtMontoComision_Constancia.setText("");
        txtMontoJunta_Constancia.setText("");
        txtHectareas_Constancia.setText("");
        txtPeriodoRango_Constancia.setText("");
    }

    /*COMITE*/
    private void gettabla_comite_byActivos(String palabra) {
        DefaultTableModel temp = (DefaultTableModel) jtModalComite_Constancia.getModel();
        temp.setRowCount(0);
        for (Comite c : new BLComite().get_comite_byActivos(palabra)) {
            Object[] datos = {c.getInt_id(), c.getVar_nombre()};
            temp.addRow(datos);
        }
    }

    private void getcombo_cliente_all() {
        cboAgricultorFiltro_Constancia.removeAllItems();
        for (Agricultor c : new BLAgricultor().get_agricultores_byActivos("")) {
            cboAgricultorFiltro_Constancia.addItem(c);
        }
        AutoCompleteDecorator.decorate(cboAgricultorFiltro_Constancia);
    }

    private void getcombo_periodo_all() {
        cboPeriodoFiltro_Constancia.removeAllItems();
        for (PeriodoCampania pc : new BLPeriodo().get_periodo_all_byactivos()) {
            cboPeriodoFiltro_Constancia.addItem(pc);
        }
        AutoCompleteDecorator.decorate(cboPeriodoFiltro_Constancia);
    }

    private void getcombo_tipocultivo_all() {
        cboTipoCultivo_Constancia.removeAllItems();
        for (Constante c : new BLConstante().get_tipocultivo_all(6)) {
            cboTipoCultivo_Constancia.addItem(c);

        }
        AutoCompleteDecorator.decorate(cboTipoCultivo_Constancia);
    }
    /*FIN COMITE*/

    /*AGRICULTOR*/
    private void gettabla_agricultor_constancia_byActivos(String palabra) {
        DefaultTableModel temp = (DefaultTableModel) jtModalAgricultor_Constancia.getModel();
        temp.setRowCount(0);
        for (Agricultor c : new BLAgricultor().get_agricultores_byActivos(palabra)) {
            Object[] datos = {c.getInt_id(), c.getVar_nombre() + ' ' + c.getVar_apepaterno() + ' ' + c.getVar_apematerno()};
            temp.addRow(datos);
        }
    }

    private void gettabla_lateral_byagricultoractivos(String palabra, int id) {
        DefaultTableModel temp = (DefaultTableModel) jtModalLateral_Constancia.getModel();
        temp.setRowCount(0);
        for (ListaLateral l : new BLLateral().get_lateral_byactivocliente(palabra, id)) {
            Object[] datos = {l.getInt_id(), l.getVar_descripcion(), l.getVar_descripcion_sublateral(), l.getDec_conmedida(), l.getDec_sinmedida(), l.getInt_numhectareas()};
            temp.addRow(datos);
        }
    }

    private void RegistrarConstancia() {
        Constancia c = new Constancia();
        c.setComite_id(idComite_Constancia);
        c.setLateral_id(idLateral_Constancia);
        c.setPeriodo_id(idPeriodo_Constancia);
        c.setInt_campania(Integer.parseInt(txtCampania_Constancia.getText()));
        c.setDec_nrohectaria(Double.parseDouble(txtHectareas_Constancia.getText()));
        if (rbAlmacigo_Constancia.isSelected()) {
            c.setVar_tipoconstancia("A"); // Almacigo
        } else {
            c.setVar_tipoconstancia("B"); // Boleo
        }
        c.setDat_fechRealizacion(new java.sql.Timestamp(txtFechaAlmacigo_constancia.getDate().getTime()));
        c.setDat_fechRegistro(new java.sql.Timestamp(txtFecha_Constancia.getDate().getTime()));
        c.setInt_tipocultivo(((Constante) cboTipoCultivo_Constancia.getSelectedItem()).getInt_valor());
        c.setDec_montoComision(Double.parseDouble(txtMontoComision_Constancia.getText()));
        c.setDec_montoJunta(Double.parseDouble(txtMontoJunta_Constancia.getText()));
        BLConstancia co = new BLConstancia();
        if (co.insertarConstancia(c)) {
            limpiarFomulario_Constancia();
            JOptionPane.showMessageDialog(null, "Registro Exitoso", "MENSAJE", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error al Registrar", "MENSAJE", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buscar_constancia_byfiltro() {
        int contador = 0;
        ArrayList<String> lista = new ArrayList();
        String condicionFinal = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        boolean fecha = chkFecha_Constancia.isSelected();
        boolean campania = chkCampania_Constancia.isSelected();
        boolean cliente = chkCliente_Constancia.isSelected();

        if (fecha == true) {
            lista.add(" ( date(dat_fechRegistro) between '" + sdf.format(txtFiltroInicio_Constancia.getDate()) + "' and '" + sdf.format(txtFiltroFin_Constancia.getDate()) + "' ) ");
            contador++;
        }
        if (campania == true) {
            lista.add(" ( int_periodo =" + ((PeriodoCampania) cboPeriodoFiltro_Constancia.getSelectedItem()).getPeriodo_id() + " )");
            contador++;
        }
        if (cliente == true) {
            lista.add(" ( cliente_id =" + ((Agricultor) cboAgricultorFiltro_Constancia.getSelectedItem()).getInt_id() + " )");
            contador++;
        }
        switch (contador) {
            case 1:
                condicionFinal = lista.get(0);
                break;
            case 2:
                condicionFinal = lista.get(0) + " and " + lista.get(1);
                break;
            case 3:
                condicionFinal = lista.get(0) + " and " + lista.get(1) + " and " + lista.get(2);
                break;
            case 4:
                condicionFinal = lista.get(0) + " and " + lista.get(1) + " and " + lista.get(2) + " and " + lista.get(3);
                break;
        }

        if (condicionFinal.compareTo("") != 0) {
            DefaultTableModel tempConstancia = (DefaultTableModel) jtBusqueda_Constancia.getModel();
            tempConstancia.setRowCount(0);
            for (ListaConstancia l : new BLConstancia().get_constancia_byfiltro(condicionFinal)) {
                Object datos[] = {l.getConstancia_id(), l.getVar_numero(),
                    l.getVar_nombre() + " " + l.getVar_apepaterno() + " " + l.getVar_apematerno(),
                    l.getVar_periodo(), l.getNroCamapania(), l.getVar_lateral(), l.getNroHectarea(), l.getDat_fechRegistro(), l.getTipoSiembra(), l.getFechaSiembra()
                };
                tempConstancia.addRow(datos);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jdConstanciaComite = new javax.swing.JDialog();
        jpBuscarComite = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtModalComite_Constancia = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtModalComite_Constancia = new javax.swing.JTable();
        jdConstanciaAgricultor = new javax.swing.JDialog();
        jPanel10 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        txtModalCliente_Constancia = new javax.swing.JTextField();
        jScrollPane14 = new javax.swing.JScrollPane();
        jtModalAgricultor_Constancia = new javax.swing.JTable();
        jdConstanciaLateral = new javax.swing.JDialog();
        jpConstanciaLateral = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        txtModalLateral_Constancia = new javax.swing.JTextField();
        jScrollPane15 = new javax.swing.JScrollPane();
        jtModalLateral_Constancia = new javax.swing.JTable();
        jdValidacion_Constancia = new javax.swing.JDialog();
        jPanel15 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jLabel65 = new javax.swing.JLabel();
        txtValidacionPass_Constancia = new javax.swing.JPasswordField();
        btn_validar_constancia = new javax.swing.JButton();
        btnTipodeSembrio = new javax.swing.ButtonGroup();
        jpConstancia_Registro = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtComite_Constancia = new javax.swing.JTextField();
        txtCliente_Constancia = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtFecha_Constancia = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        txtLateral_Constancia = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtHectareas_Constancia = new javax.swing.JTextField();
        btn_ModalComite_Constancia = new javax.swing.JButton();
        btn_ModalCliente_Constancia = new javax.swing.JButton();
        btn_ModalLateral_Constancia = new javax.swing.JButton();
        txtPeriodoRango_Constancia = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        txtCampania_Constancia = new javax.swing.JTextField();
        rbAlmacigo_Constancia = new javax.swing.JRadioButton();
        txtFechaAlmacigo_constancia = new com.toedter.calendar.JDateChooser();
        rbBoleo_Constancia = new javax.swing.JRadioButton();
        jLabel48 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        cboTipoCultivo_Constancia = new org.jdesktop.swingx.JXComboBox();
        jLabel60 = new javax.swing.JLabel();
        txtMontoComision_Constancia = new javax.swing.JTextField();
        jLabel61 = new javax.swing.JLabel();
        txtMontoJunta_Constancia = new javax.swing.JTextField();
        jLabel93 = new javax.swing.JLabel();
        txtTotal_Constancia = new javax.swing.JTextField();
        btn_Cancelar_Constancia = new javax.swing.JButton();
        btn_Guardar_Constancia = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        txtFiltroInicio_Constancia = new com.toedter.calendar.JDateChooser();
        txtFiltroFin_Constancia = new com.toedter.calendar.JDateChooser();
        jLabel12 = new javax.swing.JLabel();
        chkFecha_Constancia = new javax.swing.JCheckBox();
        chkCampania_Constancia = new javax.swing.JCheckBox();
        chkCliente_Constancia = new javax.swing.JCheckBox();
        cboPeriodoFiltro_Constancia = new org.jdesktop.swingx.JXComboBox();
        btnBuscarFiltro_Constancia = new javax.swing.JButton();
        cboAgricultorFiltro_Constancia = new org.jdesktop.swingx.JXComboBox();
        jLabel81 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jtBusqueda_Constancia = new javax.swing.JTable();

        jdConstanciaComite.setTitle("Comite");

        jpBuscarComite.setBackground(new java.awt.Color(225, 253, 203));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Comite :");

        txtModalComite_Constancia.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtModalComite_Constancia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtModalComite_ConstanciaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtModalComite_ConstanciaKeyTyped(evt);
            }
        });

        jtModalComite_Constancia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Comite"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtModalComite_Constancia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtModalComite_ConstanciaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jtModalComite_Constancia);

        javax.swing.GroupLayout jpBuscarComiteLayout = new javax.swing.GroupLayout(jpBuscarComite);
        jpBuscarComite.setLayout(jpBuscarComiteLayout);
        jpBuscarComiteLayout.setHorizontalGroup(
            jpBuscarComiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpBuscarComiteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpBuscarComiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane2)
                    .addGroup(jpBuscarComiteLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtModalComite_Constancia, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpBuscarComiteLayout.setVerticalGroup(
            jpBuscarComiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpBuscarComiteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpBuscarComiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtModalComite_Constancia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jdConstanciaComiteLayout = new javax.swing.GroupLayout(jdConstanciaComite.getContentPane());
        jdConstanciaComite.getContentPane().setLayout(jdConstanciaComiteLayout);
        jdConstanciaComiteLayout.setHorizontalGroup(
            jdConstanciaComiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpBuscarComite, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jdConstanciaComiteLayout.setVerticalGroup(
            jdConstanciaComiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpBuscarComite, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jdConstanciaAgricultor.setTitle("BUSCAR AGRICULTOR");

        jPanel10.setBackground(new java.awt.Color(225, 253, 203));

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel22.setText("Usuario:");

        txtModalCliente_Constancia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtModalCliente_Constancia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtModalCliente_ConstanciaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtModalCliente_ConstanciaKeyTyped(evt);
            }
        });

        jtModalAgricultor_Constancia.setModel(new javax.swing.table.DefaultTableModel(
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
        jtModalAgricultor_Constancia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtModalAgricultor_ConstanciaMouseClicked(evt);
            }
        });
        jtModalAgricultor_Constancia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtModalAgricultor_ConstanciaKeyPressed(evt);
            }
        });
        jScrollPane14.setViewportView(jtModalAgricultor_Constancia);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane14, javax.swing.GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addGap(18, 18, 18)
                        .addComponent(txtModalCliente_Constancia)))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(txtModalCliente_Constancia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jdConstanciaAgricultorLayout = new javax.swing.GroupLayout(jdConstanciaAgricultor.getContentPane());
        jdConstanciaAgricultor.getContentPane().setLayout(jdConstanciaAgricultorLayout);
        jdConstanciaAgricultorLayout.setHorizontalGroup(
            jdConstanciaAgricultorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jdConstanciaAgricultorLayout.setVerticalGroup(
            jdConstanciaAgricultorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jdConstanciaLateral.setTitle("Lateral");
        jdConstanciaLateral.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jdConstanciaLateralKeyTyped(evt);
            }
        });

        jpConstanciaLateral.setBackground(new java.awt.Color(225, 253, 203));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setText("Lateral:");

        txtModalLateral_Constancia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtModalLateral_Constancia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtModalLateral_ConstanciaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtModalLateral_ConstanciaKeyTyped(evt);
            }
        });

        jtModalLateral_Constancia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Lateral", "Sub Lateral", "Con Medida", "Sin Medida", "N° Hectareas"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtModalLateral_Constancia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtModalLateral_ConstanciaMouseClicked(evt);
            }
        });
        jScrollPane15.setViewportView(jtModalLateral_Constancia);

        javax.swing.GroupLayout jpConstanciaLateralLayout = new javax.swing.GroupLayout(jpConstanciaLateral);
        jpConstanciaLateral.setLayout(jpConstanciaLateralLayout);
        jpConstanciaLateralLayout.setHorizontalGroup(
            jpConstanciaLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpConstanciaLateralLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpConstanciaLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane15)
                    .addGroup(jpConstanciaLateralLayout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(18, 18, 18)
                        .addComponent(txtModalLateral_Constancia, javax.swing.GroupLayout.PREFERRED_SIZE, 539, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jpConstanciaLateralLayout.setVerticalGroup(
            jpConstanciaLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpConstanciaLateralLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpConstanciaLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtModalLateral_Constancia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jdConstanciaLateralLayout = new javax.swing.GroupLayout(jdConstanciaLateral.getContentPane());
        jdConstanciaLateral.getContentPane().setLayout(jdConstanciaLateralLayout);
        jdConstanciaLateralLayout.setHorizontalGroup(
            jdConstanciaLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpConstanciaLateral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jdConstanciaLateralLayout.setVerticalGroup(
            jdConstanciaLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpConstanciaLateral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jdValidacion_Constancia.setTitle("Validar Usuario");
        jdValidacion_Constancia.setResizable(false);

        jPanel23.setBackground(new java.awt.Color(195, 233, 164));

        jLabel65.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel65.setText("Ingrese Clave:");

        txtValidacionPass_Constancia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtValidacionPass_Constancia.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        btn_validar_constancia.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_validar_constancia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/key_validation.png"))); // NOI18N
        btn_validar_constancia.setText("OK");
        btn_validar_constancia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_validar_constanciaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(jLabel65)
                        .addGap(92, 197, Short.MAX_VALUE))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(txtValidacionPass_Constancia)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_validar_constancia, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel65)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtValidacionPass_Constancia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_validar_constancia))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jdValidacion_ConstanciaLayout = new javax.swing.GroupLayout(jdValidacion_Constancia.getContentPane());
        jdValidacion_Constancia.getContentPane().setLayout(jdValidacion_ConstanciaLayout);
        jdValidacion_ConstanciaLayout.setHorizontalGroup(
            jdValidacion_ConstanciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jdValidacion_ConstanciaLayout.setVerticalGroup(
            jdValidacion_ConstanciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(195, 233, 164));
        setClosable(true);
        setIconifiable(true);
        setTitle("REGISTRO CONSTANCIA");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/comision_logo.jpg"))); // NOI18N

        jpConstancia_Registro.setBackground(new java.awt.Color(195, 233, 164));
        jpConstancia_Registro.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Comite :");

        txtComite_Constancia.setEditable(false);
        txtComite_Constancia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtCliente_Constancia.setEditable(false);
        txtCliente_Constancia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Usuario:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Periodo :");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Fecha:");

        txtFecha_Constancia.setDateFormatString("dd 'de' MMMM 'de' yyyy");
        txtFecha_Constancia.setFocusable(false);
        txtFecha_Constancia.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtFecha_ConstanciaPropertyChange(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Lateral :");

        txtLateral_Constancia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtLateral_Constancia.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtLateral_Constancia.setEnabled(false);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("# Hectareas :");

        txtHectareas_Constancia.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtHectareas_Constancia.setForeground(new java.awt.Color(255, 153, 0));
        txtHectareas_Constancia.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtHectareas_Constancia.setText("0");
        txtHectareas_Constancia.setToolTipText("");
        txtHectareas_Constancia.setEnabled(false);
        txtHectareas_Constancia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtHectareas_ConstanciaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtHectareas_ConstanciaKeyTyped(evt);
            }
        });

        btn_ModalComite_Constancia.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_ModalComite_Constancia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/Search.png"))); // NOI18N
        btn_ModalComite_Constancia.setText("Buscar Comite");
        btn_ModalComite_Constancia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ModalComite_ConstanciaActionPerformed(evt);
            }
        });

        btn_ModalCliente_Constancia.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_ModalCliente_Constancia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/Search.png"))); // NOI18N
        btn_ModalCliente_Constancia.setText("Buscar Usuario");
        btn_ModalCliente_Constancia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ModalCliente_ConstanciaActionPerformed(evt);
            }
        });

        btn_ModalLateral_Constancia.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_ModalLateral_Constancia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/Search.png"))); // NOI18N
        btn_ModalLateral_Constancia.setText("Buscar Lateral");
        btn_ModalLateral_Constancia.setEnabled(false);
        btn_ModalLateral_Constancia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ModalLateral_ConstanciaActionPerformed(evt);
            }
        });

        txtPeriodoRango_Constancia.setEditable(false);
        txtPeriodoRango_Constancia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtPeriodoRango_Constancia.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel27.setText("# Campaña:");

        txtCampania_Constancia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtCampania_Constancia.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCampania_Constancia.setEnabled(false);

        rbAlmacigo_Constancia.setBackground(new java.awt.Color(195, 233, 164));
        btnTipodeSembrio.add(rbAlmacigo_Constancia);
        rbAlmacigo_Constancia.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        rbAlmacigo_Constancia.setSelected(true);
        rbAlmacigo_Constancia.setText("Almacigo");
        rbAlmacigo_Constancia.setEnabled(false);
        rbAlmacigo_Constancia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbAlmacigo_ConstanciaActionPerformed(evt);
            }
        });

        txtFechaAlmacigo_constancia.setDateFormatString("dd 'de' MMMM 'de' yyyy");
        txtFechaAlmacigo_constancia.setEnabled(false);

        rbBoleo_Constancia.setBackground(new java.awt.Color(195, 233, 164));
        btnTipodeSembrio.add(rbBoleo_Constancia);
        rbBoleo_Constancia.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        rbBoleo_Constancia.setText("Boleo");
        rbBoleo_Constancia.setEnabled(false);
        rbBoleo_Constancia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbBoleo_ConstanciaActionPerformed(evt);
            }
        });

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel48.setText("Sembrio :");

        jLabel89.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel89.setText("Tipo Cultivo:");

        cboTipoCultivo_Constancia.setEnabled(false);
        cboTipoCultivo_Constancia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel60.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel60.setText("Monto Comision:");

        txtMontoComision_Constancia.setEditable(false);
        txtMontoComision_Constancia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtMontoComision_Constancia.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel61.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel61.setText("Monto Junta:");

        txtMontoJunta_Constancia.setEditable(false);
        txtMontoJunta_Constancia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtMontoJunta_Constancia.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel93.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel93.setText("Total:");

        txtTotal_Constancia.setEditable(false);
        txtTotal_Constancia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTotal_Constancia.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jpConstancia_RegistroLayout = new javax.swing.GroupLayout(jpConstancia_Registro);
        jpConstancia_Registro.setLayout(jpConstancia_RegistroLayout);
        jpConstancia_RegistroLayout.setHorizontalGroup(
            jpConstancia_RegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpConstancia_RegistroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpConstancia_RegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpConstancia_RegistroLayout.createSequentialGroup()
                        .addGroup(jpConstancia_RegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1)
                            .addComponent(jLabel8)
                            .addComponent(jLabel48))
                        .addGap(481, 481, 481)
                        .addGroup(jpConstancia_RegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel27)
                            .addComponent(jLabel6))
                        .addGap(16, 16, 16)
                        .addGroup(jpConstancia_RegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtCampania_Constancia, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboTipoCultivo_Constancia, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                            .addComponent(txtPeriodoRango_Constancia, javax.swing.GroupLayout.Alignment.LEADING))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jpConstancia_RegistroLayout.createSequentialGroup()
                        .addGroup(jpConstancia_RegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(jpConstancia_RegistroLayout.createSequentialGroup()
                                .addGroup(jpConstancia_RegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel60)
                                    .addComponent(jLabel93))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jpConstancia_RegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtTotal_Constancia, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jpConstancia_RegistroLayout.createSequentialGroup()
                                        .addGroup(jpConstancia_RegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtLateral_Constancia, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jpConstancia_RegistroLayout.createSequentialGroup()
                                                .addGap(1, 1, 1)
                                                .addGroup(jpConstancia_RegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtComite_Constancia, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtCliente_Constancia, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtFecha_Constancia, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(jpConstancia_RegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jpConstancia_RegistroLayout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addGroup(jpConstancia_RegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(btn_ModalComite_Constancia, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(btn_ModalCliente_Constancia)))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpConstancia_RegistroLayout.createSequentialGroup()
                                                .addGap(15, 15, 15)
                                                .addComponent(btn_ModalLateral_Constancia, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addComponent(txtMontoComision_Constancia, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jpConstancia_RegistroLayout.createSequentialGroup()
                                        .addComponent(rbAlmacigo_Constancia, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(rbBoleo_Constancia, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtFechaAlmacigo_constancia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                        .addGap(40, 40, 40)
                        .addGroup(jpConstancia_RegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel89)
                            .addGroup(jpConstancia_RegistroLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtHectareas_Constancia, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpConstancia_RegistroLayout.createSequentialGroup()
                                .addComponent(jLabel61)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtMontoJunta_Constancia)))
                        .addContainerGap(40, Short.MAX_VALUE))))
        );
        jpConstancia_RegistroLayout.setVerticalGroup(
            jpConstancia_RegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpConstancia_RegistroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpConstancia_RegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFecha_Constancia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jpConstancia_RegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpConstancia_RegistroLayout.createSequentialGroup()
                        .addGroup(jpConstancia_RegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpConstancia_RegistroLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1))
                            .addGroup(jpConstancia_RegistroLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jpConstancia_RegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtComite_Constancia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn_ModalComite_Constancia, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)
                                    .addComponent(txtPeriodoRango_Constancia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(13, 13, 13)
                        .addGroup(jpConstancia_RegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_ModalCliente_Constancia, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jpConstancia_RegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(txtCliente_Constancia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jpConstancia_RegistroLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpConstancia_RegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(txtCampania_Constancia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jpConstancia_RegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpConstancia_RegistroLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jpConstancia_RegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel89)
                            .addComponent(cboTipoCultivo_Constancia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpConstancia_RegistroLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jpConstancia_RegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel48)
                            .addComponent(rbAlmacigo_Constancia)
                            .addComponent(rbBoleo_Constancia)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpConstancia_RegistroLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFechaAlmacigo_constancia, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(17, 17, 17)
                .addGroup(jpConstancia_RegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpConstancia_RegistroLayout.createSequentialGroup()
                        .addGroup(jpConstancia_RegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtLateral_Constancia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jpConstancia_RegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(btn_ModalLateral_Constancia)
                                .addComponent(jLabel7)))
                        .addGap(18, 18, 18)
                        .addGroup(jpConstancia_RegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel60)
                            .addComponent(txtMontoComision_Constancia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel61)
                            .addComponent(txtMontoJunta_Constancia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(txtHectareas_Constancia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jpConstancia_RegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotal_Constancia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel93))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btn_Cancelar_Constancia.setBackground(new java.awt.Color(255, 102, 0));
        btn_Cancelar_Constancia.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_Cancelar_Constancia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/Cancel-icon.png"))); // NOI18N
        btn_Cancelar_Constancia.setText("CANCELAR");
        btn_Cancelar_Constancia.setIconTextGap(8);
        btn_Cancelar_Constancia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Cancelar_ConstanciaActionPerformed(evt);
            }
        });

        btn_Guardar_Constancia.setBackground(new java.awt.Color(255, 102, 0));
        btn_Guardar_Constancia.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_Guardar_Constancia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/Save-icon.png"))); // NOI18N
        btn_Guardar_Constancia.setText("GUARDAR");
        btn_Guardar_Constancia.setIconTextGap(8);
        btn_Guardar_Constancia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Guardar_ConstanciaActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("AL");

        chkFecha_Constancia.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chkFecha_Constancia.setSelected(true);
        chkFecha_Constancia.setText("Fecha :");
        chkFecha_Constancia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkFecha_ConstanciaActionPerformed(evt);
            }
        });

        chkCampania_Constancia.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chkCampania_Constancia.setText("Periodo :");
        chkCampania_Constancia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkCampania_ConstanciaActionPerformed(evt);
            }
        });

        chkCliente_Constancia.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chkCliente_Constancia.setText("Usuario");
        chkCliente_Constancia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkCliente_ConstanciaActionPerformed(evt);
            }
        });

        cboPeriodoFiltro_Constancia.setEnabled(false);

        btnBuscarFiltro_Constancia.setBackground(new java.awt.Color(153, 255, 153));
        btnBuscarFiltro_Constancia.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnBuscarFiltro_Constancia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/Search-icon.png"))); // NOI18N
        btnBuscarFiltro_Constancia.setText("BUSCAR");
        btnBuscarFiltro_Constancia.setIconTextGap(8);
        btnBuscarFiltro_Constancia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarFiltro_ConstanciaActionPerformed(evt);
            }
        });

        cboAgricultorFiltro_Constancia.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkFecha_Constancia)
                    .addComponent(chkCampania_Constancia))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cboPeriodoFiltro_Constancia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtFiltroInicio_Constancia, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(chkCliente_Constancia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtFiltroFin_Constancia, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(cboAgricultorFiltro_Constancia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(61, 61, 61)
                .addComponent(btnBuscarFiltro_Constancia, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFiltroFin_Constancia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkFecha_Constancia, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtFiltroInicio_Constancia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkCampania_Constancia)
                    .addComponent(cboPeriodoFiltro_Constancia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkCliente_Constancia)
                    .addComponent(cboAgricultorFiltro_Constancia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnBuscarFiltro_Constancia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel81.setBackground(new java.awt.Color(0, 0, 0));
        jLabel81.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel81.setForeground(new java.awt.Color(255, 255, 255));
        jLabel81.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel81.setText("LISTA CONSTANCIAS");
        jLabel81.setOpaque(true);

        jtBusqueda_Constancia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Constancia_id", "# Documento", "Usuario", "Periodo", "# Campaña", "Lateral", "# Hectareas", "Fecha Registro", "Tipo Siembra", "Fecha Siembra"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(jtBusqueda_Constancia);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jpConstancia_Registro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_Guardar_Constancia, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_Cancelar_Constancia))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel81, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jpConstancia_Registro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_Guardar_Constancia, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_Cancelar_Constancia, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtFecha_ConstanciaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtFecha_ConstanciaPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFecha_ConstanciaPropertyChange

    private void txtHectareas_ConstanciaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHectareas_ConstanciaKeyReleased
        try {
            BLCuenta c = new BLCuenta();
            ListaCuentaMonto objcumon = new ListaCuentaMonto();
            objcumon = c.get_cuentamonto_all(Double.parseDouble(txtHectareas_Constancia.getText()));
            txtMontoComision_Constancia.setText(String.valueOf(objcumon.getMontocomision()));
            txtMontoJunta_Constancia.setText(String.valueOf(objcumon.getMontojunta()));
            txtTotal_Constancia.setText(String.valueOf(objcumon.getMontocomision() + objcumon.getMontojunta()));
        } catch (Exception e) {
            System.out.println("Error de Calculo" + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_txtHectareas_ConstanciaKeyReleased

    private void txtHectareas_ConstanciaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHectareas_ConstanciaKeyTyped
        new Funciones().soloDecimales(evt);
        if (txtHectareas_Constancia.getText().length() == 16) {
            evt.consume();
        }
    }//GEN-LAST:event_txtHectareas_ConstanciaKeyTyped

    private void btn_ModalComite_ConstanciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ModalComite_ConstanciaActionPerformed
        try {
            gettabla_comite_byActivos("");
            jdConstanciaComite.pack();
            jdConstanciaComite.setLocationRelativeTo(null);
            jdConstanciaComite.getRootPane().registerKeyboardAction(new CloseDialogEscape(jdConstanciaComite),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
            jdConstanciaComite.setModal(true);
            jdConstanciaComite.setVisible(true);
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
    }//GEN-LAST:event_btn_ModalComite_ConstanciaActionPerformed

    private void btn_ModalCliente_ConstanciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ModalCliente_ConstanciaActionPerformed
        try {
            gettabla_agricultor_constancia_byActivos("");
            jdConstanciaAgricultor.pack();
            jdConstanciaAgricultor.setLocationRelativeTo(null);
            jdConstanciaAgricultor.getRootPane().registerKeyboardAction(new CloseDialogEscape(jdConstanciaAgricultor),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
            jdConstanciaAgricultor.setModal(true);
            jdConstanciaAgricultor.setVisible(true);
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
    }//GEN-LAST:event_btn_ModalCliente_ConstanciaActionPerformed

    private void btn_ModalLateral_ConstanciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ModalLateral_ConstanciaActionPerformed
        try {
            gettabla_lateral_byagricultoractivos("", idCliente_Constancia);
            jdConstanciaLateral.pack();
            jdConstanciaLateral.setLocationRelativeTo(null);
            jdConstanciaLateral.getRootPane().registerKeyboardAction(new CloseDialogEscape(jdConstanciaLateral),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
            jdConstanciaLateral.setModal(true);
            jdConstanciaLateral.setVisible(true);
        } catch (Exception e) {
            System.out.println("Error cargar modal lateral" + e.toString());
        } finally {
        }
    }//GEN-LAST:event_btn_ModalLateral_ConstanciaActionPerformed

    private void rbAlmacigo_ConstanciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbAlmacigo_ConstanciaActionPerformed
        if (rbAlmacigo_Constancia.isSelected()) {
            txtFechaAlmacigo_constancia.setEnabled(true);
        }
    }//GEN-LAST:event_rbAlmacigo_ConstanciaActionPerformed

    private void rbBoleo_ConstanciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbBoleo_ConstanciaActionPerformed
        if (rbBoleo_Constancia.isSelected()) {
            txtFechaAlmacigo_constancia.setEnabled(true);
        }
    }//GEN-LAST:event_rbBoleo_ConstanciaActionPerformed

    private void btn_Cancelar_ConstanciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Cancelar_ConstanciaActionPerformed
        limpiarFomulario_Constancia();
    }//GEN-LAST:event_btn_Cancelar_ConstanciaActionPerformed

    private void btn_Guardar_ConstanciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Guardar_ConstanciaActionPerformed
        try {
            if (txtFecha_Constancia.getDate() != null && txtComite_Constancia.getText().compareTo("") != 0
                    && txtCliente_Constancia.getText().compareTo("") != 0 && txtPeriodoRango_Constancia.getText().compareTo("") != 0
                    && txtLateral_Constancia.getText().compareTo("") != 0 && txtHectareas_Constancia.getText().compareTo("") != 0) {
                modalvalidacion_constancia();
            } else {
                JOptionPane.showMessageDialog(null, "No se admite campos vacios", "ALERTA", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            System.out.println("" + e.toString());
        }
    }//GEN-LAST:event_btn_Guardar_ConstanciaActionPerformed

    private void chkFecha_ConstanciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkFecha_ConstanciaActionPerformed
        if (chkFecha_Constancia.isSelected()) {
            txtFiltroInicio_Constancia.setEnabled(true);
            txtFiltroFin_Constancia.setEnabled(true);
        } else if (!chkFecha_Constancia.isSelected()) {
            txtFiltroInicio_Constancia.setEnabled(false);
            txtFiltroFin_Constancia.setEnabled(false);
        }
    }//GEN-LAST:event_chkFecha_ConstanciaActionPerformed

    private void chkCampania_ConstanciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkCampania_ConstanciaActionPerformed
        if (chkCampania_Constancia.isSelected()) {
            cboPeriodoFiltro_Constancia.setEnabled(true);
        } else if (!chkCampania_Constancia.isSelected()) {
            cboPeriodoFiltro_Constancia.setEnabled(false);
        }
    }//GEN-LAST:event_chkCampania_ConstanciaActionPerformed

    private void chkCliente_ConstanciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkCliente_ConstanciaActionPerformed
        if (chkCliente_Constancia.isSelected()) {
            cboAgricultorFiltro_Constancia.setEnabled(true);
        } else if (!chkCliente_Constancia.isSelected()) {
            cboAgricultorFiltro_Constancia.setEnabled(false);
        }
    }//GEN-LAST:event_chkCliente_ConstanciaActionPerformed

    private void btnBuscarFiltro_ConstanciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarFiltro_ConstanciaActionPerformed
        buscar_constancia_byfiltro();
    }//GEN-LAST:event_btnBuscarFiltro_ConstanciaActionPerformed

    private void txtModalComite_ConstanciaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtModalComite_ConstanciaKeyReleased
        gettabla_comite_byActivos(txtModalComite_Constancia.getText());
    }//GEN-LAST:event_txtModalComite_ConstanciaKeyReleased

    private void txtModalComite_ConstanciaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtModalComite_ConstanciaKeyTyped
        Character c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            evt.setKeyChar(Character.toUpperCase(c));
        }
    }//GEN-LAST:event_txtModalComite_ConstanciaKeyTyped

    private void txtModalCliente_ConstanciaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtModalCliente_ConstanciaKeyReleased
        gettabla_agricultor_constancia_byActivos(txtModalCliente_Constancia.getText());
    }//GEN-LAST:event_txtModalCliente_ConstanciaKeyReleased

    private void txtModalCliente_ConstanciaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtModalCliente_ConstanciaKeyTyped
        Character c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            evt.setKeyChar(Character.toUpperCase(c));
        }
    }//GEN-LAST:event_txtModalCliente_ConstanciaKeyTyped

    private void jtModalAgricultor_ConstanciaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtModalAgricultor_ConstanciaMouseClicked
        try {
            idCliente_Constancia = Integer.parseInt(String.valueOf(jtModalAgricultor_Constancia.getModel().getValueAt(jtModalAgricultor_Constancia.getSelectedRow(), 0)));
            txtCliente_Constancia.setText(String.valueOf(jtModalAgricultor_Constancia.getModel().getValueAt(jtModalAgricultor_Constancia.getSelectedRow(), 1)));
            BLPeriodo p = new BLPeriodo();
            PeriodoCampania pc = p.get_peridocampania_byagricultor(idCliente_Constancia, new java.sql.Date(txtFecha_Constancia.getDate().getTime()));
            idPeriodo_Constancia = pc.getPeriodo_id();
            txtPeriodoRango_Constancia.setText(pc.getVar_periodo() + " : " + pc.getNom_mesInicio() + " - " + pc.getNom_mesFin());
            txtCampania_Constancia.setText(String.valueOf(pc.getInt_campania()));
            btn_ModalLateral_Constancia.setEnabled(true);
            rbAlmacigo_Constancia.setEnabled(true);
            rbBoleo_Constancia.setEnabled(true);
            cboTipoCultivo_Constancia.setEnabled(true);
            txtHectareas_Constancia.setEnabled(true);
            txtFechaAlmacigo_constancia.setEnabled(true);
        } catch (Exception e) {
            System.out.println("" + e.toString());
        } finally {
            jdConstanciaAgricultor.dispose();
        }
    }//GEN-LAST:event_jtModalAgricultor_ConstanciaMouseClicked

    private void jtModalAgricultor_ConstanciaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtModalAgricultor_ConstanciaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                idCliente_Constancia = Integer.parseInt(String.valueOf(jtModalAgricultor_Constancia.getModel().getValueAt(jtModalAgricultor_Constancia.getSelectedRow(), 0)));
                txtCliente_Constancia.setText(String.valueOf(jtModalAgricultor_Constancia.getModel().getValueAt(jtModalAgricultor_Constancia.getSelectedRow(), 1)));
                BLPeriodo p = new BLPeriodo();
                PeriodoCampania pc = p.get_peridocampania_byagricultor(idCliente_Constancia, new java.sql.Date(txtFecha_Constancia.getDate().getTime()));
                idPeriodo_Constancia = pc.getPeriodo_id();
                idPeriodo_Constancia = pc.getInt_campania();
                txtPeriodoRango_Constancia.setText(pc.getVar_periodo() + " : " + pc.getNom_mesInicio() + " - " + pc.getNom_mesFin());
                txtCampania_Constancia.setText(String.valueOf(pc.getInt_campania()));
                btn_ModalLateral_Constancia.setEnabled(true);
            } catch (Exception e) {
                System.out.println("" + e.toString());
            } finally {
                jdConstanciaAgricultor.dispose();
            }
        }
    }//GEN-LAST:event_jtModalAgricultor_ConstanciaKeyPressed

    private void txtModalLateral_ConstanciaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtModalLateral_ConstanciaKeyReleased
        gettabla_lateral_byagricultoractivos(txtModalLateral_Constancia.getText(), idCliente_Constancia);
    }//GEN-LAST:event_txtModalLateral_ConstanciaKeyReleased

    private void txtModalLateral_ConstanciaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtModalLateral_ConstanciaKeyTyped
        Character c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            evt.setKeyChar(Character.toUpperCase(c));
        }
    }//GEN-LAST:event_txtModalLateral_ConstanciaKeyTyped

    private void jtModalLateral_ConstanciaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtModalLateral_ConstanciaMouseClicked
        try {
            idLateral_Constancia = Integer.parseInt(String.valueOf(jtModalLateral_Constancia.getModel().getValueAt(jtModalLateral_Constancia.getSelectedRow(), 0)));
            String lateral = String.valueOf(jtModalLateral_Constancia.getModel().getValueAt(jtModalLateral_Constancia.getSelectedRow(), 1));
            String sublateral = String.valueOf(jtModalLateral_Constancia.getModel().getValueAt(jtModalLateral_Constancia.getSelectedRow(), 2));
            String concat = lateral + " - " + sublateral;
            String hec = String.valueOf(jtModalLateral_Constancia.getModel().getValueAt(jtModalLateral_Constancia.getSelectedRow(), 5));
            txtLateral_Constancia.setText(concat);
            txtHectareas_Constancia.setText(hec);

            ListaCuentaMonto c = new ListaCuentaMonto();
            c = new BLCuenta().get_cuentamonto_all(Double.parseDouble(hec));
            txtMontoComision_Constancia.setText(String.valueOf(c.getMontocomision()));
            txtMontoJunta_Constancia.setText(String.valueOf(c.getMontojunta()));
            txtTotal_Constancia.setText(String.valueOf(c.getMontocomision() + c.getMontojunta()));
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        } finally {
            jdConstanciaLateral.dispose();
        }
    }//GEN-LAST:event_jtModalLateral_ConstanciaMouseClicked

    private void jdConstanciaLateralKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jdConstanciaLateralKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jdConstanciaLateralKeyTyped

    private void btn_validar_constanciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_validar_constanciaActionPerformed
        try {
            Usuario u = new Usuario();
            BLUsuario us = new BLUsuario();
            char passArray[] = txtValidacionPass_Constancia.getPassword();
            String pass = new String(passArray);
            u = us.get_usuario_bypassword(pass);
            if (u.getVar_user() != null) {
                jdValidacion_Constancia.dispose();
                RegistrarConstancia();
                txtValidacionPass_Constancia.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "[Clave Incorrecta]", "Alerta", JOptionPane.ERROR_MESSAGE);
                txtValidacionPass_Constancia.requestFocus();
                txtValidacionPass_Constancia.setText("");
            }

        } catch (Exception e) {
            System.out.println("Error de Validacion ");
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_validar_constanciaActionPerformed

    private void jtModalComite_ConstanciaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtModalComite_ConstanciaMouseClicked
        try {
            idComite_Constancia = Integer.parseInt(String.valueOf(jtModalComite_Constancia.getModel().getValueAt(jtModalComite_Constancia.getSelectedRow(), 0)));
            txtComite_Constancia.setText(String.valueOf(jtModalComite_Constancia.getModel().getValueAt(jtModalComite_Constancia.getSelectedRow(), 1)));
            jdConstanciaComite.dispose();
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }
    }//GEN-LAST:event_jtModalComite_ConstanciaMouseClicked

    public void modalvalidacion_constancia() {
        jdValidacion_Constancia.pack();
        jdValidacion_Constancia.setLocationRelativeTo(null);
        jdValidacion_Constancia.getRootPane().registerKeyboardAction(new CloseDialogEscape(jdValidacion_Constancia),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
        jdValidacion_Constancia.setModal(true);
        jdValidacion_Constancia.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarFiltro_Constancia;
    private javax.swing.ButtonGroup btnTipodeSembrio;
    private javax.swing.JButton btn_Cancelar_Constancia;
    private javax.swing.JButton btn_Guardar_Constancia;
    private javax.swing.JButton btn_ModalCliente_Constancia;
    private javax.swing.JButton btn_ModalComite_Constancia;
    private javax.swing.JButton btn_ModalLateral_Constancia;
    private javax.swing.JButton btn_validar_constancia;
    private org.jdesktop.swingx.JXComboBox cboAgricultorFiltro_Constancia;
    private org.jdesktop.swingx.JXComboBox cboPeriodoFiltro_Constancia;
    private org.jdesktop.swingx.JXComboBox cboTipoCultivo_Constancia;
    private javax.swing.JCheckBox chkCampania_Constancia;
    private javax.swing.JCheckBox chkCliente_Constancia;
    private javax.swing.JCheckBox chkFecha_Constancia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JDialog jdConstanciaAgricultor;
    private javax.swing.JDialog jdConstanciaComite;
    private javax.swing.JDialog jdConstanciaLateral;
    private javax.swing.JDialog jdValidacion_Constancia;
    private javax.swing.JPanel jpBuscarComite;
    private javax.swing.JPanel jpConstanciaLateral;
    private javax.swing.JPanel jpConstancia_Registro;
    private javax.swing.JTable jtBusqueda_Constancia;
    private javax.swing.JTable jtModalAgricultor_Constancia;
    private javax.swing.JTable jtModalComite_Constancia;
    private javax.swing.JTable jtModalLateral_Constancia;
    private javax.swing.JRadioButton rbAlmacigo_Constancia;
    private javax.swing.JRadioButton rbBoleo_Constancia;
    private javax.swing.JTextField txtCampania_Constancia;
    private javax.swing.JTextField txtCliente_Constancia;
    private javax.swing.JTextField txtComite_Constancia;
    private com.toedter.calendar.JDateChooser txtFechaAlmacigo_constancia;
    private com.toedter.calendar.JDateChooser txtFecha_Constancia;
    private com.toedter.calendar.JDateChooser txtFiltroFin_Constancia;
    private com.toedter.calendar.JDateChooser txtFiltroInicio_Constancia;
    private javax.swing.JTextField txtHectareas_Constancia;
    private javax.swing.JTextField txtLateral_Constancia;
    private javax.swing.JTextField txtModalCliente_Constancia;
    private javax.swing.JTextField txtModalComite_Constancia;
    private javax.swing.JTextField txtModalLateral_Constancia;
    private javax.swing.JTextField txtMontoComision_Constancia;
    private javax.swing.JTextField txtMontoJunta_Constancia;
    private javax.swing.JTextField txtPeriodoRango_Constancia;
    private javax.swing.JTextField txtTotal_Constancia;
    private javax.swing.JPasswordField txtValidacionPass_Constancia;
    // End of variables declaration//GEN-END:variables
}
