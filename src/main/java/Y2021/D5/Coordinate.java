package Y2021.D5;

import java.util.Objects;

public class Coordinate {
    private int xCoord;
    private int yCoord;

    public int getxCoord() {
        return xCoord;
    }
    public int getyCoord() {
        return yCoord;
    }
    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }
    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }

    public Coordinate(int xCoord, int yCoord){
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return xCoord == that.xCoord && yCoord == that.yCoord;
    }

    @Override
    public int hashCode() {
        return Objects.hash(xCoord, yCoord);
    }
}
