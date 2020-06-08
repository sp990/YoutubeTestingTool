import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class DisjointRunnerTest {
    //init the system under test
    private final DisjointRunner disjointRunner = new DisjointRunner();

    @Test
    public void getRandomChannelID_setStringtoanID_NotNull() {
        String channelID = dijointRunner.getRandomChannelID();
        AssertNotNull(channelID);

    }
    //set up youtubequery class - test the class is initialized

    //build query for get categories - test the query is rerutned as expected

    //randomy select ccategory names - test the randomness

    //build a quesry for channels using that category and marked for kids - test the results are as expected

    //collect the results and store for comparison - test the array gets the results?

    //do another query for channels using the same category but not marked for kids - same test as before?

    //collect the results - same test as before?

    //compare the two collections to test for disjointness - test the comparison checking?

}
