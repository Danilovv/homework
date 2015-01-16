import java.util.List;

public class Pawn extends Piece {

    private int steps = 0;

    Pawn(Position position, boolean black) {
        super(position, black);
    }

    @Override
    public void moveTo(Position step) {
        super.moveTo(step);
        steps++;
    }

    @Override
    public List<Position> whereToMove() {
        MaskUnit[] maskForWhite = {
            new MaskUnit(1, -1),
            new MaskUnit(1, 1),
            new MaskUnit(1, 0),
        };
        MaskUnit[] maskForBlack = {
                new MaskUnit(-1, -1),
                new MaskUnit(-1, 1),
                new MaskUnit(-1, 0),
        };

        MaskUnit[] mask = (isBlack()) ? maskForBlack : maskForWhite;

        List<Position> positionsWhereCanMove = getStepsByUnitMask(mask);
        Position checkingPosition;

        for(int i = 0; i < mask.length - 1; i++) {

            int checkingRank = _position.getRank() + mask[i].rankStep;
            int checkingFile = Position.getIndexByFileChar(_position.getFile()) + mask[i].fileStep;

            if(checkingFile < 0 || checkingFile > 7) continue;

            checkingPosition = new Position(checkingRank, Position.getCharOfFileByIndex(checkingFile));

            if(!isPieceOnPosition(checkingPosition)) {
                Position.findAndRemove(positionsWhereCanMove, checkingPosition);
            } else {
                positionsWhereCanMove.add(checkingPosition);
            }
        }

        if(steps == 0) {
            checkingPosition = new Position(
                    ( _position.getRank() + ( (isBlack()) ? -2:+2 ) ), _position.getFile()
            );
            if (!isPieceOnPosition(checkingPosition)) {
                positionsWhereCanMove.add(checkingPosition);
            }
        }

        return positionsWhereCanMove;
    }
}
