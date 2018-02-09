import java.io.Serializable;
import java.util.Date;

public class Messaggio implements Serializable {
	
	public String mittente;
	public String ricevente;
	public String testo;
	public Date time;
	@Override
	public String toString() {
		return "Mittente: "+mittente+" -> "+testo;
	}

}
