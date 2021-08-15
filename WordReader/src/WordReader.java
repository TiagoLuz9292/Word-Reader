import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class WordReader implements Iterable<String>{

    private int currentWord;
    private BufferedReader bReader;
    private String[] words;

    public WordReader(String filePath) throws IOException {
        bReader = new BufferedReader(new FileReader(filePath));
        currentWord = 0;
        readLine();                                                  //Read first line.
    }

    public boolean readLine() throws IOException {

        String line = "";

        if((line = bReader.readLine()) == null) {
            bReader.close();
            return false;
        }

        line = line.trim();                                          //Remove spaces before and after line.
        if(line.isEmpty()) {                                         //If line is empty, read next line.
            return readLine();
        }

        line = line.replaceAll("\\p{Punct}", "");  //Remove al puctuation from the line.
        line = line.replaceAll("\\s+", " ");       //Transform multiple spaces between words, into one single space.
        words = line.split(" ");                              //Split line into words using space.

        return true;
    }

    @Override
    public Iterator<String> iterator() {

        return new Iterator<String>() {

            @Override
            public boolean hasNext() {

                if(currentWord < words.length) {
                    return true;
                }

                try {
                    if(readLine()) {                                    //After iterating all the line, read new line and reset currentWord to be used on next().
                        currentWord = 0;
                        return true;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return false;
            }

            @Override
            public String next() {
                return words[currentWord++];
            }
        };
    }
}
