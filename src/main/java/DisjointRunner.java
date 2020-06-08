import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
// I want to test the YouTube API that by querying for a channels uploaded videos I am getting only that channels videos.
//to do this test I am using the disjoint metamorphic relation test.
//I will get a channels uploads playlist and then get another random channels playlist and make sure the videosIDs are different.
//therefore confirming i am only getting that users playlist

public class DisjointRunner implements MetamorphicTestRunner{
    private String sourceChannelID;
    private String sourceUploadsID;
    private ArrayList<String> sourcePlayList;
    private String randomChannelID;
    private String randomUploadsID = "";
    private ArrayList<String> randomChannelUploadedPlayListItems = new ArrayList<>();

    public void setRandomChannelID(){
        randomChannelID = YoutubeRandomizer.getRandomChannelID();
    }

    public String getRandomChannelID(){
        return randomChannelID;
    }

    public void setRandomChannelUploadsID(String randomChannelID) throws IOException {
        Map<String,String> params = new HashMap<>();
        params.put("part","contentDetails");
        params.put("id",randomChannelID);
        params.put("field","items/contentDetails/relatedPlaylists/uploads");

        JSONObject result = YoutubeQuery.runQuery("channels", params);
        JSONArray items = result.getJSONArray("items");
        JSONObject jsonobject = items.getJSONObject(0);
        JSONObject contentDetails = jsonobject.getJSONObject("contentDetails");
        JSONObject relatedPlaylists = contentDetails.getJSONObject("relatedPlaylists");
        randomUploadsID = relatedPlaylists.get("uploads").toString();
    }

    public String getRandomChannelUploadsID(){
        return randomUploadsID;
    }

    public void setRandomChannelUploadedPlayListItems(String uploadsID) throws IOException {
        Map<String,String> params = new HashMap<>();
        params.put("part","contentDetails");
        params.put("playlistId",uploadsID);
        params.put("maxResults","25");

        JSONObject result = YoutubeQuery.runQuery("playlistItems", params);
        System.out.println(result);
        JSONArray items = result.getJSONArray("items");
        System.out.println(items);
        for(int i = 0; i < items.length(); i++){
            JSONObject jsonobject = items.getJSONObject(i);
            JSONObject contentDetails = jsonobject.getJSONObject("contentDetails");
            String videoID = contentDetails.get("videoId").toString();
            randomChannelUploadedPlayListItems.add(videoID);
        }
    }

    public ArrayList<String> getRandomChannelsUploadedPlayListItems(){
        return randomChannelUploadedPlayListItems;
    }


    @Override
    public void runTest() {

    }
}