/*
 * Il SimpleOperator è un Composite
 * è un'estensione di una espressione composto da due (sotto)espressioni
 * 
 */

public class SimpleOperator<ValueType> extends SimpleExpression<Integer> {
	public enum OperatorTag { NOP, ADD, SUB, MUL, DIV };
	
	private OperatorTag               operator;
	private SimpleExpression<Integer> left;
	private SimpleExpression<Integer> right;
	
	public SimpleOperator(OperatorTag operator, 
			SimpleExpression<Integer> left, SimpleExpression<Integer> right) {
		this.operator = operator;
		this.left = left;
		this.right = right;
	}
	
	public Integer evaluate() {
		
		//In questo caso non c'è contesto, se ci fosse (ad esempio valutare valori variabili)
		//Dovrei passare come paramentro una SymbolTable con tutti i nomi delle variabili
		int valueLeft = left.evaluate();
		int valueRight = right.evaluate();
		switch (operator) {
		case ADD :
			return (valueLeft + valueRight);
		case SUB :
			return (valueLeft - valueRight);
		case MUL :
			return (valueLeft * valueRight);
		case DIV :
			return (valueLeft / valueRight);
		case NOP :
			
			//Defensive Prrogramming, per sicurezza considero anche questi casi
			return null;
		default :
			return null;
		}
	}
	
	public String toString() {
		String valueLeft = left.toString();
		String valueRight = right.toString();
		switch (operator) {
		case ADD :
			return ("(" + valueLeft + " + " + valueRight + ")");
		case SUB :
			return ("(" + valueLeft + " - " + valueRight + ")");
		case MUL :
			return ("(" + valueLeft + " * " + valueRight + ")");
		case DIV :
			return ("(" + valueLeft + " / " + valueRight + ")");
		case NOP :
			return null;
		default :
			return null;
		}
	}

}
