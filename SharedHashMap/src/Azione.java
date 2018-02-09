import java.io.Serializable;

public class Azione implements Serializable {
	
	public static enum Tipo{get, put, delete};
	
	public Tipo type;
	public int key;
	public String data;
	
	@Override
	public String toString() {
		return type+": key: "+key+" data: "+data;
	}
	
	

}
