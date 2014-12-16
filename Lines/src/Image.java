/*
 *  28-11-2014
 *  Vladimir Danilov
 *  
 *  UTF-8
 */



import java.awt.*;
import java.util.ArrayList;

public class Image {
    private static final Color myColors[] = {Color.cyan, Color.blue, Color.red,
            Color.green, Color.yellow, Color.pink, Color.magenta};
    private Graphics canvas;
    private int leftBorder;         // Отступ слева.
    private int topBorder;          // Отступ сверху.
    private int blockSideSize;      // Размер стороны блока.
    private int canvasHeight;       // Высота поля.
    private int canvasWidth;        // Ширина поля.

    Image(Graphics graphics, int fieldHeight, int fieldWidth, int blockSideSize) {
        this.canvas = graphics;
        this.leftBorder = 30;
        this.topBorder = 30;
        this.blockSideSize = blockSideSize;
        this.canvasHeight = blockSideSize * fieldHeight;
        this.canvasWidth = blockSideSize * fieldWidth;
    }

    /*
     * Возвращает количество цветов в массиве myColors
     */
    public static int getColorsAmount() {
        return myColors.length;
    }

    private void delay(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
        }
    }

    private void paintBackground() {
        canvas.fillRect(leftBorder, topBorder, canvasWidth + 2, canvasHeight + 2);
    }

    /*
     * Создает мерцание блоков, который были собраны в ряд и скоро будут удалены
     */
    public void trimBlocks(ArrayList<Pos> l, ArrayList<Integer> index, long delay) {
        for (int i = 0; i < index.size(); i++) {
            delay(delay);
            drawBlock(l.get(index.get(i)).x, l.get(index.get(i)).y, -1);
            delay(delay);
            drawBlock(l.get(index.get(i)).x, l.get(index.get(i)).y, l.get(index.get(i)).color);
        }
    }

    private void drawBlock(int x, int y, int c) {
        if (c != -1) canvas.setColor(myColors[c]);
        else canvas.setColor(Color.GRAY);
        canvas.fillRect(leftBorder + (x + 1) * blockSideSize - blockSideSize, topBorder + (y + 1) * blockSideSize - blockSideSize, blockSideSize, blockSideSize);
        canvas.setColor(Color.black);
        canvas.drawRect(leftBorder + (x + 1) * blockSideSize - blockSideSize, topBorder + (y + 1) * blockSideSize - blockSideSize, blockSideSize, blockSideSize);
    }

    private void drawBlocks(ArrayList<Pos> poses) {
        for (int i = 0; i < poses.size(); i++) {
            drawBlock(poses.get(i).x, poses.get(i).y, poses.get(i).color);
        }
    }

    /*
     * Перерисовывает графику на апплете
     */
    public void paint(ArrayList<Pos> poses, String info) {
        paintBackground();
        drawBlocks(poses);
        drawInfo(info);
    }

    private void drawInfo(String info) {
        canvas.clearRect(leftBorder, topBorder + canvasHeight + blockSideSize / 2, canvasWidth, blockSideSize);
        canvas.setColor(Color.black);
        canvas.drawString(info, leftBorder, topBorder + canvasHeight + blockSideSize);
    }

    /*
     * Создает во время паузы мерцание активной фигуры
     */
    public void pause(ArrayList<Pos> poses) {
        paintBackground();
        delay(500);
        drawBlocks(poses);
        delay(500);
    }
}
