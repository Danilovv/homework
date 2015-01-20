import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class StateTest {

    private State state;

    @Before
    public void setup() {
        state = new State(20, 10);
    }

    @Test
    public void test() {
        state.init();
        assertNotNull(state.field);
        //assertNull(state.figure);
    }

    @Test
    public void testInit() {
        state.init();
        assertNotNull(state.figure);
        assertEquals(0, state.figureRow);
        assertEquals(state.field.box[0].length/2 - state.figure.data[0].length/2,
                state.figureColumn);
    }

    @Test
    public void testRemoveFullRows() throws Exception {
        State state = new State(5, 3);
        state.field.box = new int[][] {
                {0,0,0},
                {0,0,0},
                {0,0,0},
                {0,2,0},
                {1,1,1},
        };

        state.removeFullRows();
        assertEquals(0, state.field.box[4][0]);
        assertEquals(2, state.field.box[4][1]);
        assertEquals(0, state.field.box[4][2]);

        assertEquals(0, state.field.box[3][0]);
        assertEquals(0, state.field.box[3][1]);
        assertEquals(0, state.field.box[3][2]);
    }

}