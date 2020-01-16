import org.junit.Before;
import org.junit.Test;
import java.util.*;
import static org.junit.Assert.*;

/**
 * @author Ben-Malik TCHAMALAM
 * A test class for ensuring the WorkFrequencyFinder class works without any flaw.
 */
public class WordFrequenciesFinderTest {

    @Test
    @Before
    public void ensureThereIsNoDuplicateWordInTheOutputList() {
        WordFrequenciesFinder wordFrequenciesFinder = new WordFrequenciesFinder(new SimpleCharacterReader());
        List<Word> output = wordFrequenciesFinder.getSortedWordFrequencies();

        Set<Word> outputAsSet = new HashSet<>(output);

        assertEquals(output.size(), outputAsSet.size());
    }

    @Test
    public void testGetSortedWordFrequencies() {

        WordFrequenciesFinder wordFrequenciesFinder = new WordFrequenciesFinder(new SimpleCharacterReader());
        List<Word> output = wordFrequenciesFinder.getSortedWordFrequencies();

        assertTrue(output.size() != 0);
    }

    @Test
    public void ensureWordOccurrenceIsOneWhenTheWordIsAddedToTheListForTheFirstTime() {
        WordFrequenciesFinder wordFrequenciesFinder = new WordFrequenciesFinder(new SimpleCharacterReader());

        List<Word> wordList = new ArrayList<>();
        Word word = new Word("England");
        wordFrequenciesFinder.addWordToList(word, wordList);

        assertEquals(wordList.get(0).getOccurrence(), new Integer(1));
    }

    @Test
    public void testWordOccurrenceIsTwoWhenTheWordIsBeingAddedToTheListForTheSecondTime() {
        WordFrequenciesFinder wordFrequenciesFinder = new WordFrequenciesFinder(new SimpleCharacterReader());

        List<Word> wordList = new ArrayList<>();
        Word word = new Word("Cambridge");
        wordFrequenciesFinder.addWordToList(word, wordList);
        wordFrequenciesFinder.addWordToList(word, wordList);

        assertEquals(1, wordList.size());

        assertEquals(2, (int) wordList.get(0).getOccurrence());
    }

    @Test
    public void testWordsAreSortedIfTheyHaveTheSameNumberOfOccurrences() {
        Word[] consWords = getTwoConsecutiveWordsWithIdenticalOccurrenceNumber();

        if (consWords != null) {
            assertTrue(Word.WordComparator.compare(consWords[0], consWords[1]) < 0);
        } else {
            System.out.println("No words found with the same occurrence number!");
        }
    }

    private Word[] getTwoConsecutiveWordsWithIdenticalOccurrenceNumber() {
        WordFrequenciesFinder wordFrequenciesFinder = new WordFrequenciesFinder(new SimpleCharacterReader());
        List<Word> output = wordFrequenciesFinder.getSortedWordFrequencies();

        Word[] consWords = new Word[2];

        Word word1 = null;
        Word word2 = null;
        boolean consecutiveWordFound = false;
        for (int i = 0; i < output.size(); i++) {
            word1 = output.get(i);
            word2 = output.get(i+1);
            if(word1.getOccurrence().equals(word2.getOccurrence())) {
                consecutiveWordFound = true;
                break;
            }
        }
        if (consecutiveWordFound) {
            consWords[0] = word1;
            consWords[1] = word2;
            return consWords;
        }

        return null;
    }
}
