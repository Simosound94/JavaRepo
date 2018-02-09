import java.util.LinkedList;

public class GeneratoreEventi implements Runnable {
	
	private LinkedList<Osservato> toNotify;
	private int nome;
	

	public GeneratoreEventi(LinkedList<Osservato> toNotify, int nome) {
		super();
		this.toNotify = toNotify;
		this.nome = nome;
	}


	@Override
	public void run() {
		try{
			while(true){
				Thread.sleep((int)(Math.random()*200+200));
				int event = (int)(Math.random()*50);
				System.out.println("GenEventi #"+nome+" genera evento: "+event);
				for(Osservato o : toNotify){
					o.nuovoEvento(event);
				}
			}
		}catch(Exception e){e.printStackTrace();}
	}

}
