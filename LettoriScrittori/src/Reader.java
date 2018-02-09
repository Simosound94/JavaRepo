
public class Reader implements Runnable {
	public String name;
	public Resource res;
	
	public Reader(String name, Resource res){
		this.name = name;
		this.res = res;
	}

	@Override
	public void run() {
		while(true){
			System.out.println("Thread " + name + " tries to read");
			try {
				res.startRead();
				System.out.println("Thread " + name + " reads");
				Thread.sleep((long) (Math.random()*1000));
				//Usa risorsa...
				res.finishRead();
				System.out.println("Thread " + name + " finishes read");
				
				
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
