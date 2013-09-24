
package it.univaq.meetingplan.model;

import java.util.List;
/*
 *
 * @author Charlie Bellesso,Carlos Bellesso,Stefano Maglione
 */
public interface Gruppi {
    
    // restituisce la chiave (read-only)
    int getKey();
    
    // restituisce il tipo
    String getTipo();
    
    // imposta il tipo
    void setTipo(String tipo);
    
    
    // prendi lista con tutti i servizi che possono fare gli utenti appartenenti al mio gruppo
    List<Servizi> getServizi();
    
    // prendo lista di utenti appartenenti al mio gruppo
    List<Utente> getUtenti();
    
}
