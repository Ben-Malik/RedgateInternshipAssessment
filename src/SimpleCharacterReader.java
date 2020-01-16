import java.io.EOFException;
import java.util.Random;

public class SimpleCharacterReader implements ICharacterReader {
    private int m_Pos = 0;

    public static final char lf = '\n';

    private String m_Content = "It was the best of times, it was the worst of times," + 
    lf +
    "it was the age of wisdom, it was the age of foolishness," + 
    lf +
    "it was the epoch of belief, it was the epoch of incredulity," + 
    lf +
    "it was the season of Light, it was the season of Darkness," + 
    lf +
    "it was the spring of hope, it was the winter of despair," + 
    lf +
    "we had everything before us, we had nothing before us," +
    lf +
    "we were all going direct to Heaven, we were all going direct" + 
    lf +
    "the other way--in short, the period was so far like the present" + 
    lf +
    "period, that some of its noisiest authorities insisted on its" + 
    lf +
    "being received, for good or for evil, in the superlative degree" + 
    lf +
    "of comparison only." + 
    lf +
    "There were a king with a large jaw and a queen with a plain face," + 
    lf +
    "on the throne of England; there were a king with a large jaw and" + 
    lf +
    "a queen with a fair face, on the throne of France.  In both" + 
    lf +
    "countries it was clearer than crystal to the lords of the State" + 
    lf +
    "preserves of loaves and fishes, that things in general were" + 
    lf +
    "settled for ever";

    Random m_Rnd = new Random();

    public char GetNextChar() throws EOFException {

        if (m_Pos >= m_Content.length()) {
            throw new EOFException();
        }

        return m_Content.charAt(m_Pos++);

    }

    public void Dispose() {
        // Do nothing
    }
}
