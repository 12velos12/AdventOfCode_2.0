package Y2019.IntcodeComputerChallenge;

public class PaintingRobotReport {
    private PaintingRobot.PanelColor panelColor;
    private int[] position;
    private boolean haltFlagIsEnabled;

    public PaintingRobotReport(PaintingRobot.PanelColor panelColor, int[] position, boolean haltFlagIsEnabled){
        this.panelColor = panelColor;
        this.position = position;
        this.haltFlagIsEnabled = haltFlagIsEnabled;
    }

    public PaintingRobot.PanelColor getPanelColor() {
        return panelColor;
    }

    public int[] getPosition() {
        return position;
    }

    public boolean isHaltFlagIsEnabled() {
        return haltFlagIsEnabled;
    }

}
