import javax.swing.*;

public class TestArtificialLife {

	public static void main(String[] args) {
		ArtificialLife world = new ArtificialLife(2, 10, 50);
		world.doSimulation();
		JOptionPane.showMessageDialog(null, "Simulazione terminata");
		
		String message = 
				"PRIMA della garbage collection forzata\n" + 
				"Numero di oggetti Creature creati: " + Creature.getTotalIndividuals() + " \n" + 
				"Numero di oggetti Creature attivi: " + Creature.getObjectCount() + " \n" + 
				"Numero di oggetti Creature nel simulatore: " + world.getCreatureCount() + " \n" + 
				"Numero di oggetti Food attivi: " + Food.getObjectCount() + " \n" +
				"Numero di oggetti Food nel simulatore: " + world.getPastureCount() + " \n";
		JTextArea textArea = new JTextArea();
		textArea.setText(message);
		JOptionPane.showMessageDialog(null, textArea);

		System.gc();
		
		message = 
				"DOPO la prima garbage collection forzata\n" + 
				"Numero di oggetti Creature creati: " + Creature.getTotalIndividuals() + " \n" + 
				"Numero di oggetti Creature attivi: " + Creature.getObjectCount() + " \n" + 
				"Numero di oggetti Creature nel simulatore: " + world.getCreatureCount() + " \n" + 
				"Numero di oggetti Food attivi: " + Food.getObjectCount() + " \n" +
				"Numero di oggetti Food nel simulatore: " + world.getPastureCount() + " \n";
		textArea.setText(message);
		JOptionPane.showMessageDialog(null, textArea);
		
		System.gc();
	
		message = 
				"DOPO la seconda garbage collection forzata\n" + 
				"Numero di oggetti Creature creati: " + Creature.getTotalIndividuals() + " \n" + 
				"Numero di oggetti Creature attivi: " + Creature.getObjectCount() + " \n" +
				"Numero di oggetti Food attivi: " + Food.getObjectCount();
		textArea.setText(message);
		JOptionPane.showMessageDialog(null, textArea);	
		
		world = null;
		System.gc();
		
		message = 
				"DOPO world = null e la terza garbage collection forzata\n" + 
				"Numero di oggetti Creature creati: " + Creature.getTotalIndividuals() + " \n" + 
				"Numero di oggetti Creature attivi: " + Creature.getObjectCount() + " \n" +
				"Numero di oggetti Food attivi: " + Food.getObjectCount();
		textArea.setText(message);
		JOptionPane.showMessageDialog(null, textArea);
		
		System.gc();
		
		message = 
				"DOPO world = null e la quarta garbage collection forzata\n" + 
				"Numero di oggetti Creature creati: " + Creature.getTotalIndividuals() + " \n" + 
				"Numero di oggetti Creature attivi: " + Creature.getObjectCount() + " \n" +
				"Numero di oggetti Food attivi: " + Food.getObjectCount();
		textArea.setText(message);
		JOptionPane.showMessageDialog(null, textArea);
		
		System.exit(0);
	}

}
