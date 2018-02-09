/*
 * Potrei utilizzarlo anche per gestire espressioni string o altro
 * è generale, poi dipende dalle reali classi che lo estendono
 * infatti restituisce un tipo <ValueType>
 * 
 * In realtà il pattern divide i figli in TerminalExpression o NonTerminal
 * ma se arriviamo ad un livello di dettaglio così elevato per noi viene un casino con i numeri
 * utilizziamo i numeri come terminal expression, le operazioni come NonTerminal
 * 
 */
public abstract class SimpleExpression <ValueType> {
	public abstract ValueType evaluate();
	public String toString() {
		return null;
	};
}
