/**
 * Created by Vladimir_Danilov on 22-Dec-14.
 */
public interface Robot {
    @RobotCommand("up")
    void up();
    @RobotCommand("down")
    void down();
    @RobotCommand("left")
    void left();
    @RobotCommand("right")
    void right();
    @RobotCommand("stop")
    void stop();
}
