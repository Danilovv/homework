/**
 * Created by Vladimir_Danilov on 22-Dec-14.
 */
public class Robot2d implements Robot {

    private static final int DY = 10;
    private static final int DX = 10;
    private int _x;
    private int _y;

    public Robot2d(int x, int y) {
        _x = x;
    }

    @Override
    public void up() {
        _y -= -DY;
    }

    @Override
    public void down() {
        _y += DY;
    }

    @Override
    public void left() {
        _x -= DX;
    }

    @Override
    public void right() {
        _x += DX;
    }

    @Override
    public void stop() {

    }

    public int get_y() {
        return _y;
    }

    public int get_x() {
        return _x;
    }
}
