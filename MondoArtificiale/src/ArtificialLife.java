import java.util.LinkedList;

import edu.princeton.cs.algs4.*;

public class ArtificialLife {
	// Periodo della simulazione in millisecondi
	// Minore è il periodo, maggiore la velocità di simulazione
	public static final long STEP_DURATION = 100;
	
	private int birthRate; // Tasso di nascita delle creature (in cicli)
	private int foodRate;  // Tasso di rigenerazione del cibo (in cicli) 
	private int horizon;   // Numero di cicli della simulazione 
	
	// Creature vive e cibo disponibile
	private LinkedList<Creature> creatures;
	private LinkedList<Food>     pastures;
	
	public ArtificialLife(int birthRate, int foodRate, int horizon) {
		assert((birthRate > 0) && (foodRate >0) && (horizon > 0));
		this.birthRate = birthRate;
		this.foodRate = foodRate;
		this.horizon = horizon;
		creatures = new LinkedList<Creature>();
		pastures = new LinkedList<Food>();
	}
	
	public void doSimulation() {
		// Esegue l'update della rappresentazione grafica solo se necessario
		boolean mustUpdate = false;
		for (int step = 0; step < horizon; ++step) {
			// Ogni 'birthRate' cicli genera una nuova creatura
			if (step % birthRate == 0) {
				Creature baby = new Creature();
				creatures.add(baby);
				mustUpdate = true;
			}
			// Ogni 'foodRate' cicli genera un nuovo "pascolo"
			if (step % foodRate == 0) {
				Food pasture = 
						new Food(StdRandom.uniform(Food.MIN_AREA, Food.MAX_AREA));
				pastures.add(pasture);
				mustUpdate = true;
			}
			// Crea due liste vuote per memorizzzare creature morte per
			// mancanza di cibo e "pascoli" esauriti
			LinkedList<Creature> departedCreatures = new LinkedList<Creature>();
			LinkedList<Food> schorchedLands = new LinkedList<Food>();
			for (Creature c : creatures) {
				// Per ogni creatura c...  
				boolean hasEaten = false;
				for (Food pasture : pastures) {
					// Per ogni pascolo f...
					if (c.canEat(pasture)) {
						// Se la creatura 'c' può mangiare in 'f' allora sottraggo
						// una unità di cibo dal "pascolo"
						pasture.decreaseFeedCapacityBy(1);
						// La creatura mangia (aumenta la sua vita residua) 
						c.eat(true);
					}
					// Se 'pasture' è esaurito, lo inserisco nella lista 
					// 'schorchedLand'
					if (pasture.getFeedCapacity() < 0) {
						schorchedLands.add(pasture);
						mustUpdate = true;
					}
				}
				// Se 'c' non ha mai potuto mangiare in nessun "pascolo"
				// allora concludo che non ha potuto mangiare a questo giro
				if (!hasEaten) {
					c.eat(false);
				}
				// Se la vita residua della creatura è terminata, la
				// inserisco nella lista 'theDeparted'
				if (c.getResidualLife() < 0) {
					departedCreatures.add(c);
					mustUpdate = true;
				}
			}
			// Se mustUpdate è true, ho nuove creature, nuovi pascoli, oppure
			// sono decedute creature, oppure si sono esauriti "pascoli".
			if (mustUpdate) {
				// Rimozione delle creature decedute e dei "pascoli" esauriti
				for (Creature c : departedCreatures) {
					creatures.remove(c);
				}
				for (Food f : schorchedLands) {
					pastures.remove(f);
				}
				// Rigenerazione dello scenario
				StdDraw.clear();
				for (Food f : pastures) {
					f.draw();
				}
				for (Creature c : creatures) {
					c.draw();
				}
				mustUpdate = false;
			}
			try {
				// Ferma l'esecuzione per STEP_DURATION millisecondi
				Thread.sleep(STEP_DURATION);
			} catch (InterruptedException e ) {
				// skip
			}
		}
	}
	
	public int getPastureCount() {
		return pastures.size();
	}
	
	public int getCreatureCount() {
		return creatures.size();
	}
	
}
