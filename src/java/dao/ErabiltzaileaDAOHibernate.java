package dao;

import java.util.List;
import model.Erabiltzailea;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateUtil;

/**
 *
 * @author txapasta
 */
public class ErabiltzaileaDAOHibernate implements ErabiltzaileaDAO {

    private Session session = HibernateUtil.getSessionFactory().getCurrentSession();

    @Override
    public void gorde(Erabiltzailea erab) {
        try {
            session.beginTransaction();
            session.save(erab);
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    @Override
    public void ezabatu(Erabiltzailea erab) {
        try {
            session.beginTransaction();
            session.delete(erab);
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    @Override
    public boolean egiaztatuErabiltzailea(String erabIzena, String pasahitza) {
        try {
            session.beginTransaction();
            String hql = "from Erabiltzailea erabiltzailea where erabiltzailea.erabIzena = ? and erabiltzailea.pasahitza = ?";
            Query kontsulta = session.createQuery(hql).setParameter(0, erabIzena).setParameter(1, pasahitza);
            /*
             * return (Erabiltzailea) kontsulta.uniqueResult();
             */
            List<Erabiltzailea> lista = kontsulta.list();
            session.getTransaction().commit();
            if (lista.isEmpty()) {
                return false;
            } else {
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
            return false;
        }
    }

    @Override
    public Erabiltzailea getErabiltzaileaByErabIzena(String erabIzena) {
        try {
            session = this.session.getSessionFactory().openSession();//????
            session.beginTransaction();
            Erabiltzailea e = (Erabiltzailea) session.get(Erabiltzailea.class, erabIzena);
            session.getTransaction().commit();
            return e;
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
            return new Erabiltzailea();
        }
    }

    @Override
    public void editatu(Erabiltzailea erab) {
        try {
            session.beginTransaction();
            session.update(erab);
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    @Override
    public String saioaItxi() {
        session.close();
        return "index";
    }

}
