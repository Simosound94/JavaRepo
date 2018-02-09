import java.util.ArrayList;

public class PriorityQueue<Item extends Comparable<Item>> {
	int[] i2t, t2i;
	ArrayList<Item> elem;
	int size;
	private boolean min = true;
	
	public PriorityQueue(int n, boolean min){
		t2i = new int[n+1];
		
		//Inizializzato automaticamente a 0
		i2t = new int[n+1];
		
		//Inizializzato autom a null
		elem = new ArrayList<Item>(n+1);
		for(int i = 0; i<n+1; i++)
			this.elem.add(null);
		size = 0;
		this.min = min;
	}
	
	
	public PriorityQueue(Item[] elem, int[] key, boolean min){
		assert(elem.length == key.length);
		
		int max = 0;
		for(int i=1; i<key.length; i++)
			if(key[i]>key[max]) max=i;
		if(key.length+1 > key[max]) max = key.length+1;
		else						max = key[max]+1;
		t2i = new int[max];
		i2t = new int[max];
		this.elem = new ArrayList<Item>(max);
		for(int i = 0; i<max; i++)
			this.elem.add(null);
		for(int i = 0 ; i<key.length; i++){
			i2t[key[i]] = i+1;
			t2i[i+1] = key[i];
			this.elem.set(key[i], elem[i]);
		}
		this.size = elem.length-1;
		this.min = min;
		for(int i = max/2; i>0; i--){
			prioritySink(i);
		}
            	}
	
	
	public boolean insert(int index, Item key){
		if(index>t2i.length-1 || index==0 || i2t[index]!=0)	return false;
		elem.set(index, key);
		size++;
		t2i[size] = index;
		i2t[index] = size;
		prioritySwim(size);
		return true;
	}
	
	private void prioritySwim(int toSwim){
		int father = parent(toSwim);
		while(father>0 && ((min && elem.get(t2i[father]).compareTo(elem.get(t2i[toSwim]))>0) ||
				(!min && elem.get(t2i[father]).compareTo(elem.get(t2i[toSwim]))<0))){
			swap(t2i, father, toSwim);
			swap(i2t, t2i[father], t2i[toSwim]);
			toSwim = father;
			father = parent(toSwim);
		}
	}
	
	private void prioritySink(int prToSink){
		int l = left(prToSink);
		int r = right(prToSink);
		int m=prToSink;
		if(min && l<=size && elem.get(t2i[m]).compareTo(elem.get(t2i[l]))>0 ||
				!min && l<=size && elem.get(t2i[m]).compareTo(elem.get(t2i[l]))<0)
			m = l;
		if(min && r<=size && elem.get(t2i[m]).compareTo(elem.get(t2i[r]))>0 ||
				!min &&  r<=size && elem.get(t2i[m]).compareTo(elem.get(t2i[r]))<0)
			m = r;
		if(m!=prToSink){
			swap(t2i, m, prToSink);
			swap(i2t, t2i[m], t2i[prToSink]);
			prioritySink(m);
		}
	}
	
	public boolean find(int index){
		if(elem.get(index) != null)return true;
		else return false;
	}
	
	public int extractTop(){
		int ris = t2i[1];
		elem.set(t2i[1], null);
		swap(i2t, t2i[1], t2i[size]);
		swap(t2i, 1, size);
		t2i[size] = 0;
		i2t[t2i[size]] = 0;
		size--;
		prioritySink(1);
		return ris;	
	}
	
	public void changeElem(int index, Item newElem){
		if(!(index>elem.size() && i2t[index]==0 && index==0))
		elem.set(index, newElem);
		prioritySink(i2t[index]);
		prioritySwim(i2t[index]);
	}
	
	private int parent(int index){
		return (int)Math.floor(index/2);
	}
	
	private int left(int index){
		return 2*index;
	}
	
	private int right(int index){
		return 2*index + 1;
	}
	
	private void swap(int[] arr, int a, int b){
		Integer temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
	
	
	public String toString(){
		StringBuffer ris = new StringBuffer();
		ris.append("T2I \n");
		for(int i : t2i)
			ris.append(i+" ");
		ris.append("\nI2T\n");
		for(int i : i2t)
			ris.append(i+" ");
		ris.append("\nElem\n");
		for(Item i : elem)
			ris.append(i+" ");
		return ris.toString();
	}
	
	public static void main(String args[]){
		Integer[] elem = {3, 4, 5,6 ,1};
		int[] key = {1, 2, 9,7 ,4};
		PriorityQueue<Integer> prova = new PriorityQueue<Integer>(elem, key, false);
		System.out.println(prova);
		System.out.println(prova.extractTop());
		System.out.println(prova);
		prova.changeElem(9, 2);
		System.out.println(prova);
		prova.insert(8, 12);
		System.out.println(prova);
	}
}
