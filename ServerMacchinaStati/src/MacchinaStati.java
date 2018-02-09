
public class MacchinaStati implements Runnable{
	
	private FIFOMacchinaStati fifo;
	private int stato;

	public MacchinaStati(FIFOMacchinaStati fifo) {
		super();
		this.fifo = fifo;
		this.stato = 0;
	}

	@Override
	public void run() {
		while(true){
			Pacchetto p = fifo.pull();
			//Elaborazione pacchetto...
			System.out.println("Elaborazione pacchetto: "+p);
			
			try {
				Thread.sleep(2000);
				//Cambio lo stato..
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	

}
