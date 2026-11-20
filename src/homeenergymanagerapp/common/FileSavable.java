package common;

import java.io.IOException;

public interface FileSavable {
    void saveToFile() throws IOException;
    void loadFromFile() throws IOException;
}
