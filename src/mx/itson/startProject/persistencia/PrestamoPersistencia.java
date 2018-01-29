/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.itson.startProject.persistencia;

import java.text.DecimalFormat;
import java.util.List;
import mx.itson.startProject.entidades.Periodo;
import mx.itson.startProject.entidades.Prestamo;
import mx.itson.startProject.entidades.PrestamoPeriodo;
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
public class PrestamoPersistencia {

    public int guardar(Prestamo p) {
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

    public List<Prestamo> obtenerTodos() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        List<Prestamo> prestamos = (List<Prestamo>) session.createCriteria(Prestamo.class).list();
        return prestamos;
    }

    public List<Prestamo> obtenerFiltrados(boolean estado) {
        List<Prestamo> prestamos = null;
        try {
            Session sesion = HibernateUtils.getSessionFactory().openSession();
            Criteria criteria = sesion.createCriteria(Prestamo.class);
            criteria.add(Restrictions.eq("estado", estado));
            prestamos =criteria.list() ;
            sesion.close();
        } catch (HibernateException he) {
            System.err.println(he.getMessage());
        }
        return prestamos;
    }
    public Prestamo obtenerPorSocio(int idSocio) {
        Prestamo prestamo = new Prestamo();
        try {
            Session sesion = HibernateUtils.getSessionFactory().openSession();
            prestamo = (Prestamo) sesion.createQuery("from Prestamo p where p.socio.id="+idSocio +" and p.estado= 1").uniqueResult(); 
            sesion.close();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            prestamo= null;
        }
        return prestamo;
    }

    public void evaluarDescuento(int idSocio, double descuento, int idPeriodo) {
        Prestamo prestamo = new Prestamo();
        try {
            prestamo = obtenerPorSocio(idSocio);
            Session sesion = HibernateUtils.getSessionFactory().openSession();
            Transaction transaction = sesion.beginTransaction();
            if (prestamo.getResto() >= descuento) {
                DecimalFormat df = new DecimalFormat("00.00");
                double resto = prestamo.getResto() - descuento;
                prestamo.setResto(Double.parseDouble(df.format(resto)));
                if (prestamo.getResto() < 1) {
                    prestamo.setEstado(false);
                }
                PeriodoPersistencia pps = new PeriodoPersistencia();
                Periodo p = new Periodo();
                p = pps.obtenerPorId(idPeriodo);
                PrestamoPeriodo pp = new PrestamoPeriodo();
                pp.setPeriodo(p);
                pp.setPrestamo(prestamo);
                pp.setResto(prestamo.getResto());
                pp.setAbono(descuento);
                sesion.save(pp);
                sesion.update(prestamo);
                transaction.commit();
            }
            sesion.close();
        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }
    }
}
