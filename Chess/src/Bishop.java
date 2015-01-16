import java.util.List;

public class Bishop extends Piece {


    Bishop(Position position, boolean black) {
        super(position, black);
    }

    @Override
    public List<Position> whereToMove() {
        MaskUnit[] maskVector = {
                new MaskUnit(1, 1),
                new MaskUnit(1, -1),
                new MaskUnit(-1, 1),
                new MaskUnit(-1, -1),
        };

        return getStepsByVectorMask(maskVector);
    }


}
