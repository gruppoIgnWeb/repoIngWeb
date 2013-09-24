package it.univaq.meetingplan.model;

import java.util.List;

/*
 *
 * @author Charlie Bellesso,Carlos Bellesso,Stefano Maglione
 */
public interface Servizi {
    
    // restituisce la chiave (read-only)
    int getKey();
    
    // restituisce il nome del servizio
    String getNome();
    
    // imposta il nome del servizio
    void setNome();
    
    // prendo lista di gruppi che sono autorizzati ad far uso del servizio
    List<Gruppi> getGruppi();
    
    
}
