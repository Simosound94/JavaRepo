import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Dizionario {

	public ArrayList<String> dictionary;
	public long nWords;
	
	
	//Crea un array contenente tutte le parole presenti nel dizionario
	public Dizionario(String dicPath) throws Exception {
		dictionary = new ArrayList<String>();
		nWords=0;
		FileReader dic = new FileReader(dicPath);
		Scanner in = new Scanner(dic);
		while(in.hasNext()){
			dictionary.add(in.next());
			
			//Come da specifica, ipotizzo che siano presenti nel dizionario
			//solo lettere minuscole
			//(ai fini della classificazione di un file riconduco tutto in minuscolo
			//per contare le instanze delle parole, trovabili nel file anche in maiuscolo)
			nWords++;
		}
		in.close();
		dic.close();
		
		//Nelle specifiche non è scritto che il dizionario fornito
		//sia ordinato, lo ordino
		Collections.sort(dictionary);
	}
	
	
	//Restituisce l'indice di una certa parola nel dizionario
	public int findIndex(String s){		
		return dictionary.indexOf(s.toLowerCase());	
	}
	
	
	
	/*MAIN DI PROVA 
	private void printDictionary(FileWriter x) throws Exception{
		PrintWriter p = new PrintWriter(x);
		for(int i =0; i<nWords;i++){
			p.println(dictionary.get(i));
		}
		p.close();
		x.close();
	}
	
	
	public static void main(String[] args) throws Exception{
		Dizionario prova = new Dizionario("Files/dictionary.txt");
		FileWriter x =new FileWriter("ris.txt");
		prova.printDictionary(x);
		System.out.println("n parole: "+prova.getNwords());
		System.out.println("Index of iride: "+prova.findIndex("iride"));
		System.out.println("Index of IrIdE: "+prova.findIndex("IrIdE"));
		
	}
	*/
}
