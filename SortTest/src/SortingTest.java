import java.util.ArrayList;
import java.util.Random;

public class SortingTest {
	
	private static final int N = 10;
	
	public static void main(String[] args) {
		ArrayList<Integer> table = new ArrayList<Integer>();
		Random random = new Random();
		
		for (int i = 0; i < N; ++i) {
			table.add(random.nextInt(N));
			System.out.print(table.get(i) + " ");
		}
		System.out.println();
		
		SortArrayList.doQuickSort3way(table);
		
		for (int i = 0; i < N; ++i) {
			System.out.print(table.get(i) + " ");
		}
		System.out.println();
		
	}
}