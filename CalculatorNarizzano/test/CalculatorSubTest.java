import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class CalculatorSubTest {
private static Calculator calculator = null;
	
	@BeforeClass
	public static void setUp(){
		// 1 - Setup
		calculator = new Calculator();
		System.out.println("Crea Calculator");
	}

	@Test
	public void testSub() {
		assertNotNull("Oggetto Calculator non istanziato",calculator);
		// 2 - Setting input (Test)
		int v1 = 5;
		int v2 = 1;
		// 3 - Set Expected results
		int expectedResult = 4;
		// 4 - Running Test
		int result = calculator.sub(v1, v2);
		// 5 - Check the result
		assertEquals("La sottrazione è errata",expectedResult,result);	
	}
}
