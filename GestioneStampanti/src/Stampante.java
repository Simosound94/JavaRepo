import java.util.LinkedList;

public class Stampante implements Runnable {

	private LinkedList<Character> bufferStampa;
	public int nome;
	
	public Stampante(int nome){
		bufferStampa = new LinkedList<Character>();
		this.nome = nome;
	}
	
	public void addChar(char c){
		try{
			synchronized(bufferStampa){
				while(bufferStampa.size()>4){
					System.out.println("Buffer stampante #"+nome+" full: "+bufferStampa.toString());
					bufferStampa.wait();
				}
				bufferStampa.add(c);
				bufferStampa.notifyAll();
			}
		}catch(Exception e){e.printStackTrace();}
	}
	
	@Override
	public void run() {
		try{
			while(true){
				char c = ' ';
				synchronized(bufferStampa){
					while(bufferStampa.isEmpty()){
						bufferStampa.wait();
						System.out.println("Buffer stampante #"+nome+" empty");
					}
					c = bufferStampa.removeFirst();
					bufferStampa.notifyAll();
				}
				Thread.sleep(5000);
				System.err.print(c);
			
			}
		}catch(Exception e){e.printStackTrace();}
		
		

	}

}
