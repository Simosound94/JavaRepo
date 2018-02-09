import javax.imageio.stream.IIOByteBuffer;

public class PriorQueue {

	int[] t2i, i2t;
	Processo[] elem;
	int size;
	
	public PriorQueue(int maxProc){
		t2i = new int[maxProc+1];
		i2t = new int[maxProc+1];
		elem = new Processo[maxProc+1];
		size = 0;
		for(int i = 0; i<maxProc+1; i++)
			i2t[i] = -1;
	}
	
	public boolean isEmpty(){
		return size==0;
	}
	
	public Processo getMin(){
		return elem[t2i[1]];
	}
	
	public void insert(Processo pr){
		if(pr.id>elem.length || i2t[pr.id]!=-1) throw new IllegalArgumentException();
		size++;
		t2i[size] = pr.id;
		i2t[pr.id] = size;
		elem[pr.id] = pr;
		swim(size);
	}
	
	public int extractMin(){
		int min = t2i[1];
		swap(i2t, t2i[1], t2i[size]);
		swap(t2i, 1, size);
		size--;
		sink(1);
		elem[t2i[size+1]] = null;
		i2t[t2i[size+1]] = -1;
		t2i[size+1] = 0;
		return min;
	}
	
	
	public void changeKey(int procId){
		/* elemento già cambiato fuori da questa funzione*/
		sink(i2t[procId]);
		swim(i2t[procId]);
	}
	
	
	
	
	
	private void sink(int heapIndex){
		int l = left(heapIndex);
		int r = right(heapIndex);
		int m = heapIndex;
		if(l<=size && elem[t2i[l]].compareTo(elem[t2i[m]])>0) m=l;
		if(r<=size && elem[t2i[r]].compareTo(elem[t2i[m]])>0) m=r;
		if(m!= heapIndex){
			swap(i2t, t2i[heapIndex], t2i[m]);
			swap(t2i, heapIndex, m);
			sink(m);
		}
	}
	private void swim(int heapIndex){
		while(heapIndex>1 && elem[t2i[parent(heapIndex)]].compareTo(elem[t2i[heapIndex]])<0){
			swap(i2t, t2i[heapIndex], t2i[parent(heapIndex)]);
			swap(t2i, heapIndex, parent(heapIndex));
			heapIndex = parent(heapIndex);
		}
	}
	
	private void swap(int[]ar, int a, int b){
		int t= ar[a];
		ar[a] = ar[b];
		ar[b] = t;
	}
	
	private int parent(int heapIndex){
		return heapIndex/2;
	}
	private int left(int heapIndex){
		return heapIndex*2;
	}
	private int right(int heapIndex){
		return heapIndex*2+1;
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
		for(Processo i : elem)
			ris.append(i.prior+" ");
		return ris.toString();
	}
}
