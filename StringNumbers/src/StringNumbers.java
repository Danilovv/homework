import java.util.HashMap;
import java.util.Map;

/**
 * Created by vladimir_danilov on 13-Jan-15.
 */
public class StringNumbers {

    public static void main(String[] args) {
        int i=0;
        for (String word : words.values()) {
            System.out.println(i++ + word);
        }
    }

    static final Map<Integer, String> words = new HashMap<>();

    static {
        words.put(0, "ноль");
        words.put(1, "один");
        words.put(2, "два");
        words.put(3, "три");
        words.put(4, "четыре");
        words.put(5, "пять");
        words.put(6, "шесть");
        words.put(7, "семь");
        words.put(8, "восемь");
        words.put(9, "девять");
        words.put(10, "десять");
        words.put(11, "одинадцать");
        words.put(12, "двенадцать");
        words.put(13, "тринадцать");

        words.put(20, "двадцать");
        words.put(30, "тридцать");
        words.put(40, "сорок");
        words.put(90, "девяносто");

        int unit = 0;
        int number = 10;
        while(number < 100) {
            if(words.get(number) == null) {

                String current = convert(unit);
                int currentDecade = Math.round(number / 10);

                if (number > 10 && number < 20) {
                    current = current.substring(0, current.length() - 1);
                    current += "надцать";
                } else {

                    if (number % 10 == 0) {
                        current = convert(number / 10) + "десят";
                    } else {
                        current = convert(currentDecade * 10) + " " + convert(unit);
                    }

                }

                words.put(number, current);
            }

            number++;
            unit++;
            if (unit == 10) unit = 0;
        }
    }

    public static String convert(int number) {
        return words.get(number);
    }
}
