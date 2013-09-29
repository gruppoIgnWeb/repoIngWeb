package it.univaq.meetingplan.model.impl;

import it.univaq.meetingplan.model.Luogo;
import it.univaq.meetingplan.model.Opzioni_riunioni;
import it.univaq.meetingplan.model.Riunione;
import it.univaq.meetingplan.model.Utente;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author Charlie Bellesso,Carlos Bellesso,Stefano Maglione
 */
public class RiunioneMysqlImpl implements Riunione {

    private int key;
    private String descrizione;
    private Luogo luogo;
 //   private Timestamp data;
    private String conferma_flag;
    private Utente creatore;
    private List<Opzioni_riunioni> opzioni_riunioni;
    private MeetingplanDataLayerMysqlImpl datalayer;
    
    RiunioneMysqlImpl(MeetingplanDataLayerMysqlImpl datalayer) {


        key = 0;
        descrizione = "";
        luogo = null;
      //  data = null;
        conferma_flag = "";
        creatore = null;
        opzioni_riunioni=null;
        this.datalayer = datalayer;


    }

    RiunioneMysqlImpl(MeetingplanDataLayerMysqlImpl datalayer, ResultSet data) throws SQLException {

        key = data.getInt("id");
        descrizione = data.getString("descrizione");
     //   this.data = data.getTimestamp("data");
        conferma_flag = data.getString("conferma_flag");
        creatore = null;
        opzioni_riunioni=null;
        this.datalayer = datalayer;


    }

    @Override
    public int getKey() {
        return key;
    }

    @Override
    public String getDescrizione() {
        return descrizione;
    }

    /**
     *
     */
    @Override
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    @Override
    public Utente getCreatore() {
        return creatore;
    }

    @Override
    public void setCreatore(Utente organizzatore) {
        if (creatore == null) {
            this.creatore = datalayer.getUtenteByRiunioni(this);
        }
        this.creatore = organizzatore;
    }

    @Override
    public Luogo getLuogo() {// test ok

        if (luogo == null) {
            luogo = datalayer.getLuogoByRiunione(this);
        }
        return luogo;
    }

    @Override
    public List<Opzioni_riunioni> getOpzioni_riunioni() {
         if (opzioni_riunioni == null) {
            opzioni_riunioni = datalayer.getOpzioni_riunioniByRiunione(this);
        }
        return opzioni_riunioni;
    }

    @Override
    public void setLuogo(Luogo luogo) {
        this.luogo = luogo;
    }

    @Override
     public Timestamp getData() {
      //  return this.data;
         return null;
    }

   @Override
   public void setData(Timestamp data) {
       // this.data = data;
    }
}
