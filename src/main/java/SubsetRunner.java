import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;


public class SubsetRunner implements MetamorphicTestRunner {
	
	int testPassed = 0;
	int testFailed = 0;
	int sourceTestCount = 0;
	private boolean result = false;
	protected final ArrayList<HashMap<String, String>> sourceTests = readSourceTests();
	
	public SubsetRunner() {}
	
	/* 
	 * 		###  Subset Metamorphic Relation Testing of the YouTube Data API  ###
	 * 		
	 * 		This test class demonstrates the testing of a subset relation using YouTube's Data API.
	 * 		Initially, two source tests are read into the class on instantiation.
	 * 		A source test (Map<String, String>) represents a query that can be made to the YouTube Data API, 
	 * 		by calling YouTubeQuery.runQuery(String queryType, Map<String, String> queryParams).
	 * 		
	 * 		To test the subset relation, three follow-up test cases are created, 
	 *      and are sequentially called as part of the compareSubset(JSONArray sourceTest, JSONArray followUpTest) 
	 *      function to test whether the followUpTest is a subset of the sourceTest.
	 * 		
	 * 		Results of whether a test passes or failed are printed to the console, with a summary of test statistics.
	 * 
	 * 		The follow-up test cases include:
	 * 
	 * 		1.  YouTube's Data API "search" Endpoint "category", changed to a random category generated 
	 * 			by YouTubeRandomizer.getRandomDictioaryWord()
	 * 
	 * 		2.  YouTube's Data API "search" Endpoint "locationRadius", changed to a random radius 
	 * 			that is less than the current locationRadius
	 * 
	 * 		3.  YouTube's Data API "search" Endpoint "publishedAfter" and "publishedBefore", changed to random 
	 * 			dates that are within the original date range 
	 * 
	 * 		Constraints:
	 * 
	 * 		MaxResults returned as part of a YouTube Query is 50.  At times the subset test fails due to this constraint as an entire set of data is not available for testing.
	 * 		Efforts have been made to customise source tests and restrict randomly generated data to circumvent this issue although at times the issue still exists and remains a constraint of the project.
	 * 
	 * */
	
	@Override
    public void runTest() {
		
		System.out.println("---Starting Subset Test---\n");
		try {
			for (int i = 0; i < sourceTests.size(); i++) {
				System.out.println("Source Test: " + (sourceTestCount + 1) + "\n");

				// Follow Up Test - Category
				System.out.println("Source Test vs Follow-up Test (category changed):\t\t");
				if (compareSubsets(YoutubeQuery.runQuery("search", sourceTests.get(sourceTestCount)).getJSONArray("items"), YoutubeQuery.runQuery("search", createFollowUpTest_Category()).getJSONArray("items"))) {
					testPassed++;
					System.out.println("passed");
				} else {
					testFailed++;
					System.out.println("failed \t | \t The follow-up subset is not in the original set");
				}

				// Follow Up Test - Date Range
				System.out.println("Source Test vs Follow-up Test (date range changed):\t\t");
				if (compareSubsets(YoutubeQuery.runQuery("search", sourceTests.get(sourceTestCount)).getJSONArray("items"), YoutubeQuery.runQuery("search", createFollowUpTest_DateRange()).getJSONArray("items"))) {
					testPassed++;
					System.out.println("passed");
				} else {
					testFailed++;
					System.out.println("failed \t | \t The follow-up subset is not in the original set");
				}

				// Follow Up Test - Radius
				System.out.println("Source Test vs Follow-up Test (radius changed):\t\t");
				if (compareSubsets(YoutubeQuery.runQuery("search", sourceTests.get(sourceTestCount)).getJSONArray("items"), YoutubeQuery.runQuery("search", createFollowUpTest_Radius()).getJSONArray("items"))) {
					testPassed++;
					System.out.println("passed");
				} else {
					testFailed++;
					System.out.println("failed \t | \t The follow-up subset is not in the original set");
				}
				sourceTestCount++;
			}
		}
		catch (IOException e){
			e.printStackTrace();
		}

		//Setting the value of result
		if(testPassed > testFailed)
			result = true;
	}

	
	// Compare results of two sets, returns true if the subset is contained within the set
	public Boolean compareSubsets(JSONArray set, JSONArray subset) {
		if (set.toList().containsAll(subset.toList())) return true;
		else return false;
	}
	
	
	// Create a new YT Query with a random category, returns Map<String, String>
	public Map<String, String> createFollowUpTest_Category() {
		Map<String, String> followUpTest = new HashMap<String, String>(sourceTests.get(sourceTestCount));
		followUpTest.put("q", YoutubeRandomizer.getRandomDictionaryWord());
		return followUpTest;
	}

	
	// Create a new YT Query with a random radius that is less than original radius, returns Map<String, String>
	public Map<String, String> createFollowUpTest_Radius() {
		Map<String, String> followUpTest = new HashMap<String, String>(sourceTests.get(sourceTestCount));
		followUpTest.put("locationRadius", createRandomRadius(followUpTest.get("locationRadius")));
		return followUpTest;
	}
	

	// Generate random locationRadius, returns String  
	public String createRandomRadius(String currentRadius) {
		String temp = "";
		int newRadius; 	

		for (char c : currentRadius.toCharArray()) {
			if(c == 'm') break;
			temp += c;
		}

		newRadius = new Random().nextInt(Integer.parseInt(temp))+1;
		return String.valueOf(newRadius)+"mi";
	}
	
	
	// Create a new YT Query with a random date range that is less than original date range, returns Map<String, String>
	public Map<String, String> createFollowUpTest_DateRange() {
		Map<String, String> followUpTest = new HashMap<String, String>(sourceTests.get(sourceTestCount));
		String time = ":00Z";
		LocalDateTime afterDate = convertStringToDate(followUpTest.get("publishedAfter").substring(0, 16));
		LocalDateTime beforeDate = convertStringToDate(followUpTest.get("publishedBefore").substring(0, 16));
		
		do {
			afterDate.plusDays(new Random().nextInt(365)+1);
			beforeDate.minusDays(new Random().nextInt(365)+1);
		}
		while(afterDate.equals(beforeDate) && beforeDate.isBefore(afterDate));
		
		followUpTest.put("publishedAfter", afterDate.toString() + time);
		followUpTest.put("publishedBefore", beforeDate.toString() + time);

		return followUpTest;
	}
	

	// Convert String Date to LocalDate
	public LocalDateTime convertStringToDate(String dateTime) {
		return LocalDateTime.parse(dateTime.substring(0, 16));
	}
	
	
	// Read source tests from file, returns an ArrayList of Map<String, String> / YT Query
	public ArrayList<HashMap<String, String>> readSourceTests() {
		ArrayList<HashMap<String, String>> sourceTests = new ArrayList<>();
		File file = new File("resources/subsetSourceTests.json");
		try {
			JSONTokener tokener = new JSONTokener(new FileInputStream(file));
			JSONArray jsonArray = new JSONArray(tokener);

			for (int i = 0; i < jsonArray.length(); i++) {
				HashMap<String, String> sourceTest = new HashMap<>();
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				Iterator<String> keysIterator = jsonObject.keys();

				while (keysIterator.hasNext()) {
					String keyName = (String) keysIterator.next();
					sourceTest.put(keyName, jsonObject.getString(keyName));
				}
				sourceTests.add(sourceTest);
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return sourceTests;
	}

	public boolean getResult() {
		return result;
	}
}
