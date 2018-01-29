/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.itson.startProject.persistencia;

import java.util.List;
import mx.itson.startProject.entidades.SocioPeriodo;
import mx.itson.startProject.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Danielrodrigo
 */
public class SocioPeriodoPersistencia {

   public int guardar(SocioPeriodo sp) {
        int id = 0;
        try {
            Session sesion = HibernateUtils.getSessionFactory().openSession();
            Transaction transaction = null;
            transaction = sesion.beginTransaction();
            sesion.save(sp);
            id = sp.getId();
            transaction.commit();
        } catch (Exception e) {
            {
                System.out.println(e.getMessage());
            }
        }
        return id;
    }

    public List<SocioPeriodo> obtenerTodas() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        List<SocioPeriodo> listPeriodo= (List<SocioPeriodo>) session.createCriteria(SocioPeriodo.class).list();
        return listPeriodo;
    }

    public List<SocioPeriodo> obtenerCapturados(int idPeriodo){
        List <SocioPeriodo> socios=null;
         try {
            Session sesion = HibernateUtils.getSessionFactory().openSession();
            socios = (List<SocioPeriodo>) sesion.createQuery("from SocioPeriodo sp where sp.periodo.id="+idPeriodo).list(); 
            sesion.close();
        } catch (Exception e) {
             System.err.println(e.getMessage());
        }
        return socios;
    }
   
}
