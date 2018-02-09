package ritardi_permessi;

public class MainApp {
	
	public static final int MAXLETTORI = 10;
	

	public static void main(String[] args) {
		BufferCircolare bc = new BufferCircolare(3, MAXLETTORI);
		for(int i = 0; i<MAXLETTORI;i++){
			Lettore l = new Lettore(bc, i);
			Thread tl = new Thread(l);
			tl.start();
		}
		
		for(int i = 0; i<3;i++){
			Scrittore l = new Scrittore(bc, i);
			Thread tl = new Thread(l);
			tl.start();
		}
	}

}
