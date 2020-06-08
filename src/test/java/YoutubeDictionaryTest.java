import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class YoutubeDictionaryTest {

    @Test
    public void constructor_StartLoadFromFile_NoIOException() {
        try{
            YoutubeDictionary youtubeDictionary = new YoutubeDictionary();
        }
        catch (IOException e) {
            e.printStackTrace();
            Assertions.fail("Exception was thrown");
        }
    }

    @Test
    public void constructor_LoadAllWordsFromFile_DictionarySize9894() {
        try{
            YoutubeDictionary youtubeDictionary = new YoutubeDictionary();
            //There are 9894 words in the file
            Assertions.assertEquals(9894, youtubeDictionary.size());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
