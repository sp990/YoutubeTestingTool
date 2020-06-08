import org.json.JSONObject;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EquivalenceRunnerTest {
    @Test
    public void equivalenceTest1() throws IOException {

        String channelId = YoutubeRandomizer.getRandomChannelId();

        Map<String, String> params = new HashMap<>();
        params.put("part", "snippet");
        params.put("maxResults", "50");
        params.put("fields", "items/id/playlistId,items/snippet(title,publishTime)");
        params.put("channelId", channelId);
        params.put("type", "playlist");

        JSONObject result = YoutubeQuery.runQuery("search", params);
        System.out.println(result);

        params.put("order", "date");
        JSONObject followUp = YoutubeQuery.runQuery("search", params);
        System.out.println(followUp);

        List resultArray = result.getJSONArray("items").toList();
        List followUpArray = followUp.getJSONArray("items").toList();

        System.out.print(followUpArray.containsAll(resultArray) && resultArray.containsAll(followUpArray));
    }
}
//write test for non existent method, it fails, write actual method till it passes the test.
//write test to get a channelID with results under 50. input bad channelID, make it fail, then put good channel ID.
//have runtest method (main method) to be called run the test for equivalence.