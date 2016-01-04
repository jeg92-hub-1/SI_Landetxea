package web;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import dao.ErabiltzaileaDAO;
import dao.ErabiltzaileaDAOHibernate;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpSession;
import model.Erabiltzailea;
import org.primefaces.context.RequestContext;

/**
 *
 * @author txapasta
 */
@Named(value = "loginBean")
@ViewScoped
public class LoginBean implements Serializable {

    private String erabIzena;
    private String pasahitza;
    private Boolean logeatua;
    private Erabiltzailea erabiltzailea;

    public LoginBean() {
    }

    public String getErabIzena() {
        return erabIzena;
    }

    public void setErabIzena(String erabIzena) {
        this.erabIzena = erabIzena;
    }

    public String getPasahitza() {
        return pasahitza;
    }

    public void setPasahitza(String pasahitza) {
        this.pasahitza = pasahitza;
    }

    public Boolean getLogeatua() {
        return logeatua;
    }

    public void setLogeatua(Boolean logeatua) {
        this.logeatua = logeatua;
    }

    public Erabiltzailea getErabiltzailea() {
        return erabiltzailea;
    }

    public void setErabiltzailea(Erabiltzailea erabiltzailea) {
        this.erabiltzailea = erabiltzailea;
    }

    public void login() {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage msg = null;
        String page = null;

        if (erabIzena.equals("admin") && pasahitza.equals("admin")) {
            logeatua = true;
            page = "administraria.xhtml";
        } else if (erabIzena.equals("jabea") && pasahitza.equals("jabea")) {
            logeatua = true;
            page = "jabea.xhtml";
        } else if (erabIzena.equals("user") && pasahitza.equals("user")) {
            logeatua = true;
            page = "erabiltzailea.xhtml";
        } else {
            logeatua = false;
        }

        context.addCallbackParam("logeatuDa", logeatua);
        if (logeatua) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("erabIzena", erabIzena);
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ongi etorri", erabIzena);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            context.addCallbackParam("view", page);
        }

        /*RequestContext context = RequestContext.getCurrentInstance();
         FacesMessage msg = null;
         String page = null;

         ErabiltzaileaDAO erab = new ErabiltzaileaDAOHibernate();
         logeatua = erab.egiaztatuErabiltzailea(erabIzena, pasahitza);

         if (logeatua) {
         erabiltzailea = erab.getErabiltzaileaByErabIzena(erabIzena);
         rola = e.getRola();
         msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ongi etorri", erabIzena);
         if (rola.equalsIgnoreCase("admin")) {
         page = "administraria.xhtml";
         } else {
         page = "erabiltzailea.xhtml";
         }
         context.addCallbackParam("view", page);

         } else {
         msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error",
         "Credenciales no válidas");
         }

         FacesContext.getCurrentInstance().addMessage(null, msg);
         context.addCallbackParam("logeatuDa", logeatua);*/
    }

    public void logout() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        session.invalidate();
        logeatua = false;
    }
}
