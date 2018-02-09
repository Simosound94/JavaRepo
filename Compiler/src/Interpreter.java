/*
 * Interpreter:
 * 	Raprpesenta l'interprete vero e proprio, contiene:
 * 	- toInterpret : stringa relativa al testo da interpretare;
 * 	- expression :	una lista per ogni statement, di token;
 *  - variables :	la Symbol Table contenente le variabili;
 *  - program :		Una lista di statement, compilata durante il parsing
 *  
 *  Consente di fare:
 *  	- Analisi del testo -> compilazione expression
 *  	- parsing			-> compilazione program
 *  	- Analisi semantica	-> valutazione programma
 */

import java.util.Iterator;
import java.util.LinkedList;

public class Interpreter {

	LinkedList<Statement> program;
	String toInterpret;
	LinkedList<LinkedList<String>> expression;
	Context variables;
	
	public Interpreter(String text){
		program = new LinkedList<Statement>();
		toInterpret = text;
		variables = new Context();
	}
	
	
	/**
	 * aggiorna la LinkedList<LinkedList<String>> expression
	 * Trasforma il testo in una sequenza di "token":
	 * 	- il programma è una LinkedList di statement
	 * 	- ciascuno statement è una LinkedList di token formato nel seguente modo:
	 * 	[composto, componente1, componente2, eventualeComponenteDelComp2, 
	 * 	 eventualeCompDelComp2, componente3, ...
	 * 
	 */
	public void lexicalAnalisys(){
		LinkedList<LinkedList<String>> ris = new LinkedList<LinkedList<String>>();
		int i = 0;			/*indice della stringa toInterpret*/
		int j;				/* indice dei singoli Token */
		int par;			/* contatore parentesi */
		int end=0;			/* indice fine statement (quando lo raggiungo nella stringa devo aggiornare first)*/
		boolean first=true;  /*boolean rappresentante il nuovo statement, all'inizio settato a true*/
		LinkedList<String> temp = null;
		while(i<toInterpret.length()){
			if(first){
				if(temp!=null && !temp.isEmpty()) ris.add(temp); 	/*aggiungi lo statement al programma */
				if(temp==null || !temp.isEmpty()) temp = new LinkedList<String>();	/*crea un nuovo statement*/
			}
			/*trovo le sotto espressioni */
			if(toInterpret.charAt(i)=='('){
				j=i+1;
				par=0;
				while(j<toInterpret.length()-1 && !(toInterpret.charAt(j)==')' && par==0)){
					if(toInterpret.charAt(j)=='(') par++;
					else if(toInterpret.charAt(j)==')') par--;
					j++;
				}
				if(first)	end = j;	/* setta end come fine statement*/
				first = false;
				temp.add(toInterpret.substring(i,j+1));
			}
			/*trovo i nomi di variabili o i numeri */
			else if(Character.isLetterOrDigit(toInterpret.charAt(i)) || toInterpret.charAt(i)=='-'){
				j=i+1;
				while(Character.isLetterOrDigit(toInterpret.charAt(j)))
					j++;
				temp.add(toInterpret.substring(i,j));
				i=j-1;		/*setto l'indice alla fine del token analizzato */
			}
			i++;
			if(end <=i) first = true; /*se ho superato l'end, setta first per un altro statement */
			
		}
		expression = ris;
	}
	
	
	/*
	 * 1) Controlla la correttezza della sintassi delle espressioni (durante la loro creazione)
	 * 2) Crea gli elementi necessari alla valutazione
	 */
	public void parsing(){
		if(expression==null) lexicalAnalisys();
		
		/*------------- CREO ALBERO SINTATTICO ------------------*/
		Iterator <LinkedList<String>> it = expression.iterator();
		LinkedList<String> next;
		Statement temp;
		while(it.hasNext()){
			next = it.next();
			temp = Statement.factoryMethod(next, variables);	/*DISTRUGGE l'expression*/
			
			/*
			 * Se rimangono elementi nello statement dopo la creazione dell'elemento
			 * significa che nell'analisi sintattica ne avevo presi di più in quanto mancava
			 * qualche parentesi
			 */
			if(!next.isEmpty() && temp.err==null)
				temp.err = new Error("miss parenthesis before "+next.removeFirst()+" )");
			
			program.add(temp);
		}
	
	}
	

	public void semanticAnalisys(){
		
		if(!program.isEmpty()){
			Iterator<Statement> itStat = program.iterator();
			while(itStat.hasNext()){
				System.out.println(itStat.next().getValue(variables));
			}
		}
	}
	
	
}
