
package it.univaq.meetingplan.model.impl;

import it.univaq.meetingplan.model.Luogo;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Charlie Bellesso,Carlos Bellesso,Stefano Maglione
 */
public class LuogoMysqlImpl  implements Luogo{
    
    
    private int key;
    private int tipo; //   sala riunione --> 0, altro --> 1 *ancora da vedere*
    private String nome;
    private String indirizzo;
    private int capienza;
    private MeetingplanDataLayerMysqlImpl datalayer;
    
    
     LuogoMysqlImpl(MeetingplanDataLayerMysqlImpl datalayer) {
        key = 0;
        tipo = 0;
        nome = "";
        indirizzo = "";
        capienza = 0;
        this.datalayer = datalayer;
    }
    
     
    LuogoMysqlImpl(MeetingplanDataLayerMysqlImpl datalayer, ResultSet data) throws SQLException {
        this.datalayer = datalayer;
        key = data.getInt("id");
        tipo = data.getInt("tipo");
        nome = data.getString("nome");
        indirizzo = data.getString("indirizzo");
        capienza = data.getInt("capienza");
    }
    
    
    @Override
    public int getKey() {
        return key;
    }


    @Override
    public int getTipo() {
        return tipo;
    }

    @Override
    public void setTipo(int tipo) {
        this.tipo = tipo;
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
    public String getIndirizzo() {
        return indirizzo;
    }

    @Override
    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    @Override
    public int getCapienza() {
        return capienza;
    }

    @Override
    public void setCapienza(int capienza) {
        this.capienza = capienza;
    }
    
    
}
