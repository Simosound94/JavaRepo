package eserc;

public class ContoCorrente {

	
	public static float saldo;
	public static float sommaVers;
	public static int numVers;
	
	
	
	
	public static void main(String[] args) {
		System.out.println("Inserisci saldo");
		//... input
		ContoCorrente.saldo = 100000;
		while(ContoCorrente.scriviMenu()){
			//Non fare nulla
		}
		
		
		
		// Versione OOP: (non static)
		//ContoCorrente c = new ContoCorrente();
		//c.saldo =..
		//c.versamento();
		
	}
	
	
	public static boolean scriviMenu(){
		System.out.println("1) Versamento");
		int scelta = 1;
		//... input
		
		switch(scelta){
		case 1:{
				ContoCorrente.versamento();
				break;
				}
		
		case 4:{
				//saldo finale...
				return false;
				}
		}
		
		return true;

	}

	
	
	public static void versamento(){;
		float vers = 1;
		ContoCorrente.saldo += vers;
		ContoCorrente.numVers++;
		ContoCorrente.sommaVers += vers;

	}

	
	
	
	
}
