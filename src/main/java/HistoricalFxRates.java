import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class HistoricalFxRates extends HashMap {
    // TODO: No need to use hashmap, just have member variables
//    ArrayList<Date> historicalDates;
//    ArrayList<Double> historicalRates;

    public ArrayList<Date> getHistoricalDates() {
        ArrayList<Date> dates = (ArrayList<Date>) this.get("historicalDates");
        return dates;
    }

    public void setHistoricalDates(ArrayList<Date> historicalDates) {
        this.put("historicalDates", historicalDates);
        //this.historicalDates = historicalDates;
    }

    public ArrayList<Double> getHistoricalRates() {
        ArrayList<Double> rates = (ArrayList<Double>) this.get("historicalRates");
        return rates;
    }

    public void setHistoricalRates(ArrayList<Double> historicalRates) {
        this.put("historicalRates", historicalRates);
        //this.historicalRates = historicalRates;
    }

    public Double findRateForDate(Date selectedDate) {

        ArrayList<Date> allDates = getHistoricalDates();
        Double selectedRate = new Double(0.0);
        if (allDates.contains(selectedDate)) {
            int position = allDates.indexOf(selectedDate);
            ArrayList<Double> allRates = getHistoricalRates();
            selectedRate = allRates.get(position);
            return selectedRate;
        } else {
            System.out.println("Date not found");
        }

        return selectedRate;

    }

}
