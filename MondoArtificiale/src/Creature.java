import edu.princeton.cs.algs4.*;

public class Creature implements Drawable {
	public static final int INITIAL_LIFE = 10;
	
	private static final double SIZE = 0.01; 
	
	private static int populationCount = 0;
	private static int totalIndividuals = 0;
	
	private double x;
	private double y;
	private int    residualLife;
	
	public Creature() {
		x = StdRandom.uniform();
		y = StdRandom.uniform();
		residualLife = INITIAL_LIFE;
		populationCount += 1;
		totalIndividuals += 1;
	}
	
	public double getX() {
		return x;
	}
	public double getY() {
		
		return y;
	}
	public int getResidualLife() {
		return residualLife;
	}
	
	public boolean canEat(Food f) {
		double distance = Math.sqrt(Math.pow(x - f.getX(), 2) + Math.pow(y - f.getY(),2));
		return (distance < f.getFeedRadius());
	}
	
	public void eat(boolean hasEaten) {
		if (hasEaten) {
			residualLife += 1;
		} else {
			residualLife -= 1;
		}
	}
	
	public void draw() { 
		StdDraw.filledCircle(x,y,SIZE);
	}
	
	public static int getObjectCount() {
		return populationCount;
	}
	public static int getTotalIndividuals() {
		return totalIndividuals;
	}
	
	protected void finalize() {
		populationCount -= 1;
	}
}
