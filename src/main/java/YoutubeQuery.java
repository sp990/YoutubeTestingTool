
import org.json.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

public class YoutubeQuery {

    public static JSONObject runQuery(String queryType, Map<String, String> params) throws IOException {
        String query = YoutubeQueryBuilder.buildQuery(queryType, params);

        return new JSONObject(sendRequest(query));
    }

    public static String sendRequest(String query) throws IOException {
        URL url = new URL(query);
        URLConnection con = url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String response = "";
        String line;
        while ((line = in.readLine()) != null){
            response += line;
            response += "\n";
        }
        return response;
    }
}
