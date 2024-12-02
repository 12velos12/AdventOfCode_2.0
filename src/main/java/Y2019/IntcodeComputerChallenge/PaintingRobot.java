package Y2019.IntcodeComputerChallenge;

import java.util.ArrayList;

public class PaintingRobot {
    private IntcodeComputer comp;
    private int xPos = 0;
    private int yPos = 0;
    Orientation currentOrientation = Orientation.UP;

    enum Orientation{
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    enum PanelColor{
        BLACK,
        WHITE
    }

    public PaintingRobot(ArrayList<Long> intcodeProgram){
        this.comp = new IntcodeComputer(intcodeProgram);
    }

    public PaintingRobot(ArrayList<Long> intcodeProgram, int xPos, int yPos, Orientation startingOrientation){
        this.comp = new IntcodeComputer(intcodeProgram);
        this.xPos = xPos;
        this.yPos = yPos;
        this.currentOrientation = startingOrientation;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public PaintingRobotReport performSingleOperation(PanelColor currentPanelColor){
        int[] startingPosition = new int[]{xPos,yPos};
        PanelColor newPanelColor = determinePanelColor(currentPanelColor);
        moveRobot();
        boolean haltFlagIsEnabled = IntcodeComputerHandler.processUntilIO(comp).haltFlag;
        return new PaintingRobotReport(newPanelColor, startingPosition, haltFlagIsEnabled);
    }

    public PanelColor determinePanelColor(PanelColor currentPanelColor){
        int input = currentPanelColor.equals(PanelColor.BLACK) ? 0 : 1;
        int response = (int) IntcodeComputerHandler.processSimpleIO(comp, input);
        return (response == 0) ? PanelColor.BLACK : PanelColor.WHITE;
    }

    public void moveRobot(){
        IntcodeReport report = IntcodeComputerHandler.processUntilIO(comp);
        if(report.outputValue == 0){
            turnLeft();
        } else {
            turnRight();
        }
        moveForward();
    }

    public void turnLeft(){
        switch (currentOrientation){
            case UP:
                currentOrientation = Orientation.LEFT;
                break;
            case DOWN:
                currentOrientation = Orientation.RIGHT;
                break;
            case LEFT:
                currentOrientation = Orientation.DOWN;
                break;
            case RIGHT:
                currentOrientation = Orientation.UP;
                break;
        }
    }

    public void turnRight(){
        switch (currentOrientation){
            case UP:
                currentOrientation = Orientation.RIGHT;
                break;
            case DOWN:
                currentOrientation = Orientation.LEFT;
                break;
            case LEFT:
                currentOrientation = Orientation.UP;
                break;
            case RIGHT:
                currentOrientation = Orientation.DOWN;
                break;
        }
    }

    public void moveForward(){
        switch (currentOrientation){
            case UP:
                yPos++;
                break;
            case DOWN:
                yPos--;
                break;
            case LEFT:
                xPos--;
                break;
            case RIGHT:
                xPos++;
                break;
        }
    }
}
