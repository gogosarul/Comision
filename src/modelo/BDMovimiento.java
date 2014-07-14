/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import entidad.ListaMovimiento;
import entidad.Material;
import entidad.Movimiento;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author joseph
 */
public class BDMovimiento {

    public boolean insertarMovimiento(Movimiento m) {

        boolean resultado = true;
        Connection cnn = null;
        CallableStatement cstmt = null;
        try {
            cnn = BD.getConnection();
            cnn.setAutoCommit(false);
            String sql = "call spI_Movimiento(?,?,?,?,?,?,?,?,?);";
            cstmt = cnn.prepareCall(sql);
            cstmt.setInt(1, m.getUsuario_id());
            cstmt.setString(2, m.getVar_concepto());
            cstmt.setDouble(3, m.getDec_monto());
            cstmt.setInt(4, m.getInt_tipoOperacion());
            cstmt.setTimestamp(5, m.getDat_fecregistro());
            cstmt.setInt(6, m.getInt_tipoComprobante());
            cstmt.setString(7, m.getVar_numeroComprobante());
            cstmt.setDouble(8, m.getDec_cantidad());
            cstmt.setInt(9, m.getInt_proveedor());
            cstmt.execute();
            cnn.commit();
        } catch (SQLException a) {
            try {
                cnn.rollback();
            } catch (SQLException b) {
                System.out.println("" + b.toString());
            } finally {
                resultado = false;
            }
            System.out.println("error al registrar movimiento " + a.toString());
        } finally {
            try {
                cstmt.close();
                cnn.close();
            } catch (SQLException ex) {
                System.out.println("" + ex.getMessage());
            }
        }
        return resultado;
    }

    public ArrayList<ListaMovimiento> get_reporte_movimiento(String condicion) {
        Connection cnn = null;
        CallableStatement cstmt = null;
        ArrayList<ListaMovimiento> lista_material = new ArrayList<ListaMovimiento>();
        try {
            cnn = BD.getConnection();
            String sql = "select * from get_reporte_movimiento " + condicion;
            cstmt = cnn.prepareCall(sql);
            ResultSet rs = cstmt.executeQuery();
            int cont = 0;
            double ingreso = 0.0, egreso = 0.0, total = 0.0;
            while (rs.next()) {
                ListaMovimiento lm = new ListaMovimiento();
                ingreso = rs.getDouble("dec_ingreso");
                egreso = rs.getDouble("dec_egreso");
                lm.setDat_fecregistro(rs.getDate("dat_fecregistro"));
                lm.setTipoComprobante(rs.getString("TipoComprobante"));
                lm.setInt_tipoOperacion(rs.getInt("int_tipoOperacion"));
                lm.setVar_numeroComprobante(rs.getString("var_numeroComprobante"));
                lm.setInt_proveedor(rs.getInt("int_proveedor"));
                lm.setDec_cantidad(rs.getDouble("dec_cantidad"));
                lm.setVar_concepto(rs.getString("var_concepto"));
                lm.setUsuario_id(rs.getInt("usuario_id"));
                lm.setTipo(rs.getInt("tipo"));
                lm.setDec_ingreso(new BigDecimal(String.valueOf(ingreso)));
                lm.setDec_egreso(new BigDecimal(String.valueOf(egreso)));
                if (cont == 0) {
                    total = ingreso + egreso;
                } else {
                    total = ingreso == 0.0 ? (egreso + total) : (ingreso + total);
                }
                lm.setDec_saldo(new BigDecimal(String.valueOf(total)));
                lista_material.add(lm);
                cont++;
            }
            cstmt.close();
            cnn.close();
        } catch (SQLException a) {
            System.out.println("" + a);
        }
        return lista_material;
    }

}
