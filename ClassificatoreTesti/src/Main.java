import java.io.FileReader;
import java.util.Iterator;
import java.util.Scanner;


public class Main {
	
	public static enum modality {Run, testDelta};

	/*------------- Scegli modalita :-------------------------*/
	public static final modality mod = Main.modality.Run;
	
	/*-------------------Costanti utilizzate per testare delta---------*/
	public static final String risPath = "risProva.txt";
	public static final int numBlock = 10;
	public static final double minDelta = 160;
	public static final double maxDelta = 280;
	public static final double stepDelta = 2;
	
	
	
	

	@SuppressWarnings("incomplete-switch") //Soppreso perchè classifyFile non può restituire "None"
	public static void main(String[] args) {
		
		//---------------------------- Input ------------------------------------
		
		if(args.length!=1){
			System.err.println("Insert file with the information.");
			System.exit(0);
		}
		FileReader input=null;
		Scanner in= null;
		
		try{
			input = new FileReader(args[0]);
			in = new Scanner(input);
		}catch(Exception e){
			System.err.println(e.getMessage());
			System.exit(0);
		}
		String dictionaryPath = in.next();
		String okSuffix = in.next();
		int minOk = in.nextInt();
		int maxOk = in.nextInt();
		String spamSuffix = in.next();
		int minSpam = in.nextInt();
		int maxSpam = in.nextInt();
		String noneSuffix = in.next();
		int minNone = in.nextInt();
		int maxNone = in.nextInt();
		in.close();
		try{
			input.close();
		}catch(Exception e){
			System.err.println(e.getMessage());
			System.exit(0);
		}
		
		//------------ Generazione degli elementi necessari (TRAINING) ----------------------
		
		Dizionario dictionary = null;
		try{
			dictionary = new Dizionario(dictionaryPath);
		}catch(Exception e){
			System.err.println(e.getMessage());
			System.exit(0);
		}
		GestoreFile file = new GestoreFile(dictionary);
		String path;
		for(int i = minOk ; i<=maxOk ; i++){
			path=okSuffix+i+".txt";
			file.insertFile(path, Documento.cat.Ok);
		}
		for(int i = minSpam ; i<=maxSpam ; i++){
			path=spamSuffix+i+".txt";
			file.insertFile(path, Documento.cat.Spam);
		}
		for(int i = minNone ; i<=maxNone ; i++){
			path=noneSuffix+i+".txt";
			file.insertFile(path, Documento.cat.None);
		}
		Classificatore classifier = new Classificatore(file);
		
		//--------------------------- ESEGUZIONE -----------------------------
			
		if(mod == Main.modality.testDelta){
			
			//------------------------------ Modalita Test-------------------------
	
			System.out.println("Start testing...");
			try{
				classifier.computeDelta(risPath, numBlock, minDelta, maxDelta, stepDelta);
			}catch(Exception e){
				System.err.println(e.getMessage());
				System.exit(0);
			}
			System.out.println("End testing.");
		}
		else{ 
			
			//---------------------------- Modalita Run (TESTING FILE) ------------------------
			
			int[] distances = new int[maxOk+maxSpam];
			Iterator<Documento> itNone = file.getIterator(Documento.cat.None);
			Documento.cat valutazione;
			while(itNone.hasNext()){
				//I file già sono messi ordinati, sia quelli da classificare che quelli classificati
				valutazione = classifier.classifyFile(itNone.next(), distances);
				switch(valutazione){
				case Ok:
					System.out.print("no ");
					break;
				case Spam:
					System.out.print("si ");
					break;
				}
				for(int i = 0 ; i<maxOk+maxSpam-1 ; i++)
					System.out.print(distances[i]+" ");
				System.out.println(distances[maxOk+maxSpam-1]);
			}
		}	
	}
	
	

}
