import javax.swing.JOptionPane;


public class SimpleInterpreter {
	
	
	private static class ParseStatus {
		public SimpleExpression<Integer> expr;
		public int                       index;
	}
	
	
	public static class ParseException extends Exception {
		private static final long serialVersionUID = 1L;
	}
	
	
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
			
		} else if (Character.isDigit(text.charAt(index))) {
			return parseNumber(text, index);
		} else {
			throw new ParseException();
		}
	}
	
	private static ParseStatus parseNumber(String text, int index) {
		int saved = index;											
		while (index<text.length() && Character.isDigit(text.charAt(index))) {
			index += 1;												
		}
		int value = Integer.parseInt(text.substring(saved, index));
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
		JOptionPane.showMessageDialog(null,  "L'espressione letta  è: " + expression);
		JOptionPane.showMessageDialog(null,  "Il risultato è: " + expression.evaluate());
	}
}
