import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class SortArrayList {

	private static Random random = new Random();
	
	
	//Funzioni paramentrizzate /!\ con parametro <T extends Comparable<T>>
	public static <T extends Comparable<T>> void doQuickSort(ArrayList<T> table) {
		quickSort(table, 0, table.size() - 1);
	}
	
	public static <T extends Comparable<T>> void doQuickSort3way(ArrayList<T> table) {
		quickSort3way(table, 0, table.size() - 1);
	}
	
	public static <T extends Comparable<T>> void doHeapSort(ArrayList<T> table) {
		heapSort(table);
	}
	
	public static <T extends Comparable<T>> void heapSink(T[] table , int toSink){
		int left =(int)Math.floor(toSink/2);
		int right =(int)Math.floor(toSink/2+1);
		int max= toSink;
		if(table[toSink].compareTo(table[left])<0){
			max = (int)Math.floor(toSink/2);
		}
		if(table[max].compareTo(table[right])<0){
			max = (int)Math.floor(toSink/2+1);
		}
		T temp;
		if(max != toSink){
			temp = table[max];
			table[max]= table[toSink];
			table[toSink] = temp;
			heapSink(table, max);
		}
	}
	
	public static <T extends Comparable<T>> void heapSort(ArrayList<T> table){
		int size = table.size();
		T[] ris = (T[]) new Object[table.size()];
		table.toArray(ris);
		for(int i = 0; i<(int)Math.floor((size-1)/2); i++)
			heapSink(ris, i);
		T temp;
		while(size != 0){
			temp = ris[1];
			ris[1] = ris[--size];
			heapSink(ris, 1);
			table.add(temp);
		}
		Collections.reverse(table);
	}
	
	public static <T extends Comparable<T>> void doMergeSort(ArrayList<T> tableA) {
		int n = tableA.size();
		ArrayList<T> tableB = new ArrayList<T>(n);
		for (int i = 0; i < n; ++i) tableB.add(null);
		mergeSort(tableA, tableB, 0, n - 1);
	}
	
	public static <T extends Comparable<T>> void doCollectionsSort(ArrayList<T> table) {
		Collections.sort(table);
	}
	
	private static <T extends Comparable<T>> void quickSort(ArrayList<T> table, int lo, int hi) {
		if (lo < hi) {
			int q = random.nextInt(hi- lo + 1) + lo;
			swap(table, q, hi);
			int s = partition(table, lo, hi);
			quickSort(table, lo, s -1);
			quickSort(table, s + 1, hi);
		}
			
	}
		
	private static <T extends Comparable<T>> int partition(ArrayList<T> table, int lo, int hi) {
		T x = table.get(hi);
		int i = lo - 1;
		for (int j = lo; j < hi; ++j) {
			if (x.compareTo(table.get(j)) > 0) {
				i = i + 1;
				swap(table, i, j);
			}
		}
		swap(table, (i + 1), hi);
		return (i + 1);
	}
	
	private static <T extends Comparable<T>> void quickSort3way(ArrayList<T> table, int lo, int hi) {
		if (hi <= lo) return;
		int lt = lo; 
		int gt = hi;
		int i = lo + 1;
		T v = table.get(lo);
		while (i <= gt) {
			if (v.compareTo(table.get(i)) > 0) {			//v > table[i]
				swap(table, lt, i);
				lt += 1;
				i += 1;
			} else if (v.compareTo(table.get(i)) < 0) {		// v < table[i]
				swap(table, gt, i);
				gt -= 1;
			} else {
				i += 1;
			}
			quickSort(table, lo, lt -1);
			quickSort(table, gt + 1, hi);
		}
	}
	
	
	private static <T extends Comparable<T>> void mergeSort(
			ArrayList<T> tableA, ArrayList<T> tableB, 
			int lo, int hi) {
		if (hi > lo) {
			int mid = lo + (hi - lo)/2;
			mergeSort(tableA, tableB, lo, mid);
			mergeSort(tableA, tableB, mid + 1, hi);
			merge(tableA, tableB, lo, mid, hi);
		}
	}
	
	private static <T extends Comparable<T>> void merge(
			ArrayList<T> tableA, ArrayList<T> tableB, 
			int lo, int mid, int hi) {
		for (int k = lo; k <= hi; ++k) {
			tableB.set(k, tableA.get(k));
		}
		int i = lo; int j = mid + 1;
		for (int k = lo; k <= hi; ++k) {
			if (i > mid) {
				tableA.set(k, tableB.get(j));
				j = j + 1;
			} else if (j > hi) {
				tableA.set(k, tableB.get(i));
				i = i + 1;
			} else if (tableB.get(j).compareTo(tableB.get(i)) < 0) {
				tableA.set(k, tableB.get(j));
				j = j + 1;
			} else {
				tableA.set(k, tableB.get(i));
				i = i + 1;
			}
		}
	}

	
	
	
	private static <T extends Comparable<T>> void swap(ArrayList<T> table, int i, int j) {
		if (i != j) {
			T temp = table.get(i);
			table.set(i, table.get(j));
			table.set(j, temp);
		}
	}
	
	
}
