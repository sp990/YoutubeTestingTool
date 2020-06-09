
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EqualityRunner implements MetamorphicTestRunner {

    private boolean result;
    private ArrayList<String> firstResultTitles = new ArrayList<>();
    private ArrayList<String> secondResultTitles = new ArrayList<>();

    //Equality test for testing tool
    public boolean testEquality(JSONObject source, JSONObject other) throws IOException {


        if(source.length() != other.length()){

            return false;
        }


        for(int i = 0; i < source.getJSONArray("items").length(); i++) {

            String title1 = (String) source.getJSONArray("items").getJSONObject(i).getJSONObject("snippet").get("title");
            String title2 = (String) other.getJSONArray("items").getJSONObject(i).getJSONObject("snippet").get("title");
            firstResultTitles.add(title1);
            secondResultTitles.add(title2);


            if(!title1.equals(title2)){

                return false;
            }
        }
        return true;
    }

    //retrieves youtube api JSON
    public void GenerateYoutubeJSON() throws IOException {

        YoutubeDictionary yt1 = new YoutubeDictionary();
        int r = (int) (Math.random() * yt1.size());

        String search = yt1.get(r);

        Map<String, String> params = new HashMap<>();
        params.put("part", "snippet");
        params.put("maxResults", "5");
        params.put("order", "rating");
        params.put("q", search);
        params.put("fields", "items/snippet/title");

        System.out.println("Sending Source Request...");
        JSONObject searchTest1 = YoutubeQuery.runQuery("search", params);
        System.out.println("Sending Follow Up Request...");
        JSONObject searchTest2 = YoutubeQuery.runQuery("search", params);

        System.out.println("Comparing Results...");
        //sets result
        if (testEquality(searchTest1, searchTest2)) {

            result = true;
        } else {
            result = false;
        }
    }

    //gets result
    public boolean getResult(){
        return result;
    }

    public void printReport() {
        if(result)
            System.out.println("Equality Test: Passed");
        else
            System.out.println("Equality Test: Failed");
    }

    @Override
    public void runTest() {
        System.out.println("---Starting Equality Test---");
        try {
            GenerateYoutubeJSON();
        } catch (IOException e) {
            e.printStackTrace();
        }
        printReport();
    }
}
