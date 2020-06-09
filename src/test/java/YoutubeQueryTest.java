
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class YoutubeQueryTest {

    @Test
    public void sendRequest_SendValidURL_ResultNotNull() {
        try {
            String result = YoutubeQuery.sendRequest(
                    "https://www.googleapis.com/youtube/v3/channels?part=snippet&id=UC_x5XG1OV2P6uZZ5FSM9Ttw&key=AIzaSyDgfo8_4ugQhLhJt8dlN_5303hkXgwMKZY");
            Assertions.assertNotNull(result);
        }
        catch (IOException e) {
            Assertions.fail("IOException Thrown");
            e.printStackTrace();
        }
    }

    @Test
    public void sendRequest_SendInvalidURL_IOExceptionThrown() {
        try {
            YoutubeQuery.sendRequest("");
            Assertions.fail("Should throw an IOException");
        }
        catch (IOException e) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    public void runQuery_InvalidParams_ResponseCode400() {
        Map<String, String> params = new HashMap<>();
        params.put("part", "snippet");
        try {
            JSONObject result = YoutubeQuery.runQuery("channels", params);
            Assertions.fail("Should throw an IOException");
        }
        catch (IOException e) {
            Assertions.assertTrue(e.toString().contains("code: 400"));
        }
    }

    @Test
    public void runQuery_JSONObjectReturned_ResultNotNull() {
        Map<String, String> params = new HashMap<>();
        params.put("part", "snippet");
        params.put("id", "UC_x5XG1OV2P6uZZ5FSM9Ttw"); //Google Developers Channel id
        try{
            JSONObject result = YoutubeQuery.runQuery("channels", params);
            Assertions.assertNotNull(result);
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
            Assertions.assertTrue(result.getJSONArray("items").getJSONObject(0).has("snippet"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
