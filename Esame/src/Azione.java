import java.io.Serializable;

public class Azione implements Serializable {
	
	public static enum Tipo {crea, ellimina};
	
	public Tipo tipo;
	public String nomeFile;
	
	@Override
	public String toString() {
		return "[ Tipo: "+tipo+" File: "+nomeFile+" ]";
	}
	
	
	

}
