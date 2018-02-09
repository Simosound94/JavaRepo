public class Processo implements Comparable<Processo> {

	public int id;
	public int prior, time;
	
	public Processo(int id, int pr, int time){
		if(id<=0 || time <= 0) throw new IllegalArgumentException();
		this.id = id;
		this.prior = pr;
		this.time = time;
	}
	
	public boolean ended(){
		return time==0;
	}

	
	public String execProc(int maxTime){
		if(time>maxTime)	time -=maxTime;
		else				time = 0;
		prior--;
		return id+" "+(prior+1)+" "+prior+" "+time;
	}
	
	public int compareTo(Processo arg0) {
		if(this.prior > arg0.prior) return 1;
		if(this.prior < arg0.prior) return -1;
		if(this.id < arg0.id) return 1;
		return -1;
	}
	
	
}
