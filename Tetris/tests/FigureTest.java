import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class FigureTest {
    @Test
    public void test() {
        Figure figure = Figure.generateRandom();
        assertEquals(4, figure.data.length);
        assertEquals(4, figure.data[0].length);
    }

    @Test
    public void testCreateFigure() {
        Figure figure = Figure.generateRandom();
        assertNotNull(figure);
        assertFigureDataHasNonZeroValues(figure);
    }

    private void assertFigureDataHasNonZeroValues(Figure figure) {
        for (int i = 0; i < figure.data.length; i++) {
            for (int j = 0; j < figure.data[i].length; j++) {
                if(figure.data[i][j] != 0) {
                    return;
                }
            }
        }

        fail("figure data must contain at least ont non-zero cell");
    }

    @Test
    public void testCreateFigureList() throws Exception {
        assertNotNull(Figure.allFigures);
        assertEquals(19, Figure.allFigures.size());
    }

    @Test
    public void testFlip() throws Exception {
        int [][] data = {
                {0,0,0,0},
                {0,1,1,0},
                {1,1,0,0},
                {0,0,0,0},
        };
        data = Figure.flip(data);

        int [][] expectedData = {
                {0,0,0,0},
                {0,1,1,0},
                {0,0,1,1},
                {0,0,0,0},
        };

        assertEquals(expectedData, data);
    }

    @Test
    public void testRotateLeft() throws Exception {
        int [][] data = {
                {1,0,1,0},
                {0,0,0,0},
                {1,0,1,1},
                {1,1,0,0},
        };
        System.out.println("Before .rotateLeft:");

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                System.out.print(data[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("After .rotateLeft:");

        data = Figure.rotateLeft(data);

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                System.out.print(data[i][j] + " ");
            }
            System.out.println();
        }
        
        int [][] expectedData = {
                {0,0,1,0},
                {1,0,1,0},
                {0,0,0,1},
                {1,0,1,1},
        };

        System.out.println("Expected result after .rotateLeft:");

        for (int i = 0; i < expectedData.length; i++) {
            for (int j = 0; j < expectedData[i].length; j++) {
                System.out.print(expectedData[i][j] + " ");
            }
            System.out.println();
        }

        assertEquals(expectedData, data);
    }

    @Test
    public void testRotateRight() throws Exception {
        int [][] data = {
                {1,0,1,0},
                {0,0,0,0},
                {1,0,1,1},
                {1,1,0,0},
        };
        System.out.println("Before .rotateRight:");

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                System.out.print(data[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("After .rotateRight:");

        data = Figure.rotateRight(data);

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                System.out.print(data[i][j] + " ");
            }
            System.out.println();
        }

        int [][] expectedData = {
                {1,1,0,1},
                {1,0,0,0},
                {0,1,0,1},
                {0,1,0,0},
        };

        System.out.println("Expected result after .rotateRight:");

        for (int i = 0; i < expectedData.length; i++) {
            for (int j = 0; j < expectedData[i].length; j++) {
                System.out.print(expectedData[i][j] + " ");
            }
            System.out.println();
        }

        assertEquals(expectedData, data);
    }
}