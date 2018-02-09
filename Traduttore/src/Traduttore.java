/**
 * Traduttore
 *  - binario decimale
 *  - decimale binario
 * 
 * @author simone
 *
 */
public class Traduttore {
	
	
	/*
	public static void main(String[] args){
		Traduttore tr = new Traduttore();
		System.out.println(tr.decToBin(3));
		System.out.println(tr.binToDec(11));
	}
	*/
	
	public String binToDec(int n) {
		 int resto;
		 String risultato="";
		 while (n>0)
		 {
			 resto=n%2;
			 n/=2;
			 risultato=resto+risultato;
		 }
		 return risultato;
	}

	
	public int decToBin(int n) {
		String a = Integer.toString(n);
		int y = 0;
		int z = 0;
		int x = 0;
		for(int i = a.length()-1; i >= 0;i--){
			z = Integer.parseInt(a.substring(i,i+1));
			y = (int)(y + (z*Math.pow(2, x))); x++;
		}
		return y;
	}
}
