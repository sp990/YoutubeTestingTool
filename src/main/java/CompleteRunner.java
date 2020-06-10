import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//spare key if needed: AIzaSyAwjEZKPoz26cLXtHGDfb1GavCxZALyfQM

public class CompleteRunner implements MetamorphicTestRunner{

    private boolean result = false;

    @Override
    public void runTest() {

        /*Test whether the result of a query is different in a specified aspect
        The specified aspect is the query, with the same channelId.
        The expected result would be a set of items with identical channelIds and
        ChannelTitles, but with differing video titles and publish times*/
        System.out.println("---Starting Complete Test---");

        //Step 1: Set a ChannelId
        String channelId = "UC_x5XG1OV2P6uZZ5FSM9Ttw"; //Google Developers ChannelId

        //Step 2: Set query
        String queryWord = "darts+time";

        System.out.println("Sending Source Request...");
        //Step 3: Send request
        String duration = "any";
        JSONObject sourceResult = completeRequest(channelId, queryWord, duration);

        // step 4: send follow up request (using the 3 different categories of duration)

        System.out.println("Sending Follow Up Request...");
        String shortlength = "short";
        String mediumlength = "medium";
        String longlength = "long";

        JSONObject shortResult = completeRequest(channelId, queryWord, shortlength);
        JSONObject mediumResult = completeRequest(channelId, queryWord, mediumlength);
        JSONObject longResult = completeRequest(channelId, queryWord, longlength);

        //Convert results to lists of objects for analysis

        List sourceItems = sourceResult.getJSONArray("items").toList();//convert all to list of items
        List shortitems = shortResult.getJSONArray("items").toList();
        List mediumitems = mediumResult.getJSONArray("items").toList();
        List longitems = longResult.getJSONArray("items").toList();

        //print lists lengths to show total, short long and medium to visualise if they add up to total.
        System.out.println("Comparing Results...");

        //Step 5: Compare the results to see if every item in source list is contained in at least one of the others (therefore complete)
        result = compareRequestResults(sourceItems, shortitems, mediumitems, longitems);

        printReport();
    }

    public Boolean compareRequestResults(List sourceItems, List shortitems, List mediumitems, List longitems) {


        for(int i = 0; i < sourceItems.size(); i++)
        {
            Object sourceitem = sourceItems.get(i);

            if (!checkExists(sourceitem, shortitems) & !checkExists(sourceitem, mediumitems) & !checkExists(sourceitem, longitems))
            {
                return false;
            }
        }

        return true;
    }

    public boolean checkExists(Object source,List filtered)//check if item exists in other list
    {
        if(filtered.contains(source))
        {
            return true;
        }
        else
        {
            return false;

        }
    }


    public JSONObject completeRequest(String channelId, String query,String duration) {
        Map<String,String> params = new HashMap<>();
        params.put("part", "snippet");
        params.put("type", "video");
        params.put("videoDuration", duration);
        params.put("fields", "items");
        params.put("channelId", channelId);
        params.put("q", query);
        params.put("maxResults", "50");

        JSONObject result = null;
        try{
            result = YoutubeQuery.runQuery("search", params);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public void printReport() {
        if(result)
            System.out.println("Complete Test: Passed");
        else
            System.out.println("Complete Test: Failed");
    }

    public boolean getResult() {
        return result;
    }
}
