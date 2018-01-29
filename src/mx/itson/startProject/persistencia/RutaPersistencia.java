/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.itson.startProject.persistencia;

import java.util.List;
import mx.itson.startProject.entidades.Ruta;
import mx.itson.startProject.utils.HibernateUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Danielrodrigo
 */
public class RutaPersistencia {

    public int guardar(Ruta r) {
        int id = 0;
        try {
            Session sesion = HibernateUtils.getSessionFactory().openSession();
            Transaction transaction = null;
            transaction = sesion.beginTransaction();
            sesion.save(r);
            id = r.getId();
            transaction.commit();
        } catch (Exception e) {
            {
                System.out.println(e.getMessage());
            }
        }
        return id;
    }

    public List<Ruta> obtenerTodas() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        List<Ruta> rutas = (List<Ruta>) session.createCriteria(Ruta.class).list();
        return rutas;
    }

    public Ruta obtenerPorId(int id) {
        Ruta r = new Ruta();
        try {
            Session sesion = HibernateUtils.getSessionFactory().openSession();
            Criteria criteria = sesion.createCriteria(Ruta.class);
            criteria.add(Restrictions.eq("id", id));
            r = (Ruta) criteria.uniqueResult();
            sesion.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return r;
    }
    
    public Ruta obtenerPorNombre(String nombre) {
        Ruta r = new Ruta();
        try {
            Session sesion = HibernateUtils.getSessionFactory().openSession();
            Criteria criteria = sesion.createCriteria(Ruta.class);
            criteria.add(Restrictions.eq("nombre", nombre));
            r = (Ruta) criteria.uniqueResult();
            sesion.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return r;
    }

    public List<Ruta> obtenerMarcadas() {
        Session sesion = HibernateUtils.getSessionFactory().openSession();
        Criteria criteria = sesion.createCriteria(Ruta.class);
        criteria.add(Restrictions.eq("estado", true));
        List<Ruta> r = (List<Ruta>) criteria.list();
        return r;
    }

    public void desmarcarTodas() {
        List<Ruta> rutas = obtenerMarcadas();
        for (Ruta r : rutas) {
            r.setEstado(false);
            editarRuta(r);
        }
    }

    public void desmarcarRuta(int id) {
        Ruta r = obtenerPorId(id);
        r.setEstado(false);
        editarRuta(r);
    }

    public boolean editarRuta(Ruta r) {
        try {
            Session sesion = HibernateUtils.getSessionFactory().openSession();
            Transaction transaction = null;
            transaction = sesion.beginTransaction();
            sesion.update(r);
            transaction.commit();
            return true;
        } catch (Exception e) {
            {
                System.out.println(e.getMessage());
                return false;
            }
        }
    }
}