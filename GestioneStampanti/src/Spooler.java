
public class Spooler implements Runnable {

	private CodaDiStampa coda;
	private Stampante stampante;
	
	
	public Spooler(CodaDiStampa coda, Stampante stampante) {
		super();
		this.coda = coda;
		this.stampante = stampante;
	}

	@Override
	public void run() {
		while(true){
			PrintRequest pr = coda.getRequest(stampante.nome);
			char[] toPrint = pr.document.toCharArray();
			for(int i = 0; i<toPrint.length; i++){
				stampante.addChar(toPrint[i]);
			}
		}
		
	}

}
