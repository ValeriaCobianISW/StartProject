/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.itson.startProject.negocio;

import javax.swing.JOptionPane;
import mx.itson.startProject.entidades.SocioPeriodo;
import mx.itson.startProject.persistencia.SocioPeriodoPersistencia;

/**
 *
 * @author Danielrodrigo
 */
public class SocioPeriodoNegocio {
    
    
     public void agregarSocio(SocioPeriodo socioP){
        SocioPeriodoPersistencia spp= new SocioPeriodoPersistencia();
        if(spp.guardar(socioP)>0){
            JOptionPane.showMessageDialog(null, "El socio se agregó correctamente");
        }else{
            JOptionPane.showMessageDialog(null, "Ocurrió un error al intenar agregar al socio");
        }
    }
    
    
}
