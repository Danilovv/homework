import java.util.Scanner;

/**
 * Created by Vladimir_Danilov on 22-Dec-14.
 */
public class RobotControlPanel {
    public static void main(String[] args) {
        SimpleRobot robot = new SimpleRobot();
        startKeyBoardInterface();
    }

    private static void startKeyBoardInterface() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Scanner scanner = new Scanner(System.in);
                while(scanner.hasNextLine()) {
                    String command = scanner.nextLine();
                    System.out.println(command);
                }
            }
        }).start();
    }
}
