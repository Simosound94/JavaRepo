import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CalculatorMultiplyTest {
	
private static Calculator c = null;
	
	@BeforeClass
	public static void setUp(){
		c = new Calculator();
	}
	
	@Before
	public void stampa(){
		System.out.println("Nuovo test");
	}

	@Test
	public void multiplyTest() {
		int v1 = 3;
		int v2 = 3;
		
		double expectedResult = 9;
		double result = c.multiply(v1, v2);
		assertEquals("La moltiplicazione è errata",expectedResult, result, 0);
		
	}

}
