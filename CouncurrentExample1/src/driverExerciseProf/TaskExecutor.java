package driverExerciseProf;

import java.util.List;

/**
 * 
 * @author simone
 * 
 *	Questo Thread è in attesa sulla lista degli interrupt per uno specifico parametro
 *  di interrupt ed esegue il (primo) task per quell'interrupt
 */


public class TaskExecutor implements Runnable {
	private List<Interrupt> iList;
	private List<Task> tList;
	
	public TaskExecutor(List<Interrupt> iList, List<Task> tList) {
		super();
		this.iList = iList;
		this.tList = tList;
	}
	
	
	@Override
	public void run() {
		while(true){
			synchronized(iList){
				while(iList.size() == 0){
					//Se la mia lista degli interrupt è vuota attendo...
					try{
						iList.wait();
					}catch(Exception e){
						System.out.println("TaskExecutor Interrupted..");
					}
				}
				//interrupt nella lista...
				//recupero l'interrupt
				Interrupt i = iList.remove(0);
				synchronized(this.tList){
					if(this.tList.size()>0){
						//Se ci sono task in attesa su questo interrupt
						Task t = this.tList.remove(0);
						t.execTask(i);
					}
				}
			}
		}
	}

	

	
}
