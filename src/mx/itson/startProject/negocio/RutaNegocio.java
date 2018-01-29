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
import mx.itson.startProject.entidades.Ruta;
import mx.itson.startProject.persistencia.RutaPersistencia;

/**
 *
 * @author Danielrodrigo
 */
public class RutaNegocio {
    
    public void agregarRuta(Ruta ruta){
        RutaPersistencia rp= new RutaPersistencia();
        if(rp.guardar(ruta)>0){
            JOptionPane.showMessageDialog(null, "La ruta se agregó correctamente");
        }else{
            JOptionPane.showMessageDialog(null, "Ocurrió un error al intenar agregar la ruta");
        }
    }
    
    public void editarRuta(Ruta ruta){
        RutaPersistencia rp= new RutaPersistencia();
        if(rp.editarRuta(ruta)){
            JOptionPane.showMessageDialog(null, "La ruta se modificó correctamente");
        }else{
            JOptionPane.showMessageDialog(null, "Ocurrió un error al intenar modificar la ruta");
        }
    }
    
    public List<Ruta> verRutas(){
        List<Ruta> socios= new ArrayList<>();
        RutaPersistencia rp = new RutaPersistencia();
        socios=rp.obtenerTodas();
        return socios;
    }
    
//    public void eliminarRuta(int id) throws SQLException{
//        RutaPersistencia rp= new RutaPersistencia();
//        if(rp.eliminarRuta(id)){
//            JOptionPane.showMessageDialog(null, "La ruta se agregó correctamente");
//        }else{
//            JOptionPane.showMessageDialog(null, "Ocurrió un error al intenar agregar la ruta");
//        }
//    }
}
