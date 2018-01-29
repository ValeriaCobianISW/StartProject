/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.itson.startProject.persistencia;

import java.util.List;
import mx.itson.startProject.entidades.Socio;
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
public class SocioPersistencia {

    public int guardar(Socio s) {
        int id = 0;
        try {
            Session sesion = HibernateUtils.getSessionFactory().openSession();
            Transaction transaction = null;
            transaction = sesion.beginTransaction();
            sesion.save(s);
            id = s.getId();
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return id;
    }

    public boolean editarSocio(Socio s) {
        try {
            Session sesion = HibernateUtils.getSessionFactory().openSession();
            Transaction transaction = null;
            transaction = sesion.beginTransaction();
            sesion.update(s);
            transaction.commit();
            sesion.close();
            return true;
        } catch (Exception e) {
            {
                System.out.println(e.getMessage());
                return false;
            }
        }
    }

    public List<Socio> obtenerTodos() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        List<Socio> socios = (List<Socio>) session.createCriteria(Socio.class).list();
        return socios;
    }

//     public List<Socio> obtenerSociosSinPrestamo(int idSocio) {
//        List <Socio> socios = new ArrayList<Socio>();
//        try {
//            Session sesion = HibernateUtils.getSessionFactory().openSession();
//            Criteria criteria = sesion.createCriteria(Prestamo.class);
//            criteria.add(Restrictions.eq("idSocio", idSocio));
//            criteria.add(Restrictions.eq("estado", true));
//            prestamo = (Prestamo) criteria.uniqueResult();
//            sesion.close();
//        } catch (HibernateException e) {
//            System.out.println(e.getMessage());
//        }
//        return prestamo;
//    }
//
//    public List<Socio> obtenerSociosSinPrestamo(){
//        List<Integer> id = obtenerIdSocios();
//        List<Socio> socios = verSocios();
//        boolean esta = false;
//        List<Socio> sociosSinPrestamo = new ArrayList<>();
//        for (Socio s : socios) {
//            for (int i : id) {
//                if (i == s.getId()) {
//                    esta = true;
//                }
//            }
//            if (!esta) {
//                sociosSinPrestamo.add(s);
//            }
//            esta = false;
//        }
//        try {
//            conexion().close();
//        } catch (SQLException ex) {
//            Logger.getLogger(SocioPersistencia.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return sociosSinPrestamo;
//    }
    public void desmarcarSocio(int id) {
        try {
            Session sesion = HibernateUtils.getSessionFactory().openSession();
            Transaction transaction = sesion.beginTransaction();
            Criteria criteria = sesion.createCriteria(Socio.class);
            criteria.add(Restrictions.eq("id", id));
            Socio s = (Socio) criteria.uniqueResult();
            s.setEstado(false);
            sesion.update(s);
            transaction.commit();
            sesion.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public List<Socio> obtenerMarcados() {
        Session sesion = HibernateUtils.getSessionFactory().openSession();
        Criteria criteria = sesion.createCriteria(Socio.class);
        criteria.add(Restrictions.eq("estado", true));
        List<Socio> s = (List<Socio>)criteria.list();
        return s;
    }
    
    public void desmarcarTodos(){
        List<Socio> socios= obtenerMarcados();
        for(Socio s : socios){
            s.setEstado(false);
            editarSocio(s);
        }
    }

    public Socio obtenerPorId(int id) {
        Socio s = new Socio();
        try {
            Session sesion = HibernateUtils.getSessionFactory().openSession();
            Criteria criteria = sesion.createCriteria(Socio.class);
            criteria.add(Restrictions.eq("id", id));
            s = (Socio) criteria.uniqueResult();
            sesion.close();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return s;
    }
}
