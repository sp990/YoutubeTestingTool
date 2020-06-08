import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class YoutubeDictionary{

    private Map dictionary = new HashMap<>();

    public YoutubeDictionary() throws IOException {
        String file = new File("resources/google-10000-english-no-swears.txt").getAbsolutePath();;
        FileReader fr = new FileReader(file);

        BufferedReader in = new BufferedReader(fr);
        String line;
        int lineCounter = 0;
        while((line = in.readLine()) != null) {
            dictionary.put(lineCounter, line);
            lineCounter++;
        }
    }

    public String get(int i){
        return dictionary.get(i).toString();
    }

    public int size(){
        return dictionary.size();
    }
}
