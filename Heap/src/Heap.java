import java.util.ArrayList;

public class Heap <Item extends Comparable<Item>> {
	
	public final ArrayList<Item> heap;
	public int nItem;	//Tutte le volte devo inserire nel successivo
	
	public Heap(){
		heap = new ArrayList<Item>();
		heap.add(null); /*prima posizione vuota */
		nItem = 0; 
	}
	
	public Heap(Item[] arr){
		int size = arr.length;
		nItem = size + 1;
		heap = new ArrayList<Item>(arr.length+1);
		for(int i = 0 ; i<size ; i++)
			heap.add(i+1, arr[i]);
		for(int i = (int) Math.floor(size/2) ; i>0 ; i++){
			heapSink(i);
		}
	}
	
	public int parent(int index){
		return (int)Math.floor(index/2);
	}
	
	public int left(int index){
		return 2*index;
	}
	
	public int right(int index){
		return 2*index + 1;
	}
	
	public void heapSink(int toSink){
		int l,r,m;
		l = left(toSink);
		r = right(toSink);
		if(l<=nItem && heap.get(l).compareTo(heap.get(toSink))>0)		m = l;
		else												m = toSink;
		if(r<=nItem && heap.get(r).compareTo(heap.get(m))>0)			m = r;
		if(m!=toSink){
			swap(toSink,m);
			heapSink(m);
		}
		
	}
	
	public void heapSwim(int toSwim){
		while(toSwim > 1 && heap.get(parent(toSwim)).compareTo(heap.get(toSwim))<0){
			swap(toSwim , parent(toSwim));
			toSwim = parent(toSwim);
		}
	}
	
	public void insert(Item toInsert){
		nItem++;
		heap.add(nItem, toInsert);
		heapSwim(nItem);
	}
	
	public ArrayList<Item> heapSort(){
		Heap<Item> ris = new Heap<Item>();
		ris.heap.remove(0);
		ris.heap.addAll(this.heap);
		ris.nItem = nItem;
		for(int i = ris.nItem; i>1 ; i--){
			ris.swap(1, i);
			ris.nItem--;
			ris.heapSink(1);
		}
		ris.heap.remove(0);
		return ris.heap;
	}
	
	
	
	/////////////////////
	//
	// Private
	//
	//////////////////////
	
	private void swap(int a, int b){
		Item temp = heap.get(a);
		heap.set(a, heap.get(b));
		heap.set(b, temp);
	}
	
	
	public static void main(String[] args){
	
		Heap<Integer> prova = new Heap<Integer>();
		prova.insert(69);
		prova.insert(2);
		prova.insert(12);
		prova.insert(0);
		prova.insert(48);
		prova.heapSink(3);
		prova.heapSwim(4);
		System.out.println(prova.heap.toString());
		System.out.println(prova.heapSort());
	}
	
	
	
}

