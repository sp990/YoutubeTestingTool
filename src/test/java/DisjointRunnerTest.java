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
        String testChannelId = "UC_x5XG1OV2P6uZZ5FSM9Ttw";//Google Developers
        disjointRunner.setRandomChannelUploadsID(testChannelId);
        Assertions.assertNotNull(disjointRunner.getRandomChannelUploadsID());
    }

    @Test
    public void setRandomChannelUploadsID_returnsTheCorrectUploadID_equalsIDTest() throws IOException {
        String testChannelID = "UC_x5XG1OV2P6uZZ5FSM9Ttw";//GoogleDevelopersID
        String uploadIDTest = "UU_x5XG1OV2P6uZZ5FSM9Ttw";//retrieved from API GoogleDevelopers UploadID
        disjointRunner.setRandomChannelUploadsID(testChannelID);
        Assertions.assertEquals(uploadIDTest, disjointRunner.getRandomChannelUploadsID());
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

    @Test
    public void comparePlaylistsVideoIdsForDisjointness_playListsAreDifferent_returnTrue(){
        ArrayList<String> testList1 = new ArrayList<String>(Arrays.asList("GvgqDSnpRQM","fIc_VEQ7Vo0","V4DDt30Aat4","07718Vcwcyc","XDgC4FMftpg","RQbmXxU2dkg",
                "7nJdEXpvi1g", "7hakGJU9xco", "x9-F6dbCIHw", "gK7lUK0711E", "18TknKGC7tY", "CyRQJBBVI7g"));
        ArrayList<String> testList2 = new ArrayList<String>(Arrays.asList("onW8gbbDpdQ","FkiHjuZ2gv8","nq7_ZYJPWf0","QWdYWwW6OAE","eukOuR4vqjg","YL8amQAXD2A",
                "gx7vUyl-HXs", "0kYIZE8Gl90", "dd8H4fiL9Yc", "PCgLmzkRM38", "j-35y1M9rRU", "Y4C-6anCbmk"));

        Assertions.assertTrue(disjointRunner.comparePlaylistsVideoIdsForDisjointness(testList1,testList2));
    }

    @Test
    public void comparePlaylistsVideoIdsForDisjointness_playListsAreTheSame_returnFalse(){
        ArrayList<String> testList1 = new ArrayList<String>(Arrays.asList("GvgqDSnpRQM","fIc_VEQ7Vo0","V4DDt30Aat4","07718Vcwcyc","XDgC4FMftpg","OE63BYWdqC4","RQbmXxU2dkg",
                "7nJdEXpvi1g", "7hakGJU9xco", "x9-F6dbCIHw", "gK7lUK0711E", "18TknKGC7tY", "CyRQJBBVI7g"));
        ArrayList<String> testList2 = new ArrayList<String>(Arrays.asList("GvgqDSnpRQM","fIc_VEQ7Vo0","V4DDt30Aat4","07718Vcwcyc","XDgC4FMftpg","OE63BYWdqC4","RQbmXxU2dkg",
                "7nJdEXpvi1g", "7hakGJU9xco", "x9-F6dbCIHw", "gK7lUK0711E", "18TknKGC7tY", "CyRQJBBVI7g"));

        Assertions.assertFalse(disjointRunner.comparePlaylistsVideoIdsForDisjointness(testList1,testList2));
    }



    @Test
    public void runTests_setsUpAndRunsPassingTests_returnsTrue() throws IOException {
        disjointRunner.runTest();
        Assertions.assertTrue(disjointRunner.result);
    }
}
