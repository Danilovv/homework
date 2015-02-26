package ru.danilovv.tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class JavaPlatform implements Platform {

    private JFrame _frame;
    private PlatformKeyListener _keyListener;
    private JPanel _panel;

    private static final Color[] colors = {Color.black, Color.blue, Color.red,
            Color.green, Color.yellow, Color.orange};

    public void init(JFrame frame, final PlatformKeyListener keyListener) {
        _frame = frame;
        _keyListener = keyListener;
        _panel = new JPanel();
        _panel.setPreferredSize(new Dimension(400, 700));
        frame.add(_panel);

        _frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT: {
                        _keyListener.moveLeft();
                        break;
                    }
                    case KeyEvent.VK_RIGHT: {
                        _keyListener.moveRight();
                        break;
                    }
                    case KeyEvent.VK_DOWN: {
                        _keyListener.drop();
                        break;
                    }
                    case KeyEvent.VK_UP: {
                        _keyListener.rotate();
                    }
                }
            }
        });
    }

    @Override
    public void clearArea() {
        Graphics2D g = (Graphics2D) _panel.getGraphics();
        g.clearRect(0, 0, _panel.getWidth(), _panel.getHeight());
    }

    @Override
    public int getBackgroundColorIndex() {
        return 0;
    }

    @Override
    public void fillRect(int colorindex, int x, int y, int width, int height) {
        Graphics2D g = (Graphics2D) _panel.getGraphics();
        g.setColor(colors[colorindex]);
        g.fillRect(x, y, width, height);
    }

    @Override
    public void drawRect(int x, int y, int width, int height) {
        Graphics2D g = (Graphics2D) _panel.getGraphics();
        g.setColor(colors[0]);
        g.drawRect(x, y, width, height);
    }

    @Override
    public void drawScore(int score, int x, int y) {
        Graphics2D g = (Graphics2D) _panel.getGraphics();
        g.setColor(Color.BLACK);
        String scoresOut = "Score: " + score;
        g.drawString(scoresOut, x, y);
    }

    @Override
    public int getPlatformWidth() {
        return _panel.getWidth();
    }

    @Override
    public int getPlatformHeight() {
        return _panel.getHeight();
    }

    @Override
    public void setKeyListener(PlatformKeyListener listener) {
        _keyListener = listener;
    }

}
