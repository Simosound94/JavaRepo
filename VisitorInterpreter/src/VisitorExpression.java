
public abstract class VisitorExpression {
	
	public abstract Object visit(SimpleNumber n);
	public abstract Object visit(SimpleOperator n);
	
	
	//Posso fare così per poter mettere i tipi di ritorno compatibili tra le visite??

}
