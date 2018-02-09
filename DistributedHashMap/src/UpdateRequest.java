import java.io.Serializable;

public class UpdateRequest implements Serializable {
	@Override
	public String toString() {
		return "UpdateRequest [type=" + type + ", key=" + key + ", data=" + data + "]";
	}
	//La get non serve, non implica cercare in altre HashMap
	public static enum RequestTypes {delete, put, get};

	
	public RequestTypes type;
	public String key;
	public Data data;
	@Override
	public boolean equals(Object obj) {
		UpdateRequest other = (UpdateRequest) obj;
		if(this.type != other.type)
			return false;
		if(this.key != other.key)
			return false;
		if(this.data != other.data)
			return false;
		return true;
		
	}

}
