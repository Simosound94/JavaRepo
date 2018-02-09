public class MinHeap {

	int[] t2i, i2t;
	double[] elem;
	int size;
	
	public MinHeap(double[] elem, int[] indice){
		assert(elem.length == indice.length);
		t2i = new int[elem.length+1];
		i2t = new int[elem.length+1];
		this.elem = new double[elem.length+1];
		for(int i = 0; i<elem.length; i++){
			assert(indice[i]<this.elem.length);
			assert(indice[i]>0);
			assert(i2t[indice[i]]==0);
			t2i[i+1] = indice[i];
			i2t[indice[i]]=i+1;
			this.elem[indice[i]]= elem[i];
		}
		size = elem.length;
		for(int i = size/2; i>0; i--)
			sink(i);
	}
	
	public int extractMin(){
		int ris = t2i[1];
		swap(i2t, t2i[1], t2i[size]);
		swap(t2i, 1, size);
		size--;
		elem[t2i[size+1]]= 0;
		i2t[t2i[size+1]] = 0;
		sink(1);
		return ris;
	}
	
	public boolean find(int index){
		if(elem[index] != 0)return true;
		else return false;
	}
	
	public void changeKey(int index, double newElem){
		elem[index] = newElem;
		sink(i2t[index]);
		swim(i2t[index]);
	}
	
	private void sink(int heapIndex){
		int l = left(heapIndex);
		int r = right(heapIndex);
		int m = heapIndex;
		if(l<=size && elem[t2i[l]]<elem[t2i[m]]) m = l;
		if(r<=size && elem[t2i[r]]<elem[t2i[m]]) m = r;
		if(m!= heapIndex){
			swap(i2t, t2i[heapIndex], t2i[m]);
			swap(t2i, heapIndex, m);
			sink(m);
		}
	}
	
	private void swim(int heapIndex){
		while(parent(heapIndex)>0 && elem[t2i[heapIndex]]<elem[t2i[parent(heapIndex)]]){
			swap(i2t, t2i[heapIndex], t2i[parent(heapIndex)]);
			swap(t2i, heapIndex, parent(heapIndex));
			heapIndex = parent(heapIndex);
		}
	}
	
	private int parent(int heapIndex){
		return heapIndex/2;
	}
	private int left (int heapIndex){
		return heapIndex*2;
	}
	private int right (int heapIndex){
		return heapIndex*2+1;
	}
	private void swap(int[] arr, int a, int b){
		int temp = arr[a];
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
		for(double i : elem)
			ris.append(i+" ");
		return ris.toString();
	}
	
}
