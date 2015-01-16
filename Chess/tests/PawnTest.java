import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class PawnTest {

    private int _expectedNumberOfTurns;
    private int _rank;
    private char _file;


    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(
                new Object[][]{
                        {2, 3, 'c'},
                }
        );
    }

    public PawnTest(int expectedNumberOfTurns, int rank, char file) {
        _expectedNumberOfTurns = expectedNumberOfTurns;
        _rank = rank;
        _file = file;
    }

    @Test
    public void testWhereToMove() throws Exception {
        Pawn pawn = new Pawn(new Position(_rank, _file), true);
        List<Position> positions = pawn.whereToMove();
        for(Position position : positions) {
            System.out.println(position.getRank() + " " + position.getFile());
        }
        assertEquals(_expectedNumberOfTurns, positions.size());
        Piece.pieces.remove(Piece.pieces.size()-1);
    }
}