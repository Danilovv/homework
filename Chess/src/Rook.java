import java.util.List;

public class Rook extends Piece {
    Rook(Position position, boolean black) {
        super(position, black);
    }

    @Override
    public List<Position> whereToMove() {
        MaskUnit[] maskVector = {
                new MaskUnit(0, 1),
                new MaskUnit(0, -1),
                new MaskUnit(1, 0),
                new MaskUnit(-1, 0),
        };

        return getStepsByVectorMask(maskVector);
    }
}
