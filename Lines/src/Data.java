/*
 *  28-11-2014
 *  Vladimir Danilov
 */



import java.util.ArrayList;
import java.util.Random;

public class Data {
    private int fieldWidth;
    private int fieldHeight;
    private int blockSideSize;
    private int figureHeight;    // размер фигуры
    private int currentLevel;
    private int maxLevel;

    private int Score;
    private int colorsAmount;

    /*
     * Список блоков на поле. Последние элементы соответствуют активной фигуре.
     * Последний элемент соответствует нижней точке фигуры.
     */
    private ArrayList<Pos> listOfBlocks;
    private ArrayList<Integer> listOfBlocksToTrim;    // список блоков для уничтожения.


    Data(int colorsAmount) {
        fieldWidth      = 7;
        fieldHeight     = 15;
        blockSideSize   = 25;
        figureHeight    = 3;
        currentLevel    = 1;
        maxLevel        = 9;
        listOfBlocks    = new ArrayList<Pos>(fieldHeight * fieldWidth / figureHeight);
        listOfBlocksToTrim = new ArrayList<Integer>(figureHeight * 2);
        this.colorsAmount = colorsAmount;
        if (figureHeight < 1)     // Можно менять размер фигуры до одного блока.
            figureHeight = 3;
    }

    private boolean lookForPosAtList(Pos pos) {
        if (listOfBlocks.size() > figureHeight)
            for (int i = 0; i < listOfBlocks.size() - figureHeight; i++)
                if ((pos.x == listOfBlocks.get(i).x) && (pos.y == listOfBlocks.get(i).y)) return true;
        return false;
    }

    private int findIndexAtListByPos(Pos pos) {
        if (listOfBlocks.size() > figureHeight)
            for (int i = 0; i < listOfBlocks.size() - figureHeight; i++)
                if ((pos.x == listOfBlocks.get(i).x) && (pos.y == listOfBlocks.get(i).y)) return i;

        return -1;
    }

    /*
     * Уничтожает блоки, оттаклкиваясь от результатов this.move и направления движения фигуры.
     */
    public boolean action(int vec) {
        if (!move(vec) && (vec == 0 || vec == 1)) {   // Если движение невозможно и направление движения - вниз, значит фигура уперлась в дно поля.
            trimBlocks();
            return false;
        }
        return true;
    }

    /*
     * Проверяет, есть ли блоки, подлежащие удалению.
     */
    public boolean checkIfBlocksToTrim() {
        if (listOfBlocksToTrim.size() > 0) return true;
        return false;
    }

    public ArrayList<Pos> getListOfBlocks() {
        return listOfBlocks;
    }

    /*
     * Очищает список индексов блоков, подлежащих удалению с поля и смещает блоки.
     */
    public void clearTrimmedBlocks() {
        ArrayList<Integer> b = new ArrayList<Integer>(9);
        for (int i = 0; i < listOfBlocksToTrim.size(); i++) {
            b.add(listOfBlocks.get(listOfBlocksToTrim.get(i)).x);
            listOfBlocks.set(listOfBlocksToTrim.get(i), null);
        }
        for (int i = 0; i < b.size(); i++) {
            for (int j = 0; j < b.size(); j++) {
                if (j != i) {
                    if (b.get(i) == b.get(j)) {
                        b.remove(j);
                        j = 0;
                    }
                }
            }
        }
        for (int i = 0; i < listOfBlocks.size(); i++) {
            if (listOfBlocks.get(i) == null) {
                listOfBlocks.remove(i);
                i--;
            }
        }
        Score += listOfBlocksToTrim.size() * currentLevel * 10;
        listOfBlocksToTrim.clear();
        int index;
        int index2;
        Pos bPos;
        for (int j = 0; j < fieldWidth; j++) {                          // Начало смещения блоко.
            for (int i = fieldHeight - 2; i > figureHeight; i--) {      // Перебираются координаты по x и y.
                index = findIndexAtListByPos(new Pos(j, i, 0));         // Если блок с данными координатами пристутствует, то его индекс записывается.
                if (index != -1) {
                    index2 = findIndexAtListByPos(new Pos(listOfBlocks.get(index).x, i + 1, 0));
                    if (index2 == -1) {                                 // Если под блоком пусто, то смещаем его вниз и обнуляем счетчик цикла
                        bPos = listOfBlocks.get(index);
                        bPos.y++;
                        listOfBlocks.set(index, bPos);
                        j = 0;
                        i = fieldHeight - 2;
                    }
                }
            }
        }
    }

