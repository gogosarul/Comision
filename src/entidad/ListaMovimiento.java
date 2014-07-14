/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

import java.math.BigDecimal;
import java.sql.Date;

/**
 *
 * @author Richard
 */
public class ListaMovimiento {

    private Date dat_fecregistro;
    private String TipoComprobante;
    private int int_tipoOperacion;
    private String var_numeroComprobante;
    private int int_proveedor;
    private double dec_cantidad;
    private String var_concepto;
    private int usuario_id;
    private BigDecimal dec_ingreso;
    private BigDecimal dec_egreso;
    private int tipo;
    private BigDecimal dec_saldo;

    public Date getDat_fecregistro() {
        return dat_fecregistro;
    }

    public void setDat_fecregistro(Date dat_fecregistro) {
        this.dat_fecregistro = dat_fecregistro;
    }

    public String getTipoComprobante() {
        return TipoComprobante;
    }

    public void setTipoComprobante(String TipoComprobante) {
        this.TipoComprobante = TipoComprobante;
    }

    public int getInt_tipoOperacion() {
        return int_tipoOperacion;
    }

    public void setInt_tipoOperacion(int int_tipoOperacion) {
        this.int_tipoOperacion = int_tipoOperacion;
    }

    public String getVar_numeroComprobante() {
        return var_numeroComprobante;
    }

    public void setVar_numeroComprobante(String var_numeroComprobante) {
        this.var_numeroComprobante = var_numeroComprobante;
    }

    public int getInt_proveedor() {
        return int_proveedor;
    }

    public void setInt_proveedor(int int_proveedor) {
        this.int_proveedor = int_proveedor;
    }

    public double getDec_cantidad() {
        return dec_cantidad;
    }

    public void setDec_cantidad(double dec_cantidad) {
        this.dec_cantidad = dec_cantidad;
    }

    public String getVar_concepto() {
        return var_concepto;
    }

    public void setVar_concepto(String var_concepto) {
        this.var_concepto = var_concepto;
    }

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    public BigDecimal getDec_ingreso() {
        return dec_ingreso;
    }

    public void setDec_ingreso(BigDecimal dec_ingreso) {
        this.dec_ingreso = dec_ingreso;
    }

    public BigDecimal getDec_egreso() {
        return dec_egreso;
    }

    public void setDec_egreso(BigDecimal dec_egreso) {
        this.dec_egreso = dec_egreso;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getDec_saldo() {
        return dec_saldo;
    }

    public void setDec_saldo(BigDecimal dec_saldo) {
        this.dec_saldo = dec_saldo;
    }

}
