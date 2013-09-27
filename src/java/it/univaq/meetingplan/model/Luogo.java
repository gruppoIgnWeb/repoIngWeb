
package it.univaq.meetingplan.model;

/**
 *
 * @author Charlie Bellesso,Carlos Bellesso,Stefano Maglione
 */
public interface Luogo {
    
    // restituisce la chiave (read-only)
    int getKey();
    
    // restituisce tipo
    String getTipo();
    
    // imposta tipo
    void setTipo(String tipo);
    
    // restituisce nome
    String getNome();
    
    // imposta nome
    void setNome(String nome);
    
    // restituisce indirizzo
    String getIndirizzo();
    
    // imposta indirizzo
    void setIndirizzo(String indirizzo);
    
    // restituisce capienza
    int getCapienza();
    
    // imposta capienza
    void setCapienza(int capienza);
    
    
    
}
