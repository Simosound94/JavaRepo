package driverExerciseProf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Driver {
	
	// Struttura dati per le liste dei task da eseguire
	// key = Parametro di interrupt per il quale i task devono essere eseguiti
	// value = lista dei task in coda per quel parametro
	private Map<Integer, List<Task>> tasks;
	
	//Struttura dati degli interrupt
	private Map<Integer, List<Interrupt>> interruptList;
	
	
	public Driver(){
		//Istanzio ed esegui 16 TaskExcutor, uno su ogni interrupt
		this.tasks = new HashMap<Integer, List<Task>>();
		this.interruptList = new HashMap<Integer, List<Interrupt>>();
		
		for(int i = 0 ; i<16; i++){
			List<Task> tList = new ArrayList<Task>();
			this.tasks.put(i, tList);
			List<Interrupt> iList = new ArrayList<Interrupt>();
			this.interruptList.put(i, iList);
			TaskExecutor te = new TaskExecutor(iList, tList);
			Thread t_te = new Thread(te);
			t_te.start();
		}
	}
	
	/**
	 * 
	 * @param t
	 * @param param
	 * 
	 * Registra il Task t per ricevere gli interrupt con parametro = param
	 */
	public void registerTask(Task t, Integer param){
		//Andiamo a toccare le struttre dati del task, che sono risorse condivise tra i thread
		// Task ed Interrupt, è meglio sincronizzarsi
		synchronized(this){
			List tlist = this.tasks.get(param);
			tlist.add(t);
		}
	}
	
	
	/**
	 * 
	 * @param i
	 * Invia l'interrupt i
	 */
	public void sendInterrupt(Interrupt i){
		
	}
	
	
}
