import java.util.LinkedList;

public class FIFOMacchinaStati {

	private LinkedList<Pacchetto> fifo;
	
	public FIFOMacchinaStati(){
		fifo = new LinkedList<Pacchetto>();
	}
	
	public synchronized void push(Pacchetto p){
		fifo.add(p);
		this.notify();
	}
	
	
	
	public synchronized Pacchetto pull(){
		try{
			while(fifo.size() == 0){
				this.wait();
			}
		}catch(Exception e){e.printStackTrace();}
		
		return fifo.removeFirst();
	}
	
	
	
}
