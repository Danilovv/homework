import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PositionTest {

    @Test
    public void testGetCharOfFileByIndex() throws Exception {
        assertEquals('b', Position.getCharOfFileByIndex(1));
        assertEquals('a', Position.getCharOfFileByIndex(0));
        assertEquals('h', Position.getCharOfFileByIndex(7));
    }

    @Test
    public void testGetIndexByFileChar() throws Exception {
        assertEquals(1, Position.getIndexByFileChar('b'));
        assertEquals(7, Position.getIndexByFileChar('h'));
    }

    @Test
    public void testFindAndRemove() throws Exception {
        List<Position> positions = new ArrayList<>();
        Position testPosition = new Position(2, 'c');
        positions.add(testPosition);

        Position.findAndRemove(positions, testPosition);

        assertEquals(0, positions.size());
    }
}