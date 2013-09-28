/* 
 * Questa interfaccia contiene la lista di funzionalità astratte di cui la
 * logica business dell'applicazione necessita per manipolare i dati e
 * realizzare le operazioni richieste
 * 
 * This interface contains the list of abstract funcionalities that the
 * application business logic needs to manipulate the data
 * and perform its operations
 * 
 */

package it.univaq.meetingplan.model;

import java.util.List;



/**
 *
 * @author Charlie Bellesso,Carlos Bellesso,Stefano Maglione
 */
public interface MeetingplanDataLayer {
    
    //metodi factory per gli oggetti del data model
 
    Gruppi createGruppi();
    Luogo createLuogo();
    Opzioni_riunioni createOpzioni_riunioni();
    Riunione createRiunione();
    Servizi createServizi();
    Utente createUtente();
    
    
    //restituisce utente identificato tramite la sua chiave
    Utente getUtente(int key);
    //aggiunge utente
    Utente addUtente(Utente utente);
    //aggiorna utente
    Utente updateUtente(Utente utente);
    //elimina utente
    Utente deleteUtente(Utente utente);
    
    //restituisce gruppo identificato tramite la sua chiave
    Gruppi getGruppi(int key);
    //aggiunge gruppo
    Gruppi addGruppi(Gruppi gruppi);
    //aggiorna gruppo
    Gruppi updateGruppi(Gruppi gruppi);
    //elimina gruppo
    Gruppi deleteGruppi(Gruppi gruppi);
    
    //restituisce luogo identificato tramite la sua chiave
    Luogo getLuogo(int key);
    //aggiunge luogo
    Luogo addLuogo(Luogo luogo);
    //aggiorna luogo
    Luogo updateLuogo(Luogo luogo);
    //elimina luogo
    Luogo deleteLuogo(Luogo luogo);
    
    //restituisce riunione identificata tramite la sua chiave
    Riunione getRiunione(int key);
    //aggiunge riunione
    Riunione addRiunione(Riunione riunione);
    //aggiorna riunione
    Riunione updateRiunione(Riunione riunione);
    //elimina riunione
    Riunione deleteRiunione(Riunione riunione);
    
    //restituisce opzioni_riunione identificata tramite la sua chiave
    Opzioni_riunioni getOpzioni_riunioni(int key);
    //aggiunge opzioni_riunione
    Opzioni_riunioni addOpzioni_riunioni(Opzioni_riunioni opzioni_riunione);
    //aggiorna opzioni_riunione
    Opzioni_riunioni updateOpzioni_riunioni (Opzioni_riunioni opzioni_riunione);
    //elimina opzioni_riunione
    Opzioni_riunioni deleteOpzioni_riunioni (Opzioni_riunioni opzioni_riunione);
    
    //restituisce servizo identificato tramite la sua chiave
    Servizi getServizi(int key);
    //aggiunge servizi
    Servizi addServizi(Servizi servizi);
    //aggiorna servizi
    Servizi updateServizi(Servizi servizi);
    //elimina servizi
    Servizi deleteServizi(Servizi servizi);
    
    // prendi lista con tutti i servizi che possono fare gli utenti appartenenti al mio gruppo
    List<Servizi> getServiziByGruppo(Gruppi gruppo);
    
    // prendo lista di utenti appartenenti al mio gruppo
    List<Utente> getUtentiByGruppo(Gruppi gruppo);

    // ricevi lugo relativo ad una proposta di riunione
    Luogo getLuogoByOpzioni_riunioni(Opzioni_riunioni opzioni_riunioni);
    
    // ricevi luogo relativo ad una riunione
    Luogo getLuogoByRiunione(Riunione riunione);
    
    // ricevi utente relativo ad una riunione
    Utente getUtenteByRiunioni(Riunione riunioni);
    
    

}
