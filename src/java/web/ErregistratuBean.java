/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dao.ErabiltzaileaDAO;
import dao.ErabiltzaileaDAOHibernate;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import model.Erabiltzailea;

/**
 *
 * @author txapasta
 */
@Named(value = "erregistratuBean")
@Dependent
public class ErregistratuBean {

    private String izena;
    private String erabIzena;
    private String pasahitza;
    private String jabeaBaiEz;
    private String kontua;

    public ErregistratuBean() {
    }

    public String getIzena() {
        return izena;
    }

    public void setIzena(String izena) {
        this.izena = izena;
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

    public String getJabeaBaiEz() {
        return jabeaBaiEz;
    }

    public void setJabeaBaiEz(String jabeaBaiEz) {
        this.jabeaBaiEz = jabeaBaiEz;
    }

    public String getKontua() {
        return kontua;
    }

    public void setKontua(String kontua) {
        this.kontua = kontua;
    }

    public void gorde() {
        if (jabeaBaiEz.compareTo("Ez") == 0) {//erabiltzaile arrunta edo turista
            ErabiltzaileaDAO erabiltzaileaDAO = new ErabiltzaileaDAOHibernate();
            erabiltzaileaDAO.gorde(new Erabiltzailea(erabIzena, izena, pasahitza, "erab", kontua));
        } else if (jabeaBaiEz.compareTo("Bai") == 0) {// jabea
            ErabiltzaileaDAO erabiltzaileaDAO = new ErabiltzaileaDAOHibernate();
            erabiltzaileaDAO.gorde(new Erabiltzailea(erabIzena, izena, pasahitza, "jabea", kontua));

        }
    }

}
