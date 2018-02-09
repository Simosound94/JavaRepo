import java.io.Serializable;


public class Event implements Serializable {
	private String payload;
	private Arguments.args argomento;
	
	
	public String getPayload() {
		return payload;
	}
	public void setPayload(String payload) {
		this.payload = payload;
	}
	public Arguments.args getArgomento() {
		return argomento;
	}
	public void setArgomento(Arguments.args argomento) {
		this.argomento = argomento;
	}
	public Event(String payload, Arguments.args argomento) {
		super();
		this.payload = payload;
		this.argomento = argomento;
	}
	@Override
	public String toString() {
		return "Event [payload=" + payload + ", argomento=" + argomento + "]";
	}
	
	
	
}
