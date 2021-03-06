import java.util.Map;

public class YoutubeQueryBuilder {

    public static String buildQuery(String queryType, Map<String, String> params) {
        String request = "https://www.googleapis.com/youtube/v3/";
        request += queryType + "?";
        for (Map.Entry<String, String> entry : params.entrySet()) {
            request += entry.getKey();
            request += "=";
            request += entry.getValue();
            request += "&";
        }
        request += /*"Enter Youtube V3 API Key here"*/;
        return request;
    }
}
