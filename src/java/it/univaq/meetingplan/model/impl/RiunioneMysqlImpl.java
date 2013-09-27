
package it.univaq.meetingplan.model.impl;

import it.univaq.meetingplan.model.Luogo;
import it.univaq.meetingplan.model.Opzioni_riunioni;
import java.sql.ResultSet;
import java.sql.SQLException;
import it.univaq.meetingplan.model.Riunione;
import it.univaq.meetingplan.model.Utente;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
/**
 *
 * @author Charlie Bellesso,Carlos Bellesso,Stefano Maglione
 */
public class RiunioneMysqlImpl implements Riunione {
    
    
    private int key;
    private String descrizione;
    private int creatore_key;
    private Luogo luogo;
  //  private Timestamp data;
    private String conferma_flag;
    private Utente creatore;
    private MeetingplanDataLayerMysqlImpl datalayer;
    
    
    
    
     RiunioneMysqlImpl(MeetingplanDataLayerMysqlImpl datalayer){
    
    
    key=0;
    descrizione="";
    creatore_key=0;
    luogo=null;
   // data.getTime();
    conferma_flag="";
    creatore=null;
    this.datalayer=datalayer;
    
    
    }
    
    
    
    
     RiunioneMysqlImpl(MeetingplanDataLayerMysqlImpl datalayer, ResultSet data) throws SQLException{
    
    key=data.getInt("id");
    descrizione=data.getString("descrizione");
    creatore_key=data.getInt("creatore");
    //luogo=data.getString("luogo");
   //  this.data=data.getString("data");
    conferma_flag=data.getString("conferma_flag");
    creatore=null;
    this.datalayer=datalayer;
    
    
    }
     
     
     
     
    @Override
    public int getKey() {
    return key;
    }
    
    
    @Override
    public String getDescrizione(){
    return descrizione;
    }
    
    
    
    /**
     *
     */
    @Override
    public void setDescrizione(String descrizione){
    this.descrizione=descrizione;
    }
    
    
    @Override
    
    public Utente getCreatore(){//dubbio
     if (creatore == null) {
            creatore = datalayer.getUtente(creatore_key);
        }
        return creatore;
    }
    
    @Override
    public void setCreatore(Utente organizzatore){//dubbio
    this.creatore_key=organizzatore.getKey();
    
    }
    
    
    @Override
    public Luogo getLuogo(){
    
    if(luogo==null){
    luogo=datalayer.getLuogoByRiunione(this);
    }
        return luogo;
    };
    
    
    
    
    @Override
    public List<Opzioni_riunioni> getOpzioni_riunioni(){
        return null;
    
    
    }

    @Override
    public void setLuogo(Luogo luogo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Calendar getData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setData(Calendar data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
