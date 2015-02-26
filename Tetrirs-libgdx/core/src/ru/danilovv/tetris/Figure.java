package ru.danilovv.tetris;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Figure {
    static int[][] figureI = {
            {0, 1, 0, 0},
            {0, 1, 0, 0},
            {0, 1, 0, 0},
            {0, 1, 0, 0},
    };
    static int[][] figureS = {
            {0, 2, 0, 0},
            {0, 2, 2, 0},
            {0, 0, 2, 0},
            {0, 0, 0, 0},
    };
    static int[][] figureL = {
            {0, 3, 3, 0},
            {0, 3, 0, 0},
            {0, 3, 0, 0},
            {0, 0, 0, 0},
    };
    static int[][] figureT = {
            {0, 4, 0, 0},
            {0, 4, 4, 0},
            {0, 4, 0, 0},
            {0, 0, 0, 0},
    };
    static int[][] figureQ = {
            {0, 0, 0, 0},
            {0, 5, 5, 0},
            {0, 5, 5, 0},
            {0, 0, 0, 0},
    };

    static List<int[][]> allFigures;

    static {
        allFigures = new ArrayList<int[][]>();

        allFigures.add(figureI);
        allFigures.add(rotateLeft(figureI));

        allFigures.add(figureS);
        int[][] flippedS = flip(figureS);
        allFigures.add(flippedS);
        allFigures.add(rotateLeft(figureS));
        allFigures.add(rotateLeft(flippedS));

        allFigures.add(figureL);
        int[][] flippedL = flip(figureL);
        allFigures.add(flippedL);
        allFigures.add(rotateLeft(figureL));
        allFigures.add(rotateLeft(flippedL));
        allFigures.add(rotateRight(figureL));
        allFigures.add(rotateRight(flippedL));
        allFigures.add(rotateRight(rotateRight(figureL)));
        allFigures.add(rotateRight(rotateRight(flippedL)));

        allFigures.add(figureT);
        int[][] flippedT = flip(figureT);
        allFigures.add(flippedT);
        allFigures.add(rotateLeft(figureT));
        allFigures.add(rotateRight(figureT));

        allFigures.add(figureQ);
    }

    int[][] data = new int[4][4];
    //private int[][] _data;

    private Figure() {

    }

    private static Random random = new Random();

    static Figure generateRandom() {
        int figureIndex = random.nextInt(allFigures.size());
        Figure figure = new Figure();
        figure.data = allFigures.get(figureIndex);
        return figure;
    }

    static int[][] flip(int[][] data) { // зеркально поворачивает
        int[][] flippedData = new int[data.length][data[0].length];
        for (int i = 0; i < data.length; i++) {
            for (int jFD = 0, jD = data[i].length - 1;
                 jFD < data[i].length;
                 jFD++, jD--) {
                flippedData[i][jFD] = data[i][jD];
            }
        }
        return flippedData;
    }

    static int[][] rotateLeft(int[][] data) { // влево на 90 градусов
        int[][] rotatedData = new int[data.length][data[0].length];
        for (int rowRD = 0, columnD = rotatedData.length - 1; rowRD < rotatedData.length; rowRD++, columnD--) {
            for (int columnRD = 0, rowD = 0; columnRD < rotatedData[rowRD].length; columnRD++, rowD++) {
                rotatedData[rowRD][columnRD] = data[rowD][columnD];
            }
        }
        return rotatedData;
    }

    static int[][] rotateRight(int[][] data) {
        int[][] rotatedData = new int[data.length][data[0].length];
        for (int rowRD = 0, columnD = 0; rowRD < rotatedData.length; rowRD++, columnD++) {
            for (int columnRD = 0, rowD = rotatedData[columnD].length - 1; columnRD < rotatedData[rowRD].length; columnRD++, rowD--) {
                rotatedData[rowRD][columnRD] = data[rowD][columnD];
            }

        }
        return rotatedData;
    }

    public int[][] getData() {
        return data;
    }
}
