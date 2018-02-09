import java.io.Serializable;

public class SubscribeRequest implements Serializable {
	
	public int idSubscriber;

	@Override
	public String toString() {
		return "SubscribeRequest [idSubscriber=" + idSubscriber + "]";
	}
	
	

}
