/**
 * Devo stare attento a passare all'algoritmo di ordinamento ogni volta il vettore disordinato
 * Vettori di dimensione fissata ars[0]
 * numero di prove args[1]
 * Se noi prendiamo lo stesso vettore, lo stesso algoritmo e lo applichiamo diverse volte
 * il tempo utilizzato sarà un pochino diverso
 * questo è un l'errore di misura
 * 
 * Posso fare un analisi di caso medio, passando agli algoritmi da confrontare un po' di vettori
 * appartenenti al caso medio
 * e facendo diverse prove, fino ad ottenere una distribuzione dei tempi utilizzati
 * (per ogni algoritmo)
 * 
 * posso plottare i risultati su un diagramma di Boxe
 * (Matlab boxe diagram credo)
 * ottenendo un grafico contenente:
 * - riga nera: MEDIANA 50% da una parte, 50% dall'altra
 * - rettangolo 25% percentile: 25% da una parte 75% dall'altra (credo)
 * 
 * a parità di numero di elementi un'algoritmo è migliore se mediamente ci mette meno tempo
 * (per distribuzioni simmetriche valor medio = mediana)
 * 
 * 
 * NEL NOSTRO CASO:
 * l'esercizio di confronto è diverso di quello fatto a teoria (cosa succede al crescere di n)
 * qui vediamo cosa succede per n fissato
 * 
 * supponiamo che l'input sia rappresentativo delle possibili istanze medie del problema
 * prendo in considerazione che la misurazione dei tempi impiegati può essere rumorosa, perciò per
 * confrontare due algoritmi utilizzo diverse prove su diversi input
 * 
 * usiamo un metodo grafico per confrontare le distribuzioni dei tempi dei vari algoritmi
 * il miglior algoritmo è quello che ha bassa mediana e meno dispersione possibile
 * 
 * 
 * QUESTO ALGORITMO PERMETTE DI FARE QUESTO
 * voglio poter ottenere un codice che restituisca una tabella dei tempi impiegati
 * indipendentemente da quali algoritmi voglio testare (appartenenti tutti alla classe Collections)
 * 
 * Semplicemente scrivendo i nomi dei metodi in un vettore di stringhe voglio poter utilizzare
 *  quei metodi, testandoli tutti quanti, senza nemmeno sapere quanti sono
 *  come posso fare questo?
 *  
 *  per sapere quanti sono uso la lunghezza dell'array array.lenght
 *  
 *  java ha una serie di meccanismi (Reflection o programmazione di 2 ordine)
 *  1 ordine: metodi /classi trattano dati
 *  2 ordine: i dati trattati diventano a loro volta metodi / classi
 *  
 * 	Class<?> sortClass = Class.forName("SortArrayList");
 * 	sortClass è un oggetto che contiene un oggetto di tipo classe
 * 	contenente la classe SortArrayList
 * 
 * 	Method sortMethod = sortClass.getMethod(toTest[k], ArrayList.class);
 * l'oggetto sortMethod di tipo Method contiene un metodo della classe sortClass
 * passando come parametri il nome del metodo, ed i parametri del metodo da invocare
 * 
 * sortMethod.invoke(null, running)
 * utilizza il metodo sul paramentro running
 * null: non ha superclassi credo
 * 
 * 
 * Questo è utile per esempio quando si vuol far dialogare java con un database, il metodo di
 * accesso al database è fatto così
 * oppure: voglio creare un'interfaccia grafica che abbia tanti bottoni quanti metodi di un oggetto
 * 
 *  
 */


import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.lang.reflect.Method;

public class ComputeSortingPerformances {

	/*Rappresenta gli algoritmi da testare (titolo della tabella in output) */ 
	private static String[] toTest = {"doHeapSort", "doQuickSort", "doCollectionsSort"}; //, "doQuickSort3way"};

	/* fa il controllo se l'array è ordinato */ 
	private static boolean isSorted(ArrayList<Integer> table) {
		for (int i = 1; i < table.size(); ++i) {
			if (table.get(i-1) > table.get(i)) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		if (args.length < 2) {
			System.out.println("Utilizzo:");
			System.out.println("java ComputeSortingPerformances <n> <rep>");
			System.exit(0);
		}
	
		// n: numero degli elementi da generare
		int n = Integer.parseInt(args[0]);
		
		// rep: numero di ripetizioni del test
		int rep = Integer.parseInt(args[1]);
		
		// Inizializzo generatore casuale
		Random random = new Random();
		
		// Stampo in console l'intestazione della tabella 
		for (String name : toTest) System.out.print(name + " ");
		System.out.println();

		// Creo tanti oggetti timer quanti sono gli algoritmi da testare
		Timer[] timer = new Timer[toTest.length];
		for (int j = 0; j < timer.length; ++j) timer[j] = new Timer();
		
		for (int i = 0; i < rep; ++i) {
			
			// Creo due oggetti: uno per mantenere la versione disordinata ('table')
			// e uno che viene ordinato e sovrascritto ad ogni ciclo ('result')
			ArrayList<Integer> table = new ArrayList<Integer>(n);		/*Creo un array dinamico, non di capacità n ma di dimensione iniziale n*/
			ArrayList<Integer> running = new ArrayList<Integer>(n);
			for (int j = 0; j < n; ++j) {
				int s = random.nextInt(n);
				table.add(s);
				running.add(s);
			}
			
			// Pe ognuno degli algoritmi da testare...
			for (int k = 0; k < toTest.length; ++k) {
				// ... sovrascrivo 'running' con la copia disordinata ...
				Collections.copy(running, table);
				// .... invoco il metodo di sorting sulla base del nome utilizzando
				// i metodi per la  "reflection" di Java
				Class<?> sortClass = Class.forName("SortArrayList");
				Method sortMethod = sortClass.getMethod(toTest[k], ArrayList.class);				
				timer[k].start();
				sortMethod.invoke(null, running);
				timer[k].stop();
				// Controllo che l'algoritmo sia eseguito in modo corretto
				assert(isSorted(running));
			}
			
			// Stampo in console il tempo di esecuzione per ogni algoritmo
			for (Timer t : timer) System.out.print(t.getElapsedMilliSeconds() + " ");
			System.out.println();
			
		}
	}

}
