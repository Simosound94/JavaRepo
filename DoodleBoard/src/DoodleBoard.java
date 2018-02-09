import javax.swing.JFrame;

public class DoodleBoard {

	
	
	public static void main(String[] args){
		DoodleFrame doodleFrame = new DoodleFrame();
		
		/*
		 * Di default non c'è nulla che mi dica che se schiaccio la x
		 * il programma deve terminare, lo devo impostare
		 * Ciò significa che la x fa terminare sia il thread grafico che il mio
		 * (Se termina il mio thread invece termina automaticamente anche quello grafico)
		 * 
		 * In realtà potrei associare un listener anche a questo
		 * facendo apparire ad esempio una finestra "Vuoi davvero uscire?"
		 */
		doodleFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
