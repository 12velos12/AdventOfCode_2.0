package Y2023.D3;

public class PartNumber {
    int number;
    Coordinate startingCoord;

    public PartNumber(int number, Coordinate startingCoord){
        this.number = number;
        this.startingCoord = startingCoord;
    }

    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }

    public Coordinate getStartingCoord() {
        return startingCoord;
    }
    public void setStartingCoord(Coordinate startingCoord) {
        this.startingCoord = startingCoord;
    }
}
