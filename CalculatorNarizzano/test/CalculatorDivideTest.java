import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class CalculatorDivideTest {
	private static Calculator calculator = null;
	@BeforeClass
	public static void setUp(){
		// 1 - Setup		calculator = new Calculator();
		System.out.println("Crea Calculator");
	}
	//@Test
	@Ignore
	public void testDividePositivi() {
		assertNotNull("Oggetto Calculator non istanziato",calculator);
		// 2 - Setting input (Test)
		int v1 = 2;
		int v2 = 4;
		// 3 - Set Expected results
		double expectedResult = 0.5;
		// 4 - Running Test
		double result = calculator.divide(v1, v2);
		// 5 - Check the result
		assertEquals("Divisione non corretta",expectedResult,result,0.0);
	}

	@Test (expected = MyOwnException.class)
	public void testDividePerZero() {
		assertNotNull("Oggetto Calculator non istanziato",calculator);
		// 2 - Setting input (Test)
		int v1 = -2;
		int v2 = 0;
		// 3 - Set Expected results
		double expectedResult = -0.5;
		// 4 - Running Test
		double result = calculator.divide(v1, v2);
		// 5 - Check the result
		//assertEquals("Divisione non corretta",expectedResult,result,0.0);
	}

}
