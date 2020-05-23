import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class YoutubeSearchTest {

    @Test
    public void searchBuilderTest(){

        SearchBuilder sb = new SearchBuilder();

        Map<String, String> param = new HashMap<>();
        param.put("part", "snippet");
        param.put("maxResult", "25");

        String request = sb.searchBuilder(param);
        System.out.println(request);
        Assert.assertNotNull(request);
    }
}
