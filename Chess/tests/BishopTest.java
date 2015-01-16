import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BishopTest {

    private int _expectedNumberOfTurns;
    private int _rank;
    private char _file;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(
                new Object[][]{
                        {6, 5, 'd'},
                        {6, 5, 'f'},
                        {5, 2, 'b'},
                }
        );
    }

    public BishopTest(int expectedNumberOfTurns, int rank, char file) {
        _expectedNumberOfTurns = expectedNumberOfTurns;
        _rank = rank;
        _file = file;
    }

    @Test
    public void testWhereToMove() throws Exception {
        Bishop bishop = new Bishop(new Position(_rank, _file), false);
        List<Position> positions = bishop.whereToMove();
        assertEquals(_expectedNumberOfTurns, positions.size());
        Piece.pieces.remove(Piece.pieces.size()-1);
    }
}