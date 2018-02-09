
public class FixedSizeQueue  <Item> {

	private Item[] table;
	private int    head;
	private int    tail;
	
	public FixedSizeQueue(int n) {
		table = (Item[]) new Object[n];
		head = tail = 0;
	}
	
	public Item head() {
		assert(!isEmpty());
		return table[head];
	}
	
	public Item tail() {
		assert(!isEmpty());
		return table[tail];
	}
	
	public void enQueue(Item x) {
		assert(((tail + 1) % table.length) != head);
		table[tail] = x;
		tail = (tail + 1) % table.length;
	}
	
	public Item deQueue() {
		assert(!isEmpty());
		Item x = table[head];
		head = (head + 1) % table.length;
		return x;
	}
	
	public boolean isEmpty() {
		return (head == tail);
	}

}
