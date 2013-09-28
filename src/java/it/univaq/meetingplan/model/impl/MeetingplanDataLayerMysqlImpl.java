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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Charlie Bellesso,Carlos Bellesso,Stefano Maglione
 */
public class MeetingplanDataLayerMysqlImpl implements MeetingplanDataLayer {

    private PreparedStatement gUtente, aUtente, uUtente, dUtente, gGruppi, aGruppi, uGruppi, dGruppi, gRiunione,
            aRiunione, uRiunione, dRiunione, gOpzioni_riunione, aOpzioni_riunione, uOpzioni_riunione,
            dOpzioni_riunione, gServizi, aServizi, uServizi, dServizi, gLuogo, aLuogo, uLuogo, dLuogo,
            gIdLuogo_Riunione, gLuogo_OpzioneRiunione ;

    public MeetingplanDataLayerMysqlImpl(Connection connection) throws SQLException {


        gUtente = connection.prepareStatement("SELECT * FROM utente WHERE id=?");
        aUtente = connection.prepareStatement("INSERT INTO utente (nome,cognome,mail,username) VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
        uUtente = connection.prepareStatement("UPDATE utente SET nome=?,cognome=?,mail=?,username=? WHERE id=?");
        dUtente = connection.prepareStatement("DELETE FROM utente WHERE id=?");

        gGruppi = connection.prepareStatement("SELECT * FROM gruppi WHERE id=?");
        aGruppi = connection.prepareStatement("INSERT INTO gruppi (tipo) VALUES(?)", Statement.RETURN_GENERATED_KEYS);
        uGruppi = connection.prepareStatement("UPDATE gruppi SET tipo=? WHERE id=?");
        dGruppi = connection.prepareStatement("DELETE FROM gruppi WHERE id=?");

        gRiunione = connection.prepareStatement("SELECT * FROM riunione WHERE id=?");
        aRiunione = connection.prepareStatement("INSERT INTO riunione (descrizione,creatore,luogo,data,conferma_flag) VALUES(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
        uRiunione = connection.prepareStatement("UPDATE riunione SET descrizione=?,creatore=?,luogo=?,data=?,conferma_flag=? WHERE id=?");
        dRiunione = connection.prepareStatement("DELETE FROM riunione WHERE id=?");

        gOpzioni_riunione = connection.prepareStatement("SELECT * FROM opzioni-riunioni WHERE id=?");
        aOpzioni_riunione = connection.prepareStatement("INSERT INTO opzione-riunione (data) VALUES(?)", Statement.RETURN_GENERATED_KEYS);
        uOpzioni_riunione = connection.prepareStatement("UPDATE opzione-riunione SET tipo=? WHERE id=?");
        dOpzioni_riunione = connection.prepareStatement("DELETE FROM opzione-riunione WHERE id=?");

        gServizi = connection.prepareStatement("SELECT * FROM servizi WHERE id=?");
        aServizi = connection.prepareStatement("INSERT INTO servizi (nome) VALUES(?)", Statement.RETURN_GENERATED_KEYS);
        uServizi = connection.prepareStatement("UPDATE servizi SET nome=? WHERE id=?");
        dServizi = connection.prepareStatement("DELETE FROM servizi WHERE id=?");

        gLuogo = connection.prepareStatement("SELECT * FROM luogo WHERE id=?");
        aLuogo = connection.prepareStatement("INSERT INTO luogo (tipo,nome,indirizzo,capienza) VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
        uLuogo = connection.prepareStatement("UPDATE luogo SET tipo=?,nome=?,indirizzo=?,capienza=? WHERE id=?");
        dLuogo = connection.prepareStatement("DELETE FROM luogo WHERE id=?");


        gIdLuogo_Riunione = connection.prepareStatement("SELECT * FROM luogo_riunione WHERE id_riunione=?");
        gLuogo_OpzioneRiunione = connection.prepareStatement("SELECT * FROM luogo_opzioni-riunion WHERE id_opzione-riunione=?");
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
    public Utente addUtente(Utente utente) {

        UtenteMysqlImpl iUtente = (UtenteMysqlImpl) utente;
        ResultSet keys = null;
        try {

            aUtente.setString(1, iUtente.getNome());
            aUtente.setString(2, iUtente.getCognome());
            aUtente.setString(3, iUtente.getMail());
            aUtente.setString(4, iUtente.getUsername());

            if (aUtente.executeUpdate() == 1) {

                keys = aUtente.getGeneratedKeys();

                if (keys.next()) {

                    return getUtente(keys.getInt(1));

                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(MeetingplanDataLayer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (keys != null) {
                    keys.close();
                }
            } catch (SQLException ex) {
                //
            }
        }
        return null;





    }

    @Override
    public Utente updateUtente(Utente utente) {

        UtenteMysqlImpl iUtente = (UtenteMysqlImpl) utente;
        ResultSet keys = null;
        try {

            uUtente.setString(1, iUtente.getNome());//verificare se va messo uUtente e non aUtente
            uUtente.setString(2, iUtente.getCognome());
            uUtente.setString(3, iUtente.getMail());
            uUtente.setString(4, iUtente.getUsername());
            uUtente.setInt(5, iUtente.getKey());
            uUtente.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MeetingplanDataLayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Utente deleteUtente(Utente utente) {

        try {
            dUtente.setInt(1, utente.getKey());

            dUtente.executeQuery();

            return utente;

        } catch (SQLException ex) {
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
    public Gruppi addGruppi(Gruppi gruppi) {

        GruppiMysqlImpl iGruppi = (GruppiMysqlImpl) gruppi;
        ResultSet keys = null;
        try {

            aGruppi.setString(1, iGruppi.getTipo());


            if (aGruppi.executeUpdate() == 1) {

                keys = aGruppi.getGeneratedKeys();

                if (keys.next()) {

                    return getGruppi(keys.getInt(1));

                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(MeetingplanDataLayer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (keys != null) {
                    keys.close();
                }
            } catch (SQLException ex) {
                //
            }
        }
        return null;

    }

    @Override
    public Gruppi updateGruppi(Gruppi gruppi) {

        GruppiMysqlImpl iGruppi = (GruppiMysqlImpl) gruppi;
        ResultSet keys = null;
        try {

            uGruppi.setString(1, iGruppi.getTipo());
            uGruppi.setInt(2, iGruppi.getKey());
            uGruppi.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MeetingplanDataLayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Gruppi deleteGruppi(Gruppi gruppi) {

        try {
            dGruppi.setInt(1, gruppi.getKey());

            dGruppi.executeQuery();

            return gruppi;

        } catch (SQLException ex) {
            Logger.getLogger(MeetingplanDataLayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Luogo createLuogo() {
        return new LuogoMysqlImpl(this);
    }

    @Override
    public Luogo getLuogo(int key) {

        Luogo result = null;
        ResultSet rs = null;

        try {
            gLuogo.setInt(1, key);
            rs = gLuogo.executeQuery();

            if (rs.next()) {
                result = new LuogoMysqlImpl(this, rs);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MeetingplanDataLayerMysqlImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(MeetingplanDataLayerMysqlImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return result;
    }

    @Override
    public Luogo addLuogo(Luogo luogo) {

        LuogoMysqlImpl iLuogo = (LuogoMysqlImpl) luogo;
        ResultSet keys = null;
        try {

            aLuogo.setInt(1, iLuogo.getTipo());
            aLuogo.setString(2, iLuogo.getNome());
            aLuogo.setString(3, iLuogo.getIndirizzo());
            aLuogo.setInt(4, iLuogo.getCapienza());

            if (aLuogo.executeUpdate() == 1) {

                keys = aLuogo.getGeneratedKeys();

                if (keys.next()) {

                    return getLuogo(keys.getInt(1));

                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(MeetingplanDataLayer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (keys != null) {
                    keys.close();
                }
            } catch (SQLException ex) {
                //
            }
        }
        return null;


    }

    @Override
    public Luogo updateLuogo(Luogo luogo) {

        LuogoMysqlImpl iLuogo = (LuogoMysqlImpl) luogo;

        try {

            uLuogo.setInt(1, iLuogo.getTipo());
            uLuogo.setString(2, iLuogo.getNome());
            uLuogo.setString(3, iLuogo.getIndirizzo());
            uLuogo.setInt(4, iLuogo.getCapienza());

            uLuogo.setInt(5, iLuogo.getKey());
            uLuogo.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(MeetingplanDataLayer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;

    }

    @Override
    public Luogo deleteLuogo(Luogo luogo) {

        try {
            dLuogo.setInt(1, luogo.getKey());

            dLuogo.executeQuery();

            return luogo;

        } catch (SQLException ex) {
            Logger.getLogger(MeetingplanDataLayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    @Override
    public Riunione createRiunione() {
        return new RiunioneMysqlImpl(this);
    }

    @Override
    public Riunione getRiunione(int key) {
        Riunione result = null;
        ResultSet rs = null;
        try {
            gRiunione.setInt(1, key);
            rs = gRiunione.executeQuery();
            if (rs.next()) {
                result = new RiunioneMysqlImpl(this, rs);
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
    public Riunione addRiunione(Riunione riunione) {

        RiunioneMysqlImpl iRiunione = (RiunioneMysqlImpl) riunione;
        ResultSet keys = null;
        try {

            aRiunione.setString(1, iRiunione.getDescrizione());
            aRiunione.setInt(2, iRiunione.getCreatore().getKey());
            aRiunione.setInt(3, iRiunione.getLuogo().getKey());
            aRiunione.setTimestamp(4, iRiunione.getData());
            // aRiunione.setInt(5, iRiunione.getConferma_flag()); *metodo ancora non implementato*

            if (aRiunione.executeUpdate() == 1) {

                keys = aRiunione.getGeneratedKeys();

                if (keys.next()) {

                    return getRiunione(keys.getInt(1));

                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(MeetingplanDataLayer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (keys != null) {
                    keys.close();
                }
            } catch (SQLException ex) {
                //
            }
        }
        return null;


    }

    @Override
    public Riunione updateRiunione(Riunione riunione) {

        RiunioneMysqlImpl iRiunione = (RiunioneMysqlImpl) riunione;
        try {

            uRiunione.setString(1, iRiunione.getDescrizione());//verificare se va messo uUtente e non aUtente
            uRiunione.setInt(2, iRiunione.getCreatore().getKey());
            uRiunione.setInt(3, iRiunione.getLuogo().getKey());
            uRiunione.setTimestamp(4, iRiunione.getData());
            uRiunione.setInt(5, iRiunione.getKey());
            uRiunione.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MeetingplanDataLayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Riunione deleteRiunione(Riunione riunione) {

        try {
            dRiunione.setInt(1, riunione.getKey());

            dRiunione.executeQuery();

            return riunione;

        } catch (SQLException ex) {
            Logger.getLogger(MeetingplanDataLayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    @Override
    public Opzioni_riunioni createOpzioni_riunioni() {
        return new Opzioni_riunioniMysqlImpl(this);
    }

    @Override
    public Opzioni_riunioni getOpzioni_riunioni(int key) {
        Opzioni_riunioni result = null;
        ResultSet rs = null;
        try {
            gOpzioni_riunione.setInt(1, key);
            rs = gOpzioni_riunione.executeQuery();
            if (rs.next()) {
                result = new Opzioni_riunioniMysqlImpl(this, rs);
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
    public Opzioni_riunioni addOpzioni_riunioni(Opzioni_riunioni opzioni_riunione) {

        Opzioni_riunioni iOpzioni_riunioni = (Opzioni_riunioniMysqlImpl) opzioni_riunione;
        ResultSet keys = null;
        try {

            aOpzioni_riunione.setTimestamp(1, iOpzioni_riunioni.getData());

            if (aOpzioni_riunione.executeUpdate() == 1) {

                keys = aOpzioni_riunione.getGeneratedKeys();

                if (keys.next()) {

                    return getOpzioni_riunioni(keys.getInt(1));

                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(MeetingplanDataLayer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (keys != null) {
                    keys.close();
                }
            } catch (SQLException ex) {
                //
            }
        }
        return null;

    }

    @Override
    public Opzioni_riunioni updateOpzioni_riunioni(Opzioni_riunioni opzioni_riunione) {

        Opzioni_riunioni iOpzioni_riunioni = (Opzioni_riunioniMysqlImpl) opzioni_riunione;
        ResultSet keys = null;
        try {

            uOpzioni_riunione.setTimestamp(1, iOpzioni_riunioni.getData());//verificare se va messo uUtente e non aUtente
            uOpzioni_riunione.setInt(2, iOpzioni_riunioni.getKey());
            uOpzioni_riunione.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MeetingplanDataLayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    @Override
    public Opzioni_riunioni deleteOpzioni_riunioni(Opzioni_riunioni opzioni_riunione) {
        try {
            dOpzioni_riunione.setInt(1, opzioni_riunione.getKey());

            dOpzioni_riunione.executeQuery();

            return opzioni_riunione;

        } catch (SQLException ex) {
            Logger.getLogger(MeetingplanDataLayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    @Override
    public Servizi createServizi() {
        return new ServiziMysqlImpl(this);
    }

    @Override
    public Servizi getServizi(int key) {
        Servizi result = null;
        ResultSet rs = null;
        try {
            gServizi.setInt(1, key);
            rs = gServizi.executeQuery();
            if (rs.next()) {
                result = new ServiziMysqlImpl(this, rs);
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
    public Servizi addServizi(Servizi servizi) {
        Servizi iServizi = (ServiziMysqlImpl) servizi;
        ResultSet keys = null;
        try {

            aServizi.setString(1, iServizi.getNome());

            if (aServizi.executeUpdate() == 1) {

                keys = aServizi.getGeneratedKeys();

                if (keys.next()) {

                    return getServizi(keys.getInt(1));

                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(MeetingplanDataLayer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (keys != null) {
                    keys.close();
                }
            } catch (SQLException ex) {
                //
            }
        }
        return null;

    }

    @Override
    public Servizi updateServizi(Servizi servizi) {

        Servizi iServizi = (ServiziMysqlImpl) servizi;

        try {

            uServizi.setString(1, iServizi.getNome());//verificare se va messo uUtente e non aUtente
            uServizi.setInt(2, iServizi.getKey());
            uServizi.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MeetingplanDataLayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    @Override
    public Servizi deleteServizi(Servizi servizi) {
        try {
            dServizi.setInt(1, servizi.getKey());

            dServizi.executeQuery();

            return servizi;

        } catch (SQLException ex) {
            Logger.getLogger(MeetingplanDataLayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Servizi> getServiziByGruppo(Gruppi gruppo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public Utente getUtenteByRiunioni(Riunione riunioni) {

        Utente result = null;
        ResultSet rs = null;

        try {

            gIdUtente_Riunione.setInt(1, riunioni.getKey());
            rs = gUtente.executeQuery();

            if (rs.next()) {

                gUtente.setInt(1, rs.getInt("id_utente"));
                rs = gUtente.executeQuery();

                if (rs != null && rs.next()) {

                    result = new UtenteMysqlImpl(this, rs);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(MeetingplanDataLayerMysqlImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                //
            }
            return result;

        }
    }
}
