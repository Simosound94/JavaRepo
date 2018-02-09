import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.swing.text.html.HTMLDocument.Iterator;


public class MercatoServerWorker implements Runnable {

	HashMap<Merce.Tipi, LinkedList<Offerta>> offerte;
	Socket s;
	


	public MercatoServerWorker(Socket s, HashMap<Merce.Tipi, LinkedList<Offerta>> offerte) {
		super();
		this.s = s;
		this.offerte = offerte;
	}
	
	@Override
	public void run() {
		try{
			OutputStream os = s.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			oos.flush();
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			Object o = ois.readObject();
			
			if(o instanceof Offerta){
				Offerta off = (Offerta) o;
				off.produttore = oos;
				LinkedList<Offerta> toAdd = offerte.get(off.merce);
				synchronized(toAdd){
					toAdd.add(off);
				}
				System.out.println("Added "+off.qta+" of "+off.merce);
			}
			else if(o instanceof Domanda){
				Domanda d = (Domanda) o;
				System.out.println("Arrived domanda  of "+d.qta+ " "+ d.merce);
				LinkedList<Offerta> offerteSelezionate = offerte.get(d.merce);
				synchronized(offerteSelezionate){
					Offerta off;
					int qtaSelled = 0;
					ListIterator<Offerta> it = offerteSelezionate.listIterator();
					while(d.qta > 0 && it.hasNext()){
						off = it.next();
						if(off.qta > d.qta){
							qtaSelled = d.qta;
							off.qta -= d.qta;
							d.qta = 0;
						}
						else{
							qtaSelled = off.qta;
							d.qta -= off.qta;
							off.qta = 0;
							offerteSelezionate.remove(off);
							it = offerteSelezionate.listIterator();
						}
						int cost = qtaSelled * MercatoServer.PREZZO;
						off.produttore.writeObject(new Integer(cost));
						oos.writeObject(new Integer(cost));
						if(off.qta == 0){
							off.produttore.writeObject(0);
						}
					}
					if(d.qta>0){
						//Se non ce la faccio a soddisfare la domanda invio -1
						oos.writeObject(new Integer(-1));
					}
					else{
						oos.writeObject(new Integer(0));
					}
				}
			}
			
			
		}catch(Exception e){e.printStackTrace();}
	}

}
