import java.util.Scanner;

/**
 * Created by Vladimir_Danilov on 25-Dec-14.
 */
public class Utils {

    static final Scanner scanner = new Scanner(System.in);

    static String waitForUserInput() {
        while(!scanner.hasNextLine()) {}
        return scanner.nextLine();
    }

    public static void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
