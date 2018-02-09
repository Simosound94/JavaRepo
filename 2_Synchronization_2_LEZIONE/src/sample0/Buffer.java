package sample0;

// a simple fifo buffer...
public interface Buffer {
	public void add(String s);
	public String retrieve();
}
