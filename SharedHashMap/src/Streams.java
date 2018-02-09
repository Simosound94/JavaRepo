import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Streams {

	public ObjectOutputStream out;
	public ObjectInputStream in;
	
	public Streams(ObjectOutputStream out, ObjectInputStream in) {
		super();
		this.out = out;
		this.in = in;
	}
}
