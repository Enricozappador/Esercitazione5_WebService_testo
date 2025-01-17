package webservice;

import java.util.Collections;
import java.util.LinkedList;

public class Log {
	private Utente utenti; 
	private boolean loggedin; 
	private String timestamp;
	LinkedList<String> time = new LinkedList<String>();
	public Log(Utente utenti, boolean loggedin, String timestamp) {
		super();
		this.utenti = utenti;
		this.loggedin = loggedin;
		this.timestamp = timestamp;
	}
	public Utente getUtenti() {
		return utenti;
	}
	public void setUtenti(Utente utenti) {
		this.utenti = utenti;
	}
	public boolean isLoggedin() {
		return loggedin;
	}
	public void setLoggedin(boolean loggedin) {
		this.loggedin = loggedin;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	} 
	//@Override	
	/*public String toString() {
		
		return null;
		
	}*/
	
	public void ordinatime(String timestamp) {
		
		time.add(timestamp);
		
		Collections.sort(time);
	}

}
