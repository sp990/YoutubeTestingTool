import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class EquivalenceRunner implements MetamorphicTestRunner{

    String orderValuesList[] = {"date","rating","viewCount","title"};
    private boolean result;

    @Override
    public void runTest() {
        try {
            System.out.println("---Starting Equivalence Test---");
            String order = "relevance";
            System.out.println("Sending Source Request...");
            JSONObject source = queryRequest(order);

            order = getRandomOrder();
            System.out.println("Sending Follow Up Request...");
            JSONObject followUp = queryRequest(order);

            System.out.println("Comparing Results...");
            result = compareResults(source, followUp);
        }
        catch(IOException e){
            e.printStackTrace();
        }
        printReport();
    }

    public JSONObject queryRequest(String order) throws IOException {
        Map<String, String> params = new HashMap<>();
        params.put("part", "snippet");
        params.put("maxResults", "50");
        params.put("fields", "pageInfo/totalResults,items/id/playlistId,items/snippet(title,publishTime)");
        params.put("channelId", "UCS5tt2z_DFvG7-39J3aE-bQ");
        params.put("type", "playlist");
        params.put("order", order);

        return YoutubeQuery.runQuery("search", params);
    }

    public String getRandomOrder(){
        Random random = new Random();
        int orderVal = random.nextInt(orderValuesList.length);
        return orderValuesList[orderVal];
    }

    public boolean compareResults(JSONObject source, JSONObject followUp){
        List resultArray = source.getJSONArray("items").toList();
        List followUpArray = followUp.getJSONArray("items").toList();
        return (followUpArray.containsAll(resultArray)) && (resultArray.containsAll(followUpArray));
    }

    public void printReport() {
        if(result)
            System.out.println("Equivalence Test: Passed");
        else
            System.out.println("Equivalence Test: Failed");
    }

    public boolean getResult(){
        return result;
    }

}
