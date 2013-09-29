
package it.univaq.meetingplan.model.impl;
import it.univaq.meetingplan.model.Gruppi;
import it.univaq.meetingplan.model.Servizi;
import it.univaq.meetingplan.model.Utente;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author Charlie Bellesso,Carlos Bellesso,Stefano Maglione
 */
public class GruppiMysqlImpl implements Gruppi {
    
    
    private int key;
    private String tipo;
    private MeetingplanDataLayerMysqlImpl datalayer;
    private List<Servizi> servizi;
    
    
     GruppiMysqlImpl(MeetingplanDataLayerMysqlImpl datalayer) {
        key = 0;
        tipo = "";
        servizi=null;
        this.datalayer = datalayer;
    }

  
    GruppiMysqlImpl(MeetingplanDataLayerMysqlImpl datalayer, ResultSet data) throws SQLException {
        this.datalayer = datalayer;
        key = data.getInt("id");
        tipo = data.getString("tipo");
        servizi=null;
    }

    @Override
    public int getKey() {
        return key;
    }

  
    @Override
    public String getTipo() {
        return tipo;
    }

    @Override
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public List<Servizi> getServizi() {
        if(servizi == null)
        {
            servizi=datalayer.getServiziByGruppo(this);
        }
        
        return servizi;
    }

    @Override
    public List<Utente> getUtenti() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
