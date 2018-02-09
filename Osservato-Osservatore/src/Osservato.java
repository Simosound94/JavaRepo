
public interface Osservato {
	
	//private LinkedList<Osservatore> osservatori;
	
	public void registraOsservatore(Osservatore o);
	public void rimuoviOsservatore(Osservatore o);
	public void nuovoEvento(int e);
}
