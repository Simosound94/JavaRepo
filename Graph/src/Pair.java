// Una coppia in cui il primo elemento è 'Comparable'
// e definisce l'ordine totale sulle coppie
public class Pair <T1 extends Comparable< T1 >, T2> implements Comparable< Pair<T1, T2> >{
	
	public T1 first;
	public T2 second;
	
	public Pair(T1 first, T2 second) {
		this.first = first;
		this.second = second;
	}
	
	public int compareTo(Pair <T1, T2> other) {
		return this.first.compareTo(other.first);
	}

}
