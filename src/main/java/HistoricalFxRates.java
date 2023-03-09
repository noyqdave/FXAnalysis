import java.util.Date;
import java.util.HashMap;

public class HistoricalFxRates extends HashMap<Date, Double> {

    public Double findRateforDate(Date selectedDate) {
        Double foundRate = 0.0;
        if(this.containsKey(selectedDate)) {
            foundRate = get(selectedDate);
        }
        return foundRate;
    }

}
