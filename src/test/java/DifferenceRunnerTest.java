
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DifferenceRunnerTest {

    DifferenceRunner testRunner = new DifferenceRunner();

    @Test
    public void comparePublishTime_InputTwoJSONObjects_True() {
        JSONObject sourceTest = getSnippetFromJSONTest(generateSourceTest());
        JSONObject followUpTestGood = getSnippetFromJSONTest(generateGoodFollowUpTest());
        Boolean result = testRunner.comparePublishTime(sourceTest, followUpTestGood);

        Assertions.assertTrue(result);
    }

    @Test
    public void comparePublishTime_InputTwoJSONObjects_False() {
        JSONObject sourceTest1 = getSnippetFromJSONTest(generateSourceTest());
        JSONObject sourceTest2 = getSnippetFromJSONTest(generateSourceTest());
        Boolean result = testRunner.comparePublishTime(sourceTest1, sourceTest2);

        Assertions.assertFalse(result);
    }

    @Test
    public void compareTitle_InputTwoJSONObjects_True() {
        JSONObject sourceTest = getSnippetFromJSONTest(generateSourceTest());
        JSONObject followUpTestGood = getSnippetFromJSONTest(generateGoodFollowUpTest());
        Boolean result = testRunner.compareTitle(sourceTest, followUpTestGood);

        Assertions.assertTrue(result);
    }

    @Test
    public void compareTitle_InputTwoJSONObjects_False() {
        JSONObject sourceTest1 = getSnippetFromJSONTest(generateSourceTest());
        JSONObject sourceTest2 = getSnippetFromJSONTest(generateSourceTest());
        Boolean result = testRunner.compareTitle(sourceTest1, sourceTest2);

        Assertions.assertFalse(result);
    }

    @Test
    public void compareChannelTitle_InputTwoJSONObjects_True() {
        JSONObject sourceTest = getSnippetFromJSONTest(generateSourceTest());
        JSONObject followUpTestGood = getSnippetFromJSONTest(generateGoodFollowUpTest());
        Boolean result = testRunner.compareChannelTitle(sourceTest, followUpTestGood);

        Assertions.assertTrue(result);
    }

    @Test
    public void compareChannelTitle_InputTwoJSONObjects_False() {
        JSONObject sourceTest = getSnippetFromJSONTest(generateSourceTest());
        JSONObject followUpTestBad = getSnippetFromJSONTest(generateBadFollowUpTest());
        Boolean result = testRunner.compareChannelTitle(sourceTest, followUpTestBad);

        Assertions.assertFalse(result);
    }

    @Test
    public void compareChannelId_InputTwoJSONObjects_True() {
        JSONObject sourceTest = getSnippetFromJSONTest(generateSourceTest());
        JSONObject followUpTestGood = getSnippetFromJSONTest(generateGoodFollowUpTest());
        Boolean result = testRunner.compareChannelId(sourceTest, followUpTestGood);

        Assertions.assertTrue(result);
    }

    @Test
    public void compareChannelId_InputTwoJSONObjects_False() {
        JSONObject sourceTest = getSnippetFromJSONTest(generateSourceTest());
        JSONObject followUpTestBad = getSnippetFromJSONTest(generateBadFollowUpTest());
        Boolean result = testRunner.compareChannelId(sourceTest, followUpTestBad);

        Assertions.assertFalse(result);
    }

    @Test
    public void differenceRequest_SendRequestWithChannelID_ResponseChannelIDEqualsInput() {
        String channelId = "UC_x5XG1OV2P6uZZ5FSM9Ttw"; //Google Developers ChannelId
        JSONObject sourceRequest = testRunner.differenceRequest(channelId, "Test");

        //Asserts that the result has the correct channel ID
        Assertions.assertEquals("UC_x5XG1OV2P6uZZ5FSM9Ttw", sourceRequest.getJSONArray("items").getJSONObject(0).getJSONObject("snippet").get("channelId"));
    }

    @Test
    public void compareRequestResults_InputTwoTestJSONObjects_PassTest() {
        //Create Test result 1
        JSONObject testResult1 = generateSourceTest();

        //Create Test result 2
        JSONObject testResult2 = generateGoodFollowUpTest();

        Boolean result = testRunner.compareRequestResults(testResult1, testResult2);

        Assertions.assertTrue(result);
    }

    @Test
    public void compareRequestResults_InputTwoTestJSONObjects_FailTest() {
        //Create Test result
        JSONObject testResult = generateSourceTest();

        Boolean result = testRunner.compareRequestResults(testResult, testResult);

        Assertions.assertFalse(result);
    }

    public JSONObject getSnippetFromJSONTest(JSONObject testJSON) {
        return testJSON.getJSONArray("items").getJSONObject(0);
    }

    public JSONObject generateBadFollowUpTest() {
        JSONObject followUpTest = new JSONObject();
        followUpTest.put("items", new JSONArray());
        followUpTest.getJSONArray("items").put(new JSONObject().put("snippet", new JSONObject()));
        followUpTest.getJSONArray("items").getJSONObject(0).getJSONObject("snippet").put("channelId", "FollowUpChannel");
        followUpTest.getJSONArray("items").getJSONObject(0).getJSONObject("snippet").put("title", "FollowUpTest");
        followUpTest.getJSONArray("items").getJSONObject(0).getJSONObject("snippet").put("channelTitle", "FollowUpChannel");
        followUpTest.getJSONArray("items").getJSONObject(0).getJSONObject("snippet").put("publishTime", "FollowUpTestTime");

        return followUpTest;
    }

    public JSONObject generateGoodFollowUpTest() {
        JSONObject followUpTest = new JSONObject();
        followUpTest.put("items", new JSONArray());
        followUpTest.getJSONArray("items").put(new JSONObject().put("snippet", new JSONObject()));
        followUpTest.getJSONArray("items").getJSONObject(0).getJSONObject("snippet").put("channelId", "TestChannelID");
        followUpTest.getJSONArray("items").getJSONObject(0).getJSONObject("snippet").put("title", "FollowUpTest");
        followUpTest.getJSONArray("items").getJSONObject(0).getJSONObject("snippet").put("channelTitle", "TestChannel");
        followUpTest.getJSONArray("items").getJSONObject(0).getJSONObject("snippet").put("publishTime", "FollowUpTestTime");

        return followUpTest;
    }

    public JSONObject generateSourceTest() {
        JSONObject sourceTest = new JSONObject();
        sourceTest.put("items", new JSONArray());
        sourceTest.getJSONArray("items").put(new JSONObject().put("snippet", new JSONObject()));
        sourceTest.getJSONArray("items").getJSONObject(0).getJSONObject("snippet").put("channelId", "TestChannelID");
        sourceTest.getJSONArray("items").getJSONObject(0).getJSONObject("snippet").put("title", "InitialTest");
        sourceTest.getJSONArray("items").getJSONObject(0).getJSONObject("snippet").put("channelTitle", "TestChannel");
        sourceTest.getJSONArray("items").getJSONObject(0).getJSONObject("snippet").put("publishTime", "InitialTestTime");

        return sourceTest;
    }
}
