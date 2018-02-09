
public class SimpleOperator<ValueType> extends SimpleExpression<Integer> {
	public enum OperatorTag { NOP, ADD, SUB, MUL, DIV };
	
	private OperatorTag               operator;
	private SimpleExpression<Integer> left;
	private SimpleExpression<Integer> right;
	private VisitorExpression visit;
	
	public SimpleOperator(OperatorTag operator, 
			SimpleExpression<Integer> left, SimpleExpression<Integer> right) {
		this.operator = operator;
		this.left = left;
		this.right = right;
	}
	
	
	public Integer evaluate() {
		visit = new VisitorEvaluate();
		return (Integer) visit.visit(this);
	}
	

	public String toString(){
		visit = new VisitorPrint();
		return (String) visit.visit(this);
	}
	
	public SimpleExpression<Integer> getLeft(){
		return left;
	}
	
	public SimpleExpression<Integer> getRight(){
		return right;
	}
	public OperatorTag getOperator(){
		return operator;
	}
	

}
