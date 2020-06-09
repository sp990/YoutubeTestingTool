import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;




public class DisjointRunner implements MetamorphicTestRunner{
    /* I want to test the YouTube API that by querying for a channels uploaded videos I am getting only that channels videos.
    to do this test I am using the disjoint metamorphic relation test.
    I will get a channels uploads playlist and then get another random channels playlist and make sure the videosIDs are different.
    therefore confirming i am only getting that users playlist*/

    private final String sourceChannelID = "UC_x5XG1OV2P6uZZ5FSM9Ttw"; //"Google Developers"
    private String sourceUploadsID;
    private ArrayList<String> sourcePlayList = new ArrayList<>();
    private String randomChannelID;
    private String randomUploadsID = "";
    private ArrayList<String> randomChannelUploadedPlayListItems = new ArrayList<>();
    private boolean result;

    public DisjointRunner(){ }

    public boolean getResult(){
        return result;
    }

    public void setRandomChannelID(){
        randomChannelID = YoutubeRandomizer.getRandomChannelID();
    }

    public String getRandomChannelID(){
        return randomChannelID;
    }

    public void setRandomChannelUploadsID(String randomChannelID) throws IOException {
        System.out.println("Sending Request to get channels Upload playlistId...");
        randomUploadsID="";
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
        System.out.println("Sending Request to get playlist items by Id...");
        randomChannelUploadedPlayListItems.clear();
        Map<String,String> params = new HashMap<>();
        params.put("part","contentDetails");
        params.put("playlistId",uploadsID);
        params.put("maxResults","50"); //this is the maximum that YouTube API allows to be returned in one query

        JSONObject result = YoutubeQuery.runQuery("playlistItems", params);
        JSONArray items = result.getJSONArray("items");
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

    public boolean comparePlaylistsVideoIdsForDisjointness(ArrayList<String> sourcePlayList, ArrayList<String> followUpPlayList){
        System.out.println("Comparing the two sets...");
        boolean testResult = false;
        System.out.println(sourcePlayList);
        System.out.println(followUpPlayList);
        //iterate the test list
        for (String videoID: followUpPlayList) {
            //check the source list doesn't contain a videoId from the source list (returns true if it does contain a matching element)
            testResult = sourcePlayList.contains(videoID);
            //if a matching element has been found the test fails
            if(testResult){
                return false;
            }
        }
        //if no matching element has been found the test passes
        return true;
    }


    @Override
    public void runTest() {
        System.out.println("---Starting Disjoint Test---");
        try {
            //set up the source test to see if we can get a list of video ids based off a channels uploadId
            System.out.println("Setting the source Test uploads with channel id" + sourceChannelID+ "...");
            setRandomChannelUploadsID(sourceChannelID);
            sourceUploadsID = getRandomChannelUploadsID();
            setRandomChannelUploadedPlayListItems(sourceUploadsID);
            for(String videoId: getRandomChannelsUploadedPlayListItems()){
                sourcePlayList.add(videoId);
            }

            //get a random channel to test the source test with a follow up
            System.out.println("Setting the follow up test channelId...");
            while (getRandomChannelID() == sourceChannelID || getRandomChannelID() == null){
                setRandomChannelID();
            }

            //get the random channels uploadId by sending the request
            setRandomChannelUploadsID(getRandomChannelID());

            //use the random channels uploadId to send a request to the playlistItems and collect the videoIds
            setRandomChannelUploadedPlayListItems(getRandomChannelUploadsID());

            //using disjointness as a test of validity compare the list of the random channels uploaded videoIds with the source tests. The sets should be disjoint
            // meaning no video uploaded by the source channel should be in the random channels list.
            result = comparePlaylistsVideoIdsForDisjointness(sourcePlayList, randomChannelUploadedPlayListItems);

            printReport();
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    public void printReport() {
        if(getResult()){
            System.out.println("Disjoint test: Passed");
        } else {
            System.out.println(("Disjoint test: Failed"));
        }
    }
}