    /*
     * Возвращает значение, в зависимости от Level, которое влияет на скорость игры.
     */
    public long getDelay() {
        return 750 - (currentLevel * 50);
    }

    public ArrayList<Integer> getListOfBlocksToTrim() {
        return listOfBlocksToTrim;
    }

    /*
     * Поиск рядов одного цвета
     */
    public void trimBlocks() {
        int[][] d = new int[3][3];                                     // Матрица по которой происходит анализ соседних ячеек.
        for (int i = 0; i < listOfBlocks.size(); i++) {
            int x = listOfBlocks.get(i).x;
            int y = listOfBlocks.get(i).y;
            int c = listOfBlocks.get(i).color;
            d[0][0] = findIndexAtListByPos(new Pos(x - 1, y - 1, 0));  // Поиск соседних элементов по координатам.
            d[0][1] = findIndexAtListByPos(new Pos(x - 1, y, 0));
            d[0][2] = findIndexAtListByPos(new Pos(x - 1, y + 1, 0));
            d[1][0] = findIndexAtListByPos(new Pos(x, y - 1, 0));
            d[1][1] = i;
            d[1][2] = findIndexAtListByPos(new Pos(x, y + 1, 0));
            d[2][0] = findIndexAtListByPos(new Pos(x + 1, y - 1, 0));
            d[2][1] = findIndexAtListByPos(new Pos(x + 1, y, 0));
            d[2][2] = findIndexAtListByPos(new Pos(x + 1, y + 1, 0));
            for (int j = 0; j < 4; j++) {
                if ((d[0][0] != -1) && (d[2][2] != -1))
                    if (listOfBlocks.get(d[0][0]).color == listOfBlocks.get(d[2][2]).color && listOfBlocks.get(d[0][0]).color == c) {
                        listOfBlocksToTrim.add(d[0][0]);
                        listOfBlocksToTrim.add(d[2][2]);
                        listOfBlocksToTrim.add(d[1][1]);
                    }
                if ((d[1][0] != -1) && (d[1][2] != -1))
                    if (listOfBlocks.get(d[1][0]).color == listOfBlocks.get(d[1][2]).color && listOfBlocks.get(d[1][0]).color == c) {
                        listOfBlocksToTrim.add(d[1][0]);
                        listOfBlocksToTrim.add(d[1][2]);
                        listOfBlocksToTrim.add(d[1][1]);
                    }
                if ((d[2][0] != -1) && (d[0][2] != -1))
                    if (listOfBlocks.get(d[2][0]).color == listOfBlocks.get(d[0][2]).color && listOfBlocks.get(d[2][0]).color == c) {
                        listOfBlocksToTrim.add(d[2][0]);
                        listOfBlocksToTrim.add(d[0][2]);
                        listOfBlocksToTrim.add(d[1][1]);
                    }
                if ((d[0][1] != -1) && (d[2][1] != -1))
                    if (listOfBlocks.get(d[0][1]).color == listOfBlocks.get(d[2][1]).color && listOfBlocks.get(d[0][1]).color == c) {
                        listOfBlocksToTrim.add(d[0][1]);
                        listOfBlocksToTrim.add(d[2][1]);
                        listOfBlocksToTrim.add(d[1][1]);
                    }
            }
        }
        for (int i = 0; i < listOfBlocksToTrim.size(); i++) {
            for (int j = 0; j < listOfBlocksToTrim.size(); j++) {
                if (j != i) {
                    if (listOfBlocksToTrim.get(i) == listOfBlocksToTrim.get(j)) {
                        listOfBlocksToTrim.remove(j);
                        j = 0;
                    }
                }
            }
        }
    }

    /*
     * vec = 0 - вниз, 1 - прыжок вниз, 2 - сдвиг цвета вверх,
     * 3 - сдвиг цвета вниз , 4 - влево, 5 - вправо
     */
    private boolean move(int vec) {
        if (!tryToMove(vec)) return false;
        if (vec == 2 || vec == 3) {
            moveColorsOnFigure(vec);
            return true;
        }
        int x = 0;
        int y = 0;
        switch (vec) {
            case 0:
                y++;
                break;
            case 1:
                y++;
                int posY = listOfBlocks.get(listOfBlocks.size() - 1).y;
                int posX = listOfBlocks.get(listOfBlocks.size() - 1).x;
                do {
                    if (lookForPosAtList(new Pos(posX, posY + y, 0)) || ((posY + y) == (fieldHeight))) {
                        y--;
                        break;
                    }
                    y++;
                } while (true);
                break;
            case 4:
                x--;
                break;
            case 5:
                x++;
                break;
            default:
                break;
        }
        Pos[] f = getFigure();
        for (int i = 0; i < f.length; i++) {
            f[i].x += x;
            f[i].y += y;
        }
        setFigure(f);
        return true;
    }

