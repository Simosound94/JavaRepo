package driverExercise;

public class MainApp {

	public static void main(String[] args) {
		Driver driver = new Driver();
		for (int i = 0; i<10; i++){
			Task t = new Task(i , driver);
			Thread t_t = new Thread(t);
			t_t.start();
		}
		TimerInterrupter timer = new TimerInterrupter(driver);
		Thread t_timer = new Thread(timer);
		t_timer.start();
	}

}
