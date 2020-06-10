
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

public class SubsetRunnerTest {
    private final SubsetRunner subsetTest = new SubsetRunner();
	
    public SubsetRunnerTest() throws FileNotFoundException {}
    
    
    @Test
    public void ReadFromFileTest_ReturnValue_AssertNotNull() throws FileNotFoundException {
    	assertNotNull(subsetTest.readSourceTests(), "Source tests failed to read from file");
    }
    

    @Test
    public void CreateFollowUpTest_Category_CategoryIsDifferent_AssertAll() {
    	assertAll(  
    		() -> assertNotNull(subsetTest.createFollowUpTest_Category(), "Follow-up Test creation failed"), 
    		() -> assertNotEquals(subsetTest.createFollowUpTest_Category().get("q")
    				.equals(subsetTest.sourceTests.get(subsetTest.sourceTestCount).get("q")), 
    				"Random category equals original category, must be different")
    	);
    }

    
    @Test
    public void GenerateRandomRadiusTest_ReturnValue_AssertNotNull() {
    	assertNotNull(subsetTest.createRandomRadius(subsetTest.sourceTests.get(subsetTest.sourceTestCount)
    			.get("locationRadius")), "Random radius creation failed, returned null");
    }
    
    
    @Test
    public void CreateFollowUpTest_Radius_RadiusIsDifferent_AssertAll() {
    	assertAll(  
   			() -> assertNotNull(subsetTest.createFollowUpTest_Radius(), "Follow-up Test creation failed"), 
    		() -> assertNotEquals(subsetTest.createFollowUpTest_Radius().get("locationRadius")
    				.equals(subsetTest.sourceTests.get(subsetTest.sourceTestCount).get("locationRadius")), 
    				"New radius value equals original value, values must be different")
    	);
    }
    
    
    @Test
    public void ConvertStringToDateTest_ReturnValue_AssertNotNull() {
    	assertNotNull(subsetTest.convertStringToDate(subsetTest.sourceTests.get(subsetTest.sourceTestCount)
    			.get("publishedBefore")), "String to Date failed conversion, returned null");
    }

    
    @Test
    public void CreateFollowUpTest_DateRange_DateRangeIsDifferent_AssertAll() {
    	assertAll(  
   			() -> assertNotNull(subsetTest.createFollowUpTest_DateRange(), "Follow-up Test creation failed"), 
   			
    		() -> assertNotEquals(subsetTest.createFollowUpTest_DateRange().get("publishedAfter")
    				.equals(subsetTest.sourceTests.get(subsetTest.sourceTestCount).get("publishedAfter")), 
    				"New publishedAfter value equals original value, values must be different"),
    		
    		() -> assertNotEquals(subsetTest.createFollowUpTest_DateRange().get("publishedBefore")
    				.equals(subsetTest.sourceTests.get(subsetTest.sourceTestCount).get("publishedBefore")), 
    				"New publishedBefore value equals original value, values must be different")
    	);
    }    

    
    @Test
    public void CompareSubsetResults_OriginalContainsSubset_AssertTrue() throws IOException {
    	assertTrue(subsetTest.compareSubsets(YoutubeQuery.runQuery("search", subsetTest.sourceTests
    			.get(subsetTest.sourceTestCount)).getJSONArray("items"), 
    			YoutubeQuery.runQuery("search", subsetTest.sourceTests
    			.get(subsetTest.sourceTestCount)).getJSONArray("items")), "The follow-up subset is not in the original set");
    }
}



