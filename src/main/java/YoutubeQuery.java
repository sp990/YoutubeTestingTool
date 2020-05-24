import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class YoutubeQuery {

    public static void main(String[] args)  {
        Map<String, String> params = new HashMap<>();
        params.put("part", "snippet");
        params.put("maxResult", "25");
        String result = YoutubeQuery.runQuery("search", params);

        System.out.println(result);
    }

    public static String runQuery(String queryType, Map<String, String> params) {
        String query = YoutubeQueryBuilder.buildQuery(queryType, params);
        String response = "";
        try{
            response = sendRequest(query);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return response;
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
