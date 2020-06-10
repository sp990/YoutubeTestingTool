import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DifferenceRunner implements MetamorphicTestRunner{

    private boolean result = false;

    @Override
    public void runTest() {

        /*Test whether the result of a query is different in a specified aspect
        The specified aspect is the query, with the same channelId.
        The expected result would be a set of items with identical channelIds and
        ChannelTitles, but with differing video titles and publish times*/
        System.out.println("---Starting Difference Test---");

        //Step 1: Set a ChannelId
        String channelId = "UC_x5XG1OV2P6uZZ5FSM9Ttw"; //Google Developers ChannelId

        //Step 2: retrieve a random Word
        String queryWord = YoutubeRandomizer.getRandomDictionaryWord();

        System.out.println("Sending Source Request...");
        //Step 3: Send request with random word as the q field
        JSONObject sourceResult = differenceRequest(channelId, queryWord);

        System.out.println("Sending Follow Up Request...");
        //Step 4: Using the same ChannelId send a follow up request with a new random word in the q field
        // Check to make sure the new random word is not the same as step 2
        String followUpQueryWord = new String();
        while(followUpQueryWord.equals(queryWord) || followUpQueryWord.isEmpty())
            followUpQueryWord = YoutubeRandomizer.getRandomDictionaryWord();

        JSONObject followUpResult = differenceRequest(channelId, followUpQueryWord);

        System.out.println("Comparing Results...");
        //Step 5: Compare the results to ensure that the difference is in the video title and publish times
        result = compareRequestResults(sourceResult, followUpResult);

        printReport();
    }

    public boolean compareRequestResults(JSONObject source, JSONObject followUp) {
        JSONArray sourceItems = source.getJSONArray("items");
        JSONArray followUpItems = followUp.getJSONArray("items");

        for(int i = 0; i < sourceItems.toList().size(); i++) {
            JSONObject sourceSnippet = sourceItems.getJSONObject(i);
            for(int j = 0; j < followUpItems.toList().size(); j++) {
                JSONObject followUpSnippet = followUpItems.getJSONObject(j);
                if(!compareChannelId(sourceSnippet, followUpSnippet))
                    return false;
                else if(!compareChannelTitle(sourceSnippet, followUpSnippet))
                    return false;
                else if(!comparePublishTime(sourceSnippet, followUpSnippet))
                    return false;
                else if(!compareTitle(sourceSnippet, followUpSnippet))
                    return false;
            }
        }

        return true;
    }

    public boolean comparePublishTime(JSONObject source, JSONObject followUp) {
        String sourcePublishTime = source.getJSONObject("snippet").get("publishTime").toString();
        String followUpPublishTime = followUp.getJSONObject("snippet").get("publishTime").toString();
        if(!sourcePublishTime.equals(followUpPublishTime))
            return true;
        else
            return false;
    }

    public boolean compareTitle(JSONObject source, JSONObject followUp) {
        String sourceTitle = source.getJSONObject("snippet").get("title").toString();
        String followUpTitle = followUp.getJSONObject("snippet").get("title").toString();
        if(!sourceTitle.equals(followUpTitle))
            return true;
        else
            return false;
    }

    public boolean compareChannelTitle(JSONObject source, JSONObject followUp) {
        String sourceChannelTitle = source.getJSONObject("snippet").get("channelTitle").toString();
        String followUpChannelTitle = followUp.getJSONObject("snippet").get("channelTitle").toString();
        if(sourceChannelTitle.equals(followUpChannelTitle))
            return true;
        else
            return false;
    }

    public boolean compareChannelId(JSONObject source, JSONObject followUp){
        String sourceChannelId = source.getJSONObject("snippet").get("channelId").toString();
        String followUpChannelId = followUp.getJSONObject("snippet").get("channelId").toString();
        if(sourceChannelId.equals(followUpChannelId))
            return true;
        else
            return false;
    }

    public JSONObject differenceRequest(String channelId, String query) {
        Map<String,String> params = new HashMap<>();
        params.put("part", "snippet");
        params.put("type", "video");
        params.put("fields", "items/snippet(title,channelId,channelTitle,publishTime)");
        params.put("channelId", channelId);
        params.put("q", query);

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
            System.out.println("Difference Test: Passed");
        else
            System.out.println("Difference Test: Failed");
    }

    public boolean getResult() {
        return result;
    }
}
