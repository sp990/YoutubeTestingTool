import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class YoutubeQueryBuilderTest {

    @Test
    public void buildQuery_ValidQuery_QueryNotNull() {
        Map<String, String> params = new HashMap<>();
        params.put("part", "snippet");
        String query = YoutubeQueryBuilder.buildQuery("channels", params);
        Assert.assertNotEquals("", query);
    }
}
