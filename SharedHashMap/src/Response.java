import java.io.Serializable;

public class Response implements Serializable {
	
	public boolean ack;
	public String data;
	public int key;
	
	@Override
	public String toString() {
		return "key: "+key+" data: "+data;
	}

}
