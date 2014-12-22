
import javax.swing.*;
import java.awt.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Created by Vladimir_Danilov on 22-Dec-14.
 */
public class RobotControlPanel {




    public static void main(String[] args) {
        SimpleRobot robot = new SimpleRobot();
        Robot2d r2d2 = new Robot2d(200, 200);
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.add(robot);
        dispatcher.add(r2d2);

        ControlCenter center = new ControlCenter(dispatcher);
        new Thread(center).start();
        startKeyBoardInterface(center);
        startGraphics(r2d2);
    }

    private static void startGraphics(Robot2d robot) {
        JFrame frame = new JFrame("Robot control");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        robot.setPreferredSize(new Dimension(600, 600));
        frame.add(robot);
        frame.pack();
        frame.setVisible(true);

        while (true) {
            robot.repaint();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void startKeyBoardInterface(final CommandListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try(Scanner scanner = new Scanner(System.in)) {
                    while (scanner.hasNextLine()) {
                        String command = scanner.nextLine();
                        System.out.println(command);
                        listener.command(command);
                    }
                }
            }
        }).start();
    }
}