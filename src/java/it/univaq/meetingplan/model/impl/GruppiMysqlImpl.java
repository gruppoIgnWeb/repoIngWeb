
package it.univaq.meetingplan.model.impl;
import it.univaq.meetingplan.model.Gruppi;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author Stefano Maglione
 */
public class GruppiMysqlImpl implements Gruppi {
    
    
    private int key;
    private String tipo;
    private MeetingplanDataLayerMysqlImpl datalayer;
    
    
    
     GruppiMysqlImpl(MeetingplanDataLayerMysqlImpl datalayer) {
        key = 0;
        tipo = "";
        this.datalayer = datalayer;
    }

  
    GruppiMysqlImpl(MeetingplanDataLayerMysqlImpl datalayer, ResultSet data) throws SQLException {
        this.datalayer = datalayer;
        key = data.getInt("id");
        tipo = data.getString("tipo");
        
    }

    public int getKey() {
        return key;
    }

  
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
    
}
