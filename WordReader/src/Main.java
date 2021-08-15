import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        WordReader wordReader;
        try {
            wordReader = new WordReader("text2.txt");
        } catch (IOException e) {
            System.out.println("File not found, pls choose another one");
            return;
        }

       for(String word : wordReader) {
            System.out.println(word);
       }

    }
}
