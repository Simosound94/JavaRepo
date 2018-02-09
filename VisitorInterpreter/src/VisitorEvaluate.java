
public class VisitorEvaluate extends VisitorExpression {
	
	public Integer visit(SimpleNumber n){
		return n.geValue();
		
	}
	
	
	public Integer visit(SimpleOperator n){
		int valueLeft = (int) n.getLeft().evaluate();
		int valueRight = (int) n.getRight().evaluate();
		
		switch (n.getOperator()) {
		case ADD :
			return (valueLeft + valueRight);
		case SUB :
			return (valueLeft - valueRight);
		case MUL :
			return (valueLeft * valueRight);
		case DIV :
			return (valueLeft / valueRight);
		case NOP :
			return null;
		default :
			return null;
		}
	}
		
		
}
