package studentrooms;

public class MainApp {

	public static void main(String[] args) {
		StudentRooms rooms = new StudentRooms(4);
		
		for(int i = 0; i < 10; i++) {
			Student s = new Student("S" + i, rooms);
			Thread t_s = new Thread(s);
			t_s.start();
		}

	}

}
