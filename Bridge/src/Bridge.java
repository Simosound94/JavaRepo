import java.util.ArrayList;

public class Bridge{
	
	/*
	 * Directions
	 * 1 :NORD
	 * -1: SOUTH
	 * 0: No cars 
	 * 
	 */
	
	private ArrayList<Car> arr;
	private int currentDirection;
	public static final int MAXCAPACITY = 3;
	

	public Bridge() {
		super();
		this.arr = new ArrayList<Car>(MAXCAPACITY);
		currentDirection = 0;
	}
	
	public synchronized void enter(Car c){
		try{
			while(c.getDirection() != currentDirection){
				if(currentDirection == 0){
					break;
				}
				System.out.println("Car "+c.getName()+" wait on bridge for direction: "+c.getDirection());
				this.wait();
			}
			while(arr.size()>=MAXCAPACITY){
				System.out.println("Car "+c.getName()+" direction:"+c.getDirection()+" wait on bridge for max capacity reached");
				this.wait();
			}
			System.out.println("Car "+c.getName()+" enter bridge direction:"+c.getDirection());
			currentDirection = c.getDirection();
			arr.add(c);
		}catch(Exception e){e.printStackTrace();}
		
	}

	public synchronized void exit(){
		if(!arr.isEmpty()){
			Car c = arr.remove(0);
			if(arr.isEmpty()){
				currentDirection = 0;
			}
			this.notifyAll();
			
			System.out.println("Car "+c.getName()+" exit bridge direction:"+c.getDirection());
		}
	}
	
	

}
