package Y2021.D2;

public interface Submarine {
    int horizontalPosition = 0;
    int depth = 0;

    int getHorizontalPosition();
    int getDepth();
    void forward(int units);
    void up(int units);
    void down(int units);

}
