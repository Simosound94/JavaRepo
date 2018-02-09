import edu.princeton.cs.algs4.*;

public class Giostra {
	//private long seed;
	private int duration; //Durata in giri di simulazione RANGE: [0:15]
	private int maxPeople; //RANGE: [0:100]
	private int peopleInside;
	private int numRide;
	private int avgFun;
	
	//To draw:
	private double x;
	private double y;
	private static double r = 0.15 ;
	
	public boolean full(){
		return(peopleInside>=maxPeople);
	}
	
	public Giostra (long seed){
		numRide=0;
		peopleInside=0;
		x=0;
		y=0;
		//StdRandom.setSeed(seed);
		while(true){
		duration = (int)StdRandom.gaussian(7.5,5.5);
		if(duration >0) break;
		}
		while(true){
			maxPeople = (int)StdRandom.gaussian(20,15);
			if(maxPeople >0) break;
			}
		avgFun = StdRandom.uniform(0, 10);
		
	}
	
	public double getFun(){
	//StdRandom.setSeed(seed);
	return StdRandom.gaussian(avgFun,6);
	}
	
	public void draw(int pos){
		y=(((int)pos/3)*2*r)+r; 
		x= (pos % 3)*2*r+r+0.03;
		StdDraw.setPenColor(StdDraw.ORANGE);
		StdDraw.filledCircle(x, y, r);
		StdDraw.setPenColor(StdDraw.BLACK);
	}
	
	public int getDuration() {
		return duration;
	}
	
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getR() {
		return r;
	}

	public int getPeopleInside() {
		return peopleInside;
	}
	
	public String getPInside() {
		String ris;
		ris= Integer.toString(peopleInside);
		return ris;
	}
	public void increasePeopleInside(){
		if(peopleInside<maxPeople){
			peopleInside++;
			numRide++;
		}
	}
	public void decreasePeopleInside(){
		if(peopleInside>0) peopleInside--;
	}

	public int getNumRide() {
		return numRide;
	}
	
	public int getAvgFun() {
		return avgFun;
	}
	public int getMaxPeople() {
		return maxPeople;
	}
	
}


