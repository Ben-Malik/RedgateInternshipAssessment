import java.io.EOFException;

public interface ICharacterReader {

    char GetNextChar() throws EOFException;

    void Dispose();
}