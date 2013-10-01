package it.univaq.f4i.iw.examples;

import it.univaq.f4i.iw.framework.result.HTMLResult;
import it.univaq.meetingplan.model.Gruppi;
import it.univaq.meetingplan.model.Luogo;
import it.univaq.meetingplan.model.MeetingplanDataLayer;
import it.univaq.meetingplan.model.Opzioni_riunioni;
import it.univaq.meetingplan.model.Riunione;
import it.univaq.meetingplan.model.Servizi;
import it.univaq.meetingplan.model.Utente;
import it.univaq.meetingplan.model.impl.MeetingplanDataLayerMysqlImpl;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author Giuseppe Della Penna
 */
public class Salutami extends HttpServlet {

    private Calendar startup;
    private SimpleDateFormat sdf = new SimpleDateFormat();

    private void action_error(HttpServletRequest request, HttpServletResponse response, String message) {
        HTMLResult result = new HTMLResult(getServletContext());
        result.setTitle("ERROR");
        result.setBody("<p>" + message + "</p>");
        try {
            result.activate(request, response);
        } catch (IOException ex) {
            //if error page cannot be sent, try a standard HTTP error message
            //se non possiamo inviare la pagina di errore, proviamo un messaggio di errore HTTP standard
            try {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
            } catch (IOException ex1) {
                //if ALSO this error status cannot be notified, write to the server log
                //se ANCHE questo stato di errore non può essere notificato, scriviamo sul log del server
                Logger.getLogger(Salutami.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }

    private void action_saluta_noto(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        
        
         int i;
        Statement s = null;
        ResultSet rs = null;
        Connection connection;
        
        
        
        
        
        
        /*
        try {
            //Class.forName(getServletContext().getInitParameter("data.jdbc.driver"));
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Salutami.class.getName()).log(Level.SEVERE, null, ex);
        }*/
            //connessione al database locale
            //DBMS connection
        /*
            connection = DriverManager.getConnection(getServletContext().getInitParameter("data.jdbc.connectionstring"), getServletContext().getInitParameter("data.jdbc.username"), getServletContext().getInitParameter("data.jdbc.password"));
        */
        
            DataSource ds = (DataSource) getServletContext().getAttribute("datasource");
            //connessione al database locale
            //database connection
            connection = ds.getConnection();
            
          MeetingplanDataLayer data=new MeetingplanDataLayerMysqlImpl(connection);
        
      /*  Utente utente= data.createUtente();
        utente.setNome(request.getParameter("n"));
        data.addUtente(utente);*/
        
    /*    Gruppi gruppi=data.createGruppi();
         gruppi=data.getGruppi(1);
        //System.out.println("riga 87" + riunione.getDescrizione());
          List<Servizi> servizi =gruppi.getServizi();
          Iterator<Servizi> iterator = servizi.iterator();
	while (iterator.hasNext()) {
            Servizi opzion=iterator.next();
		System.out.println(opzion.getNome());
	}*/
          
        
        Riunione riunione= data.getRiunione(1);
       /* Gruppi gruppi=data.getGruppi(1);
        utente.addToGruppi(gruppi);*/
        System.out.println("riga 87" + riunione.getData());
        
        
        
        
        
          
        //System.out.println("riga 87" + luogo.getNome());
        
        
        HTMLResult result = new HTMLResult(getServletContext());
        result.setTitle("Salutami!");
      //  result.setBody("<p>Hello, " + luogo.getNome()+ "!</p>");
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        result.activate(request, response);
    }

    private void action_saluta_anonimo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HTMLResult result = new HTMLResult(getServletContext());
       
        result.setTitle("Salutami!");
        result.appendToBody("<p>Hello!</p>");
        result.appendToBody("<form method=\"get\" action=\"salutami\">");
        result.appendToBody("<p>What is your name?");
        result.appendToBody("<input type=\"text\" name=\"n\"/>");
        result.appendToBody("<input type=\"submit\" name=\"s\" value=\"Hello!\"/>");
        result.appendToBody("</p>");
        result.appendToBody("</form>");
        
        result.appendToBody("<p><small>I'm greeting all users since " + sdf.format(startup.getTime()) + "</small></p>");
        result.activate(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {

        try {
            String n = request.getParameter("n");
            if (n == null || n.isEmpty()) {
                action_saluta_anonimo(request, response);
            } else {
                action_saluta_noto(request, response);
            }
        } catch (IOException ex) {
            action_error(request, response, ex.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(Salutami.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        startup = Calendar.getInstance();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);




    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);




    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "A kind servlet";


    }// </editor-fold>
}
