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
        Assertions.assertNotEquals("", query);
    }
}
