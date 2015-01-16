import java.util.List;

public class Position {

    private int rank;
    private char file;

    public Position(int rank, char file) {
        this.rank = rank;
        this.file = file;
    }


    static public char getCharOfFileByIndex(int indexOfFile) {
        return Chess.files[indexOfFile];
    }

    static public int getIndexByFileChar(char file) {
        for (int indexOfFile = 0; indexOfFile < Chess.files.length; indexOfFile++) {
            if(file == Chess.files[indexOfFile])
                return indexOfFile;
        }
        return 0;
    }

    static public List<Position> findAndRemove(List<Position> whereSearch, Position toSearch) {
        for (int i = 0; i < whereSearch.size(); i++) {
            if(whereSearch.get(i).file == toSearch.file &&
                    whereSearch.get(i).rank == toSearch.rank) {
                whereSearch.remove(i);
                i = 0;
            }
        }
        return whereSearch;
    }

    public char getFile() {
        return file;
    }

    public int getRank() {
        return rank;
    }
}
