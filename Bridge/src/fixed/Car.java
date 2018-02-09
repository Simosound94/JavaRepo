package fixed;

public class Car implements Runnable {
	
	private Bridge b;
	private int name;
	private int direction;
	
	

	public Car(Bridge b, int name) {
		super();
		this.b = b;
		this.name = name;
	}



	@Override
	public void run() {
		while(true){
			if(Math.random()>0.5){
				direction = 1;
			}
			else{
				direction = -1;
			}
			b.enter(this);
			synchronized(this){
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		

	}




	public int getName() {
		return name;
	}



	public int getDirection() {
		return direction;
	}

}
