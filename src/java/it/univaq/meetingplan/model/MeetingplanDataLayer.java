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

    
    

 //   Riunione createRiunione();

  //  Servizi createServizi();

   // Opzioni_riunioni createOpzioni_riunioni();
    
   // Luogo createLuogo();
    
  

    
    Utente createUtente();
    Utente getUtente(int key);
    Utente addUtente(Utente utente);
    Utente update(Utente utente);
    Utente delete(Utente utente);
    
    Gruppi createGruppi();
    Gruppi getGruppi(int key);
    Gruppi addGruppi(Gruppi gruppi);
    Gruppi update(Gruppi gruppi);
    Gruppi delete(Gruppi gruppi);
    
    
    
    
    
    

}
