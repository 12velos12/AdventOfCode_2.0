package Y2021.D2;

public class P2Submarine implements Submarine {
    private int horizontalPostion;
    private int depth;
    private int aim;

    public int getHorizontalPosition() {
        return horizontalPostion;
    }
    private void setHorizontalPostion(int horizontalPostion) {
        this.horizontalPostion = horizontalPostion;
    }

    public int getDepth() {
        return depth;
    }
    private void setDepth(int depth) {
        this.depth = depth;
    }

    public int getAim() {
        return aim;
    }
    private void setAim(int aim) {
        this.aim = aim;
    }

    public P2Submarine(){
        setHorizontalPostion(0);
        setDepth(0);
        setAim(0);
    }

    public void forward(int units) {
        setHorizontalPostion(getHorizontalPosition() + units);
        setDepth(getDepth() + (getAim() * units));
    }

    public void up(int units) {
        setAim(getAim() - units);
    }

    public void down(int units) {
        setAim(getAim() + units);
    }
}
