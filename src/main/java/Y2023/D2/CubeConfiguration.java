package Y2023.D2;

public class CubeConfiguration {
    private int red;
    private int green;
    private int blue;

    public void setRed(int red) {
        this.red = red;
    }
    public void setGreen(int green) {
        this.green = green;
    }
    public void setBlue(int blue) {
        this.blue = blue;
    }

    public int getRed() {
        return red;
    }
    public int getGreen() {
        return green;
    }
    public int getBlue() {
        return blue;
    }

    public CubeConfiguration(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

}
