import java.util.Scanner;

/**
 * Created by Vladimir_Danilov on 22-Dec-14.
 */
public class RobotControlPanel {

    public static class Dispatcher implements CommandListener {
        private Robot _robot;
        Dispatcher(Robot robot) {
            _robot = robot;
        }

        @Override
        public void command(String command) {
            System.out.println("dispatch " + command);
        }
    }


    public static void main(String[] args) {
        SimpleRobot robot = new SimpleRobot();
        Dispatcher dispatcher = new Dispatcher(robot);
        startKeyBoardInterface(dispatcher);
    }

    private static void startKeyBoardInterface(final CommandListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Scanner scanner = new Scanner(System.in);
                while(scanner.hasNextLine()) {
                    String command = scanner.nextLine();
                    System.out.println(command);
                    listener.command(command);
                }
            }
        }).start();
    }
}