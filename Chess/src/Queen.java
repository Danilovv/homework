import java.util.List;

public class Queen extends Piece {
    Queen(Position position, boolean black) {
        super(position, black);
    }

    @Override
    public List<Position> whereToMove() {
        MaskUnit[] maskVector = {
                new MaskUnit(1, 1),
                new MaskUnit(1, -1),
                new MaskUnit(-1, 1),
                new MaskUnit(-1, -1),
                new MaskUnit(0, 1),
                new MaskUnit(0, -1),
                new MaskUnit(1, 0),
                new MaskUnit(-1, 0),
        };

        return getStepsByVectorMask(maskVector);
    }
}
