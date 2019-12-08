package webservice;

import java.util.LinkedList;

public class WebService {
	private LinkedList<Utente> utenti;
	private int numUtenti;
	private LinkedList<Admin> admin;
	private LinkedList<Acquirente> acquirenti;
	private int numAdmin;
	private int numAcq;
	private LinkedList<Log> logins; 
	private int numLog; 
	private int login;
	private int logout; 
	 
	
	
	
	public WebService(){
		utenti = new LinkedList<Utente>();
		admin = new LinkedList<Admin>();
		acquirenti = new LinkedList<Acquirente>();
		logins = new LinkedList<Log>();
		
		numUtenti = 0;
		numAdmin = 0;
		numAcq = 0;
		numLog = 0; 
		login = 0; 
		logout = 0; 
		
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
		Utente utemp = cercaUtente(email);
		Log logtemp = new Log(utemp, false, timestamp) ;
		if (utemp == null)
			return null; 
		
		if (utemp.getPassword().compareTo(password)!=0)
			utemp = null; 
		
		for(Log l : logins)
			if(l!=null && l.getUtenti().getEmail().compareTo(email)==0) {
				if(l.isLoggedin()== false) 
				{
					logtemp = l; 
					logtemp.setLoggedin(true); 
			logins.add(numLog++, logtemp);

				}
				
			}
			else 
				utemp = null; 
		
		
		return utemp;
	}
	
	public Utente logoutUtente(String email, String timestamp) {
		Utente utemp = cercaUtente(email); 
		Log logtemp = new Log(utemp, true, timestamp); 
		if(utemp == null)
			return null; 
		
		//if(utemp.getPassword().compareTo(password)!= 0 )
			// utemp = null; 
		
		for(Log l : logins)
			if(l != null && l.getUtenti().getEmail().compareTo(email)==0)
				if (l.isLoggedin()==true)
				{
					logtemp = l; 
					logtemp.setLoggedin(false);
					logins.add(numLog++, logtemp);
				}
				else 
					utemp=null; 
			
		
		return null;
	}
	
	public Utente verificaConnessioneUtente(String email) {
		Utente utemp = null;  
		for (Log l : logins)
			if (l!= null && l.getUtenti().getEmail().compareTo(email)==0 && l.isLoggedin() == true) 
			utemp = l.getUtenti();
		
			
				
		return utemp;
	}
		
	public Utente eliminaUtente(String email, String password, String emailUtenteDaEliminare ) {
		Utente uelimtemp = null; 
		Utente ueliminatotemp = null; 
		
		
		return ueliminatotemp;
	}
	
	public String elencoUtentiPerEmail() {
		for (Utente u : utenti )
		utenti.sort'm'8);
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