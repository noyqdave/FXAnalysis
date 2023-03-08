public class MyStringParser {

    public static String[] parseStringIntoArray(String str, int delimiterLength) {
        if (str.length() == 0) {
            return new String[0];
        } else {
            String[] remaining = parseStringIntoArray(str.substring(delimiterLength), delimiterLength);
            String[] result = new String[remaining.length + 1];
            result[0] = str.substring(0, delimiterLength);
            System.arraycopy(remaining, 0, result, 1, remaining.length);
            return result;
        }
    }
}
