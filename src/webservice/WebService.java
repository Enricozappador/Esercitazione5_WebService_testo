package webservice;

import java.util.LinkedList;

public class WebService {
	private LinkedList<Utente> utenti;
	private int numUtenti;
	private LinkedList<Admin> admin;
	private LinkedList<Acquirente> acquirenti;
	private int numAdmin;
	private int numAcq;
	 
	
	
	
	public WebService(){
		utenti = new LinkedList<Utente>();
		admin = new LinkedList<Admin>();
		acquirenti = new LinkedList<Acquirente>();
		
		numUtenti = 0;
		numAdmin = 0;
		numAcq = 0;
		
	}
	
	public Utente registraUtente(String nome, String cognome, String email, String password, String dataNascita) {
		Utente utemp = new Utente(nome, cognome, email, password, dataNascita);
		 String controllo = "@";
	        if (email.contains(controllo)== false)
	            utemp=null;
	       if (password.length()<8 
	    		   //|| utemp.controllLowerCase(password)== false || utemp.controllUpperCase(password)== false
	    		   )
	            utemp = null;
	        for (Utente u : utenti)
	            if (u != null && u.getEmail().equalsIgnoreCase(email) == true) {


	                return u;
	            }
	        
	        if (numUtenti == 0)
	        {
	        	Admin atemp = new Admin(nome, cognome, email, password, dataNascita);
	        	admin.add(numAdmin++, atemp);
	        	utemp = atemp;
	        	 utenti.add(numUtenti++, utemp);
	        }
	        else 
	        {
	        	Acquirente actemp = new Acquirente(nome, cognome, email, password, dataNascita);
	        	acquirenti.add(numAcq++, actemp);
	        	utemp=actemp;
	        	 utenti.add(numUtenti++, utemp);
	        }
	        	

	         



	        return utemp;
	}
	
	public Utente cercaUtente(String email) {
		for (Utente u : utenti)
        if (u!=null && u.getEmail().equalsIgnoreCase(email) == true )
            return u;


		return null;
	}
	
	public Utente loginUtente(String email, String password, String timestamp) {
		return null;
	}
	
	public Utente logoutUtente(String email, String timestamp) {
		return null;
	}
	
	public Utente verificaConnessioneUtente(String email) {
		return null;
	}
		
	public Utente eliminaUtente(String email, String password, String emailUtenteDaEliminare ) {
		return null;
	}
	
	public String elencoUtentiPerEmail() {
		return null;
	}	
	
	public String elencoAccessiUtentePerTimestamp(String email) {
		return null;
	}
	
	public String elencoUtentiConnessiPerDataNascita() {
		return null;
	}
	
	public String elencoUtentiPerCognome() {
		return null;
	}		
}