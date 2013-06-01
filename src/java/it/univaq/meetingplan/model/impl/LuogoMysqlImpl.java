/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.univaq.meetingplan.model.impl;

import it.univaq.meetingplan.model.Luogo;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Felpone
 */
public class LuogoMysqlImpl implements Luogo{
    
    
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
    
    
    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public int getCapienza() {
        return capienza;
    }

    public void setCapienza(int capienza) {
        this.capienza = capienza;
    }
    
    
}
