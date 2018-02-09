package my.prodcons.deadlock;

import java.util.ArrayList;
import java.util.List;

public class BoundedBuffer {
	private List<String> buffer;
	private int max;
	private Object buffPieno, buffVuoto;

	public BoundedBuffer(int max) {
		this.buffer = new ArrayList<String>();
		this.max = max;
		this.buffPieno = new Object();
		this.buffVuoto = new Object();
	}
	
	/*
	 * PROBLEMA:
	 * 	ci sono due synchronized annidate, 
	 * 	quando faccio la put, prendo il lock su BoundedBuffer, 
	 * 	poi prendo quello su buffPieno e lo rilascio
	 * 	i processi consumatori però non potranno più svegliarmi 
	 * 	perchè non potranno prendere il lock su BoundedBuffer
	 * 
	 * (stessa cosa per la get)
	 */

	public synchronized void put1(String s, String tname) {
		while(this.buffer.size() == this.max) {
			try {
				System.out.println("Buffer is full. Thread " + tname + " must wait...");
				synchronized(buffPieno){
					buffPieno.wait();
				}
			} catch(InterruptedException e) {

			}
		}
		buffer.add(s);
		System.out.println(s+" produced");
		synchronized(buffVuoto){
			buffVuoto.notify();
		}
		/*
		 * In questo caso non serve più la notify all
		 * su buffVuoto sono in attesa solo i consumatori
		 * mentre su buffPieno sono in attesa solo i produttori
		 * Ogni volta che produco, mi basta svegliare un processo consumatore
		 * 
		 */
	}

	public synchronized String get1(String tname) {
		String s = null;
		while(this.buffer.size() == 0) {
			try {
				System.out.println("Buffer is empty. Thread " + tname + " must wait...");
				synchronized(buffVuoto){
					buffVuoto.wait();
				};
			} catch (InterruptedException e) {
				
			}
		}
		s = this.buffer.remove(0);
		System.out.println(s+" consumed");
		synchronized(buffPieno){
			buffPieno.notify();
		}
		return s;
	}
	

	/*
	 * SOLUZIONE:
	 * Non mettiamo synchronized annidate, 
	 * il lock non è su BoundedBuffer ma su buffer
	 */
	
	
	public void put2(String s, String tname) {
		while(this.buffer.size() == this.max) {
			try {
				System.out.println("Buffer is full. Thread " + tname + " must wait...");
				synchronized(buffPieno){
					buffPieno.wait();
				}
			} catch(InterruptedException e) {

			}
		}
		synchronized(buffer){
			buffer.add(s);
		}
		System.out.println(s+" produced");
		synchronized(buffVuoto){
			buffVuoto.notify();
		}
		/*
		 * In questo caso non serve più la notify all
		 * su buffVuoto sono in attesa solo i consumatori
		 * mentre su buffPieno sono in attesa solo i produttori
		 * Ogni volta che produco, mi basta svegliare un processo consumatore
		 * 
		 */
	}

	public synchronized String get2(String tname) {
		String s = null;
		while(this.buffer.size() == 0) {
			try {
				System.out.println("Buffer is empty. Thread " + tname + " must wait...");
				synchronized(buffVuoto){
					buffVuoto.wait();
				};
			} catch (InterruptedException e) {
				
			}
		}
		s = this.buffer.remove(0);
		System.out.println(s+" consumed");
		synchronized(buffPieno){
			buffPieno.notify();
		}
		return s;
	}
}
