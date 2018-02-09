import java.util.ArrayList;
import java.util.LinkedList;

public class Ascensore implements Runnable {
	
	public static final int NUMPIANI = 10;
	
	//Persone che devono SCENDERE al piano indicato dall'indice arrayList
	private ArrayList<LinkedList<Persona>> peopleInside;
	
	//Persone che devono SALIRE al piano indicato dall'ArrayList
	private ArrayList<LinkedList<Persona>> peopleRequests;
	private int nome;
	private int pianoCorrente;
	//Verso dove va l'ascensore +1 : verso l'alto,  -1: verso il basso
	private int direction;
	
	
	

	public Ascensore(ArrayList<LinkedList<Persona>> peopleRequests, int nome) {
		super();
		this.peopleRequests = peopleRequests;
		this.nome = nome;
		this.direction = 1;
		this.pianoCorrente = (int)(Math.random()*(Ascensore.NUMPIANI-1));
		this.peopleInside = new ArrayList<LinkedList<Persona>>(NUMPIANI);
		for(int i = 0; i<NUMPIANI;i++){
			peopleInside.add(new LinkedList<Persona>());
		}
	}
	
	
	public void request(Persona p){
		try{
			synchronized(peopleRequests){
				LinkedList<Persona> req = peopleRequests.get(p.pianoCorrente);
				req.add(p);
			}
			synchronized(p){
				System.out.println("Persona #"+p.getNome()+" at floor "+p.pianoCorrente+" request elevator for floor: "+p.getPianoRichiesto());
				p.wait();
			}
		}catch(Exception e){e.printStackTrace();}
	}
	


	@Override
	public void run() {
		try{
			while(true){
				Thread.sleep(10000);
				pianoCorrente += direction;
				System.out.println("Elevator #"+nome+" at floor: "+pianoCorrente);

				//Fai uscire la gente di quel piano
				LinkedList<Persona> inside = peopleInside.get(pianoCorrente);
				if(inside.isEmpty()){
					System.out.println("Elevator #"+nome+" nobody wants to exit at floor: "+pianoCorrente);
				}
				for(int i = 0; i<inside.size(); i++){
					Persona p = inside.removeFirst();
					synchronized(p){
						p.pianoCorrente = pianoCorrente;
						System.out.println("Persona #"+p.getNome()+" exied elevator #"+nome+" on floor: "+p.pianoCorrente);
						p.notify();
					}
				}
				//Fai salire la gente di quelpiano
				//peopleRequests è condivisa con gli altri ascensori
				LinkedList<Persona> outside = null;
				synchronized(peopleRequests){
					outside = peopleRequests.get(pianoCorrente);
				}
				synchronized(outside){
					if(outside.isEmpty()){
						System.out.println("Elevator #"+nome+" nobody wants to enter at floor: "+pianoCorrente);
					}
					for(int i = 0; i<outside.size(); i++){
						Persona p = outside.removeFirst();
						System.out.println("Persona #"+p.getNome()+" enter elevator #"+nome+" at floot: "+pianoCorrente+" for floor: "+p.getPianoRichiesto());
						inside = peopleInside.get(p.getPianoRichiesto());
						inside.add(p);
					}
				}
				
				//Politica scelta piano
				if(pianoCorrente >= NUMPIANI-1){
					direction = -1;
				}
				else if(pianoCorrente == 0){
					direction = 1;
				}
				else{
					//Conto persone/richieste nella direzione corrente, se 
					//non c'è nessuno cambio direzione
					int rangemin = direction==1 ? pianoCorrente : 0;
					int rangemax = direction==1 ? NUMPIANI : pianoCorrente;
					int count = 0;
					synchronized(peopleRequests){
						for(int i = rangemin; i<rangemax; i++){
							count += peopleInside.get(i).size();
							count += peopleRequests.get(i).size();
							if(count > 0) break;
						}
					}
					//Se non c'è nulla da fare nella direzione corrente, cambiala
					if(count == 0){
						direction = direction==1 ? -1 : 1;
					}					
				}
			}
		}catch(Exception e){e.printStackTrace();
		}

	}

}
