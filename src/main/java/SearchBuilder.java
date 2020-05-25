import java.util.Map;

public class SearchBuilder {

    public String searchBuilder(Map<String, String> param) {
        String request = "https://www.googleapis.com/youtube/v3/search?";
        for (Map.Entry<String, String> entry : param.entrySet()) {
            request += entry.getKey();
            request += "=";
            request += entry.getValue();
            request += "&";
        }
        request += "key=AIzaSyDgfo8_4ugQhLhJt8dlN_5303hkXgwMKZY";
        return request;
    }
}
