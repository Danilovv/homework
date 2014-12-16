/*
 *  28-11-2014
 *  Vladimir Danilov
 *  
 *  UTF-8
 */



import java.applet.Applet;
import java.awt.*;

public class Main extends Applet implements Runnable {
    private Image image = null;
    private Data data = null;
    private Thread maintThread;
    private boolean KeyPressed = false;
    private int keyCode;

    @Override
    public void init() {
        data = new Data(image.getColorsAmount());
        image = new Image(getGraphics(), data.getFieldHeight(), data.getFieldWidth(), data.getBlockSideSize());
        keyCode = 01;
    }

    @Override
    public boolean keyDown(Event e, int k) {
        KeyPressed = true;
        keyCode = k;
        return true;
    }

    @Override
    public boolean lostFocus(Event e, Object w) {
        KeyPressed = true;
        keyCode = 'P';
        return true;
    }

    @Override
    public void paint(Graphics graphics) {
        image.paint(data.getListOfBlocks(), data.getInfo());
    }

    @Override
    public void start() {
        maintThread = new Thread(this);
        maintThread.start();
    }

    @Override
    public void run() {
        requestFocus();
        int vec;
        do {
            if(!data.createFigure()) { // Если не удается создать фигуру, то производится сброс
                stop();
                init();
                start();
                break;
            }
            data.trimBlocks();
            if (data.checkIfBlocksToTrim()) {                       // Удаление рядов
                image.trimBlocks(data.getListOfBlocks(), data.getListOfBlocksToTrim(), 120);
                data.clearTrimmedBlocks();
            }
            do {
                vec = 0;
                if (KeyPressed) {
                    KeyPressed = false;

                    /*
                     * vec = 0 - вниз, 1 - прыжок вниз, 2 - сдвиг цвета вверх,
                     * 3 - сдвиг цвета вниз , 4 - влево, 5 - вправо
                     */
                    switch (keyCode) {
                        case Event.LEFT:
                            vec = 4;
                            break;
                        case Event.RIGHT:
                            vec = 5;
                            break;
                        case Event.UP:
                            vec = 2;
                            break;
                        case Event.DOWN:
                            vec = 3;
                            break;
                        case ' ':
                            vec = 1;
                            break;
                        case 'p':
                        case 'P':
                            while (!KeyPressed) {
                                image.pause(data.getListOfBlocks());
                            }
                            KeyPressed = false;
                            break;
                        case '-':
                            data.downLevel();
                            break;
                        case '+':
                            data.upLevel();
                            break;
                        default:
                            break;
                    }
                }
                if (!data.action(vec)) {                                // Если движение вниз не выполнить, цикл прерывается
                    break;
                }
                image.paint(data.getListOfBlocks(), data.getInfo());    // Повторная отрисовка
                delay(data.getDelay());
            } while (true);
        } while (true);
    }

    private void delay(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {}
    }
}

/*
 * Объекты на базе этого класса хранят координаты блоков и их цвета
 */
class Pos {
    int x = 0;
    int y = 0;
    int color = 0;

    Pos(int x, int y, int color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }
}
