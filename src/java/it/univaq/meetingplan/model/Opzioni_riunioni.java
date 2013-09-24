
package it.univaq.meetingplan.model;

import java.util.Calendar;

/**
 *
 * @author Charlie Bellesso,Carlos Bellesso,Stefano Maglione
 */
public interface Opzioni_riunioni {
    
    // restituisce la chiave (read-only)
    int getKey();
    
    // restituisce la data o intervallo di tempo nel quale si effetuerà la riunione.
    /********* IMPORTANTE : NON SONO SICURO SE IL TIPO CALENDAR è ADATTO .. è UNA COSA DA RIVALUTARE *********/
    Calendar getData();
    
    //imposta data della riunione 
    /********* IMPORTANTE : NON SONO SICURO SE IL TIPO CALENDAR è ADATTO .. è UNA COSA DA RIVALUTARE *********/
    void setData(Calendar data);
    
    // restituisce luogo della riunione
    Luogo getLuogo();
    
    // imposta luogo della riunione
    void setLuogo(Luogo luogo);
    
    // prendi riunione a cui fa riferimento questa proposta
    Riunione getRiunione();
    
    // imposta a quale riunione fa riferimento questa proposta
    void setRiunione(Riunione riunione);
    
    
}
