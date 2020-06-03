import org.json.JSONObject;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class EquivalenceRunnerTest {
    @Test
    public void test(){
        Map<String, String> params = new HashMap<>();
        params.put("part", "snippet");
        params.put("channelId", "UC_x5XG1OV2P6uZZ5FSM9Ttw");
        params.put("maxResults", "1");

        try{
            JSONObject result = YoutubeQuery.runQuery("playlists", params);
            String title = result.getJSONArray("items").getJSONObject(0).getJSONObject("snippet").get("title").toString();
            System.out.println(title);
        }
        catch(Exception e){

        }
    }
}
