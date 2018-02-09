import java.util.ArrayList;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
//Comando per stampare su file invece che su console: (da terminale)
//    [comando da eseguire] >nomefile.txt
// Java.util contiene molte strutture dati interessanti

public class TestArrayList {

	//OBBIETTIVO: EFFICIENZA AMMORTIZZATA
	// Monitorare costo del singolo inserimento
	// (Vedremo che qualche inserimento costerà poco, altri costeranno molto
	// per l'allargamento dell'array)
	// Calcolare l'efficienza ammortizzata
	
	public static void main(String[] args) {
		
		//Nome del file da riga di comando
		if (args.length<2) System.exit(0);
		
		
		//Creo un ArrayList di stringe per leggere e memorizzare
		//il contenuto del file
		ArrayList<String> table = new ArrayList<String>();
		FileReader textFileReader=null;
		try{
			//NON POSSO DICHIARARE VAR QUI DENTRO PERCHÈ SAREBBERO
			//VISIBILI SOLO IN QUESTO BLOCCO
			textFileReader = new FileReader(args[0]);
		}
		//Se io gestisco le eccezioni fa differenza come impatto con l'utente
		//perchè senza ha l'impressione che il programma non funzioni
		//mentre così capisce quanto meno di aver sbagliato
		catch(Exception e){
			//Più precisamente ci sarà una FileNotFoundException
			System.out.println(e.getMessage());
			System.exit(0);
		}
		Scanner in = new Scanner(textFileReader);
		Timer insert = new Timer();
		Timer tot = new Timer();
		long numIns=0;
		FileWriter out=null;
		try{
			out = new FileWriter(args[1]);
		}catch(Exception e){
			System.out.println(e.getMessage());
			System.exit(0);
		}
		PrintWriter print = new PrintWriter(out);
		
		tot.start();
		while(in.hasNext()){
			insert.start();
			table.add(in.next());
			insert.stop();
			tot.stop();
			numIns++;
			print.println(insert.getElapsedNanoSeconds());
			tot.start(tot);
		}
		tot.stop();
		
		in.close();
		print.println("Tempo totale: "+tot.getElapsedNanoSeconds());
		print.println("Tempo medio ins: "+tot.getElapsedNanoSeconds()/numIns);
		try{
			textFileReader.close();
			out.close();
		}catch(IOException e){
			System.out.println(e.getMessage());
			System.exit(0);
		}
		
		/*
		for(String s:table){
			//Ogni ciclo s rappresenterà un elemento di table
			//dal primo all'ultimo --> lo scorro tutto
			System.out.println(s);
		}
		*/
		
	}

}
