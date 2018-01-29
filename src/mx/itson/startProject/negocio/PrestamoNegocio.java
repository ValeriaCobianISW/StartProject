/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.itson.startProject.negocio;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import mx.itson.startProject.entidades.Prestamo;
import mx.itson.startProject.persistencia.PrestamoPersistencia;

/**
 *
 * @author Danielrodrigo
 */
public class PrestamoNegocio {
    
    public void agregarPrestamo(Prestamo p){
        PrestamoPersistencia pp= new PrestamoPersistencia();
        if(pp.guardar(p)>0){
            JOptionPane.showMessageDialog(null,"El prestamo se agrego correctamente");
        }else{
            JOptionPane.showMessageDialog(null, "Ocurrió un problema al intentar agregar el préstamo");
        }
    }
    
    public List<Prestamo> verPrestamos(){
        List<Prestamo> prestamos= new ArrayList<>();
        PrestamoPersistencia pp = new PrestamoPersistencia();
        prestamos=pp.obtenerTodos();
        return prestamos;
    }
}
