import java.util.ArrayList;

import edu.princeton.cs.algs4.StdRandom;

public class CodaMarkoviana {
	
	private int server;
	private int bufferSize;				/* 0 if is infinite*/
	private int client;
	private float faultRate;
	private float repairRate;
	private boolean fail;
	private boolean blocked;
	private ArrayList<Flow> out;
	private ArrayList<Flow> in;
	private float serverRate;
	
	
	/////////////////////////////////////////
	//
	// --------------- INTERFACCIA
	//
	/////////////////////////////////////////
	
	public CodaMarkoviana(int server, int bufferSize, float serverRate, float faultRate, float repairRate){
		this.server = server;
		this.bufferSize = bufferSize;
		this.serverRate = serverRate;
		this.faultRate = faultRate;
		this.repairRate = repairRate;
		in = new ArrayList<Flow>();
		out = new ArrayList<Flow>();
		client = 0;
		blocked = fail = false;
	}
	
	public void addFlowIn(Flow toAdd){
		in.add(toAdd);
	}
	
	public void addFlowOut(Flow toAdd){
		out.add(toAdd);
	}
	
	public boolean addClient(){
		
		//aggiungere possibilità blocco
		if(client < bufferSize){
			client++;
			return true;
		}
		blocked = true;
		return false;
	}
	
	public boolean serve(){
		Flow target = chooseFlow();
		if(target != null){
			client--;
			target.update();
			return true;
		}
		else{
		blocked = true;
		return false;
		}
	}
	
	public void setFail(){
		fail = true;
	}
	
	public void repair(){
		fail = false;
		
	}
	
	public boolean isBlocked(){
		return blocked;
	}
	
	
	
	//////////////////////////////////////
	//
	// METODI INTERNI
	//
	/////////////////////////////////////
	
	private Flow chooseFlow(){
		float[] areas = new float[out.size()];
		boolean[] checked = new boolean[areas.length];	
		areas[0] = out.get(0).percentage;
		for (int i = 1; i< areas.length ; i++)
			areas[i] = areas[i-1] + out.get(i).percentage;
		
		float choose;
		int controlled = 0;
		while(controlled < checked.length){
			choose = (float) StdRandom.uniform();
			int i = 0;
			while(choose < areas[i])	i++;
			i--;
			if(checked[i] == false && !out.get(i).in.isBlocked()) 	return out.get(i);
			else{
				checked[i] = true;
				controlled++;
			}
		}
		return null;
		
	}
	
}
