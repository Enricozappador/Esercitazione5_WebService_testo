package webservice;

import java.util.ArrayList;
import java.util.Collections;
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
		boolean islower = false; 
		boolean isupper = false; 
		boolean isnumber = false; 
	/*	int numutentifittizio = 0;
		int numadminfittizio = 0;
		int numacqfittizio = 0;
		LinkedList<Admin> adminfittizio = new LinkedList <Admin>();
		LinkedList<Utente>utentifittizio = new LinkedList <Utente>();
		LinkedList<Acquirente> acquirentefittizio = new LinkedList <Acquirente>();*/
		 String controllo = "@";
		 
		 
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
	        	

	     //   Utente uttemp = utemp;
	       /* if (numutentifittizio == 0)
	        {
	        	Admin atemp2 = new Admin(nome, cognome, email, password, dataNascita);
	        	adminfittizio.add(numadminfittizio++, atemp2);
	        	uttemp = atemp2;
	        	 utentifittizio.add(numutentifittizio++, uttemp);
	        }
	        else 
	        {
	        	Acquirente actemp2 = new Acquirente(nome, cognome, email, password, dataNascita);
	        	acquirentefittizio.add(numacqfittizio++, actemp2);
	        	uttemp=actemp2;
	        	
	        	 utentifittizio.add(numutentifittizio++,uttemp);
	        }
	        uttemp.setPsw(null);
*/
	        //uttemp.setPsw(null);
