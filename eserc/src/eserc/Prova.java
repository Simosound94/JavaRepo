package eserc;

import java.util.Scanner;

public class Prova {
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		
	
	
	
	
	//Variabili "Globali"
	public static int x;
	public static int y;
	
	private static Scanner s;

	public static void main(String[] args) {
		//Come usare var. "globali"
		Prova.x = 5;
		
		//Variabile locale
		int z = 5;
		if(z>3){ //Blocco di codice dove definisco k
			int k = 6;
			Prova.mean();
			System.out.println(k);
			//Z è visibile in un blocco di codice sottostante
			System.out.println(z);

		}
		//fuori dal blocco di codice k non esiste
		//System.out.println(k);
		
		//Uguaglianza tra due valori primitivi uguali -> true
		System.out.println(Prova.x == z);
		
		//Uguaglianza tra due oggetti  diversi appartentemente uguali -> false
		Prova p1 = new Prova();
		Prova p2 = new Prova();
		System.out.println(p1 == p2);
		System.out.println(p1.hashCode()+" "+p2.hashCode());

		p2 = p1;
		System.out.println(p1 == p2);
		System.out.println(p1.hashCode()+" "+p2.hashCode());

		


	}

	
	public static float mean(){
		int z = 9;
		System.out.println(z);
		return (x+y)/2;


	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
