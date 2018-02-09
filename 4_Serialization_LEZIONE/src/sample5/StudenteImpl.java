package sample5;

public class StudenteImpl extends Studente {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	public String toString() {
		return "Matricola: " + this.getMatricola() +
				" Nome: " + this.getNome() +
				" Cognome: " + this.getCognome() +
				" Anno Iscrizione: " + this.getDataIscrizione() +
				" Anno Frequenza: " + this.getAnnoFrequenza() +
				" Corso di studi: " + this.getCorsoDiStudi();
	}

}
