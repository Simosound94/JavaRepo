import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;

public class ErrMess implements Serializable {
	public int errCode;
	public String errMess;
	public LinkedList<Date> timeSteps;
	
	public ErrMess(){
		this.timeSteps = new LinkedList<Date>();
	}
	
	
	@Override
	public String toString() {
		return "ErrMess [errCode=" + errCode + ", errMess=" + errMess + "]";
	}
	
	
}
