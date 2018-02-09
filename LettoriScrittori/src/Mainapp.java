
public class Mainapp {
	
	public static void main(String[] args){
		RisorsaString str = new RisorsaString("");
		Resource r = new Resource(str);
		
		
		for (int i = 0; i<5; i++){
			Reader t = new Reader(Integer.toString(i), r);
			Thread t_t = new Thread(t);
			t_t.start();
		}
			
			for (int i = 0; i<3; i++){
				Writer t = new Writer(Integer.toString(i), r);
				Thread t_t = new Thread(t);
				t_t.start();
	
		}
	}
		

}
