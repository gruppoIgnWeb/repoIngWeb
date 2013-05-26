/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.univaq.meetingplan.model.impl;

import it.univaq.meetingplan.model.Utente;
import it.univaq.meetingplan.model.Servizi;
import it.univaq.meetingplan.model.Riunione;
import it.univaq.meetingplan.model.MeetingplanDataLayer;
import it.univaq.meetingplan.model.Opzioni_riunioni;
import it.univaq.meetingplan.model.Luogo;
import it.univaq.meetingplan.model.Gruppi;

import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Charlie Bellesso,Carlos Bellesso,Stefano Maglione
 */
public class MeetingplanDataLayerMysqlImpl implements MeetingplanDataLayer {
    
    private PreparedStatement gUtente,aUtente,uUtente,dUtente;
    
    public MeetingplanDataLayerMysqlImpl(Connection connection) throws SQLException {

      
        gUtente = connection.prepareStatement("SELECT * FROM utente WHERE id=?");
        aUtente = connection.prepareStatement("INSERT INTO utente (nome,cognome,mail,username) VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
        uUtente = connection.prepareStatement("UPDATE utente SET nome=?,cognome=?,mail=?,username=? WHERE id=?");
        dUtente = connection.prepareStatement("DELETE FROM utente WHERE id=?");
    }
    
    public Utente createUtente() {
        return new UtenteMysqlImpl(this);
    }
    
     public Utente getUtente(int key) {
        Utente result = null;
        ResultSet rs = null;
        try {
            gUtente.setInt(1, key);
            rs = gUtente.executeQuery();
            if (rs.next()) {
                result = new UtenteMysqlImpl(this, rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MeetingplanDataLayer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                //
            }
        }
        return result;
    }
     
     
    public Utente addUtente(Utente utente){
    
     UtenteMysqlImpl iUtente = (UtenteMysqlImpl) utente;
        ResultSet keys = null;
        try {
           
            aUtente.setString(1,iUtente.getNome() );
            aUtente.setString(2,iUtente.getCognome());
            aUtente.setString(3,iUtente.getMail());
            aUtente.setString(4,iUtente.getUsername());
            
            if (aUtente.executeUpdate() == 1) {
                //per leggere la chiave generata dal database
                //per il record appena inserito, usiamo il metodo
                //getGeneratedKeys sullo statement.
                //to read the generated record key from the database
                //we use the getGeneratedKeys method on the same statement
                keys = aUtente.getGeneratedKeys();
                //il valore restituito è un ResultSet con un record
                //per ciascuna chiave generata (uno solo nel nostro caso)
                //the returned value is a ResultSet with a distinct record for
                //each generated key (only one in our case)
                if (keys.next()) {
                    //i campi del record sono le componenti della chiave
                    //(nel nostro caso, un solo intero)
                    //the record fields are the key componenets
                    //(a single integer in our case)
                    return getUtente(keys.getInt(1));
                    //restituiamo la foto appena inserita RICARICATA
                    //dal database tramite le API del modello. In tal
                    //modo terremo conto di ogni modifica apportata
                    //durante la fase di inserimento
                    //we return the just-inserted photo RELOADED from the
                    //database through our API. In this way, the resulting
                    //object will ambed any data correction performed by
                    //the DBMS
                }
    
    }
        }    catch (SQLException ex) {
            Logger.getLogger(MeetingplanDataLayer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                keys.close();
            } catch (SQLException ex) {
                //
            }
        }
        return null;
     
    
    
    
    
        }
    
    
    
    public Utente delete(Utente utente){
    
     try {
            dUtente.setInt(1,utente.getKey());
           
            dUtente.executeQuery();
            
             return utente;
           
            }
         catch (SQLException ex) {
            Logger.getLogger(MeetingplanDataLayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    
    
    
    }
    
    
    
    public Utente update(Utente utente){
    
     UtenteMysqlImpl iUtente = (UtenteMysqlImpl) utente;
        ResultSet keys = null;
        try {
           
            aUtente.setString(1,iUtente.getNome() );
            aUtente.setString(2,iUtente.getCognome());
            aUtente.setString(3,iUtente.getMail());
            aUtente.setString(4,iUtente.getUsername()); 
            aUtente.executeUpdate();
    }
            catch (SQLException ex) {
            Logger.getLogger(MeetingplanDataLayer.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return null;
     
    
    
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
        
        
        
}
    
