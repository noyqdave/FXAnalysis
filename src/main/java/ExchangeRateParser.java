import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExchangeRateParser {

    public ArrayList<String> readFxFile(String filePath) {

    //Open and read the file, breaking it into lines and removing blank lines
    ArrayList<String> fxFileLines = FileManager.readFileByName(filePath);
        // Iterator.remove()
        Iterator itr = fxFileLines.iterator();
        while (itr.hasNext())
        {
            String lineValue = (String)itr.next();
            if (Objects.equals(lineValue, ""))
                itr.remove();
        }
        return fxFileLines;
}
    //Look through the pairs and parse the data into pairs of dates and rates


    public HistoricalFxRates parseDatesRatesFromLines(ArrayList<String> fxLines) {

        HistoricalFxRates fxHistory = new HistoricalFxRates();
        ArrayList<Date> historicalDates = new ArrayList<>();
        ArrayList<Double> historicalRates = new ArrayList<>();

        //Loop through the lines 2 at a time
        for (int linePair = 0; linePair < fxLines.size(); linePair += 2) {

            // Extract year and month from first line
            String yearString = fxLines.get(linePair).substring(40,44);
            String monthString = fxLines.get(linePair).substring(36,39);

            // Extract exchange rates from second line
            // First, split the string into an array with "Mmm ddrr.rr"
            MyStringParser parser = new MyStringParser();
            String [] tokens = parser.parseStringIntoArray(fxLines.get(linePair+1),11);

            //Now, use the year and month from above along with the date and rate from the array to create array lists of date and associated rates
            for (int i = 0; i < tokens.length; i ++) {
                try {
                    String dateString = monthString + " " + tokens[i].substring(4, 6) + ", " + yearString;
                    historicalRates.add(Double.parseDouble(tokens[i].substring(6, 11)));
                    Date date = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH).parse(dateString);
                    historicalDates.add(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }

        fxHistory.setHistoricalDates(historicalDates);
        fxHistory.setHistoricalRates(historicalRates);
        return fxHistory;
    }
}
