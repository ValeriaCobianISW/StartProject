/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.itson.startProject.persistencia;

import java.util.List;
import mx.itson.startProject.entidades.Periodo;
import mx.itson.startProject.utils.HibernateUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Danielrodrigo
 */
public class PeriodoPersistencia {

    public int guardar(Periodo p) {
        int id = 0;
        try {
            Session sesion = HibernateUtils.getSessionFactory().openSession();
            Transaction transaction = null;
            transaction = sesion.beginTransaction();
            sesion.save(p);
            id = p.getId();
            transaction.commit();
        } catch (Exception e) {
            {
                System.out.println(e.getMessage());
            }
        }
        return id;
    }

    public List<Periodo> obtenerTodos() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        List<Periodo> periodos = (List<Periodo>) session.createCriteria(Periodo.class).list();
        return periodos;
    }

    public Periodo obtenerPorId(int idPeriodo) {
        Periodo p = new Periodo();
        try {
            Session sesion = HibernateUtils.getSessionFactory().openSession();
            Criteria criteria = sesion.createCriteria(Periodo.class);
            criteria.add(Restrictions.eq("id", idPeriodo));
            p = (Periodo) criteria.uniqueResult();
            sesion.close();
        } catch (HibernateException he) {
            System.err.println(he.getMessage());
        }
        return p;
    }
    
     public List<Periodo> obtenerPeriodosFiltrados(String estado) {
        List<Periodo> periodos = null;
        try {
            Session sesion = HibernateUtils.getSessionFactory().openSession();
            Criteria criteria = sesion.createCriteria(Periodo.class);
            criteria.add(Restrictions.eq("estado", estado));
            periodos =criteria.list() ;
            sesion.close();
        } catch (HibernateException he) {
            System.err.println(he.getMessage());
        }
        return periodos;
    }

    public void cambiarEstado(Periodo p) {
        try {
            Session sesion = HibernateUtils.getSessionFactory().openSession();
            Transaction transaction = null;
            transaction = sesion.beginTransaction();
            sesion.update(p);
            transaction.commit();
        } catch (Exception e) {
            {
                System.out.println(e.getMessage());
            }
        }
    }

}
