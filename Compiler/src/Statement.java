/**
 * Statement:
 * 	Rappresenta uno statement del linguaggio
 * 	possiede un factoryMethod per creare GET o SET
 */

import java.util.LinkedList;

public abstract class Statement {
	
	Expression expression;
	Error err;
	
	
	public abstract String getValue(Context syTable);
	
	
	public static Statement factoryMethod(LinkedList<String> elem, Context variables){
		if(elem.get(1).equals("GET"))	return new ExpressionDef(elem, variables);
		else							return new VariableDef(elem, variables);
	}
	

}
