/**
 * Expression:
 * 	classe astratta contenente tutti gli operatori previsti dal linguaggio
 * 	contiene inoltre un factoryMethod per la loro costruzione.
 * 
 */

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public abstract class Expression {
	
	public abstract long getValue(Context syTable) throws Error; 
	
	
	/*
	 * Seleziona e costruisce l'espressione piu adatta, può trattarsi di:
	 * -numero
	 * -variabile
	 * -operatore
	 */
	public static Expression factoryMethod(LinkedList<String> elem, Context variables) throws Error{
		/* text può essere:
		 * espressione: (OP ex1 ex2) o numero o nome variablie */
		Expression ris = null;
		String text = elem.removeFirst();
		/*
		 * Mi basta controllare il primo carattere per distinguerli essendo
		 * - numero 	-> inizia con [0-9] o con '-'
		 * - variabile 	-> inizia con [a-zA-Z]
		 * - operatore 	-> altrimenti
		 */
		if(Character.isDigit(text.charAt(0)) || text.charAt(0)=='-')	//Se è numero
			ris = new Number(text);
		else if(Character.isLetter(text.charAt(0)))	//Se è variabile
			ris = VariableID.linkVar(text, false, variables);
		else{
			/*---- Controllo Correttezza sotto espressione ----*/
			Pattern path = Pattern.compile("\\Q(\\E[\\s]*(ADD|SUB|MUL|DIV)[\\s]+(?<exp1>.+)[\\s]*(?<exp2>.+)[\\s]*\\Q)\\E");
			Matcher match = path.matcher(text);
			if(!match.matches())
				throw new Error(text+" isn't a valid operation.");
			
			ris = new Operator(elem, variables);
		}
		return ris;
	}
	
}
