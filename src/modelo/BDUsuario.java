/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import entidad.ListaOpciones;
import entidad.ListaUsuario;
import entidad.Usuario;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author joseph
 */
public class BDUsuario {

    public boolean Registrar(Usuario u) {
        Connection cn = null;
        CallableStatement cstm = null;
        boolean resultado = true;
        try {
            cn = BD.getConnection();
            cn.setAutoCommit(false);
            String sql = "call spI_Usuario(?,?,?,?,?,?,?,?,?,?);";
            cstm = cn.prepareCall(sql);
            cstm.setString(1, u.getVar_user());
            cstm.setString(2, u.getVar_password());
            cstm.setString(3, u.getVar_dni());
            cstm.setString(4, u.getVar_nombres());
            cstm.setString(5, u.getVar_apellidos());
            cstm.setInt(6, u.getCargo_id());
            cstm.setString(7, u.getVar_direccion());
            cstm.setString(8, u.getVar_email());
            cstm.setString(9, u.getVar_telefono());
            cstm.setDate(10, u.getDat_fechanacimiento());
            cstm.execute();
            cn.commit();
        } catch (SQLException a) {
            try {
                cn.rollback();
            } catch (SQLException b) {
                System.out.println("" + b.getMessage());
            } finally {
                resultado = false;
            }
            System.out.println("" + a);
        } finally {
            try {
                cstm.close();
                cn.close();
            } catch (SQLException ex) {
                System.out.println("" + ex.getMessage());
            }
        }
        return resultado;
    }

