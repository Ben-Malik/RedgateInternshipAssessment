import java.io.EOFException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * A client class making use of the Character reader interface to compute the frequencies of the words in
 * a string.
 * @author Ben-Malik TCHAMALAM
 */
public class WordFrequenciesFinder {
    private ICharacterReader iCharacterReader;
    private List<Word>  sortedWords;

    public WordFrequenciesFinder(ICharacterReader iCharacterReader) {
        this.iCharacterReader = iCharacterReader;
        sortedWords = new ArrayList<>();
    }

    /**
     * The method first accumulates the characters as words, then adds them to the wordFrequencies list.
     *
     * @return A List of words from the String in the SimpleCharacterReader alongside with their occurrences.
     */
    public List<Word> getSortedWordFrequencies() {
        List<Word> wordFrequencies = new ArrayList<>();
        //This will loop as long as the EOFException is not thrown
        while(true) {
            try {
                Word word = charactersToWordConverter();
                if (word.getStr().length() != 0)
                    addWordToList(word, wordFrequencies);
            } catch (EOFException e) { // the end of the string content is reached.
                orderWordsByWordCount(wordFrequencies);
                return sortedWords;
            }
        }
    }

    /**
     * A method to make use of the ICharacterReader interface and convert the characters into word
     * @throws EOFException When Character reader reaches the end of the content.
     */
    private Word charactersToWordConverter() throws EOFException {
        char currentChar = iCharacterReader.GetNextChar();
        StringBuilder currentWord = new StringBuilder();
        String punctuation = " ;,.!?(){}\n";
        while (!punctuation.contains(Character.toString(currentChar))) {
            currentWord.append(currentChar);
            currentChar = iCharacterReader.GetNextChar();
        }
        return new Word(currentWord.toString());
    }

    /**
     * Orders the words having the same number of occurrences alphabetically
     * @param wordFrequencies The list of words to be sorted.
     */
    private void orderWordsByWordCount(List<Word> wordFrequencies) {
        if (wordFrequencies == null || wordFrequencies.size() == 0) {
            throw new IllegalArgumentException("Empty list of words!");
        }

        //Occurrences are stored in a hash set, to be able to iterate in the wordFrequencies according to occurrence.
        //LinkedHashSet is used in order to maintain the insertion order unbroken.
        LinkedHashSet<Integer> occurrences = new LinkedHashSet<>();
        for (Word word: wordFrequencies) {
            occurrences.add(word.getOccurrence());
        }

        //Take all words with the same occurrence number and sort them.
        for (Integer occurrence: occurrences) {
            List<Word> wordsWithOccurrence = getWordsGivenOccurrence(occurrence, wordFrequencies);
            if (!wordsWithOccurrence.isEmpty()) {
                wordsWithOccurrence.sort(Word.WordComparator);
                sortedWords.addAll(wordsWithOccurrence);
            }
        }

    }

    /**
     * A helper method that get the words having a specific occurrence in a given list of word frequencies.
     * @param occurrence The value whose words are to be sought.
     * @param wordFrequencies The list of words to iterate.
     * @return A list of words having the given occurrence.
     */
    private List<Word> getWordsGivenOccurrence(Integer occurrence, List<Word> wordFrequencies) {

        List<Word> temp = new ArrayList<>();
        for (Word word: wordFrequencies) {
            if (occurrence.equals(word.getOccurrence())) {
                temp.add(word);
            }
        }
        return temp;
    }

    /**
     * Adds a new word into the given parameter list.
     * In case the given word was already in the list, increments the occurrence of the word already in the list by one.
     * @param newWord The new words to be added.
     * @param wordFrequencies The list to which the new word is to be added.
     */
    public void addWordToList(Word newWord, List<Word> wordFrequencies) {
        boolean isWordFound = false;

        for (Word word : wordFrequencies) {
            if (Word.WordComparator.compare(newWord, word) == 0) {
                word.incrementOccurrence();
                isWordFound = true;
                break;
            }
        }

        if (!isWordFound)
            wordFrequencies.add(newWord);
    }

} //end WordFrequencyFinder
