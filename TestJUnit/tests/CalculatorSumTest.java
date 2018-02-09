import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Before;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.BeforeClass;


@RunWith(Parameterized.class)
public class CalculatorSumTest {
	public int var1;
	public int var2;
	public int expectedResult;
	
	
	private static Calculator c = null;
	

	public CalculatorSumTest(int var1, int var2, int expectedResult){
		this.var1 = var1;
		this.var2 = var2;
		this.expectedResult = expectedResult;
	}
	
	/*
	 * Funzione che fornisce gli input
	 */
	@Parameters
	public static Collection<int[]> data(){
		return Arrays.asList(new int[][]{
			//Ogni riga è un test
			// var1, var2, expected result
			{3,3,6},
			{-7,-2,-9}
			
		});
	}
	
	
	
	@BeforeClass
	public static void setUp(){
		c = new Calculator();
	}
	
	@Before
	public void stampa(){
		System.out.println("Nuovo test");
	}

	@Test
	public void positiviTest() {
		/*
		 * Ora che abbiamo i test automatici non serve
		// SET INPUT TEST
		int v1 = 3;
		int v2 = 3;
		// SET EXPECTED RESULT
		int expectedResult = 5;
		
		*/
		// RUNNING TEST
		int result = c.sum(this.var1, this.var2);
		//  CHECK RESULT
		assertEquals("La somma è errata",this.expectedResult, result);
		
	}
	
	/*
	 * Nulla mi vieta di avere più test in una sola classe
	 * 
	 
	@Test
	public void negativiTest() {
		// SET INPUT TEST
		int v1 = -3;
		int v2 = -7;
		// SET EXPECTED RESULT
		int expectedResult = -10;
		// RUNNING TEST
		int result = c.sum(v1, v2);
		//  CHECK RESULT
		assertEquals("La somma è errata",expectedResult, result);
	}
	*/


}
