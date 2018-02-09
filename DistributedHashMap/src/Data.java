import java.io.Serializable;

public class Data implements Serializable {

	public String key;
	
	
	public String data;
	
	@Override
	public String toString() {
		return "Data [key=" + key + ", data=" + data + "]";
	}
	
	public boolean equals(Object obj) {
		Data other =(Data) obj;
		if(!this.key.equals(other.key)){
			return false;
		}
		if(!this.data.equals(other.data)){
			return false;
		}
		return true;
	}

	
}
