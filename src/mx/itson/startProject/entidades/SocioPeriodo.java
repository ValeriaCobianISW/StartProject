/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.itson.startProject.entidades;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Danielrodrigo
 */

@Entity
@Table(name="socioperiodo")
public class SocioPeriodo {

    private int id;
    private Socio socio;
    private Periodo periodo;
    private String rutas;
    private double ingreso;
    private double gastoCombustible;
    private double gastoSeguro;
    private double gastoMantenimiento;
    private double descuento;
    private double total;
    private double impuesto;

    
    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    public String getRutas() {
        return rutas;
    }

    public void setRutas(String rutas) {
        this.rutas = rutas;
    }

    @Basic
    public double getIngreso() {
        return ingreso;
    }

    public void setIngreso(double ingreso) {
        this.ingreso = ingreso;
    }

    @Basic
    public double getGastoCombustible() {
        return gastoCombustible;
    }
    

    public void setGastoCombustible(double gastoCombustible) {
        this.gastoCombustible = gastoCombustible;
    }

    @Basic
    public double getGastoSeguro() {
        return gastoSeguro;
    }

    public void setGastoSeguro(double gastoSeguro) {
        this.gastoSeguro = gastoSeguro;
    }

    @Basic
    public double getGastoMantenimiento() {
        return gastoMantenimiento;
    }

    public void setGastoMantenimiento(double gastoMantenimiento) {
        this.gastoMantenimiento = gastoMantenimiento;
    }

    @Basic
    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    @Basic
    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @OneToOne (cascade= CascadeType.ALL)
    @JoinColumn(name = "idSocio")
    public Socio getSocio() {
        return socio;
    }

    public void setSocio(Socio socio) {
        this.socio = socio;
    }

    @OneToOne (cascade= CascadeType.ALL)
    @JoinColumn(name = "idPeriodo")
    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    @Basic
    public double getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(double impuesto) {
        this.impuesto = impuesto;
    }

}
