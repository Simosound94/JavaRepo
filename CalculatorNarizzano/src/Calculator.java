class MyOwnException extends ArithmeticException {
   public MyOwnException(String msg){
      super(msg);
   }
}



public class Calculator {
	
	public int sum(int var1, int var2){
		System.out.println("Somma tra i valori : " + var1 + " e " + var2);
		return var1+ var2;
	}
	
	public int sub(int var1, int var2){
		System.out.println("Sottrazione tra i valori : " + var1 + " e " + var2);
		return var1 - var2;
	}
	
	public int multiply(int var1, int var2) {
		System.out.println("Moltiplicazione tra i valori : " + var1 + " e " + var2);
		return var1 * var2;
	}
	
	public double divide(int var1, int var2) {
		System.out.println("Divisione tra i valori : " + var1 + " e " + var2);
		if (var2 == 0) {
			throw new MyOwnException("DivisionBy0");
		}
		return (double) var1/(double) var2;
	}
}
