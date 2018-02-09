package deadlock;

public class Scheduler {

	public static void main(String[] args) {
		Object mutex1 = new Object();
		Object mutex2 = new Object();
		
		Runner_One one = new Runner_One(mutex1, mutex2);
		Runner_Two two = new Runner_Two(mutex1, mutex2);
		
		Thread t_one = new Thread(one);
		Thread t_two = new Thread(two);
		
		t_one.start();
		t_two.start();
	}

}
