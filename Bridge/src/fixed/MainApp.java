package fixed;

public class MainApp {

	public static void main(String[] args) {
		Bridge b = new Bridge();
		
		TimerExit te = new TimerExit(b);
		Thread tte = new Thread(te);
		
		for(int i = 0; i<3; i++){
			Car c = new Car(b, i);
			Thread tc = new Thread(c);
			tc.start();
		}
		
		tte.start();
	}

}
