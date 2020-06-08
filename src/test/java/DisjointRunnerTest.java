import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class DisjointRunnerTest {
    //init the system under test
    private final DisjointRunner disjointRunner = new DisjointRunner();

    @Test
    public void setRandomChannelID_setsRandomChannelID_NotNull() {
        disjointRunner.setRandomChannelID();
        Assertions.assertNotNull(disjointRunner.getRandomChannelID());
    }

    @Test
    public void getRandomChannelID_hasNotBeenSet_isNull(){
        String emptyChannelID = disjointRunner.getRandomChannelID();
        Assertions.assertNull(emptyChannelID);
    }

    @Test
    public void getRandomChannelID_hasBeenSet_isNotNull(){
        disjointRunner.setRandomChannelID();
        String initChannelID = disjointRunner.getRandomChannelID();
        Assertions.assertNotNull(initChannelID);
    }

    @Test
    public void setRandomChannelUploadsID_returnsAnUploadID_isNotNull() throws IOException {
        disjointRunner.setRandomChannelUploadsID("UC_x5XG1OV2P6uZZ5FSM9Ttw");
        Assertions.assertNotNull(disjointRunner.getRandomChannelUploadsID());
    }

    @Test
    public void setRandomChannelUploadsID_returnsTheCorrectUploadID_equalsIDTest() throws IOException {
        String IDTest = "UU_x5XG1OV2P6uZZ5FSM9Ttw";
        disjointRunner.setRandomChannelUploadsID("UC_x5XG1OV2P6uZZ5FSM9Ttw"); //GoogleDevelopersID
        Assertions.assertEquals(IDTest, disjointRunner.getRandomChannelUploadsID());
    }

    @Test
    public void getRandomChannelUploadsID_hasBeenSet_isNotEmpty() throws IOException {
        disjointRunner.setRandomChannelUploadsID("UC_x5XG1OV2P6uZZ5FSM9Ttw");
        Assertions.assertNotEquals("",disjointRunner.getRandomChannelUploadsID());
    }

    @Test
    public void getRandomChannelUploadsID_hasNotBeenSet_isEmpty(){
        Assertions.assertEquals("",disjointRunner.getRandomChannelUploadsID());
    }

    @Test
    public void setRandomChannelsUploadedPlayListItems_setsAListOfVideoIDs_isNotEmpty() throws IOException {
        ArrayList<String> emptyList = new ArrayList<>();
        disjointRunner.setRandomChannelUploadedPlayListItems("PLBCF2DAC6FFB574DE"); //googleDevelopers UploadsID
        Assertions.assertNotEquals(emptyList, disjointRunner.getRandomChannelsUploadedPlayListItems());
    }
    @Test
    public void setRandomChannelsUploadedPlayListItems_setsTheCorrectListOfVideoIDs_equalsTestList() throws IOException {
        ArrayList<String> testList = new ArrayList<String>(Arrays.asList("GvgqDSnpRQM","fIc_VEQ7Vo0","V4DDt30Aat4","07718Vcwcyc","XDgC4FMftpg","OE63BYWdqC4","RQbmXxU2dkg",
                "7nJdEXpvi1g", "7hakGJU9xco", "x9-F6dbCIHw", "gK7lUK0711E", "18TknKGC7tY", "CyRQJBBVI7g"));
        disjointRunner.setRandomChannelUploadedPlayListItems("PLBCF2DAC6FFB574DE"); //googleDevelopers UploadsID
        Assertions.assertEquals(testList, disjointRunner.getRandomChannelsUploadedPlayListItems());
    }

    @Test
    public void getRandomChannelsUploadedPlayListItems_hasNotBeenSet_isEmpty(){
        ArrayList<String> emptyList = new ArrayList<>();
        Assertions.assertEquals(emptyList, disjointRunner.getRandomChannelsUploadedPlayListItems());
    }

    //build a quesry for channels using that category and marked for kids - test the results are as expected

    //collect the results and store for comparison - test the array gets the results?

    //do another query for channels using the same category but not marked for kids - same test as before?

    //collect the results - same test as before?

    //compare the two collections to test for disjointness - test the comparison checking?

}
