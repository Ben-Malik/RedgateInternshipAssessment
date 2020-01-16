import java.util.Comparator;
/**
 * A Word class to store words of the very string and their occurrences
 *
 * @author Ben-Malik TCHAMALAM
 */
public class Word implements Comparable<Word>{

    //the string of the word itself.
    private String str;
    //the number of times the word occurs in the text.
    private Integer occurrence;

    public Word(String str, Integer occurrence){
        this.str = str;
        this.occurrence = occurrence;
    }

    public Word(String str) {
        this.str = str;
        this.occurrence = 1;
    }

    public String getStr() { return str; }

    public void setStr(String str) {
        if (str == null)
            throw new IllegalArgumentException("Invalid argument for word");
        this.str = str;
    }

    public Integer getOccurrence() { return occurrence; }

    public void setOccurrence(Integer occurrence) {
        if (occurrence.compareTo(0) < 0 || occurrence.compareTo(0) == 0)
            throw new IllegalArgumentException("The occurrence of a word should be larger or equal to 1");
        this.occurrence = occurrence;
    }

    public void incrementOccurrence() { occurrence++; }

    public String toString() {
        return str + " - " + occurrence;
    }

    @Override
    public int compareTo(Word word) {
        int comparedOccurrence = word.getOccurrence();

        return this.occurrence - comparedOccurrence;
    }

    @Override
    public boolean equals(Object other) {
        Word otherWord = (Word)other;
        return otherWord.str.equals(str) && otherWord.occurrence.equals(occurrence)
                && other.getClass().equals(Word.class);
    }

    /**
     * Compares two Words.
     */
    public static Comparator<Word> WordComparator = (o1, o2) -> {
        String word1 = o1.getStr().toLowerCase();
        String word2 = o2.getStr().toLowerCase();

        return word1.compareTo(word2);
    };
}
