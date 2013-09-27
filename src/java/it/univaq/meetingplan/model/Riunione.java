
package it.univaq.meetingplan.model;

import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Charlie Bellesso,Carlos Bellesso,Stefano Maglione
 */
public interface Riunione {
    
    // restituisce la chiave (read-only)
    int getKey();
    
    // restituisce descrizione
    String getDescrizione();
    
    // imposta descrizione
    void setDescrizione(String descrizione);
    
    // restituisce creatore, cioè l'organizzatore
    Utente getCreatore();
    
    // imposta creatore
    void setCreatore(Utente organizzatore);
    
    // restituisce luogo della riunione
    Luogo getLuogo();
    
    // imposta luogo della riunione
    void setLuogo(Luogo luogo);
    
    // restituisce la data o intervallo di tempo nel quale si effetuerà la riunione.
    /********* IMPORTANTE : NON SONO SICURO SE IL TIPO CALENDAR è ADATTO .. è UNA COSA DA RIVALUTARE *********/
    Calendar getData();
    
    //imposta data della riunione 
    /********* IMPORTANTE : NON SONO SICURO SE IL TIPO CALENDAR è ADATTO .. è UNA COSA DA RIVALUTARE *********/
    void setData(Calendar data);
    
    // prendi lista di proposte riunioni per la riunione
    List<Opzioni_riunioni> getOpzioni_riunioni();
    
}
