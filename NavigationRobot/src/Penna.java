/**
 * @autor Simone Merello
 * 
 * 	PENNA:
 * 	corrisponde al vecchio esercizio "PrintTurtle" lievemente modificato
 *  non modifica il funzionamento del robot.
 *  Incluso per completezza, siccome è stato usato per tenere traccia del percorso effettuato
 *  nel main di prova (classe Robot)
 * 
 */

import edu.princeton.cs.algs4.StdDraw;

public class Penna {

	private double x,y,angolo;
	private boolean scrivi;
	private double step;
	
	public Penna(int dim){
		x=0.1;
		y=0.9;
		angolo= 90;
		scrivi = false;
		step = 1 / (dim * 1.2);
	}
	public void Rotate(double gradi, char dir){
		if(dir == 'l') angolo +=gradi;
		else if(dir == 'r') angolo -=gradi;
		angolo = angolo % 360;
	}
	
	public void setDegrees(double gradi){
		angolo = gradi% 360;
	}
	
	public void PenUp(){
		scrivi=false;
	}
	public void PenDown(){
		scrivi=true;
	}
	
	public void Move(int n, boolean dir){
		double l;
		if(dir) l= step*n;
		else l=-step*n;
		double rad = angolo/360*2*Math.PI;
		double x1 = l*Math.cos(rad) + x;
		double y1 = l*Math.sin(rad) + y;
		if(scrivi) StdDraw.line(x, y, x1, y1);
		x=x1;
		y=y1;
	}
	
	public void clear(){
		StdDraw.clear();
	}

}
