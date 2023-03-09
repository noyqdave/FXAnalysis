import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

class FileManagerTest {
    String testFilePath = "/Users/Testing/";
    ArrayList<String> testLines = new ArrayList<>();
    static File testFile;

    @BeforeEach
    void setUp() {
        //Create test file
        testFile = FileManager.createFile(testFilePath,"TestFile");
    }

    @Test
    void readOneLine() {
        //Write to the file
        testLines.add("test line 1");
        FileManager.writeToFile(testFile, testLines);

        //Read from the file
        ArrayList<String> readLines = FileManager.readFileObject(testFile);
        Assertions.assertEquals(testLines.get(0), readLines.get(0));
    }

    @Test
    void readAllLines() {
        //Write to the file
        testLines.add("test line 1");
        testLines.add("test line 2");
        FileManager.writeToFile(testFile, testLines);

        //Read from the file
        ArrayList<String> readLines = FileManager.readFileObject(testFile);

        // Check that what was read is what was written
        Assertions.assertEquals(testLines, readLines);
    }

    @AfterEach
    void tearDown() {
        //Delete test file
        boolean success = testFile.delete();
    }

}