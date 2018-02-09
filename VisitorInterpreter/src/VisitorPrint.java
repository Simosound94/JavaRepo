
public class VisitorPrint extends VisitorExpression {
	
	public String visit(SimpleNumber n){
		return String.valueOf(n.evaluate());
	}
	
	
	public String visit(SimpleOperator op){
		switch (op.getOperator()) {
		case ADD :
			return ("(" + op.getLeft() + " + " + op.getRight() + ")");
		case SUB :
			return ("(" + op.getLeft() + " - " + op.getRight() + ")");
		case MUL :
			return ("(" + op.getLeft() + " * " + op.getRight() + ")");
		case DIV :
			return ("(" + op.getLeft() + " / " + op.getRight() + ")");
		case NOP :
			return null;
		default :
			return null;
		}
	}
	
}
