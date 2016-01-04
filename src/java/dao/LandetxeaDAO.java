
package dao;

import java.util.Set;
import model.Landetxea;

/**
 *
 * @author txapasta
 */
public interface LandetxeaDAO {
    public void gorde(Landetxea landetxea);
    public void ezabatu(String izena);
    public Landetxea landetxeaBilatu(String landaIzena);
    public Set<Landetxea> getLandetxeak();
}
