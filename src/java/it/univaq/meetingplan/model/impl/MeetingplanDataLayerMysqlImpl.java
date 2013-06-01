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
    
    private PreparedStatement gUtente,aUtente,uUtente,dUtente,gGruppi,aGruppi,uGruppi,dGruppi;
    
    public MeetingplanDataLayerMysqlImpl(Connection connection) throws SQLException {

      
        gUtente = connection.prepareStatement("SELECT * FROM utente WHERE id=?");
        aUtente = connection.prepareStatement("INSERT INTO utente (nome,cognome,mail,username) VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
        uUtente = connection.prepareStatement("UPDATE utente SET nome=?,cognome=?,mail=?,username=? WHERE id=?");
        dUtente = connection.prepareStatement("DELETE FROM utente WHERE id=?");
        
        gGruppi = connection.prepareStatement("SELECT * FROM gruppi WHERE id=?");
        aGruppi = connection.prepareStatement("INSERT INTO gruppi (tipo) VALUES(?)", Statement.RETURN_GENERATED_KEYS);
        uGruppi = connection.prepareStatement("UPDATE gruppi SET tipo=? WHERE id=?");
        dGruppi = connection.prepareStatement("DELETE FROM gruppi WHERE id=?");
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
                
                keys = aUtente.getGeneratedKeys();
               
                if (keys.next()) {
                   
                    return getUtente(keys.getInt(1));
                    
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
           
            aUtente.setString(1,iUtente.getNome() );//verificare se va messo uUtente e non aUtente
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
    
    
    
    
    
    
    
    public Gruppi createGruppi() {
        return new GruppiMysqlImpl(this);
    }
    
     public Gruppi getGruppi(int key) {
        Gruppi result = null;
        ResultSet rs = null;
        try {
            gGruppi.setInt(1, key);
            rs = gGruppi.executeQuery();
            if (rs.next()) {
                result = new GruppiMysqlImpl(this, rs);
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
     
     
    public Gruppi addGruppi(Gruppi gruppi){
    
     GruppiMysqlImpl iGruppi = (GruppiMysqlImpl) gruppi;
        ResultSet keys = null;
        try {
           
            aGruppi.setString(1,iGruppi.getTipo() );
            
            
            if (aGruppi.executeUpdate() == 1) {
                
                keys = aGruppi.getGeneratedKeys();
               
                if (keys.next()) {
                   
                    return getGruppi(keys.getInt(1));
                    
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
    
    
    
    public Gruppi delete(Gruppi gruppi){
    
     try {
            dGruppi.setInt(1,gruppi.getKey());
           
            dGruppi.executeQuery();
            
             return gruppi;
           
            }
         catch (SQLException ex) {
            Logger.getLogger(MeetingplanDataLayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    
    
    
    }
    
    
    
    public Gruppi update(Gruppi gruppi){
    
     GruppiMysqlImpl iGruppi = (GruppiMysqlImpl) gruppi;
        ResultSet keys = null;
        try {
           
            uGruppi.setString(1,iGruppi.getTipo());
            uGruppi.setInt(2,iGruppi.getKey());
            aUtente.executeUpdate();
    }
            catch (SQLException ex) {
            Logger.getLogger(MeetingplanDataLayer.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return null;
     
    
    
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
        
        
        
}
    
