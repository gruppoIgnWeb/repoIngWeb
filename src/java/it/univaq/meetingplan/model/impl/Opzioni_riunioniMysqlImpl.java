
package it.univaq.meetingplan.model.impl;

import it.univaq.meetingplan.model.Luogo;
import it.univaq.meetingplan.model.Opzioni_riunioni;
import it.univaq.meetingplan.model.Riunione;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;



/**
 *
 * @author Charlie Bellesso,Carlos Bellesso,Stefano Maglione
 */
public class Opzioni_riunioniMysqlImpl implements Opzioni_riunioni{
    
    private int key;
    Timestamp orario;
    Riunione riunione;
    Luogo luogo;
    
    Opzioni_riunioniMysqlImpl(MeetingplanDataLayerMysqlImpl datalayer){
        
        key = 0;
        orario = null;
        this.riunione = null;
        this.luogo = null;
        
    }
    Opzioni_riunioniMysqlImpl(MeetingplanDataLayerMysqlImpl datalayer, ResultSet rs) throws SQLException{
        
        key = rs.getInt("id");
        orario = rs.getTimestamp("data");
        this.riunione = null;
        this.luogo = null;
        
    }

    @Override
    public int getKey() {
        return key;
    }

    @Override
    public Timestamp getData() {
        return orario;
    }

    @Override
    public void setData(Timestamp data) {
        this.orario = data;
    }

    @Override
    public Luogo getLuogo() {
        return this.luogo;
    }

    @Override
    public void setLuogo(Luogo luogo) {
        this.luogo = luogo;
    }

    @Override
    public Riunione getRiunione() {
        return this.riunione;
    }

    @Override
    public void setRiunione(Riunione riunione) {
        this.riunione = riunione;
    }
    
}
