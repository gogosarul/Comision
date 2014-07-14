package vista;

import componente.jifConstancia_C;
import componente.jifTraspaso_C;
import componente.jifVerPagos_C;
import componente.jifMovimientos_C;
import componente.jifInicioCierreCaja_C;
import componente.jifMultaAsamblea_C;
import componente.jifMultaSufragio_C;
import componente.jifComites_C;
import componente.jifCargos_C;
import componente.jifMateriales_C;
import componente.jifReporte_Movimiento_C;
import controlador.BLAgricultor;
import controlador.BLAlquiler;
import controlador.BLCargo;
import controlador.BLComite;
import controlador.BLConstante;
import controlador.BLCuenta;
import controlador.BLLateral;
import controlador.BLMaterial;
import controlador.BLNoCliente;
import controlador.BLPeriodo;
import entidad.Agricultor;
import entidad.Asignar_Costo;
import entidad.Cargo;
import entidad.Comite;
import entidad.Constante;
import entidad.Cuenta;
import entidad.Detalle_Alquiler;
import entidad.Lateral;
import entidad.ListaAgricultorLateral;
import entidad.ListaAlquiler;
import entidad.ListaLateral;
import entidad.ListaOpciones;
import entidad.ListaUsuario;
import entidad.Material;
import entidad.NoCliente;
import entidad.PeriodoCampania;
import entidad.SubLateral;
import entidad.Usuario;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import modelo.BDAgricultor;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import utilitario.CloseDialogEscape;
import utilitario.Funciones;

/**
 *
 * @author joseph
 */
public class Inicio extends javax.swing.JFrame implements Runnable {

    //OBTENER LA DIMENSION DE TU PANTALLA
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension df = this.getSize();
    //VARIABLES GLOBALES
    int idEmpleado_Alquiler = 0, idAgricultor_Alquiler = 0, idPago = 0, idAgricultor_Edit = 0;
    double vcuota = 0;
    double vintangible = 0;
    double vseguro = 0;
    double vtarifa = 0;
    int identificador = 0;

    String fechaActual, hora, minutos, segundos, ampm, nroDocumentoBF, motivoCortesia = "", motivoDescuento = "";
    Calendar calendario;
    Thread h1;

    public Inicio() {

        initComponents();

        h1 = new Thread(this);
        h1.start();
        this.setDefaultCloseOperation(0);
        this.setResizable(false);
        this.setExtendedState(MAXIMIZED_BOTH);
        //this.setSize(d.width - 100, d.height - 50);
        this.setSize(1280, 720);
        this.setLocationRelativeTo(null);
        txtAnio_Principal.setText(new SimpleDateFormat("yyyy").format(new Date()));
        formatear_estructura_todas_tablas();
    }
    /*METOO LIMPIAR TABLA*/

