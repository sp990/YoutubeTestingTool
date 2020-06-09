import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class EquivalenceRunner implements MetamorphicTestRunner{

    String orderValuesList[] = {"date","rating","viewCount","title"};

    @Override
    public void runTest() {
        try {
            String order = "relevance";
            JSONObject result = queryRequest(order);

            order = getRandomOrder();
            JSONObject followUp = queryRequest(order);

            Boolean testResult = compareResults(result, followUp);
            System.out.print(testResult);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public JSONObject queryRequest(String order) throws IOException {
        Map<String, String> params = new HashMap<>();
        params.put("part", "snippet");
        params.put("maxResults", "50");
        params.put("fields", "pageInfo/totalResults,items/id/playlistId,items/snippet(title,publishTime)");
        params.put("channelId", "UCS5tt2z_DFvG7-39J3aE-bQ");
        params.put("type", "playlist");
        params.put("order", order);
        JSONObject result = YoutubeQuery.runQuery("search", params);
        return result;
    }

    public String getRandomOrder(){
        Random random = new Random();
        int orderVal = random.nextInt(orderValuesList.length);
        return orderValuesList[orderVal];
    }

    public boolean compareResults(JSONObject result, JSONObject followUp){
        List resultArray = result.getJSONArray("items").toList();
        List followUpArray = followUp.getJSONArray("items").toList();
        return (followUpArray.containsAll(resultArray)) && (resultArray.containsAll(followUpArray));
    }


}
