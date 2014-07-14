/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidad.ListaMovimiento;
import entidad.Movimiento;
import java.sql.Timestamp;
import java.util.ArrayList;
import modelo.BDMovimiento;

/**
 *
 * @author Richard
 */
public class BLMovimiento {

    public boolean insertarMovimiento(int usuario, double monto, int tipoOperacion,
            int tipoComprobante, String numeroComprobante, double cantidad, int proveedor, String concepto,
            Timestamp fechaRegistro) {
        boolean resultado = false;
        try {
            Movimiento m = new Movimiento();
            m.setDec_monto(monto);
            m.setUsuario_id(usuario);
            m.setVar_concepto(concepto);
            m.setDec_cantidad(cantidad);
            m.setInt_proveedor(proveedor);
            m.setInt_tipoComprobante(tipoComprobante);
            m.setVar_numeroComprobante(numeroComprobante);
            m.setInt_tipoOperacion(tipoOperacion);
            m.setDat_fecregistro(fechaRegistro);
            resultado = new BDMovimiento().insertarMovimiento(m);
        } catch (Exception e) {
            System.out.println("error enviar al modelo movimiento : " + e.toString());
        }
        return resultado;
    }

    public ArrayList<ListaMovimiento> get_reporte_movimiento(String fechaDesde, String fechaHasta, int tipo_operacion) {
        ArrayList<ListaMovimiento> lista = new ArrayList<ListaMovimiento>();
        try {
            String condicion = "";
            if (tipo_operacion == -1) {
                condicion = "where dat_fecregistro between '" + fechaDesde + "' and '" + fechaHasta+"'";
            } else {
                condicion = "where (case " + tipo_operacion + " when 0 then int_tipoOperacion<>0 \n"
                        + "else int_tipoOperacion=" + tipo_operacion + " end) and (dat_fecregistro between '" + fechaDesde + "' and '" + fechaHasta+"' ) ";
            }
            lista = new BDMovimiento().get_reporte_movimiento(condicion);
        } catch (Exception e) {
            System.out.println("Error de Listado" + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }

}
