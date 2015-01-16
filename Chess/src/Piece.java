import java.util.ArrayList;
import java.util.List;

abstract class Piece {
    static final List<Piece> pieces = new ArrayList<>();

    Position _position;
    private final boolean _black;

    {
        _position = new Position(1, 'a');
    }

    Piece(Position position, boolean black) {
        _position = position;
        _black = black;
        pieces.add(this);
    }

    abstract public List<Position> whereToMove();

    void moveTo(Position step) {
        _position = step;
    }

    class MaskUnit {
        final int rankStep;
        final int fileStep;

        public MaskUnit(int rankStep, int fileStep) {
            this.rankStep = rankStep;
            this.fileStep = fileStep;
        }
    }

    public Position getPosition() {
        return _position;
    }

    public boolean isBlack() {
        return _black;
    }

    static boolean isPieceOnPosition(Position position) {
        for(Piece piece : pieces) {
            if(position.getRank() == piece._position.getRank() &&
                    position.getFile() == piece._position.getFile()) {
                return true;
            }
        }
        return false;
    }

    List<Position> getStepsByVectorMask(MaskUnit[] mask) {
        List<Position> positionsWhereCanMove = new ArrayList<>();
        Position checkingPosition;

        inside:
        for (MaskUnit maskUnit : mask) {
            for (int rank = _position.getRank() + maskUnit.rankStep,
                         file = Position.getIndexByFileChar(_position.getFile()) + maskUnit.fileStep;
                 rank < Chess.MAX_RANK && rank > Chess.MIN_RANK &&
                         file <= 7 && file >= 0;
                 rank += maskUnit.rankStep,
                         file += maskUnit.fileStep) {

                checkingPosition = new Position(rank, Position.getCharOfFileByIndex(file));
                if (!isPieceOnPosition(checkingPosition)) {
                    positionsWhereCanMove.add(checkingPosition);
                } else {
                    continue inside;
                }

            }
        }
        return positionsWhereCanMove;
    }

    List<Position> getStepsByUnitMask(MaskUnit[] mask) {
        List<Position> positionsWhereCanMove = new ArrayList<>();
        Position checkingPosition;

        for(MaskUnit maskUnit : mask) {
            int checkingRank = _position.getRank() + maskUnit.rankStep;
            int checkingFile = Position.getIndexByFileChar(_position.getFile()) + maskUnit.fileStep;

            if ((checkingRank < Chess.MIN_RANK || checkingRank > Chess.MAX_RANK) ||
                    (checkingFile < 0 || checkingFile > 7)) {
                continue;
            }

            checkingPosition = new Position(checkingRank, Position.getCharOfFileByIndex(checkingFile));
            if(!isPieceOnPosition(checkingPosition))
                positionsWhereCanMove.add(checkingPosition);
        }

        return positionsWhereCanMove;
    }

}
