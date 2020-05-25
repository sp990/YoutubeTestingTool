
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class YoutubeQueryTest {

    @Test
    public void sendRequest_SendValidURL_ResultNotNull() {
        Map<String, String> params = new HashMap<>();
        params.put("part", "snippet");
        try {
            String result = YoutubeQuery.sendRequest(
                    "https://www.googleapis.com/youtube/v3/channels?part=snippet&id=UC_x5XG1OV2P6uZZ5FSM9Ttw&key=AIzaSyDgfo8_4ugQhLhJt8dlN_5303hkXgwMKZY");
            Assert.assertNotNull(result);
        }
        catch (IOException e) {
            Assert.fail("IOException Thrown");
            e.printStackTrace();
        }
    }

    @Test
    public void sendRequest_SendInvalidURL_IOExceptionThrown() {
        try {
            YoutubeQuery.sendRequest("");
            Assert.fail("Should throw an IOException");
        }
        catch (IOException e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void runQuery_InvalidParams_ResponseCode400() {
        Map<String, String> params = new HashMap<>();
        params.put("part", "snippet");
        try {
            JSONObject result = YoutubeQuery.runQuery("channels", params);
            Assert.fail("Should throw an IOException");
        }
        catch (IOException e) {
            Assert.assertTrue(e.toString().contains("code: 400"));
        }
    }

    @Test
    public void runQuery_JSONObjectReturned_ResultNotNull() {
        Map<String, String> params = new HashMap<>();
        params.put("part", "snippet");
        params.put("id", "UC_x5XG1OV2P6uZZ5FSM9Ttw"); //Google Developers Channel id
        try{
            JSONObject result = YoutubeQuery.runQuery("channels", params);
            Assert.assertNotNull(result);
        }
        catch (IOException e ) {
            e.printStackTrace();
        }
    }

    @Test
    public void runQuery_AssertSnippetJSONObject_True() {
        Map<String, String> params = new HashMap<>();
        params.put("part", "snippet");
        params.put("id", "UC_x5XG1OV2P6uZZ5FSM9Ttw"); //Google Developers Channel id
        try {
            JSONObject result = YoutubeQuery.runQuery("channels", params);
            Assert.assertTrue(result.getJSONArray("items").getJSONObject(0).has("snippet"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void runQuery_FollowUpQueryRequest_CorrectPlaylistReturned() {
        Map<String, String> params = new HashMap<>();
        params.put("part", "contentDetails");
        params.put("id", "UC_x5XG1OV2P6uZZ5FSM9Ttw"); //Google Developers Channel id
        try {
            //Initial Query
            JSONObject initialResult = YoutubeQuery.runQuery("channels", params);

            //Follow Up Query
            params.clear();
            params.put("part", "snippet");
            params.put("id", initialResult.getJSONArray("items")
                    .getJSONObject(0)
                    .getJSONObject("contentDetails")
                    .getJSONObject("relatedPlaylists")
                    .get("uploads")
                    .toString());

            JSONObject followUpResult = YoutubeQuery.runQuery("playlists", params);

            //Follow up result should have the channelId from the initial search
            Assert.assertEquals("UC_x5XG1OV2P6uZZ5FSM9Ttw", followUpResult.getJSONArray("items")
                    .getJSONObject(0)
                    .getJSONObject("snippet")
                    .get("channelId"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
