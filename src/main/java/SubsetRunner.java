import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
 
import java.io.FileReader;
import java.util.Iterator;


public class SubsetRunner implements MetamorphicTestRunner {

	// This is just for testing and will be removed from final version
   public static void main(String[] args) throws IOException {
	   SubsetRunner testing = new SubsetRunner();
	   System.out.println(testing.isSubsetBinSubsetA());
   }
	
	@Override
    public void runTest() {

    }

	public Boolean isSubsetBinSubsetA() {
		
		// This will be replaced by getSet Method, which will call YT.runQuery()
		JSONArray subsetA = readJSONData("C:\\Users\\swyer\\Desktop\\314\\data-full-subset.json");
		JSONArray subsetB = readJSONData("C:\\Users\\swyer\\Desktop\\314\\data-partial-subset.json");
		JSONArray subsetC = readJSONData("C:\\Users\\swyer\\Desktop\\314\\data-no-subset.json");
		
		return subsetA.containsAll(subsetB);
	}
	
	
//	public JSONArray getSet() throws IOException {
// 		JSONArray items = null;
//		Map<String, String> params = new HashMap<>();
//        params.put("part", "snippet");
//        params.put("location", "-33.865143%2C151.209900"); 
//        params.put("locationRadius", "100mi");
//        params.put("publishedAfter", "2015-01-01T00%3A00%3A00Z"); 
//        params.put("publishedBefore", "2020-01-02T00%3A00%3A00Z"); 
//        params.put("q", "cat");
//        params.put("type", "video");
//        params.put("fields", "items.id.videoId");
//        
//		JSONObject obj = YoutubeQuery.runQuery("search", params);
//	    items = (JSONArray) jsonObject.get("items");
//      return items; 
//	}
	
	
	// For testing purposes only, reads in JSON data from a file
	public JSONArray readJSONData(String filePath) {
		JSONParser parser = new JSONParser();
		JSONArray items = null;
		try {
			Object obj = parser.parse(new FileReader(filePath));
			JSONObject jsonObject = (JSONObject) obj;
			items = (JSONArray) jsonObject.get("items");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return items;
	}

	
	// TODO figure out YT query and implement with a live queries
	
	// TODO generate random test data
	
	// TODO look up strict subset
}