    public ArrayList<ListaUsuario> get_usuario_all(String condicion) {
        Connection cnn = null;
        CallableStatement cstmt = null;
        ArrayList<ListaUsuario> listUsuario = new ArrayList<ListaUsuario>();
        try {
            cnn = BD.getConnection();
            String sql = "select * from  get_usuario_all where " + condicion + " order by 1 desc";
            cstmt = cnn.prepareCall(sql);
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                ListaUsuario a = new ListaUsuario();
                a.setInt_id(rs.getInt("int_id"));
                a.setVar_user(rs.getString("var_user"));
                a.setVar_password(rs.getString("var_password"));
                a.setVar_dni(rs.getString("var_dni"));
                a.setVar_nombres(rs.getString("var_nombres"));
                a.setVar_apellidos(rs.getString("var_apellidos"));
                a.setVar_descripcion(rs.getString("var_descripcion"));
                a.setVar_telefono(rs.getString("var_telefono"));
                listUsuario.add(a);
            }
            cstmt.close();
            cnn.close();
        } catch (SQLException a) {
            System.out.println("" + a);
        }
        return listUsuario;
    }

    public ArrayList<Usuario> get_usuario_all() {
        Connection cnn = null;
        CallableStatement cstmt = null;
        ArrayList<Usuario> listUsuario = new ArrayList<Usuario>();
        try {
            cnn = BD.getConnection();
            String sql = "select * from  get_usuario_all";
            cstmt = cnn.prepareCall(sql);
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                Usuario a = new Usuario();
                a.setInt_id(rs.getInt("int_id"));
                a.setVar_user(rs.getString("var_user"));
                a.setVar_password(rs.getString("var_password"));
                a.setVar_dni(rs.getString("var_dni"));
                a.setVar_nombres(rs.getString("var_nombres"));
                a.setVar_apellidos(rs.getString("var_apellidos"));
                a.setVar_telefono(rs.getString("var_telefono"));
                listUsuario.add(a);
            }
            cstmt.close();
            cnn.close();
        } catch (SQLException a) {
            System.out.println("" + a);
        }
        return listUsuario;
    }

    public Usuario login(String usuario, String password) {
        Connection cnn = null;
        CallableStatement cstmt = null;
        Usuario u = new Usuario();
        try {
            cnn = BD.getConnection();
            String sql = "call spC_Login(?,?);";
            cstmt = cnn.prepareCall(sql);
            cstmt.setString(1, usuario);
            cstmt.setString(2, password);
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                u.setInt_id(rs.getInt("int_id"));
                u.setVar_user(rs.getString("var_user"));
                u.setVar_password(rs.getString("var_password"));
                u.setVar_estado(rs.getString("var_estado"));
            }
            cstmt.close();
            cnn.close();
        } catch (SQLException a) {
            System.out.println("" + a);
        }
        return u;
    }

    public Usuario get_usuario_bypassword(String pass) {
        Connection cnn = null;
        CallableStatement cstmt = null;
        Usuario u = new Usuario();
        try {
            cnn = BD.getConnection();
            String sql = "call spC_Usuario_ByPassword(?);";
            cstmt = cnn.prepareCall(sql);
            cstmt.setString(1, pass);
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                u.setInt_id(rs.getInt("int_id"));
                u.setVar_user(rs.getString("var_user"));
                u.setVar_password(rs.getString("var_password"));
                u.setVar_estado(rs.getString("var_estado"));
            }
            cstmt.close();
            cnn.close();
        } catch (SQLException a) {
            System.out.println("" + a);
        }
        return u;
    }

    public ArrayList<ListaOpciones> selectOpciones_ByUsuario(int usuario_id) {
        ArrayList<ListaOpciones> listm = new ArrayList<ListaOpciones>();
        try {
            Connection cnn = BD.getConnection();
            CallableStatement cstmt = null;
            cstmt = cnn.prepareCall("{call spC_ListaOpciones_Menu (?)}");
            cstmt.setInt(1, usuario_id);
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                ListaOpciones mp = new ListaOpciones();
                mp.setGrupo(rs.getInt("grupo"));
                mp.setNivel(rs.getInt("nivel"));
                mp.setMenu(rs.getString("menu"));
                mp.setSubmenu(rs.getString("submenu"));
                mp.setPath(rs.getString("path"));
                mp.setOrden(rs.getInt("orden"));
                mp.setEstado(rs.getBoolean("estado"));
                listm.add(mp);
            }
            cnn.close();
            cstmt.close();
        } catch (SQLException a) {
            System.out.println("" + a);
        }
        return listm;
    }

    public ArrayList<ListaOpciones> selectOpcionesActivos_ByUsuario(int usuario_id) {
        ArrayList<ListaOpciones> listm = new ArrayList<ListaOpciones>();
        try {
            Connection cnn = BD.getConnection();
            CallableStatement cstmt = null;
            cstmt = cnn.prepareCall("{call spC_ListaOpcionesActivos_Menu (?)}");
            cstmt.setInt(1, usuario_id);
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                ListaOpciones mp = new ListaOpciones();
                mp.setGrupo(rs.getInt("grupo"));
                mp.setNivel(rs.getInt("nivel"));
                mp.setMenu(rs.getString("menu"));
                mp.setSubmenu(rs.getString("submenu"));
                mp.setPath(rs.getString("path"));
                mp.setOrden(rs.getInt("orden"));
                mp.setEstado(rs.getBoolean("estado"));
                listm.add(mp);
            }
            cnn.close();
            cstmt.close();
        } catch (SQLException a) {
            System.out.println("" + a);
        }
        return listm;
    }

    public int insertPermisosUsuario(int usuario_id, int group_id, int estado) {
        int valor = 0;
        try {
            Connection cnn = BD.getConnection();
            PreparedStatement ps = null;
            PreparedStatement ps_udp = null;
            PreparedStatement ps_reg = null;
            ps = cnn.prepareStatement("select mu.* from usuario_group mu where mu.usuario_id=? and mu.group_id=?");
            ps.setInt(1, usuario_id);
            ps.setInt(2, group_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ps_udp = cnn.prepareStatement("update usuario_group set int_estado=? where mu.usuario_id=? and mu.group_id=?;");
                ps_udp.setInt(1, estado);
                ps_udp.setInt(2, usuario_id);
                ps_udp.setInt(3, group_id);
                ps_udp.execute();
            } else {
                ps_reg = cnn.prepareStatement("INSERT INTO usuario_group (group_id,usuario_id,int_estado) VALUES (?,?,?); ");
                ps_reg.setInt(1, group_id);
                ps_reg.setInt(2, usuario_id);
                ps_reg.setInt(3, estado);
                ps_reg.execute();
            }
            rs.close();
            ps.close();
        } catch (SQLException a) {
            System.out.println("" + a);
            a.printStackTrace();
        }
        return valor;
    }

}
