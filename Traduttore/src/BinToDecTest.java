

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class BinToDecTest {
	private static Traduttore tr = null;


	@Before
	public void setUp() throws Exception {
		tr = new Traduttore();
	}

	@Test
	public void positivo() {
		int n = 3;
		// SET EXPECTED RESULT
		String expectedResult = "11";
		// RUNNING TEST
		String result = tr.binToDec(n);
		//  CHECK RESULT
		assertEquals(expectedResult, result);
		
	}
	
	/*
	@Test
	public void negativo() {
		int n = -3;
		// SET EXPECTED RESULT
		String expectedResult = "11";
		// RUNNING TEST
		String result = tr.binToDec(n);
		//  CHECK RESULT
		assertEquals(expectedResult, result);
	}
	*/

}
