package Y2021.D4;

public class BingoCoord {
    private int rowIndex;
    private int colIndex;

    public int getRowIndex() {
        return rowIndex;
    }
    public int getColIndex() {
        return colIndex;
    }

    public BingoCoord(int rowIndex, int colIndex){
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
    }

    @Override
    public String toString() {
        return "BingoCoord{" +
                "rowIndex=" + rowIndex +
                ", colIndex=" + colIndex +
                '}';
    }
}
