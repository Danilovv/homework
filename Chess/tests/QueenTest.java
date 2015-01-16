import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class QueenTest {

    private int _expectedNumberOfTurns;
    private int _rank;
    private char _file;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(
                new Object[][]{
                        {16, 4, 'd'},
                        {15, 5, 'b'},
                }
        );
    }

    public QueenTest(int expectedNumberOfTurns, int rank, char file) {
        _expectedNumberOfTurns = expectedNumberOfTurns;
        _rank = rank;
        _file = file;
    }

    @Test
    public void testWhereToMove() throws Exception {
        Queen queen = new Queen(new Position(_rank, _file), false);
        List<Position> positions = queen.whereToMove();
        assertEquals(_expectedNumberOfTurns, positions.size());
        Piece.pieces.remove(Piece.pieces.size()-1);
    }
}