import javafx.util.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

class ExchangeRateParserTest {

    @Test
    void readFileAndParseResults() {
        //Read the file
        String testFilePath = "/Users/Testing/CADTHBFXRates.txt";
        ExchangeRateParser fxParser = new ExchangeRateParser();
        ArrayList<String> actualFxLines = fxParser.readFxFile(testFilePath);
        List<Pair<Date, Double>> expectedRatesForDates = new ArrayList<Pair<Date, Double>>();

        //Parse the lines
        HistoricalFxRates actualResult = fxParser.parseDatesRatesFromLines(actualFxLines);

        //Sample a couple of dates to see if the rates are correct
        try {
            Date date = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH).parse("Dec 27, 2019");
            Assertions.assertEquals(23.05, actualResult.findRateforDate(date));

            date = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH).parse("Nov 28, 2019");
            Assertions.assertEquals(22.76, actualResult.findRateforDate(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Test
    void readFxFile() {
        //Access the test file (which already exists) and set up the expected data
        String testFilePath = "/Users/Testing/CADTHBFXRates.txt";
        ArrayList<String> expectedFxLines = new ArrayList<>();
        expectedFxLines.add("CAD/THB (Canadian dollar/Thai baht) Dec 2019 (12.2019) exchange rate history");
        expectedFxLines.add("Dec 3122.93Dec 3022.93Dec 2723.05Dec 2422.92Dec 2322.93Dec 2022.94Dec 1923.01Dec 1823.03Dec 1722.98Dec 1623.00Dec 1322.92Dec 1222.88Dec 1122.92Dec 1022.90Dec 0922.90Dec 0622.90Dec 0523.05Dec 0422.92Dec 0322.76Dec 0222.77");
        expectedFxLines.add("CAD/THB (Canadian dollar/Thai baht) Nov 2019 (11.2019) exchange rate history");
        expectedFxLines.add("Nov 2922.74Nov 2822.76Nov 2722.77Nov 2622.75Nov 2522.71Nov 2222.73Nov 2122.74Nov 2022.69Nov 1922.81Nov 1822.86Nov 1522.87Nov 1422.78Nov 1322.82Nov 1222.94Nov 0822.97Nov 0723.08Nov 0622.99Nov 0522.99Nov 0422.97Nov 0122.94");

        ExchangeRateParser fxParser = new ExchangeRateParser();
        ArrayList<String> actualFxLines = fxParser.readFxFile(testFilePath);
        Assertions.assertEquals(expectedFxLines, actualFxLines);
    }

    @Test
    void testParsingToArrayLists() {
        // Set up the expected result and the input strings
        HistoricalFxRates expectedRatesForDates = new HistoricalFxRates();
        setUpExpectedData(expectedRatesForDates);

        ArrayList<String> inputLines = new ArrayList<>();
        setUpInputData(inputLines);

        // Create the parser and get the result
        ExchangeRateParser fxParser = new ExchangeRateParser();
        HistoricalFxRates actualFxHistory = fxParser.parseDatesRatesFromLines(inputLines);

        Assertions.assertEquals(expectedRatesForDates,actualFxHistory);
    }

    private void setUpInputData(ArrayList<String> inputLines) {
        inputLines.add("CAD/THB (Canadian dollar/Thai baht) Dec 2019 (12.2019) exchange rate history");
        inputLines.add("Dec 3122.93Dec 3022.93Dec 2723.05");
        inputLines.add("CAD/THB (Canadian dollar/Thai baht) Nov 2019 (11.2019) exchange rate history");
        inputLines.add("Nov 2922.74Nov 2822.76Nov 2722.77");
    }

    private void setUpExpectedData(HistoricalFxRates expectedRatesForDates) {
        try {
            Date date = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH).parse("Dec 31, 2019");
            Double rate = 22.93;
            expectedRatesForDates.put(date, rate);
            date = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH).parse("Dec 30, 2019");
            rate = 22.93;
            expectedRatesForDates.put(date, rate);
            date = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH).parse("Dec 27, 2019");
            rate = 23.05;
            expectedRatesForDates.put(date, rate);
            date = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH).parse("Nov 29, 2019");
            rate = 22.74;
            expectedRatesForDates.put(date, rate);
            date = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH).parse("Nov 28, 2019");
            rate = 22.76;
            expectedRatesForDates.put(date, rate);
            date = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH).parse("Nov 27, 2019");
            rate = 22.77;
            expectedRatesForDates.put(date, rate);
        } catch  (ParseException e) {
          e.printStackTrace();
        }
    }
}