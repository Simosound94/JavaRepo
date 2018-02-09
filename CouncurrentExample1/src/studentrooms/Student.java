package studentrooms;

public class Student implements Runnable {
	private String name;
	private StudentRooms rooms;

	public Student(String name, StudentRooms rooms) {
		this.name = name;
		this.rooms = rooms;
	}

	public void run() {
		// simulare uno studente...
		// sleep x N secondi
		// ottiene sala studio
		// usa sala per M secondi
		// rilascia sala studio...
		while(true) {
			long sleepMs = (long)(Math.random() * 10000);
			System.out.println("Student " + this.name + " sleeps for " + sleepMs);
			try {
				Thread.sleep(sleepMs);
			} catch (Exception e) {

			}

			Room r = this.rooms.getRoom();

			System.out.println("Student " + this.name + " got room #" + r.getNumber());

			long studyMs = (long)(Math.random() * 10000);

			System.out.println("Student " + this.name + " study for " + studyMs);

			try {
				Thread.sleep(studyMs);
			} catch (Exception e) {

			}

			System.out.println("Student " + this.name + " release room #" + r.getNumber());

			this.rooms.releaseRoom(r);
		}

	}
}
