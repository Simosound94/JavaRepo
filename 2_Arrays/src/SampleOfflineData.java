import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SampleOfflineData {

	public static void main(String[] args) {
		System.out.println(randomUniformSampling(Arrays.asList(1,2,3,4,5,6,7,8),3).toString());

	}
	
	public static List<Integer> randomUniformSampling(List<Integer> set, int subSize){
		Random gen = new Random();
		for(int i = 0; i< subSize; i++){
			Collections.swap(set, i, i+gen.nextInt(set.size()-i));
		}
		return set.subList(0, subSize);
	}

}
