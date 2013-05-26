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
    //factory methods to create data model object instances
    Utente createUtente();

 //   Riunione createRiunione();

  //  Servizi createServizi();

   // Opzioni_riunioni createOpzioni_riunioni();
    
   // Luogo createLuogo();
    
  //  Gruppi createGruppi();

    
 
    Utente getUtente(int key);
    Utente addUtente(Utente utente);
    Utente update(Utente utente);
    Utente delete(Utente utente);

}
