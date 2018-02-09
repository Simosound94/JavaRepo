package joincalc;

public class Calculator implements Runnable {
	private int maxN;
	private int result;
	
	public Calculator(int maxN) {
		this.maxN = maxN;
	}
	
	public void run() {
		result = 0;
		for(int i = 0; i < maxN; i++) {
			result = result + i;
		}
		// Thread subt ....
		// subt.join()
	}
	
	public int getResult() {
		return this.result;
	}
}
