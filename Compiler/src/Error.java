/**
 * Error:
 * 	Classe utilizzata per gestire gli errori nel file da interpretare
 * 	consente di fare un throw, dall'espressione (operazione, numero, o variabile)
 * 	in fondo all'albero sintattico, fino allo statement, che possiede un Error,
 * 	da valutare prima di calcolare il valore dello stesso statement
 */

public  class Error extends Exception{

	private static final long serialVersionUID = 1L;
	
	
	public Error(String message){
		super(message);
	}
	
	public String getErrorMessage(){
		return "(ERROR "+super.getMessage()+")";
	}
}
