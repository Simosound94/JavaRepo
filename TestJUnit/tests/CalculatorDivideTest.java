import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CalculatorDivideTest {

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
	public void positiviTest(){
		int v1 = 4;
		int v2 = 2;
		
		double expectedResult = 2;
		double result = c.divide(v1, v2);
		assertEquals(expectedResult, result, 0);
		
	}
	
	@Test
	public void negativiTest(){
		int v1 = -4;
		int v2 = 2;
		
		double expectedResult = -2;
		double result = c.divide(v1, v2);
		assertEquals(expectedResult, result, 0);
		
	}

}
