import java.io.Serializable;

public class Pacchetto implements Serializable {

	public int id;
	public String protocollo;
	public String infoNecessarie;
	@Override
	public String toString() {
		return "Pacchetto [id=" + id + ", protocollo=" + protocollo + ", infoNecessarie=" + infoNecessarie + "]";
	}
	
	
}
