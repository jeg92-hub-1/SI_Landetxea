package dao;

import java.util.List;
import java.util.Set;
import model.Landetxea;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateUtil;

/**
 *
 * @author txapasta
 */
public class LandetxeaDAOHibernate implements LandetxeaDAO {

    private Session session = HibernateUtil.getSessionFactory().getCurrentSession();

    @Override
    public void gorde(Landetxea landetxea) {
        try {
            session.beginTransaction();
            session.save(landetxea);
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    @Override
    public void ezabatu(String izena) {
        try {
            session.beginTransaction();
            String hql = "DELETE FROM Landetxea WEHRE izena='" + izena + "';";
            session.createQuery(hql);
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    @Override
    public Landetxea landetxeaBilatu(String landaIzena) {
        try {
            session.beginTransaction();
            String hql = "from Landetxea land where land.Izena =? ";
            Query kontsulta = session.createQuery(hql).setParameter(0, landaIzena);
            List<Landetxea> lista = kontsulta.list();
            session.getTransaction().commit();
            if(lista.isEmpty()){
                return null;
            }else{
                return lista.get(0);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
            return null;
        }

    }

    @Override
    public Set<Landetxea> getLandetxeak() {
         try {
            session.beginTransaction();
            String hql = "from Landetxea ";
            Query kontsulta = session.createQuery(hql);
            Set<Landetxea> lista = (Set<Landetxea>) kontsulta.list();
            session.getTransaction().commit();
            if(lista.isEmpty()){
                return null;
            }else{
                return lista;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
            return null;
        }

    }

}
