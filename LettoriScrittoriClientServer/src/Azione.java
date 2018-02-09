import java.io.Serializable;

public class Azione implements Serializable {
	
	public static enum Tipo {lettura, scrittura, fineLettura, inserisci}
	
	public Tipo tipo;
	public int idFile;
	public byte[] file;

}
