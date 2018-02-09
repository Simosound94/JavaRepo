import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;

public class ServerWorker implements Runnable {
	
	
	private Socket s;
	private HashMap<String, LinkedList<Oggetto>> magazzino;


	public ServerWorker(Socket s, HashMap<String, LinkedList<Oggetto>> magazzino) {
		super();
		this.s = s;
		this.magazzino = magazzino;
	}




	@Override
	public void run() {
		try{
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.flush();
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			
			Object o = ois.readObject();
			if(o instanceof RichiestaOggetto){
				System.out.println("Arrived request");
				RichiestaOggetto r = (RichiestaOggetto) o;
				LinkedList<Oggetto> categoria = null;
				synchronized(magazzino){
					while(!magazzino.containsKey(r.categoria)){
						System.out.println("Never had category "+r.categoria);
						magazzino.wait();
					}
					categoria = magazzino.get(r.categoria);
				}
				//per ragioni di efficienza nel non svegliare troppi thread
				//se una categoria è vuota sveglia/addormentati solo su quella categoria
				Oggetto risp = null;
				synchronized(categoria){
					while(categoria.isEmpty()){
						System.out.println("Empty category "+r.categoria);
						categoria.wait();
					}
					risp = categoria.removeFirst();
				}
				oos.writeObject(risp);
			}
			
			else if(o instanceof Oggetto){
				Oggetto ogg = (Oggetto) o;
				LinkedList<Oggetto> categoria = null;
				System.out.println("Object arrived "+ogg.categoria);
				synchronized(magazzino){
					if(!magazzino.containsKey(ogg.categoria)){
						System.out.println("New category "+ogg.categoria);
						magazzino.put(ogg.categoria, new LinkedList<Oggetto>());
						magazzino.notify();
					}
					categoria = magazzino.get(ogg.categoria);
				}
				synchronized(categoria){
					categoria.add(ogg);
					categoria.notify();
				}
			}
		}catch(Exception e){e.printStackTrace();}
	}

}
