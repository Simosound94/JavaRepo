/**
 * CLASSIFICATORE:
 * 
 * 	Classe utilizzata per gestire la classificazione dei file ed il calcolo dell'errore
*/

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Iterator;


public class Classificatore {
	
	private GestoreFile file;
	private double DELTA;
 
	
	public Classificatore(GestoreFile f){
		file=f;
		
		//Mi assicuro che tutte le frequenze delle parole siano state calcolate
		file.categoryWordFrequencies(Documento.cat.Ok);
		file.categoryWordFrequencies(Documento.cat.Spam);
		
		/*
		 * Il valore del delta è stato scelto grazie alla funzione apposita
		 * per mettere il main in modalità "test delta" è sufficiente cambiare il valore
		 * dell'enum mod presente nel main.
		 * 
		 * Tramite tale funzione ho constatato che l'errore è pari a 0 (circa) per valori
		 * compresi tra [180; 270] (circa)
		 * considerando che la precisione decresce per delta troppo grande
		 * o troppo piccolo, ho preso il valor medio di tali estremi
		 */
		DELTA = 220;
	}
 
	
	//Classifica tutti i file ti tipo "Non classificato" presenti nel GestoreFile
	public void classifyAll(){
		Iterator<Documento> itNone = file.getIterator(Documento.cat.None);
		while(itNone.hasNext()){
			classifyFile(itNone.next());
		}
	}
	
	
	//Classifica un file passato come paramentro, sulla base dei documenti classificati
	//contenuti nel classificatore
	public Documento.cat classifyFile(Documento docToClassify){
		int okInDelta = 0, spamInDelta = 0;									/*contatori dei file in delta*/
		int likelihood;
		Iterator<Documento> itOk = file.getIterator(Documento.cat.Ok);
		Iterator<Documento> itSpam = file.getIterator(Documento.cat.Spam);
		while(itOk.hasNext()){												/*per tutti i file di tipo ok*/
			likelihood = getLikelihood(docToClassify,itOk.next());
			if(likelihood <= DELTA)											/*se la distanza è minore di delta, incrementa il contatore */
				okInDelta++;
		}
		while(itSpam.hasNext()){
			likelihood = getLikelihood(docToClassify,itSpam.next());
			if(likelihood <= DELTA)
				spamInDelta++;
		}
		if(okInDelta>spamInDelta){
			docToClassify.setCategory(Documento.cat.Ok);
			return Documento.cat.Ok;
		}
		else{
			docToClassify.setCategory(Documento.cat.Spam);
			return Documento.cat.Spam;
		}
	 
	}
 
 
	//Funzione overload che restituisce anche le distanze dai singoli file
	//Se il vettore non è sufficientemente grande verranno restituite solo in parte.
	public Documento.cat classifyFile(Documento docToClassify, int[] distances){
		int okInDelta = 0, spamInDelta = 0;
		int likelihood;
		Iterator<Documento> itOk = file.getIterator(Documento.cat.Ok);
		Iterator<Documento> itSpam = file.getIterator(Documento.cat.Spam);
		int i = 0;
		while(itOk.hasNext()){
			likelihood = getLikelihood(docToClassify,itOk.next());
			if(i<distances.length)
				distances[i] = likelihood;
			if(likelihood <= DELTA)
				okInDelta++;
			i++;
		}
		while(itSpam.hasNext()){
			likelihood = getLikelihood(docToClassify,itSpam.next());
			if(i<distances.length)
				distances[i] = likelihood;
			if(likelihood <= DELTA)
				spamInDelta++;
			i++;
		}
		if(okInDelta>spamInDelta){ 
			docToClassify.setCategory(Documento.cat.Ok);
			return Documento.cat.Ok;
		}
		else{
			docToClassify.setCategory(Documento.cat.Spam);
			return Documento.cat.Spam;
		}
	}
 
	
	//Restituisce la distanza di due documenti passati come parametro
	public static int getLikelihood(Documento d1, Documento d2){
		Iterator<Integer> it1 = d1.wordsIterator();
		Iterator<Integer> it2 = d2.wordsIterator();
		int result = 0;
		
		//Metodo "Brute Force" che segue semplicemente l'algoritmo dato
		while(it1.hasNext()){
			result += Math.pow((it1.next()-it2.next()), 2);
		}
		return result;
	}
 
	
	private void setDELTA(double x){
	 if(x>0)
		 DELTA=x;
	}
 
	
 //------------------ Calcolo di DELTA --------------------------------
	
	
	//Divide in K blocchi il GestoreFile presente nel classificatore
	//assicurandosi che in ciascun blocco la percentuale di file
	//appartenenti alle diverse categorie sia la stessa
	private GestoreFile[] builtBlock(int K){ 
		GestoreFile[] ris = new GestoreFile[K];
		int okFileBlock, spamFileBlock;
		
		// Ok/Spam file che devono essere presenti ogni blocco
		okFileBlock = file.getNfileCategory(Documento.cat.Ok);
		spamFileBlock = file.getNfileCategory(Documento.cat.Spam);
		okFileBlock = (int) Math.floor(okFileBlock / K);
		spamFileBlock = (int) Math.floor(spamFileBlock / K);
		
		//K deve essere t.c. deve sussistere almeno un file per blocco, me ne assicuro
		if(okFileBlock==0 && spamFileBlock==0)
			return null;
		
		//Creo un gestore file dal quale rimuvere pian piano tutti i file per inserirli
		//nei vari blocchi
		GestoreFile copy = new GestoreFile(file);
		for(int i = 0; i<K ; i++){							/*per ciascun blocco*/
			ris[i]=new GestoreFile(copy.getDictionary());
			for(int j = 0; j<okFileBlock ; j++)				/*inserisci x file a caso di tipo ok*/
				ris[i].insertFile(copy.removeRandomFile(Documento.cat.Ok));
			for(int j = 0; j<spamFileBlock ; j++)			/*inserisci y file a caso di tipo spam*/
				ris[i].insertFile(copy.removeRandomFile(Documento.cat.Spam));
		}
		return ris;
	}
 
 
	//Calcola l'errore utilizzando un certo delta
	private double computeError(GestoreFile[] blocks, double delta){
		GestoreFile classified, toClassify;
		int K = blocks.length;
		double[] errForBlock = new double[K];  	/*Errori per ciascuna prova di classificazione*/
		for(int j = 0 ; j<K ; j++){				
			errForBlock[j]= 0;
		}
		
		//Ciascun Gestore, ha stesso numero di file, tutti categorizzati
		int fileForEachBlock = blocks[0].getNfileCategory(Documento.cat.Ok) + blocks[0].getNfileCategory(Documento.cat.Spam);
		
		//Effettuo una "prova di classificazione" per ciascun blocco
		for(int i = 0;i<K;i++){
			
			//Creo blocco da classificare
			toClassify = new GestoreFile(blocks[i].getDictionary());
			toClassify.addGestoreFile(blocks[i]);
			
			//Creo blocco di classificati dall'unione degli altri blocchi
			classified = new GestoreFile(blocks[i].getDictionary());
			for(int j=0;j<K;j++){
				if(j!=i)
					classified.addGestoreFile(blocks[j]);
			}
			
			//Classificazione
			Iterator<Documento> it = toClassify.getIterator(Documento.cat.Ok);
			Documento.cat ris;
			Classificatore prova = new Classificatore(classified); 	/*addestro il classificatore sul blocco di classificati*/
			prova.setDELTA(delta);									/*setto il delta scelto*/
			while(it.hasNext()){									/*classifico tutti i file ok*/
				ris = prova.classifyFile(it.next());	
				if(ris != Documento.cat.Ok)
					errForBlock[i]++;								/*conto gli errori commessi*/
			}
			it = toClassify.getIterator(Documento.cat.Spam);		
			while(it.hasNext()){									/*idem per i file spam*/
				ris = prova.classifyFile(it.next());
				if(ris != Documento.cat.Spam)
					errForBlock[i]++;
			}
			errForBlock[i] /= fileForEachBlock;	 					/*calcolo l'errore per la prova i-esima*/
		}
		
		//Calcolo l'errore medio di classificazione commesso
		float ris = 0;
		for(int i = 0;i<K;i++)
			ris +=errForBlock[i];
		ris /= K; 
		return ris;
	}
 
 
	// Calcola l'errore di delta tra un minimo ed un massimo incrementandolo
	// di uno step e salva il risultato in un file di output
	public void computeDelta(String risPath, int numBlock, double minDelta, double maxDelta, double step) throws Exception{
		double err;
		FileWriter out = new FileWriter(risPath);
		PrintWriter pr = new PrintWriter(out);
		GestoreFile[] blocks = builtBlock(numBlock);			/*creo i blocchi*/
		if(blocks!= null){
			pr.println("number of block (K): "+numBlock);
			pr.println("DELTA  ERROR");
			for(double tryDelta = minDelta ; tryDelta<=maxDelta ; tryDelta += step){
				err = computeError(blocks, tryDelta);			/*calcolo l'errore con quel delta*/
				pr.println(tryDelta+" , "+err);
			}
		}
		else pr.println("K too big for the number of files");
		pr.close();
		out.close();
	}
 
	
	
	
 /* MAIN DI PROVA
 public static void main(String[] args) throws Exception{
	Dizionario dic = new Dizionario("Files/dictionary.txt");;
	GestoreFile file = new GestoreFile(dic);
	Documento[] docArr = new Documento[15];
	docArr[0] = new Documento("Files/fileOk_1.txt", Documento.cat.Ok, dic);
	docArr[1] = new Documento("Files/fileOk_2.txt", Documento.cat.Ok, dic);
	docArr[2] = new Documento("Files/fileOk_3.txt", Documento.cat.Ok, dic);
	docArr[3] = new Documento("Files/fileOk_4.txt", Documento.cat.Ok, dic);
	docArr[4] = new Documento("Files/fileOk_5.txt", Documento.cat.Ok, dic);
	docArr[5] = new Documento("Files/fileSpam_1.txt", Documento.cat.Spam, dic);
	docArr[6] = new Documento("Files/fileSpam_2.txt", Documento.cat.Spam, dic);
	docArr[7] = new Documento("Files/fileSpam_3.txt", Documento.cat.Spam, dic);
	docArr[8] = new Documento("Files/fileSpam_4.txt", Documento.cat.Spam, dic);
	docArr[9] = new Documento("Files/fileSpam_5.txt", Documento.cat.Spam, dic);
	docArr[10] = new Documento("Files/fileToAnalyze_1.txt", Documento.cat.None, dic);
	docArr[11] = new Documento("Files/fileToAnalyze_2.txt", Documento.cat.None, dic);
	docArr[12] = new Documento("Files/fileToAnalyze_3.txt", Documento.cat.None, dic);
	docArr[13] = new Documento("Files/fileToAnalyze_4.txt", Documento.cat.None, dic);
	docArr[14] = new Documento("Files/fileToAnalyze_5.txt", Documento.cat.None, dic);
	
	for(int i = 0; i<15;i++){
		file.insertFile(docArr[i]);
	}
	Classificatore prova = new Classificatore(file);
	System.out.println("Likelihood ok/ok:" + getLikelihood(docArr[0], docArr[1]));
	System.out.println("Likelihood spam/ok:" + getLikelihood(docArr[7], docArr[1]));
	System.out.println("Analize ok: "+ prova.classifyFile(docArr[0]));
	System.out.println("Analize ok: "+ prova.classifyFile(docArr[2]));
	System.out.println("Analize ok: "+ prova.classifyFile(docArr[3]));
	System.out.println("Analize ok: "+ prova.classifyFile(docArr[1]));
	System.out.println("Analize spam: "+ prova.classifyFile(docArr[6]));
	System.out.println("Analize spam: "+ prova.classifyFile(docArr[8]));
	System.out.println("Analize spam: "+ prova.classifyFile(docArr[9]));
	System.out.println("Analize spam: "+ prova.classifyFile(docArr[5]));
	prova.classifyAll();
	prova.computeDelta("ris1.txt", 3, 1, 320, 10);
 	}
 */
 
}

