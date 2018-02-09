import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Richiesta implements Serializable {
	
	public String descrizione;
	public String ntel;
	public int id;
	public String ipClient;
	public int portClient;
	
	
	@Override
	public String toString() {
		return "ID: "+id+" portClient: "+portClient+" descrizione: "+descrizione;
	}

}
