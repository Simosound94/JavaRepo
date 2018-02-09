/**
 * VariableID
 * 	eredita da Expression
 * 	rappresenta il token "variabile" presente in un'espressione
 */
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VariableID extends Expression {
	
	private String name;
	
	public VariableID() {
		name = null;
	}
	

	
	String getName() {
		return name;
	}
	
	
	public static VariableID linkVar(String elem, boolean isADefinition, Context variables) throws Error{
		Long x =variables.getVariable(elem); /*variabile di cui stiamo parlando */
		
		/**
		 * se arrivo da VariableDef -> isADefinition = true
		 * 		se non trovo la variabile, la definisco
		 * 		se la trovo, la ritorno, in modo da poter cambiare il suo valore
		 * 		(altro oggetto VariableID ma con lo stesso nome di variabile)
		 * se arrivo da Expression -> isADefinition = false
		 * 		se non trovo la variabile definita ritorno errore
		 * 
		 */
		VariableID ris = new VariableID();
		if(isADefinition && x == null){ 
			//Controllo nome non parola chiave
			Pattern path = Pattern.compile("[a-zA-Z]+");
			Matcher match = path.matcher(elem);
			if(elem.equals("ADD")||elem.equals("SUB")||elem.equals("MUL")||elem.equals("DIV")||
					elem.equals("GET")||elem.equals("SET") || !match.matches()){
				throw new Error(elem+" isn't a valid variable name.");
			}
			
			ris.name = elem;
			/*inizializzo a 0 */
			variables.setVariable(elem, 0);
		}
		else if(x != null){
			ris.name = elem;
		}
		else{
				throw new Error(elem+" isn't defined jet.");
		}
			return ris;
		
			
	}
	
	public void setValue(long value, Context syTable){
		syTable.setVariable(name, value);
	}



	public long getValue(Context syTable) {
		Long ris = syTable.getVariable(name);
		assert(ris!=null);
		return ris;
	}



}
