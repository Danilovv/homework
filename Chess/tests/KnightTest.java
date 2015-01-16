import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class KnightTest {

        private int _expectedNumberOfTurns;
        private int _rank;
        private char _file;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(
                new Object[][]{
                        {1, 1, 'a'},
                        {2, 3, 'a'},
                }
        );
    }

    public KnightTest(int expectedNumberOfTurns, int rank, char file) {
        _expectedNumberOfTurns = expectedNumberOfTurns;
        _rank = rank;
        _file = file;
    }

    @Test
    public void testTryToMove() {
        Knight knight = new Knight(new Position(_rank, _file), false);
        List<Position> positions = knight.whereToMove();
        assertEquals(_expectedNumberOfTurns, positions.size());
        Piece.pieces.remove(Piece.pieces.size()-1);
    }


}