import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.*;

public class YoutubeRandomizer {

    private static YoutubeDictionary dictionary;

    public static String getRandomChannelID() {
        Random rand = new Random();
        String channelID = null;

        Map<String, String> params = new HashMap<>();
        params.put("part", "snippet");
        params.put("type", "channel");
        params.put("q", getRandomDictionaryWord());
        params.put("fields", "items/snippet/channelId");
        params.put("maxResults", "20");

        try{
            JSONObject result = YoutubeQuery.runQuery("search", params);
            JSONArray items = result.getJSONArray("items");
            JSONObject snippet = items.getJSONObject(rand.nextInt(items.toList().size())).getJSONObject("snippet");
            channelID = snippet.get("channelId").toString();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return channelID;
    }

    public static String getRandomDictionaryWord(){
        if(dictionary == null) {
            try {
                dictionary = new YoutubeDictionary();
            } catch (IOException e) {
                System.err.println("Problem in loading Dictionary in YoutubeDictionary");
                e.printStackTrace();
            }
        }
        Random rand = new Random();
        String word = dictionary.get(rand.nextInt(dictionary.size()));
        return word;
    }
}
