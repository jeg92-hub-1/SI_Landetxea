package dao;

import model.Erabiltzailea;

public interface ErabiltzaileaDAO {

    public void gorde(Erabiltzailea erab);

    public void ezabatu(Erabiltzailea erab);

    public void editatu(Erabiltzailea erab);
    
    public boolean egiaztatuErabiltzailea(String erabIzena, String pasahitza);

    public Erabiltzailea getErabiltzaileaByErabIzena(String erabIzena);

    public String saioaItxi();
}
