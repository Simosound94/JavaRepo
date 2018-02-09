package driverExercise;

import java.util.LinkedList;

public class Driver {
	private LinkedList<Task>[] queues;
	
	public Driver(){
		queues = new LinkedList[15];
		for(int i = 0; i<15; i++){
			queues[i] = new LinkedList<Task>();
		}
	}
	
	public synchronized void sendInterrupt(int num){
		if(!queues[num].isEmpty()){
			Task t = queues[num].removeFirst();
			System.out.println("Interrupt #" +num+" has wake up Task #"+t.name);
			t.callBack();
		}
		else{
			System.out.println("Interrupt #" +num+" has wake up no one");

		}
	}
	
	public synchronized void register(int num, Task t){
		System.out.println("Registered task #"+t.name+" at int "+num);
		queues[num].add(t);
	}

}
