import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CalculatorSubTest {
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
	public void subTest(){
		// SET INPUT TEST

		int v1 = 3;
		int v2 = 2;
		// SET EXPECTED RESULT
		int expectedResult = 1;
		// RUNNING TEST
		int result = c.sub(v1, v2);
		//  CHECK RESULT
		assertEquals(expectedResult, result);
		
	}
}