    private Pos[] getFigure() {
        Pos[] f = new Pos[figureHeight];
        for (int i = 0, j = listOfBlocks.size() - figureHeight; i < figureHeight; i++, j++)
            f[i] = listOfBlocks.get(j);
        return f;
    }

    private void setFigure(Pos[] f) {
        for (int i = 0, j = listOfBlocks.size() - figureHeight; i < f.length; i++, j++)
            listOfBlocks.set(j, f[i]);
    }

    private void moveColorsOnFigure(int vec) {
        Pos[] f = getFigure();
        if (vec == 2) {
            int buffColor = f[0].color;
            for (int i = 0; i < f.length - 1; i++)
                f[i].color = f[i + 1].color;
            f[f.length - 1].color = buffColor;
        } else {
            int buffColor = f[f.length - 1].color;
            for (int i = f.length - 1; i > 0; i--)
                f[i].color = f[i - 1].color;
            f[0].color = buffColor;
        }
        for (int i = 0, j = listOfBlocks.size() - figureHeight; i < f.length; i++, j++)
            listOfBlocks.set(j, f[i]);
    }

    private boolean tryToMove(int vec) {
        switch (vec) {
            case 0:
            case 1:
                if ((listOfBlocks.get(listOfBlocks.size() - 1).y) == (fieldHeight - 1)) // если фигура достигла нижней точки
                    return false;
                break;
            case 4:
                if ((listOfBlocks.get(listOfBlocks.size() - 1).x) == 0) // если фигура достигла левой границы
                    return false;
                break;
            case 5:
                if ((listOfBlocks.get(listOfBlocks.size() - 1).x) == (fieldWidth - 1)) // если фигура достигла левой границы
                    return false;
                break;
        }
        switch (vec) {
            case 0:
            case 1:
                if (lookForPosAtList(new Pos(listOfBlocks.get(listOfBlocks.size() - 1).x, listOfBlocks.get(listOfBlocks.size() - 1).y + 1, 0)))
                    return false;
                break;
            case 4:
                for (int i = listOfBlocks.size() - 1; i > listOfBlocks.size() - 1 - figureHeight; i--)
                    if (lookForPosAtList(new Pos(listOfBlocks.get(i).x - 1, listOfBlocks.get(i).y, 0))) // поиск элементов по левую сторону
                        return false;
                break;
            case 5:
                for (int i = listOfBlocks.size() - 1; i > listOfBlocks.size() - 1 - figureHeight; i--)
                    if (lookForPosAtList(new Pos(listOfBlocks.get(i).x + 1, listOfBlocks.get(i).y, 0))) // поиск элементов по левую сторону
                        return false;
                break;
        }
        return true;
    }

    /*
     * Добавление данных о новой фигуре в массив.
     * Если фигуре мешают блоки, то возвращается false.
     */
    public boolean createFigure() {
        for(int j=0;j<fieldWidth;j++){
            if(lookForPosAtList(new Pos(j,figureHeight,0))) return false;
        }
        Random r = new Random();
        int newPosX = getNewFigurePosition();  // получаем кооридану по X для новой фигуры
        for (int i = 0, j = 0; i < figureHeight; i++, j++)
            listOfBlocks.add(new Pos(newPosX, j, r.nextInt(colorsAmount - 1)));
        return true;
    }


    public int getFieldWidth() {
        return fieldWidth;
    }

    public int getFieldHeight() {
        return fieldHeight;
    }

    public int getBlockSideSize() {
        return blockSideSize;
    }

    public void upLevel() {
        if (currentLevel < maxLevel) currentLevel++;
    }

    public void downLevel() {
        if (currentLevel > 1) currentLevel--;
    }

    /*
     * Возвращает количество очков и уровень в виде строки
     */
    public String getInfo() {
        String output = "";
        output += "\nLevel: " + currentLevel;
        output += "\nScore: " + Score;
        return output;
    }

    /*
     * Генерирует координату по X для создания новой фигуры.
     * Если текущий уровень больше результата деления максимально допустимого уровня на два,
     * то генерируется случайное число.
     */
    private int getNewFigurePosition() { // координата зависит от уровня. Если уровень выше среднего, то координата случайна
        if (currentLevel > maxLevel / 2) {
            Random r = new Random();
            return r.nextInt(fieldWidth - 1);
        } else return fieldWidth / 2;
    }
}
