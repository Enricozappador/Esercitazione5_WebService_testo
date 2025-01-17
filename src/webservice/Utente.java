package webservice;

import java.util.Collections;
import java.util.LinkedList;

//import java.util.LinkedList;

public class Utente {
	private String nome;
	private String cognome;
	private String email;
	private String psw;
	private String data;
//	private LinkedList<Utente> utenti; 
	
	public Utente(String nome, String cognome, String email, String psw, String data) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.psw = psw;
		this.data = data;
	}
	public String getNome() {
		return nome;
	}
	
	public String getCognome() {
		return cognome;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return psw;
	}
	
	public String getDataNascita() {
		return data;
	}
	public boolean controllUpperCase(String psw) {
        char ch = ' ';
        int i = getPassword().length();
        boolean flag = false;
        for (int k=0; k<i ; k++)
            ch = psw.charAt(k);
            if (Character.isUpperCase(ch)==true)
                flag = true;


        return flag;

    }
    public boolean controllLowerCase(String psw) {
        char ch = ' ';
        int i = getPassword().length();
        boolean flag = false;
        for (int k=0; k<i ; k++)
            ch = psw.charAt(k);
            if (Character.isLowerCase(ch)==true)
                flag = true;

        return flag;

    }
    @Override
    public String toString() { 
    	/*String string =""; 
    	for (Utente u : utenti)
    	if(u!=null)
    		string += u.getEmail()+" "+u.getCognome()+" "+u.getNome(); 
    		*/
    	
    	return email+" "+cognome+" "+nome; 
    }
	public void setPsw(String psw) {
		this.psw = psw;
	}
	public void printpsw (String psw) {
		
	}
	
	public void ordinaemail (String email) {
		LinkedList<String> mail = new LinkedList<String>();
		
		Collections.sort(mail);
		
	}
	
	
	
}
