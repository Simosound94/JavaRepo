import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runners.Parameterized;
import org.junit.runner.RunWith;
import org.junit.BeforeClass;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class CalculatorSumTest {
	public int var1;
	public int var2;
	public int expectedResult;
	
	private static Calculator calculator = null;
	
	public CalculatorSumTest(int var1,int var2, int expectedResult){
		this.var1 = var1;
		this.var2 = var2;
		this.expectedResult = expectedResult;
	}
	
	@Parameters
	public static Collection<Integer[]> data(){
		return Arrays.asList(new Integer[][] {
			{1,2,3},
			{-1,2,1},
			{1,-2,-1},
			{-1,-2,-3},
		});
	}

	
	@BeforeClass
	public static void setUp(){
		// 1 - Setup
		calculator = new Calculator();
		System.out.println("Crea Calculator");
	}
		
	@Test
	public void testSum() {
		// 4 - Running Test
		int result = calculator.sum(this.var1, this.var2);
		// 5 - Check the result
		assertEquals(this.expectedResult,result);
	}
	
	
	
}
