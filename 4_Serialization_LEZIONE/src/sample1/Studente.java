package sample1;

import java.io.Serializable;
import java.util.Date;

public class Studente implements Serializable {
	
	private String matricola;
	private String nome;
	private String cognome;
	private Date dataIscrizione;
	private int annoFrequenza;
	
	public String getMatricola() {
		return matricola;
	}
	public void setMatricola(String matricola) {
		this.matricola = matricola;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public Date getDataIscrizione() {
		return dataIscrizione;
	}
	public void setDataIscrizione(Date dataIscrizione) {
		this.dataIscrizione = dataIscrizione;
	}
	public int getAnnoFrequenza() {
		return annoFrequenza;
	}
	public void setAnnoFrequenza(int annoFrequenza) {
		this.annoFrequenza = annoFrequenza;
	}
	
	public String toString() {
		return "Matricola: " + this.getMatricola() +
				" Nome: " + this.getNome() +
				" Cognome: " + this.getCognome() +
				" Anno Iscrizione: " + this.getDataIscrizione() +
				" Anno Frequenza: " + this.getAnnoFrequenza();
	}
	
	
}
