import java.util.List;

public class Knight extends Piece {

    Knight(Position position, boolean black) {
        super(position, black);
    }

    @Override
    public List<Position> whereToMove() {
        MaskUnit[] mask = {
                new MaskUnit(2, 1),
                new MaskUnit(1, 2),
                new MaskUnit(-1, 2),
                new MaskUnit(-2, 1),
                new MaskUnit(-2, -1),
                new MaskUnit(-1, -2),
                new MaskUnit(1, -2),
                new MaskUnit(2, -1),
        };

        return getStepsByUnitMask(mask);
    }


}
