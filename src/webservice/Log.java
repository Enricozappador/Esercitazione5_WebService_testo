package webservice;

public class Log {
	private Utente utenti; 
	private boolean loggedin; 
	private String timestamp;
	public Log(Utente utenti, boolean loggedin, String timestamp) {
		super();
		this.utenti = utenti;
		this.loggedin = loggedin = false;
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
	

}
