import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class KingTest {

    private int _expectedNumberOfTurns;
    private int _rank;
    private char _file;


    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(
                new Object[][]{
                        { 3, 3, 'a' },
                        { 5, 3, 'd' },
                        { 8, 5, 'f' },
                }
        );
    }

    public KingTest(int expectedNumberOfTurns, int rank, char file) {
        _expectedNumberOfTurns = expectedNumberOfTurns;
        _rank = rank;
        _file = file;
    }

    @Test
    public void testTryToMove() {
        King king = new King(new Position(_rank, _file), false);
        List<Position> positions = king.whereToMove();
        assertEquals(_expectedNumberOfTurns, positions.size());
        Piece.pieces.remove(Piece.pieces.size()-1);

    }

}