import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

public class SubsetRunner implements MetamorphicTestRunner {
	
   public static void main(String[] args) throws IOException {
	   SubsetRunner testing = new SubsetRunner();
	   System.out.println(testing.createYouTubeQuery());
   }
	
	@Override
    public void runTest() {

    }

	public Boolean isSubsetBinSubsetA() {
		
		return null;
	}
	
	
	public JSONObject generateQueryParams() throws IOException {
		Map<String, String> params = new HashMap<>();
        params.put("part", "snippet");
        params.put("location", "-33.865143%2C151.209900"); 
        params.put("locationRadius", "100mi");
        params.put("publishedAfter", "2015-01-01T00%3A00%3A00Z"); 
        params.put("publishedBefore", "2020-01-02T00%3A00%3A00Z"); 
        params.put("q", "cat");
        params.put("type", "video");
        params.put("fields", "items.id.videoId");
        
        return YoutubeQuery.runQuery("search", params);
	}
	
	// TODO Friday - read in test data from JSON file, into JSONObject
	
	// TODO Friday - create two subsets from the JSON file, one that is correct, one that is incorrect - read them both into JSONObjects
	
	// TODO Sunday - create a hash function that hashes videoID from JSONObject and store it in look-up table
	
	// TODO Tuesday - create a function that takes the follow-up subset and looks up value of it's videoID hash value, if it does not exist isSubsetBinSubsetA function returns false, else true
	
	// TODO figure out YT query and implement with a live YT query
	
	// TODO generate random test data
}


