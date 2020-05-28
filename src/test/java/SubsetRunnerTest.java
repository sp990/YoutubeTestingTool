import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SubsetRunnerTest {
	
    @Test
    public void runQuery_IsSubsetOf_AssertTrue() {
    	SubsetRunner subsetTest = new SubsetRunner();
    	Boolean isSubset = subsetTest.isSubsetBinSubsetA();
    	assertTrue(isSubset, () -> "Set B should be a strict subset of Set A");
    }
}





