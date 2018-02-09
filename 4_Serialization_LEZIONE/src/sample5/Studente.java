package sample5;

import java.io.Serializable;
import java.util.Date;

public abstract class Studente implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String matricola;
	private String nome;
	private String cognome;
	private Date dataIscrizione;
	private int annoFrequenza;
	private String corsoDiStudi;
	
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
	public abstract String toString();
	public String getCorsoDiStudi() {
		return corsoDiStudi;
	}
	public void setCorsoDiStudi(String corsoDiStudi) {
		this.corsoDiStudi = corsoDiStudi;
	}
	
	
}
