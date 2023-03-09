import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class ExchangeRateParser {

    public ArrayList<String> readFxFile(String filePath) {

    //Open and read the file, breaking it into lines and removing blank lines
    ArrayList<String> fxFileLines = FileManager.readFileByName(filePath);
    fxFileLines.removeIf(lineValue -> Objects.equals(lineValue, ""));
    return fxFileLines;
}

    public HistoricalFxRates parseDatesRatesFromLines(ArrayList<String> fxLines) {

        //Look through the pairs and parse the data into pairs of dates and rates
        HistoricalFxRates fxHistory = new HistoricalFxRates();

        //Loop through the lines 2 at a time
        for (int linePair = 0; linePair < fxLines.size(); linePair += 2) {

            // Extract year and month from first line
            String yearString = fxLines.get(linePair).substring(40,44);
            String monthString = fxLines.get(linePair).substring(36,39);

            // Extract exchange rates from second line
            // First, split the string into an array with "Mmm ddrr.rr"
            String [] tokens = MyStringParser.parseStringIntoArray(fxLines.get(linePair+1),11);

            //Now, use the year and month from above along with the date and rate from the array to create array lists of date and associated rates
            for (String token : tokens) {
                try {
                    String dateString = monthString + " " + token.substring(4, 6) + ", " + yearString;
                    Date date = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH).parse(dateString);
                    Double rate = Double.parseDouble(token.substring(6, 11));
                    fxHistory.put(date, rate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }

        return fxHistory;
    }
}

