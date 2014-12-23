/**
 * Created by Vladimir_Danilov on 22-Dec-14.
 */
public class SimpleRobot implements Robot {

    @RobotCommand("up")
    @Override
    public void up() {
        System.out.println("up");
    }

    @RobotCommand("down")
    @Override
    public void down() {
        System.out.println("down");
    }

    @RobotCommand("left")
    @Override
    public void left() {
        System.out.println("left");
    }

    @RobotCommand("right")
    @Override
    public void right() {
        System.out.println("right");
    }

    @RobotCommand("stop")
    @Override
    public void stop() {
        System.out.println("stop");
    }
}
