package sample3;

import java.io.Serializable;

public class CorsoDiStudi implements Serializable {
	
	private String nome;
	private String tipo; // specialistica/magistrale
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
}
