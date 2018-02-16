import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MatrixInSpiralOrder {

	public static void main(String[] args) {
		List<Integer> row1 = Arrays.asList(1,2,3,4);
		List<Integer> row2 = Arrays.asList(1,2,3,4);
		List<Integer> row3 = Arrays.asList(1,2,3,4);
		List<Integer> row4 = Arrays.asList(1,2,3,4);
		List<List<Integer>> toOrder = new ArrayList<List<Integer>>();
		toOrder.add(row1);
		toOrder.add(row2);
		toOrder.add(row3);
		toOrder.add(row4);
		System.out.println(matrixInSpiralOrder(toOrder).toString());
	}
	
	public static List<Integer> matrixInSpiralOrder(List<List<Integer>> toOrder){
		int initPoint = 0;
		List<Integer> ordered = new ArrayList<Integer>(toOrder.size()*2);
		
		while(initPoint < (int) (toOrder.size()/2)){
			ordered.addAll(subMatrixInSpiralOrder(toOrder, initPoint, initPoint));
			initPoint++;
		}
		if(toOrder.size()%2 !=0){
			ordered.add(toOrder.get((int)(toOrder.size()%2)+1).get((int)(toOrder.size()%2)+1));
		}
		
		return ordered;
		
	}
	
	private static List<Integer> subMatrixInSpiralOrder(List<List<Integer>> toOrder, int initRow, int initCol){
		
		List<Integer> ordered = new ArrayList<Integer>(4*(toOrder.size()-2*initCol) -4);
		
		//roof
		ordered.addAll(toOrder.get(initRow).subList(initCol, toOrder.size()-initCol));
		//right part
		for(int i=initRow+1; i<toOrder.size()-initRow; i++){
			ordered.add(toOrder.get(i).get(toOrder.size()-initCol -1));
		}
		
		//bottom part
		List<Integer> bottomReversed = toOrder.get(toOrder.size()-initRow-1).subList(initCol, toOrder.size()-initCol-1);
		Collections.reverse(bottomReversed);
		ordered.addAll(bottomReversed);
		
		//left part
		for(int i=toOrder.size()-initRow-2; i>initRow; i--){
			ordered.add(toOrder.get(i).get(initCol));
		}
		
		
			
		
		return ordered;
	}

}
