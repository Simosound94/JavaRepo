import edu.princeton.cs.algs4.*;

public class Food implements Drawable {
	public static final double MIN_AREA = 0.05;
	public static final double MAX_AREA = 0.1;
	public static final int    INITIAL_FEED_CAPACITY = 100;
	
	private static int foodCount = 0;
	
	private double x;
	private double y;
	private double feedRadius;
	private int    feedCapacity;
	
	public Food(double feedRadius) {
		assert((feedRadius >= MIN_AREA) && (feedRadius <= MAX_AREA));
		x = StdRandom.uniform();
		y = StdRandom.uniform();
		this.feedRadius = feedRadius;
		feedCapacity = INITIAL_FEED_CAPACITY;
		foodCount += 1;
	}

	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public double getFeedRadius() {
		return feedRadius;
	}
	public int getFeedCapacity() {
		return feedCapacity;
	}
	
	public static int getObjectCount() {
		return foodCount;
	}
	
	public boolean canFeed(Creature c) {
		double distance = Math.sqrt(Math.pow(x - c.getX(), 2) + Math.pow(y - c.getY(),2));
		return (distance < feedRadius);
	}
	
	public void decreaseFeedCapacityBy(int feeds) {
		assert(feeds >= 0);
		feedCapacity -= feeds;
	}
	
	public void draw() {
		StdDraw.setPenColor(StdDraw.GREEN);
		StdDraw.filledCircle(x,y,feedRadius);
		StdDraw.setPenColor(StdDraw.BLACK);
	}
	
	protected void finalize() {
		foodCount -= 1;
	}
	
}
