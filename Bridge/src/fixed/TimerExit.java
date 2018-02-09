package fixed;
import java.util.Random;

public class TimerExit implements Runnable {

	private Bridge b;
	
	
	
	public TimerExit(Bridge b) {
		super();
		this.b = b;
	}



	@Override
	public void run() {
		try{
			Random r = new Random();
			while(true){
				Thread.sleep((long) ((r.nextInt(3))*5000));
				b.exit();
			}
		}catch(Exception e){e.printStackTrace();}

	}

}
