/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.itson.startProject.negocio;

import javax.swing.JOptionPane;
import mx.itson.startProject.entidades.Periodo;
import mx.itson.startProject.persistencia.PeriodoPersistencia;

/**
 *
 * @author Danielrodrigo
 */
public class PeriodoNegocio {
    
    public void agregarPeriodo(Periodo P){
        
        PeriodoPersistencia pp=new PeriodoPersistencia();
        
        if(pp.guardar(P)>0){
            JOptionPane.showMessageDialog(null, "Se agregó correctamente");
        }else{
            JOptionPane.showMessageDialog(null, "Ocurrió un problema");
        }
    }
    
    
}
