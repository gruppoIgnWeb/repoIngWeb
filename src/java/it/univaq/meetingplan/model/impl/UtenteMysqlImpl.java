
package it.univaq.meetingplan.model.impl;

import it.univaq.meetingplan.model.Gruppi;
import it.univaq.meetingplan.model.Utente;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;







public class UtenteMysqlImpl implements Utente {
    
    
    private int key;
    private String nome;
    private String cognome;
    private String username;
    private String mail;
    private MeetingplanDataLayerMysqlImpl datalayer;
    
    
    
     UtenteMysqlImpl(MeetingplanDataLayerMysqlImpl datalayer) {
        key = 0;
        username = "";
        nome="";
        cognome="";
        mail="";
        this.datalayer = datalayer;
    }

  
    UtenteMysqlImpl(MeetingplanDataLayerMysqlImpl datalayer, ResultSet data) throws SQLException {
        this.datalayer = datalayer;
        key = data.getInt("id");
        username = data.getString("username");
        nome=data.getString("nome");
        cognome=data.getString("cognome");
        mail=data.getString("mail");
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
    public String getCognome() {
        return cognome;
    }

    @Override
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getMail() {
        return mail;
    }

    @Override
    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public List<Gruppi> getGruppi() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
    
    
    
}
