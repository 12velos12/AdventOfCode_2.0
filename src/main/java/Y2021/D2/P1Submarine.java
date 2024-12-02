package Y2021.D2;

public class P1Submarine implements Submarine {
    private int depth;
    private int horizontalPosition;

    public int getDepth() {
        return depth;
    }
    private void setDepth(int depth) {
        this.depth = depth;
    }

    public int getHorizontalPosition() {
        return horizontalPosition;
    }
    private void setHorizontalPosition(int horizontalPosition) {
        this.horizontalPosition = horizontalPosition;
    }

    public P1Submarine() {
        this.depth = 0;
        this.horizontalPosition = 0;
    }

    public void forward(int units) {
        setHorizontalPosition(getHorizontalPosition() + units);
    }

    public void up(int units){
        setDepth(getDepth() - units);
    }

    public void down(int units){
        setDepth(getDepth() + units);
    }
}