for (int i= 0 ; i<password.length(); i++) {
			 char ch = password.charAt(i);
			 if (Character.isLowerCase(ch)==true)
				 islower = true;
			 if(Character.isUpperCase(ch)==true)
				 isupper = true; 
			 if(Character.isDigit(ch) == true)
				 isnumber = true; 
		 }
		 
	        if (email.contains(controllo)== false)
	            utemp=null;
	        
	       if (password.length()<8 || islower == false || isupper == false || isnumber == false
	    		   //|| utemp.controllLowerCase(password)== false || utemp.controllUpperCase(password)== false
	    		   )
	            utemp = null;
	       
	       
	       
	       
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
			//System.out.println("fatto");
		}
	/*	else 
			System.out.println("nonfatto");
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
		//Log logtemp = new Log(ueliminatotemp, false, "cancellato");
		
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
		
		/*if(ueliminatotemp != null) {
			logins.add(logtemp);
			numLog++;
	
		}
		*/
			
		
		return ueliminatotemp;
	}
	
	public String elencoUtentiPerEmail() {
		LinkedList <String> utentiordinati = new LinkedList<String>() ;
		String toString = ""; 
		int numUtentiordinati = numUtenti;
		
		for (int i = 0; i<numUtentiordinati; i++) {
			utentiordinati.add(utenti.get(i).getEmail());
			
		}
		Collections.sort(utentiordinati, Collections.reverseOrder());
		
		for (int i = 0; i<numUtentiordinati; i++ ) {
			if(utentiordinati.get(i)!= null ) {
				toString += utentiordinati.get(i).toString(); 
				if(i!= numUtentiordinati-1)
					toString+="\n";
				
				}
			//else 
				//	toString+= null;
				
			
		
		}
		return toString;
	}	
	
	public String elencoAccessiUtentePerTimestamp(String email) {
		Utente utemp = cercaUtente(email);
		LinkedList <String> timelogin = new LinkedList<String>() ;
		LinkedList <String> timelogout = new LinkedList<String>();
		int numlogin = 0;
		int numlogout = 0;
		LinkedList <String> time = new LinkedList<String>();
		int numtime = 0; 
		String toString = "";
		int numfit = 0;
		
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
		
		/*for (int i=0; i<numLog; i++) {
			if(logins.get(i)!=null)
				logins.get(i).ordinatime(logins.get(i).getTimestamp());
		}
		*/
		
		for(int i = 0 ; i<numLog; i++) {
			if(logins.get(i)!=null && logins.get(i).getUtenti().getEmail().compareTo(email)==0) {
				if(logins.get(i).isLoggedin()== true) {
					timelogin.add(logins.get(i).getTimestamp());
				numlogin++;
				time.add(numtime++,logins.get(i).getTimestamp());
				}
				else
					if(logins.get(i).isLoggedin()==false) {
					timelogout.add(logins.get(i).getTimestamp());
					numlogout++;
					time.add(numtime++, logins.get(i).getTimestamp());
					}
			//	if(i!= numLog-1)
				//	toString+="\n";
				
				}
		
				
			}
		Collections.sort(time);
		
		for(int k = 0; k<numtime; k++) {
			if (time.get(k) != null ) 
			{
				/*for(int i = 0; i<numlogin ; i++) {
					if(timelogin.get(i)!=null)
						numfit = i;
				}*/
				for(int j=0; j<numlogin ; j++) {
					if(timelogin.get(j)!=null && timelogin.get(j).compareTo(time.get(k))==0) {
						
						toString += "login : "+timelogin.get(j);
						
						
					}
				}
				
				for (int s = 0; s<numlogout ; s++) {
					if(timelogout.get(s)!=null && timelogout.get(s).compareTo(time.get(k))==0) {
						toString += "logout : "+ timelogout.get(s);
					}
				}
			
			//toString += timelogin.get(k);
			
			if(k!= numtime-1)
				toString+="\n";
			}
		}
				
				
		return toString;
	}
	
	public String elencoUtentiConnessiPerDataNascita() {
		LinkedList<Utente> utemp = utenti; 
		LinkedList<Utente> uutemp = new LinkedList<Utente>();
		
		int numutemp = 0;
		LinkedList<String> data = new LinkedList <String>();
		int numdata = 0; 
		int x = 0;
		int y= 0; 
		
	//	String connect= "";
		/*for (int i = 0 ; i<numUtenti; i++) {
			if(utenti.get(i)!=null )
				//	&& verificaConnessioneUtente(utenti.get(i).getEmail())==null)
				{
			utemp.remove(utenti.get(i));
			numutemp = numutemp-1;
		
		/*for (Utente u : utenti)
			if(u!=null)
			{
				utemp = verificaConnessioneUtente(); 
			}
		
		}
		}*/
		String toString="";
		
		//toString = utenti.toString(); 
		/*String utemp = ""; 
		for (Utente u : utenti)
			if(u != null)
				utemp += verificaConnessioneUtente(u.getEmail()); 
				*/
		for (int i = 0; i<numUtenti; i++ ) {
			if(utemp.get(i)!= null && verificaConnessioneUtente(utenti.get(i).getEmail())!=null) {
				//if (verificaConnessioneUtente(utenti.get(i).getEmail())!=null)
			uutemp.add(utenti.get(i)); 
			numutemp++;
			data.add(utenti.get(i).getDataNascita());
			numdata++;
				
				//if(i!= numUtenti-1)
					//toString+="\n";
				
				}
		}
			Collections.sort(data);
			
			for(int k= 0 ; k<numdata ; k++) {
				if(data.get(k)!=null) {
					for (int j = 0; j<numutemp; j++) {
						if(uutemp.get(j)!=null && uutemp.get(j).getDataNascita().compareTo(data.get(k))==0) {
							x = j;
						}
							
					} 
					for (int d = 0 ; d<numUtenti ; d++) {
						if(utenti.get(d)!=null && utenti.get(d).getEmail().compareTo(uutemp.get(x).getEmail())==0) {
							y = d;
						}
					}
					
					toString += utenti.get(y).toString();
					uutemp.remove(uutemp.get(x));
					numutemp = numutemp-1;
					
					if(k!= numdata) {
						toString +="\n";
					}
					
				}
					
			}
			
			
			//else 
				//	toString+= null;
		
		return toString;
	
	}
	
	public String elencoUtentiPerCognome() {
		//ArrayList <Utente> utemp= utenti; 
		LinkedList<String> stringa = new LinkedList<String>();
		LinkedList<Utente> utemp= utenti;
		int numut = numUtenti;
		int numtemp = numUtenti;
		int numutfit = 0;
	  String toString="";
	  
	  for(int i = 0; i<numut ; i++) {
		  stringa.add(utenti.get(i).getCognome());
		  }
	  
	  Collections.sort(stringa);
	  
	  for (int i = 0; i<numut; i++ ) {
			if(stringa.get(i)!= null ) {
				for(int k=0 ; k<numtemp ; k++) {
					if(utemp.get(k)!=null && utemp.get(k).getCognome().compareTo(stringa.get(i)) == 0) {
						numutfit = k;
					
					}
				}
				toString += utenti.get(numutfit).toString(); 
				utemp.remove(utenti.get(numutfit));
				numtemp = numtemp - 1;
				stringa.add(toString);
				
				
			if(i!= numut-1)
					toString+="\n";
				
				}
	  }
	  
	  
	/*	for (int i = 0; i<numUtenti; i++ ) {
			if(utenti.get(i)!= null ) {
				toString += utenti.get(i).toString(); 
				stringa.add(toString);
			if(i!= numUtenti-1
					)
					toString+="\n";
				
				}
			//else 
				//	toString+= null;
		}*/
		
		Collections.sort(stringa);
		
		return toString;
		
	}		
}