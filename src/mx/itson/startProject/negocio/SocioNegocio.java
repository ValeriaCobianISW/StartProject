/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.itson.startProject.negocio;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import mx.itson.startProject.entidades.Socio;
import mx.itson.startProject.persistencia.SocioPersistencia;

/**
 *
 * @author Danielrodrigo
 */
public class SocioNegocio {
    
    
    public void agregarSocio(Socio socio){
        SocioPersistencia sp= new SocioPersistencia();
        if(sp.guardar(socio)>0){
            JOptionPane.showMessageDialog(null, "El socio se agregó correctamente");
        }else{
            JOptionPane.showMessageDialog(null, "Ocurrió un error al intenar agregar al socio");
        }
    }
    
    public void editarSocio(Socio socio){
        SocioPersistencia sp= new SocioPersistencia();
        if(sp.editarSocio(socio)){
            JOptionPane.showMessageDialog(null, "El socio se modificó correctamente");
        }else{
            JOptionPane.showMessageDialog(null, "Ocurrió un error al intenar agregar al socio");
        }
    }
    
    public List<Socio> verSocios(){
        List<Socio> socios= new ArrayList<>();
        SocioPersistencia sp = new SocioPersistencia();
        socios=sp.obtenerTodos();
        return socios;
    }
}
