
public class MainApp {

	public static final int NUMRES = 10;
	public static final int NUMFILOSOFI = 10;

	
	public static void main(String[] args) {
		ResourcePool rp = new ResourcePool(NUMRES);
		for(int i = 0; i<NUMFILOSOFI; i++){
			Filosofo f = new Filosofo(rp, i, NUMRES/2);
			Thread t_f = new Thread(f);
			t_f.start();
		}
	}

}
