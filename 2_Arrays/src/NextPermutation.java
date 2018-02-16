import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NextPermutation {
	
	public static void main(String[] args){
		List<Integer> a = Arrays.asList(6,2,1,5,4,3,0);
		System.out.println(nextPermutation(a).toString());

	}
	

	public static List<Integer> nextPermutation(List<Integer> toPerm){
		int i,j;
		
		for(i=toPerm.size()-1; i>=0; i--){
			if(toPerm.get(i-1)<toPerm.get(i))
				break;
		}
		i--;
		for(j=i+1; j<toPerm.size(); j++){
			if(toPerm.get(j)<toPerm.get(i))
				break;
		}
		j--;
		Collections.swap(toPerm, i, j);
		Collections.reverse(toPerm.subList(i+1, toPerm.size()));
		
		return toPerm;
	}
	
}
