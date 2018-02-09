
public class ContoBancario {

	public double conto;
	public int id;

	public ContoBancario(int id){
		conto = 0;
		this.id = id;
	}
	
	
	public synchronized boolean preleva(double amount){
		if(this.conto >= amount){
			System.out.println("Withdraw Account: "+id+" of: "+amount);
			this.conto -= amount;
			return true;
		}
		System.out.println("No withdraw Account: "+id+" of: "+amount);
		return false;
	}
	
	
	public synchronized void deposita(double amount){
		System.out.println("Deposit Account: "+id+" of: "+amount);
		this.conto += amount;
	}
	


}
