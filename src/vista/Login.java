/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import entidad.ListaOpciones;
import entidad.Usuario;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import utilitario.StringMD;

/**
 *
 * @author joseph
 */
public class Login extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.cargarUsuario();
    }

    private void cargarUsuario() {
        for (Usuario u : new BLUsuario().get_usuario_login()) {
            cboUsuario.addItem(u);
        }
        AutoCompleteDecorator.decorate(cboUsuario);
    }

    private String login(Usuario usu, String pass) {
        String mensaje = "error";
        try {
            pass = StringMD.getStringMessageDigest(pass, StringMD.MD5);
            if (usu.getVar_password().equalsIgnoreCase(pass)) {
                Inicio i = new Inicio();
                habilitar_Opciones_Menu(usu.getInt_id(), i);
                i.txtUsuario_Principal.setText(usu.getVar_nombres() + ", " + usu.getVar_apellidos());
                i.setVisible(true);
                this.dispose();
            }
            mensaje = "ok";
        } catch (Exception e) {
            System.out.println("Error de Login-Vista :" + e.getMessage());
            e.printStackTrace();
        }
        return mensaje;
    }

    private void habilitar_Opciones_Menu(int usuario_id, Inicio i) {
        for (ListaOpciones mop : new BLUsuario().selectOpciones_ByUsuario(usuario_id)) {
            switch (mop.getPath()) {
                case "jmCaja":
                    i.jmCaja.setVisible(mop.isEstado());
                    break;
                case "jmiInicioCierre":
                    i.jmiInicioCierre.setVisible(mop.isEstado());
                    break;
                case "jmiMovimiento":
                    i.jmiMovimiento.setVisible(mop.isEstado());
                    break;
                case "jmConstancia":
                    i.jmConstancia.setVisible(mop.isEstado());
                    break;
                case "jmiRegistro":
                    i.jmiRegistro.setVisible(mop.isEstado());
                    break;
                case "jmiTraspaso":
                    i.jmiTraspaso.setVisible(mop.isEstado());
                    break;
                case "jmPagos":
                    i.jmPagos.setVisible(mop.isEstado());
                    break;
                case "jmiVerPagos":
                    i.jmiVerPagos.setVisible(mop.isEstado());
                    break;
                case "jmiAlquiler":
                    i.jmiAlquiler.setVisible(mop.isEstado());
                    break;
                case "jmiPagoMultaAsamblea":
                    i.jmiPagoMultaAsamblea.setVisible(mop.isEstado());
                    break;
                case "jmiPagoMultaSufragio":
                    i.jmiPagoMultaSufragio.setVisible(mop.isEstado());
                    break;
                case "jpReportes":
                    i.jpReportes.setVisible(mop.isEstado());
                    break;
                case "jmiPagos":
                    i.jmiPagos.setVisible(mop.isEstado());
                    break;
                case "jmiClientes":
                    i.jmiClientes.setVisible(mop.isEstado());
                    break;
                case "jmiReporte_Movimiento":
                    i.jmiReporte_Movimiento.setVisible(mop.isEstado());
                    break;
                case "jmAdministracion":
                    i.jmAdministracion.setVisible(mop.isEstado());
                    break;
                case "jmiTrabajador":
                    i.jmiTrabajador.setVisible(mop.isEstado());
                    break;
                case "jmiUsuario":
                    i.jmiUsuario.setVisible(mop.isEstado());
                    break;
                case "jmiNoUsuario":
                    i.jmiNoUsuario.setVisible(mop.isEstado());
                    break;
                case "jmiCuentas":
                    i.jmiCuentas.setVisible(mop.isEstado());
                    break;
                case "jmiPeriodo":
                    i.jmiPeriodo.setVisible(mop.isEstado());
                    break;
                case "jmiCargos":
                    i.jmiPeriodo.setVisible(mop.isEstado());
                    break;
                case "jmiComite":
                    i.jmiComite.setVisible(mop.isEstado());
                    break;
                case "jmiLateral_SubLateral":
                    i.jmiLateral_SubLateral.setVisible(mop.isEstado());
                    break;
                case "jmiMateriales":
                    i.jmiMateriales.setVisible(mop.isEstado());
                    break;
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btn_ingresar_login = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btn_salir_login = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtPassword_Login = new javax.swing.JPasswordField();
        cboUsuario = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Sistema de Cobranza Comisión de Usuarios Perla del Huallaga");
        setIconImage(new ImageIcon(getClass().getResource("/recurso/comision_logo.jpg")).getImage());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btn_ingresar_login.setBackground(new java.awt.Color(255, 204, 0));
        btn_ingresar_login.setFont(new java.awt.Font("Trebuchet MS", 1, 26)); // NOI18N
        btn_ingresar_login.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/ingresar.png"))); // NOI18N
        btn_ingresar_login.setText("INGRESAR");
        btn_ingresar_login.setToolTipText("");
        btn_ingresar_login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ingresar_loginActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(143, 183, 68));
        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 1, 28)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SISTEMA DE COBRANZA COMISION DE USUARIOS PERLA DEL HUALLAGA");
        jLabel1.setOpaque(true);

        btn_salir_login.setBackground(new java.awt.Color(255, 204, 0));
        btn_salir_login.setFont(new java.awt.Font("Trebuchet MS", 1, 26)); // NOI18N
        btn_salir_login.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/salir.png"))); // NOI18N
        btn_salir_login.setText("SALIR");
        btn_salir_login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salir_loginActionPerformed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recurso/User_2.png"))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 3, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Sistema de Cobranza V 0.1");

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 3, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Derechos Reservados por @CLMDEVELOPERS");

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel5.setText("USUARIO :");

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel6.setText("CLAVE :");

        txtPassword_Login.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtPassword_Login.setForeground(new java.awt.Color(255, 204, 0));
        txtPassword_Login.setText("password");

        cboUsuario.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addComponent(jLabel2)
                .addGap(66, 66, 66)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btn_ingresar_login)
                        .addGap(18, 18, 18)
                        .addComponent(btn_salir_login, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPassword_Login)
                            .addComponent(cboUsuario, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 83, Short.MAX_VALUE))
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtPassword_Login, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_salir_login, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_ingresar_login))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ingresar_loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ingresar_loginActionPerformed
        try {
            Usuario u = (Usuario) cboUsuario.getSelectedItem();
            String clave = new String(txtPassword_Login.getPassword());
            if (clave.compareTo("") != 0) {
                if (login(u, clave).equalsIgnoreCase("error")) {
                    JOptionPane.showMessageDialog(null, "[ Contraseña incorrecta ]");
                };
            } else {
                JOptionPane.showMessageDialog(null, "[ Ingrese su Contraseña ]");
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btn_ingresar_loginActionPerformed

    private void btn_salir_loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salir_loginActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_salir_loginActionPerformed

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_ingresar_login;
    private javax.swing.JButton btn_salir_login;
    private javax.swing.JComboBox cboUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField txtPassword_Login;
    // End of variables declaration//GEN-END:variables
}
