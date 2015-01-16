import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class RookTest {

    private int _expectedNumberOfTurns;
    private int _rank;
    private char _file;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(
                new Object[][]{
                        {10, 5, 'd'},
                        {10, 3, 'a'},
                        {10, 4, 'b'},
                        {0, 1, 'a'},
                }
        );
    }

    public RookTest(int expectedNumberOfTurns, int rank, char file) {
        _expectedNumberOfTurns = expectedNumberOfTurns;
        _rank = rank;
        _file = file;
    }

    @Test
    public void testWhereToMove() throws Exception {
        Rook rook = new Rook(new Position(_rank, _file), false);
        List<Position> positions = rook.whereToMove();
        assertEquals(_expectedNumberOfTurns, positions.size());
        Piece.pieces.remove(Piece.pieces.size() - 1);
    }
}