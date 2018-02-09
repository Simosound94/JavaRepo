
public class Lettore implements Runnable {

	private BufferCircolare bc;
	private int name;
	
	
	public Lettore(BufferCircolare bc, int name) {
		super();
		this.bc = bc;
		this.name = name;
	}


	@Override
	public void run() {
		try{
			while(true){
				Thread.sleep((int)((Math.random()*2000)+500));
				String s = bc.getFirst();
				System.out.println("Lettore "+name+" read: "+s);
			}
		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
