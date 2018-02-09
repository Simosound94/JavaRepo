import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CompuntingAnAlternation {
	public static void main(String[] args){
		
		List<Integer> A = new ArrayList<Integer>(10);
		for(int i = 0; i<10; i++){
			A.add((int)(Math.random()*100));
		}
		
		System.out.println(A.toString() +" alternated: "+ computingAnAlternation(A));
		
	}
	
	public static List<Integer> computingAnAlternation(List<Integer> A){
		/*
		 * Given A compute the output such that
		 * A[0] <= A[1] >= A[2] <= A[3] ...		 
		 * 
		 * Solution: the solution is not unique, an easy way to choose one is computing
		 * the median, all values above the median must be stored in odd indexes
		 * */
		
		for(int i=0; i<A.size()-1; i++){
			if((i%2 ==0 && A.get(i)>A.get(i+1)) || (i%2!=0 && A.get(i)<A.get(i+1)))
				Collections.swap(A, i, i+1);
		}
		
		return A;
	}

}
