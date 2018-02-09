/**
 * VariableDef:
 * 	Classe corrispondende alla definizione di una variabile, eredita dalla classe statement
 */
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VariableDef extends Statement {

	VariableID var;
	
	
	public VariableDef(LinkedList<String> elem, Context variables){
		String temp;
		temp = elem.removeFirst();
		/*--------------- Controllo Correttezza --------------- */
		Pattern path = Pattern.compile("\\Q(\\E[\\s]*SET[\\s]+[a-zA-Z]+[\\s]*.*[\\s]*\\Q)\\E");
		Matcher match = path.matcher(temp);
		if(!match.matches()){
			err = new Error(temp+" is not valid.");
		}
		elem.removeFirst();
		try{
			var = VariableID.linkVar(elem.removeFirst(), true , variables);
			this.expression = Expression.factoryMethod(elem, variables);
		} catch(Error e){
			if(err== null)
				this.err = new Error(e.getMessage());
		}
		
	}
	
	
	public String getValue(Context syTable) {
		Long ris = null;
		if(err != null) return err.getErrorMessage();
		try{
			ris = this.expression.getValue(syTable);
		}catch(Error e){
			this.err = new Error(e.getMessage());
			return err.getErrorMessage();
		}
		var.setValue(ris, syTable);
		return var.getName()+" = "+ris.longValue();
		
	}
	
}
