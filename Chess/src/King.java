import java.util.List;

public class King extends Piece {


    King(Position position, boolean black) {
        super(position, black);
    }

    @Override
    public List<Position> whereToMove() {
        MaskUnit[] mask = {
                new MaskUnit(+1, -1),
                new MaskUnit(+1, 0),
                new MaskUnit(+1, +1),
                new MaskUnit(0, +1),
                new MaskUnit(-1, +1),
                new MaskUnit(-1, 0),
                new MaskUnit(-1, -1),
                new MaskUnit(0, -1),
        };

        return getStepsByUnitMask(mask);
    }
}
