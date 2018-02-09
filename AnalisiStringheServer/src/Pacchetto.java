import java.io.Serializable;

public class Pacchetto implements Serializable {
	
	public static enum Richiesta {submit, reply, syncReply};
	
	public Richiesta type;
	public String stringa;
	public int charDiv;
	public int maxOccChar;
	public int idClient;
	public int idRequest;
	
	
	public Pacchetto(Richiesta type, String stringa, int charDiv, int maxOccChar, int idClient, int idRequest) {
		super();
		this.type = type;
		this.stringa = stringa;
		this.charDiv = charDiv;
		this.maxOccChar = maxOccChar;
		this.idClient = idClient;
		this.idRequest = idRequest;
	}
	@Override
	public String toString() {
		return "type: "+type+" idClient: "+idClient+" idReq: "+idRequest+" string: "+stringa;
	}

}
