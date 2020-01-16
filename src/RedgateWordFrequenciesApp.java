import java.util.List;

public class RedgateWordFrequenciesApp {

    public static void main(String[] args) {
        WordFrequenciesFinder wordFrequenciesFinder = new WordFrequenciesFinder(new SimpleCharacterReader());
        List<Word> wordFrequencies = wordFrequenciesFinder.getSortedWordFrequencies();

        //Display the words
        System.out.println("......Regdate Word Frequencies App......\n");

        for(Word word: wordFrequencies)
            System.out.println(word);
        System.out.println(" \n @Ben-Malik TCHAMALAM");
    }
}
