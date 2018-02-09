package studentrooms;

import java.util.ArrayList;
import java.util.List;

public class StudentRooms {
	private List<Room> rooms;

	// costruttore 
	// accetta il numero di sale studio
	public StudentRooms(int n) {
		this.rooms = new ArrayList<Room>(n);
		for(int i = 0; i < n; i++) {
			Room r = new Room(i);
			this.rooms.add(r);
		}
	}

	//
	// metodo per ottenere una sala studio...
	//
	public Room getRoom() {
		Room availableRoom = null;

		synchronized(this.rooms)  {
			while(availableRoom == null) {
				for(Room r : this.rooms) {
					if(!r.isBusy()) {
						availableRoom = r;
						break;
					}
				}
				if(availableRoom == null) {
					try {
						System.out.println("NO ROOMS AVAILABLE. MUST WAIT");
						this.rooms.wait();
					} catch (InterruptedException e) {

					}
				}
			}
			availableRoom.occupy();			
		}

		return availableRoom;
	}

	//
	// metodo per rilasciare la sala studio
	//
	public void releaseRoom(Room r) {
		synchronized(this.rooms) {
			r.free();
			this.rooms.notify();
		}
	}

}