    private void limpiarTabla(JTable tabla) {
        try {
            DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
            int filas = tabla.getRowCount();
            for (int i = 0; filas > i; i++) {
                modelo.removeRow(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
        }
    }
    /*FIN LIMPIAR TABLAS*/

    /*FORMATEO DE STRUCTURA DE TABLAS DEL SISTEMA*/
    private void formatear_estructura_todas_tablas() {
        ((DefaultTableCellRenderer) jtLista_Alquileres.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        jtLista_Alquileres.setRowHeight(22);
        jtLista_Alquileres.getColumnModel().getColumn(0).setMaxWidth(0);
        jtLista_Alquileres.getColumnModel().getColumn(0).setMinWidth(0);
        jtLista_Alquileres.getColumnModel().getColumn(0).setPreferredWidth(0);
        jtLista_Alquileres.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        ((DefaultTableCellRenderer) jtAgricultor.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        jtAgricultor.setRowHeight(22);
        jtAgricultor.getColumnModel().getColumn(0).setMaxWidth(0);
        jtAgricultor.getColumnModel().getColumn(0).setMinWidth(0);
        jtAgricultor.getColumnModel().getColumn(0).setPreferredWidth(0);
        jtAgricultor.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        ((DefaultTableCellRenderer) jtbDetalle_Alquiler.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        jtbDetalle_Alquiler.setRowHeight(22);
        jtbDetalle_Alquiler.getColumnModel().getColumn(0).setMaxWidth(0);
        jtbDetalle_Alquiler.getColumnModel().getColumn(0).setMinWidth(0);
        jtbDetalle_Alquiler.getColumnModel().getColumn(0).setPreferredWidth(0);

        ((DefaultTableCellRenderer) jtLista_Usuario.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        jtLista_Usuario.setRowHeight(22);
        jtLista_Usuario.getColumnModel().getColumn(0).setMaxWidth(0);
        jtLista_Usuario.getColumnModel().getColumn(0).setMinWidth(0);
        jtLista_Usuario.getColumnModel().getColumn(0).setPreferredWidth(0);

        ((DefaultTableCellRenderer) jtb_lista_permisos.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        jtb_lista_permisos.setRowHeight(22);
        jtb_lista_permisos.getColumnModel().getColumn(0).setMaxWidth(0);
        jtb_lista_permisos.getColumnModel().getColumn(0).setMinWidth(0);
        jtb_lista_permisos.getColumnModel().getColumn(0).setPreferredWidth(0);
    }
    /*FIN FORMATEO DE STRUCTURA DE TABLAS DEL SISTEMA*/

    /*LATERALES*/
    /*private void get_latreles_all() {
     cboLateral_Agricultor.removeAllItems();
     ArrayList<String> lista_lat = new BLAgricultor().get_latreles_all();
     cboLateral_Agricultor.addItem("");
     for (int i = 0; i < lista_lat.size(); i++) {
     cboLateral_Agricultor.addItem(lista_lat.get(i));
     }
     cboLateral_Agricultor.setSelectedIndex(1);
     //AutoCompleteDecorator.decorate(cboLateral_Agricultor);
     }*/
    private void get_sublatreles_all(String condicion) {
        cboSubLateral_Agricultor.removeAllItems();
        ArrayList<SubLateral> lista_lat = new BLLateral().get_sublateral_all("");
        for (int i = 0; i < lista_lat.size(); i++) {
            cboSubLateral_Agricultor.addItem(lista_lat.get(i));
        }

        DefaultTableModel temp = (DefaultTableModel) jtSubLateral_Adm.getModel();
        temp.setRowCount(0);
        for (SubLateral l : new BLLateral().get_sublateral_all(condicion)) {
            Object[] datos = {l.getInt_id(), l.getVar_descripcion(), l.getVar_estado()};
            temp.addRow(datos);
        }
        //AutoCompleteDecorator.decorate(cboSubLateral_Agricultor);
    }

    private void get_lateral_all() {
        cboLateral_Agricultor.removeAllItems();
        ArrayList<Lateral> lista_lat = new BLLateral().get_lateral_all();
        for (int i = 0; i < lista_lat.size(); i++) {
            cboLateral_Agricultor.addItem(lista_lat.get(i));
        }
        //AutoCompleteDecorator.decorate(cboSubLateral_Agricultor);
    }

    private void gettable_getlateral_all(String palabra) {
        DefaultTableModel temp = (DefaultTableModel) jtLateral_Adm.getModel();
        temp.setRowCount(0);
        for (Lateral l : new BLLateral().gettabla_lateral_all(palabra)) {
            Object[] datos = {l.getInt_id(), l.getVar_descripcion(), l.getVar_estado()};
            temp.addRow(datos);
        }
    }
    /*LATERALES*/

    //*ALQUILER*/
    private void limpiarFomulario_Alquiler() {
        idEmpleado_Alquiler = 0;
        idAgricultor_Alquiler = 0;
        txtAgricultor_Alquiler.setText("");
        txtFechaDesde_Alquiler.setDate(new Date());
        txtFechaHasta_Alquiler.setDate(new Date());
        txtCantidad_Alquiler.setValue(1);
    }

    private void RegistrarAlquiler() {
        boolean resultado = false;
        BLAlquiler a = new BLAlquiler();
        ArrayList<Detalle_Alquiler> lista_detalle = new ArrayList<Detalle_Alquiler>();
        int nroFilas = ((DefaultTableModel) jtbDetalle_Alquiler.getModel()).getRowCount();
        for (int f = 0; f < nroFilas; f++) {
            Detalle_Alquiler l = new Detalle_Alquiler();
            l.setMaterial_id(Integer.parseInt(jtbDetalle_Alquiler.getModel().getValueAt(f, 0).toString()));
            l.setInt_cantidad(Integer.parseInt(jtbDetalle_Alquiler.getModel().getValueAt(f, 2).toString()));
            l.setDec_monto(Double.parseDouble(jtbDetalle_Alquiler.getModel().getValueAt(f, 6).toString()));
            l.setDat_fechinicio(Timestamp.valueOf(jtbDetalle_Alquiler.getModel().getValueAt(f, 3).toString()));
            l.setDat_fechfin(Timestamp.valueOf(jtbDetalle_Alquiler.getModel().getValueAt(f, 4).toString()));
            l.setInt_horas(Integer.parseInt(jtbDetalle_Alquiler.getModel().getValueAt(f, 5).toString()));
            lista_detalle.add(l);
        }

        resultado = a.insertarAlquiler(((ListaUsuario) cboTrabajador_Alquiler.getSelectedItem()).getInt_id(), idAgricultor_Alquiler, lista_detalle, identificador);
        if (resultado == true) {
            JOptionPane.showMessageDialog(null, "Se registro Correctamente");
            limpiarFomulario_Alquiler();
            limpiarTabla(jtbDetalle_Alquiler);
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo Registrar");
        }
    }

    private void gettabla_agricultor_alquiler_byActivos(String palabra) {
        DefaultTableModel temp = (DefaultTableModel) jtModalAgricultor_Alquiler.getModel();
        temp.setRowCount(0);
        for (Agricultor c : new BLAgricultor().get_agricultores_byActivos(palabra)) {
            Object[] datos = {c.getInt_id(), c.getVar_nombre() + ' ' + c.getVar_apepaterno() + ' ' + c.getVar_apematerno()};
            temp.addRow(datos);
        }
    }
    /*FIN ALQUILER*/

    /*COMITE*/
    private void gettabla_comite_byActivos(String palabra) {
        DefaultTableModel temp1 = (DefaultTableModel) jtComite_Administracion.getModel();
        temp1.setRowCount(0);
        for (Comite c : new BLComite().get_comite_byActivos(palabra)) {
            Object[] datos1 = {c.getVar_nombre(), c.getVar_estado()};
            temp1.addRow(datos1);
        }
    }
    /*FIN COMITE*/
    /*CUENTA*/

    private void gettabla_asignacioncosto_cuenta_all() {
        DefaultTableModel temp = (DefaultTableModel) jtAsignarCosto_Cuentas.getModel();
        temp.setRowCount(0);
        for (Asignar_Costo c : new BLCuenta().get_asignarcosto_cuenta_all()) {
            Object[] datos = {c.getCuenta_id(), c.getVar_nombre(), c.getDec_monto(), c.getVar_concepto()};
            temp.addRow(datos);
        }
    }

    private void limpiarFomulario_Cuenta() {
        txtCodigo_Cuenta.setText("");
        txtNumCuenta_Registrar.setText("");
        txtNombre_Cuentas.setText("");
    }

    private void limpiarFomulario_AsignacionCosto_Cuenta() {
        txtMonto_AsignarCuenta.setText("");
        txtConcepto_AsignarCosto.setText("");
        cboCuentas_AsignarCostos.setSelectedIndex(0);
    }

    private void gettabla_cuenta_all(String palabra, int indice) {
        cboCuentas_AsignarCostos.removeAllItems();
        DefaultTableModel temp = (DefaultTableModel) jtCuentas.getModel();
        temp.setRowCount(0);
        for (Cuenta c : new BLCuenta().get_cuenta_all(palabra, indice)) {
            Object[] datos = {c.getVar_codigo(), c.getVar_nombre(), c.getVar_numcuenta()};
            temp.addRow(datos);
            cboCuentas_AsignarCostos.addItem(c);
        }
        AutoCompleteDecorator.decorate(cboCuentas_AsignarCostos);
    }
    /*FIN CUENTA*/


    /*AGRICULTOR*/
    private void limpiarformulario_agricultor() {
        txtDNI_Agricultor.setText("");
        txtNombres_Agricultor.setText("");
        txtApePaterno_Agricultor.setText("");
        txtApeMaterno_Agricultor.setText("");
        txtTelefono_Agricultor.setText("");
        txtCelular_Agricultor.setText("");
        txtDireccion_Agricultor.setText("");
        txtEmail_Agricultor.setText("");
        txtSinMedida_Agricultor.setText("");
        txtConMedida_Agricultor.setText("");
        txtNumHectareas_Agricultor.setText("");
        limpiarTabla(jtDetalleLaterales_Agricultor);
    }

    private void getcombo_cliente_all() {
        cboAgricultor_Alquiler.removeAllItems();
        for (Agricultor c : new BLAgricultor().get_agricultores_byActivos("")) {
            cboAgricultor_Alquiler.addItem(c);
        }
        AutoCompleteDecorator.decorate(cboAgricultor_Alquiler);
    }

    private void gettabla_agricultor_all(String condicion, int indicecombo) {
        DefaultTableModel temp = (DefaultTableModel) jtAgricultor.getModel();
        temp.setRowCount(0);
        for (Agricultor a : new BLAgricultor().get_agricultor_all(condicion, indicecombo)) {
            Object[] datos = {a.getInt_id(), a.getVar_dni(), a.getVar_nombre() + ' ' + a.getVar_apepaterno(),
                a.getVar_telefono() + '/' + a.getVar_celular(), a.getVar_direccion(), a.getNumLaterales(),
                a.getInt_numhectareas()};
            temp.addRow(datos);
        }
    }

    /*FIN AGRICULTOR*/

    /*PERIODO*/
    private void limpiarformulario_periodo() {
        txtNombre_Periodo.setText("");
        txtFiltro_Periodo.setText("");
    }

    private void getcombo_periodo_mesiniciofin() {
        cboPeriodo_MesInicio.removeAllItems();
        cboPeriodo_MesFin.removeAllItems();
        for (Constante c : new BLConstante().get_constante_all_byClase(1)) {
            cboPeriodo_MesInicio.addItem(c);
            cboPeriodo_MesFin.addItem(c);
        }
        //AutoCompleteDecorator.decorate(cboPeriodoFiltro_Constancia);
    }

    private void gettabla_periodo_all(String palabra, int indice) {
        DefaultTableModel temp = (DefaultTableModel) jtPeriodo_All.getModel();
        temp.setRowCount(0);
        for (PeriodoCampania p : new BLPeriodo().get_periodo_all(palabra, indice)) {
            Object[] datos = {p.getVar_periodo(), p.getNom_mesInicio(), p.getNom_mesFin()};
            temp.addRow(datos);

        }
    }
    /*FIN PERIODO*/

    /*MATERIAL*/
    private void getcombo_material_all(String condicion) {
        cboTipoMaterial_Alquiler.removeAllItems();
        for (Material m : new BLMaterial().get_material_all(condicion)) {
            cboTipoMaterial_Alquiler.addItem(m);
        }
        AutoCompleteDecorator.decorate(cboTipoMaterial_Alquiler);
    }
    /*FIN MATERIAL*/

    /*USUARIO*/
    private void limpiarFomulario_Usuario() {
        txtID_Usuario.setText("");
        txtpass_usuario.setText("");
        txtdni_usuario.setText("");
        txtnombres_usuario.setText("");
        txtapellidos_usuario.setText("");
        txtTeleCelular_Usuario.setText("");
        txtDireccion_Usuario.setText("");
        txtEmail_Usuario.setText("");
        txtFechaNacimiento_Usuario.setDate(new Date());
    }
    /*FIN USUARIO*/

    /*CARGO*/
    private void getcombo_cargo_all(String condicion) {
        cboCargo_Usuario.removeAllItems();
        for (Cargo c : new BLCargo().get_cargo_all(condicion)) {
            cboCargo_Usuario.addItem(c);
        }
        //AutoCompleteDecorator.decorate(cboTipoOperacion_Movimiento);
    }
    /*FIN CARGO*/

    /*Usuario*/
    private void gettabla_usuario_byfiltro(String filtro, int indice) {
        DefaultTableModel temp = (DefaultTableModel) jtLista_Usuario.getModel();
        temp.setRowCount(0);
        for (ListaUsuario u : new BLUsuario().get_usuario_all(filtro, indice)) {
            Object[] datos = {u.getInt_id(), u.getVar_dni(), u.getVar_user(), u.getVar_nombres() + ' ' + u.getVar_apellidos(),
                u.getVar_telefono(), u.getVar_descripcion()};
            temp.addRow(datos);
        }
    }

    private void getcombo_usuario() {
        cboTrabajador_Alquiler.removeAllItems();
        for (ListaUsuario u : new BLUsuario().get_usuario_all("", 0)) {
            cboTrabajador_Alquiler.addItem(u);
        }
        AutoCompleteDecorator.decorate(cboTrabajador_Alquiler);
    }
    /*NO CLIENTE*/

    private void gettable_nocliente_alquiler_all(String palabra) {
        DefaultTableModel temp1 = (DefaultTableModel) jtModalAgricultor_Alquiler.getModel();
        temp1.setRowCount(0);
        for (NoCliente c : new BLNoCliente().get_nocliente_all(palabra)) {
            Object[] datos = {c.getInt_id(), c.getVar_nombre() + ' ' + c.getVar_apepaterno() + ' ' + c.getVar_apematerno()};
            temp1.addRow(datos);
        }
    }

    private void gettable_getnocliente_all(String palabra) {
        DefaultTableModel temp = (DefaultTableModel) jtNoCliente_Adm.getModel();
        temp.setRowCount(0);
        for (NoCliente c : new BLNoCliente().get_nocliente_all(palabra)) {
            Object[] datos = {c.getInt_id(), c.getVar_nombre() + ' ' + c.getVar_apepaterno() + ' ' + c.getVar_apematerno(), c.getVar_direccion(),
                c.getVar_dni(), c.getVar_ruc(), c.getVar_telefono()};
            temp.addRow(datos);
        }
    }

    private void limpiarformulario_nocliente() {
        txtDNI_NoUsuario.setText("");
        txtNombres_NoUsuario.setText("");
        txtApePaterno_NoUsuario.setText("");
        txtApeMaterno_NoUsuario.setText("");
        txtTelefono_NoUsuario.setText("");
        txtCelular_NoUsuario.setText("");
        txtDireccion_NoUsuario.setText("");
        txtRuc_NoUsuario.setText("");
        txtRazonSocial_NoUsuario.setText("");
    }

    private void habilitarOpcioneMenu(int usuario_id) {
        DefaultTableModel temp = (DefaultTableModel) jtb_lista_permisos.getModel();
        temp.setRowCount(0);
        for (ListaOpciones mop : new BLUsuario().selectOpciones_ByUsuario(usuario_id)) {
            Object nuevo[] = {mop.getGrupo(), mop.getMenu(), mop.getSubmenu(), mop.isEstado()};
            temp.addRow(nuevo);
        }
    }

    private int registrarPermisos(int usuario_id) {
        int resultado = 0;
        try {
            int fila = jtb_lista_permisos.getRowCount();
            for (int f = 0; f < fila; f++) {
                int menu = Integer.parseInt(String.valueOf(jtb_lista_permisos.getModel().getValueAt(f, 0)));
                int estado = Boolean.parseBoolean(String.valueOf(jtb_lista_permisos.getModel().getValueAt(f, 3))) == true ? 1 : 0;
                new BLUsuario().registrarPermisosUsuario(usuario_id, menu, estado);
            }
            resultado = 1;
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }
        return resultado;
    }

    /*FIN USUARIO*/
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jifIngresarAlquiler = new javax.swing.JInternalFrame();
        jifRegistrarAlquiler = new javax.swing.JPanel();
        jtbAlquiler = new javax.swing.JTabbedPane();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtLista_Alquileres = new javax.swing.JTable();
        jLabel82 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        chkFiltroFecha_Alquiler = new javax.swing.JCheckBox();
        txtFechaInicio_Alquiler = new com.toedter.calendar.JDateChooser();
        jLabel29 = new javax.swing.JLabel();
        txtFechaFin_Alquiler = new com.toedter.calendar.JDateChooser();
        chkFiltroAgricultor_Alquiler = new javax.swing.JCheckBox();
        cboAgricultor_Alquiler = new org.jdesktop.swingx.JXComboBox();
        btn_buscar_alquileres = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        cboTipoMaterial_Alquiler = new org.jdesktop.swingx.JXComboBox();
        jLabel28 = new javax.swing.JLabel();
        txtMonto_Alquiler = new org.jdesktop.swingx.JXTextField();
        jLabel25 = new javax.swing.JLabel();
        txtCantidad_Alquiler = new com.toedter.components.JSpinField();
        jLabel23 = new javax.swing.JLabel();
        txtFechaDesde_Alquiler = new com.toedter.calendar.JDateChooser();
        jLabel26 = new javax.swing.JLabel();
        txtFechaHasta_Alquiler = new com.toedter.calendar.JDateChooser();
        btnAgregarDet_Alquiler = new javax.swing.JButton();
        btnEliminarDet_Alquiler = new javax.swing.JButton();
        txtHoras_Alquiler = new com.toedter.components.JSpinField();
        jLabel42 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtAgricultor_Alquiler = new javax.swing.JTextField();
        jScrollPane12 = new javax.swing.JScrollPane();
        jtbDetalle_Alquiler = new javax.swing.JTable();
        btn_Cancelar1 = new javax.swing.JButton();
        btn_registrar_alquiler = new javax.swing.JButton();
        btnBuscarAgricultor_Alquiler = new javax.swing.JButton();
        cboTrabajador_Alquiler = new org.jdesktop.swingx.JXComboBox();
        jifAgricultores = new javax.swing.JInternalFrame();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel17 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jtAgricultor = new javax.swing.JTable();
        jLabel70 = new javax.swing.JLabel();
        txtFiltroAgricultor = new org.jdesktop.swingx.JXSearchField();
        cboFiltroAgricultor = new javax.swing.JComboBox();
        jPanel18 = new javax.swing.JPanel();
        jTabbedPane7 = new javax.swing.JTabbedPane();
        jPanel31 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        txtNombres_Agricultor = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        txtApePaterno_Agricultor = new javax.swing.JTextField();
        txtApeMaterno_Agricultor = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        txtEmail_Agricultor = new javax.swing.JTextField();
        txtDireccion_Agricultor = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        txtDNI_Agricultor = new org.jdesktop.swingx.JXSearchField();
        txtTelefono_Agricultor = new javax.swing.JTextField();
        txtCelular_Agricultor = new javax.swing.JTextField();
        cboSexo_Agricultor = new org.jdesktop.swingx.JXComboBox();
        jpLaterales = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        btnEliminar_DetLateales = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jtDetalleLaterales_Agricultor = new javax.swing.JTable();
        jLabel37 = new javax.swing.JLabel();
        txtSinMedida_Agricultor = new javax.swing.JTextField();
        txtConMedida_Agricultor = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        txtNumHectareas_Agricultor = new javax.swing.JTextField();
        cboSubLateral_Agricultor = new org.jdesktop.swingx.JXComboBox();
        cboLateral_Agricultor = new org.jdesktop.swingx.JXComboBox();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jifPeriodos = new javax.swing.JInternalFrame();
        jPanel26 = new javax.swing.JPanel();
        btn_guardar_periodo = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        jtPeriodo_All = new javax.swing.JTable();
        cboFiltro_Periodo = new javax.swing.JComboBox();
        txtFiltro_Periodo = new org.jdesktop.swingx.JXSearchField();
        jLabel78 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        txtNombre_Periodo = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        cboPeriodo_MesInicio = new org.jdesktop.swingx.JXComboBox();
        cboPeriodo_MesFin = new org.jdesktop.swingx.JXComboBox();
        jLabel79 = new javax.swing.JLabel();
        jifDocumento = new javax.swing.JInternalFrame();
        btnTipodeSembrio = new javax.swing.ButtonGroup();
        jdAlquilerAgricultor = new javax.swing.JDialog();
        jpBuscarAgricultor_Traspaso1 = new javax.swing.JPanel();
        jLabel59 = new javax.swing.JLabel();
        txtModalAgricultor_Alquiler = new javax.swing.JTextField();
        jScrollPane18 = new javax.swing.JScrollPane();
        jtModalAgricultor_Alquiler = new javax.swing.JTable();
        btn_buscar_usuario_alquiler = new javax.swing.JButton();
        jLabel130 = new javax.swing.JLabel();
        rbtnUsuario_Alquiler = new javax.swing.JRadioButton();
        rbtnNoUsuario_Alquiler = new javax.swing.JRadioButton();
        rb_group = new javax.swing.ButtonGroup();
        jpmAgricultor = new javax.swing.JPopupMenu();
        jmiEditar = new javax.swing.JMenuItem();
        jpmVerPagos = new javax.swing.JPopupMenu();
        jmip_Pagar = new javax.swing.JMenuItem();
        jmip_GenerarDocumento = new javax.swing.JMenuItem();
        jmip_Anular = new javax.swing.JMenuItem();
        jifCuentas = new javax.swing.JInternalFrame();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel39 = new javax.swing.JPanel();
        jPanel41 = new javax.swing.JPanel();
        btnGuardar5 = new javax.swing.JButton();
        btnCancelar5 = new javax.swing.JButton();
        jLabel98 = new javax.swing.JLabel();
        txtFiltroNombre_Cuenta2 = new org.jdesktop.swingx.JXSearchField();
        cboFiltro_Cuenta = new javax.swing.JComboBox();
        jScrollPane23 = new javax.swing.JScrollPane();
        jtCuentas = new javax.swing.JTable();
        jLabel99 = new javax.swing.JLabel();
        jPanel42 = new javax.swing.JPanel();
        Codigo2 = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        txtNombre_Cuentas = new javax.swing.JTextField();
        txtCodigo_Cuenta = new org.jdesktop.swingx.JXTextField();
        txtNumCuenta_Registrar = new org.jdesktop.swingx.JXTextField();
        jPanel43 = new javax.swing.JPanel();
        jPanel44 = new javax.swing.JPanel();
        jLabel102 = new javax.swing.JLabel();
        Codigo3 = new javax.swing.JLabel();
        jLabel103 = new javax.swing.JLabel();
        txtMonto_AsignarCuenta = new org.jdesktop.swingx.JXTextField();
        jLabel104 = new javax.swing.JLabel();
        cboCuentas_AsignarCostos = new org.jdesktop.swingx.JXComboBox();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtConcepto_AsignarCosto = new javax.swing.JTextArea();
        btnGuardar6 = new javax.swing.JButton();
        btnCancelar6 = new javax.swing.JButton();
        jLabel105 = new javax.swing.JLabel();
        jScrollPane24 = new javax.swing.JScrollPane();
        jtAsignarCosto_Cuentas = new javax.swing.JTable();
        jifUsuario = new javax.swing.JInternalFrame();
        jpUsurio = new javax.swing.JPanel();
        jTabbedPane10 = new javax.swing.JTabbedPane();
        jPanel45 = new javax.swing.JPanel();
        jScrollPane22 = new javax.swing.JScrollPane();
        jtLista_Usuario = new javax.swing.JTable();
        txtFiltro_Usuario = new org.jdesktop.swingx.JXSearchField();
        cboTipoFiltro_Usuario = new javax.swing.JComboBox();
        jLabel77 = new javax.swing.JLabel();
        jPanel46 = new javax.swing.JPanel();
        jLabel80 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        jLabel96 = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        jLabel106 = new javax.swing.JLabel();
        jLabel107 = new javax.swing.JLabel();
        jLabel108 = new javax.swing.JLabel();
        jLabel109 = new javax.swing.JLabel();
        txtnombres_usuario = new javax.swing.JTextField();
        txtapellidos_usuario = new javax.swing.JTextField();
        txtID_Usuario = new javax.swing.JTextField();
        txtDireccion_Usuario = new javax.swing.JTextField();
        txtEmail_Usuario = new javax.swing.JTextField();
        txtTeleCelular_Usuario = new javax.swing.JTextField();
        txtFechaNacimiento_Usuario = new com.toedter.calendar.JDateChooser();
        cboCargo_Usuario = new org.jdesktop.swingx.JXComboBox();
        btn_Guardar_Usuario = new javax.swing.JButton();
        btn_Cancelar_Usuario = new javax.swing.JButton();
        jLabel41 = new javax.swing.JLabel();
        txtpass_usuario = new javax.swing.JPasswordField();
        txtdni_usuario = new javax.swing.JTextField();
        jifComites = new javax.swing.JInternalFrame();
        jPanel28 = new javax.swing.JPanel();
        jPanel29 = new javax.swing.JPanel();
        jLabel110 = new javax.swing.JLabel();
        txtComite_Registrar = new javax.swing.JTextField();
        btnGuardar_Comite = new javax.swing.JButton();
        jScrollPane25 = new javax.swing.JScrollPane();
        jtComite_Administracion = new javax.swing.JTable();
        jLabel111 = new javax.swing.JLabel();
        txtFiltroComite_Administracion = new org.jdesktop.swingx.JXSearchField();
        jdValidacion_Alquiler = new javax.swing.JDialog();
        jPanel22 = new javax.swing.JPanel();
        txtValidacionPass_Alquiler = new javax.swing.JPasswordField();
        btn_validar_alquiler = new javax.swing.JButton();
        jLabel62 = new javax.swing.JLabel();
        jifLateral_SubLateral_Adm = new javax.swing.JInternalFrame();
        jPanel24 = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel25 = new javax.swing.JPanel();
        txtFiltroNombre_Lateral = new org.jdesktop.swingx.JXSearchField();
        jLabel112 = new javax.swing.JLabel();
        jScrollPane28 = new javax.swing.JScrollPane();
        jtLateral_Adm = new javax.swing.JTable();
        jLabel113 = new javax.swing.JLabel();
        btn_cancelar_lateral = new javax.swing.JButton();
        btn_guardar_lateral = new javax.swing.JButton();
        txtNombre_Lateral = new javax.swing.JTextField();
        jLabel114 = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        jLabel115 = new javax.swing.JLabel();
        btn_guardar_sublateral = new javax.swing.JButton();
        btn_cancelar_sublateral = new javax.swing.JButton();
        jLabel117 = new javax.swing.JLabel();
        txtFiltroNombre_SubLateral = new org.jdesktop.swingx.JXSearchField();
        jScrollPane27 = new javax.swing.JScrollPane();
        jtSubLateral_Adm = new javax.swing.JTable();
        txtNombre_SubLateral = new javax.swing.JTextField();
        jlbSubLateral = new javax.swing.JLabel();
        jpmLateral = new javax.swing.JPopupMenu();
        jimQuitarLateral = new javax.swing.JMenuItem();
        jpmSubLateral = new javax.swing.JPopupMenu();
        jmiSubLateral = new javax.swing.JMenuItem();
        jifNoUsuario = new javax.swing.JInternalFrame();
        jPanel32 = new javax.swing.JPanel();
        jTabbedPane5 = new javax.swing.JTabbedPane();
        jPanel35 = new javax.swing.JPanel();
        jPanel37 = new javax.swing.JPanel();
        jLabel119 = new javax.swing.JLabel();
        txtFiltroNombre_NoCliente = new org.jdesktop.swingx.JXSearchField();
        jScrollPane31 = new javax.swing.JScrollPane();
        jtNoCliente_Adm = new javax.swing.JTable();
        jPanel36 = new javax.swing.JPanel();
        btn_guardar_nousuario = new javax.swing.JButton();
        btn_cancelar_nousuario = new javax.swing.JButton();
        jLabel127 = new javax.swing.JLabel();
        rbtnPersonaNatural = new javax.swing.JRadioButton();
        rbtnPersonaJuridica = new javax.swing.JRadioButton();
        jLabel126 = new javax.swing.JLabel();
        txtDNI_NoUsuario = new org.jdesktop.swingx.JXSearchField();
        jLabel121 = new javax.swing.JLabel();
        txtNombres_NoUsuario = new javax.swing.JTextField();
        jLabel122 = new javax.swing.JLabel();
        txtApePaterno_NoUsuario = new javax.swing.JTextField();
        jLabel123 = new javax.swing.JLabel();
        txtApeMaterno_NoUsuario = new javax.swing.JTextField();
        txtCelular_NoUsuario = new javax.swing.JTextField();
        jLabel129 = new javax.swing.JLabel();
        jLabel128 = new javax.swing.JLabel();
        txtTelefono_NoUsuario = new javax.swing.JTextField();
        txtDireccion_NoUsuario = new javax.swing.JTextField();
        jLabel124 = new javax.swing.JLabel();
        chkRuc = new javax.swing.JCheckBox();
        txtRuc_NoUsuario = new javax.swing.JTextField();
        jLabel125 = new javax.swing.JLabel();
        txtRazonSocial_NoUsuario = new javax.swing.JTextField();
        btngroup_NoUsuario = new javax.swing.ButtonGroup();
        btngroup_Tipo_Alquiler = new javax.swing.ButtonGroup();
        jpmTrabajador = new javax.swing.JPopupMenu();
        jmiPermiso = new javax.swing.JMenuItem();
        jdPermiso_Usuario = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        txtID_Usuario_Permiso = new javax.swing.JTextField();
        txtUsuario_Permiso = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtb_lista_permisos = new javax.swing.JTable();
        btnGuardar_Permisos = new javax.swing.JButton();
        jpInicio = new javax.swing.JPanel();
        jdeskpanInicio = new javax.swing.JDesktopPane();
        jTextField1 = new javax.swing.JTextField();
        txtFechaHora_Principal = new javax.swing.JTextField();
        txtAnio_Principal = new javax.swing.JTextField();
        txtUsuario_Principal = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jmbPrincipal = new javax.swing.JMenuBar();
        jmInicio = new javax.swing.JMenu();
        jmiSalir = new javax.swing.JMenuItem();
        jmCaja = new javax.swing.JMenu();
        jmiInicioCierre = new javax.swing.JMenuItem();
        jmiMovimiento = new javax.swing.JMenuItem();
        jmConstancia = new javax.swing.JMenu();
        jmiRegistro = new javax.swing.JMenuItem();
        jmiTraspaso = new javax.swing.JMenuItem();
        jmPagos = new javax.swing.JMenu();
        jmiVerPagos = new javax.swing.JMenuItem();
        jmiAlquiler = new javax.swing.JMenuItem();
        jmiPagoMultas = new javax.swing.JMenu();
        jmiPagoMultaAsamblea = new javax.swing.JMenuItem();
        jmiPagoMultaSufragio = new javax.swing.JMenuItem();
        jpReportes = new javax.swing.JMenu();
        jmiPagos = new javax.swing.JMenuItem();
        jmiClientes = new javax.swing.JMenuItem();
        jmiReporte_Movimiento = new javax.swing.JMenuItem();
        jmAdministracion = new javax.swing.JMenu();
        jmiTrabajador = new javax.swing.JMenuItem();
        jmiUsuario = new javax.swing.JMenuItem();
        jmiNoUsuario = new javax.swing.JMenuItem();
        jmiCuentas = new javax.swing.JMenuItem();
        jmiPeriodo = new javax.swing.JMenuItem();
        jmiCargos = new javax.swing.JMenuItem();
        jmiComite = new javax.swing.JMenuItem();
        jmiDocumento = new javax.swing.JMenuItem();
        jmiLateral_SubLateral = new javax.swing.JMenuItem();
        jmiMateriales = new javax.swing.JMenuItem();

        jifIngresarAlquiler.setClosable(true);
        jifIngresarAlquiler.setIconifiable(true);
        jifIngresarAlquiler.setResizable(true);
        jifIngresarAlquiler.setTitle("ALQUILER");
        jifIngresarAlquiler.setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/comision_logo.jpg"))); // NOI18N
        jifIngresarAlquiler.setVisible(true);

        jifRegistrarAlquiler.setBackground(new java.awt.Color(255, 255, 255));

        jtbAlquiler.setBackground(new java.awt.Color(195, 233, 164));
        jtbAlquiler.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jtbAlquiler.setOpaque(true);

        jPanel12.setBackground(new java.awt.Color(195, 233, 164));
        jPanel12.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jtLista_Alquileres.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N° Documento", "Usuario", "Tipo Material", "Fecha Inicio", "Fecha Fin", "Cantidad", "Monto"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtLista_Alquileres.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(jtLista_Alquileres);
        if (jtLista_Alquileres.getColumnModel().getColumnCount() > 0) {
            jtLista_Alquileres.getColumnModel().getColumn(1).setPreferredWidth(150);
            jtLista_Alquileres.getColumnModel().getColumn(2).setPreferredWidth(60);
            jtLista_Alquileres.getColumnModel().getColumn(3).setPreferredWidth(40);
            jtLista_Alquileres.getColumnModel().getColumn(4).setPreferredWidth(40);
            jtLista_Alquileres.getColumnModel().getColumn(5).setPreferredWidth(40);
            jtLista_Alquileres.getColumnModel().getColumn(6).setPreferredWidth(40);
        }

        jLabel82.setBackground(new java.awt.Color(0, 153, 153));
        jLabel82.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel82.setForeground(new java.awt.Color(255, 255, 255));
        jLabel82.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel82.setText("LISTA DE ALQUILERES");
        jLabel82.setOpaque(true);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        chkFiltroFecha_Alquiler.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chkFiltroFecha_Alquiler.setSelected(true);
        chkFiltroFecha_Alquiler.setText("Fecha:");
        chkFiltroFecha_Alquiler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkFiltroFecha_AlquilerActionPerformed(evt);
            }
        });

        txtFechaInicio_Alquiler.setDateFormatString("dd 'de' MMMM 'de' yyyy");
        txtFechaInicio_Alquiler.setEnabled(false);
        txtFechaInicio_Alquiler.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("Al");

        txtFechaFin_Alquiler.setDateFormatString("dd 'de' MMMM 'de' yyyy");
        txtFechaFin_Alquiler.setEnabled(false);
        txtFechaFin_Alquiler.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        chkFiltroAgricultor_Alquiler.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chkFiltroAgricultor_Alquiler.setText("Usuario:");
        chkFiltroAgricultor_Alquiler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkFiltroAgricultor_AlquilerActionPerformed(evt);
            }
        });

        cboAgricultor_Alquiler.setEnabled(false);
        cboAgricultor_Alquiler.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btn_buscar_alquileres.setBackground(new java.awt.Color(204, 255, 204));
        btn_buscar_alquileres.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_buscar_alquileres.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/Search-icon.png"))); // NOI18N
        btn_buscar_alquileres.setText("BUSCAR");
        btn_buscar_alquileres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscar_alquileresActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkFiltroFecha_Alquiler)
                    .addComponent(chkFiltroAgricultor_Alquiler))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtFechaInicio_Alquiler, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(txtFechaFin_Alquiler, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cboAgricultor_Alquiler, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(52, 52, 52)
                .addComponent(btn_buscar_alquileres, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtFechaInicio_Alquiler, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                                    .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(chkFiltroFecha_Alquiler, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFechaFin_Alquiler, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkFiltroAgricultor_Alquiler)
                            .addComponent(cboAgricultor_Alquiler, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btn_buscar_alquileres, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel82, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel82, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                .addContainerGap())
        );

        jtbAlquiler.addTab("CONSULTAR   ", new javax.swing.ImageIcon(getClass().getResource("/recurso/Consultar.png")), jPanel12); // NOI18N

        jPanel13.setBackground(new java.awt.Color(195, 233, 164));
        jPanel13.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jPanel11.setBackground(new java.awt.Color(195, 233, 164));
        jPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel24.setText("Activo Alquilado:");

        cboTipoMaterial_Alquiler.setEnabled(false);
        cboTipoMaterial_Alquiler.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel28.setText("Monto :");

        txtMonto_Alquiler.setForeground(new java.awt.Color(51, 153, 255));
        txtMonto_Alquiler.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMonto_Alquiler.setDisabledTextColor(new java.awt.Color(51, 153, 255));
        txtMonto_Alquiler.setEnabled(false);
        txtMonto_Alquiler.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtMonto_Alquiler.setPrompt("S/. 0.00");
        txtMonto_Alquiler.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMonto_AlquilerKeyTyped(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel25.setText("Cantidad:");

        txtCantidad_Alquiler.setEnabled(false);
        txtCantidad_Alquiler.setMaximum(100);
        txtCantidad_Alquiler.setMinimum(1);
        txtCantidad_Alquiler.setValue(1);

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel23.setText("Desde:");

        txtFechaDesde_Alquiler.setEnabled(false);
        txtFechaDesde_Alquiler.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel26.setText("Hasta:");

        txtFechaHasta_Alquiler.setEnabled(false);
        txtFechaHasta_Alquiler.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btnAgregarDet_Alquiler.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnAgregarDet_Alquiler.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/Create.png"))); // NOI18N
        btnAgregarDet_Alquiler.setText("Agregar");
        btnAgregarDet_Alquiler.setEnabled(false);
        btnAgregarDet_Alquiler.setIconTextGap(8);
        btnAgregarDet_Alquiler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarDet_AlquilerActionPerformed(evt);
            }
        });

        btnEliminarDet_Alquiler.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnEliminarDet_Alquiler.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/Remove.png"))); // NOI18N
        btnEliminarDet_Alquiler.setText("Eliminar");
        btnEliminarDet_Alquiler.setEnabled(false);
        btnEliminarDet_Alquiler.setIconTextGap(8);
        btnEliminarDet_Alquiler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarDet_AlquilerActionPerformed(evt);
            }
        });

        txtHoras_Alquiler.setEnabled(false);
        txtHoras_Alquiler.setMaximum(100);
        txtHoras_Alquiler.setMinimum(1);
        txtHoras_Alquiler.setValue(1);

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel42.setText("Horas:");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24)
                    .addComponent(jLabel23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(txtFechaDesde_Alquiler, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtFechaHasta_Alquiler, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(btnAgregarDet_Alquiler))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(cboTipoMaterial_Alquiler, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMonto_Alquiler, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCantidad_Alquiler, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtHoras_Alquiler, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnEliminarDet_Alquiler))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtCantidad_Alquiler, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel24)
                        .addComponent(cboTipoMaterial_Alquiler, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel28)
                        .addComponent(txtMonto_Alquiler, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel25))
                    .addComponent(txtHoras_Alquiler, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(23, 23, 23)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFechaDesde_Alquiler, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFechaHasta_Alquiler, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAgregarDet_Alquiler)
                        .addComponent(btnEliminarDet_Alquiler)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setText("Trabajador:");

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel21.setText("Usuario:");

        txtAgricultor_Alquiler.setEditable(false);
        txtAgricultor_Alquiler.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jtbDetalle_Alquiler.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Material", "Cantidad", "Desde", "Hasta", "Horas", "Monto"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtbDetalle_Alquiler.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane12.setViewportView(jtbDetalle_Alquiler);
        if (jtbDetalle_Alquiler.getColumnModel().getColumnCount() > 0) {
            jtbDetalle_Alquiler.getColumnModel().getColumn(1).setPreferredWidth(100);
            jtbDetalle_Alquiler.getColumnModel().getColumn(2).setPreferredWidth(40);
            jtbDetalle_Alquiler.getColumnModel().getColumn(3).setPreferredWidth(40);
            jtbDetalle_Alquiler.getColumnModel().getColumn(4).setPreferredWidth(40);
            jtbDetalle_Alquiler.getColumnModel().getColumn(6).setPreferredWidth(40);
        }

        btn_Cancelar1.setBackground(new java.awt.Color(255, 102, 0));
        btn_Cancelar1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_Cancelar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/Cancel-icon.png"))); // NOI18N
        btn_Cancelar1.setText("CANCELAR");
        btn_Cancelar1.setIconTextGap(8);
        btn_Cancelar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Cancelar1ActionPerformed(evt);
            }
        });

        btn_registrar_alquiler.setBackground(new java.awt.Color(255, 102, 0));
        btn_registrar_alquiler.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_registrar_alquiler.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/Save-icon.png"))); // NOI18N
        btn_registrar_alquiler.setText("GUARDAR");
        btn_registrar_alquiler.setIconTextGap(8);
        btn_registrar_alquiler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_registrar_alquilerActionPerformed(evt);
            }
        });

        btnBuscarAgricultor_Alquiler.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnBuscarAgricultor_Alquiler.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/Search.png"))); // NOI18N
        btnBuscarAgricultor_Alquiler.setText("Buscar Usuario");
        btnBuscarAgricultor_Alquiler.setIconTextGap(8);
        btnBuscarAgricultor_Alquiler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarAgricultor_AlquilerActionPerformed(evt);
            }
        });

        cboTrabajador_Alquiler.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel20)
                                    .addComponent(jLabel21))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtAgricultor_Alquiler, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
                                    .addComponent(cboTrabajador_Alquiler, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnBuscarAgricultor_Alquiler)
                                .addGap(73, 73, 73))
                            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane12))
                        .addContainerGap(25, Short.MAX_VALUE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_registrar_alquiler, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_Cancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42))))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(cboTrabajador_Alquiler, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAgricultor_Alquiler, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(btnBuscarAgricultor_Alquiler))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Cancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_registrar_alquiler, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jtbAlquiler.addTab("REGISTRAR   ", new javax.swing.ImageIcon(getClass().getResource("/recurso/registro_2.png")), jPanel13); // NOI18N

        javax.swing.GroupLayout jifRegistrarAlquilerLayout = new javax.swing.GroupLayout(jifRegistrarAlquiler);
        jifRegistrarAlquiler.setLayout(jifRegistrarAlquilerLayout);
        jifRegistrarAlquilerLayout.setHorizontalGroup(
            jifRegistrarAlquilerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jtbAlquiler)
        );
        jifRegistrarAlquilerLayout.setVerticalGroup(
            jifRegistrarAlquilerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jifRegistrarAlquilerLayout.createSequentialGroup()
                .addComponent(jtbAlquiler, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jifIngresarAlquilerLayout = new javax.swing.GroupLayout(jifIngresarAlquiler.getContentPane());
        jifIngresarAlquiler.getContentPane().setLayout(jifIngresarAlquilerLayout);
        jifIngresarAlquilerLayout.setHorizontalGroup(
            jifIngresarAlquilerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jifRegistrarAlquiler, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jifIngresarAlquilerLayout.setVerticalGroup(
            jifIngresarAlquilerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jifRegistrarAlquiler, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jifAgricultores.setClosable(true);
        jifAgricultores.setIconifiable(true);
        jifAgricultores.setResizable(true);
        jifAgricultores.setTitle("USUARIO");
        jifAgricultores.setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/comision_logo.jpg"))); // NOI18N
        jifAgricultores.setVisible(true);

        jTabbedPane4.setBackground(new java.awt.Color(195, 233, 164));
        jTabbedPane4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTabbedPane4.setOpaque(true);

        jPanel17.setBackground(new java.awt.Color(195, 233, 164));
        jPanel17.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jtAgricultor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "DNI", "USUARIO", "TELÉFONO / CELULAR", "DIRECCIÓN", "# LATERALES", "N° HECTAREAS"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtAgricultor.setToolTipText("");
        jtAgricultor.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jtAgricultor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jtAgricultorMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jtAgricultorMouseReleased(evt);
            }
        });
        jScrollPane7.setViewportView(jtAgricultor);
        if (jtAgricultor.getColumnModel().getColumnCount() > 0) {
            jtAgricultor.getColumnModel().getColumn(0).setPreferredWidth(10);
            jtAgricultor.getColumnModel().getColumn(1).setPreferredWidth(20);
            jtAgricultor.getColumnModel().getColumn(2).setPreferredWidth(140);
            jtAgricultor.getColumnModel().getColumn(3).setPreferredWidth(70);
            jtAgricultor.getColumnModel().getColumn(4).setPreferredWidth(140);
            jtAgricultor.getColumnModel().getColumn(5).setPreferredWidth(30);
        }

        jLabel70.setBackground(new java.awt.Color(0, 153, 153));
        jLabel70.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(255, 255, 255));
        jLabel70.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel70.setText("LISTA DE USUARIOS");
        jLabel70.setOpaque(true);

        txtFiltroAgricultor.setToolTipText("Buscar Usuario");
        txtFiltroAgricultor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtFiltroAgricultor.setPrompt("Buscar Usuario");
        txtFiltroAgricultor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFiltroAgricultorKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFiltroAgricultorKeyTyped(evt);
            }
        });

        cboFiltroAgricultor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cboFiltroAgricultor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nombre", "DNI" }));

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel70, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel17Layout.createSequentialGroup()
                        .addComponent(txtFiltroAgricultor, javax.swing.GroupLayout.DEFAULT_SIZE, 663, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboFiltroAgricultor, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboFiltroAgricultor, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFiltroAgricultor, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
                .addGap(6, 6, 6))
        );

        jTabbedPane4.addTab(" CONSULTAR    ", new javax.swing.ImageIcon(getClass().getResource("/recurso/Consultar.png")), jPanel17); // NOI18N

        jPanel18.setBackground(new java.awt.Color(195, 233, 164));
        jPanel18.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jTabbedPane7.setBackground(new java.awt.Color(195, 233, 164));
        jTabbedPane7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTabbedPane7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTabbedPane7.setOpaque(true);

        jPanel31.setBackground(new java.awt.Color(195, 233, 164));
        jPanel31.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel30.setText("Nombres:");

        txtNombres_Agricultor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNombres_Agricultor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNombres_Agricultor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombres_AgricultorKeyTyped(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel31.setText("Ape. Paterno:");

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel32.setText("Ape. Materno:");

        txtApePaterno_Agricultor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtApePaterno_Agricultor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtApePaterno_Agricultor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApePaterno_AgricultorKeyTyped(evt);
            }
        });

        txtApeMaterno_Agricultor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtApeMaterno_Agricultor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtApeMaterno_Agricultor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApeMaterno_AgricultorKeyTyped(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel33.setText("Direccion:");

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel34.setText("Email:");

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel35.setText("DNI:");

        txtEmail_Agricultor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtEmail_Agricultor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtEmail_Agricultor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEmail_AgricultorKeyTyped(evt);
            }
        });

        txtDireccion_Agricultor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtDireccion_Agricultor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDireccion_Agricultor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDireccion_AgricultorKeyTyped(evt);
            }
        });

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel36.setText("Sexo:");

        jLabel68.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel68.setText("Teléfono :");

        jLabel69.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel69.setText("Celular :");

        txtDNI_Agricultor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDNI_Agricultor.setText("47197204");
        txtDNI_Agricultor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtDNI_Agricultor.setPrompt("ingrese D.N.I");
        txtDNI_Agricultor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDNI_AgricultorKeyTyped(evt);
            }
        });

        txtTelefono_Agricultor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTelefono_Agricultor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTelefono_Agricultor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefono_AgricultorKeyTyped(evt);
            }
        });

        txtCelular_Agricultor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtCelular_Agricultor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCelular_Agricultor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCelular_AgricultorKeyTyped(evt);
            }
        });

        cboSexo_Agricultor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "FEMENINO", "MASCULINO" }));
        cboSexo_Agricultor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31)
                    .addComponent(jLabel35)
                    .addComponent(jLabel68)
                    .addComponent(jLabel33)
                    .addComponent(jLabel34)
                    .addComponent(jLabel36)
                    .addComponent(jLabel30))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtEmail_Agricultor)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel31Layout.createSequentialGroup()
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createSequentialGroup()
                                .addComponent(txtDNI_Agricultor, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(75, 75, 75))
                            .addComponent(txtApePaterno_Agricultor)
                            .addComponent(txtTelefono_Agricultor))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel32)
                            .addComponent(jLabel69))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtApeMaterno_Agricultor, javax.swing.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
                            .addComponent(txtCelular_Agricultor)))
                    .addComponent(txtDireccion_Agricultor, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNombres_Agricultor)
                    .addComponent(cboSexo_Agricultor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(33, 33, 33))
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(txtDNI_Agricultor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(txtNombres_Agricultor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(txtApePaterno_Agricultor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32)
                    .addComponent(txtApeMaterno_Agricultor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel68)
                    .addComponent(txtTelefono_Agricultor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel69)
                    .addComponent(txtCelular_Agricultor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDireccion_Agricultor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(cboSexo_Agricultor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmail_Agricultor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jTabbedPane7.addTab("Datos Personales", jPanel31);

        jpLaterales.setBackground(new java.awt.Color(195, 233, 164));
        jpLaterales.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel40.setText("Lateral:");

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel39.setText("Sub Lateral:");

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel38.setText("Con Medida:");

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel49.setText("Sin Medida:");

        btnEliminar_DetLateales.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnEliminar_DetLateales.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/Remove.png"))); // NOI18N
        btnEliminar_DetLateales.setText("Eliminar");
        btnEliminar_DetLateales.setEnabled(false);
        btnEliminar_DetLateales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminar_DetLatealesActionPerformed(evt);
            }
        });

        jButton17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/Create.png"))); // NOI18N
        jButton17.setText("Agregar");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jtDetalleLaterales_Agricultor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N° Lateral", "N° Sub Lateral", "Lateral", "Sub Lateral", "Sin Medida", "Con Medida", "N° Hectareas"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtDetalleLaterales_Agricultor.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane5.setViewportView(jtDetalleLaterales_Agricultor);

        jLabel37.setBackground(new java.awt.Color(0, 153, 153));
        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setText("LISTA DE LATERALES");
        jLabel37.setOpaque(true);

        txtSinMedida_Agricultor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtSinMedida_Agricultor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSinMedida_Agricultor.setText("0.0");
        txtSinMedida_Agricultor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSinMedida_AgricultorKeyTyped(evt);
            }
        });

        txtConMedida_Agricultor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtConMedida_Agricultor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtConMedida_Agricultor.setText("0.0");
        txtConMedida_Agricultor.setToolTipText("");
        txtConMedida_Agricultor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtConMedida_AgricultorKeyTyped(evt);
            }
        });

        jLabel56.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel56.setText("N° Hectareas");

        txtNumHectareas_Agricultor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNumHectareas_Agricultor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNumHectareas_Agricultor.setText("0.0");
        txtNumHectareas_Agricultor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumHectareas_AgricultorKeyTyped(evt);
            }
        });

        cboSubLateral_Agricultor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        cboLateral_Agricultor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout jpLateralesLayout = new javax.swing.GroupLayout(jpLaterales);
        jpLaterales.setLayout(jpLateralesLayout);
        jpLateralesLayout.setHorizontalGroup(
            jpLateralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpLateralesLayout.createSequentialGroup()
                .addGroup(jpLateralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpLateralesLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jpLateralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel39)
                            .addComponent(jLabel40))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpLateralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cboSubLateral_Agricultor, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                            .addComponent(cboLateral_Agricultor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(33, 33, 33)
                        .addGroup(jpLateralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel38)
                            .addGroup(jpLateralesLayout.createSequentialGroup()
                                .addComponent(jLabel49)
                                .addGap(34, 34, 34)
                                .addGroup(jpLateralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtConMedida_Agricultor)
                                    .addComponent(txtSinMedida_Agricultor, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE))))
                        .addGap(42, 42, 42)
                        .addGroup(jpLateralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jpLateralesLayout.createSequentialGroup()
                                .addComponent(jLabel56)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtNumHectareas_Agricultor))
                            .addGroup(jpLateralesLayout.createSequentialGroup()
                                .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnEliminar_DetLateales, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpLateralesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jpLateralesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane5)))
                .addContainerGap())
        );
        jpLateralesLayout.setVerticalGroup(
            jpLateralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpLateralesLayout.createSequentialGroup()
                .addGroup(jpLateralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpLateralesLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jpLateralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpLateralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel56)
                                .addComponent(txtNumHectareas_Agricultor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpLateralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel40)
                                .addComponent(jLabel49)
                                .addComponent(txtSinMedida_Agricultor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpLateralesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(cboLateral_Agricultor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jpLateralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpLateralesLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jpLateralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel39)
                            .addComponent(jLabel38)
                            .addComponent(btnEliminar_DetLateales)
                            .addComponent(txtConMedida_Agricultor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton17)))
                    .addGroup(jpLateralesLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(cboSubLateral_Agricultor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84))
        );

        jTabbedPane7.addTab("Laterales", jpLaterales);

        btnGuardar.setBackground(new java.awt.Color(255, 102, 0));
        btnGuardar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/Save-icon.png"))); // NOI18N
        btnGuardar.setText("GUARDAR");
        btnGuardar.setIconTextGap(8);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setBackground(new java.awt.Color(255, 102, 0));
        btnCancelar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/Cancel-icon.png"))); // NOI18N
        btnCancelar.setText("CANCELAR");
        btnCancelar.setIconTextGap(8);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane7)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addComponent(jTabbedPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("REGISTRAR  ", new javax.swing.ImageIcon(getClass().getResource("/recurso/agricultor.png")), jPanel18); // NOI18N

        javax.swing.GroupLayout jifAgricultoresLayout = new javax.swing.GroupLayout(jifAgricultores.getContentPane());
        jifAgricultores.getContentPane().setLayout(jifAgricultoresLayout);
        jifAgricultoresLayout.setHorizontalGroup(
            jifAgricultoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane4)
        );
        jifAgricultoresLayout.setVerticalGroup(
            jifAgricultoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 498, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jifPeriodos.setClosable(true);
        jifPeriodos.setIconifiable(true);
        jifPeriodos.setResizable(true);
        jifPeriodos.setTitle("PERIODOS");
        jifPeriodos.setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/comision_logo.jpg"))); // NOI18N
        jifPeriodos.setVisible(true);

        jPanel26.setBackground(new java.awt.Color(195, 233, 164));
        jPanel26.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        btn_guardar_periodo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_guardar_periodo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/Save.png"))); // NOI18N
        btn_guardar_periodo.setText("GUARDAR");
        btn_guardar_periodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardar_periodoActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/cancelar.png"))); // NOI18N
        jButton1.setText("CANCELAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jtPeriodo_All.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Periodo", "Fecha Inicio", "Fecha Fin"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtPeriodo_All.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane8.setViewportView(jtPeriodo_All);

        cboFiltro_Periodo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cboFiltro_Periodo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nombre", "Mes Inicio", "Mes Fin" }));

        txtFiltro_Periodo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtFiltro_Periodo.setPrompt("Buscar Campañas");
        txtFiltro_Periodo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFiltro_PeriodoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFiltro_PeriodoKeyTyped(evt);
            }
        });

        jLabel78.setBackground(new java.awt.Color(0, 153, 153));
        jLabel78.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel78.setForeground(new java.awt.Color(255, 255, 255));
        jLabel78.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel78.setText("LISTA CAMPAÑAS");
        jLabel78.setOpaque(true);

        jPanel16.setBackground(new java.awt.Color(195, 233, 164));
        jPanel16.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtNombre_Periodo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNombre_Periodo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNombre_Periodo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombre_PeriodoKeyTyped(evt);
            }
        });

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel44.setText("Nombre :");

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel46.setText("Mes Inicio :");

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel47.setText("Mes Fin :");

        cboPeriodo_MesInicio.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        cboPeriodo_MesFin.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel46)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel44)
                        .addGap(42, 42, 42)))
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cboPeriodo_MesInicio, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                    .addComponent(txtNombre_Periodo))
                .addGap(27, 27, 27)
                .addComponent(jLabel47)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cboPeriodo_MesFin, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNombre_Periodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel44))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cboPeriodo_MesFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel47))
                            .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cboPeriodo_MesInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jLabel79.setBackground(new java.awt.Color(195, 233, 164));
        jLabel79.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel79.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel79.setText("REGISTRO DE PERIODO");
        jLabel79.setOpaque(true);

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8)
                    .addComponent(jLabel79, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(btn_guardar_periodo, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)))
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addComponent(txtFiltro_Periodo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboFiltro_Periodo, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel78, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addComponent(btn_guardar_periodo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFiltro_Periodo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboFiltro_Periodo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jifPeriodosLayout = new javax.swing.GroupLayout(jifPeriodos.getContentPane());
        jifPeriodos.getContentPane().setLayout(jifPeriodosLayout);
        jifPeriodosLayout.setHorizontalGroup(
            jifPeriodosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jifPeriodosLayout.setVerticalGroup(
            jifPeriodosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jifDocumento.setClosable(true);
        jifDocumento.setIconifiable(true);
        jifDocumento.setResizable(true);
        jifDocumento.setTitle("DOCUMENTOS");
        jifDocumento.setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/comision_logo.jpg"))); // NOI18N
        jifDocumento.setVisible(true);

        javax.swing.GroupLayout jifDocumentoLayout = new javax.swing.GroupLayout(jifDocumento.getContentPane());
        jifDocumento.getContentPane().setLayout(jifDocumentoLayout);
        jifDocumentoLayout.setHorizontalGroup(
            jifDocumentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 647, Short.MAX_VALUE)
        );
        jifDocumentoLayout.setVerticalGroup(
            jifDocumentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 220, Short.MAX_VALUE)
        );

        jpBuscarAgricultor_Traspaso1.setBackground(new java.awt.Color(195, 233, 164));

        jLabel59.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel59.setText("Usuario:");

        txtModalAgricultor_Alquiler.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtModalAgricultor_Alquiler.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtModalAgricultor_AlquilerKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtModalAgricultor_AlquilerKeyTyped(evt);
            }
        });

        jtModalAgricultor_Alquiler.setModel(new javax.swing.table.DefaultTableModel(
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
        jtModalAgricultor_Alquiler.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jtModalAgricultor_Alquiler.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtModalAgricultor_AlquilerMouseClicked(evt);
            }
        });
        jtModalAgricultor_Alquiler.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtModalAgricultor_AlquilerKeyPressed(evt);
            }
        });
        jScrollPane18.setViewportView(jtModalAgricultor_Alquiler);
        if (jtModalAgricultor_Alquiler.getColumnModel().getColumnCount() > 0) {
            jtModalAgricultor_Alquiler.getColumnModel().getColumn(0).setPreferredWidth(30);
            jtModalAgricultor_Alquiler.getColumnModel().getColumn(1).setPreferredWidth(230);
        }

        btn_buscar_usuario_alquiler.setBackground(new java.awt.Color(204, 255, 204));
        btn_buscar_usuario_alquiler.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_buscar_usuario_alquiler.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/Search-icon.png"))); // NOI18N
        btn_buscar_usuario_alquiler.setText("BUSCAR");
        btn_buscar_usuario_alquiler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscar_usuario_alquilerActionPerformed(evt);
            }
        });

        jLabel130.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel130.setText("Tipo:");

        rbtnUsuario_Alquiler.setBackground(new java.awt.Color(195, 233, 164));
        btngroup_Tipo_Alquiler.add(rbtnUsuario_Alquiler);
        rbtnUsuario_Alquiler.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        rbtnUsuario_Alquiler.setSelected(true);
        rbtnUsuario_Alquiler.setText("Usuario");
        rbtnUsuario_Alquiler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnUsuario_AlquilerActionPerformed(evt);
            }
        });

        rbtnNoUsuario_Alquiler.setBackground(new java.awt.Color(195, 233, 164));
        btngroup_Tipo_Alquiler.add(rbtnNoUsuario_Alquiler);
        rbtnNoUsuario_Alquiler.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        rbtnNoUsuario_Alquiler.setText("No Usuario");
        rbtnNoUsuario_Alquiler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnNoUsuario_AlquilerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpBuscarAgricultor_Traspaso1Layout = new javax.swing.GroupLayout(jpBuscarAgricultor_Traspaso1);
        jpBuscarAgricultor_Traspaso1.setLayout(jpBuscarAgricultor_Traspaso1Layout);
        jpBuscarAgricultor_Traspaso1Layout.setHorizontalGroup(
            jpBuscarAgricultor_Traspaso1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpBuscarAgricultor_Traspaso1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpBuscarAgricultor_Traspaso1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane18, javax.swing.GroupLayout.DEFAULT_SIZE, 625, Short.MAX_VALUE)
                    .addGroup(jpBuscarAgricultor_Traspaso1Layout.createSequentialGroup()
                        .addGroup(jpBuscarAgricultor_Traspaso1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpBuscarAgricultor_Traspaso1Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jLabel130)
                                .addGap(25, 25, 25))
                            .addComponent(jLabel59, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(23, 23, 23)
                        .addGroup(jpBuscarAgricultor_Traspaso1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtModalAgricultor_Alquiler)
                            .addGroup(jpBuscarAgricultor_Traspaso1Layout.createSequentialGroup()
                                .addComponent(rbtnUsuario_Alquiler)
                                .addGap(18, 18, 18)
                                .addComponent(rbtnNoUsuario_Alquiler)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addComponent(btn_buscar_usuario_alquiler, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jpBuscarAgricultor_Traspaso1Layout.setVerticalGroup(
            jpBuscarAgricultor_Traspaso1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpBuscarAgricultor_Traspaso1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpBuscarAgricultor_Traspaso1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jpBuscarAgricultor_Traspaso1Layout.createSequentialGroup()
                        .addGroup(jpBuscarAgricultor_Traspaso1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel130)
                            .addComponent(rbtnUsuario_Alquiler)
                            .addComponent(rbtnNoUsuario_Alquiler))
                        .addGap(13, 13, 13)
                        .addGroup(jpBuscarAgricultor_Traspaso1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel59)
                            .addComponent(txtModalAgricultor_Alquiler, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btn_buscar_usuario_alquiler, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jdAlquilerAgricultorLayout = new javax.swing.GroupLayout(jdAlquilerAgricultor.getContentPane());
        jdAlquilerAgricultor.getContentPane().setLayout(jdAlquilerAgricultorLayout);
        jdAlquilerAgricultorLayout.setHorizontalGroup(
            jdAlquilerAgricultorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpBuscarAgricultor_Traspaso1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jdAlquilerAgricultorLayout.setVerticalGroup(
            jdAlquilerAgricultorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpBuscarAgricultor_Traspaso1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jmiEditar.setText("Editar");
        jmiEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiEditarActionPerformed(evt);
            }
        });
        jpmAgricultor.add(jmiEditar);

        jmip_Pagar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jmip_Pagar.setText("Realizar Pago");
        jmip_Pagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmip_PagarActionPerformed(evt);
            }
        });
        jpmVerPagos.add(jmip_Pagar);

        jmip_GenerarDocumento.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jmip_GenerarDocumento.setText("Ver Documento");
        jmip_GenerarDocumento.setToolTipText("");
        jpmVerPagos.add(jmip_GenerarDocumento);

        jmip_Anular.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jmip_Anular.setText("Anular");
        jmip_Anular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmip_AnularActionPerformed(evt);
            }
        });
        jpmVerPagos.add(jmip_Anular);

        jifCuentas.setClosable(true);
        jifCuentas.setIconifiable(true);
        jifCuentas.setResizable(true);
        jifCuentas.setTitle("CUENTAS");
        jifCuentas.setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/comision_logo.jpg"))); // NOI18N
        jifCuentas.setVisible(true);

        jTabbedPane2.setBackground(new java.awt.Color(195, 233, 164));
        jTabbedPane2.setOpaque(true);

        jPanel41.setBackground(new java.awt.Color(195, 233, 164));

        btnGuardar5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnGuardar5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/Save.png"))); // NOI18N
        btnGuardar5.setText("GUARDAR");
        btnGuardar5.setIconTextGap(8);
        btnGuardar5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar5ActionPerformed(evt);
            }
        });

        btnCancelar5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCancelar5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/cancelar.png"))); // NOI18N
        btnCancelar5.setText("CANCELAR");
        btnCancelar5.setIconTextGap(8);
        btnCancelar5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelar5ActionPerformed(evt);
            }
        });

        jLabel98.setBackground(new java.awt.Color(0, 153, 153));
        jLabel98.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel98.setForeground(new java.awt.Color(255, 255, 255));
        jLabel98.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel98.setText("LISTA DE CUENTAS");
        jLabel98.setOpaque(true);

        txtFiltroNombre_Cuenta2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtFiltroNombre_Cuenta2.setPrompt("Buscar Cuentas");
        txtFiltroNombre_Cuenta2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFiltroNombre_Cuenta2KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFiltroNombre_Cuenta2KeyTyped(evt);
            }
        });

        cboFiltro_Cuenta.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cboFiltro_Cuenta.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nombre", "N° Cuenta" }));

        jtCuentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Nombre", "N° Cuenta"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtCuentas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane23.setViewportView(jtCuentas);

        jLabel99.setBackground(new java.awt.Color(195, 233, 164));
        jLabel99.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel99.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel99.setText("REGISTRO DE CUENTAS");
        jLabel99.setOpaque(true);

        jPanel42.setBackground(new java.awt.Color(195, 233, 164));
        jPanel42.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        Codigo2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Codigo2.setText("Codigo:");

        jLabel100.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel100.setText("Nombre:");

        jLabel101.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel101.setText("N° Cuenta:");

        txtNombre_Cuentas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNombre_Cuentas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNombre_Cuentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombre_CuentasActionPerformed(evt);
            }
        });
        txtNombre_Cuentas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombre_CuentasKeyTyped(evt);
            }
        });

        txtCodigo_Cuenta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCodigo_Cuenta.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtCodigo_Cuenta.setPrompt("Ejemplo: CU1");
        txtCodigo_Cuenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigo_CuentaKeyTyped(evt);
            }
        });

        txtNumCuenta_Registrar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNumCuenta_Registrar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNumCuenta_Registrar.setPrompt("Ejemplo: 4567");
        txtNumCuenta_Registrar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumCuenta_RegistrarKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel42Layout = new javax.swing.GroupLayout(jPanel42);
        jPanel42.setLayout(jPanel42Layout);
        jPanel42Layout.setHorizontalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel42Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Codigo2)
                    .addComponent(jLabel100))
                .addGap(25, 25, 25)
                .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel42Layout.createSequentialGroup()
                        .addComponent(txtCodigo_Cuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel101)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNumCuenta_Registrar, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtNombre_Cuentas))
                .addGap(59, 59, 59))
        );
        jPanel42Layout.setVerticalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel42Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Codigo2)
                    .addComponent(jLabel101)
                    .addComponent(txtCodigo_Cuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNumCuenta_Registrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel100)
                    .addComponent(txtNombre_Cuentas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel41Layout = new javax.swing.GroupLayout(jPanel41);
        jPanel41.setLayout(jPanel41Layout);
        jPanel41Layout.setHorizontalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel41Layout.createSequentialGroup()
                        .addComponent(jPanel42, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnGuardar5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCancelar5, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE))
                        .addGap(94, 94, 94))
                    .addGroup(jPanel41Layout.createSequentialGroup()
                        .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel98, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel41Layout.createSequentialGroup()
                                .addComponent(txtFiltroNombre_Cuenta2, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                                .addComponent(cboFiltro_Cuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane23, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel99, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel41Layout.setVerticalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel99, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel41Layout.createSequentialGroup()
                        .addComponent(btnGuardar5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel98, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFiltroNombre_Cuenta2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboFiltro_Cuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane23, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
        jPanel39.setLayout(jPanel39Layout);
        jPanel39Layout.setHorizontalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addComponent(jPanel41, javax.swing.GroupLayout.PREFERRED_SIZE, 655, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
        );
        jPanel39Layout.setVerticalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("CUENTAS", new javax.swing.ImageIcon(getClass().getResource("/recurso/cuenta.png")), jPanel39); // NOI18N

        jPanel44.setBackground(new java.awt.Color(195, 233, 164));

        jLabel102.setBackground(new java.awt.Color(195, 233, 164));
        jLabel102.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel102.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel102.setText("ASIGNAR COSTOS A CUENTAS");
        jLabel102.setOpaque(true);

        Codigo3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Codigo3.setText("Cuentas:");

        jLabel103.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel103.setText("Monto:");

        txtMonto_AsignarCuenta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMonto_AsignarCuenta.setToolTipText("S/. 0.00");
        txtMonto_AsignarCuenta.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtMonto_AsignarCuenta.setPrompt("S/. 0.00");
        txtMonto_AsignarCuenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMonto_AsignarCuentaKeyTyped(evt);
            }
        });

        jLabel104.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel104.setText("Concepto:");

        cboCuentas_AsignarCostos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtConcepto_AsignarCosto.setColumns(20);
        txtConcepto_AsignarCosto.setRows(5);
        txtConcepto_AsignarCosto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtConcepto_AsignarCostoKeyTyped(evt);
            }
        });
        jScrollPane6.setViewportView(txtConcepto_AsignarCosto);

        btnGuardar6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnGuardar6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/Save.png"))); // NOI18N
        btnGuardar6.setText("GUARDAR");
        btnGuardar6.setIconTextGap(8);
        btnGuardar6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar6ActionPerformed(evt);
            }
        });

        btnCancelar6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCancelar6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/cancelar.png"))); // NOI18N
        btnCancelar6.setText("CANCELAR");
        btnCancelar6.setIconTextGap(8);
        btnCancelar6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelar6ActionPerformed(evt);
            }
        });

        jLabel105.setBackground(new java.awt.Color(0, 153, 153));
        jLabel105.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel105.setForeground(new java.awt.Color(255, 255, 255));
        jLabel105.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel105.setText("LISTA DE COSTOS POR CUENTA");
        jLabel105.setOpaque(true);

        jtAsignarCosto_Cuentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Nombre", "N° Cuenta"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtAsignarCosto_Cuentas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane24.setViewportView(jtAsignarCosto_Cuentas);

        javax.swing.GroupLayout jPanel44Layout = new javax.swing.GroupLayout(jPanel44);
        jPanel44.setLayout(jPanel44Layout);
        jPanel44Layout.setHorizontalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel44Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel102, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 637, Short.MAX_VALUE)
                    .addGroup(jPanel44Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Codigo3)
                            .addComponent(jLabel104))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                            .addComponent(cboCuentas_AsignarCostos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel44Layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnGuardar6, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnCancelar6, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel44Layout.createSequentialGroup()
                                .addComponent(jLabel103)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtMonto_AsignarCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane24)
                    .addComponent(jLabel105, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel44Layout.setVerticalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel44Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel102, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel44Layout.createSequentialGroup()
                        .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Codigo3)
                            .addComponent(cboCuentas_AsignarCostos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel103)
                            .addComponent(txtMonto_AsignarCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel104)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel44Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(btnGuardar6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jLabel105, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane24, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(150, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel43Layout = new javax.swing.GroupLayout(jPanel43);
        jPanel43.setLayout(jPanel43Layout);
        jPanel43Layout.setHorizontalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel43Layout.setVerticalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel43Layout.createSequentialGroup()
                .addComponent(jPanel44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("ASIGNAR COSTOS", new javax.swing.ImageIcon(getClass().getResource("/recurso/asignar_costo.png")), jPanel43); // NOI18N

        javax.swing.GroupLayout jifCuentasLayout = new javax.swing.GroupLayout(jifCuentas.getContentPane());
        jifCuentas.getContentPane().setLayout(jifCuentasLayout);
        jifCuentasLayout.setHorizontalGroup(
            jifCuentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jifCuentasLayout.createSequentialGroup()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 662, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jifCuentasLayout.setVerticalGroup(
            jifCuentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 516, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jifUsuario.setClosable(true);
        jifUsuario.setIconifiable(true);
        jifUsuario.setResizable(true);
        jifUsuario.setTitle("TRABAJADORES");
        jifUsuario.setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/comision_logo.jpg"))); // NOI18N
        jifUsuario.setVisible(true);

        jTabbedPane10.setBackground(new java.awt.Color(195, 233, 164));
        jTabbedPane10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTabbedPane10.setOpaque(true);

        jPanel45.setBackground(new java.awt.Color(195, 233, 164));
        jPanel45.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jtLista_Usuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "DNI", "Usuario", "Nombre y Apellido", "Teléfono/Celular", "Cargo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtLista_Usuario.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jtLista_Usuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jtLista_UsuarioMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jtLista_UsuarioMouseReleased(evt);
            }
        });
        jScrollPane22.setViewportView(jtLista_Usuario);

        txtFiltro_Usuario.setToolTipText("");
        txtFiltro_Usuario.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtFiltro_Usuario.setPrompt("Buscar Usuario");
        txtFiltro_Usuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFiltro_UsuarioKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFiltro_UsuarioKeyTyped(evt);
            }
        });

        cboTipoFiltro_Usuario.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cboTipoFiltro_Usuario.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "DNI", "NOMBRES", "APELLIDOS", "USUARIO" }));

        jLabel77.setBackground(new java.awt.Color(0, 153, 153));
        jLabel77.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel77.setForeground(new java.awt.Color(255, 255, 255));
        jLabel77.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel77.setText("LISTA DE TRABAJADORES");
        jLabel77.setOpaque(true);

        javax.swing.GroupLayout jPanel45Layout = new javax.swing.GroupLayout(jPanel45);
        jPanel45.setLayout(jPanel45Layout);
        jPanel45Layout.setHorizontalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel45Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane22)
                    .addComponent(jLabel77, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel45Layout.createSequentialGroup()
                        .addComponent(txtFiltro_Usuario, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboTipoFiltro_Usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel45Layout.setVerticalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel45Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboTipoFiltro_Usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFiltro_Usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane22, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane10.addTab("CONSULTAR   ", new javax.swing.ImageIcon(getClass().getResource("/recurso/Consultar.png")), jPanel45); // NOI18N

        jPanel46.setBackground(new java.awt.Color(195, 233, 164));
        jPanel46.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jLabel80.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel80.setText("Nombres:");

        jLabel94.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel94.setText("Apellidos:");

        jLabel95.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel95.setText("ID:");

        jLabel96.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel96.setText("DNI:");

        jLabel97.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel97.setText("Fec. Nacimiento:");

        jLabel106.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel106.setText("Email:");

        jLabel107.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel107.setText("Teléfono/Celular :");

        jLabel108.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel108.setText("Direccion:");

        jLabel109.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel109.setText("Cargo :");

        txtnombres_usuario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtnombres_usuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtnombres_usuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnombres_usuarioKeyTyped(evt);
            }
        });

        txtapellidos_usuario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtapellidos_usuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtapellidos_usuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtapellidos_usuarioKeyTyped(evt);
            }
        });

        txtID_Usuario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtID_Usuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtID_Usuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtID_UsuarioKeyTyped(evt);
            }
        });

        txtDireccion_Usuario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtDireccion_Usuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDireccion_Usuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDireccion_UsuarioKeyTyped(evt);
            }
        });

        txtEmail_Usuario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtEmail_Usuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtEmail_Usuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEmail_UsuarioKeyTyped(evt);
            }
        });

        txtTeleCelular_Usuario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTeleCelular_Usuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTeleCelular_Usuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTeleCelular_UsuarioKeyTyped(evt);
            }
        });

        txtFechaNacimiento_Usuario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        cboCargo_Usuario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btn_Guardar_Usuario.setBackground(new java.awt.Color(255, 102, 0));
        btn_Guardar_Usuario.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_Guardar_Usuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/Save-icon.png"))); // NOI18N
        btn_Guardar_Usuario.setText("GUARDAR");
        btn_Guardar_Usuario.setIconTextGap(8);
        btn_Guardar_Usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Guardar_UsuarioActionPerformed(evt);
            }
        });

        btn_Cancelar_Usuario.setBackground(new java.awt.Color(255, 102, 0));
        btn_Cancelar_Usuario.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_Cancelar_Usuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/Cancel-icon.png"))); // NOI18N
        btn_Cancelar_Usuario.setText("CANCELAR");
        btn_Cancelar_Usuario.setIconTextGap(8);
        btn_Cancelar_Usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Cancelar_UsuarioActionPerformed(evt);
            }
        });

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel41.setText("PASSWORD:");

        txtpass_usuario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtpass_usuario.setForeground(new java.awt.Color(255, 153, 0));
        txtpass_usuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtpass_usuario.setToolTipText("");
        txtpass_usuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtpass_usuarioKeyTyped(evt);
            }
        });

        txtdni_usuario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtdni_usuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtdni_usuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtdni_usuarioKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel46Layout = new javax.swing.GroupLayout(jPanel46);
        jPanel46.setLayout(jPanel46Layout);
        jPanel46Layout.setHorizontalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel46Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel46Layout.createSequentialGroup()
                        .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel94)
                            .addComponent(jLabel80))
                        .addGap(74, 74, 74)
                        .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtapellidos_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtnombres_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel46Layout.createSequentialGroup()
                            .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel96)
                                .addComponent(jLabel95))
                            .addGap(39, 39, 39)
                            .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel46Layout.createSequentialGroup()
                                    .addGap(267, 267, 267)
                                    .addComponent(jLabel41)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtpass_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel46Layout.createSequentialGroup()
                                    .addGap(70, 70, 70)
                                    .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtID_Usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtdni_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel46Layout.createSequentialGroup()
                                    .addGap(70, 70, 70)
                                    .addComponent(cboCargo_Usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel46Layout.createSequentialGroup()
                            .addComponent(jLabel97)
                            .addGap(29, 29, 29)
                            .addComponent(txtFechaNacimiento_Usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel107)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtTeleCelular_Usuario))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel46Layout.createSequentialGroup()
                                .addComponent(btn_Guardar_Usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_Cancelar_Usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel46Layout.createSequentialGroup()
                                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel108)
                                    .addComponent(jLabel106)
                                    .addComponent(jLabel109))
                                .addGap(75, 75, 75)
                                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtDireccion_Usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtEmail_Usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        jPanel46Layout.setVerticalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel46Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel95)
                    .addComponent(txtID_Usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel41)
                    .addComponent(txtpass_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel96)
                    .addComponent(txtdni_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtnombres_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel80))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtapellidos_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel94))
                .addGap(18, 18, 18)
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtFechaNacimiento_Usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel97, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel107)
                        .addComponent(txtTeleCelular_Usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel109)
                    .addComponent(cboCargo_Usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel108)
                    .addComponent(txtDireccion_Usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel106)
                    .addComponent(txtEmail_Usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_Guardar_Usuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_Cancelar_Usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jTabbedPane10.addTab("REGISTRAR  ", new javax.swing.ImageIcon(getClass().getResource("/recurso/agricultor.png")), jPanel46); // NOI18N

        javax.swing.GroupLayout jpUsurioLayout = new javax.swing.GroupLayout(jpUsurio);
        jpUsurio.setLayout(jpUsurioLayout);
        jpUsurioLayout.setHorizontalGroup(
            jpUsurioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane10)
        );
        jpUsurioLayout.setVerticalGroup(
            jpUsurioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout jifUsuarioLayout = new javax.swing.GroupLayout(jifUsuario.getContentPane());
        jifUsuario.getContentPane().setLayout(jifUsuarioLayout);
        jifUsuarioLayout.setHorizontalGroup(
            jifUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpUsurio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jifUsuarioLayout.setVerticalGroup(
            jifUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpUsurio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jifComites.setClosable(true);
        jifComites.setIconifiable(true);
        jifComites.setResizable(true);
        jifComites.setTitle("COMITES");
        jifComites.setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/comision_logo.jpg"))); // NOI18N
        jifComites.setVisible(true);

        jPanel28.setBackground(new java.awt.Color(195, 233, 164));

        jPanel29.setBackground(new java.awt.Color(195, 233, 164));
        jPanel29.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel110.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel110.setText("Comite :");

        txtComite_Registrar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtComite_Registrar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtComite_Registrar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtComite_RegistrarKeyTyped(evt);
            }
        });

        btnGuardar_Comite.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnGuardar_Comite.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/Save.png"))); // NOI18N
        btnGuardar_Comite.setText("GUARDAR");
        btnGuardar_Comite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar_ComiteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel110)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtComite_Registrar, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardar_Comite, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar_Comite, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtComite_Registrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel110))
                .addContainerGap())
        );

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
        jtComite_Administracion.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane25.setViewportView(jtComite_Administracion);

        jLabel111.setBackground(new java.awt.Color(0, 153, 153));
        jLabel111.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel111.setForeground(new java.awt.Color(255, 255, 255));
        jLabel111.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel111.setText("LISTA DE COMITES");
        jLabel111.setOpaque(true);

        txtFiltroComite_Administracion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtFiltroComite_Administracion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFiltroComite_AdministracionKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFiltroComite_AdministracionKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel111, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtFiltroComite_Administracion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane25, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel111, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFiltroComite_Administracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane25, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jifComitesLayout = new javax.swing.GroupLayout(jifComites.getContentPane());
        jifComites.getContentPane().setLayout(jifComitesLayout);
        jifComitesLayout.setHorizontalGroup(
            jifComitesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jifComitesLayout.setVerticalGroup(
            jifComitesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jdValidacion_Alquiler.setTitle("Validar Usuario");
        jdValidacion_Alquiler.setResizable(false);

        jPanel22.setBackground(new java.awt.Color(195, 233, 164));

        txtValidacionPass_Alquiler.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btn_validar_alquiler.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_validar_alquiler.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/key_validation.png"))); // NOI18N
        btn_validar_alquiler.setText("OK");
        btn_validar_alquiler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_validar_alquilerActionPerformed(evt);
            }
        });

        jLabel62.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel62.setText("Ingrese Clave:");

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(jLabel62)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(txtValidacionPass_Alquiler, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_validar_alquiler, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel62)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtValidacionPass_Alquiler, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_validar_alquiler))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jdValidacion_AlquilerLayout = new javax.swing.GroupLayout(jdValidacion_Alquiler.getContentPane());
        jdValidacion_Alquiler.getContentPane().setLayout(jdValidacion_AlquilerLayout);
        jdValidacion_AlquilerLayout.setHorizontalGroup(
            jdValidacion_AlquilerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jdValidacion_AlquilerLayout.createSequentialGroup()
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jdValidacion_AlquilerLayout.setVerticalGroup(
            jdValidacion_AlquilerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jifLateral_SubLateral_Adm.setClosable(true);
        jifLateral_SubLateral_Adm.setIconifiable(true);
        jifLateral_SubLateral_Adm.setResizable(true);
        jifLateral_SubLateral_Adm.setTitle("MANTENEDOR DE LATERALES");
        jifLateral_SubLateral_Adm.setToolTipText("");
        jifLateral_SubLateral_Adm.setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/comision_logo.jpg"))); // NOI18N
        jifLateral_SubLateral_Adm.setVisible(true);

        jPanel24.setBackground(new java.awt.Color(225, 253, 203));

        jTabbedPane3.setBackground(new java.awt.Color(195, 233, 164));
        jTabbedPane3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTabbedPane3.setOpaque(true);

        jPanel25.setBackground(new java.awt.Color(195, 233, 164));
        jPanel25.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        txtFiltroNombre_Lateral.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFiltroNombre_LateralKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFiltroNombre_LateralKeyTyped(evt);
            }
        });

        jLabel112.setBackground(new java.awt.Color(0, 153, 153));
        jLabel112.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel112.setForeground(new java.awt.Color(255, 255, 255));
        jLabel112.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel112.setText("LISTA DE LATERALES");
        jLabel112.setOpaque(true);

        jtLateral_Adm.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Lateral", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtLateral_Adm.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jtLateral_Adm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jtLateral_AdmMouseReleased(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jtLateral_AdmMousePressed(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtLateral_AdmMouseClicked(evt);
            }
        });
        jScrollPane28.setViewportView(jtLateral_Adm);

        jLabel113.setBackground(new java.awt.Color(195, 233, 164));
        jLabel113.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel113.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel113.setText("REGISTRO DE LATERALES");
        jLabel113.setOpaque(true);

        btn_cancelar_lateral.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_cancelar_lateral.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/cancelar.png"))); // NOI18N
        btn_cancelar_lateral.setText("CANCELAR");
        btn_cancelar_lateral.setIconTextGap(8);
        btn_cancelar_lateral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelar_lateralActionPerformed(evt);
            }
        });

        btn_guardar_lateral.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_guardar_lateral.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/Save.png"))); // NOI18N
        btn_guardar_lateral.setText("GUARDAR");
        btn_guardar_lateral.setIconTextGap(8);
        btn_guardar_lateral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardar_lateralActionPerformed(evt);
            }
        });

        txtNombre_Lateral.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNombre_Lateral.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNombre_Lateral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombre_LateralActionPerformed(evt);
            }
        });
        txtNombre_Lateral.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombre_LateralKeyTyped(evt);
            }
        });

        jLabel114.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel114.setText("LATERAL :");

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel113, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel112, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane28, javax.swing.GroupLayout.DEFAULT_SIZE, 681, Short.MAX_VALUE)
                    .addComponent(txtFiltroNombre_Lateral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addComponent(jLabel114)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNombre_Lateral)
                        .addGap(18, 18, 18)
                        .addComponent(btn_guardar_lateral, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_cancelar_lateral, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel113, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel114)
                    .addComponent(txtNombre_Lateral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cancelar_lateral, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_guardar_lateral, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel112, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFiltroNombre_Lateral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane28, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("LATERAL", new javax.swing.ImageIcon(getClass().getResource("/recurso/registro_2.png")), jPanel25); // NOI18N

        jPanel27.setBackground(new java.awt.Color(195, 233, 164));
        jPanel27.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jLabel115.setBackground(new java.awt.Color(195, 233, 164));
        jLabel115.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel115.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel115.setText("REGISTRO DE SUB-LATERALES");
        jLabel115.setOpaque(true);

        btn_guardar_sublateral.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_guardar_sublateral.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/Save.png"))); // NOI18N
        btn_guardar_sublateral.setText("GUARDAR");
        btn_guardar_sublateral.setIconTextGap(8);
        btn_guardar_sublateral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardar_sublateralActionPerformed(evt);
            }
        });

        btn_cancelar_sublateral.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_cancelar_sublateral.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/cancelar.png"))); // NOI18N
        btn_cancelar_sublateral.setText("CANCELAR");
        btn_cancelar_sublateral.setIconTextGap(8);
        btn_cancelar_sublateral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelar_sublateralActionPerformed(evt);
            }
        });

        jLabel117.setBackground(new java.awt.Color(0, 153, 153));
        jLabel117.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel117.setForeground(new java.awt.Color(255, 255, 255));
        jLabel117.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel117.setText("LISTA DE SUB-LATERALES");
        jLabel117.setOpaque(true);

        txtFiltroNombre_SubLateral.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFiltroNombre_SubLateralKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFiltroNombre_SubLateralKeyTyped(evt);
            }
        });

        jtSubLateral_Adm.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Sub Lateral", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtSubLateral_Adm.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jtSubLateral_Adm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jtSubLateral_AdmMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jtSubLateral_AdmMouseReleased(evt);
            }
        });
        jScrollPane27.setViewportView(jtSubLateral_Adm);

        txtNombre_SubLateral.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNombre_SubLateral.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNombre_SubLateral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombre_SubLateralActionPerformed(evt);
            }
        });
        txtNombre_SubLateral.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombre_SubLateralKeyTyped(evt);
            }
        });

        jlbSubLateral.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlbSubLateral.setText("SUB-LATERAL :");

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel117, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtFiltroNombre_SubLateral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel115, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addComponent(jlbSubLateral)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNombre_SubLateral, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_guardar_sublateral, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_cancelar_sublateral, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane27))
                .addContainerGap())
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel115, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_guardar_sublateral, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cancelar_sublateral, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbSubLateral)
                    .addComponent(txtNombre_SubLateral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel117, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFiltroNombre_SubLateral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane27, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("SUBLATERAL", new javax.swing.ImageIcon(getClass().getResource("/recurso/registro_2.png")), jPanel27); // NOI18N

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3)
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout jifLateral_SubLateral_AdmLayout = new javax.swing.GroupLayout(jifLateral_SubLateral_Adm.getContentPane());
        jifLateral_SubLateral_Adm.getContentPane().setLayout(jifLateral_SubLateral_AdmLayout);
        jifLateral_SubLateral_AdmLayout.setHorizontalGroup(
            jifLateral_SubLateral_AdmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jifLateral_SubLateral_AdmLayout.setVerticalGroup(
            jifLateral_SubLateral_AdmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jimQuitarLateral.setText("Quitar Lateral");
        jimQuitarLateral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jimQuitarLateralActionPerformed(evt);
            }
        });
        jpmLateral.add(jimQuitarLateral);

        jmiSubLateral.setText("Quitar Sub Lateral");
        jmiSubLateral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiSubLateralActionPerformed(evt);
            }
        });
        jpmSubLateral.add(jmiSubLateral);

        jifNoUsuario.setClosable(true);
        jifNoUsuario.setIconifiable(true);
        jifNoUsuario.setResizable(true);
        jifNoUsuario.setTitle("NO - USUARIO");
        jifNoUsuario.setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/comision_logo.jpg"))); // NOI18N
        jifNoUsuario.setVisible(true);

        jPanel32.setBackground(new java.awt.Color(225, 253, 203));

        jTabbedPane5.setBackground(new java.awt.Color(195, 233, 164));
        jTabbedPane5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTabbedPane5.setOpaque(true);

        jPanel37.setBackground(new java.awt.Color(195, 233, 164));
        jPanel37.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jLabel119.setBackground(new java.awt.Color(0, 153, 153));
        jLabel119.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel119.setForeground(new java.awt.Color(255, 255, 255));
        jLabel119.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel119.setText("LISTA DE NO - USUARIOS");
        jLabel119.setOpaque(true);

        txtFiltroNombre_NoCliente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtFiltroNombre_NoCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFiltroNombre_NoClienteKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFiltroNombre_NoClienteKeyTyped(evt);
            }
        });

        jtNoCliente_Adm.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "No-Usuario", "Direccion", "DNI", "RUC", "Telefono"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtNoCliente_Adm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jtNoCliente_AdmMouseReleased(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtNoCliente_AdmMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jtNoCliente_AdmMousePressed(evt);
            }
        });
        jScrollPane31.setViewportView(jtNoCliente_Adm);

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtFiltroNombre_NoCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel119, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane31, javax.swing.GroupLayout.DEFAULT_SIZE, 754, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel119, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFiltroNombre_NoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane31, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(72, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel35Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane5.addTab("CONSULTAR", new javax.swing.ImageIcon(getClass().getResource("/recurso/Consultar.png")), jPanel35); // NOI18N

        jPanel36.setBackground(new java.awt.Color(195, 233, 164));
        jPanel36.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        btn_guardar_nousuario.setBackground(new java.awt.Color(255, 102, 0));
        btn_guardar_nousuario.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_guardar_nousuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/Save-icon.png"))); // NOI18N
        btn_guardar_nousuario.setText("GUARDAR");
        btn_guardar_nousuario.setIconTextGap(8);
        btn_guardar_nousuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardar_nousuarioActionPerformed(evt);
            }
        });

        btn_cancelar_nousuario.setBackground(new java.awt.Color(255, 102, 0));
        btn_cancelar_nousuario.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_cancelar_nousuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/Cancel-icon.png"))); // NOI18N
        btn_cancelar_nousuario.setText("CANCELAR");
        btn_cancelar_nousuario.setIconTextGap(8);
        btn_cancelar_nousuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelar_nousuarioActionPerformed(evt);
            }
        });

        jLabel127.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel127.setText("TIPO:");

        rbtnPersonaNatural.setBackground(new java.awt.Color(195, 233, 164));
        btngroup_NoUsuario.add(rbtnPersonaNatural);
        rbtnPersonaNatural.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rbtnPersonaNatural.setText("Persona Natural");
        rbtnPersonaNatural.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnPersonaNaturalActionPerformed(evt);
            }
        });

        rbtnPersonaJuridica.setBackground(new java.awt.Color(195, 233, 164));
        btngroup_NoUsuario.add(rbtnPersonaJuridica);
        rbtnPersonaJuridica.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rbtnPersonaJuridica.setText("Persona Juridica");
        rbtnPersonaJuridica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnPersonaJuridicaActionPerformed(evt);
            }
        });

        jLabel126.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel126.setText("DNI:");

        txtDNI_NoUsuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDNI_NoUsuario.setEnabled(false);
        txtDNI_NoUsuario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtDNI_NoUsuario.setPrompt("ingrese D.N.I");
        txtDNI_NoUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDNI_NoUsuarioKeyTyped(evt);
            }
        });

        jLabel121.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel121.setText("NOMBRES:");

        txtNombres_NoUsuario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNombres_NoUsuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNombres_NoUsuario.setEnabled(false);
        txtNombres_NoUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombres_NoUsuarioKeyTyped(evt);
            }
        });

        jLabel122.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel122.setText("APE. PATERNO:");

        txtApePaterno_NoUsuario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtApePaterno_NoUsuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtApePaterno_NoUsuario.setEnabled(false);
        txtApePaterno_NoUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApePaterno_NoUsuarioKeyTyped(evt);
            }
        });

        jLabel123.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel123.setText("APE. MATERNO:");

        txtApeMaterno_NoUsuario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtApeMaterno_NoUsuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtApeMaterno_NoUsuario.setEnabled(false);
        txtApeMaterno_NoUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApeMaterno_NoUsuarioKeyTyped(evt);
            }
        });

        txtCelular_NoUsuario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtCelular_NoUsuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCelular_NoUsuario.setEnabled(false);
        txtCelular_NoUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCelular_NoUsuarioKeyTyped(evt);
            }
        });

        jLabel129.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel129.setText("CELULAR :");

        jLabel128.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel128.setText("TELEFONO :");

        txtTelefono_NoUsuario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTelefono_NoUsuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTelefono_NoUsuario.setEnabled(false);
        txtTelefono_NoUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefono_NoUsuarioKeyTyped(evt);
            }
        });

        txtDireccion_NoUsuario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtDireccion_NoUsuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDireccion_NoUsuario.setEnabled(false);
        txtDireccion_NoUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDireccion_NoUsuarioKeyTyped(evt);
            }
        });

        jLabel124.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel124.setText("DIRECCION:");

        chkRuc.setBackground(new java.awt.Color(195, 233, 164));
        chkRuc.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        chkRuc.setText("RUC:");
        chkRuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkRucActionPerformed(evt);
            }
        });

        txtRuc_NoUsuario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtRuc_NoUsuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtRuc_NoUsuario.setEnabled(false);
        txtRuc_NoUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRuc_NoUsuarioKeyTyped(evt);
            }
        });

        jLabel125.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel125.setText("RAZON SOCIAL:");

        txtRazonSocial_NoUsuario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtRazonSocial_NoUsuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtRazonSocial_NoUsuario.setEnabled(false);
        txtRazonSocial_NoUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRazonSocial_NoUsuarioKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
        jPanel36.setLayout(jPanel36Layout);
        jPanel36Layout.setHorizontalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel36Layout.createSequentialGroup()
                        .addComponent(btn_guardar_nousuario, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_cancelar_nousuario, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel36Layout.createSequentialGroup()
                            .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel126)
                                .addComponent(jLabel127)
                                .addComponent(jLabel121))
                            .addGap(49, 49, 49)
                            .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel36Layout.createSequentialGroup()
                                    .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(rbtnPersonaNatural)
                                        .addComponent(txtDNI_NoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(35, 35, 35)
                                    .addComponent(rbtnPersonaJuridica))
                                .addComponent(txtNombres_NoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel36Layout.createSequentialGroup()
                            .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel122)
                                .addComponent(jLabel128)
                                .addComponent(jLabel124)
                                .addComponent(chkRuc))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtRuc_NoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel36Layout.createSequentialGroup()
                                    .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtTelefono_NoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtApePaterno_NoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(82, 82, 82)
                                    .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(jPanel36Layout.createSequentialGroup()
                                            .addComponent(jLabel129)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtCelular_NoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel36Layout.createSequentialGroup()
                                            .addComponent(jLabel123)
                                            .addGap(30, 30, 30)
                                            .addComponent(txtApeMaterno_NoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addComponent(txtDireccion_NoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 589, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel36Layout.createSequentialGroup()
                            .addComponent(jLabel125)
                            .addGap(18, 18, 18)
                            .addComponent(txtRazonSocial_NoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 586, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel36Layout.setVerticalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtnPersonaNatural)
                    .addComponent(rbtnPersonaJuridica)
                    .addComponent(jLabel127))
                .addGap(18, 18, 18)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel126)
                    .addComponent(txtDNI_NoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel121)
                    .addComponent(txtNombres_NoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel122)
                    .addComponent(txtApePaterno_NoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel123)
                    .addComponent(txtApeMaterno_NoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCelular_NoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel129)
                    .addComponent(jLabel128)
                    .addComponent(txtTelefono_NoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDireccion_NoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel124, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRuc_NoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkRuc))
                .addGap(18, 18, 18)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel125)
                    .addComponent(txtRazonSocial_NoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_guardar_nousuario, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cancelar_nousuario, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jTabbedPane5.addTab("REGISTRAR   ", new javax.swing.ImageIcon(getClass().getResource("/recurso/agricultor.png")), jPanel36); // NOI18N

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addComponent(jTabbedPane5)
                .addGap(0, 0, 0))
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addComponent(jTabbedPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jifNoUsuarioLayout = new javax.swing.GroupLayout(jifNoUsuario.getContentPane());
        jifNoUsuario.getContentPane().setLayout(jifNoUsuarioLayout);
        jifNoUsuarioLayout.setHorizontalGroup(
            jifNoUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jifNoUsuarioLayout.setVerticalGroup(
            jifNoUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jmiPermiso.setText("Permiso");
        jmiPermiso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiPermisoActionPerformed(evt);
            }
        });
        jpmTrabajador.add(jmiPermiso);

        jdPermiso_Usuario.setTitle("PERMISO");

        jPanel1.setBackground(new java.awt.Color(195, 233, 164));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtID_Usuario_Permiso.setEditable(false);
        txtID_Usuario_Permiso.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtID_Usuario_Permiso.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtUsuario_Permiso.setEditable(false);
        txtUsuario_Permiso.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("USUARIO :");

        jtb_lista_permisos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "MENU", "SUB MENU", "ASIGNADO"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jtb_lista_permisos);

        btnGuardar_Permisos.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnGuardar_Permisos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/Save.png"))); // NOI18N
        btnGuardar_Permisos.setText("GUARDAR");
        btnGuardar_Permisos.setIconTextGap(8);
        btnGuardar_Permisos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar_PermisosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 664, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtID_Usuario_Permiso, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUsuario_Permiso))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnGuardar_Permisos, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtID_Usuario_Permiso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUsuario_Permiso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnGuardar_Permisos, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jdPermiso_UsuarioLayout = new javax.swing.GroupLayout(jdPermiso_Usuario.getContentPane());
        jdPermiso_Usuario.getContentPane().setLayout(jdPermiso_UsuarioLayout);
        jdPermiso_UsuarioLayout.setHorizontalGroup(
            jdPermiso_UsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jdPermiso_UsuarioLayout.setVerticalGroup(
            jdPermiso_UsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SISTEMA DE COBRANZA COMISIÓN DE USUARIOS PERLA DEL HUALLAGA");
        setIconImage(new ImageIcon(getClass().getResource("/recurso/comision logo.jpg")).getImage());

        jdeskpanInicio.setBackground(new java.awt.Color(255, 255, 255));
        jdeskpanInicio.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jdeskpanInicio.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout jdeskpanInicioLayout = new javax.swing.GroupLayout(jdeskpanInicio);
        jdeskpanInicio.setLayout(jdeskpanInicioLayout);
        jdeskpanInicioLayout.setHorizontalGroup(
            jdeskpanInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jdeskpanInicioLayout.setVerticalGroup(
            jdeskpanInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 434, Short.MAX_VALUE)
        );

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(255, 255, 255));
        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField1.setText("  COMIS. DE USUARIO PERLA DEL HUALLAGA");
        jTextField1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        txtFechaHora_Principal.setEditable(false);
        txtFechaHora_Principal.setBackground(new java.awt.Color(255, 255, 255));
        txtFechaHora_Principal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtFechaHora_Principal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFechaHora_Principal.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        txtAnio_Principal.setEditable(false);
        txtAnio_Principal.setBackground(new java.awt.Color(255, 255, 255));
        txtAnio_Principal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtAnio_Principal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAnio_Principal.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        txtUsuario_Principal.setEditable(false);
        txtUsuario_Principal.setBackground(new java.awt.Color(255, 255, 255));
        txtUsuario_Principal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtUsuario_Principal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtUsuario_Principal.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jTextField5.setEditable(false);
        jTextField5.setBackground(new java.awt.Color(255, 255, 255));
        jTextField5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField5.setText("TRABAJADOR :");
        jTextField5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        javax.swing.GroupLayout jpInicioLayout = new javax.swing.GroupLayout(jpInicio);
        jpInicio.setLayout(jpInicioLayout);
        jpInicioLayout.setHorizontalGroup(
            jpInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jdeskpanInicio)
            .addGroup(jpInicioLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAnio_Principal, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFechaHora_Principal, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUsuario_Principal, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );
        jpInicioLayout.setVerticalGroup(
            jpInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpInicioLayout.createSequentialGroup()
                .addComponent(jdeskpanInicio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFechaHora_Principal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAnio_Principal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUsuario_Principal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        jmbPrincipal.setBackground(new java.awt.Color(225, 253, 203));
        jmbPrincipal.setFont(new java.awt.Font("Garamond", 1, 18)); // NOI18N
        jmbPrincipal.setPreferredSize(new java.awt.Dimension(0, 40));

        jmInicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/Home.png"))); // NOI18N
        jmInicio.setText("INICIO");
        jmInicio.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jmInicio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jmiSalir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        jmiSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/Exit.png"))); // NOI18N
        jmiSalir.setText("CERRAR SESION");
        jmiSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiSalirActionPerformed(evt);
            }
        });
        jmInicio.add(jmiSalir);

        jmbPrincipal.add(jmInicio);

        jmCaja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/caja.png"))); // NOI18N
        jmCaja.setText("CAJA");
        jmCaja.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jmCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmCajaActionPerformed(evt);
            }
        });

        jmiInicioCierre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/inicio.png"))); // NOI18N
        jmiInicioCierre.setText("INICIAR / CIERRE");
        jmiInicioCierre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiInicioCierreActionPerformed(evt);
            }
        });
        jmCaja.add(jmiInicioCierre);

        jmiMovimiento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/money.png"))); // NOI18N
        jmiMovimiento.setText("MOVIMIENTOS");
        jmiMovimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiMovimientoActionPerformed(evt);
            }
        });
        jmCaja.add(jmiMovimiento);

        jmbPrincipal.add(jmCaja);

        jmConstancia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/constancia.png"))); // NOI18N
        jmConstancia.setText("CONSTANCIA");
        jmConstancia.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jmiRegistro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/constancia_icon.png"))); // NOI18N
        jmiRegistro.setText("REGISTRO");
        jmiRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiRegistroActionPerformed(evt);
            }
        });
        jmConstancia.add(jmiRegistro);

        jmiTraspaso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/traspaso.png"))); // NOI18N
        jmiTraspaso.setText("TRASPASO");
        jmiTraspaso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiTraspasoActionPerformed(evt);
            }
        });
        jmConstancia.add(jmiTraspaso);

        jmbPrincipal.add(jmConstancia);

        jmPagos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/pagos.png"))); // NOI18N
        jmPagos.setText("PAGOS");
        jmPagos.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jmiVerPagos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/money.png"))); // NOI18N
        jmiVerPagos.setText("VER PAGOS");
        jmiVerPagos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiVerPagosActionPerformed(evt);
            }
        });
        jmPagos.add(jmiVerPagos);

        jmiAlquiler.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/alquiler.png"))); // NOI18N
        jmiAlquiler.setText("ALQUILER");
        jmiAlquiler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiAlquilerActionPerformed(evt);
            }
        });
        jmPagos.add(jmiAlquiler);

        jmiPagoMultas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/caja_1.png"))); // NOI18N
        jmiPagoMultas.setText("PAGO MULTAS");

        jmiPagoMultaAsamblea.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/asamblea.png"))); // NOI18N
        jmiPagoMultaAsamblea.setText("ASAMBLEA");
        jmiPagoMultaAsamblea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiPagoMultaAsambleaActionPerformed(evt);
            }
        });
        jmiPagoMultas.add(jmiPagoMultaAsamblea);

        jmiPagoMultaSufragio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/votaciones.png"))); // NOI18N
        jmiPagoMultaSufragio.setText("SUFRAGIO");
        jmiPagoMultaSufragio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiPagoMultaSufragioActionPerformed(evt);
            }
        });
        jmiPagoMultas.add(jmiPagoMultaSufragio);

        jmPagos.add(jmiPagoMultas);

        jmbPrincipal.add(jmPagos);

        jpReportes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/report_menu.png"))); // NOI18N
        jpReportes.setText("REPORTES");
        jpReportes.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jmiPagos.setText("PAGOS");
        jpReportes.add(jmiPagos);

        jmiClientes.setText("USUARIOS");
        jpReportes.add(jmiClientes);

        jmiReporte_Movimiento.setText("MOVIMIENTO");
        jmiReporte_Movimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiReporte_MovimientoActionPerformed(evt);
            }
        });
        jpReportes.add(jmiReporte_Movimiento);

        jmbPrincipal.add(jpReportes);

        jmAdministracion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/configuracion.png"))); // NOI18N
        jmAdministracion.setText("ADMINISTRACIÓN");
        jmAdministracion.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jmAdministracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmAdministracionActionPerformed(evt);
            }
        });

        jmiTrabajador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/icon_usuario.png"))); // NOI18N
        jmiTrabajador.setText("TRABAJADOR");
        jmiTrabajador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiTrabajadorActionPerformed(evt);
            }
        });
        jmAdministracion.add(jmiTrabajador);

        jmiUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/icon_agricultor.png"))); // NOI18N
        jmiUsuario.setText("USUARIO");
        jmiUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiUsuarioActionPerformed(evt);
            }
        });
        jmAdministracion.add(jmiUsuario);

        jmiNoUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/no_user.png"))); // NOI18N
        jmiNoUsuario.setText("NO USUARIO");
        jmiNoUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiNoUsuarioActionPerformed(evt);
            }
        });
        jmAdministracion.add(jmiNoUsuario);

        jmiCuentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/lista.png"))); // NOI18N
        jmiCuentas.setText("CUENTAS");
        jmiCuentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiCuentasActionPerformed(evt);
            }
        });
        jmAdministracion.add(jmiCuentas);

        jmiPeriodo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/periodo.png"))); // NOI18N
        jmiPeriodo.setText("PERIODO");
        jmiPeriodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiPeriodoActionPerformed(evt);
            }
        });
        jmAdministracion.add(jmiPeriodo);

        jmiCargos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/lista_cargo.png"))); // NOI18N
        jmiCargos.setText("CARGOS");
        jmiCargos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiCargosActionPerformed(evt);
            }
        });
        jmAdministracion.add(jmiCargos);

        jmiComite.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/lista_cargo.png"))); // NOI18N
        jmiComite.setText("COMITES");
        jmiComite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiComiteActionPerformed(evt);
            }
        });
        jmAdministracion.add(jmiComite);

        jmiDocumento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/documento.png"))); // NOI18N
        jmiDocumento.setText("DOCUMENTO");
        jmAdministracion.add(jmiDocumento);

        jmiLateral_SubLateral.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/lista_cargo.png"))); // NOI18N
        jmiLateral_SubLateral.setText("LALTERAL/SUBLATERAL");
        jmiLateral_SubLateral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiLateral_SubLateralActionPerformed(evt);
            }
        });
        jmAdministracion.add(jmiLateral_SubLateral);

        jmiMateriales.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/lista_cargo.png"))); // NOI18N
        jmiMateriales.setText("MATERIALES");
        jmiMateriales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiMaterialesActionPerformed(evt);
            }
        });
        jmAdministracion.add(jmiMateriales);

        jmbPrincipal.add(jmAdministracion);

        setJMenuBar(jmbPrincipal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpInicio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpInicio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jmiCargosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiCargosActionPerformed
        iniciarFomrulario_Cargo(new jifCargos_C());
    }//GEN-LAST:event_jmiCargosActionPerformed

    private void jmiAlquilerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiAlquilerActionPerformed
        try {
            limpiarFomulario_Alquiler();
            getcombo_material_all("");
            getcombo_cliente_all();
            getcombo_usuario();
            iniciarFomrulario_Alquiler(jifIngresarAlquiler);
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_jmiAlquilerActionPerformed

    private void jmiInicioCierreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiInicioCierreActionPerformed
        iniciarFomrulario_CierreInicioCaja(new jifInicioCierreCaja_C());
    }//GEN-LAST:event_jmiInicioCierreActionPerformed

    private void jmiRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiRegistroActionPerformed
        iniciarFomrulario_Constancia(new jifConstancia_C());
    }//GEN-LAST:event_jmiRegistroActionPerformed

    private void jmiTraspasoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiTraspasoActionPerformed
        iniciarFomrulario_Traspaso(new jifTraspaso_C());
    }//GEN-LAST:event_jmiTraspasoActionPerformed

    private void jmiVerPagosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiVerPagosActionPerformed
        iniciarFomrulario_VerPagos(new jifVerPagos_C());
    }//GEN-LAST:event_jmiVerPagosActionPerformed

    private void jmiTrabajadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiTrabajadorActionPerformed
        limpiarFomulario_Usuario();
        getcombo_cargo_all("");
        gettabla_usuario_byfiltro("", 0);
        iniciarFomrulario_Usuario(jifUsuario);
    }//GEN-LAST:event_jmiTrabajadorActionPerformed

    private void jmiUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiUsuarioActionPerformed
        get_lateral_all();
        get_sublatreles_all("");
        gettabla_agricultor_all("", 1);
        iniciarFomrulario_Agricultor(jifAgricultores);
    }//GEN-LAST:event_jmiUsuarioActionPerformed

    private void jmiCuentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiCuentasActionPerformed
        gettabla_cuenta_all("", 0);
        gettabla_asignacioncosto_cuenta_all();
        limpiarFomulario_Cuenta();
        limpiarFomulario_AsignacionCosto_Cuenta();
        iniciarFomrulario_Cuentas(jifCuentas);
    }//GEN-LAST:event_jmiCuentasActionPerformed

    private void jmiPeriodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiPeriodoActionPerformed
        getcombo_periodo_mesiniciofin();
        gettabla_periodo_all("", 0);
        iniciarFomrulario_Periodo(jifPeriodos);
    }//GEN-LAST:event_jmiPeriodoActionPerformed

    private void jmiComiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiComiteActionPerformed
        iniciarFomrulario_Comite(new jifComites_C());
    }//GEN-LAST:event_jmiComiteActionPerformed

    private void jmiMovimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiMovimientoActionPerformed
        iniciarFomrulario_Movimiento(new jifMovimientos_C());
    }//GEN-LAST:event_jmiMovimientoActionPerformed

    private void jmiSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jmiSalirActionPerformed


    private void txtMonto_AlquilerKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMonto_AlquilerKeyTyped
        new Funciones().soloDecimales(evt);
        if (txtMonto_Alquiler.getText().length() == 16) {
            evt.consume();
        }
    }//GEN-LAST:event_txtMonto_AlquilerKeyTyped

    private void txtModalAgricultor_AlquilerKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtModalAgricultor_AlquilerKeyReleased
        if (rbtnUsuario_Alquiler.isSelected()) {
            gettabla_agricultor_alquiler_byActivos(txtModalAgricultor_Alquiler.getText());
        }
        if (rbtnNoUsuario_Alquiler.isSelected()) {
            gettable_nocliente_alquiler_all(txtModalAgricultor_Alquiler.getText());
        }
    }//GEN-LAST:event_txtModalAgricultor_AlquilerKeyReleased

    private void jtModalAgricultor_AlquilerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtModalAgricultor_AlquilerMouseClicked
        try {

            idAgricultor_Alquiler = Integer.parseInt(String.valueOf(jtModalAgricultor_Alquiler.getModel().getValueAt(jtModalAgricultor_Alquiler.getSelectedRow(), 0)));
            txtAgricultor_Alquiler.setText(String.valueOf(jtModalAgricultor_Alquiler.getModel().getValueAt(jtModalAgricultor_Alquiler.getSelectedRow(), 1)));
            if (rbtnUsuario_Alquiler.isSelected()) {
                identificador = 1;

            }
            if (rbtnNoUsuario_Alquiler.isSelected()) {
                identificador = 0;

            }
            cboTipoMaterial_Alquiler.setEnabled(true);
            txtMonto_Alquiler.setEnabled(true);
            txtCantidad_Alquiler.setEnabled(true);
            txtHoras_Alquiler.setEnabled(true);
            txtFechaDesde_Alquiler.setEnabled(true);
            txtFechaHasta_Alquiler.setEnabled(true);
            btnAgregarDet_Alquiler.setEnabled(true);
        } catch (Exception e) {
            System.out.println("" + e.toString());
        } finally {
            jdAlquilerAgricultor.dispose();
        }
    }//GEN-LAST:event_jtModalAgricultor_AlquilerMouseClicked

    private void jtModalAgricultor_AlquilerKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtModalAgricultor_AlquilerKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                idAgricultor_Alquiler = Integer.parseInt(String.valueOf(jtModalAgricultor_Alquiler.getModel().getValueAt(jtModalAgricultor_Alquiler.getSelectedRow(), 0)));
                txtAgricultor_Alquiler.setText(String.valueOf(jtModalAgricultor_Alquiler.getModel().getValueAt(jtModalAgricultor_Alquiler.getSelectedRow(), 1)));
            } catch (Exception e) {
                System.out.println("" + e.toString());
            } finally {
                jdAlquilerAgricultor.dispose();
            }
        }
    }//GEN-LAST:event_jtModalAgricultor_AlquilerKeyPressed

    private void btnBuscarAgricultor_AlquilerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarAgricultor_AlquilerActionPerformed
        try {
            getcombo_cliente_all();
            jdAlquilerAgricultor.pack();
            jdAlquilerAgricultor.setLocationRelativeTo(null);
            jdAlquilerAgricultor.getRootPane().registerKeyboardAction(new CloseDialogEscape(jdAlquilerAgricultor),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
            jdAlquilerAgricultor.setModal(true);
            jdAlquilerAgricultor.setVisible(true);
        } catch (Exception e) {
            System.out.println("Error al cargar Alquiler" + e.toString());
        }
    }//GEN-LAST:event_btnBuscarAgricultor_AlquilerActionPerformed

    private void btn_buscar_alquileresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscar_alquileresActionPerformed
        try {
            int contador = 0;
            ArrayList<String> lista = new ArrayList();
            String condicionFinal = "";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            boolean fecha = chkFiltroFecha_Alquiler.isSelected();
            boolean cliente = chkFiltroAgricultor_Alquiler.isSelected();

            if (fecha == true) {
                lista.add(" ( date(dat_fechaRegistro) between '" + sdf.format(txtFechaInicio_Alquiler.getDate()) + "' and '" + sdf.format(txtFechaFin_Alquiler.getDate()) + "' ) ");
                contador++;
            }
            if (cliente == true) {
                lista.add(" ( int_id =" + ((Agricultor) cboAgricultor_Alquiler.getSelectedItem()).getInt_id() + " )");
                contador++;
            }
            switch (contador) {
                case 1:
                    condicionFinal = lista.get(0);
                    break;
                case 2:
                    condicionFinal = lista.get(0) + " and " + lista.get(1);
                    break;
            }
            DefaultTableModel tempConstancia = (DefaultTableModel) jtLista_Alquileres.getModel();
            tempConstancia.setRowCount(0);
            for (ListaAlquiler l : new BLAlquiler().get_alquiler_byclientefecha(condicionFinal)) {
                Object datos[] = {l.getVar_numero(), l.getVar_nombre_cliente() + ' ' + l.getVar_apepaterno() + ' ' + l.getVar_apematerno(),
                    l.getVar_nombre_material(), l.getDat_fechinicio(), l.getDat_fechfin(), l.getInt_cantidad(), l.getDec_monto()};
                tempConstancia.addRow(datos);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_buscar_alquileresActionPerformed

    private void btn_Cancelar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Cancelar1ActionPerformed
        limpiarFomulario_Alquiler();
    }//GEN-LAST:event_btn_Cancelar1ActionPerformed

    private void btn_registrar_alquilerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_registrar_alquilerActionPerformed
        try {
            if (txtAgricultor_Alquiler.getText().compareTo("") != 0) {
                modalvalidacion_alquiler();
            } else {
                JOptionPane.showMessageDialog(null, "No se Admiten Campos Vacios", "ALERTA", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            System.out.println("Error de Ingreso" + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_registrar_alquilerActionPerformed

    private void jmip_PagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmip_PagarActionPerformed

    }//GEN-LAST:event_jmip_PagarActionPerformed

    private void jmip_AnularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmip_AnularActionPerformed

    }//GEN-LAST:event_jmip_AnularActionPerformed

    private void btnGuardar5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar5ActionPerformed
        try {
            if (txtCodigo_Cuenta.getText().compareTo("") != 0 && txtNombre_Cuentas.getText().compareTo("") != 0
                    && txtNumCuenta_Registrar.getText().compareTo("") != 0) {
                if (new BLCuenta().Registrar(txtCodigo_Cuenta.getText(), txtNombre_Cuentas.getText(), txtNumCuenta_Registrar.getText())) {
                    gettabla_cuenta_all("", 0);
                    JOptionPane.showMessageDialog(null, "Registro Exitoso", "MENSAJE", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Error al Registrar", "MENSAJE", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "No se admite campos vacios", "ALERTA", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            System.out.println("Error al registrar Cuenta" + e.toString());
        }
    }//GEN-LAST:event_btnGuardar5ActionPerformed

    private void txtFiltroNombre_Cuenta2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFiltroNombre_Cuenta2KeyReleased
        gettabla_cuenta_all(txtFiltroNombre_Cuenta2.getText(), cboFiltro_Cuenta.getSelectedIndex());
    }//GEN-LAST:event_txtFiltroNombre_Cuenta2KeyReleased

    private void btnGuardar6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar6ActionPerformed
        try {
            int id_cuenta = 0;
            if (txtMonto_AsignarCuenta.getText().compareTo("") != 0 && txtConcepto_AsignarCosto.getText().compareTo("") != 0) {
                BLCuenta cu = new BLCuenta();
                id_cuenta = ((Cuenta) cboCuentas_AsignarCostos.getSelectedItem()).getInt_id();
                if (cu.AsignarCosto(id_cuenta, Double.parseDouble(txtMonto_AsignarCuenta.getText()),
                        txtConcepto_AsignarCosto.getText().trim())) {
                    limpiarTabla(jtAsignarCosto_Cuentas);
                    gettabla_asignacioncosto_cuenta_all();
                    JOptionPane.showMessageDialog(null, "Registro Exitoso", "MENSAJE", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Error al Registrar", "MENSAJE", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "No se admite campos vacios", "ALERTA", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            System.out.println("Error al registrar Cuenta" + e.toString());
        }
    }//GEN-LAST:event_btnGuardar6ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        try {
            if (txtSinMedida_Agricultor.getText().compareTo("") != 0 && txtConMedida_Agricultor.getText().compareTo("") != 0
                    && txtNumHectareas_Agricultor.getText().compareTo("") != 0
                    && (cboLateral_Agricultor.getSelectedItem().toString()).compareTo("") != 0 && (cboSubLateral_Agricultor.getSelectedItem().toString()).compareTo("") != 0) {
                DefaultTableModel temporal = (DefaultTableModel) jtDetalleLaterales_Agricultor.getModel();
                Object datos[] = {
                    ((Lateral) cboLateral_Agricultor.getSelectedItem()).getInt_id(),
                    ((SubLateral) cboSubLateral_Agricultor.getSelectedItem()).getInt_id(),
                    cboLateral_Agricultor.getSelectedItem().toString(),
                    cboSubLateral_Agricultor.getSelectedItem().toString(),
                    txtSinMedida_Agricultor.getText(),
                    txtConMedida_Agricultor.getText(),
                    txtNumHectareas_Agricultor.getText()
                };
                temporal.addRow(datos);
                txtSinMedida_Agricultor.setText("0.0");
                txtConMedida_Agricultor.setText("0.0");
                btnEliminar_DetLateales.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(null, "No se Admiten Campos Vacios", "ALERTA", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }
    }//GEN-LAST:event_jButton17ActionPerformed

    private void btnEliminar_DetLatealesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminar_DetLatealesActionPerformed
        int fila = jtDetalleLaterales_Agricultor.getSelectedRow();
        DefaultTableModel temporal = (DefaultTableModel) jtDetalleLaterales_Agricultor.getModel();
        if (fila > 0) {
            temporal.removeRow(fila);
        } else if (fila == 0) {
            temporal.removeRow(fila);
        }
        btnEliminar_DetLateales.setEnabled(true);
    }//GEN-LAST:event_btnEliminar_DetLatealesActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try {
            if (txtDNI_Agricultor.getText().compareTo("") != 0
                    && txtNombres_Agricultor.getText().compareTo("") != 0
                    && txtApePaterno_Agricultor.getText().compareTo("") != 0
                    && txtApeMaterno_Agricultor.getText().compareTo("") != 0) {
                String sexo = cboSexo_Agricultor.getSelectedItem().toString().equalsIgnoreCase("FEMENINO") ? "F" : "M";

                ArrayList<ListaLateral> lista_laterales = new ArrayList<ListaLateral>();
                int nroFilas = ((DefaultTableModel) jtDetalleLaterales_Agricultor.getModel()).getRowCount();
                for (int f = 0; f < nroFilas; f++) {
                    ListaLateral l = new ListaLateral();
                    //l.setInt_id(Integer.parseInt(jtDetalleLaterales_Agricultor.getModel().getValueAt(f, 0).toString()));
                    l.setIdlateral(Integer.parseInt(jtDetalleLaterales_Agricultor.getModel().getValueAt(f, 0).toString()));
                    l.setIdsublateral(Integer.parseInt(jtDetalleLaterales_Agricultor.getModel().getValueAt(f, 1).toString()));
                    l.setDec_sinmedida(Double.parseDouble(jtDetalleLaterales_Agricultor.getModel().getValueAt(f, 4).toString()));
                    l.setDec_conmedida(Double.parseDouble(jtDetalleLaterales_Agricultor.getModel().getValueAt(f, 5).toString()));
                    l.setInt_numhectareas(Integer.parseInt(jtDetalleLaterales_Agricultor.getModel().getValueAt(f, 6).toString()));
                    lista_laterales.add(l);
                }
                //REGISTRAR AGRICULTOR                
                if (new BLAgricultor().RegistrarAgricultor(idAgricultor_Edit, txtNombres_Agricultor.getText(),
                        txtApeMaterno_Agricultor.getText(), txtApePaterno_Agricultor.getText(),
                        txtDireccion_Agricultor.getText(), txtEmail_Agricultor.getText(), txtDNI_Agricultor.getText(),
                        sexo, txtTelefono_Agricultor.getText(),
                        txtCelular_Agricultor.getText(), lista_laterales)) {

                    JOptionPane.showMessageDialog(null, "Registro Exitoso", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                    limpiarTabla(jtAgricultor);
                    limpiarTabla(jtDetalleLaterales_Agricultor);
                    limpiarformulario_agricultor();
                    gettabla_agricultor_all("", 1);
                    idAgricultor_Edit = 0;
                } else {
                    limpiarformulario_agricultor();
                    JOptionPane.showMessageDialog(null, "Registro Fallido", "Mensaje", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, " No se admiten campos vacios ", "Mensaje", 1);
            }
        } catch (Exception e) {
            System.out.println("Error al registrar Orden Compra" + e.toString());
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btn_guardar_periodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardar_periodoActionPerformed
        try {
            if (txtNombre_Periodo.getText().compareTo("") != 0) {
                boolean resultado = false;
                resultado = new BLPeriodo().Registrar(txtNombre_Periodo.getText(), ((Constante) cboPeriodo_MesInicio.getSelectedItem()).getInt_valor(),
                        ((Constante) cboPeriodo_MesFin.getSelectedItem()).getInt_valor());
                if (resultado == true) {
                    limpiarTabla(jtPeriodo_All);
                    gettabla_periodo_all("", 0);
                    JOptionPane.showMessageDialog(null, "Se Registro Correctamente");
                } else {
                    JOptionPane.showMessageDialog(null, "No se Pudo Registrar", "Alerta", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(null, "No se Admiten Campos Vacios", "Alerta", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_guardar_periodoActionPerformed

    private void jtAgricultorMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtAgricultorMousePressed
        if (evt.isPopupTrigger() && jtAgricultor.getModel().getRowCount() != 0
                && jtAgricultor.getSelectedRow() != -1) {
            jpmAgricultor.show(jtAgricultor, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_jtAgricultorMousePressed

    private void jtAgricultorMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtAgricultorMouseReleased
        if (evt.isPopupTrigger() && jtAgricultor.getModel().getRowCount() != 0
                && jtAgricultor.getSelectedRow() != -1) {
            jpmAgricultor.show(jtAgricultor, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_jtAgricultorMouseReleased

    private void jmiEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiEditarActionPerformed
        try {
            idAgricultor_Edit = Integer.parseInt(jtAgricultor.getValueAt(jtAgricultor.getSelectedRow(), 0).toString());
            DefaultTableModel temp = (DefaultTableModel) jtDetalleLaterales_Agricultor.getModel();
            temp.setRowCount(0);
            for (ListaAgricultorLateral lista : new BDAgricultor().get_agricultorlateral_byid(idAgricultor_Edit)) {
                Object[] datos = {lista.getInt_idlateral(), lista.getVar_lateral(), lista.getVar_sublateral(),
                    lista.getDec_sinmedida(), lista.getDec_conmedida()};
                temp.addRow(datos);
                txtDNI_Agricultor.setText(lista.getVar_dni());
                txtNombres_Agricultor.setText(lista.getVar_nombre());
                txtApePaterno_Agricultor.setText(lista.getVar_apepaterno());
                txtApeMaterno_Agricultor.setText(lista.getVar_apematerno());
                txtDireccion_Agricultor.setText(lista.getVar_direccion());
                txtTelefono_Agricultor.setText(lista.getVar_telefono());
                txtCelular_Agricultor.setText(lista.getVar_celular());
                txtEmail_Agricultor.setText(lista.getVar_email());
            }
        } catch (Exception e) {
            System.out.println("Error de Listado Editar -vISTA" + e.getMessage());
        }
    }//GEN-LAST:event_jmiEditarActionPerformed

    private void txtFiltroAgricultorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFiltroAgricultorKeyReleased
        gettabla_agricultor_all(txtFiltroAgricultor.getText(), cboFiltroAgricultor.getSelectedIndex());
    }//GEN-LAST:event_txtFiltroAgricultorKeyReleased

    private void btn_Guardar_UsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Guardar_UsuarioActionPerformed
        try {
            if (txtID_Usuario.getText().compareTo("") != 0 && txtpass_usuario.getPassword().toString().compareTo("") != 0
                    && txtdni_usuario.getText().compareTo("") != 0 && txtnombres_usuario.getText().compareTo("") != 0
                    && txtapellidos_usuario.getText().compareTo("") != 0 && txtTeleCelular_Usuario.getText().compareTo("") != 0
                    && txtFechaNacimiento_Usuario.getDate() != null) {
                char passArray[] = txtpass_usuario.getPassword();
                String pass = new String(passArray);
                if (new BLUsuario().Registrar(txtID_Usuario.getText(),
                        pass,
                        txtdni_usuario.getText(),
                        txtnombres_usuario.getText(),
                        txtapellidos_usuario.getText(),
                        new java.sql.Date(txtFechaNacimiento_Usuario.getDate().getTime()),
                        txtTeleCelular_Usuario.getText(),
                        ((Cargo) cboCargo_Usuario.getSelectedItem()).getInt_id(),
                        txtDireccion_Usuario.getText(),
                        txtEmail_Usuario.getText())) {
                    limpiarFomulario_Usuario();
                    gettabla_usuario_byfiltro("", 0);
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
    }//GEN-LAST:event_btn_Guardar_UsuarioActionPerformed

    private void btnGuardar_ComiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar_ComiteActionPerformed
        try {
            if (txtComite_Registrar.getText().compareTo("") != 0) {
                if (new BLComite().Registrar(txtComite_Registrar.getText())) {
                    limpiarTabla(jtComite_Administracion);
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
    }//GEN-LAST:event_btnGuardar_ComiteActionPerformed

    private void btnAgregarDet_AlquilerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarDet_AlquilerActionPerformed
        try {
            if (txtMonto_Alquiler.getText().compareTo("") != 0 && txtFechaDesde_Alquiler.getDate() != null
                    && txtFechaHasta_Alquiler.getDate() != null) {
                DefaultTableModel temporal = (DefaultTableModel) jtbDetalle_Alquiler.getModel();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                Object datos[] = {
                    ((Material) cboTipoMaterial_Alquiler.getSelectedItem()).getInt_id(),
                    ((Material) cboTipoMaterial_Alquiler.getSelectedItem()).getVar_nombre(),
                    txtCantidad_Alquiler.getValue(),
                    sdf.format(txtFechaDesde_Alquiler.getDate()),
                    sdf.format(txtFechaHasta_Alquiler.getDate()),
                    txtHoras_Alquiler.getValue(),
                    txtMonto_Alquiler.getText()
                };
                temporal.addRow(datos);
                txtMonto_Alquiler.setText("");
                btnEliminarDet_Alquiler.setEnabled(true);
            }
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }
    }//GEN-LAST:event_btnAgregarDet_AlquilerActionPerformed

    private void btn_Cancelar_UsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Cancelar_UsuarioActionPerformed
        limpiarFomulario_Usuario();
    }//GEN-LAST:event_btn_Cancelar_UsuarioActionPerformed

    private void btnEliminarDet_AlquilerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarDet_AlquilerActionPerformed
        int fila = jtbDetalle_Alquiler.getSelectedRow();
        DefaultTableModel temporal = (DefaultTableModel) jtbDetalle_Alquiler.getModel();
        if (fila > 0) {
            temporal.removeRow(fila);
        } else if (fila == 0) {
            temporal.removeRow(fila);
        }
        btnEliminar_DetLateales.setEnabled(true);
    }//GEN-LAST:event_btnEliminarDet_AlquilerActionPerformed

    private void chkFiltroFecha_AlquilerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkFiltroFecha_AlquilerActionPerformed
        if (chkFiltroFecha_Alquiler.isSelected()) {
            txtFechaInicio_Alquiler.setEnabled(true);
            txtFechaFin_Alquiler.setEnabled(true);
        } else {
            txtFechaInicio_Alquiler.setEnabled(false);
            txtFechaFin_Alquiler.setEnabled(false);
        }
    }//GEN-LAST:event_chkFiltroFecha_AlquilerActionPerformed

    private void chkFiltroAgricultor_AlquilerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkFiltroAgricultor_AlquilerActionPerformed
        if (chkFiltroAgricultor_Alquiler.isSelected()) {
            cboAgricultor_Alquiler.setEnabled(true);
        } else {
            cboAgricultor_Alquiler.setEnabled(false);
        }
    }//GEN-LAST:event_chkFiltroAgricultor_AlquilerActionPerformed

    private void txtFiltro_UsuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFiltro_UsuarioKeyReleased
        gettabla_usuario_byfiltro(txtFiltro_Usuario.getText(), cboTipoFiltro_Usuario.getSelectedIndex());
    }//GEN-LAST:event_txtFiltro_UsuarioKeyReleased

    private void txtFiltroComite_AdministracionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFiltroComite_AdministracionKeyReleased
        gettabla_comite_byActivos(txtFiltroComite_Administracion.getText());
    }//GEN-LAST:event_txtFiltroComite_AdministracionKeyReleased

    private void jmiPagoMultaAsambleaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiPagoMultaAsambleaActionPerformed
        iniciarFomrulario_PadronMultaAsamblea(new jifMultaAsamblea_C());
    }//GEN-LAST:event_jmiPagoMultaAsambleaActionPerformed

    private void txtNombres_AgricultorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombres_AgricultorKeyTyped
        new Funciones().soloLetras(evt);
        if (txtNombres_Agricultor.getText().length() == 30) {
            evt.consume();
        }
        new Funciones().convertir_mayuscula(evt);
    }//GEN-LAST:event_txtNombres_AgricultorKeyTyped

    private void txtApePaterno_AgricultorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApePaterno_AgricultorKeyTyped
        new Funciones().soloLetras(evt);
        if (txtApePaterno_Agricultor.getText().length() == 30) {
            evt.consume();
        }
        new Funciones().convertir_mayuscula(evt);
    }//GEN-LAST:event_txtApePaterno_AgricultorKeyTyped

    private void txtApeMaterno_AgricultorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApeMaterno_AgricultorKeyTyped
        new Funciones().soloLetras(evt);
        if (txtApeMaterno_Agricultor.getText().length() == 30) {
            evt.consume();
        }
        new Funciones().convertir_mayuscula(evt);
    }//GEN-LAST:event_txtApeMaterno_AgricultorKeyTyped

    private void txtSinMedida_AgricultorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSinMedida_AgricultorKeyTyped
        new Funciones().soloDecimales(evt);
        if (txtSinMedida_Agricultor.getText().length() == 8) {
            evt.consume();
        }
    }//GEN-LAST:event_txtSinMedida_AgricultorKeyTyped

    private void txtConMedida_AgricultorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConMedida_AgricultorKeyTyped
        new Funciones().soloDecimales(evt);
        if (txtConMedida_Agricultor.getText().length() == 8) {
            evt.consume();
        }
    }//GEN-LAST:event_txtConMedida_AgricultorKeyTyped

    private void txtNumHectareas_AgricultorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumHectareas_AgricultorKeyTyped
        new Funciones().soloDecimales(evt);
        if (txtNumHectareas_Agricultor.getText().length() == 8) {
            evt.consume();
        }
    }//GEN-LAST:event_txtNumHectareas_AgricultorKeyTyped

    private void txtMonto_AsignarCuentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMonto_AsignarCuentaKeyTyped
        new Funciones().soloDecimales(evt);
        if (txtMonto_AsignarCuenta.getText().length() == 16) {
            evt.consume();
        }
    }//GEN-LAST:event_txtMonto_AsignarCuentaKeyTyped

    private void txtdni_usuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdni_usuarioKeyTyped
        new Funciones().soloNumeros(evt);
        if (txtdni_usuario.getText().length() == 8) {
            evt.consume();
        }
    }//GEN-LAST:event_txtdni_usuarioKeyTyped

    private void txtnombres_usuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombres_usuarioKeyTyped
        new Funciones().soloLetras(evt);
        if (txtnombres_usuario.getText().length() == 80) {
            evt.consume();
        }
        new Funciones().convertir_mayuscula(evt);
    }//GEN-LAST:event_txtnombres_usuarioKeyTyped

    private void txtapellidos_usuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtapellidos_usuarioKeyTyped
        new Funciones().soloLetras(evt);
        if (txtapellidos_usuario.getText().length() == 80) {
            evt.consume();
        }
        new Funciones().convertir_mayuscula(evt);
    }//GEN-LAST:event_txtapellidos_usuarioKeyTyped

    private void txtID_UsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtID_UsuarioKeyTyped
        if (txtID_Usuario.getText().length() == 20) {
            evt.consume();
        }
    }//GEN-LAST:event_txtID_UsuarioKeyTyped

    private void txtpass_usuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpass_usuarioKeyTyped
        if (txtpass_usuario.getText().length() == 30) {
            evt.consume();
        }
    }//GEN-LAST:event_txtpass_usuarioKeyTyped

    private void txtTeleCelular_UsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTeleCelular_UsuarioKeyTyped
        if (txtTeleCelular_Usuario.getText().length() == 15) {
            evt.consume();
        }
    }//GEN-LAST:event_txtTeleCelular_UsuarioKeyTyped

    private void txtDireccion_UsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccion_UsuarioKeyTyped
        if (txtDireccion_Usuario.getText().length() == 250) {
            evt.consume();
        }
        new Funciones().convertir_mayuscula(evt);
    }//GEN-LAST:event_txtDireccion_UsuarioKeyTyped

    private void txtEmail_UsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmail_UsuarioKeyTyped
        if (txtEmail_Usuario.getText().length() == 250) {
            evt.consume();
        }
    }//GEN-LAST:event_txtEmail_UsuarioKeyTyped

    private void txtDNI_AgricultorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDNI_AgricultorKeyTyped
        if (txtDNI_Agricultor.getText().length() == 20) {
            evt.consume();
        }
    }//GEN-LAST:event_txtDNI_AgricultorKeyTyped

    private void txtTelefono_AgricultorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefono_AgricultorKeyTyped
        if (txtTelefono_Agricultor.getText().length() == 20) {
            evt.consume();
        }
    }//GEN-LAST:event_txtTelefono_AgricultorKeyTyped

    private void txtCelular_AgricultorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCelular_AgricultorKeyTyped
        if (txtCelular_Agricultor.getText().length() == 20) {
            evt.consume();
        }
    }//GEN-LAST:event_txtCelular_AgricultorKeyTyped

    private void txtDireccion_AgricultorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccion_AgricultorKeyTyped
        if (txtDireccion_Agricultor.getText().length() == 100) {
            evt.consume();
        }
        new Funciones().convertir_mayuscula(evt);
    }//GEN-LAST:event_txtDireccion_AgricultorKeyTyped

    private void txtEmail_AgricultorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmail_AgricultorKeyTyped
        if (txtEmail_Agricultor.getText().length() == 35) {
            evt.consume();
        }
        new Funciones().convertir_mayuscula(evt);
    }//GEN-LAST:event_txtEmail_AgricultorKeyTyped

    private void txtCodigo_CuentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigo_CuentaKeyTyped
        if (txtCodigo_Cuenta.getText().length() == 10) {
            evt.consume();
        }
        new Funciones().convertir_mayuscula(evt);
    }//GEN-LAST:event_txtCodigo_CuentaKeyTyped

    private void txtNumCuenta_RegistrarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumCuenta_RegistrarKeyTyped
        if (txtNumCuenta_Registrar.getText().length() == 45) {
            evt.consume();
        }
    }//GEN-LAST:event_txtNumCuenta_RegistrarKeyTyped

    private void txtNombre_CuentasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombre_CuentasKeyTyped
        if (txtNombre_Cuentas.getText().length() == 45) {
            evt.consume();
        }
        new Funciones().convertir_mayuscula(evt);
    }//GEN-LAST:event_txtNombre_CuentasKeyTyped

    private void txtConcepto_AsignarCostoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConcepto_AsignarCostoKeyTyped
        if (txtConcepto_AsignarCosto.getText().length() == 45) {
            evt.consume();
        }
        new Funciones().convertir_mayuscula(evt);
    }//GEN-LAST:event_txtConcepto_AsignarCostoKeyTyped

    private void txtComite_RegistrarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtComite_RegistrarKeyTyped
        if (txtComite_Registrar.getText().length() == 45) {
            evt.consume();
        }
        new Funciones().convertir_mayuscula(evt);
    }//GEN-LAST:event_txtComite_RegistrarKeyTyped

    private void jmiPagoMultaSufragioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiPagoMultaSufragioActionPerformed
        iniciarFomrulario_PadronMultaSufragio(new jifMultaSufragio_C());
    }//GEN-LAST:event_jmiPagoMultaSufragioActionPerformed

    private void txtFiltro_PeriodoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFiltro_PeriodoKeyReleased
        gettabla_periodo_all(txtFiltro_Periodo.getText(), cboFiltro_Periodo.getSelectedIndex());
    }//GEN-LAST:event_txtFiltro_PeriodoKeyReleased

    private void btn_validar_alquilerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_validar_alquilerActionPerformed
        try {
            Usuario u = null;
            String pass = new String(txtValidacionPass_Alquiler.getPassword());
            u = new BLUsuario().get_usuario_bypassword(pass);
            if (u.getVar_user() != null) {
                jdValidacion_Alquiler.dispose();
                RegistrarAlquiler();
                txtValidacionPass_Alquiler.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "[Clave Incorrecta]", "Alerta", JOptionPane.ERROR_MESSAGE);
                txtValidacionPass_Alquiler.requestFocus();
                txtValidacionPass_Alquiler.setText("");
            }

        } catch (Exception e) {
            System.out.println("Error de Validacion ");
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_validar_alquilerActionPerformed

    private void txtFiltroAgricultorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFiltroAgricultorKeyTyped
        new Funciones().convertir_mayuscula(evt);
    }//GEN-LAST:event_txtFiltroAgricultorKeyTyped

    private void txtNombre_PeriodoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombre_PeriodoKeyTyped
        new Funciones().convertir_mayuscula(evt);
    }//GEN-LAST:event_txtNombre_PeriodoKeyTyped

    private void txtFiltro_PeriodoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFiltro_PeriodoKeyTyped
        new Funciones().convertir_mayuscula(evt);
    }//GEN-LAST:event_txtFiltro_PeriodoKeyTyped

    private void txtModalAgricultor_AlquilerKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtModalAgricultor_AlquilerKeyTyped
        new Funciones().convertir_mayuscula(evt);
    }//GEN-LAST:event_txtModalAgricultor_AlquilerKeyTyped

    private void txtNombre_CuentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombre_CuentasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombre_CuentasActionPerformed

    private void txtFiltroNombre_Cuenta2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFiltroNombre_Cuenta2KeyTyped
        new Funciones().convertir_mayuscula(evt);
    }//GEN-LAST:event_txtFiltroNombre_Cuenta2KeyTyped

    private void txtFiltro_UsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFiltro_UsuarioKeyTyped
        new Funciones().convertir_mayuscula(evt);
    }//GEN-LAST:event_txtFiltro_UsuarioKeyTyped

    private void txtFiltroComite_AdministracionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFiltroComite_AdministracionKeyTyped
        new Funciones().convertir_mayuscula(evt);
    }//GEN-LAST:event_txtFiltroComite_AdministracionKeyTyped

    private void jmiLateral_SubLateralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiLateral_SubLateralActionPerformed
        gettable_getlateral_all("");
        get_sublatreles_all("");
        iniciarFomrulario_lateralsublateral(jifLateral_SubLateral_Adm);
    }//GEN-LAST:event_jmiLateral_SubLateralActionPerformed

    private void btn_guardar_lateralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardar_lateralActionPerformed
        try {
            if (txtNombre_Lateral.getText().compareTo("") != 0) {
                boolean resultado = false;
                BLLateral l = new BLLateral();
                resultado = l.Registrar(txtNombre_Lateral.getText());
                if (resultado) {
                    txtNombre_Lateral.setText("");
                    gettable_getlateral_all("");
                    JOptionPane.showMessageDialog(null, "Se Registro Correctamente", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "No se Pudo Registrar", "Altera", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "No se Admiten Campos Vacios", "Altera", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            System.out.println("Error de Ingreso Vista" + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_guardar_lateralActionPerformed

    private void txtNombre_LateralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombre_LateralActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombre_LateralActionPerformed

    private void txtNombre_LateralKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombre_LateralKeyTyped
        if (txtNombre_Lateral.getText().length() == 45) {
            evt.consume();
        }
        new Funciones().convertir_mayuscula(evt);
    }//GEN-LAST:event_txtNombre_LateralKeyTyped

    private void txtFiltroNombre_LateralKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFiltroNombre_LateralKeyReleased
        gettable_getlateral_all(txtFiltroNombre_Lateral.getText());
    }//GEN-LAST:event_txtFiltroNombre_LateralKeyReleased

    private void txtFiltroNombre_LateralKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFiltroNombre_LateralKeyTyped
        if (txtFiltroNombre_Lateral.getText().length() == 45) {
            evt.consume();
        }
        new Funciones().convertir_mayuscula(evt);
    }//GEN-LAST:event_txtFiltroNombre_LateralKeyTyped

    private void jtLateral_AdmMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtLateral_AdmMouseClicked

    }//GEN-LAST:event_jtLateral_AdmMouseClicked

    private void jtLateral_AdmMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtLateral_AdmMousePressed
        if (evt.isPopupTrigger() && jtLateral_Adm.getModel().getRowCount() != 0
                && jtLateral_Adm.getSelectedRow() != -1) {
            jpmLateral.show(jtLateral_Adm, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_jtLateral_AdmMousePressed

    private void jtLateral_AdmMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtLateral_AdmMouseReleased
        if (evt.isPopupTrigger() && jtLateral_Adm.getModel().getRowCount() != 0
                && jtLateral_Adm.getSelectedRow() != -1) {
            jpmLateral.show(jtLateral_Adm, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_jtLateral_AdmMouseReleased

    private void jimQuitarLateralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jimQuitarLateralActionPerformed
        try {
            int fila = jtLateral_Adm.getSelectedRow();
            if (fila != -1) {
                BLLateral l = new BLLateral();
                boolean resultado = false;
                resultado = l.QuitarLateral(Integer.parseInt(jtLateral_Adm.getValueAt(fila, 0).toString()));
                if (resultado) {
                    gettable_getlateral_all("");
                    JOptionPane.showMessageDialog(null, "Lateral Inactivo", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "No se Pudo Desactivar el Lateral", "Mensaje", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Debes Seleccionar Una Fila", "Mensaje", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            System.out.println("Error de Quitar Lateral " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_jimQuitarLateralActionPerformed

    private void txtNombre_SubLateralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombre_SubLateralActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombre_SubLateralActionPerformed

    private void txtNombre_SubLateralKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombre_SubLateralKeyTyped
        if (txtNombre_SubLateral.getText().length() == 45) {
            evt.consume();
        }
        new Funciones().convertir_mayuscula(evt);
    }//GEN-LAST:event_txtNombre_SubLateralKeyTyped

    private void btn_guardar_sublateralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardar_sublateralActionPerformed
        try {
            if (txtNombre_SubLateral.getText().compareTo("") != 0) {
                BLLateral l = new BLLateral();
                boolean resultado = false;
                resultado = l.Registrar_SubLateral(txtNombre_SubLateral.getText());
                if (resultado) {
                    txtNombre_SubLateral.setText("");
                    get_sublatreles_all("");
                    JOptionPane.showMessageDialog(null, "Se Registro Correctamente", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "No se Admiten Campos Vacios", "Alerta", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            System.out.println("Error de Ingreso SubLateral" + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_guardar_sublateralActionPerformed

    private void txtFiltroNombre_SubLateralKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFiltroNombre_SubLateralKeyReleased
        get_sublatreles_all(txtFiltroNombre_SubLateral.getText());
    }//GEN-LAST:event_txtFiltroNombre_SubLateralKeyReleased

    private void txtFiltroNombre_SubLateralKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFiltroNombre_SubLateralKeyTyped
        if (txtFiltroNombre_SubLateral.getText().length() == 45) {
            evt.consume();
        }
        new Funciones().convertir_mayuscula(evt);
    }//GEN-LAST:event_txtFiltroNombre_SubLateralKeyTyped

    private void jtSubLateral_AdmMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtSubLateral_AdmMousePressed
        if (evt.isPopupTrigger() && jtSubLateral_Adm.getModel().getRowCount() != 0
                && jtSubLateral_Adm.getSelectedRow() != -1) {
            jpmSubLateral.show(jtSubLateral_Adm, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_jtSubLateral_AdmMousePressed

    private void jtSubLateral_AdmMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtSubLateral_AdmMouseReleased
        if (evt.isPopupTrigger() && jtSubLateral_Adm.getModel().getRowCount() != 0
                && jtSubLateral_Adm.getSelectedRow() != -1) {
            jpmSubLateral.show(jtSubLateral_Adm, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_jtSubLateral_AdmMouseReleased

    private void jmiSubLateralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiSubLateralActionPerformed
        try {
            int fila = jtSubLateral_Adm.getSelectedRow();
            if (fila != -1) {
                BLLateral l = new BLLateral();
                boolean resultado = false;
                resultado = l.QuitarSubLateral(Integer.parseInt(jtSubLateral_Adm.getValueAt(fila, 0).toString()));
                if (resultado) {
                    get_sublatreles_all("");
                    JOptionPane.showMessageDialog(null, "Sub Lateral Inactivo", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "No se Pudo Desactivar el Sub-Lateral", "Mensaje", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Debes Seleccionar Una Fila", "Mensaje", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            System.out.println("Error de Quitar Sub-Lateral " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_jmiSubLateralActionPerformed

    private void jmiMaterialesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiMaterialesActionPerformed
        iniciarFomrulario_materiales_Adm(new jifMateriales_C());
    }//GEN-LAST:event_jmiMaterialesActionPerformed

    private void txtFiltroNombre_NoClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFiltroNombre_NoClienteKeyReleased
        gettable_getnocliente_all(txtFiltroNombre_NoCliente.getText());
    }//GEN-LAST:event_txtFiltroNombre_NoClienteKeyReleased

    private void txtFiltroNombre_NoClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFiltroNombre_NoClienteKeyTyped
        new Funciones().soloLetras(evt);
        if (txtFiltroNombre_NoCliente.getText().length() == 45) {
            evt.consume();
        }
        new Funciones().convertir_mayuscula(evt);
    }//GEN-LAST:event_txtFiltroNombre_NoClienteKeyTyped

    private void jtNoCliente_AdmMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtNoCliente_AdmMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jtNoCliente_AdmMouseReleased

    private void jtNoCliente_AdmMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtNoCliente_AdmMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtNoCliente_AdmMousePressed

    private void jtNoCliente_AdmMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtNoCliente_AdmMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jtNoCliente_AdmMouseClicked

    private void txtNombres_NoUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombres_NoUsuarioKeyTyped
        new Funciones().soloLetras(evt);
        if (txtNombres_NoUsuario.getText().length() == 45) {
            evt.consume();
        }
        new Funciones().convertir_mayuscula(evt);
    }//GEN-LAST:event_txtNombres_NoUsuarioKeyTyped

    private void txtApePaterno_NoUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApePaterno_NoUsuarioKeyTyped
        new Funciones().soloLetras(evt);
        if (txtApePaterno_NoUsuario.getText().length() == 45) {
            evt.consume();
        }
        new Funciones().convertir_mayuscula(evt);
    }//GEN-LAST:event_txtApePaterno_NoUsuarioKeyTyped

    private void txtApeMaterno_NoUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApeMaterno_NoUsuarioKeyTyped
        new Funciones().soloLetras(evt);
        if (txtApeMaterno_NoUsuario.getText().length() == 45) {
            evt.consume();
        }
        new Funciones().convertir_mayuscula(evt);
    }//GEN-LAST:event_txtApeMaterno_NoUsuarioKeyTyped

    private void txtRazonSocial_NoUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRazonSocial_NoUsuarioKeyTyped
        if (txtRazonSocial_NoUsuario.getText().length() == 45) {
            evt.consume();
        }
        new Funciones().convertir_mayuscula(evt);
    }//GEN-LAST:event_txtRazonSocial_NoUsuarioKeyTyped

    private void txtDireccion_NoUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccion_NoUsuarioKeyTyped
        if (txtDireccion_NoUsuario.getText().length() == 100) {
            evt.consume();
        }
        new Funciones().convertir_mayuscula(evt);
    }//GEN-LAST:event_txtDireccion_NoUsuarioKeyTyped

    private void txtDNI_NoUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDNI_NoUsuarioKeyTyped
        new Funciones().soloNumeros(evt);
        if (txtDNI_NoUsuario.getText().length() == 8) {
            evt.consume();
        }
        new Funciones().convertir_mayuscula(evt);
    }//GEN-LAST:event_txtDNI_NoUsuarioKeyTyped

    private void txtTelefono_NoUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefono_NoUsuarioKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefono_NoUsuarioKeyTyped

    private void txtCelular_NoUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCelular_NoUsuarioKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCelular_NoUsuarioKeyTyped

    private void btn_guardar_nousuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardar_nousuarioActionPerformed
        try {
            boolean resultado = false;
            if (rbtnPersonaNatural.isSelected()) {
                if (txtNombres_NoUsuario.getText().compareTo("") != 0 && txtApeMaterno_NoUsuario.getText().compareTo("") != 0
                        && txtApePaterno_NoUsuario.getText().compareTo("") != 0 && txtDNI_NoUsuario.getText().compareTo("") != 0
                        && txtDireccion_NoUsuario.getText().compareTo("") != 0 && txtTelefono_NoUsuario.getText().compareTo("") != 0) {

                    BLNoCliente blnc = new BLNoCliente();
                    resultado = blnc.Registrar(txtNombres_NoUsuario.getText(), txtApeMaterno_NoUsuario.getText(),
                            txtApePaterno_NoUsuario.getText(), txtDireccion_NoUsuario.getText(), txtDNI_NoUsuario.getText(),
                            txtTelefono_NoUsuario.getText(), txtCelular_NoUsuario.getText(), txtRuc_NoUsuario.getText(),
                            txtRazonSocial_NoUsuario.getText());

                    if (resultado) {
                        txtNombres_NoUsuario.setText("");
                        txtApeMaterno_NoUsuario.setText("");
                        txtApePaterno_NoUsuario.setText("");
                        txtDireccion_NoUsuario.setText("");
                        txtDNI_NoUsuario.setText("");
                        txtTelefono_NoUsuario.setText("");
                        txtCelular_NoUsuario.setText("");
                        txtRuc_NoUsuario.setText("");
                        txtRazonSocial_NoUsuario.setText("");
                        gettable_getnocliente_all("");
                        JOptionPane.showMessageDialog(null, "Se Guardo Correctamente", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "No Se Pudo Registrar", "Alerta", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No Se Admiten Campos Vacios", "Alerta", JOptionPane.ERROR_MESSAGE);
                }
            }

            if (rbtnPersonaJuridica.isSelected()) {
                if (txtTelefono_NoUsuario.getText().compareTo("") != 0
                        && txtDireccion_NoUsuario.getText().compareTo("") != 0
                        && txtRazonSocial_NoUsuario.getText().compareTo("") != 0
                        && txtRuc_NoUsuario.getText().compareTo("") != 0) {
                    BLNoCliente blnc = new BLNoCliente();
                    resultado = blnc.Registrar("", "", "", txtDireccion_NoUsuario.getText(), "", txtTelefono_NoUsuario.getText(), txtCelular_NoUsuario.getText(),
                            txtRuc_NoUsuario.getText(), txtRazonSocial_NoUsuario.getText());

                    if (resultado) {
                        txtNombres_NoUsuario.setText("");
                        txtApeMaterno_NoUsuario.setText("");
                        txtApePaterno_NoUsuario.setText("");
                        txtDireccion_NoUsuario.setText("");
                        txtDNI_NoUsuario.setText("");
                        txtTelefono_NoUsuario.setText("");
                        txtCelular_NoUsuario.setText("");
                        txtRuc_NoUsuario.setText("");
                        txtRazonSocial_NoUsuario.setText("");
                        gettable_getnocliente_all("");
                        JOptionPane.showMessageDialog(null, "Se Guardo Correctamente", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "No Se Pudo Registrar", "Alerta", JOptionPane.ERROR_MESSAGE);
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "No Se Admiten Campos Vacios", "Alerta", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception e) {
            System.out.println("Aqui esta el error" + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_guardar_nousuarioActionPerformed

    private void txtRuc_NoUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRuc_NoUsuarioKeyTyped
        new Funciones().soloNumeros(evt);
        if (txtRuc_NoUsuario.getText().length() == 11) {
            evt.consume();
        }
        new Funciones().convertir_mayuscula(evt);
    }//GEN-LAST:event_txtRuc_NoUsuarioKeyTyped

    private void rbtnPersonaJuridicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnPersonaJuridicaActionPerformed
        if (rbtnPersonaJuridica.isSelected()) {
            txtDNI_NoUsuario.setEnabled(false);
            txtNombres_NoUsuario.setEnabled(false);
            txtApePaterno_NoUsuario.setEnabled(false);
            txtApeMaterno_NoUsuario.setEnabled(false);
            txtTelefono_NoUsuario.setEnabled(true);
            txtCelular_NoUsuario.setEnabled(true);
            txtDireccion_NoUsuario.setEnabled(true);
            txtRazonSocial_NoUsuario.setEnabled(true);
            txtRuc_NoUsuario.setEnabled(true);
            chkRuc.setSelected(true);
            chkRuc.setEnabled(false);
        }
    }//GEN-LAST:event_rbtnPersonaJuridicaActionPerformed

    private void jmiNoUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiNoUsuarioActionPerformed
        gettable_getnocliente_all("");
        iniciarFomrulario_nousuario_Adm(jifNoUsuario);
    }//GEN-LAST:event_jmiNoUsuarioActionPerformed

    private void rbtnPersonaNaturalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnPersonaNaturalActionPerformed
        if (rbtnPersonaNatural.isSelected()) {
            txtDNI_NoUsuario.setEnabled(true);
            txtNombres_NoUsuario.setEnabled(true);
            txtApePaterno_NoUsuario.setEnabled(true);
            txtApeMaterno_NoUsuario.setEnabled(true);
            txtTelefono_NoUsuario.setEnabled(true);
            txtCelular_NoUsuario.setEnabled(true);
            txtDireccion_NoUsuario.setEnabled(true);
            txtRazonSocial_NoUsuario.setEnabled(false);
            txtRuc_NoUsuario.setEnabled(false);
            chkRuc.setSelected(false);
            chkRuc.setEnabled(true);
        }
    }//GEN-LAST:event_rbtnPersonaNaturalActionPerformed

    private void chkRucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkRucActionPerformed
        if (chkRuc.isSelected()) {
            txtRuc_NoUsuario.setEnabled(true);
            txtRazonSocial_NoUsuario.setEnabled(true);
        } else {
            txtRuc_NoUsuario.setEnabled(false);
            txtRazonSocial_NoUsuario.setEnabled(false);
        }
    }//GEN-LAST:event_chkRucActionPerformed

    private void btn_buscar_usuario_alquilerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscar_usuario_alquilerActionPerformed
        if (rbtnUsuario_Alquiler.isSelected()) {
            DefaultTableModel temp = (DefaultTableModel) jtModalAgricultor_Alquiler.getModel();
            temp.setRowCount(0);
            for (Agricultor a : new BLAgricultor().get_agricultores_byActivos("")) {
                Object[] datos = {a.getInt_id(), a.getVar_nombre() + ' ' + a.getVar_apepaterno() + ' ' + a.getVar_apematerno()};
                temp.addRow(datos);
            }
        }
        if (rbtnNoUsuario_Alquiler.isSelected()) {
            DefaultTableModel temp = (DefaultTableModel) jtModalAgricultor_Alquiler.getModel();
            temp.setRowCount(0);
            for (NoCliente c : new BLNoCliente().get_nocliente_all("")) {
                Object[] datos = {c.getInt_id(), c.getVar_nombre() + ' ' + c.getVar_apepaterno() + ' ' + c.getVar_apematerno()};
                temp.addRow(datos);
            }
        }
    }//GEN-LAST:event_btn_buscar_usuario_alquilerActionPerformed

    private void rbtnUsuario_AlquilerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnUsuario_AlquilerActionPerformed
        txtModalAgricultor_Alquiler.setText("");
    }//GEN-LAST:event_rbtnUsuario_AlquilerActionPerformed

    private void rbtnNoUsuario_AlquilerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnNoUsuario_AlquilerActionPerformed
        txtModalAgricultor_Alquiler.setText("");
    }//GEN-LAST:event_rbtnNoUsuario_AlquilerActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpiarformulario_agricultor();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        limpiarformulario_periodo();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnCancelar5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelar5ActionPerformed
        limpiarFomulario_Cuenta();
    }//GEN-LAST:event_btnCancelar5ActionPerformed

    private void btnCancelar6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelar6ActionPerformed
        limpiarFomulario_AsignacionCosto_Cuenta();
    }//GEN-LAST:event_btnCancelar6ActionPerformed

    private void btn_cancelar_lateralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelar_lateralActionPerformed
        txtNombre_Lateral.setText("");
    }//GEN-LAST:event_btn_cancelar_lateralActionPerformed

    private void btn_cancelar_sublateralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelar_sublateralActionPerformed
        txtNombre_SubLateral.setText("");
    }//GEN-LAST:event_btn_cancelar_sublateralActionPerformed

    private void btn_cancelar_nousuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelar_nousuarioActionPerformed
        limpiarformulario_nocliente();
    }//GEN-LAST:event_btn_cancelar_nousuarioActionPerformed

    private void jmCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmCajaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jmCajaActionPerformed

    private void jmAdministracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmAdministracionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jmAdministracionActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jmiReporte_MovimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiReporte_MovimientoActionPerformed
        iniciarFomrulario_Reporte_Movimiento(new jifReporte_Movimiento_C());
    }//GEN-LAST:event_jmiReporte_MovimientoActionPerformed

    private void jtLista_UsuarioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtLista_UsuarioMousePressed
        if (evt.isPopupTrigger() && jtLista_Usuario.getModel().getRowCount() != 0
                && jtLista_Usuario.getSelectedRow() != -1) {
            jpmTrabajador.show(jtLista_Usuario, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_jtLista_UsuarioMousePressed

    private void jtLista_UsuarioMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtLista_UsuarioMouseReleased
        if (evt.isPopupTrigger() && jtLista_Usuario.getModel().getRowCount() != 0
                && jtLista_Usuario.getSelectedRow() != -1) {
            jpmTrabajador.show(jtLista_Usuario, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_jtLista_UsuarioMouseReleased

    private void jmiPermisoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiPermisoActionPerformed
        try {
            habilitarOpcioneMenu(Integer.parseInt(jtLista_Usuario.getValueAt(jtLista_Usuario.getSelectedRow(), 0).toString()));
            txtID_Usuario_Permiso.setText(jtLista_Usuario.getValueAt(jtLista_Usuario.getSelectedRow(), 0).toString());
            txtUsuario_Permiso.setText(jtLista_Usuario.getValueAt(jtLista_Usuario.getSelectedRow(), 3).toString());
            modalPermiso_usuario();
        } catch (Exception e) {
            System.out.println("Error al cargar los menus ");
        }
    }//GEN-LAST:event_jmiPermisoActionPerformed

    private void btnGuardar_PermisosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar_PermisosActionPerformed
        if (registrarPermisos(Integer.parseInt(txtID_Usuario_Permiso.getText())) != 0) {
            JOptionPane.showMessageDialog(null, "Para ver los Cambios en el Sistema, termine sesion y vuelva inciar",
                    "Aviso", 1);
        } else {
            JOptionPane.showMessageDialog(null, "No se puedo asignar persmiso al sistema", "Aviso", 1);
        }
    }//GEN-LAST:event_btnGuardar_PermisosActionPerformed

    /*METODOS PARA MOSTRAR EL FORMULARIO*/
    public void modalvalidacion_alquiler() {
        jdValidacion_Alquiler.pack();
        jdValidacion_Alquiler.setLocationRelativeTo(null);
        jdValidacion_Alquiler.getRootPane().registerKeyboardAction(new CloseDialogEscape(jdValidacion_Alquiler),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
        jdValidacion_Alquiler.setModal(true);
        jdValidacion_Alquiler.setVisible(true);
    }

    public void modalPermiso_usuario() {
        jdPermiso_Usuario.pack();
        jdPermiso_Usuario.setLocationRelativeTo(null);
        jdPermiso_Usuario.getRootPane().registerKeyboardAction(new CloseDialogEscape(jdPermiso_Usuario),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
        jdPermiso_Usuario.setModal(true);
        jdPermiso_Usuario.setVisible(true);
    }

    public void iniciarFomrulario(JInternalFrame jif) {
        try {
            jif.setSize(1014, 650);
            int x = (jdeskpanInicio.getWidth() / 2) - (jif.getWidth() / 2);
            int y = (jdeskpanInicio.getHeight() / 2) - (jif.getHeight() / 2);
            if (jif.isShowing()) {
                jif.setLocation(x, y);
            } else {
                jdeskpanInicio.add(jif);
                jif.setLocation(x, y);
                //jif.setVisible(true);
                jif.show();
            }
        } catch (Exception e) {
            System.out.println("" + e.toString());
            System.out.println("" + e.getMessage());
        }
    }

    public void iniciarFomrulario_nousuario_Adm(JInternalFrame jif) {
        try {
            jif.setSize(797, 547);
            int x = (jdeskpanInicio.getWidth() / 2) - (jif.getWidth() / 2);
            int y = (jdeskpanInicio.getHeight() / 2) - (jif.getHeight() / 2);
            if (jif.isShowing()) {
                jif.setLocation(x, y);
            } else {
                jdeskpanInicio.add(jif);
                jif.setLocation(x, y);
                //jif.setVisible(true);
                jif.show();
            }
        } catch (Exception e) {
            System.out.println("" + e.toString());
            System.out.println("" + e.getMessage());
        }
    }

    public void iniciarFomrulario_materiales_Adm(JInternalFrame jif) {
        try {
            jif.setSize(703, 559);
            int x = (jdeskpanInicio.getWidth() / 2) - (jif.getWidth() / 2);
            int y = (jdeskpanInicio.getHeight() / 2) - (jif.getHeight() / 2);
            if (jif.isShowing()) {
                jif.setLocation(x, y);
            } else {
                jdeskpanInicio.add(jif);
                jif.setLocation(x, y);
                //jif.setVisible(true);
                jif.show();
            }
        } catch (Exception e) {
            System.out.println("" + e.toString());
        }
    }

    public void iniciarFomrulario_lateralsublateral(JInternalFrame jif) {
        try {
            jif.setSize(700, 500);
            int x = (jdeskpanInicio.getWidth() / 2) - (jif.getWidth() / 2);
            int y = (jdeskpanInicio.getHeight() / 2) - (jif.getHeight() / 2);
            if (jif.isShowing()) {
                jif.setLocation(x, y);
            } else {
                jdeskpanInicio.add(jif);
                jif.setLocation(x, y);
                //jif.setVisible(true);
                jif.show();
            }
        } catch (Exception e) {
            System.out.println("" + e.toString());
            System.out.println("" + e.getMessage());
        }
    }

    public void iniciarFomrulario_Constancia(JInternalFrame jif) {
        try {
            jif.setSize(1112, 609);
            int x = (jdeskpanInicio.getWidth() / 2) - (jif.getWidth() / 2);
            int y = (jdeskpanInicio.getHeight() / 2) - (jif.getHeight() / 2);
            if (jif.isShowing()) {
                jif.setLocation(x, y);
            } else {
                jdeskpanInicio.add(jif);
                jif.setLocation(x, y);
                jif.show();
            }
        } catch (Exception e) {
            System.out.println("" + e.toString());
        }
    }

    public void iniciarFomrulario_VerPagos(JInternalFrame jif) {
        try {
            jif.setSize(1013, 524);
            int x = (jdeskpanInicio.getWidth() / 2) - (jif.getWidth() / 2);
            int y = (jdeskpanInicio.getHeight() / 2) - (jif.getHeight() / 2);
            if (jif.isShowing()) {
                jif.setLocation(x, y);
            } else {
                jdeskpanInicio.add(jif);
                jif.setLocation(x, y);
                jif.show();
            }
        } catch (Exception e) {
            System.out.println("" + e.toString());
        }
    }

    public void iniciarFomrulario_Traspaso(JInternalFrame jif) {
        try {
            jif.setSize(915, 591);
            int x = (jdeskpanInicio.getWidth() / 2) - (jif.getWidth() / 2);
            int y = (jdeskpanInicio.getHeight() / 2) - (jif.getHeight() / 2);
            if (jif.isShowing()) {
                jif.setLocation(x, y);
            } else {
                jdeskpanInicio.add(jif);
                jif.setLocation(x, y);
                //jif.setVisible(true);
                jif.show();
            }
        } catch (Exception e) {
            System.out.println("" + e.toString());
        }
    }

    public void iniciarFomrulario_Alquiler(JInternalFrame jif) {
        try {
            jif.setSize(852, 520);
            int x = (jdeskpanInicio.getWidth() / 2) - (jif.getWidth() / 2);
            int y = (jdeskpanInicio.getHeight() / 2) - (jif.getHeight() / 2);
            if (jif.isShowing()) {
                jif.setLocation(x, y);
            } else {
                jdeskpanInicio.add(jif);
                jif.setLocation(x, y);
                //jif.setVisible(true);
                jif.show();
            }
        } catch (Exception e) {
            System.out.println("" + e.toString());
        }
    }

    public void iniciarFomrulario_PadronMultaAsamblea(JInternalFrame jif) {
        try {
            jif.setSize(706, 312);
            int x = (jdeskpanInicio.getWidth() / 2) - (jif.getWidth() / 2);
            int y = (jdeskpanInicio.getHeight() / 2) - (jif.getHeight() / 2);
            if (jif.isShowing()) {
                jif.setLocation(x, y);
            } else {
                jdeskpanInicio.add(jif);
                jif.setLocation(x, y);
                //jif.setVisible(true);
                jif.show();
            }
        } catch (Exception e) {
            System.out.println("" + e.toString());
        }
    }

    public void iniciarFomrulario_PadronMultaSufragio(JInternalFrame jif) {
        try {
            jif.setSize(706, 312);
            int x = (jdeskpanInicio.getWidth() / 2) - (jif.getWidth() / 2);
            int y = (jdeskpanInicio.getHeight() / 2) - (jif.getHeight() / 2);
            if (jif.isShowing()) {
                jif.setLocation(x, y);
            } else {
                jdeskpanInicio.add(jif);
                jif.setLocation(x, y);
                //jif.setVisible(true);
                jif.show();
            }
        } catch (Exception e) {
            System.out.println("" + e.toString());
        }
    }

    public void iniciarFomrulario_Movimiento(JInternalFrame jif) {
        try {
            jif.setSize(610, 461);
            int x = (jdeskpanInicio.getWidth() / 2) - (jif.getWidth() / 2);
            int y = (jdeskpanInicio.getHeight() / 2) - (jif.getHeight() / 2);
            if (jif.isShowing()) {
                jif.setLocation(x, y);
            } else {
                jdeskpanInicio.add(jif);
                jif.setLocation(x, y);
                //jif.setVisible(true);
                jif.show();
            }
        } catch (Exception e) {
            System.out.println("" + e.toString());
        }
    }

    public void iniciarFomrulario_Cuentas(JInternalFrame jif) {
        try {
            jif.setSize(678, 531);
            int x = (jdeskpanInicio.getWidth() / 2) - (jif.getWidth() / 2);
            int y = (jdeskpanInicio.getHeight() / 2) - (jif.getHeight() / 2);
            if (jif.isShowing()) {
                jif.setLocation(x, y);
            } else {
                jdeskpanInicio.add(jif);
                jif.setLocation(x, y);
                //jif.setVisible(true);
                jif.show();
            }
        } catch (Exception e) {
            System.out.println("" + e.toString());
        }
    }

    public void iniciarFomrulario_Agricultor(JInternalFrame jif) {
        try {
            jif.setSize(907, 532);
            int x = (jdeskpanInicio.getWidth() / 2) - (jif.getWidth() / 2);
            int y = (jdeskpanInicio.getHeight() / 2) - (jif.getHeight() / 2);
            if (jif.isShowing()) {
                jif.setLocation(x, y);
            } else {
                jdeskpanInicio.add(jif);
                jif.setLocation(x, y);
                jif.show();
            }
        } catch (Exception e) {
            System.out.println("" + e.toString());
        }
    }

    public void iniciarFomrulario_Cargo(JInternalFrame jif) {
        try {
            jif.setSize(532, 491);
            int x = (jdeskpanInicio.getWidth() / 2) - (jif.getWidth() / 2);
            int y = (jdeskpanInicio.getHeight() / 2) - (jif.getHeight() / 2);
            if (jif.isShowing()) {
                jif.setLocation(x, y);
            } else {
                jdeskpanInicio.add(jif);
                jif.setLocation(x, y);
                //jif.setVisible(true);
                jif.show();
            }
        } catch (Exception e) {
            System.out.println("" + e.toString());
        }
    }

    public void iniciarFomrulario_Usuario(JInternalFrame jif) {
        try {
            jif.setSize(659, 510);
            int x = (jdeskpanInicio.getWidth() / 2) - (jif.getWidth() / 2);
            int y = (jdeskpanInicio.getHeight() / 2) - (jif.getHeight() / 2);
            if (jif.isShowing()) {
                jif.setLocation(x, y);
            } else {
                jdeskpanInicio.add(jif);
                jif.setLocation(x, y);
                jif.show();
            }
        } catch (Exception e) {
            System.out.println("" + e.toString());
        }
    }

    public void iniciarFomrulario_CierreInicioCaja(JInternalFrame jif) {
        try {
            jif.setSize(472, 239);
            int x = (jdeskpanInicio.getWidth() / 2) - (jif.getWidth() / 2);
            int y = (jdeskpanInicio.getHeight() / 2) - (jif.getHeight() / 2);
            if (jif.isShowing()) {
                jif.setLocation(x, y);
            } else {
                jdeskpanInicio.add(jif);
                jif.setLocation(x, y);
                //jif.setVisible(true);
                jif.show();
            }
        } catch (Exception e) {
            System.out.println("" + e.toString());
        }
    }

    public void iniciarFomrulario_Periodo(JInternalFrame jif) {
        try {
            jif.setSize(711, 471);
            int x = (jdeskpanInicio.getWidth() / 2) - (jif.getWidth() / 2);
            int y = (jdeskpanInicio.getHeight() / 2) - (jif.getHeight() / 2);
            if (jif.isShowing()) {
                jif.setLocation(x, y);
            } else {
                jdeskpanInicio.add(jif);
                jif.setLocation(x, y);
                //jif.setVisible(true);
                jif.show();
            }
        } catch (Exception e) {
            System.out.println("" + e.toString());
        }
    }

    public void iniciarFomrulario_Comite(JInternalFrame jif) {
        try {
            jif.setSize(709, 457);
            int x = (jdeskpanInicio.getWidth() / 2) - (jif.getWidth() / 2);
            int y = (jdeskpanInicio.getHeight() / 2) - (jif.getHeight() / 2);
            if (jif.isShowing()) {
                jif.setLocation(x, y);
            } else {
                jdeskpanInicio.add(jif);
                jif.setLocation(x, y);
                //jif.setVisible(true);
                jif.show();
            }
        } catch (Exception e) {
            System.out.println("" + e.toString());
        }
    }

    public void iniciarFomrulario_Reporte_Movimiento(JInternalFrame jif) {
        try {
            jif.setSize(1198, 608);
            int x = (jdeskpanInicio.getWidth() / 2) - (jif.getWidth() / 2);
            int y = (jdeskpanInicio.getHeight() / 2) - (jif.getHeight() / 2);
            if (jif.isShowing()) {
                jif.setLocation(x, y);
            } else {
                jdeskpanInicio.add(jif);
                jif.setLocation(x, y);
                jif.show();
            }
        } catch (Exception e) {
            System.out.println("" + e.toString());
        }
    }
    /*FIN DE METODOS PARA MOSTRAR EL FORMULARIO*/

    public void run() {
        Thread ct = Thread.currentThread();
        while (ct == h1) {
            calcula();
            txtFechaHora_Principal.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date()) + " " + hora + ":" + minutos + ":" + segundos + " " + ampm);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }

    public void calcula() {
        Calendar calendario = new GregorianCalendar();
        Date fechaHoraActual = new Date();

        calendario.setTime(fechaHoraActual);
        ampm = calendario.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM";
        if (ampm.equals("PM")) {
            int h = calendario.get(Calendar.HOUR_OF_DAY) - 12;
            if (h == 0) {
                h = 12;
            }

            hora = h > 9 ? "" + h : "0" + h;
        } else {
            hora = calendario.get(Calendar.HOUR_OF_DAY) > 9 ? "" + calendario.get(Calendar.HOUR_OF_DAY) : "0" + calendario.get(Calendar.HOUR_OF_DAY);
        }
        minutos = calendario.get(Calendar.MINUTE) > 9 ? "" + calendario.get(Calendar.MINUTE) : "0" + calendario.get(Calendar.MINUTE);
        segundos = calendario.get(Calendar.SECOND) > 9 ? "" + calendario.get(Calendar.SECOND) : "0" + calendario.get(Calendar.SECOND);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Inicio.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inicio.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inicio.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inicio.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inicio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Codigo2;
    private javax.swing.JLabel Codigo3;
    private javax.swing.JButton btnAgregarDet_Alquiler;
    private javax.swing.JButton btnBuscarAgricultor_Alquiler;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCancelar5;
    private javax.swing.JButton btnCancelar6;
    private javax.swing.JButton btnEliminarDet_Alquiler;
    private javax.swing.JButton btnEliminar_DetLateales;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnGuardar5;
    private javax.swing.JButton btnGuardar6;
    private javax.swing.JButton btnGuardar_Comite;
    private javax.swing.JButton btnGuardar_Permisos;
    private javax.swing.ButtonGroup btnTipodeSembrio;
    private javax.swing.JButton btn_Cancelar1;
    private javax.swing.JButton btn_Cancelar_Usuario;
    private javax.swing.JButton btn_Guardar_Usuario;
    private javax.swing.JButton btn_buscar_alquileres;
    private javax.swing.JButton btn_buscar_usuario_alquiler;
    private javax.swing.JButton btn_cancelar_lateral;
    private javax.swing.JButton btn_cancelar_nousuario;
    private javax.swing.JButton btn_cancelar_sublateral;
    private javax.swing.JButton btn_guardar_lateral;
    private javax.swing.JButton btn_guardar_nousuario;
    private javax.swing.JButton btn_guardar_periodo;
    private javax.swing.JButton btn_guardar_sublateral;
    private javax.swing.JButton btn_registrar_alquiler;
    private javax.swing.JButton btn_validar_alquiler;
    private javax.swing.ButtonGroup btngroup_NoUsuario;
    private javax.swing.ButtonGroup btngroup_Tipo_Alquiler;
    private org.jdesktop.swingx.JXComboBox cboAgricultor_Alquiler;
    private org.jdesktop.swingx.JXComboBox cboCargo_Usuario;
    private org.jdesktop.swingx.JXComboBox cboCuentas_AsignarCostos;
    private javax.swing.JComboBox cboFiltroAgricultor;
    private javax.swing.JComboBox cboFiltro_Cuenta;
    private javax.swing.JComboBox cboFiltro_Periodo;
    private org.jdesktop.swingx.JXComboBox cboLateral_Agricultor;
    private org.jdesktop.swingx.JXComboBox cboPeriodo_MesFin;
    private org.jdesktop.swingx.JXComboBox cboPeriodo_MesInicio;
    private org.jdesktop.swingx.JXComboBox cboSexo_Agricultor;
    private org.jdesktop.swingx.JXComboBox cboSubLateral_Agricultor;
    private javax.swing.JComboBox cboTipoFiltro_Usuario;
    private org.jdesktop.swingx.JXComboBox cboTipoMaterial_Alquiler;
    private org.jdesktop.swingx.JXComboBox cboTrabajador_Alquiler;
    private javax.swing.JCheckBox chkFiltroAgricultor_Alquiler;
    private javax.swing.JCheckBox chkFiltroFecha_Alquiler;
    private javax.swing.JCheckBox chkRuc;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton17;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel122;
    private javax.swing.JLabel jLabel123;
    private javax.swing.JLabel jLabel124;
    private javax.swing.JLabel jLabel125;
    private javax.swing.JLabel jLabel126;
    private javax.swing.JLabel jLabel127;
    private javax.swing.JLabel jLabel128;
    private javax.swing.JLabel jLabel129;
    private javax.swing.JLabel jLabel130;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane23;
    private javax.swing.JScrollPane jScrollPane24;
    private javax.swing.JScrollPane jScrollPane25;
    private javax.swing.JScrollPane jScrollPane27;
    private javax.swing.JScrollPane jScrollPane28;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane31;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTabbedPane jTabbedPane10;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTabbedPane jTabbedPane5;
    private javax.swing.JTabbedPane jTabbedPane7;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JDialog jdAlquilerAgricultor;
    private javax.swing.JDialog jdPermiso_Usuario;
    private javax.swing.JDialog jdValidacion_Alquiler;
    private javax.swing.JDesktopPane jdeskpanInicio;
    private javax.swing.JInternalFrame jifAgricultores;
    private javax.swing.JInternalFrame jifComites;
    private javax.swing.JInternalFrame jifCuentas;
    private javax.swing.JInternalFrame jifDocumento;
    private javax.swing.JInternalFrame jifIngresarAlquiler;
    private javax.swing.JInternalFrame jifLateral_SubLateral_Adm;
    private javax.swing.JInternalFrame jifNoUsuario;
    private javax.swing.JInternalFrame jifPeriodos;
    private javax.swing.JPanel jifRegistrarAlquiler;
    private javax.swing.JInternalFrame jifUsuario;
    private javax.swing.JMenuItem jimQuitarLateral;
    private javax.swing.JLabel jlbSubLateral;
    public javax.swing.JMenu jmAdministracion;
    public javax.swing.JMenu jmCaja;
    public javax.swing.JMenu jmConstancia;
    private javax.swing.JMenu jmInicio;
    public javax.swing.JMenu jmPagos;
    private javax.swing.JMenuBar jmbPrincipal;
    public javax.swing.JMenuItem jmiAlquiler;
    public javax.swing.JMenuItem jmiCargos;
    public javax.swing.JMenuItem jmiClientes;
    public javax.swing.JMenuItem jmiComite;
    public javax.swing.JMenuItem jmiCuentas;
    public javax.swing.JMenuItem jmiDocumento;
    private javax.swing.JMenuItem jmiEditar;
    public javax.swing.JMenuItem jmiInicioCierre;
    public javax.swing.JMenuItem jmiLateral_SubLateral;
    public javax.swing.JMenuItem jmiMateriales;
    public javax.swing.JMenuItem jmiMovimiento;
    public javax.swing.JMenuItem jmiNoUsuario;
    public javax.swing.JMenuItem jmiPagoMultaAsamblea;
    public javax.swing.JMenuItem jmiPagoMultaSufragio;
    public javax.swing.JMenu jmiPagoMultas;
    public javax.swing.JMenuItem jmiPagos;
    public javax.swing.JMenuItem jmiPeriodo;
    private javax.swing.JMenuItem jmiPermiso;
    public javax.swing.JMenuItem jmiRegistro;
    public javax.swing.JMenuItem jmiReporte_Movimiento;
    private javax.swing.JMenuItem jmiSalir;
    private javax.swing.JMenuItem jmiSubLateral;
    public javax.swing.JMenuItem jmiTrabajador;
    public javax.swing.JMenuItem jmiTraspaso;
    public javax.swing.JMenuItem jmiUsuario;
    public javax.swing.JMenuItem jmiVerPagos;
    private javax.swing.JMenuItem jmip_Anular;
    private javax.swing.JMenuItem jmip_GenerarDocumento;
    private javax.swing.JMenuItem jmip_Pagar;
    private javax.swing.JPanel jpBuscarAgricultor_Traspaso1;
    private javax.swing.JPanel jpInicio;
    private javax.swing.JPanel jpLaterales;
    public javax.swing.JMenu jpReportes;
    private javax.swing.JPanel jpUsurio;
    private javax.swing.JPopupMenu jpmAgricultor;
    private javax.swing.JPopupMenu jpmLateral;
    private javax.swing.JPopupMenu jpmSubLateral;
    private javax.swing.JPopupMenu jpmTrabajador;
    private javax.swing.JPopupMenu jpmVerPagos;
    private javax.swing.JTable jtAgricultor;
    private javax.swing.JTable jtAsignarCosto_Cuentas;
    private javax.swing.JTable jtComite_Administracion;
    private javax.swing.JTable jtCuentas;
    private javax.swing.JTable jtDetalleLaterales_Agricultor;
    private javax.swing.JTable jtLateral_Adm;
    private javax.swing.JTable jtLista_Alquileres;
    private javax.swing.JTable jtLista_Usuario;
    private javax.swing.JTable jtModalAgricultor_Alquiler;
    private javax.swing.JTable jtNoCliente_Adm;
    private javax.swing.JTable jtPeriodo_All;
    private javax.swing.JTable jtSubLateral_Adm;
    private javax.swing.JTabbedPane jtbAlquiler;
    private javax.swing.JTable jtbDetalle_Alquiler;
    private javax.swing.JTable jtb_lista_permisos;
    private javax.swing.ButtonGroup rb_group;
    private javax.swing.JRadioButton rbtnNoUsuario_Alquiler;
    private javax.swing.JRadioButton rbtnPersonaJuridica;
    private javax.swing.JRadioButton rbtnPersonaNatural;
    private javax.swing.JRadioButton rbtnUsuario_Alquiler;
    private javax.swing.JTextField txtAgricultor_Alquiler;
    private javax.swing.JTextField txtAnio_Principal;
    private javax.swing.JTextField txtApeMaterno_Agricultor;
    private javax.swing.JTextField txtApeMaterno_NoUsuario;
    private javax.swing.JTextField txtApePaterno_Agricultor;
    private javax.swing.JTextField txtApePaterno_NoUsuario;
    private com.toedter.components.JSpinField txtCantidad_Alquiler;
    private javax.swing.JTextField txtCelular_Agricultor;
    private javax.swing.JTextField txtCelular_NoUsuario;
    private org.jdesktop.swingx.JXTextField txtCodigo_Cuenta;
    private javax.swing.JTextField txtComite_Registrar;
    private javax.swing.JTextField txtConMedida_Agricultor;
    private javax.swing.JTextArea txtConcepto_AsignarCosto;
    private org.jdesktop.swingx.JXSearchField txtDNI_Agricultor;
    private org.jdesktop.swingx.JXSearchField txtDNI_NoUsuario;
    private javax.swing.JTextField txtDireccion_Agricultor;
    private javax.swing.JTextField txtDireccion_NoUsuario;
    private javax.swing.JTextField txtDireccion_Usuario;
    private javax.swing.JTextField txtEmail_Agricultor;
    private javax.swing.JTextField txtEmail_Usuario;
    private com.toedter.calendar.JDateChooser txtFechaDesde_Alquiler;
    private com.toedter.calendar.JDateChooser txtFechaFin_Alquiler;
    private com.toedter.calendar.JDateChooser txtFechaHasta_Alquiler;
    private javax.swing.JTextField txtFechaHora_Principal;
    private com.toedter.calendar.JDateChooser txtFechaInicio_Alquiler;
    private com.toedter.calendar.JDateChooser txtFechaNacimiento_Usuario;
    private org.jdesktop.swingx.JXSearchField txtFiltroAgricultor;
    private org.jdesktop.swingx.JXSearchField txtFiltroComite_Administracion;
    private org.jdesktop.swingx.JXSearchField txtFiltroNombre_Cuenta2;
    private org.jdesktop.swingx.JXSearchField txtFiltroNombre_Lateral;
    private org.jdesktop.swingx.JXSearchField txtFiltroNombre_NoCliente;
    private org.jdesktop.swingx.JXSearchField txtFiltroNombre_SubLateral;
    private org.jdesktop.swingx.JXSearchField txtFiltro_Periodo;
    private org.jdesktop.swingx.JXSearchField txtFiltro_Usuario;
    private com.toedter.components.JSpinField txtHoras_Alquiler;
    private javax.swing.JTextField txtID_Usuario;
    private javax.swing.JTextField txtID_Usuario_Permiso;
    private javax.swing.JTextField txtModalAgricultor_Alquiler;
    private org.jdesktop.swingx.JXTextField txtMonto_Alquiler;
    private org.jdesktop.swingx.JXTextField txtMonto_AsignarCuenta;
    private javax.swing.JTextField txtNombre_Cuentas;
    private javax.swing.JTextField txtNombre_Lateral;
    private javax.swing.JTextField txtNombre_Periodo;
    private javax.swing.JTextField txtNombre_SubLateral;
    private javax.swing.JTextField txtNombres_Agricultor;
    private javax.swing.JTextField txtNombres_NoUsuario;
    private org.jdesktop.swingx.JXTextField txtNumCuenta_Registrar;
    private javax.swing.JTextField txtNumHectareas_Agricultor;
    private javax.swing.JTextField txtRazonSocial_NoUsuario;
    private javax.swing.JTextField txtRuc_NoUsuario;
    private javax.swing.JTextField txtSinMedida_Agricultor;
    private javax.swing.JTextField txtTeleCelular_Usuario;
    private javax.swing.JTextField txtTelefono_Agricultor;
    private javax.swing.JTextField txtTelefono_NoUsuario;
    private javax.swing.JTextField txtUsuario_Permiso;
    public javax.swing.JTextField txtUsuario_Principal;
    private javax.swing.JPasswordField txtValidacionPass_Alquiler;
    private javax.swing.JTextField txtapellidos_usuario;
    private javax.swing.JTextField txtdni_usuario;
    private javax.swing.JTextField txtnombres_usuario;
    private javax.swing.JPasswordField txtpass_usuario;
    // End of variables declaration//GEN-END:variables
}
