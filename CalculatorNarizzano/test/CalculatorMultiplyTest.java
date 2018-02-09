import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class CalculatorMultiplyTest {
	private static Calculator calculator = null;
	@Parameter(0) public int var1;
	@Parameter(1) public int var2;
	@Parameter(2) public int expectedResult;
	
	@BeforeClass
	public static void setUp(){
		// 1 - Setup
		calculator = new Calculator();
		System.out.println("Crea Calculator");
	}
	
	@Parameters
	public static Collection<Integer[]> data(){
		return Arrays.asList(new Integer[][] {
			{1,2,2},
			{-1,2,-2},
			{1,-2,-2},
			{-1,-2,2},
		});
	}
		
	@Test
	public void testMultiply() {
		assertNotNull("Oggetto Calculator non istanziato",calculator);
		int result = calculator.multiply(this.var1, this.var2);
		// 5 - Check the result
		assertEquals("Moltiplicazione non corretta",expectedResult,result);
	}
}
