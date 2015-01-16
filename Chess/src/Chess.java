public class Chess {

    public static final int MIN_RANK = 1;
    public static final int MAX_RANK = 8;
    public static final char[] files = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h' };

    private static final Knight[] knights = new Knight[4];
    private static final Pawn[] pawns = new Pawn[16];
    private static final Bishop[] bishops = new Bishop[4];
    private static final Queen[] queens = new Queen[2];
    private static final King[] kings = new King[2];
    private static final Rook[] rooks = new Rook[4];

    static {
        boolean blackPiece = false;
        int rank = 2;
        char file;

        // PAWNS
        for (int files_index = 0, piece_index = 0; piece_index < pawns.length; files_index++, piece_index++) {

            if(piece_index == 8) {
                blackPiece = true;
                rank = 7;
                files_index = 0;
            }
            file = files[files_index];
            pawns[piece_index] = new Pawn(new Position(rank, file), blackPiece);

        }

        // KNIGHT
        blackPiece = false;
        knights[0] = new Knight(new Position(1, 'b'), blackPiece);
        knights[1] = new Knight(new Position(1, 'g'), blackPiece);
        blackPiece = true;
        knights[2] = new Knight(new Position(8, 'b'), blackPiece);
        knights[3] = new Knight(new Position(8, 'g'), blackPiece);

        // BISHOP
        blackPiece = false;
        bishops[0] = new Bishop(new Position(1, 'c'), blackPiece);
        bishops[1] = new Bishop(new Position(1, 'f'), blackPiece);
        blackPiece = true;
        bishops[2] = new Bishop(new Position(8, 'c'), blackPiece);
        bishops[3] = new Bishop(new Position(8, 'f'), blackPiece);

        // QUEEN
        blackPiece = false;
        queens[0] = new Queen(new Position(1, 'd'), blackPiece);
        blackPiece = true;
        queens[1] = new Queen(new Position(8, 'd'), blackPiece);

        //KING
        blackPiece = false;
        kings[0] = new King(new Position(1, 'e'), blackPiece);
        blackPiece = true;
        kings[1] = new King(new Position(8, 'e'), blackPiece);

        // ROOKS
        blackPiece = false;
        rooks[0] = new Rook(new Position(1, 'a'), blackPiece);
        rooks[1] = new Rook(new Position(1, 'h'), blackPiece);
        blackPiece = true;
        rooks[2] = new Rook(new Position(8, 'a'), blackPiece);
        rooks[3] = new Rook(new Position(8, 'h'), blackPiece);
    }

    public static void main(String[] args) {

        for(Piece piece : Piece.pieces) {
            System.out.println("" + (piece.isBlack() ? "black" : "white") + " " + piece._position.getRank() + " " + piece._position.getFile() + " " + piece.getClass().getSimpleName());
        }

    }

}

    /*


    */
