import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ComputePrimesBelowN {
	
	public static void main(String[] args){
		System.out.println(computePrimesBelowN(15).toString());

	}
	
	public static List<Integer> computePrimesBelowN(int n){
		List<Integer> primes = new ArrayList<Integer>();
		
		List<Boolean> excluded = new ArrayList<Boolean>(Collections.nCopies(n-1, true));
		
		for(int p=0; p<n-1; p++){
			if(excluded.get(p)){
				primes.add(p+2);
				for(int j=2*p; j<n-1; j+=p){
					excluded.set(j, false);
				}
			}

		}
		return primes;
		
			
	}

}
