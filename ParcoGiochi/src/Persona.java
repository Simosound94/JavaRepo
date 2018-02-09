import edu.princeton.cs.algs4.StdRandom;


public class Persona {

	private int time; //Range [10:50]
	private double fun;
	public boolean inGiostra; 
	private int inTime;
	private int waitTime;
	private int gameTime;
	private Giostra game;
	
	
	public Persona(long seed){
		//StdRandom.setSeed(seed);
		while(true){
		time =(int) StdRandom.gaussian(25, 20);
		if(time>20) break;
		}
		fun=0;
		inGiostra=false;
		inTime=0;
		waitTime=0;
		gameTime=0;
		game=null;
	}
	
	
	
	public boolean haveFun(){   //Ritorna true se è uscito dalla giostra
		inTime++;
		gameTime++;
		fun +=game.getFun();
		if(inTime == game.getDuration()){
			this.leaveGame();
			return true;
		}
		return false;
	}
	
	public void leaveGame(){
		inTime=0;
		inGiostra=false;
		game.decreasePeopleInside();
		game=null;
	}
	

	public int getTime() {
		return time;
	}



	public int getInTime() {
		return inTime;
	}

	public double getFun() {
		return fun;
	}

	public void setFun(double fun) {
		this.fun = fun;
	}

	public Giostra getGame() {
		return game;
	}

	public void setGame(Giostra game) {
		inGiostra=true;
		this.game = game;
		game.increasePeopleInside();
	}

	public void decreaseTime(){
		if(time>0)time--;
	}

	public void increaseWaitTime(){
		waitTime++;
	}
	public int getWaitTime(){
		return waitTime;
	}



	public int getGameTime() {
		return gameTime;
	}

}
