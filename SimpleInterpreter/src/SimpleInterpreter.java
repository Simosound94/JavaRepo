/*
 * DAL PUNTO di vista strutturale sono un Pattern Composite
 * da quello comportamentale sono un Pattern Interpreter
 * 
 * 
 */

import javax.swing.JOptionPane;


public class SimpleInterpreter {
	
	//Coppia da restituire dopo il parsing
	//Quando faccio parsing devo restituire sia l'espressione creata, che l'indice dove sono arrivato
	//Ciò da lo stato del parsing
	private static class ParseStatus {
		public SimpleExpression<Integer> expr;
		public int                       index;
	}
	
	
	//Classe eccezione creata, deve estendere Exception per essere Trowable
	public static class ParseException extends Exception {
		
		/*Aggiunta automaticamente da java per dare un id unico ad un'eccezione */
		private static final long serialVersionUID = 1L;
	}
	
	
	//Restituisce l'operazione letta, (albero credo) se è andata a buon fine
	//Devo gestire il fatto che l'espressione possa essere sbagliata (eccezione ParsException)
	private static SimpleExpression<Integer> parseExpression(String text) throws ParseException {
		ParseStatus ps = parseOperator(text, 0);
		if (ps.index != text.length()){
			throw new ParseException();
		}
		return ps.expr;
	}
	
	private static ParseStatus parseOperator(String text, int index) throws ParseException {
		if (text.charAt(index) == '(') {
			
			//Valutazione primo operando	
			ParseStatus psLeft = parseOperator(text, index + 1);
			index = psLeft.index;
			
			//Operatore
			SimpleOperator.OperatorTag op = SimpleOperator.OperatorTag.NOP;
			switch (text.charAt(index)) {
			case '+' : op = SimpleOperator.OperatorTag.ADD; break;
			case '-' : op = SimpleOperator.OperatorTag.SUB; break;
			case '*' : op = SimpleOperator.OperatorTag.MUL; break;
			case '/' : op = SimpleOperator.OperatorTag.DIV; break;
			default : throw new ParseException();
			}
			
			//Valutazione secondo operatore
			ParseStatus psRight = parseOperator(text, index + 1);
			index = psRight.index;
			if (text.charAt(index) != ')') {
				throw new ParseException();
			}
			
			//Creazione espressione
			ParseStatus ps = new ParseStatus();
			ps.expr = new SimpleOperator(op, psLeft.expr, psRight.expr);
			ps.index = index + 1;
			return ps;
			
			//Se invece che essere ( il carattere è un numero
		} else if (Character.isDigit(text.charAt(index))) {
			return parseNumber(text, index);
		} else {
			throw new ParseException();
		}
	}
	
	private static ParseStatus parseNumber(String text, int index) {
		int saved = index;											/*Salvo indice iniziale*/
		while (index<text.length() && Character.isDigit(text.charAt(index))) {
			index += 1;												/*Scorro finchè ci sono numeri*/
		}
		int value = Integer.parseInt(text.substring(saved, index));	/*Costruisco numero*/
		ParseStatus ps = new ParseStatus();
		ps.expr = new SimpleNumber(value);
		ps.index = index;
		return ps;
	}
	
	public static void main(String[] args) throws Exception {
		String message = 
				"Inserisci un'espressione da valutare\n" +
				"Usare solo numeri, parentesi, e operatori (+,-,*,/)";	
				
		String text = JOptionPane.showInputDialog(null, message);
		
		SimpleExpression<Integer> expression = parseExpression(text);

		//Possiamo mettere direttamente l'object expression al posto del expression.toString
		//Questo funziona perchè tutti gli oggetti ereditano da Object e questo ha il metodo toString
		JOptionPane.showMessageDialog(null,  "L'espressione letta  è: " + expression);
		
		JOptionPane.showMessageDialog(null,  "Il risultato è: " + expression.evaluate());
	}
}
