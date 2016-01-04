
package web;

import dao.LandetxeaDAO;
import dao.LandetxeaDAOHibernate;
import java.util.Set;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.context.FacesContext;
import model.Landetxea;

/**
 *
 * @author txapasta
 */
@Named(value = "landetxeaBean")
@Dependent
public class LandetxeaBean {

    private int id;
    private String izena;
    private String helbidea;
    private String deskribapena;
    private Landetxea landetxea;

    public LandetxeaBean() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIzena() {
        return izena;
    }

    public void setIzena(String izena) {
        this.izena = izena;
    }

    public String getHelbidea() {
        return helbidea;
    }

    public void setHelbidea(String helbidea) {
        this.helbidea = helbidea;
    }

    public String getDeskribapena() {
        return deskribapena;
    }

    public void setDeskribapena(String deskribapena) {
        this.deskribapena = deskribapena;
    }

    public void gordeLandetxea() {
        LandetxeaDAO landetxeaDAO = new LandetxeaDAOHibernate();
        Set<Landetxea> landetxeak=landetxeaDAO.getLandetxeak();
        id= landetxeak.size() + 1;
        
        String erabIzena = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("erabIzena");
        Landetxea land = new Landetxea(id, izena, helbidea, deskribapena, erabIzena);
        landetxeaDAO.gorde(land);

    }

    public void setLandetxea(Landetxea landetxea) {
        this.landetxea = landetxea;
    }

    public void landetxeaBilatu() {
        LandetxeaDAO landetxeaDAO = new LandetxeaDAOHibernate();
        setLandetxea(landetxeaDAO.landetxeaBilatu(izena));
    }

    public Set<Landetxea> getLandetxeak() {
        LandetxeaDAO landetxeaDAO = new LandetxeaDAOHibernate();
        return landetxeaDAO.getLandetxeak();
    }

    public void removeLandetxea() {
        LandetxeaDAO landetxeaDAO = new LandetxeaDAOHibernate();
        landetxeaDAO.ezabatu(izena);
    }

}
