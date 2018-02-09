/**
 * ExpressionDef:
 * 		Classe corrispondende alla definizione di un'istruzione, eredita dalla classe statement
 */

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionDef extends Statement {
	
	
	public ExpressionDef(LinkedList<String> elem, Context variables){
		String temp;
		/*----------- Controllo Correttezza --------------*/
		temp = elem.removeFirst();
		Pattern path = Pattern.compile("\\Q(\\E[\\s]*GET[\\s]+.*[\\s]*\\Q)\\E");
		Matcher match = path.matcher(temp);
		if(!match.matches())
			err = new Error(temp+" is not valid.");
		
		elem.removeFirst();
		try{
			this.expression = Expression.factoryMethod(elem, variables);
		}catch(Error e){
			if(err== null)
				this.err = new Error(e.getMessage());
		}
	}
	
	

	public String getValue(Context syTable) {
		if(err != null) return err.getErrorMessage();
		try{
			Long ris = this.expression.getValue(syTable);
			return ris.toString();
		}catch(Error e){
			this.err = new Error(e.getMessage());
			return err.getErrorMessage();
		}
	}
	
}
