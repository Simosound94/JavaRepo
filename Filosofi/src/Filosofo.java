import java.util.LinkedList;

public class Filosofo implements Runnable {
	 private ResourcePool rp;
	 private int name;
	 private int maxResRequest;

	public Filosofo(ResourcePool rp, int name, int maxResRequest) {
		super();
		this.rp = rp;
		this.name = name;
		this.maxResRequest = maxResRequest;
	}

	@Override
	public void run() {
		try {
			while(true){
				Thread.sleep(2000);
				int numRes = (int)(Math.random()*maxResRequest+1);
				System.out.println("Filosofo #"+name+" request "+numRes+" resources");
				LinkedList<Forchetta> res = rp.getResources(numRes);
				System.out.println("Filosofo #"+name+" get "+res.size()+" resources");
				//Filosofo uses resources...
				Thread.sleep(2000);
				rp.releaseResources(res);
				System.out.println("Filosofo #"+name+" releases resources");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
