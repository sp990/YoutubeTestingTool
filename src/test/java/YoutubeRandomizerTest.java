import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class YoutubeRandomizerTest {

    @Test
    public void getRandomChannel_CallMethodTwice_RetrieveDifferentChannelID() {
        //Retrieve first channelID
        String channelID1 = YoutubeRandomizer.getRandomChannelID();
        //Retrieve second channelID
        String channelID2 = YoutubeRandomizer.getRandomChannelID();

        //Compare channelIDs
        Assertions.assertNotEquals(channelID1, channelID2);
    }

    @Test
    public void getRandomDictionaryWord_CallMethodTwice_RetrieveDifferentWords() {
        String randomWord1 = YoutubeRandomizer.getRandomDictionaryWord();

        String randomWord2 = YoutubeRandomizer.getRandomDictionaryWord();

        System.out.println("String1: " + randomWord1 + " String2: " + randomWord2);

        Assertions.assertNotEquals(randomWord1, randomWord2);
    }
}
