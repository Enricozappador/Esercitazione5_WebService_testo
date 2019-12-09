package webservice;

import java.util.ArrayList;
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
	//private int login;
	//private int logout; 
	 
	
	
	
	public WebService(){
		utenti = new LinkedList<Utente>();
		admin = new LinkedList<Admin>();
		acquirenti = new LinkedList<Acquirente>();
		logins = new LinkedList<Log>();
		
		numUtenti = 0;
		numAdmin = 0;
		numAcq = 0;
		numLog = 0; 
		//login = 0; 
		//logout = 0; 
		
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
	        	 utenti.add(numUtenti++,utemp);
	        }
	        	

	        Utente uttemp = new Utente(utemp.getNome(), utemp.getCognome(), utemp.getEmail(), null, utemp.getDataNascita()); 
	         



	        return uttemp;
	}
	
	public Utente cercaUtente(String email) {
		for (Utente u : utenti)
        if (u!=null && u.getEmail().equalsIgnoreCase(email) == true )
            return u;


		return null;
	}
	
	public Utente loginUtente(String email, String password, String timestamp) {
		Utente utemp = cercaUtente(email);
		//Log logtemp = new Log(utemp, true, timestamp) ;
		int countlogin = 0;
		int countlogout = 0;
		
		if (utemp == null)
			return null; 
		
		if (utemp.getPassword().compareTo(password)!=0)
			utemp = null; 
		
		for(Log l : logins) {
			if(l!=null && l.getUtenti().getEmail().compareTo(email)==0) {
				if(l.isLoggedin() == true)
				{
					countlogin++;

				}
				else if(l.isLoggedin()== false)
					countlogout++;
				
			}
		}
		if(countlogin == countlogout) {
			Log logtemp = new Log(utemp, true, timestamp); 
			logins.add(logtemp);
			numLog++;
		}
			
			/*else 
				utemp = null; */
		
		
		return utemp;
	}
	
	public Utente logoutUtente(String email, String timestamp) {
		Utente utemp = cercaUtente(email); 
		//Log logtemp = new Log(utemp, false, timestamp); 
		int login = 0 ;
		int logoff = 0; 
		if(utemp == null)
			return null; 
		
		//if(utemp.getPassword().compareTo(password)!= 0 )
			// utemp = null; 
		
		for(Log l : logins) {
			if(l != null && l.getUtenti().getEmail().compareTo(email)==0)
			
				{
					if (l.isLoggedin()== true)
					{
						login++;
					}
					else if(l.isLoggedin()== false)
						logoff++;
				}
		}
	
		if (login == logoff+1) {
			Log logtemp = new Log(utemp, false, timestamp); 
			logins.add(logtemp);
			numLog++;
			
		}
		
		return utemp;
	}
	
	public Utente verificaConnessioneUtente(String email) {
		Utente utemp = cercaUtente(email);  
		int numlogin = 0;
		int numlogoff = 0;
		
		if (utemp == null)
			return null;
		
		for (Log l : logins) {
			if (l!= null && l.getUtenti().getEmail().compareTo(email)==0) {
				if(l.isLoggedin()==true) {
					numlogin++;
				}
					
				else if (l.isLoggedin()==false)
					numlogoff++;
			}
		
		}
	
		
		if (numlogin > numlogoff)
			return utemp;
			
				
		return null;
	}
		
	public Utente eliminaUtente(String email, String password, String emailUtenteDaEliminare ) {
		Utente uelimtemp = cercaUtente(email); 
		Utente ueliminatotemp = cercaUtente(emailUtenteDaEliminare); 
		Log logtemp = new Log(ueliminatotemp, false, "cancellato");
		
		if(uelimtemp.getPassword().compareTo(password)!=0)
			ueliminatotemp = null;
		
		else 
		{
			if(uelimtemp instanceof Admin && uelimtemp!=null)
			{ uelimtemp = verificaConnessioneUtente(email);
			if(uelimtemp!=null) {
				if(ueliminatotemp instanceof Acquirente) {
					utenti.remove(ueliminatotemp); 
					numUtenti= numUtenti-1; 
					return ueliminatotemp;
				}
					
				else if (ueliminatotemp instanceof Admin && uelimtemp.getEmail().compareTo(ueliminatotemp.getEmail())==0) {
			//	if() 
					utenti.remove(ueliminatotemp);
					numUtenti = numUtenti-1; 
					return ueliminatotemp;
				}
			}
			else 
				ueliminatotemp = null; 
			}
			else if(uelimtemp instanceof Acquirente && uelimtemp!=null)
			{
				uelimtemp = verificaConnessioneUtente(email);
				if (uelimtemp != null) {
					if(uelimtemp.getEmail().compareTo(ueliminatotemp.getEmail())==0) {
						utenti.remove(ueliminatotemp);
						numUtenti = numUtenti-1; 
						return ueliminatotemp;
					}
				}
				else 
					ueliminatotemp = null;
				
			}
				
				
		}
		
		if(ueliminatotemp != null) {
			logins.add(logtemp);
			numLog++;
		}
		
			
		
		return ueliminatotemp;
	}
	
	public String elencoUtentiPerEmail() {
		String toString = ""; 
		for (int i = 0; i<numUtenti; i++ ) {
			if(utenti.get(i)!= null ) {
				toString += utenti.get(i).toString(); 
				if(i!= numUtenti-1)
					toString+="\n";
				
				}else 
					toString+= null;
				
			
		
		}
		return toString;
	}	
	
	public String elencoAccessiUtentePerTimestamp(String email) {
		Utente utemp = cercaUtente(email);
		String toString = "";
		
		if(utemp==null)
			toString = null;
	/*	for(Log l : logins)
			if(l!=null & l.getUtenti().getEmail().compareTo(email)==0) {
				if(l.isLoggedin()==true)
					toString += "Login : "+l.getTimestamp();
			//	else 
					//if(l.isLoggedin()==false)
			//		toString += "Logout : "+l.getTimestamp();
			}
				*/
		for(int i = 0 ; i<numLog; i++) {
			if(logins.get(i)!=null && logins.get(i).getUtenti().getEmail().compareTo(email)==0) {
				if(logins.get(i).isLoggedin()== true)
					toString += "login : "+logins.get(i).getTimestamp();
				else
					if(logins.get(i).isLoggedin()==false)
					toString += "logout : "+logins.get(i).getTimestamp();

				if(i!= numLog-1)
					toString+="\n";
				
				}//else 
					//toString+= null;
				
			}
				
				
		return toString;
	}
	
	public String elencoUtentiConnessiPerDataNascita() {
		LinkedList<Utente> utemp = null; 
		/*for (Utente u : utenti)
			if(u!=null)
			{
				utemp = verificaConnessioneUtente(); 
			}
		*/
		String toString="";
		
		//toString = utenti.toString(); 
		/*String utemp = ""; 
		for (Utente u : utenti)
			if(u != null)
				utemp += verificaConnessioneUtente(u.getEmail()); 
				*/
		for (int i = 0; i<numUtenti; i++ ) {
			if(utenti.get(i)!= null ) {
				toString += utenti.get(i).toString(); 
				if(i!= numUtenti-1)
					toString+="\n";
				
				}else 
					toString+= null;
		}
		return toString;
	
	}
	
	public String elencoUtentiPerCognome() {
		//ArrayList <Utente> utemp= utenti; 
	  String toString="";
		for (int i = 0; i<numUtenti; i++ ) {
			if(utenti.get(i)!= null ) {
				toString += utenti.get(i).toString(); 
			if(i!= numUtenti-1
					)
					toString+="\n";
				
				}else 
					toString+= null;
		}
		
		return toString;
		
	}		
}