import javax.swing.*;
import java.awt.*;

/**
 * Created by Vladimir_Danilov on 22-Dec-14.
 */
public class Robot2d extends JPanel implements Robot {

    private static final int DY = 10;
    private static final int DX = 10;
    private int _x;
    private int _y;

    public Robot2d(int x, int y) {
        _x = x;
        _y = x;
    }

    @RobotCommand("up")
    @Override
    public void up() {
        _y -= +DY;
    }

    @RobotCommand("down")
    @Override
    public void down() {
        _y += DY;
    }

    @RobotCommand("left")
    @Override
    public void left() {
        _x -= DX;
    }

    @RobotCommand("right")
    @Override
    public void right() {
        _x += DX;
    }

    @RobotCommand("stop")
    @Override
    public void stop() {

    }

    public int get_y() {
        return _y;
    }

    public int get_x() {
        return _x;
    }

    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.fillOval(_x, _y, 30, 30);
    }
}
