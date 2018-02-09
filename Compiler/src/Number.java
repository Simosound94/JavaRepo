/**
 * Number:
 * 	eredita da Expression
 * 	rappresenta il token "numero" presente in un'espressione
 */
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Number extends Expression {
	long value;

	public Number() {
		value = 0;
	}
	
	public Number(String text) throws Error {
		Pattern path = Pattern.compile("[0-9]+|(-[0-9]+)");
		Matcher match = path.matcher(text);
		if(!match.matches())
			throw new Error(text+" isn't a valid variable name.");
		
		value = Long.parseLong(text);
	}

	public long getValue(Context syTable) {
		return value;
	}

}
