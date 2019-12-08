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
		Log logtemp = new Log(utemp, true, timestamp) ;
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
		if(countlogin < countlogout) {
			logins.add(logtemp);
			numLog++;
		}
			
			/*else 
				utemp = null; */
		
		
		return utemp;
	}
	
	public Utente logoutUtente(String email, String timestamp) {
		Utente utemp = cercaUtente(email); 
		Log logtemp = new Log(utemp, false, timestamp); 
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
	
		
		if (numlogin == numlogoff+1)
			utemp = null;
			
				
		return utemp;
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
				if(ueliminatotemp instanceof Acquirente)
					return ueliminatotemp;
				else if (ueliminatotemp instanceof Admin && uelimtemp.getEmail().compareTo(ueliminatotemp.getEmail())==0)
			//	if()
					return ueliminatotemp;
			}
			else 
				ueliminatotemp = null; 
			}
			else if(uelimtemp instanceof Acquirente && uelimtemp!=null)
			{
				uelimtemp = verificaConnessioneUtente(email);
				if (uelimtemp != null) {
					if(uelimtemp.getEmail().compareTo(ueliminatotemp.getEmail())==0)
						return ueliminatotemp;
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
		//for (Utente u : utenti )
		//utenti.sort'm'8);
		return null;
	}	
	
	public String elencoAccessiUtentePerTimestamp(String email) {
		Utente utemp = cercaUtente(email);
		String toString = " ";
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
				//if(logins.get(i).isLoggedin()== true)
					//toString += "login : "+logins.get(i).getTimestamp();
				//else
					if(logins.get(i).isLoggedin()==false)
					toString += "logout : "+logins.get(i).getTimestamp();

				if(i!= numLog-1)
					toString+="\n";
				
				}else 
					toString+= null;
				
			}
				
				
		return toString;
	}
	
	public String elencoUtentiConnessiPerDataNascita() {
		return null;
	}
	
	public String elencoUtentiPerCognome() {
		return null;
	}		
}