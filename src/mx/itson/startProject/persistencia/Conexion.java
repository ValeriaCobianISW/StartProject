/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.itson.startProject.persistencia;
import java.sql.*;

/**
 *
 * @author Danielrodrigo
 */
public class Conexion {
    
   
    static Connection conexion=null;
    public static Connection conexion(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion=DriverManager.getConnection("jdbc:mysql://localhost/starproject?user=root&password=140397");
        } catch (Exception e) {
            System.out.println("Ocurrió un error "+e.getMessage());
        }
        return conexion;
    }
}
