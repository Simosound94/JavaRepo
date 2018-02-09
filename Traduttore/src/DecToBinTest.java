import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class DecToBinTest {
	private static Traduttore tr = null;


	@Before
	public void setUp() throws Exception {
		tr = new Traduttore();
	}

	@Test
	public void test() {
		int n = 11;
		// SET EXPECTED RESULT
		int expectedResult = 3;
		// RUNNING TEST
		int result = tr.decToBin(n);
		//  CHECK RESULT
		assertEquals(expectedResult, result);
		
	}

}
