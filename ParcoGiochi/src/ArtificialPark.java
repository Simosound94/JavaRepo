import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

public class ArtificialPark {

	private ArrayList<Persona> pers;
	private ArrayList<Giostra> gio;
	private ArrayList<Persona> timed;
	public static final long STEP_DURATION = 150;
	public static long SEED = 1234;
	public static int NPERS = 100;
	public static int NPROVE = 3;
	public static int NGIOSTRE = 9; //MAX 9
	private int totTime;
	
	
	public ArtificialPark(){
		pers = new ArrayList<Persona>();
		gio = new ArrayList<Giostra>();
		timed = new ArrayList<Persona>();
		
	}
	
	public void simulation(){
		int peoplePark=NPERS;
		boolean modify=false;
		//CREO GIOSTRE
		Giostra temp=null;
		for(int i=0;i<NGIOSTRE;i++){
			temp= new Giostra(SEED);
			temp.draw(i);
			gio.add(temp);
		}
		//CREO PERSONE
		Persona ptemp;
		for(int i=0; i<NPERS; i++){
			ptemp= new Persona(SEED);
			pers.add(ptemp);
		}
		//--------------simulazione
		totTime=0;
		//StdRandom.setSeed(SEED);
		while(true){
			Persona s;
			for(int k=0; k<pers.size();k++){
				s=pers.get(k);
				if(s.inGiostra){
					modify = s.haveFun();
				}
				else{
					int x;
					for(int j=0;j<NPROVE-1;j++){ //prova Nvolte a trovare una giostra
						x=StdRandom.uniform(NGIOSTRE);
						temp=gio.get(x);
						if(!temp.full()) break;
					}
					if(!temp.full()){
						modify=true;
						s.setGame(temp);
						s.haveFun();	
					}
					else{
						s.increaseWaitTime();
					}
				}
				s.decreaseTime();
				if(s.getTime()==0) {
					modify=true;
					timed.add(s);
					if(s.inGiostra) s.leaveGame();
					pers.remove(s);
					peoplePark--;
					k--;
				}
			}
			//AGGIORNAMENTO
			if(modify){
				StdDraw.clear();
				int j=0;
				int tot=0;
				for(Giostra g : gio){
					g.draw(j);
					tot+=g.getPeopleInside();
					StdDraw.text(g.getX(), g.getY(),g.getPInside());
					j++;
				}
				System.out.println("Nel parco: "+peoplePark+ " sulle giostre: "+tot);
				//Fuori
				String ris="Persone in attesa: "+(peoplePark-tot)+" nel parco: "+peoplePark;
				StdDraw.text(0.30, 0.95, ris);
			}
			modify=false;
			try {
				// Ferma l'esecuzione per STEP_DURATION millisecondi
				Thread.sleep(STEP_DURATION);
			} catch (InterruptedException e ) {
				// skip
			}
			if(peoplePark==0) break;
			totTime++;
		}
		System.out.println("End.");
	}
	
	
	public void statistics(FileWriter out) throws Exception{
		PrintWriter pr =new PrintWriter(out);
		pr.println("\n Number of person: "+NPERS);
		pr.println(" Number of game: "+NGIOSTRE);
		pr.println(" Number of try each time: "+NPROVE);
		pr.println(" Total Simulation Time: "+totTime);
		pr.println("\n ------GIOSTRE------- \n");
		int k=1;
		for(Giostra g : gio){
			pr.println("\n Giostra "+k);
			pr.println("Divertimento medio: "+g.getAvgFun());
			pr.println("Durata:"+g.getDuration());
			pr.println("Numero posti: "+g.getMaxPeople());
			pr.println("Numero clienti: "+g.getNumRide());
			k++;
		}
		
		pr.println("\n \n \n ------PERSONE------- \n");
		k=1;
		int tot;
		for(Persona p : timed){
			pr.println("\n Persona "+k);
			pr.println("Tempo di gioco: "+p.getGameTime());
			pr.println("Tempo di attesa: "+p.getWaitTime());
			tot=p.getGameTime()+p.getWaitTime();
			pr.println("Tempo totale: "+tot);
			pr.println("Divertimento: "+p.getFun());
			pr.println("Divertimento/TempoTot : "+p.getFun()/tot);
			k++;
		}
		pr.close();
		out.close();
	}
	
	
	
	
}
