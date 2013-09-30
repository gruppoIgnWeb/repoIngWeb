
package it.univaq.meetingplan.model.impl;

import it.univaq.meetingplan.model.Gruppi;
import it.univaq.meetingplan.model.Servizi;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Charlie Bellesso,Carlos Bellesso,Stefano Maglione
 */
public class ServiziMysqlImpl implements Servizi{
    
    private int key;
    private String nome;
    private MeetingplanDataLayerMysqlImpl datalayer;

    ServiziMysqlImpl(MeetingplanDataLayerMysqlImpl datalayer) {
        key = 0;
        nome = "";
       this.datalayer = datalayer; 
    }

    ServiziMysqlImpl(MeetingplanDataLayerMysqlImpl datalayer, ResultSet data) throws SQLException {
        this.datalayer = datalayer;
        key = data.getInt("id");
        nome = data.getString("nome");
    }


    @Override
    public int getKey() {
        return key;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public List<Gruppi> getGruppi() {
        return datalayer.getGruppiByServizi(this);
    }
    
    
    
    
}
