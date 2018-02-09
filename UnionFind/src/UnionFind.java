
public class UnionFind<Item> {
	public class ItemRef{
		UnionFind<Item> first;
		Item elem;
		ItemRef next;
		
		public ItemRef(UnionFind<Item> first, Item item, ItemRef next){
			this.first = first;
			this.elem = item;
			this.next = next;
		}
	}
	
		public int size;
		public ItemRef head, tail;
	
	public UnionFind(Item toIns){
		size=1;
		head = new ItemRef(this, toIns, null);
		tail = head;
	}
	
	public ItemRef find(){
		return head;
	}
	
	public void union(UnionFind<Item> oth){
		ItemRef other = oth.head;
		ItemRef less;
		if(other.first.size < size){
			less = other;
			other = this.head;
		}
		else less = this.head;
		
		other.first.size += less.first.size;
		other.first.tail.next = less;
		other.first.tail = less.first.tail;
		
		while(less!=null){
			less.first = other.first;
			less = less.next;
		}
		this.head = other.first.head;
		this.tail = other.first.tail;
		
	}
	
	
	public static void main(String[] args) {

	}

}
