import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CalculatorDivideTest.class, CalculatorMultiplyTest.class, CalculatorSubTest.class,
		CalculatorSumTest2.class })
public class AllTests {

}
