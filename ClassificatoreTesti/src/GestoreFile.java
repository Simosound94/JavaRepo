/**
 * GESTORE FILE:
 * 	
 * 	la classe si occupa di gestire l'inisieme di tutti i file
 * 
 * 	ho scelto di gestirli tramite una lista per ogni categoria di file.
 * 
 * 	la scelta della lista è motivata anche dal fatto che ho bisogno solo di accessi sequenziali
 * 	(non devo scorrerla per cercare qualche file in particolare)
 * 	
 */

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;


public class GestoreFile {
	
	private LinkedList<Documento> fileOk;
	private LinkedList<Documento> fileSpam;
	private LinkedList<Documento> fileNone;
	private Dizionario dictionary;
	
	
	
	public GestoreFile(Dizionario d){
		 fileOk = new LinkedList<Documento>();
		 fileSpam = new LinkedList<Documento>();
		 fileNone = new LinkedList<Documento>();
		 dictionary=d;
	 }

	
	//Costruttore di copia di un gestore già esistente
	public GestoreFile(GestoreFile cpy){
		 fileOk = new LinkedList<Documento>(cpy.fileOk);
		 fileSpam = new LinkedList<Documento>(cpy.fileSpam);
		 fileNone = new LinkedList<Documento>(cpy.fileNone);
		 dictionary=cpy.dictionary;
	 }
	
	
	//Crea un nuovo file e lo inserisce nel gestore, calcolando le
	//occorrenze delle parole nel file
	public void insertFile(String path, Documento.cat c){
		Documento temp = null;
		try{
			temp = new Documento(path, c, dictionary);	/*try/catch perchè qui provo ad aprire il file*/
			temp.computeWordFrequencies(dictionary);	
		}catch(Exception e){
			System.err.println(e.getMessage());
			System.exit(0);
		}
		categoryList(c).add(temp);						/* aggiungo il file creato all'opportuna lista*/
	}
	
	
	//Inserisce un file già esistente nel gestore
	public void insertFile(Documento doc){
		if(doc.getNwords()==0){ 						
			try{
				doc.computeWordFrequencies(dictionary);	/*Se la frequenza non è stata calcolata, la calcolo */
			}catch(Exception e){
				System.err.println(e.getMessage());
				System.exit(0);
			}
		}
		categoryList(doc.getCategory()).add(doc);		/*aggiungo il documento all'opportuna categoria*/
	}
	
	
	//Si occupa di calcolare il numero di occorrenze delle parole
	//per ciascun file appartenente ad una categoria
	public void categoryWordFrequencies(Documento.cat c){
		LinkedList<Documento> choice = categoryList(c);
		Documento doc;
		Iterator<Documento> choiceIt = choice.iterator();
		while(choiceIt.hasNext()){
			doc=choiceIt.next();
			if(doc.getNwords()==0){   //Se è già stata calcolata non la ricalcolare
				try{
					doc.computeWordFrequencies(dictionary);
				}catch(Exception e){
					System.err.println("File: "+doc.getPath()+"\n"+e.getMessage());
					System.exit(0);
				}
			}
		}
		
	}
	
	
	//Consente di fare unire ad un gestore file un altro gestore file
	//serve per il calcolo dell'errore (unione dei blocchi considerati già classificati)
	public void addGestoreFile(GestoreFile x){
		fileOk.addAll(x.fileOk);
		fileSpam.addAll(x.fileSpam);
		fileNone.addAll(x.fileNone);
	 }
	
	
	//Restituisce ed ellimina un file a caso di una determinata categoria dalla lista
	//serve per creare i blocchi per il calcolo dell'errore in modo random
	//senza ripetizioni, perchè poi lo ellimino
	public Documento removeRandomFile(Documento.cat c){
		Random rnd = new Random();
		LinkedList<Documento> choice = categoryList(c);
		return choice.remove(rnd.nextInt(choice.size()));
	}
	
	//Ritorna la lista della categoria scelta
	//Serve per elliminare la rindondanza del codice 
	private LinkedList<Documento> categoryList(Documento.cat c){
		LinkedList<Documento> choice = null;
		switch(c){
		case None:
			choice=fileNone;
			break;
		case Ok:
			choice=fileOk;
			break;
		case Spam:
			choice=fileSpam;
			break;
		}
		return choice;
	}
	
	
	//------------------- GETTER E SETTER ------------------
	
	
	//Ritorna un iteratore ai file della categoria scelta
	public Iterator<Documento> getIterator(Documento.cat c){
		LinkedList<Documento> choice = categoryList(c);
		return choice.iterator();
	}
	
	
	//Ritorna il numero dei file appartenenti ad una categoria
	public int getNfileCategory(Documento.cat c){
		LinkedList<Documento> choice = categoryList(c);
		return choice.size();
	}
	
	
	public Dizionario getDictionary(){
		return dictionary;
	}

	

	/*MAIN DI PROVA 
	public static void main(String[] args) throws Exception{
		Dizionario dic = new Dizionario("Files/dictionary.txt");;
		GestoreFile file = new GestoreFile(dic);
		file.insertFile("Files/fileOk_1.txt", Documento.cat.Ok);
		file.insertFile("Files/fileOk_2.txt", Documento.cat.Ok);
		file.insertFile("Files/fileOk_3.txt", Documento.cat.Ok);
		file.insertFile("Files/fileSpam_1.txt", Documento.cat.Spam);
		Iterator<Documento> ok = file.getIterator(Documento.cat.Ok);
		Iterator<Documento> spam = file.getIterator(Documento.cat.Spam);
		while(ok.hasNext())
			System.out.println("Ok: "+ok.next().toString());
		file.categoryWordFrequencies(Documento.cat.Ok);
		ok = file.getIterator(Documento.cat.Ok);
		while(ok.hasNext())
			System.out.println("Ok: "+ok.next().toString());
		while(spam.hasNext())
			System.out.println("Spam: "+spam.next().toString());
			
	}
*/
	
	
}
