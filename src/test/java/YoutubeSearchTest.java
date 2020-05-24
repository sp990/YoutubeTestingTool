import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class YoutubeSearchTest {

    @Test
    public void searchBuilderTest(){

        YoutubeQueryBuilder queryBuilder = new YoutubeQueryBuilder();

        Map<String, String> params = new HashMap<>();
        params.put("part", "status");
        params.put("forUsername", "Youtube");

        String result = YoutubeQuery.runQuery("channels", params);
        System.out.println(result);
    }
}
