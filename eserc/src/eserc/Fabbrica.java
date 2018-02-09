package eserc;

public class Fabbrica {

	public static void main(String[] args) {
		Dipendente antonio = new Dipendente();
		
		MacchinaCaffe[] m = new MacchinaCaffe[5];
		for(int i=0; i<5;i++){
			m[i] = new MacchinaCaffe("Espresso", 220, "Espresso");
		}
		
		antonio.lavora(m);
	}

}
