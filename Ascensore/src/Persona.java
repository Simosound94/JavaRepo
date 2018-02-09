
public class Persona implements Runnable {
	
	private Ascensore asc;
	private int nome;
	
	public int getNome() {
		return nome;
	}



	public int pianoCorrente;
	private int pianoRichiesto;
	
	public int getPianoRichiesto() {
		return pianoRichiesto;
	}
	
	



	public Persona(Ascensore asc, int nome) {
		super();
		this.asc = asc;
		this.nome = nome;
		pianoCorrente = (int)(Math.random()*Ascensore.NUMPIANI);
	}



	@Override
	public void run() {
		try{
			while(true){
				Thread.sleep((int)(Math.random()*4000));
				pianoRichiesto = (int)(Math.random()*Ascensore.NUMPIANI);
				asc.request(this);
			}
		}catch(Exception e){e.printStackTrace();}
		
	}

}
