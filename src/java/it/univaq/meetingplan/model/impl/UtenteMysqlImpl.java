/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.univaq.meetingplan.model.impl;

import it.univaq.meetingplan.model.Utente;
import java.sql.ResultSet;
import java.sql.SQLException;







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

    public int getKey() {
        return key;
    }

  
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
    
    
    
    
    
    
    
    
}
