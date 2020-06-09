import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class EquivalenceRunnerTest {

    private EquivalenceRunner equivalenceRunner = new EquivalenceRunner();

    @Test
    public void queryRequest_returnsQueryResultSet_NotNull() throws IOException {
        Assertions.assertNotNull(equivalenceRunner.queryRequest("relevance"));
    }

    @Test
    public void getRandomOrder_returnsRandomOrderValue_NotNull(){
        Assertions.assertNotNull(equivalenceRunner.getRandomOrder());
    }

    @Test
    public void compareResults_comparedEquivalenceOfTwoResultSets_NotNull(){
        JSONObject result = new JSONObject();
        result.put("items", new JSONArray());
        result.getJSONArray("items").put(new JSONObject().put("snippet", new JSONObject()));
        result.getJSONArray("items").getJSONObject(0).getJSONObject("snippet").put("channelId","resultSet1");

        JSONObject followUp = new JSONObject();
        followUp.put("items", new JSONArray());

        Assertions.assertNotNull(equivalenceRunner.compareResults(result, followUp));
    }

    @Test
    public void compareResults_comparedEquivalenceOfTwoResultSets_EquivalenceIsFalse(){
        JSONObject result = new JSONObject();
        result.put("items", new JSONArray());
        result.getJSONArray("items").put(new JSONObject().put("snippet", new JSONObject()));
        result.getJSONArray("items").getJSONObject(0).getJSONObject("snippet").put("channelId","resultSet1");
        result.getJSONArray("items").getJSONObject(0).getJSONObject("snippet").put("title","resultSet1");

        JSONObject followUp = new JSONObject();
        followUp.put("items", new JSONArray());
        followUp.getJSONArray("items").put(new JSONObject().put("snippet", new JSONObject()));
        followUp.getJSONArray("items").getJSONObject(0).getJSONObject("snippet").put("channelId","resultSet2");
        followUp.getJSONArray("items").getJSONObject(0).getJSONObject("snippet").put("title","resultSet2");

        Assertions.assertFalse(equivalenceRunner.compareResults(result, followUp));
    }

    @Test
    public void compareResults_comparedEquivalenceOfTwoResultSets_EquivalenceIsTrue(){
        JSONObject result = new JSONObject();
        result.put("items", new JSONArray());
        result.getJSONArray("items").put(new JSONObject().put("snippet", new JSONObject()));
        result.getJSONArray("items").getJSONObject(0).getJSONObject("snippet").put("channelId","resultSet1");
        result.getJSONArray("items").getJSONObject(0).getJSONObject("snippet").put("title","resultSet1");

        JSONObject followUp = new JSONObject();
        followUp.put("items", new JSONArray());
        followUp.getJSONArray("items").put(new JSONObject().put("snippet", new JSONObject()));
        followUp.getJSONArray("items").getJSONObject(0).getJSONObject("snippet").put("channelId","resultSet1");
        followUp.getJSONArray("items").getJSONObject(0).getJSONObject("snippet").put("title","resultSet1");

        Assertions.assertTrue(equivalenceRunner.compareResults(result, followUp));
    }

}