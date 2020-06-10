import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class YoutubeQueryBuilderTest {

    @Test
    public void buildQuery_ValidQuery_QueryNotNull() {
        Map<String, String> params = new HashMap<>();
        params.put("part", "snippet");
        String query = YoutubeQueryBuilder.buildQuery("channels", params);
        Assertions.assertEquals("https://www.googleapis.com/youtube/v3/channels?part=snippet&key=AIzaSyDgfo8_4ugQhLhJt8dlN_5303hkXgwMKZY", query);
    }
}
