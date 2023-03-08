import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MyStringParserTest {

    @Test
     void testOneValue() {
        MyStringParser parser = new MyStringParser();
        String testString = "Nov 2922.74";
        String [] expectedStrings = {"Nov 2922.74"};
        Assertions.assertEquals(expectedStrings, parser.parseStringIntoArray(testString,11));
    }

    @Test
    void testEmptyLine() {
        MyStringParser parser = new MyStringParser();
        String testString = "";
        String [] actualStrings = parser.parseStringIntoArray(testString,1);
        Assertions.assertEquals(0, actualStrings.length);
    }

    @Test
    void testLineOfValues() {
        MyStringParser parser = new MyStringParser();
        String testString = "Nov 2922.74Nov 2822.76Nov 2722.77Nov 2622.75Nov 2522.71Nov 2222.73Nov 2122.74Nov 2022.69Nov 1922.81Nov 1822.86Nov 1522.87Nov 1422.78Nov 1322.82Nov 1222.94Nov 0822.97Nov 0723.08Nov 0622.99Nov 0522.99Nov 0422.97Nov 0122.94";
        String [] expectedStrings = {"Nov 2922.74","Nov 2822.76","Nov 2722.77",
                "Nov 2622.75","Nov 2522.71","Nov 2222.73","Nov 2122.74","Nov 2022.69",
                "Nov 1922.81","Nov 1822.86","Nov 1522.87","Nov 1422.78","Nov 1322.82",
                "Nov 1222.94","Nov 0822.97","Nov 0723.08","Nov 0622.99","Nov 0522.99",
                "Nov 0422.97","Nov 0122.94"};
        Assertions.assertArrayEquals(expectedStrings, parser.parseStringIntoArray(testString,11));
    }
}