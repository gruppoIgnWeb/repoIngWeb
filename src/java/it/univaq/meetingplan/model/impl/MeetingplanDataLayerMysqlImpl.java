
package it.univaq.meetingplan.model.impl;

import it.univaq.meetingplan.model.Utente;
import it.univaq.meetingplan.model.Servizi;
import it.univaq.meetingplan.model.Riunione;
import it.univaq.meetingplan.model.MeetingplanDataLayer;
import it.univaq.meetingplan.model.Opzioni_riunioni;
import it.univaq.meetingplan.model.Luogo;
import it.univaq.meetingplan.model.Gruppi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Charlie Bellesso,Carlos Bellesso,Stefano Maglione
 */
public class MeetingplanDataLayerMysqlImpl implements MeetingplanDataLayer {

    private PreparedStatement gUtente, aUtente, uUtente, dUtente, gGruppi, aGruppi, uGruppi, dGruppi, gRiunioni,
            aRiunione, uRiunione, dRiunione, gOpzioni_riunione, aOpzioni_riunione, uOpzioni_riunione,
            dOpzioni_riunione,gOpzioni_Riunione, gServizi, aServizi, uServizi, dServizi, gLuogo, aLuogo, uLuogo, dLuogo,
            gIdLuogo_Riunione,gIdOpzioni_riunione_Riunione,gIdGruppi_Servizi, gLuogo_OpzioneRiunione ;

