
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;


import java.util.Arrays;
import java.util.List;

public class CompleteRunnerTest {

    CompleteRunner testRunner = new CompleteRunner();


    @Test
    public void completeRequest_SendRequestWithChannelID_ResponseChannelIDEqualsInput() {
        String channelId = "UC_x5XG1OV2P6uZZ5FSM9Ttw"; //Google Developers ChannelId
        JSONObject sourceRequest = testRunner.completeRequest(channelId, "Test", "any");

        //Asserts that the result has the correct channel ID
        Assertions.assertEquals("UC_x5XG1OV2P6uZZ5FSM9Ttw", sourceRequest.getJSONArray("items").getJSONObject(0).getJSONObject("snippet").get("channelId"));
    }

    @Test
    public void compareRequestResults_InputSourceAndGoodFollowUpLists_PassTest() {
        //Create source list (full list)
        List sourcelist = generateSourceList();

        //Create follow up lists (short, medium and long)
        List shortlist = generateShortList();
        List mediumlist = generateMediumList();
        List longlist = generateLongList();

        //Call method using generated lists
        Boolean result = testRunner.compareRequestResults(sourcelist, shortlist,mediumlist,longlist);

        Assertions.assertTrue(result);
    }

    @Test
    public void compareRequestResults_InputSourceAndBadFollowUpLists_FailTest() {
        //Create source list (full list)
        List sourcelist = generateSourceList();

        //Create follow up lists (short, medium and long) where long list is incomplete
        List shortlist = generateShortList();
        List mediumlist = generateMediumList();
        List longlist = generateBadLongList();

        //Call method using generated lists
        Boolean result = testRunner.compareRequestResults(sourcelist, shortlist,mediumlist,longlist);

        Assertions.assertFalse(result);
    }

    @Test
    public void checkExists_InputObjectAndGoodList_True() {
        String testobject = "duck";
        List testlist = generateGoodExistsList();

        Boolean result = testRunner.checkExists(testobject, testlist);

        Assertions.assertTrue(result);
    }

    @Test
    public void checkExists_InputObjectAndBadList_False() {
        String testobject = "duck";
        List testlist = generateBadExistsList();

        Boolean result = testRunner.checkExists(testobject, testlist);

        Assertions.assertFalse(result);
    }

    public List generateSourceList()
    {
        List<String> sourcelist = Arrays.asList("cat", "dog", "bird", "frog", "duck");
        return sourcelist;
    }

    public List generateShortList()
    {
        List<String> shortlist = Arrays.asList("dog", "bird");
        return shortlist;
    }

    public List generateMediumList()
    {
        List<String> mediumlist = Arrays.asList("cat");
        return mediumlist;
    }
    public List generateLongList()
    {
        List<String> longlist = Arrays.asList("frog", "duck");
        return longlist;
    }
    public List generateBadLongList()
    {
        List<String> longlist = Arrays.asList("duck");
        return longlist;
    }
    public List generateGoodExistsList()
    {
        List<String> goodlist = Arrays.asList("cat", "dog", "bird", "frog", "duck");
        return goodlist;
    }
    public List generateBadExistsList()
    {
        List<String> badlist = Arrays.asList("cat", "dog", "bird", "frog");
        return badlist;
    }

}
