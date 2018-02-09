/**
 * DOCUMENTO:
 * 
 * 	classe corrispondente all'oggetto "file"
 *
 *	Ho scelto di creare un ArrayList per le parole
 *	(anche se era possibile farlo con un semplice array)
 *	in quanto necessito di poterle iterare dall'esterno
 *	ad ogni modo conosco in anticipo la dimensione delle parole nel dizionario
 *	pertanto non necessito di allungare l'array dinamicamente
 *
 */

import java.io.*;
import java.util.*;


public class Documento {
	
	public static enum cat{None, Ok, Spam};
	private cat category;
	private String docPath;
	private ArrayList<Integer> words;
	private long nWords;

	
	public Documento(String f, cat c, Dizionario diz) throws Exception{
		docPath = f;
		words = new ArrayList<Integer>((int)diz.nWords);
		for(int i = 0; i<diz.nWords; i++){
			
			//Inizialmente setto tutti i conteggi delle parole a 0
			words.add(0);				
		}
		category = c;
		nWords=0;
		
		//Mi sembra corretto provare la correttezza del file durante la creazione
		FileReader tryFile = new FileReader(docPath);
		tryFile.close();
	}

	
	//Calcola il numero delle parole nel file
	public void computeWordFrequencies(Dizionario dic) throws Exception{
		FileReader doc = new FileReader(docPath);
		Scanner in = new Scanner(doc);
		String s;
		Integer temp;
		
		//Resetto l'array words, nel caso in cui venisse calcolata 2 volte
		//(per non raddoppiare il conteggio)
		for(int i = 0; i<dic.nWords; i++){
			words.set(i, 0);
		}
		nWords=0;
		while (in.hasNext()) {					/*finchè ci sono parole in input*/
			s=in.next();
			int wIndex = dic.findIndex(s);		/*trova l'indice della parola*/
			if (wIndex >= 0){					/* se l'hai trovato */
				temp = words.get(wIndex);
				temp +=1;
				words.set(wIndex, temp);		/* +1 alle parole di quel tipo nel contatore*/
				nWords++;
			}
		}
		in.close();
		doc.close();
	}

	
	//Fornisce un'iterator delle parole nel file
	public Iterator<Integer> wordsIterator(){
		return words.iterator();
	}
	
	//Ritorna una stringa contenente il numero di ciascuna parola nel file
	public String toString(){
		
		//StringBuffer perchè più efficiente per le concatenazioni
		StringBuffer ris = new StringBuffer("[ ");
		Iterator<Integer> itWords = words.iterator();
		while(itWords.hasNext())
			ris.append(itWords.next()+" ");
		ris.append("]");
		return ris.toString();
	}
	

	//---------------------- SETTER E GETTER ---------------
	
	
	public long getNwords() {
		return nWords;
	}
	
	public String getPath() {
		return docPath;
	}
	
	public cat getCategory() {
		return category;
	}

	public void setCategory(cat category) {
			this.category = category;
	}

	
	
	
	
/* MAIN DI PROVA 
	public static void main(String[] args) throws Exception{
		Dizionario diz = new Dizionario("Files/dictionary.txt");
		Documento prova = new Documento("Files/fileOk_1.txt",Documento.cat.Ok,diz);
		System.out.println("Path: "+prova.getPath());
		System.out.println("#words: "+prova.getNwords());
		prova.computeWordFrequencies(diz);
		System.out.println("#words: "+prova.getNwords());
		Iterator<Integer> it = prova.wordsIterator();
		FileWriter x =new FileWriter("ris.txt");
		PrintWriter p = new PrintWriter(x);
		while (it.hasNext()) {
			p.println(it.next());
		}
		p.close();
		x.close();
	}
*/
		

}
