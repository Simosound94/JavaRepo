/**
 * COSI FA SCHIFO
 * meglio farlo come ogni set è un' union find
 * 
 * ciò equivale a creare la classe URef, con al suo interno union ecc..
 * ed una lista concatenata di item
 * 
 * l'array di set me ne batto il cazzo e lo faccio di URef anzi che di ItemRef (rappresentanti)
 * il rappresentante sara in [URef].first
 * 
 * 
 * tra l'altro fare un array di ItemRef è un casino perchè al loro interno hanno item
 * 
 */


public class UnionFindArr<Item> {

	public class ItemRef{
		Item value;
		ItemRef next;
		URef first;
		
		public ItemRef(Item v, ItemRef n, URef f){
			value = v;
			next = n;
			first = f;
		}
	}

	public class URef{
		int size;
		ItemRef head, tail;
	}
	
	public ItemRef[] set;
	public int numSet;
	
	public UnionFindArr(int maxSet){
		assert(maxSet>0);
		set = (ItemRef[]) new Object[maxSet];
		numSet = 0;
	}
	
	public void newSet(Item ref){
		URef x = new URef();
		ItemRef it = new ItemRef(ref,null, x);
		x.size = 1;
		x.head = x.tail = it;
		set[numSet] = it;
		numSet++;	
	}
	
	public void newElem(ItemRef set, Item toIns){
		set.first.tail.next = new ItemRef(toIns, null, set.first);
		set.first.size++;
	}
	
	public ItemRef getAgent(ItemRef toFindSet){
		return toFindSet.first.head;
	}
	
	public boolean sameSet(ItemRef a, ItemRef b){
		return (getAgent(a) == getAgent(b));
	}
	
	public void union(ItemRef a, ItemRef b){
		if(a.first.size < b.first.size){
			ItemRef temp = a;
			a=b;
			b=temp;
		}
		a.first.size += b.first.size;
		a = a.first.tail;
		b = b.first.head;
		a.next = b;
		while(b!=null){
			b.first = a.first;
			b = b.next;
		}
	}
	
	
	
	public static void main(String[] args){
		UnionFindArr<Double> prova = new UnionFindArr<Double>(10);
		prova.newSet(12.5);
		prova.newSet(8.4);
	}
	
}
