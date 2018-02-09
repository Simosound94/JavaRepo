
public class SimpleNumber extends SimpleExpression<Integer> {
	private int value;
	VisitorExpression visit;
	
	//Sbagliato: il visitor non deve fare parte dell'oggetto visitato
	//deve essere l'oggetto visitor ad avere dentro di se l'oggetto da visitare
	
	public SimpleNumber(int value) {
		this.value = value;
	}
	
	public Integer geValue() {
		return value;
	}
	
	public String toString(){
		visit = new VisitorPrint();
		return (String) visit.visit(this);
	}

	public Integer evaluate() {
		visit = new VisitorEvaluate();
		return (Integer) visit.visit(this);

	}
	
}
