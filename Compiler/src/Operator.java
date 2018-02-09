/**
 * Operator:
 * 	eredita da Expression
 * 	consente di creare espressioni "nested"
 */
import java.util.LinkedList;

public class Operator extends Expression {

public enum OperatorTag {ADD, SUB, MUL, DIV };
	
	private OperatorTag operator;
	private Expression left;
	private Expression right;
	

	
	public Operator(LinkedList<String> elem, Context variables) throws Error{
		String temp = elem.removeFirst();
		switch(temp){
		case "ADD": this.operator = OperatorTag.ADD;
					break;
		case "SUB": this.operator = OperatorTag.SUB;
					break;
		case "MUL": this.operator = OperatorTag.MUL;
					break;
		case "DIV": this.operator = OperatorTag.DIV;
					break;
		default:	{
					throw new Error(temp+" isn't a valid operator.");
					}
		}
		left = Expression.factoryMethod(elem, variables);
		/*il factoryMethod avrà rimosso gli elementi di elem necessari fino all'altra espressione*/
		right = Expression.factoryMethod(elem, variables);
	}



	public long getValue(Context syTable) throws Error {
		Long ris1 = left.getValue(syTable);
		Long ris2 = right.getValue(syTable);
		switch(operator){
		case ADD: return(ris1+ris2);
		case SUB: return(ris1-ris2);
		case MUL: return(ris1*ris2);
		case DIV:
				{
					if(ris2==0)
						throw new Error("division by zero.");
	
					return(ris1/ris2);
				}
		}
		return 0; /*Impossibile*/
	}
	
	
}
