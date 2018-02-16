import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class SampleOnlineData {

	public static void main(String[] args) {
		
		randomUniformSampling(Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22).iterator(),3);

	}
	
	public static List<Integer> randomUniformSampling(Iterator<Integer> flow, int subSize){
		List<Integer> sampled = new ArrayList<Integer>(Collections.nCopies(subSize, 0));
		long n = 0;
		Random gen = new Random();
		while(flow.hasNext()){
			if(gen.nextInt((int)(n+2))==0){
				int toChange = gen.nextInt(subSize);
				sampled.remove(toChange);
				sampled.add(flow.next());
			}
			else{
				flow.next();
			}
			n++;
			System.out.println(sampled.toString());
		}
		
		return sampled;
		
	}

}
