import webservice.*;

public class Esempio {

	public static void main(String[] args) {

		WebService w = new WebService();
		
		System.out.println("Registrazione utente Fabrizio Lamberti");

		System.out.println("\nInformazioni utente aggiunto:");

		Utente u1 = w.registraUtente("Fabrizio", "Lamberti", "fabrizio.lamberti@polito.it", "Pwd12345", "20191105");

		System.out.println("Nome: " + u1.getNome());
		System.out.println("Cognome: " + u1.getCognome());
		System.out.println("Email: " + u1.getEmail());
		System.out.println("Password: " + u1.getPassword());
		System.out.println("Data di nascita: " + u1.getDataNascita());
		if(u1 instanceof Admin)
			System.out.println("Tipologia utente: admin");
		else if(u1 instanceof Acquirente)
			System.out.println("Tipologia utente: acquirente");
		else 
			System.out.println("Tipologia: N/D");

		System.out.println("\nRegistrazione utente Fabio Garcea");
		
		System.out.println("\nInformazioni utente aggiunto:");
		
		Utente u2 = w.registraUtente("Fabio", "Garcea", "fabio.garcea@polito.it", "Pwd67890", "20191203");

		System.out.println("Nome: " + u2.getNome());
		System.out.println("Cognome: " + u2.getCognome());
		System.out.println("Email: " + u2.getEmail());
		System.out.println("Password: " + u2.getPassword());
		System.out.println("Data di nascita: " + u2.getDataNascita());
		if(u2 instanceof Admin)
			System.out.println("Tipologia utente: admin");
		else if(u2 instanceof Acquirente)
			System.out.println("Tipologia utente: acquirente");
		else 
			System.out.println("Tipologia: N/D");
		
		System.out.println("\nRicerca utente Fabrizio Lamberti");
		
		Utente uTrovato = w.cercaUtente("fabrizio.lamberti@polito.it");

		System.out.println("\nInformazioni utente trovato:");
		
		System.out.println("Nome: " + uTrovato.getNome());
		System.out.println("Cognome: " + uTrovato.getCognome());
		System.out.println("Email: " + uTrovato.getEmail());
		System.out.println("Password: " + uTrovato.getPassword());
		System.out.println("Data di nascita: " + uTrovato.getDataNascita());
		if(uTrovato instanceof Admin)
			System.out.println("Tipologia utente: admin");
		else if(uTrovato instanceof Acquirente)
			System.out.println("Tipologia utente: acquirente");
		else 
			System.out.println("Tipologia: N/D");
		
		w.loginUtente("fabio.garcea@polito.it", "Pwd67890", "2019-12-03 00:58:25");
	
		
		System.out.println("\nVerifica connessione utente Fabio Garcea");
		
		Utente uConnesso = w.verificaConnessioneUtente("fabio.garcea@polito.it");
		
		System.out.println("\nInformazioni utente (se connesso):");	
		
		System.out.println("Nome: " + uConnesso.getNome());
		System.out.println("Cognome: " + uConnesso.getCognome());
		System.out.println("Email: " + uConnesso.getEmail());
		System.out.println("Password: " + uConnesso.getPassword());
		System.out.println("Data di nascita: " + uConnesso.getDataNascita());
		if(uConnesso instanceof Admin)
			System.out.println("Tipologia utente: admin");
		else if(uConnesso instanceof Acquirente)
			System.out.println("Tipologia utente: acquirente");
		else 
			System.out.println("Tipologia: N/D");
		
		System.out.println("\nRegistrazione utente Alberto Cannavo'");
		
		System.out.println("\nInformazioni utente aggiunto:");
		
		Utente u3 = w.registraUtente("Alberto", "Cannavo'", "alberto.cannavo@polito.it", "Pwd98765", "20191109");

		System.out.println("Nome: " + u3.getNome());
		System.out.println("Cognome: " + u3.getCognome());
		System.out.println("Email: " + u3.getEmail());
		System.out.println("Password: " + u3.getPassword());
		System.out.println("Data di nascita: " + u3.getDataNascita());
		if(u3 instanceof Admin)
			System.out.println("Tipologia utente: admin");
		else if(u3 instanceof Acquirente)
			System.out.println("Tipologia utente: acquirente");
		else 
			System.out.println("Tipologia: N/D");
		
		System.out.println("\nLogin di Fabrizio Lamberti");
		w.loginUtente("fabrizio.lamberti@polito.it", "Pwd12345", "2019-12-04 13:02:10");
		
		System.out.println("\nRimozione dell'utente Alberto Cannavo'");
		Utente uEliminato = w.eliminaUtente("fabrizio.lamberti@polito.it", "Pwd12345", "alberto.cannavo@polito.it");
		
		System.out.println("\nInformazioni utente eliminato:");
		
		System.out.println("Nome: " + uEliminato.getNome());
		System.out.println("Cognome: " + uEliminato.getCognome());
		System.out.println("Email: " + uEliminato.getEmail());
		System.out.println("Password: " + uEliminato.getPassword());
		System.out.println("Data di nascita: " + uEliminato.getDataNascita());
		if(uEliminato instanceof Admin)
			System.out.println("Tipologia utente: admin");
		else if(uEliminato instanceof Acquirente)
			System.out.println("Tipologia utente: acquirente");
		else 
			System.out.println("Tipologia: N/D");
		
		System.out.println("\nRegistrazione utenti Lia Morra, Davide Calandra e Cesare Sartirana");
		
		w.registraUtente("Lia", "Morra", "lia.morra@polito.it", "Pwd11111", "20191126");
		w.registraUtente("Davide", "Calandra", "davide.calandra@polito.it", "Pwd22222", "20191220");
		w.registraUtente("Cesare", "Sartirana", "cesare.sartirana@polito.it", "Pwd33333", "20171229");
		
		System.out.println("\nElenco utenti per email (ordine alfabetico inverso):");

		String elencoUtentiEmail= w.elencoUtentiPerEmail();
		System.out.println(elencoUtentiEmail);
		
		System.out.println("\nLogin e logout multipli dell'utente Davide Calandra: ");
		w.loginUtente("davide.calandra@polito.it", "Pwd22222", "2019-12-03 10:58:25");
		w.logoutUtente("davide.calandra@polito.it", "2019-12-03 00:44:38");
		w.loginUtente("davide.calandra@polito.it", "Pwd22222", "2019-12-02 18:44:49");
		w.logoutUtente("davide.calandra@polito.it", "2019-12-01 18:44:49");
		w.loginUtente("davide.calandra@polito.it", "Pwd22222", "2019-12-01 13:58:25");
		
		System.out.println("\nElenco accessi utente Davide Calandra per timestamp (ordine crescente):");

		String elencoAccessiTimestamp= w.elencoAccessiUtentePerTimestamp("davide.calandra@polito.it");
		System.out.println(elencoAccessiTimestamp);
		
		System.out.println("\nLogin dell' utente Cesare Sartirana");
		
		w.loginUtente("cesare.sartirana@polito.it", "Pwd33333", "2019-12-04 13:00:10");

		System.out.println("\nElenco utenti connessi per data di nascita (ordine crescente):");

		String elencoUtentiConnessi = w.elencoUtentiConnessiPerDataNascita();
		System.out.println(elencoUtentiConnessi);
		
		System.out.println("\nElenco utenti per cognome (ordine alfabetico):");

		String elencoUtentiCognome= w.elencoUtentiPerCognome();
		System.out.println(elencoUtentiCognome);

	}

}
