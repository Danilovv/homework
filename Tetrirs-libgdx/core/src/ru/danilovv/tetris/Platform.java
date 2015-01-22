package ru.danilovv.tetris;

public interface Platform {

    void setKeyListener(PlatformKeyListener listener);
    void clearArea();
    void drawRect(int x, int y, int width, int height);
    void fillRect(int backgroundColorIndex, int x, int y, int width, int height);
    void drawScore(int score, int x, int y);
    int getBackgroundColorIndex();
    int getPlatformWidth();
    int getPlatformHeight();
}
