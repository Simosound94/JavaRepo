import java.util.Random;
import java.io.FileWriter;
import java.io.PrintWriter;
//OBBIETTIVO:
//Confronto prestazioni tra string e StringBuffer per la tabulazione di vettori
//tabulazione: prendere i contenuti di un vettore e sistemarli dentro una stringa
//es vettore {0,2,3} stringa: "0 2 3"

public class StringVsStringBuffer {

	//Tabula un vettore usando string
	public static String tabulateWithString(int[] table){
		String result="";
		for(int i=0; i<table.length; i++){
			result = result + " "+ table[i]; 
		//Ogni ciclo for verranno create 5 stringhe:
		//- per table[i]
		//- per " "
		//- per la concatenazione " " + result
		//- concatenazione precedente + table[i]
		//
		//Inoltre la lunghezza di result aumenterà con l'aumentare dei giri
		//Tutte le volte creo una nuova stringa in cui io andrò a mettere tutti gli
		//elementi precedenti + minimo 2 char
		//STIMIAMO PERCIO' OGNI OPERAZIONE VALERE [#operazione+1]
		
		}
		//Quanto vale sum_{i=0}^n[i+1] (da i=0 a n) di (i+1)?
		// = n+n(n+1)/2
		//Risposta theta(n^2)
		return result;
	}
	
	//Tabula un vettore usando oggetti StringBuffer
	public static String tabulateWithStringBuffer(int[] table){
		StringBuffer result = new StringBuffer("");
		for(int i=0; i<table.length; i++){
			//stimo quest'operazione valere 1
			result.append(" "+ table[i]);   	
		}
		//Quanto vale sum_{i=0}^n[1]?
		//Risposta: theta(n)
		return result.toString();
		
	}
		
	public static void main(String[] args) throws Exception {
		//Per i due algoritmi sopra visti vi sarà quindi una differenza di prestazioni
		//insensibile per pochi numeri ma importante al crescere della dimensione
		//dei vettori
		//Come facciamo a vederlo?
		int[] prova;
		final int fattoreScala=50;
		final int tentativi = 60;
		Random rand = new Random();
		FileWriter out = new FileWriter(args[0]);
		PrintWriter print = new PrintWriter(out);
		long start,elapsed1,elapsed2;
		for(int i=1; i<tentativi+1;i++){
			prova= new int[i*fattoreScala];
			for(int j=0;j<prova.length;j++)
					prova[j]=rand.nextInt();
			start = System.nanoTime();
			tabulateWithString(prova);
			elapsed1= System.nanoTime()-start;
			start=System.nanoTime();
			tabulateWithStringBuffer(prova);
			elapsed2= System.nanoTime()-start;
			print.print(elapsed1);
			print.print(",");
			print.println(elapsed2);
		}
		print.close();
		out.close();
		System.out.println("End.");
	}

}
