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
		/*
		 * 
		 *  NON SI PUÒ FARE LA SYNCHRONIZED SOLO SULLE SINGOLE ROOM, AVREMMO DELLE CORSE
		 *  LA PROCEDURA DI SEARCH DI UNA ROOM DEVE ESSERE SINCRONIZZATA, IN MODO CHE MENTRE
		 *  CERCO UNA STANZA E NE INDIVIDUO UNA VUOTA NESSUNO LA POSSA PRENOTARE
		 *  (A MENO CHE NON FACCIA UNA COSA ATOMICA, TIPO OPERAZIONE TEST & SET
		 *  ES. OPERAZIONE ROOM: PRENOTA SE LIBERA)
		 *  
		 *  LA STESSA COSA SI APPLICA AD I BUFFER CONDIVISI, NON POSSO SINCRONIZZARE I SINGOLI ELEMENTI
		 *  PER FARE ALCUNE OPERAZIONI TIPO PROBLEMA BUFFER PIENO/VUOTO LETTORI/SCRITTORI
		 *  DEVO SINCRONIZZARE LA STRUTTURA
		 *  (PER FARE OPERAZIONI COME WRITE ELEMENTO MI BASTA SINCRONIZZARE L'ELEMENTO)
		 *  
		 *  
		 */
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
