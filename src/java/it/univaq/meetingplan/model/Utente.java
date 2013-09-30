package it.univaq.meetingplan.model;

import java.util.List;

/**
 *
 * @author Charlie Bellesso,Carlos Bellesso,Stefano Maglione
 */
public interface Utente {
    
    /**
     * @return the key
     */
    int getKey();
    
     /**
     * @return nome
     */
    String getNome();
    
      /**
     * @param nome nome to set
     */
    void setNome(String nome);
    
      /**
     * @return cognome
     */
    String getCognome();
    
    /**
     * @param cognome cognome to set
     */
    void setCognome(String cognome);
    
     /**
     * @param mail mail to set
     */
    void setMail(String mail);
    
      /**
     * @return mail
     */
    String getMail();
    
     /**
     * @return username
     */
    String getUsername();
    
     /**
     * @param username username to set
     */
    void setUsername(String username);
    
    // prendi lista di gruppi di appartenenza del utente 
    
    List<Gruppi> getGruppi();
    
    
     public void addToGruppi(Gruppi g);
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
