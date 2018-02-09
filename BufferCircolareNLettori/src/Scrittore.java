
public class Scrittore implements Runnable {
	

	private BufferCircolare bc;
	private int name;
	
	

	public Scrittore(BufferCircolare bc, int name) {
		super();
		this.bc = bc;
		this.name = name;
	}



	@Override
	public void run() {
		try{
			while(true){
				Thread.sleep((int)((Math.random()*4000)+2000));
				String s = String.valueOf((int)((Math.random()*2000)));
				bc.put(s);
				System.out.println("Scrittore "+name+" writes: "+s);
				System.out.println(bc);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
