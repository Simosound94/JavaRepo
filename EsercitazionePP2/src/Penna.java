import java.io.FileReader;
import java.util.Scanner;

import edu.princeton.cs.algs4.StdDraw;

public class Penna {

	private double x,y,angolo;
	private boolean scrivi;
	
	public Penna(){
		x=0.5;
		y=0.5;
		angolo= 90;
		scrivi = false;
	}
	public void Rotate(double gradi, char dir){
		if(dir == 'l') angolo +=gradi;
		else if(dir == 'r') angolo -=gradi;
		angolo = angolo % 360;
	}
	
	public void PenUp(){
		scrivi=false;
	}
	public void PenDown(){
		scrivi=true;
	}
	
	public void Move(int n, boolean dir){
		//dir=1 avanti
		double l;
		if(dir) l= 0.05*n;
		else l=-0.05*n;
		double rad = angolo/360*2*Math.PI;
		double x1 = l*Math.cos(rad) + x;
		double y1 = l*Math.sin(rad) + y;
		if(scrivi) StdDraw.line(x, y, x1, y1);
		x=x1;
		y=y1;
	}
	
	
	public static void main(String[] args)  throws Exception{
		if(args.length < 1){
			System.out.println("Please, insert file source.");
			System.exit(0);
		}
		Penna turtle = new Penna();
		FileReader file = new FileReader(args[0]);
		Scanner input = new Scanner(file);
		String statement;
		int var;
		while(input.hasNextLine()){
			statement = input.next();
			if(statement.equalsIgnoreCase("PENUP")) turtle.PenUp();
			else if(statement.equalsIgnoreCase("PENDOWN")) turtle.PenDown();
			else if(statement.equalsIgnoreCase("LEFT")){
				var = input.nextInt();
				turtle.Rotate((double)var, 'l');
			}
			else if(statement.equalsIgnoreCase("RIGHT")){
				var = input.nextInt();
				turtle.Rotate((double)var, 'r');
			}
			else if(statement.equalsIgnoreCase("FORWARD")){
				var = input.nextInt();
				turtle.Move(var, true);
			}
			else if(statement.equalsIgnoreCase("BACK")){
				var = input.nextInt();
				turtle.Move(var, false);
			}
			else System.out.println("Command not found: "+statement);
			
			}
		input.close();
		}

}
