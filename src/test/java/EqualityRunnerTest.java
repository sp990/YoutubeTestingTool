import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.ArrayList;

public class EqualityRunnerTest {

    EqualityRunner eqRun = new EqualityRunner();

    //tests equality success from generate data methods - tdd test 2
    @Test
    public void testEqualitySuccess() throws IOException {

        JSONObject sourceData = new JSONObject();
        JSONObject passData = new JSONObject();
        ArrayList<String> titles = new ArrayList<>();

        sourceData = generateTestData();
        passData = generatePassTestData();

        String title = (String) sourceData.getJSONArray("items").getJSONObject(0).getJSONObject("snippet").get("title");
        String title1 = (String) passData.getJSONArray("items").getJSONObject(0).getJSONObject("snippet").get("title");
        Assertions.assertNotNull(title);
        Assertions.assertFalse(title.isEmpty());
        titles.add(title);
        titles.add(title1);

        Assertions.assertTrue(eqRun.testEquality(sourceData, passData));

        for(int i = 0; i < titles.size(); i++){

            System.out.println(titles.get(i));
        }

    }

    //tests equality fail from generated data methods - tdd test 1
    @Test
    public void testEqualityFail() throws IOException {

        JSONObject sourceData = new JSONObject();
        JSONObject failData = new JSONObject();
        ArrayList<String> titles = new ArrayList<>();

        sourceData = generateTestData();
        failData = generateFailTestData();

        String title = (String) sourceData.getJSONArray("items").getJSONObject(0).getJSONObject("snippet").get("title");
        String title1 = (String) failData.getJSONArray("items").getJSONObject(0).getJSONObject("snippet").get("title");
        Assertions.assertNotNull(title);
        Assertions.assertFalse(title.isEmpty());
        titles.add(title);
        titles.add(title1);

        Assertions.assertFalse(eqRun.testEquality(sourceData, failData));
        for(int i = 0; i < titles.size(); i++){

            System.out.println(titles.get(i));
        };

    }

    //generates base test data for tdd test, contains title for comparing
    public JSONObject generateTestData() {

        JSONObject testData = new JSONObject();
        testData.put("items", new JSONArray());
        testData.getJSONArray("items").put(new JSONObject().put("snippet", new JSONObject()));
        testData.getJSONArray("items").getJSONObject(0).getJSONObject("snippet").put("title", "InitialTest");

        return testData;
    }

    //generates a different title to assertfalse
    public JSONObject generateFailTestData() {

        JSONObject testDataFail = new JSONObject();
        testDataFail.put("items", new JSONArray());
        testDataFail.getJSONArray("items").put(new JSONObject().put("snippet", new JSONObject()));
        testDataFail.getJSONArray("items").getJSONObject(0).getJSONObject("snippet").put("title", "FailTest");

        return testDataFail;
    }

    //generates the same title for asserttrue
    public JSONObject generatePassTestData() {

        JSONObject testDataPass = new JSONObject();
        testDataPass.put("items", new JSONArray());
        testDataPass.getJSONArray("items").put(new JSONObject().put("snippet", new JSONObject()));
        testDataPass.getJSONArray("items").getJSONObject(0).getJSONObject("snippet").put("title", "InitialTest");

        return testDataPass;
    }

    //runs program
    @Test
    public void test(){

        eqRun.runTest();

        System.out.println(eqRun.getResult());
    }
}