    public MeetingplanDataLayerMysqlImpl(Connection connection) throws SQLException {

      
        gUtente = connection.prepareStatement("SELECT * FROM utente WHERE id=?");
        aUtente = connection.prepareStatement("INSERT INTO utente (nome,cognome,mail,username) VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
        uUtente = connection.prepareStatement("UPDATE utente SET nome=?,cognome=?,mail=?,username=? WHERE id=?");
        dUtente = connection.prepareStatement("DELETE FROM utente WHERE id=?");
        
        gGruppi = connection.prepareStatement("SELECT * FROM gruppi WHERE id=?");
        aGruppi = connection.prepareStatement("INSERT INTO gruppi (tipo) VALUES(?)", Statement.RETURN_GENERATED_KEYS);
        uGruppi = connection.prepareStatement("UPDATE gruppi SET tipo=? WHERE id=?");
        dGruppi = connection.prepareStatement("DELETE FROM gruppi WHERE id=?");
        
        gLuogo = connection.prepareStatement("SELECT * FROM luogo WHERE id=?");
        
        
        gRiunioni = connection.prepareStatement("SELECT * FROM riunione WHERE id=?");
        
        
        gOpzioni_Riunione = connection.prepareStatement("SELECT * FROM opzioni_riunioni WHERE id=?");
        
        
        gServizi = connection.prepareStatement("SELECT * FROM servizi WHERE id=?");
        
        
        
        
        gIdLuogo_Riunione = connection.prepareStatement("SELECT * FROM luogo_riunione WHERE id_riunione=?");//statement sulla relazione
        gIdOpzioni_riunione_Riunione = connection.prepareStatement("SELECT * FROM opzioni_riunione__riunione WHERE id_riunione=?");//statement sulla relazione
        gIdGruppi_Servizi = connection.prepareStatement("SELECT * FROM gruppi_servizi WHERE id_gruppo=?");//statement sulla relazione
    }
    
    @Override
    public Utente createUtente() {
        return new UtenteMysqlImpl(this);
    }
    
    @Override
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
                if(rs!=null)rs.close();
                
            } catch (SQLException ex) {
                //
            }
        }
        return result;
    }
     
     
    @Override
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
                if(keys!=null)keys.close();
            } catch (SQLException ex) {
                //
            }
        }
        return null;
     
    
    
    
    
        }
    
    @Override
    public Utente deleteUtente(Utente utente) {
    
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
    
    @Override
    public Utente updateUtente(Utente utente) {
    
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
    
    
    @Override
    public Gruppi createGruppi() {
        return new GruppiMysqlImpl(this);
    }
    
    @Override
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
               if(rs!=null) rs.close();
            } catch (SQLException ex) {
                //
            }
        }
        return result;
    }
     
     
    @Override
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
                if(keys!=null)keys.close();
            } catch (SQLException ex) {
                //
            }
        }
        return null;
    
        }
    
    @Override
    public Gruppi deleteGruppi(Gruppi gruppi) {
    
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
    
    @Override
    public Gruppi updateGruppi(Gruppi gruppi) {
    
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

    @Override
    public Luogo createLuogo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Opzioni_riunioni createOpzioni_riunioni() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Riunione createRiunione() {
        return new RiunioneMysqlImpl(this);
    }

    @Override
    public Servizi createServizi() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public Luogo getLuogo(int key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Luogo addLuogo(Luogo luogo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Luogo updateLuogo(Luogo luogo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Luogo deleteLuogo(Luogo luogo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Riunione getRiunione(int key) {
       Riunione result = null;
        ResultSet rs = null;
        try {
            gRiunioni.setInt(1, key);
            rs = gRiunioni.executeQuery();
            
            if (rs.next()) {
                result = new RiunioneMysqlImpl(this, rs);
                //System.out.println("305 "+result);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MeetingplanDataLayer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
               if(rs!=null) rs.close();
            } catch (SQLException ex) {
                //
            }
        }
        return result;
    }

    @Override
    public Riunione addRiunione(Riunione riunione) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Riunione updateRiunione(Riunione riunione) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Riunione deleteRiunione(Riunione riunione) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Opzioni_riunioni getOpzioni_riunioni(int key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<Opzioni_riunioni> getOpzioni_riunioniByRiunione(Riunione riunione){//test ok
        List<Opzioni_riunioni> result = new ArrayList();
        ResultSet rs = null;
        ResultSet rs1 = null;
        try{
     //   System.out.println("riga 363" + riunione.getKey());
            gIdOpzioni_riunione_Riunione.setInt(1, riunione.getKey());
            rs = gIdOpzioni_riunione_Riunione.executeQuery();
            
            while (rs.next()) {
                
            try{    
                gOpzioni_Riunione.setInt(1, rs.getInt("id_opzioni-riunione"));
               
                rs1=gOpzioni_Riunione.executeQuery();
            } catch (SQLException ex) {
            Logger.getLogger(MeetingplanDataLayer.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            //System.out.println("riga 374" + rs1);
                 while(rs1.next()){
                 
                  result.add(new Opzioni_riunioniMysqlImpl(this, rs1));
                 }
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(MeetingplanDataLayer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
               if(rs!=null) rs.close();
            } catch (SQLException ex) {
                //
            }
        }
        return result;
        
        
        
    
    }

    @Override
    public Opzioni_riunioni addOpzioni_riunioni(Opzioni_riunioni opzioni_riunione) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Opzioni_riunioni updateOpzioni_riunioni(Opzioni_riunioni opzioni_riunione) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Opzioni_riunioni deleteOpzioni_riunioni(Opzioni_riunioni opzioni_riunione) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Servizi getServizi(int key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Servizi addServizi(Servizi servizi) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Servizi updateServizi(Servizi servizi) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Servizi deleteServizi(Servizi servizi) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Servizi> getServiziByGruppo(Gruppi gruppo) {
        List<Servizi> result = new ArrayList();
        ResultSet rs = null;
        ResultSet rs1 = null;
        try{
     //   System.out.println("riga 363" + riunione.getKey());
            gIdGruppi_Servizi.setInt(1, gruppo.getKey());
            rs = gIdGruppi_Servizi.executeQuery();
            
            while (rs.next()) {
                
            try{    
                gServizi.setInt(1, rs.getInt("id_servizio"));
               
                rs1=gServizi.executeQuery();
            } catch (SQLException ex) {
            Logger.getLogger(MeetingplanDataLayer.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            //System.out.println("riga 374" + rs1);
                 while(rs1.next()){
                 
                  result.add(new ServiziMysqlImpl(this, rs1));
                 }
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(MeetingplanDataLayer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
               if(rs!=null) rs.close();
            } catch (SQLException ex) {
                //
            }
        }
        return result;
        
        
        
        
    }

    @Override
    public List<Utente> getUtentiByGruppo(Gruppi gruppo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Luogo getLuogoByOpzioni_riunioni(Opzioni_riunioni opzioni_riunioni) {

        Luogo result = null;
        ResultSet rs = null;
        try {
            //System.out.println(riunione.getKey());
            gLuogo_OpzioneRiunione.setInt(1, opzioni_riunioni.getKey());
            rs = gIdLuogo_Riunione.executeQuery();

            if (rs.next()) {

                try {
                    gLuogo.setInt(1, rs.getInt("id_luogo"));
                    rs = gLuogo.executeQuery();
                } catch (SQLException ex) {
                    Logger.getLogger(MeetingplanDataLayer.class.getName()).log(Level.SEVERE, null, ex);
                }


                if (rs != null && rs.next()) {

                    result = new LuogoMysqlImpl(this, rs);
                }


            }
        } catch (SQLException ex) {
            Logger.getLogger(MeetingplanDataLayer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                //
            }
        }
        return result;
    }

    @Override
    public Luogo getLuogoByRiunione(Riunione riunione) {
        
        Luogo result = null;
        ResultSet rs = null;
        try {
            //System.out.println(riunione.getKey());
            gIdLuogo_Riunione.setInt(1, riunione.getKey());
            rs = gIdLuogo_Riunione.executeQuery();
            
            if (rs.next()) {
                
            try{    
                gLuogo.setInt(1, rs.getInt("id_luogo"));
                rs=gLuogo.executeQuery();
            } catch (SQLException ex) {
            Logger.getLogger(MeetingplanDataLayer.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
                 if(rs.next()){
                 
                 
                 
                 result = new LuogoMysqlImpl(this, rs);
                 }
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(MeetingplanDataLayer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
               if(rs!=null) rs.close();
            } catch (SQLException ex) {
                //
            }
        }
        return result;
        
        
        
        
        
        
        
        
    }

    @Override
    public Utente getUtenteByRiunioni(Riunione riunioni) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
        
        
        
}
    
