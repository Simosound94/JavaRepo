package eserc;

public class Dipendente {
	
	
	
	public void lavora(MacchinaCaffe[] macchine){
		for(int i= 0; i<macchine.length; i++){
			System.out.println("la macchina si è accesa? "+macchine[i].accendi(true));	
		}
	}

}
