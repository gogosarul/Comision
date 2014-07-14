/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import entidad.ListaOpciones;
import entidad.ListaUsuario;
import entidad.Usuario;
import java.sql.Date;
import java.util.ArrayList;
import modelo.BDUsuario;

/**
 *
 * @author Richard
 */
class BLUsuario {

    public boolean Registrar(String usuario, String pass, String dni, String nombres, String apellidos,
            Date fechaNacimiento, String telefon, int idcargo, String direccion, String email) {
        boolean resultado = false;
        try {
            Usuario u = new Usuario();
            u.setVar_apellidos(apellidos);
            u.setVar_direccion(direccion);
            u.setVar_dni(dni);
            u.setVar_email(email);
            u.setVar_nombres(nombres);
            u.setVar_password(pass);
            u.setVar_telefono(telefon);
            u.setVar_user(usuario);
            u.setDat_fechanacimiento(fechaNacimiento);
            u.setCargo_id(idcargo);
            resultado = new BDUsuario().Registrar(u);
        } catch (Exception e) {
            System.out.println("error en controlador al registrar usuario " + e.toString());
        }
        return resultado;
    }

    public ArrayList<ListaUsuario> get_usuario_all(String user_filtro, int indice) {
        ArrayList<ListaUsuario> listUsuario = new ArrayList<ListaUsuario>();
        String condicion = "";
        try {
            BDUsuario u = new BDUsuario();
            if (indice == 0) {
                condicion = " var_dni like '%" + user_filtro + "%' ";
            }
            if (indice == 1) {
                condicion = " var_nombres like '%" + user_filtro + "%' ";
            }
            if (indice == 2) {
                condicion = " var_apellidos like '%" + user_filtro + "%' ";
            }
            if (indice == 3) {
                condicion = " var_user like '%" + user_filtro + "%' ";
            }
            listUsuario = u.get_usuario_all(condicion);
        } catch (Exception e) {
            System.out.println("Error de Listado");
            e.printStackTrace();
        }
        return listUsuario;

    }

    public ArrayList<Usuario> get_usuario_login() {
        ArrayList<Usuario> listUsuario = new ArrayList<Usuario>();
        try {
            listUsuario = new BDUsuario().get_usuario_all();
        } catch (Exception e) {
            System.out.println("Error de Listado");
            e.printStackTrace();
        }
        return listUsuario;
    }

    public Usuario login(String usuario, String password) {
        Usuario u = new Usuario();
        try {
            BDUsuario us = new BDUsuario();
            u = us.login(usuario, password);
        } catch (Exception e) {
            System.out.println("Error de Login" + e.getMessage());
            e.printStackTrace();
        }
        return u;
    }

    public Usuario get_usuario_bypassword(String pass) {
        Usuario us = new Usuario();
        try {
            BDUsuario u = new BDUsuario();
            us = u.get_usuario_bypassword(pass);
        } catch (Exception e) {
            System.out.println("Error de Validacion " + e.getMessage());
            e.printStackTrace();
        }
        return us;
    }

    public ArrayList<ListaOpciones> selectOpciones_ByUsuario(int usuario_id) {
        ArrayList<ListaOpciones> listm = new ArrayList<ListaOpciones>();
        try {
            listm = new BDUsuario().selectOpciones_ByUsuario(usuario_id);
        } catch (Exception a) {
            System.out.println("" + a);
        }
        return listm;
    }
    
    public ArrayList<ListaOpciones> selectOpcionesActivas_ByUsuario(int usuario_id) {
        ArrayList<ListaOpciones> listm = new ArrayList<ListaOpciones>();
        try {
            listm = new BDUsuario().selectOpciones_ByUsuario(usuario_id);
        } catch (Exception a) {
            System.out.println("" + a);
        }
        return listm;
    }
    
    public int registrarPermisosUsuario(int usuario_id,int group_id,int estado){
        int valor =0;
        try{
            valor = new BDUsuario().insertPermisosUsuario(usuario_id, group_id,estado);
        }catch (Exception a) {
            System.out.println("" + a);
        }
        return valor;
    }

}
